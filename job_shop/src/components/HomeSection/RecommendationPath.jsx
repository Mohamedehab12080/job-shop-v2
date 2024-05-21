import React, { useState, useEffect } from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import { recommendedPosts } from "../../store/Post/recommededPost/Action";
import SearchIcon from '@mui/icons-material/Search';
import { Grid, TextField, Button, Typography, Box, IconButton, InputAdornment, Tab } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import AddIcon from '@mui/icons-material/Add';
import Close from '@mui/icons-material/Close';
import { findAllSkills } from "../../store/skills/Action";
import PostCardJobSeeker from "./posts/PostCardJobSeeker";

const validationSchema = Yup.object().shape({
  skills: Yup.array().min(1, "At least one skill is required").required("Skills are required"),
});

const RecommendationPath = () => {
  const [tabValue, setTabValue] = useState("1");
  const auth = useSelector(state => state.auth);  
  const recommedRed = useSelector(state => state.recommedRed);
  const skills = useSelector(state => state.skills);
  const dispatch = useDispatch();

  const [modelResponse, setModelResponse] = useState([]);
  const [posts, setPosts] = useState([]);
  const [filterInputSkills, setFilterInputSkills] = useState("");
  const [fetchedSkills, setFetchedSkills] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [filteredSkills, setFilteredSkills] = useState([]);
  const [displayedSkills, setDisplayedSkills] = useState([]);
  const [statusOfPosts, setStatusOfPosts] = useState(false);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [filterInputPost, setFilterInputPost] = useState("");

  useEffect(() => {
    
      dispatch(findAllSkills());
    
  }, [ dispatch]);

  useEffect(() => {
  
      setFetchedSkills(skills.skills);
    
  }, [skills.skills]);

  const handleFilterSkills = (input) => {
    const normalizedInput = input.toLowerCase();
    const filtered = fetchedSkills.filter((skill) =>
      skill.toLowerCase().includes(normalizedInput) && !selectedSkills.includes(skill)
    );
    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };

  const handleSubmit = (values) => {
    dispatch(recommendedPosts(values.skills));
  };

  useEffect(() => {
      setModelResponse(recommedRed.response);
      setPosts(recommedRed.posts);
  }, [ recommedRed.response, recommedRed.posts]);

  useEffect(() => {
    setDisplayedSkills(fetchedSkills.slice(0, 10));
  }, [fetchedSkills]);

  const formik = useFormik({
    initialValues: {
      skills: []
    },
    validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = selectedSkills.filter((skill) => skill !== skillToRemove);
    setSelectedSkills(updatedSkills);
    formik.setFieldValue("skills", updatedSkills);
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

  const handleFetchPosts = () => {
    setStatusOfPosts(true); // Set statusOfPosts to true on button click
  };

  const handleChange = (event, newValue) => {
    setTabValue(newValue);
    if (newValue === '2') {
      handleFetchPosts();
    }
  };

  const handleFilterPosts = (input) => {
    const filtered = auth.user.userType === "jobSeeker" ? posts : posts; // Adjust this according to your state
    const filteredResult = filtered.filter((post) => post.title.toLowerCase().includes(input.toLowerCase()));
    setFilteredPosts(filteredResult);
    setFilterInputPost(input);
  };

  return (
    <div className="space-y-5">
      <section>
        <h1 className="py-7 text-xl font-bold opacity-90">Home</h1>
      </section>
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
                error={formik.touched.skills && Boolean(formik.errors.skills)}
                helperText={formik.touched.skills && formik.errors.skills}
                InputProps={{
                  style: {
                    color: '#000', // Text color
                    backgroundColor: '#fff', // Background color
                    borderRadius: '5px', // Border radius
                },
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
              <div className="skills-scroll-container" style={{ maxHeight: "200px", overflowY: "auto" }}>
                {(filterInputSkills === "" ? displayedSkills : filteredSkills)
                  .filter((skill) => !selectedSkills.includes(skill))
                  .map((skill, index) => (
                    <Button key={index} variant="outlined" onClick={() => handleAddSkill(skill)}>
                      {skill}
                    </Button>
                  ))}
              </div>
            </Grid>
            <Grid item xs={12}>
              <Button type="submit" variant="contained" color="primary" fullWidth>
                Submit
              </Button>
            </Grid>
          </Grid>
        </div>
      </form>
      <div className='relative flex items-center'>
        <Grid item xs={12}>
          <TextField
            fullWidth
            id="filterInputPost"
            name="filterInputPost"
            label="Filter Posts"
            value={filterInputPost}
            onChange={(e) => handleFilterPosts(e.target.value)}
            InputProps={{
              style: {
                color: '#000', // Text color
                backgroundColor: '#fff', // Background color
                borderRadius: '5px', // Border radius
            }}}
          />
        </Grid>
      </div>
      <section className='py-5'>
        <Box sx={{ width: '100%', typography: 'body1' }}>
          <TabContext value={tabValue}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
              <TabList onChange={handleChange} aria-label="lab API tabs example">
                <Tab label="Recommended posts" value="1" />
                <Tab label="Recommended jobs" value="2" />
              </TabList>
            </Box>
            <TabPanel key="1" value='1'>
              <section>
                {(filterInputPost === "" ? posts : filteredPosts).map((p, index) => (
                  <PostCardJobSeeker  
                    key={index}
                    id={p.id}
                    employerId={p.employerId}
                    employerUserName={p.employerUserName}
                    Title={p.title}
                    description={p.description}
                    jobRequirements={p.jobRequirements}
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
                    matchedQualifications={p.matchedQualifications}
                    matchedSkills={p.matchedSkills}
                    applicationCount={p.applicationCount}
                    jobSeekerId={auth.user.id}
                    experience={p.experience}
                    postImage={p.postImage}
                  />
                ))}
              </section>
            </TabPanel>
            <TabPanel key="2" value='2'>
              {statusOfPosts && (
                <section key={2}>
                  <div style={{ marginTop: 20 }}>
                    <Typography variant="h6">Response</Typography>
                    <TextField
                      fullWidth
                      multiline
                      rows={10}
                      value={modelResponse ? JSON.stringify(modelResponse, null, 2) : ""}
                      variant="outlined"
                      InputProps={{
                        style: {
                          color: '#000', // Text color
                          backgroundColor: '#fff', // Background color
                          borderRadius: '5px', // Border radius
                      },
                        readOnly: true }}
                      
                    />
                  </div>
                </section>
              )}
            </TabPanel>
          </TabContext>
        </Box>
      </section>
    </div>
  );
};

export default RecommendationPath;
