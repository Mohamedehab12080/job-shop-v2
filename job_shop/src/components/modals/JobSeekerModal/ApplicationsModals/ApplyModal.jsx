import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { MenuItem, Typography } from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axios from "axios";
import * as Yup from "yup";
import { IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { useDispatch, useSelector } from "react-redux";
import { findJobSeekerSkillsQualifications } from "../../../../store/JobSeeker/Action";
import ShowApplicationResultsModal from "./ShowApplicationResults";
import { applyForPost, showPostsAfterApply } from "../../../../store/Post/Action";
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
  const [openShowApplicationResultsModal,setOpenShowApplicationResultsModal]=React.useState(false);
  const handleOpenShowApplicationResultsModal=()=>setOpenShowApplicationResultsModal(true);
  const handleCloseShowApplicationResultsModal=()=>setOpenShowApplicationResultsModal(false);
  const auth=useSelector(state=>state.auth);
  const post=useSelector(state=>state.post);
  const jobSeeker=useSelector(state=>state.jobSeeker);
  const dispatch=useDispatch();
  const dispatch2=useDispatch();
  const dispatch3=useDispatch();
  const [disable,setDisable]=React.useState(false);
  // const [selectedExperience, setSelectedExperience] = useState("");
  React.useEffect(()=>
{
    if(openApplyModal)
    {
      dispatch(findJobSeekerSkillsQualifications(auth.user.id));
    }

},[dispatch,openApplyModal,auth.user.id]);

const handleCloseAndDeleteAppliedPost=()=>{
  if(post.stateOfApplication)
  {
    dispatch3(showPostsAfterApply(postIdd));
  }
  handleClose();
}
  const handleSubmit = async (values) => {
      dispatch2(applyForPost(values));
      setDisable(!post.stateOfApplication);
      handleOpenShowApplicationResultsModal(true);
  };
  const validationSchema = Yup.object().shape({
    experience:Yup.string().required("You must select the experience needed"),
    postId:Yup.string().required("This post deleted")
});
  const formik = useFormik({
    initialValues: {
      experience:"",
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
      postId:postIdd,
    },
    validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  React.useEffect(()=>
{
  if(openApplyModal)
  {
    const returnedSkills = jobSeeker.skillsObjects
    .filter(skill => !skills.includes(skill.skillName))
    .map(({ skillName, id }) => ({ skillName, skillId: id }));
  
    console.log('Returned Skills:', returnedSkills);
    // const myListSkills = returnedSkills.reduce((unique, item) => {
    //   if (item && item.skillId && !unique.some(obj => obj.skillId === item.skillId)) {
    //     unique.push(item);
    //   }
    // return unique;
    // },[]);
    setJobSeekerSkills(returnedSkills);  
    
  
  
    const returnedQualifications=jobSeeker.qualificationsObjects
    .filter(qual=>!Qualifications.includes(qual.qualificationName))
    .map(({qualificationName,id})=>({qualification:qualificationName,qualificationId:id}));
  // const myList = returnedQualifications.reduce((unique, item) => {
  //   if (!unique.some(obj => obj.qualificationId === item.qualificationId)) {
  //     unique.push(item);
  //   }
  //   return unique;
  // },[]);
  console.log("Returned Qualifications : ",returnedQualifications)
  setJobSeekerQualifications(returnedQualifications);
  
  const updatedQualificationsList = Qualifications.map(qualification => {
  const matchingQualification = jobSeeker.qualificationsObjects.find(
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
  const matchingSkill =jobSeeker.skillsObjects.find(
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
},[openApplyModal,jobSeeker]);

  
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
        onClose={handleCloseAndDeleteAppliedPost}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className="modal-content-container flex items-center justify-between mb-4">
            <div className="flex items-center space-x-1 space-y-1 text-gray-500">
              <IconButton
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
                onClick={handleCloseAndDeleteAppliedPost}
              >
                <CloseIcon style={{ color: isHovered ? 'red' : 'black' }} />
              </IconButton>
              <p className="">Apply for this job </p>
            </div>
          </div>
          
            <form onSubmit={formik.handleSubmit}>
              <Grid container direction="column" spacing={2}>
                <Grid item container justifyContent="center">
                  <Button type="submit" variant="contained"
                  disabled={disable}
                  color="primary">
                    Save
                  </Button>
                </Grid>
                <Grid container direction="row" spacing={2} item xs={12}>
                <Typography variant="subtitle1" fontWeight="bold" mb={1}>
                     ** skills for apply :
                </Typography>
                  <div className="ml-3 text-xl selected-skills-container">
                    {selectedSkills.length > 0 ? (
                      selectedSkills.map((skill, index) => (
                        <div key={index} className="selected-skill">
                          <span>- {skill.skill}</span>
                          {!skills.includes(skill.skill) &&(
                            <IconButton
                            onClick={() => handleRemoveSkill(skill.skill,skill.skillId)}
                            aria-label="delete"
                            size="small"
                          >
                            <Close />
                          </IconButton>
                          )}
                        </div>
                      ))
                    ) : jobSeekerSkills.length > 0 && (
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

                <Grid container direction="row" item xs={12}>
                <Typography variant="subtitle1" fontWeight="bold" mb={1}>
                     ** Qualifications for apply :
                </Typography>
                  <div className="ml-3 text-xl selected-qualifications-container">
                    {selectedQualifications.length > 0 ? (
                      selectedQualifications.map((qualification, index) => (
                        <div key={index} className="selected-qualification">
                          <span>- {qualification.qualification}</span>
                          {!Qualifications.includes(qualification.qualification) &&(
                            <IconButton
                            onClick={() =>
                              handleRemoveQualification(qualification.qualification,qualification.qualificationId)
                            }
                            aria-label="delete"
                            size="small"
                          >
                            <Close />
                          </IconButton>
                          )}
                        </div>
                      ))
                    ) : jobSeekerQualifications.length > 0 && (
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

              </Grid>
              <section>
                <ShowApplicationResultsModal 
                openShowApplicationResultsModal={openShowApplicationResultsModal} 
                handleCloseShowApplicationResultsModal={handleCloseShowApplicationResultsModal}/>
              </section>
            </form>
            
          
        </Box>
      </Modal>
    </div>
  );
}
