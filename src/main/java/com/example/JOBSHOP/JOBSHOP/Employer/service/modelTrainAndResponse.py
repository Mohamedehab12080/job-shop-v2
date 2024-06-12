from flask import Flask, request, jsonify
import pandas as pd
import json
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, accuracy_score
from sklearn.metrics.pairwise import cosine_similarity
import nltk
import re
from sklearn.preprocessing import LabelEncoder
import string
import os
import numpy as np
import pickle

nltk.download('punkt')
nltk.download('wordnet')
nltk.download('stopwords')

app = Flask(__name__)

def cleanup(sentence):
    cleaned_sentences = []
    for sent in sentence:
        tokenized_words = nltk.word_tokenize(sent)
        remove_punct = [word for word in tokenized_words if word not in string.punctuation]
        cleaned_words = [re.sub(r'[^a-zA-Z0-9]', '', word) for word in remove_punct]
        cleaned_words = [word.lower() for word in cleaned_words if word.strip()]
        cleaned_sentences.append(' '.join(cleaned_words))
    return cleaned_sentences

def convert_to_serializable(obj):
    if isinstance(obj, np.integer):
        return int(obj)
    elif isinstance(obj, np.floating):
        return float(obj)
    elif isinstance(obj, np.ndarray):
        return obj.tolist()
    elif isinstance(obj, pd.Timestamp):
        return obj.isoformat()
    else:
        return str(obj)

def train_and_save_model():
    try:
        current_directory = os.path.dirname(os.path.abspath(__file__))
        csv_file_path = os.path.join(current_directory, 'output.csv')
        data = pd.read_csv(csv_file_path)

        label_encoder = LabelEncoder()
        data['job_title_encoded'] = label_encoder.fit_transform(data['Job Title'])

        vectorizer = TfidfVectorizer(min_df=1, stop_words='english')
        skills_matrix = vectorizer.fit_transform(cleanup(data['skills']))

        X_train, X_test, y_train, y_test = train_test_split(skills_matrix, data['job_title_encoded'], test_size=0.2, random_state=42)
        rf_model = RandomForestClassifier(random_state=42)
        rf_model.fit(X_train, y_train)

        predictions = rf_model.predict(X_test)
        print("Classification Report:")
        print(classification_report(y_test, predictions))

        model_path = os.path.join(current_directory, 'model.pkl')
        vectorizer_path = os.path.join(current_directory, 'vectorizer.pkl')
        label_encoder_path = os.path.join(current_directory, 'label_encoder.pkl')

        with open(model_path, 'wb') as model_file:
            pickle.dump(rf_model, model_file)
        with open(vectorizer_path, 'wb') as vectorizer_file:
            pickle.dump(vectorizer, vectorizer_file)
        with open(label_encoder_path, 'wb') as label_encoder_file:
            pickle.dump(label_encoder, label_encoder_file)

        accuracy = accuracy_score(y_test, predictions)
        return {"accuracy": accuracy}
    except Exception as e:
        return {"error": str(e)}

def load_model():
    try:
        current_directory = os.path.dirname(os.path.abspath(__file__))
        model_path = os.path.join(current_directory, 'model.pkl')
        vectorizer_path = os.path.join(current_directory, 'vectorizer.pkl')
        label_encoder_path = os.path.join(current_directory, 'label_encoder.pkl')

        with open(model_path, 'rb') as model_file:
            model = pickle.load(model_file)
        with open(vectorizer_path, 'rb') as vectorizer_file:
            vectorizer = pickle.load(vectorizer_file)
        with open(label_encoder_path, 'rb') as label_encoder_file:
            label_encoder = pickle.load(label_encoder_file)

        return model, vectorizer, label_encoder
    except FileNotFoundError as fnf_error:
        print(f"File not found: {fnf_error}")
        raise
    except Exception as e:
        print(f"Error loading model: {e}")
        raise
def match_skills_to_jobs(user_skills):
    try:
        current_directory = os.path.dirname(os.path.abspath(__file__))
        csv_file_path = os.path.join(current_directory, 'output.csv')
        data = pd.read_csv(csv_file_path)

        model, vectorizer, label_encoder = load_model()
        skills_matrix = vectorizer.transform(cleanup(data['skills']))

        user_skills_list = cleanup(user_skills.split(','))  # Split user skills and clean them
        user_skills_matrix = vectorizer.transform(user_skills_list)

        similarity_scores = cosine_similarity(user_skills_matrix, skills_matrix)
        
        # Initialize a list to store indices of matched jobs
        matched_indices = []

        # Iterate over each skill similarity score
        for skill_score in similarity_scores:
            # Find indices where the score is above or equal to 0.6
            matched_skill_indices = (skill_score >= 0.6).nonzero()[0]
            matched_indices.extend(matched_skill_indices)

        # Convert to set to remove duplicates
        matched_indices = list(set(matched_indices))

        results = []
        if matched_indices:
            seen = set()
            for index in matched_indices:
                row = data.iloc[index]
                row_dict = row.to_dict()
                row_dict_serializable = {key: convert_to_serializable(value) for key, value in row_dict.items()}
                row_tuple = tuple(sorted(row_dict_serializable.items()))
                if row_tuple not in seen:
                    seen.add(row_tuple)
                    if row['rowType'] == 'Train':
                        results.append(row_dict_serializable)
                    else:
                        results.append({
                            "postId": row_dict_serializable["postId"],
                            "jobTitle": row_dict_serializable["Job Title"],
                            "rowType": row_dict_serializable["rowType"]
                        })

        return json.dumps(results, indent=4)
    except Exception as e:
        return json.dumps({"error": str(e)}, indent=4)
    
@app.route('/train-model', methods=['POST'])
def train_model():
    result = train_and_save_model()
    return jsonify(result)

@app.route('/match-skills', methods=['POST'])
def match_skills():
    try:
        data = request.get_json()
        user_skills_input = data.get('skills', '')
        matched_jobs_json = match_skills_to_jobs(user_skills_input.lower())
        return jsonify(json.loads(matched_jobs_json))
    except Exception as e:
        return jsonify({"error": str(e)})

if __name__ == '__main__':
    app.run(debug=True)
