import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { IconButton, MenuItem } from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axios from "axios";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import { createPost } from "../../../store/Post/Action";

const style = {
  position: "absolute",
  top: "50%",
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
  var [filteredSkills, setFilteredSkills] = React.useState(skillsOptions);
  var [filteredQualifications, setFilteredQualifications] =
    React.useState(qualificationOptions);
  const [filterInputSkills, setFilterInputSkills] = React.useState("");
  const [filterInputQualifications, setFilterInputQualifications] =
    React.useState("");
  const [selectedCategory, setSelectedCategory] = React.useState("");
  const [fields, setFields] = React.useState([]);
  const [selectedField, setSelectedField] = React.useState(null);

  const post=useSelector(state=>state.post)
  const dispatch =useDispatch()

  React.useEffect(() => {
    
    // Fetch fields when the component mounts
    if(openPostModal)
    {
      fetchFields();
    }
  }, []); // Empty dependency array ensures the effect runs only once after initial render

  const fetchFields = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8089/employer/findFields/2"
      ); // Replace '/api/findFields/1' with your actual endpoint
      setFields(response.data);
    } catch (error) {
      console.error("Error fetching fields:", error);
    }
  };
  const [categorySkills, setCategorySkills] = React.useState([]);
  const [categoryQualifications, setCategoryQualifications] = React.useState(
    []
  );

  const handleFieldSelect = (event) => {
    const selectedFieldId = event.target.value;
    const field = fields.find((field) => field.id === Number(selectedFieldId));
    setSelectedField(field);
    setCategorySkills(field.skills);
    setCategoryQualifications(field.qualifications);
    const filteredSkills = categorySkills;
    skillsOptions = categorySkills;
    setFilteredSkills(filteredSkills);
    const filteredQualifications = categoryQualifications;
    qualificationOptions = categoryQualifications;
    setFilteredQualifications(filteredQualifications);
    if (selectedField && selectedField.skills && selectedField.qualifications) {
      formik.setFieldValue("field", selectedField.id); // Set the selected field value in Formik state
    }
  };

  const handleSubmit = async (values) => {
    // try {
      console.log("values : ",values);
    //   const response = await axios.post(
    //     "http://localhost:8089/employer/post",
    //     values
    //   );
    //   if (response.status === 200) {
    //     console.log("Form data submitted successfully:", response.data);
    //     formik.resetForm();
    //    setSelectedSkills([]);
    //    setSelectedQualifications([]);
    //   }
    // } catch (error) {
    //   console.error("Error submitting form data : ", error);
    // }
    dispatch(createPost(values));
    console.log("post Response : ",post.response)
  };

  const validationSchema = Yup.object().shape({
    title: Yup.string().required("Title is required"),
    description: Yup.string().required("Description is required"),
    location: Yup.string().required("Location is required"),
    field: Yup.number().required("Field is required"),
    jobRequirments: Yup.string().required("Job requirements are required"),
    employmentType: Yup.string().required("Employment type is required"),
  });

  const formik = useFormik({
    initialValues: {
      title: "",
      description: "",
      location: "",
      employerId:2,
      field: 0,
      skills: [],
      qualifications: [],
      jobRequirments: "",
      employmentType: "",
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

  return (
    <div>
      <Modal
        open={openPostModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
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
              <Grid item container justifyContent="center">
                <Button type="submit" variant="contained" color="primary">
                  Save
                </Button>
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
                  onChange={handleFieldSelect}
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
            </Grid>
          </form>
        </Box>
      </Modal>
    </div>
  );
}
