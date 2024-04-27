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

export default function ApplyModal({
  field,
  postIdd,
  skills=[], // Default value for skills prop
  Qualifications=[], // Default value for Qualifications prop
  openApplyModal,
  handleClose,
  id,
  postRef, // Receive the postRef
}) {
  const [selectedSkills, setSelectedSkills] = React.useState([{skill:"",skillId:0}]);
  const [selectedQualifications, setSelectedQualifications] = React.useState([{qualification:"",qualificationId:0}]);
  const [isLoading, setIsLoading] = React.useState(true);
  const [jobSeekerSkills,setJobSeekerSkills]=React.useState([{skillName:"",skillId:0}]);
  const [jobSeekerQualifications,setJobSeekerQualifications]=React.useState([{qualificationName:"",qualificationId:0}]);
 
  const handleSubmit = async (values) => {
    try {
      console.log("values : ", values);
      const response = await axios.post(
        "http://localhost:8089/jobSeekers/apply",
        values
      );
      if (response.status === 201) {
        console.log("Form data submitted successfully:", response.data);
        formik.resetForm();
        setSelectedSkills([]);
        setSelectedQualifications([]);
        window.location.reload();
        postRef.current.scrollIntoView({ behavior: 'smooth' });
      }
    } catch (error) {
      console.error("Error submitting form data : ", error);
    }
  };

  const formik = useFormik({
    initialValues: {
      applicationSkills: [
        {
          jobSeekerSkillId:0
        }
      ],
      applicationQualifications: [
        {
          jobSeekerQualificationId:0
        }
      ],
      jobSeekerId:id,
      postId:postIdd
    },
    // validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  const fetchFields = async () => {
    try {
      const response=await axios.get(`http://localhost:8089/jobSeekers/findSkills/${id}`);
      if(response.status===200) {
        console.log("Form data submitted successfully:", response.data);
       
        const returnedSkills = skills.map(skillName =>
          {
            const matched=response.data.jobSeekerSkillList.find(
              skill => skill.skillName !==skillName
            )
            if(matched)
            {
              return {
                skillName:matched.skillName,
                skillId:matched.id
              }
            }
          }).filter(obj => obj !== null);
  
          let myListSkills= returnedSkills.reduce((unique , item) => {
            if(!unique.some(obj => obj.id === item.id))
            {
              unique.push(item);
            }
            return unique
          },[]);
          setJobSeekerSkills(myListSkills);
         
        const returnedQualifications = Qualifications.map(qual =>
        {
          const matched=response.data.jobSeekerQualificationsList.find(
            qualification => qualification.qualificationName !==qual
          )
          if(matched)
          {
            return {
              qualificationName:matched.qualificationName,
              qualificationId:matched.id
            }
          }
        }).filter(obj => obj !== null);

        let myList= returnedQualifications.reduce((unique , item) => {
          if(!unique.some(obj => obj.id === item.id))
          {
            unique.push(item);
          }
          return unique
        },[]);
        setJobSeekerQualifications(myList);
        // const qualificationsToAppear = [];
        // const seenQualifications = new Set();
        
        // Qualifications.forEach(qualification => {
        //     const matchingQual = response.data.jobSeekerQualificationsList.find(qual => qual.qualificationName !== qualification);
        //     if (matchingQual && !seenQualifications.has(matchingQual.qualificationName)) {
        //         qualificationsToAppear.push(matchingQual);
        //         seenQualifications.add(matchingQual.qualificationName);
        //     }
        // });
        
        // setJobSeekerQualifications(qualificationsToAppear);

        const updatedQualificationsList = Qualifications.map(qualification => {
          const matchingQualification = response.data.jobSeekerQualificationsList.find(
            qual => qual.qualificationName === qualification
          );
          if (matchingQualification) {
            return {
              qualification: qualification,
              qualificationId: matchingQualification.id
            };
          }
          return null;
        }).filter(obj => obj !== null);
        setSelectedQualifications(updatedQualificationsList);

        const ListOfObjectOfUpdatedQualifications = updatedQualificationsList.map(
          (qual) => ({
            jobSeekerQualificationId: qual.qualificationId,
          })
        );
        formik.setFieldValue("applicationQualifications",ListOfObjectOfUpdatedQualifications);
        
        const updateSkillsList = skills.map(skillq => {
          const matchingSkill = response.data.jobSeekerSkillList.find(
            skill => skill.skillName === skillq
          );
          if (matchingSkill) {
            return {
              skill: skillq,
              skillId: matchingSkill.id
            };
          }
          return null;
        }).filter(obj => obj !== null);
        setSelectedSkills(updateSkillsList);

        const ListOfObjectsOfSkills = updateSkillsList.map(
          (skill) => ({
            jobSeekerSkillId: skill.skillId,
          })
        );
        formik.setFieldValue("applicationSkills",ListOfObjectsOfSkills);
      }
      setIsLoading(false); // Data fetching complete
    } catch (error) {
      console.error("Error fetching fields:", error);
      setIsLoading(false); // Data fetching failed
    }
  };
  React.useEffect(() => {
    if (openApplyModal) {
      fetchFields();
    }
  }, [openApplyModal]);

  
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
  


  const handleAddSkill = (skill,skillId) => {
    if (
      !selectedSkills.some((skill) => skill.skillId === skillId)
    ) {
      const updatedSkills = [
        ...selectedSkills,
        { skill, skillId },
      ];

    // Map the qualificationIds of selectedQualifications to an array for faster lookup
    const selectedSkillsId = updatedSkills.map((skill) => skill.skillId);

    // Filter jobSeekerQualifications to exclude qualifications that are already in selectedQualifications
    const filteredJobSeekerSkills = jobSeekerSkills.filter(
      (skill) => !selectedSkillsId.includes(skill.skillId)
    );

    setJobSeekerSkills(filteredJobSeekerSkills);

      const ListOfObjectOfUpdatedSkills = updatedSkills.map(
        (skill) => ({
          jobSeekerSkillId: skill.skillId,
        })
      );

      setSelectedSkills(updatedSkills);
      formik.setFieldValue("applicationSkills", ListOfObjectOfUpdatedSkills);
    }
  };

  const handleRemoveSkill = (skillToRemove,skillId) => {
    
    const updatedSkills = selectedSkills.filter(
      (skill) => skill.skillId !== skillId
    );
  
    const myObj={
      skillName:skillToRemove,
      skillId:skillId
    }
    jobSeekerSkills.push(myObj);
    setSelectedSkills(updatedSkills);
  
    const ListOfObjectsOfUpdatedSkills = updatedSkills.map((skill) => ({
      jobSeekerSkillId: skill.skillId,
    }));
  
    formik.setFieldValue("applicationSkills", ListOfObjectsOfUpdatedSkills);
  };

  const handleAddQualification = (qualification, qualificationId) => {
    if (
      !selectedQualifications.some((qual) => qual.qualificationId === qualificationId)
    ) {
      const updatedQualifications = [
        ...selectedQualifications,
        { qualification, qualificationId },
      ];

          // Map the qualificationIds of selectedQualifications to an array for faster lookup
    const selectedQualificationIds = updatedQualifications.map((qual) => qual.qualificationId);

    // Filter jobSeekerQualifications to exclude qualifications that are already in selectedQualifications
    const filteredJobSeekerQualifications = jobSeekerQualifications.filter(
      (qual) => !selectedQualificationIds.includes(qual.qualificationId)
    );
      setSelectedQualifications(updatedQualifications);

      setJobSeekerQualifications(filteredJobSeekerQualifications);

      const ListOfObjectOfUpdatedQualifications = updatedQualifications.map(
        (qual) => ({
          jobSeekerQualificationId: qual.qualificationId,
        })
      );
     
// Map the qualificationIds of selectedQualifications to an array for faster lookup
// Filter jobSeekerQualifications to exclude qualifications that are already in selectedQualifications

      formik.setFieldValue("applicationQualifications", ListOfObjectOfUpdatedQualifications);
    }
  };

  const handleRemoveQualification = (qualificationToRemove, qualificationIdToRemove) => {
    const updatedQualifications = selectedQualifications.filter(
      (qualification) => qualification.qualificationId !== qualificationIdToRemove
    );
  
    setSelectedQualifications(updatedQualifications);
    const myObj={
      qualificationName:qualificationToRemove,
      qualificationId:qualificationIdToRemove
    }
    jobSeekerQualifications.push(myObj);

    const ListOfObjectOfUpdatedQualifications = updatedQualifications.map((qualification) => ({
      jobSeekerQualificationId: qualification.qualificationId,
    }));
  
    formik.setFieldValue("applicationQualifications", ListOfObjectOfUpdatedQualifications);
  };

  const [isHovered, setIsHovered] = React.useState(false);

  return (
    <div>
      <Modal
        open={openApplyModal}
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
                          <span>{skill.skill}</span>
                          <IconButton
                            onClick={() => handleRemoveSkill(skill.skill,skill.skillId)}
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
                     {jobSeekerSkills && jobSeekerSkills.length > 0 &&(
                        <div>
                            {jobSeekerSkills.map((skill, index) => ( // Render skills
                            <Button
                                key={index}
                                variant="outlined"
                                onClick={() => handleAddSkill(skill.skillName,skill.skillId)}
                            >
                                {skill.skillName}
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
                          <span>{qualification.qualification}</span>
                          <IconButton
                            onClick={() =>
                              handleRemoveQualification(qualification.qualification,qualification.qualificationId)
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
                 {jobSeekerQualifications && jobSeekerQualifications.length > 0 &&(
                    <div>
                        {jobSeekerQualifications.map((qual, index) => ( // Render skills
                            <Button
                                key={index}
                                variant="outlined"
                                onClick={() => handleAddQualification(qual.qualificationName,qual.qualificationId)}
                            >
                                {qual.qualificationName}
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
