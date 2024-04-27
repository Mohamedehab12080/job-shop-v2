import React, { useState } from "react";
import { Avatar } from "@mui/material";
import logo from "../common/images/default.jpg";
import { useFormik } from "formik";
import * as Yup from "yup";
import ImageIcon from "@mui/icons-material/Image";
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import TagFacesIcon from '@mui/icons-material/TagFaces';
import Button from '@mui/material/Button';
import PostCard from "./PostCard";
import PostCardJobSeeker from "./PostCardJobSeeker"
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { getJobSeekerMatchedPostsAfterLogin } from "../../store/Auth/Action";
import { fetchMatchedPosts } from "../../store/Post/Action";
const validationSchema = Yup.object().shape({
  content: Yup.string().required("Info is required"),
});

const HomeSection = () => {
  const auth = useSelector(state => state.auth);  
  const post = useSelector(state => state.post);
  const dispatch=useDispatch();
  
React.useEffect(()=>
{
  if(auth.user){
    dispatch(fetchMatchedPosts(auth.user.id))
  }
},[dispatch,auth.user]
)

  const [uploadingImage, setUploadingImage] = useState(false);
  const [selectedImage, setSelectedImage] = useState("");
  const handleSubmit = (values) => {
    console.log("values ", values);
  };

// const [posts,setPosts] =useState([]);

// React.useEffect(() => {
//   fetchPosts();
 
// },[]); // Ensure useEffect runs only once when component mounts


// const fetchPosts=async () =>
// {
//     try
//     {
//         const response = await axios.get(
//             `http://localhost:8089/Post/findPostsWithProfileSkills/${auth.user.id}`
//           ); // Replace '/api/findFields/1' with your actual endpoint
//           setPosts(response.data);
//     }catch(error)
//     {
//         console.error("Error fetching posts : ",error);
//     }
// }

  const formik = useFormik({
    initialValues: {
      content: "",
      image: "",
    },
    onSubmit: handleSubmit,
    validationSchema,
  });

  const handleSelectImage = (event) => {
    setUploadingImage(true);
    const imgUrl = event.target.files[0];
    formik.setFieldValue("image", imgUrl);
    setSelectedImage(imgUrl);
    setUploadingImage(false);
  };

  
  return (
    <div className="space-y-5">
      <section>
        <h1 className="py-7 text-xl font-bold opacity-90">Home</h1>
      </section>
      <section className={`pb-10`}>
        <div className="flex space-x-5">
          <Avatar alt="userName" src={logo} />
          <div className="w-full">
            <form onSubmit={formik.handleSubmit}>
              <div>
                <input
                  type="text"
                  name="content"
                  placeholder="What is happening"
                  className={`border-none outline-none text-xl bg-transparent`}
                  {...formik.getFieldProps("content")}
                />
                {formik.errors.content && formik.touched.content && (
                  <span className="text-red-500">{formik.errors.content}</span>
                )}
              </div>
              <div className="flex justify-between items-center mt-3">
                <div className="flex space-x-5 items-center">
                  <label className="flex items-center space-x-2 rounded-md cursor-pointer">
                    <ImageIcon className="text-[#1d9bf0]" />
                    <input
                      type="file"
                      name="imageFile"
                      className="hidden"
                      onChange={handleSelectImage}
                    />
                  </label>
                  <FmdGoodIcon className="text-[#1d9bf0]"/>
                  <TagFacesIcon className="text-[#1d9bf0]"/>
                </div>
                <div>
                  <Button 
                    sx={{
                      width: "100%",
                      borderRadius: "20px",
                      paddingY:"5px",
                      paddingX:"20px",
                      marginLeft:"20px",
                      py: "10px",
                      bgcolor: "#1e88e5",
                    }}
                    variant="contained"
                    type="submit"
                  >
                    POST
                  </Button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </section>
      <section>
        {post.posts.length > 0 ? (
          post.posts.map(p => (
            auth.user?.userType==="Admin" ? <PostCard key={p.id} /> :(
                <PostCardJobSeeker
                  key={p.id}
                  id={p.id}
                  employerId={p.employerId}
                  employerUserName={p.employerUserName}
                  Title={p.title}
                  description={p.description}
                  jobRequirements={p.jobRequirements} 
                  location={p.location}
                  employmentType={p.employmentType}
                  companyName={p.companyName}
                  profileId={p.profileId}
                  skills={p.postField.skills}
                  qualifications={p.postField.qualifications}
                  field={p.field}
                  employerpicture={p.employerpicture}
                  fieldName={p.postField.employerField.companyField.fieldName}
                  createdDate={p.createdDate}
                  remainedSkills={p.remainedSkills}
                  remainedQualifications={p.remainedQualifications}
                  state={p.state}
                  matchedQulifications={p.matchedQulifications}
                  matchedSkills={p.matchedSkills}
                  applicationCount={p.applicationCount}
                />
              ))
          )) : (
            <p>No posts found.</p>
          )}
      </section>

    </div>
  );
};

export default HomeSection;
