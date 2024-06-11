import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { Close } from "@mui/icons-material";
import Grid from "@mui/material/Grid";
import axios from "axios";
import { IconButton, InputAdornment, Slide, TextField } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import { useDispatch, useSelector } from "react-redux";
import { findAllSkills } from "../../../../store/skills/Action";
import { getQualifications } from "../../../../store/qualifications/Action";
import AddIcon from "@mui/icons-material/Add";
import {
  addSkillsQualifications,
  findJobSeekerSkills,
  findJobSeekerSkillsQualifications,
} from "../../../../store/JobSeeker/Action";

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
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::-webkit-scrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::WebkitScrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function AddSkillsModal({
  openAddSkillsModal,
  handleCloseAddSkillsModal,
  id,
}) {
  const [selectedSkills, setSelectedSkills] = React.useState([]);
  var [filteredSkills, setFilteredSkills] = React.useState([]);
  const [filterInputSkills, setFilterInputSkills] = React.useState("");
  const [selectedQualifications, setSelectedQualifications] = React.useState(
    []
  );
  var [filteredQuals, setFilteredQuals] = React.useState([]);
  const [filterInputQuals, setFilterInputQuals] = React.useState("");

  const [isLoading, setIsLoading] = React.useState(true);
  const [skills, setSkills] = React.useState([]);
  const [Qualifications, setQualifications] = React.useState([]);
  const [fetchedSkills, setFetchedSkills] = React.useState([]);
  const [displayedSkills, setDisplayedSkills] = React.useState([]);
  const [fetchedQuals, setFetchedQuals] = React.useState([]);
  const [displayedQuals, setDisplayedQuals] = React.useState([]);
  const auth = useSelector((state) => state.auth);
  const skillsStore = useSelector((state) => state.skills);
  const quals = useSelector((state) => state.quals);
  const jobSeeker = useSelector((state) => state.jobSeeker);
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();
  const dispatch3 = useDispatch();

  React.useEffect(() => {
    if (openAddSkillsModal) {
      dispatch(findAllSkills());
      dispatch2(getQualifications());
    }
  }, [openAddSkillsModal, dispatch, dispatch2]);

  React.useEffect(() => {
    if (openAddSkillsModal) {
      setFetchedSkills(skillsStore.skills);
    }
  }, [openAddSkillsModal, skillsStore]);

  React.useEffect(() => {
    if (openAddSkillsModal) {
      setFetchedQuals(quals.quals);
    }
  }, [openAddSkillsModal, quals]);

  React.useEffect(() => {
    if (openAddSkillsModal) {
      setDisplayedSkills(fetchedSkills.slice(0, 10));
      setDisplayedQuals(fetchedQuals.slice(0, 10));
    }
  }, [openAddSkillsModal, fetchedSkills, fetchedQuals]);

  React.useEffect(() => {
    if (openAddSkillsModal) {
      dispatch3(findJobSeekerSkillsQualifications(auth.user.id));
    }
  }, [openAddSkillsModal, dispatch3, auth.user.id]);

  React.useEffect(() => {
    if (openAddSkillsModal) {
      setSkills(jobSeeker.skills);
      setQualifications(jobSeeker.qualifications);
      setSelectedSkills(skills);
      setSelectedQualifications(Qualifications);
    }
  }, [openAddSkillsModal, jobSeeker, skills, Qualifications]);

  const handleSubmit = async (values) => {
    dispatch3(addSkillsQualifications(values));
    console.log("Sent data  ", values);
  };

  const formik = useFormik({
    initialValues: {
      skills: [],
      qualifications: [],
      userId: auth.user.id,
    },
    // validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  const handleFilterSkills = (input) => {
    const filtered = fetchedSkills.filter(
      (skill) =>
        skill && // Check if skill is not null
        !selectedSkills.includes(input) &&
        !skill.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };  

  const handleFilterQuals = (input) => {
    const filtered = fetchedQuals.filter((qual) =>
      qual.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredQuals(filtered);
    setFilterInputQuals(input);
  };

  const handleAddManualQual = (value) => {
    if (filterInputQuals !== "" && !selectedQualifications.includes(value)) {
      const updatedQuals = [...selectedQualifications, value];
      setSelectedQualifications(updatedQuals);
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
    // const updatedSkillsToShow = skillToRemove+" Delete";
    // console.log("deleted Skill ",updatedSkillsToShow);
    // updatedSkillsToShow.push(updatedSkills);
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
    // const updatedQualificationsToShow = selectedQualifications.filter(
    //   (qualification) => qualification !== qualificationToRemove
    // );
    // updatedQualificationsToShow.push(updatedQualifications);
    setSelectedQualifications(updatedQualifications);
    formik.setFieldValue("qualifications", updatedQualifications);
  };

  const [isHovered, setIsHovered] = React.useState(false);

  return (
    <div>
      <Modal
        open={openAddSkillsModal}
        onClose={handleCloseAddSkillsModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Slide
          direction="left"
          in={openAddSkillsModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 300 }}
          transitionTimingFunction="ease-in-out"
          style={slideStyle}
        >
          <Box sx={style}>
            <div className="modal-content-container flex items-center justify-between mb-4">
              <div className="flex items-center space-x-1 space-y-1 text-gray-500">
                <IconButton
                  onMouseEnter={() => setIsHovered(true)}
                  onMouseLeave={() => setIsHovered(false)}
                  onClick={handleCloseAddSkillsModal}
                >
                  <CloseIcon style={{ color: isHovered ? "red" : "black" }} />
                </IconButton>
                <p className="">Add Remained Skills , Qualificaitons </p>
              </div>
            </div>
            <form onSubmit={formik.handleSubmit}>
              <Grid container direction="column" spacing={2}>
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
                              handleAddManualSkill(filterInputSkills)
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
                    {(filterInputSkills === ""
                      ? displayedSkills
                      : filteredSkills
                    )
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
                      formik.touched.filteredQuals &&
                      formik.errors.filteredQuals
                    }
                    InputProps={{
                      endAdornment: (
                        <InputAdornment position="end">
                          <IconButton
                            onClick={() =>
                              handleAddManualQual(filterInputQuals)
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
                    {(filterInputQuals === "" ? displayedQuals : filteredQuals)
                      .filter((qual) => !selectedQualifications.includes(qual))
                      .map((qual, index) => (
                        <Button
                          key={index}
                          variant="outlined"
                          onClick={() => handleAddQualification(qual)}
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
        </Slide>
      </Modal>
    </div>
  );
}
