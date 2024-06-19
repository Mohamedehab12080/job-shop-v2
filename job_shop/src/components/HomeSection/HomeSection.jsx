import React, { useEffect, useState } from "react";
import { Avatar, Box, Grid, Tab, TextField } from "@mui/material";
import logo from "../common/images/default.jpg";
import { useFormik } from "formik";
import * as Yup from "yup";
import ImageIcon from "@mui/icons-material/Image";
import FmdGoodIcon from "@mui/icons-material/FmdGood";
import TagFacesIcon from "@mui/icons-material/TagFaces";
import Button from "@mui/material/Button";
import PostCard from "./posts/PostCard";
import PostCardJobSeeker from "./posts/PostCardJobSeeker";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { getJobSeekerMatchedPostsAfterLogin } from "../../store/Auth/Action";
import {
  fetchCompanyPosts,
  fetchEmployerPosts,
  fetchMatchedPosts,
} from "../../store/Post/Action";
import PostCardSocial from "./posts/PostCardSocial";
import PostCardCompany from "./posts/PostCardCompany";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import { uploadToCloudnary } from "../../Utils/UploadToCloudnary.";
import { recommendedPosts } from "../../store/Post/recommededPost/Action";
// import { uploadToCloudnary } from "../../Utils/UploadToCloudnary.";
// import { responseUploadUrl } from "../../Utils/Server";
import SearchIcon from "@mui/icons-material/Search";
import { useLocation, useNavigate } from "react-router-dom";

const validationSchema = Yup.object().shape({
  content: Yup.string().required("Info is required"),
});

