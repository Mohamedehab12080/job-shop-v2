import React, { useEffect, useState } from 'react';
import { Box, Modal, IconButton, Typography, Grid, Button, TextField, InputAdornment, Slide } from '@mui/material';
import { Close, Add as AddIcon } from '@mui/icons-material';
import { useDispatch, useSelector } from 'react-redux';
import { useFormik } from 'formik';
import { recommendedPosts } from '../../../../store/Post/recommededPost/Action';
import { findAllSkills } from '../../../../store/skills/Action';

const slideStyle = {
  height: '100%',
  overflowY: 'auto',
  scrollbarWidth: 'none', // Hide scrollbar for Firefox
  '&::WebkitScrollbar': {
    display: 'none', // Hide scrollbar for Chrome, Safari, Edge
  },
};

export default function RecommendationModal({
  openRecommendationModal,
  handleCloseRecommendationModal
}) {
  const post = useSelector(state => state.post);
  const recommedRed = useSelector(state => state.recommedRed);
  const dispatch = useDispatch();
  const [modelResponse, setModelResponse] = useState([]);
  const [posts, setPosts] = useState([]);
  const [filterInputSkills, setFilterInputSkills] = useState("");
  const [fetchedSkills, setFetchedSkills] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [filteredSkills, setFilteredSkills] = useState([]);
  const [displayedSkills, setDisplayedSkills] = useState([]);
  const skills = useSelector(state => state.skills);
  const dispatch2 = useDispatch();

  useEffect(() => {
    if (openRecommendationModal) {
      dispatch2(findAllSkills());
    }
  }, [openRecommendationModal, dispatch2]);

  useEffect(() => {
    if (openRecommendationModal) {
      setFetchedSkills(skills.skills);
    }
  }, [openRecommendationModal, skills]);

  const handleFilterSkills = (input) => {
    const normalizedInput = input.toLowerCase();
    const filtered = fetchedSkills.filter((skill) => {
      if (skill.toLowerCase().includes(normalizedInput) && !selectedSkills.includes(normalizedInput)) {
        return skill;
      }
      return false;
    });
    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };

  const handleSubmit = (values) => {
    dispatch(recommendedPosts(values.skills));
  };

  useEffect(() => {
    if (openRecommendationModal) {
      setModelResponse(recommedRed.response);
    }
  }, [openRecommendationModal, recommedRed.response]);

  useEffect(() => {
    if (openRecommendationModal) {
      setPosts(recommedRed.posts);
    }
  }, [openRecommendationModal, recommedRed.posts]);

  useEffect(() => {
    setDisplayedSkills(fetchedSkills.slice(0, 10));
  }, [fetchedSkills]);

  const formik = useFormik({
    initialValues: {
      skills: []
    },
    onSubmit: handleSubmit
  });

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = selectedSkills.filter((skill) => skill !== skillToRemove);
    setSelectedSkills(updatedSkills);
    if (updatedSkills) {
      formik.setFieldValue("skills", updatedSkills);
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
      if (updatedSkills) {
        formik.setFieldValue("skills", updatedSkills);
      }
    }
  };

  return (
    <Modal
      open={openRecommendationModal}
      onClose={handleCloseRecommendationModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Slide
        direction="left"
        in={openRecommendationModal}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 500, exit: 500 }}
        transitionTimingFunction="ease-in-out"
        style={slideStyle}
      >
        <Box
          sx={{
            position: 'absolute',
            top: '10%',
            left: '20%',
            transform: 'translate(-50%, -50%)',
            width: 800,
            bgcolor: 'background.paper',
            boxShadow: 24,
            p: 4,
            borderRadius: 8,
            maxHeight: '80vh',
            overflowY: 'auto',
          }}
        >
          <form onSubmit={formik.handleSubmit}>
            <div style={{ display: 'flex', alignItems: 'center', marginBottom: 16 }}>
              <Grid container spacing={2}>
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
                          <IconButton onClick={() => handleAddManualSkill(filterInputSkills)}>
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
                  <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                  >
                    Submit
                  </Button>
                </Grid>
              </Grid>
            </div>
          </form>
          <div style={{ marginTop: 20 }}>
            <Typography variant="h6">Response</Typography>
            <TextField
              fullWidth
              multiline
              rows={10}
              value={modelResponse ? JSON.stringify(modelResponse, null, 2) : ""}
              variant="outlined"
              InputProps={{
                readOnly: true,
              }}
            />
          </div>
        </Box>
      </Slide>
    </Modal>
  );
}
