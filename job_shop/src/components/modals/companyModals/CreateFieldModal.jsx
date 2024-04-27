import React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { IconButton, MenuItem } from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import { useEffect } from "react";
import {createField, getAllFields} from "../../../store/company/Action"
import {findAllSkills} from "../../../store/skills/Action"
import axios from "axios";
import Header from "../../Header";
import { getQualifications } from "../../../store/qualifications/Action";

export default function CreateFieldModal({
  openCreateFieldModal,
  handleCloseCreateFieldModal,
}) {
  // const jwt=localStorage.getItem("jwt");
  var [selectedSkills, setSelectedSkills] = React.useState([]);
  var [filteredSkills, setFilteredSkills] = React.useState([]);
  const [filterInputSkills, setFilterInputSkills] = React.useState("");
  const comp=useSelector(state=>state.comp)
  const auth = useSelector(state => state.auth);
  const dispatch=useDispatch();
  const dispatch2=useDispatch();
  const dispatch3=useDispatch();
  const skills=useSelector(state=>state.skills)
  const quals=useSelector(state=>state.quals)
  const [fields,setFields]=useState([])
  const [fetchedSkills,setFetchedSkills]=useState([])
  const [displayedSkills,setDisplayedSkills]=useState([])
  const [fetchedQuals,setFetchedQuals]=useState([])
  const [displayedQuals,setDisplayedQuals]=useState([])
  var [selectedQuals, setSelectedQuals] = React.useState([]);
  var [filteredQuals, setFilteredQuals] = React.useState([]);
  const [filterInputQuals, setFilterInputQuals] = React.useState("");
  
useEffect(() => {
  if(openCreateFieldModal)
  {
    dispatch2(findAllSkills());
    dispatch3(getQualifications());
    handleSetSkills();
    handleSetQuals();
    setDisplayedSkills(fetchedSkills.slice(0,3))
    setDisplayedQuals(fetchedQuals.slice(0,3))
  }
}, [openCreateFieldModal, dispatch2,dispatch3,getQualifications,fetchedQuals,fetchedSkills]);
const handleSetSkills = () => {
  if (skills && skills.skills) {
    setFetchedSkills(skills.skills);
    console.log("skills : ", skills.skills);
  }
};
const handleSetQuals = () => {
  if (quals && quals.quals) {
    setFetchedQuals(quals.quals);
    console.log("quals : ", quals.quals);
  }
};
// React.useEffect(()=>
// {
//   if(openCreateFieldModal)
//   {
//     fetchSkills();
//   }
// });
// const fetchSkills=async()=>
// {
//   try {
//     const response =await axios.get(`http://localhost:8089/api/skills/findAllJWT`,
//   {headers:{
//     "Authorization":`Bearer ${jwt}`
//   }
// });
//     console.log("skills from kosom el back : ",response.data)
//     // setFetchedSkills(response.data);
//   } catch (error) {
//     console.error("Error fetching fields:", error);
//   }
// }

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
  const filtered = fetchedSkills.filter((skill) =>
    skill.toLowerCase().includes(input.toLowerCase())
  );
  setFilteredSkills(filtered);
  setFilterInputSkills(input);
};

const handleAddQual = (qual) => {
  if (!selectedQuals.includes(qual)) {
    const updatedQuals = [...selectedQuals, qual];
    setSelectedQuals(updatedQuals);
    if (updatedSkills) {
      formik.setFieldValue("qualifications", updatedSkills);
    }
  }
};

const handleRemoveQual = (qualToRemove) => {
  const updatedQuals = selectedQuals.filter(
    (qual) => qual !== qualToRemove
  );
  setSelectedQuals(updatedQuals);
  if (updatedQuals) {
    formik.setFieldValue("qualifications", updatedQuals);
  }
};

const handleFilterQuals = (input) => {
  const filtered = fetchedQuals.filter((qual) =>
    qual.toLowerCase().includes(input.toLowerCase())
  );
  setFilteredQuals(filtered);
  setFilterInputQuals(input);
};

const handleFieldDelete=()=>
{
  
}
  const formik = useFormik({
    initialValues: {
      fieldName: '',
      companyAdministratorId:auth.user.id,
      qualifications:[],
      skills:[],
      qualifications:[]
     }, // Initialize field with an empty string or appropriate initial value    },
    onSubmit: (values) => {
      dispatch(createField(values))
    },
  });

  return (
    <Modal
      open={openCreateFieldModal}
      onClose={handleCloseCreateFieldModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box
        sx={{
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
          overflowY: "auto",
        }}
      >
        <div className="modal-content-container flex items-center justify-between mb-4">
          <IconButton onClick={handleCloseCreateFieldModal} aria-label="delete">
            <Close />
          </IconButton>
          <p className="">Make Post</p>
        </div>
        <form onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
        <Grid item xs={12}>
              <TextField
                fullWidth
                id="fieldName"
                name="fieldName"
                label="Field Name"
                value={formik.values.fieldName}
                onChange={formik.handleChange}
                variant="outlined"
              >
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
                  {(filterInputSkills === "" ? displayedSkills : filteredSkills)
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
                  id="filterQuals"
                  name="filterQuals"
                  label="Filter Qualifications"
                  value={filterInputQuals}
                  onChange={(e) => handleFilterQuals(e.target.value)}
                  error={
                    formik.touched.filteredQuals &&
                    Boolean(formik.errors.filteredQuals)
                  }
                  helperText={
                    formik.touched.filteredQuals && formik.errors.filteredQuals
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <div className="selected-skills-container">
                  {selectedQuals.length > 0 ? (
                    selectedQuals.map((qual, index) => (
                      <div key={index} className="selected-skill">
                        <span>{qual}</span>
                        <IconButton
                          onClick={() => handleRemoveQual(qual)}
                          aria-label="delete"
                          size="small"
                        >
                          <Close />
                        </IconButton>
                      </div>
                    ))
                  ) : (
                    <div className="error-message">
                      At least one Qual is required
                    </div>
                  )}
                </div>
              </Grid>
              <Grid item xs={12}>
                <div
                  className="skills-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputQuals === "" ? displayedQuals : filteredQuals)
                    .filter((qual) => !selectedQuals.includes(qual))
                    .map((qual, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddQual(qual)}
                      >
                        {qual}
                      </Button>
                    ))}
                </div>
              </Grid>
            <Grid item container justifyContent="center">
              <Button type="submit" variant="contained" color="primary">
                Save
              </Button>
            </Grid>
            </Grid>
        </form>
      </Box>
    </Modal>
  );
}
