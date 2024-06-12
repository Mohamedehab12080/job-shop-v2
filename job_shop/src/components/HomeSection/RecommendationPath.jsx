import React, { useState, useEffect } from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import { recommendedPosts } from "../../store/Post/recommededPost/Action";
import { findAllSkills } from "../../store/skills/Action";
import {
  Grid,
  TextField,
  Button,
  Typography,
  Box,
  IconButton,
  InputAdornment,
  Tab,
  CircularProgress,
  Card,
  CardContent,
  CardActions,
} from "@mui/material";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import AddIcon from "@mui/icons-material/Add";
import Close from "@mui/icons-material/Close";
import PostCardJobSeeker from "./posts/PostCardJobSeeker";

const validationSchema = Yup.object().shape({
  skills: Yup.array()
    .min(1, "At least one skill is required")
    .required("Skills are required"),
});

const RecommendationPath = () => {
  const [tabValue, setTabValue] = useState("1");
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
  const dispatch = useDispatch();

  const auth = useSelector((state) => state.auth);
  const recommedRed = useSelector((state) => state.recommedRed);
  const skills = useSelector((state) => state.skills);

  useEffect(() => {
    dispatch(findAllSkills());
  }, [dispatch]);

  useEffect(() => {
    setFetchedSkills(skills.skills || []);
  }, [skills.skills]);

  useEffect(() => {
    setModelResponse(recommedRed.response || []);
    console.log("REomm : ", recommedRed.response);
    setPosts(recommedRed.posts || []);
  }, [recommedRed.response, recommedRed.posts]);

  useEffect(() => {
    setDisplayedSkills(fetchedSkills.slice(0, 10));
  }, [fetchedSkills]);

  const handleFilterSkills = (input) => {
    const normalizedInput = input.toLowerCase();
    const filtered = fetchedSkills.filter(
      (skill) =>
        skill.toLowerCase().includes(normalizedInput) &&
        !selectedSkills.includes(skill)
    );
    setFilteredSkills(filtered);
    setFilterInputSkills(input);
  };

  const handleSubmit = (values) => {
    dispatch(recommendedPosts(values.skills));
  };

  const formik = useFormik({
    initialValues: {
      skills: [],
    },
    validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = selectedSkills.filter(
      (skill) => skill !== skillToRemove
    );
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
    setStatusOfPosts(true);
  };

  const handleChange = (event, newValue) => {
    setTabValue(newValue);
    if (newValue === "2") {
      handleFetchPosts();
    }
  };

  const handleFilterPosts = (input) => {
    const filteredResult = posts.filter((post) =>
      post.title.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredPosts(filteredResult);
    setFilterInputPost(input);
  };

  return (
    <div className="recommendation-path-container space-y-5">
      <style>{`
        .recommendation-path-container {
          padding: 20px;
          background-color: #f9f9f9;
        }
        .skills-form-container {
          display: flex;
          align-items: center;
        }
        .selected-skills-container {
          display: flex;
          flex-wrap: wrap;
          gap: 10px;
        }
        .selected-skill {
          display: flex;
          align-items: center;
          background-color: #e0e0e0;
          border-radius: 5px;
          padding: 5px 10px;
        }
        .skills-scroll-container {
          max-height: 200px;
          overflow-y: auto;
          display: flex;
          flex-wrap: wrap;
          gap: 10px;
        }
        .skill-button {
          margin: 5px;
        }
        .error-message {
          color: red;
        }
        .py-7 {
          padding-top: 7px;
          padding-bottom: 7px;
        }
        .font-bold {
          font-weight: bold;
        }
        .opacity-90 {
          opacity: 0.9;
        }
        .model-response-card {
          margin-bottom: 20px;
        }
      `}</style>
      <section>
        <Typography variant="h4" className="py-7 font-bold opacity-90">
          Home
        </Typography>
      </section>
      <form onSubmit={formik.handleSubmit}>
        <div className="skills-form-container" style={{ marginBottom: 16 }}>
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
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        onClick={() => handleAddManualSkill(filterInputSkills)}
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
              <div className="skills-scroll-container">
                {(filterInputSkills === "" ? displayedSkills : filteredSkills)
                  .filter((skill) => !selectedSkills.includes(skill))
                  .map((skill, index) => (
                    <Button
                      key={index}
                      variant="outlined"
                      onClick={() => handleAddSkill(skill)}
                      className="skill-button"
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
      <div className="relative flex items-center">
        <Grid item xs={12}>
          <TextField
            fullWidth
            id="filterInputPost"
            name="filterInputPost"
            label="Filter Posts"
            value={filterInputPost}
            onChange={(e) => handleFilterPosts(e.target.value)}
          />
        </Grid>
      </div>
      <section className="py-5">
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={tabValue}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
                <Tab label="Recommended posts" value="1" />
                <Tab label="Recommended jobs" value="2" />
              </TabList>
            </Box>
            <TabPanel key="1" value="1">
              <section>
                {(filterInputPost === "" ? posts : filteredPosts).map(
                  (p, index) => (
                    <PostCardJobSeeker
                      key={p.id}
                      id={p.id}
                      employerId={p.employerId}
                      employerUserName={p.employerUserName}
                      Title={p.title}
                      description={p.description}
                      jobRequirements={p.jobRequirments}
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
                      matchedQulifications={p.matchedQulifications}
                      matchedSkills={p.matchedSkills}
                      applicationCount={p.applicationCount}
                      jobSeekerId={auth.user.id}
                      Experience={p.experience}
                      postImage={p.postImage}
                      statusCode={p.statuseCode}
                    />
                  )
                )}
              </section>
            </TabPanel>
            <TabPanel key="2" value="2">
              {statusOfPosts ? (
                <section key={2}>
                  <div style={{ marginTop: 20 }}>
                    <Typography variant="h6">You can work</Typography>
                    {modelResponse.length > 0 ? (
                      modelResponse.map((response, index) => (
                        <Card key={index} className="model-response-card">
                          <CardContent>
                            <Typography variant="body1">
                              <b>Job Title:</b> {response["Job Title"]}
                            </Typography>
                            <Typography variant="body1">
                              <b>Skills:</b>
                            </Typography>
                            <Typography variant="body2">
                              {response.skills}
                            </Typography>
                          </CardContent>
                        </Card>
                      ))
                    ) : (
                      <Typography variant="body2">
                        No recommendations available
                      </Typography>
                    )}
                  </div>
                </section>
              ) : (
                <CircularProgress />
              )}
            </TabPanel>
          </TabContext>
        </Box>
      </section>
    </div>
  );
};

export default RecommendationPath;
