import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useDispatch, useSelector } from "react-redux";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import {
  Avatar,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  MenuItem,
  Slide,
  TextField,
  Typography,
  Chip,
} from "@mui/material";
import Menu from "@mui/material/Menu";
import { useNavigate } from "react-router-dom";
import {
  acceptApplication,
  deleteApplication,
  fetchBestPostApplications,
  fetchPostApplications,
  rejectApplication,
} from "../../../../store/Post/Action";
import {
  deleteJobSeekerApplication,
  findJobSeekerApplications,
} from "../../../../store/JobSeeker/Action";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import CheckIcon from "@mui/icons-material/Check";
import ClearIcon from "@mui/icons-material/Clear";
import EditIcon from "@mui/icons-material/Edit";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CancelIcon from "@mui/icons-material/Cancel";

const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none",
  "&::WebkitScrollbar": {
    display: "none",
  },
};

export default function ShowApplicationsModal({
  openShowApplicationsModal,
  handleCloseShowApplicationsModal,
  postId,
}) {
  const [filteredApplicants, setFilteredApplicants] = React.useState([]);
  const [filterInputApplicants, setFilterInputApplicants] = React.useState("");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openMenu = Boolean(anchorEl);
  const [fetchedApplications, setFetchedApplications] = React.useState([]);

  const post = useSelector((state) => state.post);
  const jobSeeker = useSelector((state) => state.jobSeeker);

  const [selectedSearchStatus, setSelectedSearchStatus] = React.useState("");
  const [filteredApplicationsStatus, setFilteredApplicationsStatus] =
    React.useState([]);
  const [acceptStatusState, setAcceptStatusState] = React.useState(true);
  const [rejectStatusState, setRejectStatusState] = React.useState(true);
  const [progressStatusState, setProgressStatusState] = React.useState(true);

  const handleFilterApplications = (input) => {
    const filtered = fetchedApplications.filter((application) => {
      if (auth.user.userType !== "jobSeeker") {
        return application.jobSeekerUserName
          .toLowerCase()
          .includes(input?.toLowerCase());
      } else {
        return (
          application?.companyName
            ?.toLowerCase()
            .includes(input.toLowerCase()) ||
          application?.postTitle?.toLowerCase().includes(input.toLowerCase())
        );
      }
    });
    setFilteredApplicants(filtered);
    setFilterInputApplicants(input);
  };

  const handleEditApplications = (fieldId) => {
    // Handle Edit open the createFieldModal and make it for edit
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  React.useEffect(() => {
    if (openShowApplicationsModal) {
      if (auth.user.userType !== "jobSeeker") {
        dispatch(fetchBestPostApplications(postId));
      } else {
        dispatch(findJobSeekerApplications(auth.user.id));
      }
    }
  }, [openShowApplicationsModal, dispatch, postId, auth.user.id]);

  React.useEffect(() => {
    if (openShowApplicationsModal) {
      if (auth.user.userType !== "jobSeeker") {
        setFetchedApplications(post.applications);
        console.log("Applications From effect : ", post.applications);
      } else {
        setFetchedApplications(jobSeeker.applications);
      }
    }
  }, [post, jobSeeker, openShowApplicationsModal]);

  const handleDeleteApplication = (applicationId) => {
    dispatch(deleteJobSeekerApplication(applicationId));
    handleClose();
  };

  const handleRejectApplication = (applicationId) => {
    dispatch(rejectApplication(applicationId));
    handleClose();
  };

  const handleAcceptApplication = (applicationId) => {
    if (auth.user.userType !== "jobSeeker") {
      dispatch(acceptApplication(applicationId));
      handleClose();
    }
    setFetchedApplications(post.applications);
  };

  const isSkillMatched = (skill, postSkills) => {
    return postSkills.includes(skill);
  };

  const handleSearchApplications = (value) => {
    switch (value) {
      case "Accepted":
        setAcceptStatusState(false);
        setRejectStatusState(true);
        setProgressStatusState(true);
        break;
      case "Rejected":
        setAcceptStatusState(true);
        setRejectStatusState(false);
        setProgressStatusState(true);
        break;
      case "progress":
        setAcceptStatusState(true);
        setRejectStatusState(true);
        setProgressStatusState(false);
        break;
      default:
        setAcceptStatusState(true);
        setRejectStatusState(true);
        setProgressStatusState(true);
        break;
    }

    if (value !== "progress") {
      setSelectedSearchStatus(value);
      const filtered = fetchedApplications.filter((application) => {
        return application?.statuseCode
          ?.toLowerCase()
          .includes(value.toLowerCase());
      });
      setFilteredApplicationsStatus(filtered);
    } else {
      setSelectedSearchStatus("");
      setSelectedSearchStatus(value);
      const filtered = fetchedApplications.filter((application) => {
        return (
          application.statuseCode === "" ||
          application.statuseCode.includes("Matched")
        );
      });
      setFilteredApplicationsStatus(filtered);
    }
  };

  return (
    <div>
      <Modal
        open={openShowApplicationsModal}
        onClose={handleCloseShowApplicationsModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Slide
          direction="left"
          in={openShowApplicationsModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 300 }}
          transitionTimingFunction="ease-in-out"
          style={slideStyle}
        >
          <Box
            sx={{
              position: "absolute",
              top: "0%",
              left: "10%",
              transform: "translate(-50%, -50%)",
              width: "90%",
              bgcolor: "background.paper",
              border: "none",
              boxShadow: 24,
              p: 4,
              outline: "none",
              borderRadius: 4,
              maxHeight: "90%",
              overflowY: "auto",
              scrollbarWidth: "none",
              "&::-webkit-scrollbar": {
                display: "none",
              },
            }}
          >
            <Grid item xs={12}>
              <TextField
                fullWidth
                id="filterApplications"
                name="filterApplications"
                label="Filter Applications"
                value={filterInputApplicants}
                onChange={(e) => handleFilterApplications(e.target.value)}
              />
            </Grid>

            <Grid>
              <div className="flex items-center justify-between mt-3">
                <Button
                  id="basic-button"
                  disabled={!acceptStatusState}
                  onClick={() => handleSearchApplications("Accepted")}
                >
                  Accepted Apps
                </Button>
                <Button
                  id="basic-button"
                  disabled={!progressStatusState}
                  onClick={() => handleSearchApplications("progress")}
                >
                  In progress Apps
                </Button>
                <Button
                  id="basic-button"
                  disabled={!rejectStatusState}
                  onClick={() => handleSearchApplications("Rejected")}
                >
                  Rejected Apps
                </Button>
              </div>
            </Grid>

            <Grid container spacing={2}>
              {(filterInputApplicants === "" && selectedSearchStatus === ""
                ? fetchedApplications
                : filteredApplicants.length > 0
                ? filteredApplicants
                : filteredApplicationsStatus.length > 0
                ? filteredApplicationsStatus
                : []
              ).map((app) => (
                <Grid item xs={12} key={app.id}>
                  <Card>
                    {app.statuseCode === "accepted" && (
                      <Chip
                        label="Accepted"
                        color="success"
                        style={{ position: "absolute", top: 10, right: 10 }}
                      />
                    )}
                    <CardHeader
                      avatar={
                        <Avatar
                          onClick={() =>
                            navigate(`/profile/${app.jobSeekerId}`)
                          }
                          className="cursor-pointer"
                          alt="userName"
                          src={app.jobSeekerPicture}
                        />
                      }
                      action={
                        <div>
                          <Button
                            id="basic-button"
                            aria-controls={openMenu ? "basic-menu" : undefined}
                            aria-haspopup="true"
                            aria-expanded={openMenu ? "true" : undefined}
                            onClick={handleClick}
                          >
                            <MoreHorizIcon />
                          </Button>
                          <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={openMenu}
                            onClose={handleClose}
                            MenuListProps={{
                              "aria-labelledby": "basic-button",
                            }}
                          >
                            {auth.user.userType !== "jobSeeker"
                              ? [
                                  <MenuItem
                                    key="accept"
                                    onClick={() =>
                                      handleAcceptApplication(app.id)
                                    }
                                  >
                                    <CheckIcon style={{ color: "green" }} />{" "}
                                    Accept
                                  </MenuItem>,
                                  <MenuItem
                                    key="reject"
                                    onClick={() =>
                                      handleRejectApplication(app.id)
                                    }
                                  >
                                    <ClearIcon style={{ color: "red" }} />{" "}
                                    Reject
                                  </MenuItem>,
                                ]
                              : [
                                  <MenuItem
                                    key="delete"
                                    onClick={() =>
                                      handleDeleteApplication(app.id)
                                    }
                                  >
                                    <ClearIcon style={{ color: "red" }} />{" "}
                                    Delete
                                  </MenuItem>,
                                  <MenuItem
                                    key="edit"
                                    onClick={() =>
                                      handleEditApplications(app.id)
                                    }
                                  >
                                    <EditIcon style={{ color: "gray" }} /> Edit
                                  </MenuItem>,
                                ]}
                          </Menu>
                        </div>
                      }
                      title={
                        <Typography variant="h6">
                          {app.jobSeekerUserName}{" "}
                          <span className="text-gray-600">
                            @{app.jobSeekerEmail}
                          </span>
                        </Typography>
                      }
                      subheader={
                        <Typography
                          variant="body2"
                          color="textSecondary"
                          display="flex"
                          alignItems="center"
                        >
                          <AccessTimeIcon /> {app.createdDate}
                        </Typography>
                      }
                    />
                    <Divider
                      style={{
                        backgroundColor: "blue",
                        height: 3,
                        marginBottom: 10,
                      }}
                    />
                    <CardContent>
                      {auth.user.userType !== "jobSeeker" ? (
                        <div>
                          {app.statuseCode !== null && (
                            <>
                              {app.statuseCode.includes("Matched") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "green" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : app.statuseCode.includes("Not match") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "red" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : app.statuseCode.includes("Accepted") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "green" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : app.statuseCode.includes("Rejected") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "red" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : null}
                            </>
                          )}
                        </div>
                      ) : (
                        <div>
                          {app.statuseCode !== null && (
                            <>
                              {app.statuseCode.includes("Accepted") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "green" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : app.statuseCode.includes("Rejected") ? (
                                <p
                                  className="font-semibold"
                                  style={{ color: "red" }}
                                >
                                  {app.statuseCode}
                                </p>
                              ) : null}
                            </>
                          )}
                        </div>
                      )}
                      <Grid container spacing={2}>
                        <Grid item xs={12} md={5}>
                          <Typography variant="subtitle1" fontWeight="bold">
                            Application Details
                          </Typography>
                          <Typography variant="body1">
                            <strong>Application ID:</strong> {app.id}
                          </Typography>
                          <Typography variant="body1">
                            <strong>Experience:</strong> {app.experience}
                          </Typography>
                          <Typography variant="body1">
                            <strong>Company Name:</strong> {app.companyName}
                          </Typography>
                          <Typography variant="body1">
                            <strong>Skills:</strong>
                            <ul>
                              {app.skills && app.skills.length > 0 ? (
                                app.skills.map((appSkill, index) => (
                                  <li key={index}>{appSkill}</li>
                                ))
                              ) : (
                                <li>No Skills</li>
                              )}
                            </ul>
                          </Typography>
                          <Typography variant="body1">
                            <strong>Qualifications:</strong>
                            <ul>
                              {app.qualifications &&
                              app.qualifications.length > 0 ? (
                                app.qualifications.map((qual, index) => (
                                  <li key={index}>{qual}</li>
                                ))
                              ) : (
                                <li>No Qualifications</li>
                              )}
                            </ul>
                          </Typography>
                        </Grid>
                        <Divider
                          orientation="vertical"
                          flexItem
                          style={{
                            margin: "0 20px",
                            backgroundColor: "blue",
                            width: 3,
                          }}
                        />
                        <Grid item xs={12} md={5}>
                          <Typography variant="subtitle1" fontWeight="bold">
                            Related Post
                          </Typography>
                          <Typography variant="body1">
                            <strong>Post Title:</strong> {app.postTitle}
                          </Typography>
                          <Typography variant="body1">
                            <strong>Post Experience:</strong>{" "}
                            {app.postExperienc}
                          </Typography>
                          <Typography variant="body1">
                            <strong>Skills:</strong>
                            <ul>
                              {app.postSkills && app.postSkills.length > 0 ? (
                                app.postSkills.map((postSkill, index) => (
                                  <li key={index}>
                                    {isSkillMatched(postSkill, app.skills) ? (
                                      <span style={{ color: "green" }}>
                                        <CheckCircleIcon /> {postSkill}
                                      </span>
                                    ) : (
                                      <span style={{ color: "red" }}>
                                        <CancelIcon /> {postSkill}
                                      </span>
                                    )}
                                  </li>
                                ))
                              ) : (
                                <li>No Skills</li>
                              )}
                            </ul>
                          </Typography>
                          <Typography variant="body1">
                            <strong>Qualifications:</strong>
                            <ul>
                              {app.postQualifications &&
                              app.postQualifications.length > 0 ? (
                                app.postQualifications.map(
                                  (postQual, index) => (
                                    <li key={index}>
                                      {isSkillMatched(
                                        postQual,
                                        app.qualifications
                                      ) ? (
                                        <span style={{ color: "green" }}>
                                          <CheckCircleIcon /> {postQual}
                                        </span>
                                      ) : (
                                        <span style={{ color: "red" }}>
                                          <CancelIcon /> {postQual}
                                        </span>
                                      )}
                                    </li>
                                  )
                                )
                              ) : (
                                <li>No Qualifications</li>
                              )}
                            </ul>
                          </Typography>
                        </Grid>
                      </Grid>
                    </CardContent>
                  </Card>
                </Grid>
              ))}
            </Grid>
          </Box>
        </Slide>
      </Modal>
    </div>
  );
}
