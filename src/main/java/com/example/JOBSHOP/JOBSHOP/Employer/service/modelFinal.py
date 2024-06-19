from flask import Flask, request, jsonify
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, accuracy_score
import nltk
import re
from sklearn.preprocessing import LabelEncoder
import string
import os
import numpy as np
import joblib

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

@app.route('/train-model', methods=['POST'])
def train_model():
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
    accuracy = accuracy_score(y_test, predictions)
    print("Accuracy:", accuracy)
    print("Classification Report:")
    print(classification_report(y_test, predictions))

    model_path = os.path.join(current_directory, 'rf_model.joblib')
    vectorizer_path = os.path.join(current_directory, 'vectorizer.joblib')
    label_encoder_path = os.path.join(current_directory, 'label_encoder.joblib')

    joblib.dump(rf_model, model_path)
    joblib.dump(vectorizer, vectorizer_path)
    joblib.dump(label_encoder, label_encoder_path)

    return jsonify({"message": "Model trained and saved successfully", "accuracy": accuracy}), 200

@app.route('/match-skills', methods=['POST'])
def match_skills():
    current_directory = os.path.dirname(os.path.abspath(__file__))
    model_path = os.path.join(current_directory, 'rf_model.joblib')
    vectorizer_path = os.path.join(current_directory, 'vectorizer.joblib')
    label_encoder_path = os.path.join(current_directory, 'label_encoder.joblib')
    csv_file_path = os.path.join(current_directory, 'output.csv')

    rf_model = joblib.load(model_path)
    vectorizer = joblib.load(vectorizer_path)
    label_encoder = joblib.load(label_encoder_path)

    data = pd.read_csv(csv_file_path)
    user_skills_input = request.get_json().get('skills', '')

    user_skills_matrix = vectorizer.transform(cleanup([user_skills_input]))
    predicted_job_encoded = rf_model.predict(user_skills_matrix)
    predicted_job = label_encoder.inverse_transform(predicted_job_encoded)

    results = []
    for job in predicted_job:
        matched_rows = data[data['Job Title'] == job]
        for _, row in matched_rows.iterrows():
            row_dict = row.to_dict()
            row_dict_serializable = {key: convert_to_serializable(value) for key, value in row_dict.items()}
            results.append(row_dict_serializable)

    return jsonify(results), 200

if __name__ == '__main__':
    app.run(debug=True)
