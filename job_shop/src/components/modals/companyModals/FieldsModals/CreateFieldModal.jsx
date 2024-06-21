import React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import {
  ClickAwayListener,
  IconButton,
  InputAdornment,
  MenuItem,
  Slide,
} from "@mui/material";
import { Close } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import { useEffect } from "react";
import {
  createField,
  createFieldWithJobs,
  getAllFields,
} from "../../../../store/company/Action";
import { findAllSkills } from "../../../../store/skills/Action";
import { getQualifications } from "../../../../store/qualifications/Action";
import AddIcon from "@mui/icons-material/Add";
import ShowFieldsModal from "./ShowFieldsModal";
import * as Yup from "yup";
import SearchableDropdown from "./SearchableDropDown";
import {
  fetchAllFields,
  fetchAllFieldsForCreateFields,
} from "../../../../store/fields/Action";
import { findAllJobs } from "../../../../store/Jobs/Action";
const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::WebkitScrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function CreateFieldModal({
  openCreateFieldModal,
  handleCloseCreateFieldModal,
}) {
  // const jwt=localStorage.getItem("jwt");
  const [openShowFieldsModal, setOpenShowFieldsModal] = React.useState(false);
  const handleOpenShowFieldsModal = () => setOpenShowFieldsModal(true);
  const handleCloseShowFielsModal = () => setOpenShowFieldsModal(false);

  var [selectedSkills, setSelectedSkills] = React.useState([]);
  var [selectedJobs, setSelectedJobs] = React.useState([]);
  var [filteredSkills, setFilteredSkills] = React.useState([]);
  var [filteredJobs, setFilteredJobs] = React.useState([]);
  var [filteredFields, setFilteredFields] = React.useState([]);
  const [filterInputSkills, setFilterInputSkills] = React.useState("");
  const [filterInputJobs, setFilterInputJobs] = React.useState("");
  const [filterInputFields, setFilterInputFields] = React.useState("");
  // const [anchorEl, setAnchorEl] = useState(null);
  const comp = useSelector((state) => state.comp);
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();
  const dispatch3 = useDispatch();
  const skills = useSelector((state) => state.skills);
  const jobRed = useSelector((state) => state.jobRed);
  const quals = useSelector((state) => state.quals);
  const [fields, setFields] = useState([]);
  const [fetchedSkills, setFetchedSkills] = useState([]);
  const [fetchedJobs, setFetchedJobs] = useState([]);
  const [displayedSkills, setDisplayedSkills] = useState([]);
  const [displayedFields, setDisplayedFields] = useState([]);
  const [fetchedQuals, setFetchedQuals] = useState([]);
  const [displayedQuals, setDisplayedQuals] = useState([]);
  const [displayedJobs, setDisplayedJobs] = useState([]);
  var [selectedQuals, setSelectedQuals] = React.useState([]);
  var [filteredQuals, setFilteredQuals] = React.useState([]);
  const [filterInputQuals, setFilterInputQuals] = React.useState("");

  const [fetchedFields, setFetchedFields] = React.useState([]);
  const [selectedField, setSelectedField] = React.useState("");
  const dispatch4 = useDispatch();
  const fieldReducer = useSelector((state) => state.fieldReducer);
  const dispatch5 = useDispatch();
  const handleAddManualField = (value) => {
    if (filterInputFields !== "" && !selectedField.includes(value)) {
      setSelectedField(value);
      formik.setFieldValue("fieldName", value);
      setFilterInputFields("");
    }
  };

  useEffect(() => {
    if (openCreateFieldModal) {
      dispatch5(fetchAllFieldsForCreateFields());
    }
  }, [dispatch5, openCreateFieldModal]);

  useEffect(() => {
    if (openCreateFieldModal) {
      dispatch4(findAllJobs());
    }
  }, [dispatch4, openCreateFieldModal]);

  useEffect(() => {
    if (openCreateFieldModal) {
      setFetchedFields(fieldReducer.fieldsForCreate);
      console.log("Fetched Fields Front : ", fieldReducer.fieldsForCreate);
    }
  }, [openCreateFieldModal, fieldReducer.fieldsForCreate]);

  useEffect(() => {
    if (openCreateFieldModal) {
      dispatch2(findAllSkills());
      dispatch3(getQualifications());
    }
  }, [openCreateFieldModal, dispatch2, dispatch3]);

  useEffect(() => {
    if (openCreateFieldModal) {
      setFetchedJobs(jobRed.jobs);
    }
  }, [openCreateFieldModal, jobRed]);

  useEffect(() => {
    if (openCreateFieldModal) {
      setFetchedSkills(skills.skills);
    }
  }, [openCreateFieldModal, skills]);

  useEffect(() => {
    if (openCreateFieldModal) {
      setFetchedQuals(quals.quals);
    }
  }, [openCreateFieldModal, quals]);

  useEffect(() => {
    setDisplayedSkills(fetchedSkills.slice(0, 10));
    setDisplayedQuals(fetchedQuals.slice(0, 10));
    setDisplayedJobs(fetchedJobs.slice(0, 10));
  }, [fetchedSkills, fetchedQuals, fetchedJobs]);

  useEffect(() => {
    setDisplayedFields(fetchedFields.slice(0, 10));
  }, [fetchedFields]);

  const handleAddField = (value) => {
    if (!selectedField.includes(value)) {
      setSelectedField(value);
      formik.setFieldValue("fieldName", value);
    }
  };
  const handleAddSkill = (skill) => {
    if (!selectedSkills.includes(skill)) {
      const updatedSkills = [...selectedSkills, skill];
      setSelectedSkills(updatedSkills);
      if (updatedSkills) {
        formik.setFieldValue("skills", updatedSkills);
      }
    }
  };

  const handleAddJob = (job) => {
    if (!selectedJobs.includes(job)) {
      const updatedJobs = [...selectedJobs, job];
      setSelectedJobs(updatedJobs);
      if (updatedJobs) {
        formik.setFieldValue("jobs", updatedJobs);
      }
    }
  };

  const handleRemoveField = () => {
    setSelectedField("");
    formik.setFieldValue("fieldName", "");
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

  const handleRemoveJob = (jobToRemove) => {
    const updatedJobs = selectedJobs.filter((job) => job !== jobToRemove);
    setSelectedJobs(updatedJobs);
    if (updatedJobs) {
      formik.setFieldValue("jobs", updatedJobs);
    }
  };

  const handleFilterFields = (input) => {
    const normalizedInput = input.toLowerCase();

    const filtered = fetchedFields.filter((field) => {
      if (
        field.toLowerCase().includes(normalizedInput) &&
        !selectedField.includes(normalizedInput)
      ) {
        return field;
      }
    });
    setFilteredFields(filtered);
    setFilterInputFields(input);
  };

  const handleFilterSkills = (input) => {
    const normalizedInput = input.toLowerCase();

    const filtered = fetchedSkills.filter((skill) => {
      if (
        skill.toLowerCase().includes(normalizedInput) &&
        !selectedSkills.includes(normalizedInput)
      ) {
        return skill;
      }
    });

    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };

  const handleFilterJobs = (input) => {
    const normalizedInput = input.toLowerCase();

    if (fetchedJobs) {
      const filtered = fetchedJobs.filter((job) => {
        if (
          job.toLowerCase().includes(normalizedInput) &&
          !selectedSkills.includes(normalizedInput)
        ) {
          return job;
        }
      });
      setFilteredJobs(filtered);
      setFilterInputJobs(input);
    }
  };

  // const handleClickAway=()=>
  //   {
  //     setAnchorEl(null);
  //   };
  const handleAddQual = (qual) => {
    if (!selectedQuals.includes(qual)) {
      const updatedQuals = [...selectedQuals, qual];
      setSelectedQuals(updatedQuals);
      if (updatedQuals) {
        formik.setFieldValue("qualifications", updatedQuals);
      }
    }
  };

  const handleRemoveQual = (qualToRemove) => {
    const updatedQuals = selectedQuals.filter((qual) => qual !== qualToRemove);
    setSelectedQuals(updatedQuals);
    if (updatedQuals) {
      formik.setFieldValue("qualifications", updatedQuals);
    }
  };

  const handleAddManualQual = (value) => {
    if (filterInputQuals !== "" && !selectedQuals.includes(value)) {
      const updatedQuals = [...selectedQuals, value];
      setSelectedQuals(updatedQuals);
      formik.setFieldValue("qualifications", updatedQuals);
      setFilterInputQuals("");
    }
  };

  const handleAddManualSkill = (value) => {
    if (filterInputSkills !== "" && !selectedSkills.includes(value)) {
      const updatedSkills = [...selectedSkills, value];
      setSelectedSkills(updatedSkills);
      formik.setFieldValue("skills", updatedSkills);
      setFilterInputSkills("");
    }
  };

  const handleAddManualJob = (value) => {
    if (filterInputJobs !== "" && !selectedJobs.includes(value)) {
      const updatedJobs = [...selectedJobs, value];
      setSelectedJobs(updatedJobs);
      formik.setFieldValue("jobs", updatedJobs);
      setFilterInputJobs("");
    }
  };

  // const handleAddManualField=(value)=>
  //   {
  //     if(filterInputFields !== "" && !selectedField.includes(value))
  //     {
  //         setSelectedField(value);
  //         formik.setFieldValue("fieldName",value);
  //         setFilterInputFields("")
  //     }
  //   }

  const handleFilterQuals = (input) => {
    const filtered = fetchedQuals.filter((qual) =>
      qual.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredQuals(filtered);
    setFilterInputQuals(input);
  };

  const formik = useFormik({
    initialValues: {
      fieldName: "",
      companyAdministratorId: auth.user.id,
      qualifications: [],
      skills: [],
      jobs: [],
    }, // Initialize field with an empty string or appropriate initial value    },
    onSubmit: async (values) => {
      if (values.fieldName !== "" && values.jobs.length > 0) {
        console.log("VALUES : ", values);
        await dispatch(createFieldWithJobs(values));
        formik.resetForm();
        setSelectedQuals([]);
        setSelectedSkills([]);
        setSelectedJobs([]);
        setSelectedField([]);
        handleOpenShowFieldsModal();
      }
    },
  });

  return (
    <Modal
      open={openCreateFieldModal}
      onClose={handleCloseCreateFieldModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Slide
        direction="down"
        in={openCreateFieldModal}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 800, exit: 800 }}
        transitiontimingfunction="ease-in-out"
        style={slideStyle}
      >
        <Box
          sx={{
            position: "absolute",
            top: "10%",
            left: "20%",
            transform: "translate(-50%, -50%)",
            width: 900,
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
          <div className="modal-content-container flex items-center mb-4">
            <IconButton
              onClick={handleCloseCreateFieldModal}
              aria-label="delete"
            >
              <Close />
            </IconButton>
            <p className="mt-3 font-semibold text-lg text-gray-500">
              Create Field
            </p>
          </div>
          <form onSubmit={formik.handleSubmit}>
            <Grid container spacing={2}>
              {/* <Grid item xs={12}>
              <TextField
                fullWidth
                id="filterFields"
                name="filterFields"
                label="Filter Fields"
                onChange={formik.handleChange}
                variant="outlined"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={()=>handleAddManualSkill(filterInputSkills)}>
                        <AddIcon />
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              >
              </TextField>
        </Grid>  */}

              {/* <SearchableDropdown options={fetchedFields}/> */}
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="filterFields"
                  name="filterFields"
                  label="Filter Fields"
                  value={filterInputFields}
                  onChange={(e) => handleFilterFields(e.target.value)}
                  error={
                    formik.touched.filterFields &&
                    Boolean(formik.errors.filterFields)
                  }
                  helperText={
                    formik.touched.filterFields && formik.errors.filterFields
                  }
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() =>
                            handleAddManualField(filterInputFields.trim())
                          }
                        >
                          <AddIcon />
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={12}>
                <div className="selected-skills-container">
                  {selectedField !== "" ? (
                    <div className="selected-skill">
                      <span>{selectedField}</span>
                      <IconButton
                        onClick={() => handleRemoveField()}
                        aria-label="delete"
                        size="small"
                      >
                        <Close />
                      </IconButton>
                    </div>
                  ) : (
                    <div className="error-message">Field Required</div>
                  )}
                </div>
              </Grid>

              <Grid item xs={12}>
                <div
                  className="skills-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputFields === "" ? displayedFields : filteredFields)
                    .filter((field) => !selectedField.includes(field))
                    .map((field, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddField(field)}
                      >
                        {field}
                      </Button>
                    ))}
                </div>
              </Grid>

              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="filterJobs"
                  name="filterJobs"
                  label="Filter Jobs"
                  value={filterInputJobs}
                  onChange={(e) => handleFilterJobs(e.target.value)}
                  error={
                    formik.touched.filterJobs &&
                    Boolean(formik.errors.filterJobs)
                  }
                  helperText={
                    formik.touched.filterJobs && formik.errors.filterJobs
                  }
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() =>
                            handleAddManualJob(filterInputJobs.trim())
                          }
                        >
                          <AddIcon />
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />
              </Grid>

              <Grid item xs={12}>
                <div className="selected-skills-container">
                  {selectedJobs.length > 0 ? (
                    selectedJobs.map((job, index) => (
                      <div key={index} className="selected-skill">
                        <span>{job}</span>
                        <IconButton
                          onClick={() => handleRemoveJob(job)}
                          aria-label="delete"
                          size="small"
                        >
                          <Close />
                        </IconButton>
                      </div>
                    ))
                  ) : (
                    <div className="error-message">
                      At least one job is required
                    </div>
                  )}
                </div>
              </Grid>

              <Grid item xs={12}>
                <div
                  className="skills-scroll-container"
                  style={{ maxHeight: "200px", overflowY: "auto" }}
                >
                  {(filterInputJobs === "" ? displayedJobs : filteredJobs)
                    .filter((job) => !selectedJobs.includes(job))
                    .map((job, index) => (
                      <Button
                        key={index}
                        variant="outlined"
                        onClick={() => handleAddJob(job)}
                      >
                        {job}
                      </Button>
                    ))}
                </div>
              </Grid>
              {/* 
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
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() =>
                            handleAddManualSkill(filterInputSkills.trim())
                          }
                        >
                          <AddIcon />
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
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
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() =>
                            handleAddManualQual(filterInputQuals.trim())
                          }
                        >
                          <AddIcon />
                        </IconButton>
                      </InputAdornment>
                    ),
                  }}
                />
              </Grid>
              <Grid item xs={12}>
                <div className="selected-qualifications-container">
                  {selectedQuals.length > 0 ? (
                    selectedQuals.map((qual, index) => (
                      <div key={index} className="selected-qualification">
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
              */}
              <Grid item container justifyContent="center">
                <Button type="submit" variant="contained" color="primary">
                  Save
                </Button>
              </Grid>
              <Grid item container justifyContent="center">
                <Button
                  onClick={() => handleOpenShowFieldsModal()}
                  variant="outlined"
                  color="primary"
                >
                  Show fields
                </Button>
              </Grid>
            </Grid>
          </form>
          <section>
            <ShowFieldsModal
              openShowFieldsModal={openShowFieldsModal}
              handleCloseShowFieldsModal={handleCloseShowFielsModal}
              isRequestUser={true}
            />
          </section>
        </Box>
      </Slide>
    </Modal>
  );
}