const HomeSection = () => {
  const [tabValue, setTabValue] = useState("1");
  const auth = useSelector((state) => state.auth);
  const post = useSelector((state) => state.post);
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();
  const [jobSeekerPosts, setJobSeekerPosts] = useState([]);
  const [companyPosts, setCompanyPosts] = useState([]);
  const [jobSeekerPostsUserPosts, setJobSeekerPostsUserPosts] = useState([]);
  const [companyPostsUserPosts, setCompanyPostsUserPosts] = useState([]);
  const [preview, setPreview] = useState("");
  const [statusOfPosts, setStatusOfPosts] = useState(false);
  const [filteredJobSeekerPosts, setFilteredJobSeekerPosts] = useState([]);
  const [filteredCompanyPosts, setFilteredCompanyPosts] = useState([]);
  const [filterInputPost, setFilterInputPost] = React.useState("");

  const dispatch4 = useDispatch();

  // useEffect(() => {
  //   if (location.pathname === "signin" || location.pathname === "signup") {
  //     navigate("/");
  //   }
  // }, [auth]);
  const handleChange = (event, newValue) => {
    setTabValue(newValue);

    if (newValue === "2") {
      console.log("Clicked ");
      handleFetchPosts();
      // Call handleFetchPosts when the second tab is clicked
      if (auth.user.userType === "jobSeeker") {
        // dispatch2()
        // setJobSeekerPostsUserPosts(post.posts);
      } else {
        if (auth.user.userType === "Employer") {
          dispatch2(fetchEmployerPosts(auth.user.id));
          setCompanyPostsUserPosts(post.userPosts);
        }
      }
    }
  };
  const handleFilterPosts = (input) => {
    if (auth.user.userType === "jobSeeker") {
      const filtered = jobSeekerPosts.filter((post) => {
        return post.title.toLowerCase().includes(input.toLowerCase());
      });
      setFilteredJobSeekerPosts(filtered);
      setFilterInputPost(input);
    } else {
      const filtered = companyPosts.filter((post) => {
        return post.title.toLowerCase().includes(input.toLowerCase());
      });
      setFilteredCompanyPosts(filtered);
      setFilterInputPost(input);
    }
  };
  const handleFetchPosts = () => {
    setStatusOfPosts(true); // Set statusOfPosts to true on button click
  };

  React.useEffect(() => {
    if (auth.user && auth.user.userType && auth.user.id) {
      if (auth.user.userType === "jobSeeker") {
        dispatch(fetchMatchedPosts(auth.user.id));
      } else {
        dispatch(fetchCompanyPosts(auth.user.id));
      }
    }
  }, [dispatch, auth.user]);

  React.useEffect(() => {
    if (auth.user.userType === "jobSeeker") {
      setJobSeekerPosts(post.posts);
      console.log("POSTS : ", post.posts);
    } else {
      setCompanyPosts(post.posts);
    }
  }, [post, auth.user.userType]);

  const [uploadingImage, setUploadingImage] = useState(false);
  const [selectedImage, setSelectedImage] = useState("");
  const handleSubmit = (values) => {
    console.log("values ", values);
  };

  const formik = useFormik({
    initialValues: {
      content: "",
      image: "",
    },
    onSubmit: handleSubmit,
    validationSchema,
  });

  const handleFileUpload = (e) => {
    console.log(e.target.files[0]);
    const file = e.target.files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
      setPreview(reader.result);
    };
    reader.readAsDataURL(file);
  };

  // const handleSendFile=async(e)=>
  //   {
  //     e.preventDefault();
  //     if(!preview) return;
  //     try {
  //       const res=await axios.post("http://localhost:8000/upload",{
  //         image_url:preview,
  //       });
  //       console.log("response : ",res);
  //     } catch (error) {
  //       console.log("Error in handleSendFile : ",error);
  //     }
  //   };

  const handleSelectImage = async (event) => {
    setUploadingImage(true);
    event.preventDefault();
    if (!preview) return;
    const imgUrl = await uploadToCloudnary(preview);
    formik.setFieldValue("image", imgUrl);
    console.log("image url : ", imgUrl);
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
          <Avatar alt="userName" src={auth.user.picture} />
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
                      onChange={handleFileUpload}
                    />
                  </label>
                  <FmdGoodIcon className="text-[#1d9bf0]" />
                  <TagFacesIcon className="text-[#1d9bf0]" />
                </div>
                <div>
                  <Button
                    sx={{
                      width: "100%",
                      borderRadius: "20px",
                      paddingY: "5px",
                      paddingX: "20px",
                      marginLeft: "20px",
                      py: "10px",
                      bgcolor: "#1e88e5",
                    }}
                    variant="contained"
                    type="submit"
                    onClick={handleSelectImage}
                  >
                    POST
                  </Button>
                </div>
              </div>
            </form>
            <div>{preview && <img src={preview} alt="" />}</div>
          </div>
        </div>
      </section>
      <div className="relative flex items-center">
        <Grid item xs={12}>
          <TextField
            fullWidth
            id="filterInputPost"
            name="filterInputPost"
            label="Filter Posts"
            value={filterInputPost}
            onChange={(e) => handleFilterPosts(e.target.value)}
          />
        </Grid>
        {/* <div className='absolute top-0 left-0 pl-3 pt-3'>
        <SearchIcon className='text-gray-500'/>
    </div>         */}
      </div>

      <section className="py-5">
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={tabValue}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
                <Tab label="Job Posts" value="1" />
                <Tab label="Your Posts" value="2" />
              </TabList>
            </Box>
            <TabPanel key="1" value="1">
              <section>
                {(filterInputPost === ""
                  ? jobSeekerPosts
                  : filteredJobSeekerPosts
                ).length > 0 ? (
                  (filterInputPost === ""
                    ? jobSeekerPosts
                    : filteredJobSeekerPosts
                  ).map(
                    (p, index) =>
                      p.companyName !== "" && (
                        <PostCardJobSeeker
                          key={index}
                          id={p.id}
                          employerId={p.employerId}
                          employerUserName={p.employerUserName}
                          Title={p.title}
                          description={p.description}
                          jobRequirements={p.jobRequirments}
                          location={p.location}
                          employmentType={p.employmentType}
                          companyName={p.companyName}
                          profileId={p.profileId}
                          skills={p.skills}
                          qualifications={p.qualifications}
                          field={p.field}
                          employerpicture={p.employerpicture}
                          fieldName={p.fieldName}
                          createdDate={p.createdDate}
                          remainedSkills={p.remainedSkills}
                          remainedQualifications={p.remainedQualifications}
                          state={p.state}
                          matchedQulifications={p.matchedQulifications}
                          matchedSkills={p.matchedSkills}
                          applicationCount={p.applicationCount}
                          jobSeekerId={auth.user.id}
                          Experience={p.experience}
                          postImage={p.postImage}
                          statusCode={p.statuseCode}
                        />
                      )
                  )
                ) : (filterInputPost === ""
                    ? companyPosts
                    : filteredCompanyPosts
                  ).length > 0 ? (
                  (filterInputPost === ""
                    ? companyPosts
                    : filteredCompanyPosts
                  ).map((p, index) => (
                    <PostCardCompany
                      key={index}
                      id={p.id}
                      employerId={p.employerId}
                      employerUserName={p.employerUserName}
                      Title={p.title}
                      statusCode={p.statuseCode}
                      description={p.description}
                      jobRequirements={p.jobRequirments}
                      location={p.location}
                      employmentType={p.employmentType}
                      companyName={p.companyName}
                      profileId={p.profileId}
                      skills={p.skills}
                      qualifications={p.qualifications}
                      field={p.field}
                      employerpicture={p.employerpicture}
                      fieldName={p.fieldName}
                      createdDate={p.createdDate}
                      remainedSkills={p.remainedSkills}
                      remainedQualifications={p.remainedQualifications}
                      state={p.state}
                      matchedQulifications={p.matchedQulifications}
                      matchedSkills={p.matchedSkills}
                      applicationCount={p.applicationCount}
                      Experience={p.experience}
                      postImage={p.postImage}
                    />
                  ))
                ) : (
                  <></> // Render nothing if there are no company posts to display
                )}

                {/* {post.posts.length > 0 ? (
          post.posts.map(p => (
            auth.user?.userType==="Admin" ? <PostCard key={p.id} /> :(
                <PostCardJobSeeker
                  key={p.id}
                  id={p.id}
                  employerId={p.employerId}
                  employerUserName={p.employerUserName}
                  Tit le={p.title}
                  description={p.description}
                  jobRequirements={p.jobRequirments} 
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
          )} */}
              </section>
            </TabPanel>
            <TabPanel key="2" value="2">
              {statusOfPosts && (
                <section key={2}>
                  {(filterInputPost === ""
                    ? jobSeekerPostsUserPosts
                    : filteredJobSeekerPosts
                  ).length > 0 ? (
                    (filterInputPost === ""
                      ? jobSeekerPosts
                      : filteredJobSeekerPosts
                    ).map(
                      (p, index) =>
                        p.companyName !== "" && (
                          <PostCardJobSeeker
                            key={index}
                            id={p.id}
                            employerId={p.employerId}
                            employerUserName={p.employerUserName}
                            Title={p.title}
                            description={p.description}
                            jobRequirements={p.jobRequirments}
                            location={p.location}
                            employmentType={p.employmentType}
                            companyName={p.companyName}
                            profileId={p.profileId}
                            skills={p.skills}
                            qualifications={p.qualifications}
                            field={p.field}
                            employerpicture={p.employerpicture}
                            fieldName={p.fieldName}
                            createdDate={p.createdDate}
                            remainedSkills={p.remainedSkills}
                            remainedQualifications={p.remainedQualifications}
                            state={p.state}
                            matchedQulifications={p.matchedQulifications}
                            matchedSkills={p.matchedSkills}
                            applicationCount={p.applicationCount}
                            jobSeekerId={auth.user.id}
                            Experience={p.experience}
                            postImage={p.postImage}
                            statusCode={p.statuseCode}
                          />
                        )
                    )
                  ) : (filterInputPost === ""
                      ? companyPostsUserPosts
                      : filteredCompanyPosts
                    ).length > 0 ? (
                    (filterInputPost === ""
                      ? companyPostsUserPosts
                      : filteredCompanyPosts
                    ).map((p, index) => (
                      <PostCardCompany
                        key={index}
                        id={p.id}
                        employerId={p.employerId}
                        employerUserName={p.employerUserName}
                        Title={p.title}
                        description={p.description}
                        jobRequirements={p.jobRequirments}
                        location={p.location}
                        employmentType={p.employmentType}
                        companyName={p.companyName}
                        profileId={p.profileId}
                        skills={p.skills}
                        qualifications={p.qualifications}
                        field={p.field}
                        employerpicture={p.employerpicture}
                        fieldName={p.fieldName}
                        createdDate={p.createdDate}
                        remainedSkills={p.remainedSkills}
                        remainedQualifications={p.remainedQualifications}
                        state={p.state}
                        statusCode={p.statuseCode}
                        matchedQulifications={p.matchedQulifications}
                        matchedSkills={p.matchedSkills}
                        applicationCount={p.applicationCount}
                        Experience={p.experience}
                        postImage={p.postImage}
                      />
                    ))
                  ) : (
                    <></> // Render nothing if there are no company posts to display
                  )}

                  {/* {post.posts.length > 0 ? (
           post.posts.map(p => (
             auth.user?.userType==="Admin" ? <PostCard key={p.id} /> :(
                 <PostCardJobSeeker
                   key={p.id}
                   id={p.id}
                   employerId={p.employerId}
                   employerUserName={p.employerUserName}
                   Tit le={p.title}
                   description={p.description}
                   jobRequirements={p.jobRequirments} 
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
           )} */}
                </section>
              )}
            </TabPanel>
          </TabContext>
        </Box>
      </section>
    </div>
  );
};

export default HomeSection;
