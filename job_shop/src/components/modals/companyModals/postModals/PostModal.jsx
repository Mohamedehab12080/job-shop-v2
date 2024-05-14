import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { IconButton, MenuItem, Slide } from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axios from "axios";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import {getEmployerFields } from "../../../../store/company/Employer/Action";
import { createPost } from "../../../../store/Post/Action";
import { uploadToCloudnary } from "../../../../Utils/UploadToCloudnary.";
import { library } from '@fortawesome/fontawesome-svg-core';
import { faSpinner, faCheckCircle, faExclamationCircle, faCloudUploadAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const style = {
  position: "absolute",
  top: "10%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "none",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: 4,
  maxHeight: "80vh",
  overflowY: "auto", // Enable scrolling
};

// Array of skills options
var skillsOptions = [];

// Array of qualification options
var qualificationOptions = [];

export default function PostModal({ openPostModal, handleClose }) {
  
  var [selectedSkills, setSelectedSkills] = React.useState([]);
  var [selectedQualifications, setSelectedQualifications] = React.useState(
    []
  );
  const [disabled,setDisabled]=React.useState(false);
  var [filteredSkills, setFilteredSkills] = React.useState(skillsOptions);
  var [filteredQualifications, setFilteredQualifications] =
    React.useState(qualificationOptions);
  const [filterInputSkills, setFilterInputSkills] = React.useState("");
  const [filterInputQualifications, setFilterInputQualifications] =
    React.useState("");
  const [fields, setFields] = React.useState([]);
  const [selectedField, setSelectedField] = React.useState(null);

  const post=useSelector(state=>state.post)
  const dispatch =useDispatch();

  const auth=useSelector(state=>state.auth)
  const dispatch2=useDispatch();

  const emp=useSelector(state=>state.emp)
  const [uploadingImage, setUploadingImage] = React.useState(true);
    const [selectedImage, setSelectedImage] = React.useState("");
    const [uploadProgress, setUploadProgress] = React.useState(0);
    const [uploadStatus, setUploadStatus] = React.useState(null); // 'uploading', 'success', 'error'
  const [postImageUrl,setPostImageUrl]=React.useState("");
  React.useEffect(()=>
{
    if(openPostModal)
    {
      dispatch2(getEmployerFields(auth.user.id))
    }
},[openPostModal,dispatch2,auth.user.id]);

React.useEffect(()=>
{
    if(openPostModal)
    {
      setFields(emp.fields);
    }
},[openPostModal,emp]);

const renderIcon = () => {
  if (uploadStatus === 'uploading') {
    return <FontAwesomeIcon icon="spinner" spin />; // Display spinner icon while uploading
  } else if (uploadStatus === 'success') {
    return <FontAwesomeIcon icon="check-circle" />; // Display checkmark icon on successful upload
  } else if (uploadStatus === 'error') {
    return <FontAwesomeIcon icon="exclamation-circle" />; // Display error icon if upload fails
  } else {
    return <FontAwesomeIcon icon="cloud-upload-alt" />; // Default upload icon
  }
};
  // React.useEffect(() => {
    
  //   // Fetch fields when the component mounts
  //   if(openPostModal)
  //   {
  //     fetchFields();
  //   }
  // }, []); // Empty dependency array ensures the effect runs only once after initial render

  // const fetchFields = async () => {
  //   try {
  //     const response = await axios.get(
  //       "http://localhost:8089/employer/findFields/2"
  //     ); // Replace '/api/findFields/1' with your actual endpoint
  //     setFields(response.data);
  //   } catch (error) {
  //     console.error("Error fetching fields:", error);
  //   }
  // };

  const handleFieldSelect = (value) => {
    const field = fields.find((field) => field.id === Number(value));
    setSelectedField(field);
    console.log("Selected Field ID : ",field.id);
    skillsOptions = field.skills;
    setFilteredSkills(field.skills);
    qualificationOptions = field.qualifications;
    setFilteredQualifications(field.qualifications);
    formik.setFieldValue("field",field.id);
  };
  
    
  const [preview,setPreview]=React.useState("");

  const handleFileUpload=(e)=>
      {
      setUploadProgress(0); // Reset progress when a new file is selected
      setUploadStatus(null); // Reset status
        const file=e.target.files[0];
        var reader=new FileReader();
        reader.onloadend=function(){
          setPreview(reader.result);
        };
        reader.readAsDataURL(file);
      }
        const handleSelectImage = async () => {
          try {
          if(!preview){
            return; 
          }else {
            setUploadingImage(true);
            setUploadStatus('uploading');
            const imgUrl = await uploadToCloudnary(preview ,{
              onUploadProgress: (progressEvent) => {
                  const progress = Math.round((progressEvent.loaded / progressEvent.total) * 100);
                  setUploadProgress(progress);
                },
          });
          setPostImageUrl(imgUrl);
          console.log("image url : ",imgUrl);
          setSelectedImage(imgUrl);
          setUploadingImage(false);
          setUploadStatus('success'); 
          return imgUrl;// Set status to 'success' after successful upload
          }
         
          } catch (error) {
          console.error('Error uploading file:', error);
          setUploadStatus('error'); // Set status to 'error' if upload fails
          }
      };
  
  const handleSubmit = async (values,actions) => {
    const imageUrl = await handleSelectImage();
      if( imageUrl !=="")
      {
        const updatedValues = {
          ...values,
          postImage: imageUrl
        };
        console.log("values : ",updatedValues);
        dispatch(createPost(updatedValues));
        setDisabled(true);
        if(post.response.id !== 0)
        {
          actions.resetForm();
          setSelectedSkills([]);
          setSelectedField("");
          setSelectedQualifications([]);
          setFilteredSkills([]);
          setFilterInputQualifications([]);
          setPreview("");
          setDisabled(false);
        }
        console.log("post Response : ",post.response)
      }else 
      {
        dispatch(createPost(values));
        setDisabled(true);
        if(post.response.id !== 0)
        {
          actions.resetForm();
          setDisabled(false);
        }
        console.log("post Response : ",post.response)
      }
  // else 
  //   {
  //     if(uploadingImage === true)
  //       {
  //           console.log("values : ",values);
  //         dispatch(createPost(values));
  //         setDisabled(true);
  //         if(post.response.id !== 0)
  //         {
  //           // actions.resetForm();
  //           // setPreview("");
  //           setDisabled(false);
  //         }
  //         console.log("post Response : ",post.response)
  //       } 
  //   }
  };

  const validationSchema = Yup.object().shape({
    title: Yup.string().required("Title is required"),
    description: Yup.string().required("Description is required"),
    location: Yup.string().required("Location is required"),
    field: Yup.number().required("Field is required"),
    jobRequirments: Yup.string().required("Job requirements are required"),
    employmentType: Yup.string().required("Employment type is required"),
    experience:Yup.string().required("You must select the experience needed")
  });

  const formik = useFormik({
    initialValues: {
      title: "",
      description: "",
      location: "",
      field:0,
      skills: [],
      qualifications: [],
      jobRequirments: "",
      employmentType: "",
      experience:""
    },
    validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

    
  const handleAddSkill = (skill) => {
    if (!selectedSkills.includes(skill)) {
      const updatedSkills = [...selectedSkills, skill];
      setSelectedSkills(updatedSkills);
      if (updatedSkills) {
        formik.setFieldValue("skills", updatedSkills);
      }
    }
  };

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = selectedSkills.filter(
      (skill) => skill !== skillToRemove
    );
    setSelectedSkills(updatedSkills);
    if (updatedSkills) {
      formik.setFieldValue("skills", updatedSkills);
    }
  };

  const handleFilterSkills = (input) => {
    const filtered = skillsOptions.filter((skill) =>
      skill.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };

  const handleAddQualification = (qualification) => {
    if (!selectedQualifications.includes(qualification)) {
      const updatedQualifications = [...selectedQualifications, qualification];
      setSelectedQualifications(updatedQualifications);
      if (updatedQualifications) {
        formik.setFieldValue("qualifications", updatedQualifications);
      }
    }
  };

  const handleRemoveQualification = (qualificationToRemove) => {
    const updatedQualifications = selectedQualifications.filter(
      (qualification) => qualification !== qualificationToRemove
    );
    setSelectedQualifications(updatedQualifications);
    if (updatedQualifications) {
      formik.setFieldValue("qualifications", updatedQualifications);
    }
  };

  const handleFilterQualifications = (input) => {
    const filtered = qualificationOptions.filter((qualification) =>
      qualification.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredQualifications(filtered);
    setFilterInputQualifications(input);
  };

  const handleClearFilter = () => {
    setFilteredSkills(skillsOptions);
    setFilteredQualifications(qualificationOptions);
    setFilterInputSkills("");
    setFilterInputQualifications("");
  };
  const slideStyle = {
    height: '100%',
    overflowY: 'auto',
    scrollbarWidth: 'none', // Hide scrollbar for Firefox
    '&::-webkit-scrollbar': {
      display: 'none', // Hide scrollbar for Chrome, Safari, Edge
    },
  };
  return (
    <div>
      <Modal
        open={openPostModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
      <Slide
          direction="left"
          in={openPostModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 300 }}
          transitiontimingfunction="ease-in-out" 
          style={slideStyle}
      >
        <Box sx={style}>
          <div className="modal-content-container flex items-center justify-between mb-4">
            <div className="flex items-center space-x-1 space-y-1 text-gray-500">
              <IconButton onClick={handleClose} aria-label="delete" />
              <Close
                style={{
                  cursor: "pointer",
                  transition:
                    "transform 0.3s ease-in-out, background-color 0.3s ease-in-out",
                  borderRadius: 50,
                }}
                onMouseEnter={(e) => {
                  e.target.style.transform = "scale(1.2)";
                  e.target.style.backgroundColor = "#e0e0e0";
                }}
                onMouseLeave={(e) => {
                  e.target.style.transform = "scale(1)";
                  e.target.style.backgroundColor = "transparent";
                }}
              />
              <IconButton />
              <p className="">Make Post</p>
            </div>
          </div>
          <form onSubmit={formik.handleSubmit}>
            <Grid container direction="column" spacing={2}>
                <div className="sticky">
                    <Grid item container justifyContent="center">
                      <Button type="submit" 
                      variant="contained" 
                      color="primary"
                      disabled={disabled}>
                        Save
                      </Button>
                    </Grid>
              </div>
              <Grid item xs={12}>
                <div>
                    {renderIcon()}
                    {uploadStatus === 'uploading' && <span>Uploading: {uploadProgress}%</span>}
                </div>
                </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="title"
                  name="title"
                  label="Title"
                  value={formik.values.title}
                  onChange={formik.handleChange}
                  error={formik.touched.title && Boolean(formik.errors.title)}
                  helperText={formik.touched.title && formik.errors.title}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="description"
                  name="description"
                  label="Description"
                  multiline
                  rows={4}
                  value={formik.values.description}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.description &&
                    Boolean(formik.errors.description)
                  }
                  helperText={
                    formik.touched.description && formik.errors.description
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="location"
                  name="location"
                  label="Location"
                  value={formik.values.location}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.location && Boolean(formik.errors.location)
                  }
                  helperText={formik.touched.location && formik.errors.location}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  select
                  id="field"
                  name="field"
                  label="Select a Field"
                  value={selectedField ? selectedField.id : ""}
                  onChange={(event) => handleFieldSelect(event.target.value)}
                  error={formik.touched.field && Boolean(formik.errors.field)}
                  helperText={formik.touched.field && formik.errors.field}
                  variant="outlined"
                >
                  {fields.map((field) => (
                    <MenuItem key={field.id} value={field.id}>
                      {field.fieldName}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  select
                  id="experience"
                  name="experience"
                  label="Experience"
                  value={formik.values.experience}
                  onChange={formik.handleChange}
                  variant="outlined"
                  error={
                    formik.touched.experience &&
                    Boolean(formik.errors.experience)
                  }
                  helperText={
                    formik.touched.experience &&
                    formik.errors.experience
                  }
                >
                  <MenuItem value="No">No</MenuItem>
                  <MenuItem value="1-2">1-2</MenuItem>
                  <MenuItem value="2-5">2-5</MenuItem>
                  <MenuItem value="5-8">5-8</MenuItem>
                  <MenuItem value="8-10">8-10</MenuItem>
                  <MenuItem value="more than 10">more than 10</MenuItem>
                </TextField>
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="filterSkills"
                  name="filterSkills"
                  label="Filter Skills"
                  value={filterInputSkills}
                  onChange={(e) => handleFilterSkills(e.target.value)}
                  error={
                    formik.touched.filterSkills &&
                    Boolean(formik.errors.filterSkills)
                  }
                  helperText={
                    formik.touched.filterSkills && formik.errors.filterSkills
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <div className="selected-skills-container">
                  {selectedSkills.length > 0 ? (
                    selectedSkills.map((skill, index) => (
                      <div key={index} className="selected-skill">
                        <span>{skill}</span>
                        <IconButton
                          onClick={() => handleRemoveSkill(skill)}
                          aria-label="delete"
                          size="small"
                        >
                          <Close />
                        </IconButton>
                      </div>
                    ))
                  ) : (
                    <div className="error-message">
                      At least one skill is required
                    </div>
                  )}
                </div>
              </Grid>
              <Grid item xs={12}>
                <div
                  className="skills-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputSkills === "" ? skillsOptions : filteredSkills)
                    .filter((skill) => !selectedSkills.includes(skill))
                    .map((skill, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddSkill(skill)}
                      >
                        {skill}
                      </Button>
                    ))}
                </div>
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="filterQualifications"
                  name="filterQualifications"
                  label="Filter Qualifications"
                  value={filterInputQualifications}
                  onChange={(e) => handleFilterQualifications(e.target.value)}
                  error={
                    formik.touched.filterQualifications &&
                    Boolean(formik.errors.filterQualifications)
                  }
                  helperText={
                    formik.touched.filterQualifications &&
                    formik.errors.filterQualifications
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <div className="selected-qualifications-container">
                  {selectedQualifications.length > 0 ? (
                    selectedQualifications.map((qualification, index) => (
                      <div key={index} className="selected-qualification">
                        <span>{qualification}</span>
                        <IconButton
                          onClick={() =>
                            handleRemoveQualification(qualification)
                          }
                          aria-label="delete"
                          size="small"
                        >
                          <Close />
                        </IconButton>
                      </div>
                    ))
                  ) : (
                    <div className="error-message">
                     
                    </div>
                  )}
                </div>
              </Grid>

              <Grid item xs={12}>
                <div
                  className="qualifications-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputQualifications === ""
                    ? qualificationOptions
                    : filteredQualifications
                  )
                    .filter(
                      (qualification) =>
                        !selectedQualifications.includes(qualification)
                    )
                    .map((qualification, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddQualification(qualification)}
                      >
                        {qualification}
                      </Button>
                    ))}
                </div>
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="jobRequirments"
                  name="jobRequirments"
                  label="Job Requirments"
                  multiline
                  rows={4}
                  value={formik.values.jobRequirments}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.jobRequirments &&
                    Boolean(formik.errors.jobRequirments)
                  }
                  helperText={
                    formik.touched.jobRequirments &&
                    formik.errors.jobRequirments
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  select
                  id="employmentType"
                  name="employmentType"
                  label="Employment Type"
                  value={formik.values.employmentType}
                  onChange={formik.handleChange}
                  variant="outlined"
                  error={
                    formik.touched.employmentType &&
                    Boolean(formik.errors.employmentType)
                  }
                  helperText={
                    formik.touched.employmentType &&
                    formik.errors.employmentType
                  }
                >
                  <MenuItem value="Remote">Remote</MenuItem>
                  <MenuItem value="Close">Close</MenuItem>
                </TextField>
              </Grid>
              <Grid item xs={12}>
                <input
                    accept="image/*"
                    id="upload-picture"
                    name="postImage"
                    type="file"
                    onChange={handleFileUpload}
                />
                 <Grid item>
                    {preview && (
                    <div
                        style={{
                        width: 100,
                        height: 100,
                        borderRadius: '50%',
                        overflow: 'hidden',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        }}
                    >
                        <img
                        src={preview}
                        alt=""
                        style={{ maxWidth: '100%', maxHeight: '100%', objectFit: 'cover' }}
                        />
                    </div>
                    )}
                </Grid>
               
            </Grid>
            </Grid>
          </form>
        </Box>
        </Slide>
      </Modal>
    </div>
  );
}
