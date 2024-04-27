import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { MenuItem } from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axios from "axios";
import * as Yup from "yup";
import { IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
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

export default function AddSkillsModal({
  openAddSkillsModal,
  handleClose,
  skills=[], // Default value for skills prop
  Qualifications=[], // Default value for Qualifications prop
  id
}) {
  const [selectedSkills, setSelectedSkills] = React.useState([]);
  const [selectedQualifications, setSelectedQualifications] = React.useState([]);
  const [isLoading, setIsLoading] = React.useState(true);

  const fetchFields = async () => {
    try {
      // Simulate fetching data
      // In actual implementation, replace this with your API calls
      // For demonstration, using setTimeout to mimic asynchronous behavior
      setTimeout(() => {
        setIsLoading(false); // Data fetching complete
      }, 1000);
    } catch (error) {
      console.error("Error fetching fields:", error);
      setIsLoading(false); // Data fetching failed
    }
  };

  React.useEffect(() => {
    fetchFields(); // Fetch data when component mounts
  }, []);

  const handleSubmit = async (values) => {
    try {
      console.log("values : ", values);
      const response = await axios.post(
        "http://localhost:8089/jobSeekers/save-skill",
        values
      );
      if (response.status === 200) {
        console.log("Form data submitted successfully:", response.data);
        formik.resetForm();
        setSelectedSkills([]);
        setSelectedQualifications([]);
      }
    } catch (error) {
      console.error("Error submitting form data : ", error);
    }
  };

//   const validationSchema = Yup.object().shape({
//     skillsOrQualifications: Yup.lazy((value) => {
//       if (!value || (!value.skills && !value.qualifications)) {
//         return Yup.string().required('At least one skill or qualification is required');
//       }
//       return Yup.array(); // If either field is present, return an empty schema
//     }),
//     skills: Yup.array().of(
//       Yup.string().required('Skill is required')
//     ),
//     qualifications: Yup.array().of(
//       Yup.string().required('Qualification is required')
//     ),
//   });
  

  const formik = useFormik({
    initialValues: {
      skills: [],
      qualifications: [],
      userId:id,
    },
    // validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  const handleAddSkill = (skill) => {
    if (!selectedSkills.includes(skill)) {
      const updatedSkills = [...selectedSkills, skill];
      setSelectedSkills(updatedSkills);
      formik.setFieldValue("skills", updatedSkills);
    }
  };

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = selectedSkills.filter(
      (skill) => skill !== skillToRemove
    );
    setSelectedSkills(updatedSkills);
    formik.setFieldValue("skills", updatedSkills);
  };

  const handleAddQualification = (qualification) => {
    if (!selectedQualifications.includes(qualification)) {
      const updatedQualifications = [...selectedQualifications, qualification];
      setSelectedQualifications(updatedQualifications);
      formik.setFieldValue("qualifications", updatedQualifications);
    }
  };

  const handleRemoveQualification = (qualificationToRemove) => {
    const updatedQualifications = selectedQualifications.filter(
      (qualification) => qualification !== qualificationToRemove
    );
    setSelectedQualifications(updatedQualifications);
    formik.setFieldValue("qualifications", updatedQualifications);
  };

  const [isHovered, setIsHovered] = React.useState(false);

  return (
    <div>
      <Modal
        open={openAddSkillsModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className="modal-content-container flex items-center justify-between mb-4">
            <div className="flex items-center space-x-1 space-y-1 text-gray-500">
              <IconButton
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
                onClick={handleClose}
              >
                <CloseIcon style={{ color: isHovered ? 'red' : 'black' }} />
              </IconButton>
              <p className="">Add Remained Skills , Qualificaitons </p>
            </div>
          </div>
          {isLoading ? ( // Show loading indicator while fetching data
            <div>Loading...</div>
          ) : (
            <form onSubmit={formik.handleSubmit}>
              <Grid container direction="column" spacing={2}>
                <Grid item container justifyContent="center">
                  <Button type="submit" variant="contained" color="primary">
                    Save
                  </Button>
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
                     {skills && skills.length > 0 &&(
                        <div>
                            {skills.map((skill, index) => ( // Render skills
                            <Button
                                key={index}
                                variant="outlined"
                                onClick={() => handleAddSkill(skill)}
                            >
                                {skill}
                            </Button>
                            ))}
                        </div>
                     )}
                  </div>
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
                        At least one qualification is required
                      </div>
                    )}
                  </div>
                </Grid>
                <Grid item xs={12}>
                  <div
                    className="skills-scroll-container"
                    style={{ maxHeight: "200px", overflowY: "auto" }}
                  >
                 {Qualifications && Qualifications.length > 0 &&(
                    <div>
                        {Qualifications.map((qual, index) => ( // Render skills
                            <Button
                                key={index}
                                variant="outlined"
                                onClick={() => handleAddQualification(qual)}
                            >
                                {qual}
                            </Button>
                            ))}
                        </div>
                 )}
                  </div>
                </Grid>
              </Grid>
            </form>
          )}
        </Box>
      </Modal>
    </div>
  );
}
