import React, { useState } from "react";
import {
  Avatar,
  Box,
  Card,
  CardContent,
  CardHeader,
  Grid,
  IconButton,
  Menu,
  MenuItem,
  Typography,
  Divider,
  Button,
} from "@mui/material";
import { Tooltip } from "@mui/material";
import {
  MoreHoriz as MoreHorizIcon,
  Group as GroupIcon,
  FmdGood as FmdGoodIcon,
} from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import ConfirmMessage from "../../../responses/ConfirmMessage";
import ShowApplicationsModal from "../../modals/companyModals/postModals/ShowApplicationsModal";
import ShowPostImageModal from "./ShowPostImageModal";
import PostModal from "../../modals/companyModals/postModals/PostModal";
import ShowSkillsModal from "../../Profile/ShowSkillsModal";

const PostCardCompany = ({
  id,
  employerId,
  employerUserName,
  Title,
  description,
  Experience,
  jobRequirements,
  location,
  employmentType,
  companyName,
  skills,
  qualifications,
  field,
  employerpicture,
  fieldName,
  applicationCount,
  postImage,
  statusCode,
  jobName,
}) => {
  const [anchorEl, setAnchorEl] = useState(null);
  const openMenu = Boolean(anchorEl);
  const [openShowApplicationsModal, setOpenShowApplicationsModal] =
    useState(false);
  const [openShowPostImageModal, setOpenShowPostImageModal] = useState(false);
  const [openConfirmMessage, setOpenConfirmMessage] = useState(false);
  const [openPostModal, setOpenPostModal] = useState(false);
  const [hidden, setHidden] = useState(true);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const auth = useSelector((state) => state.auth);

  const [openShowSkillsModal, setOpenShowSkillsModal] = useState(false);
  const handleOpenShowSkillsModal = () => setOpenShowSkillsModal(true);
  const handleCloseShowSkillsModal = () => setOpenShowSkillsModal(false);

  const MAX_DISPLAY_LENGTH = 70; // Example maximum length before tooltip appears

  // Function to truncate text if it exceeds the maximum length
  const truncateText = (text, maxLength) => {
    if (text.length > maxLength) {
      return text.substring(0, maxLength) + "..."; // Adjust ellipsis as needed
    }
    return text;
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleToggleHidden = () => {
    setHidden(!hidden);
  };
  const handleEditPost = () => {
    setOpenPostModal(true);
  };

  const truncatedJobRequirements =
    jobRequirements && jobRequirements.length > 300
      ? `${jobRequirements.substring(0, 300)}...`
      : jobRequirements;
  const truncatedDescription =
    description && description.length > 300
      ? `${description.substring(0, 300)}...`
      : description;

  return (
    <Card sx={{ mb: 4, border: "1px solid #e0e0e0", borderRadius: 8 }}>
      <CardHeader
        avatar={
          <Avatar
            onClick={() => navigate(`/employerProfile/${employerId}`)}
            alt={employerUserName}
            src={employerpicture}
            sx={{ cursor: "pointer" }}
          />
        }
        action={
          (auth.user.userType === "Admin" || auth.user.id === employerId) && (
            <>
              <IconButton onClick={handleClick}>
                <MoreHorizIcon />
              </IconButton>
              <Menu anchorEl={anchorEl} open={openMenu} onClose={handleClose}>
                <MenuItem onClick={() => setOpenConfirmMessage(true)}>
                  Delete
                </MenuItem>
                <MenuItem onClick={handleEditPost}>Edit</MenuItem>
                <MenuItem onClick={() => setOpenShowApplicationsModal(true)}>
                  Show Applications
                </MenuItem>
              </Menu>
            </>
          )
        }
        title={
          <Box sx={{ display: "flex", alignItems: "center" }}>
            <Typography variant="h6" sx={{ mr: 1 }}>
              {employerUserName}
            </Typography>
            <Typography variant="body2" color="textSecondary">
              @{companyName.split(" ").join("_").toLowerCase()}
            </Typography>
          </Box>
        }
      />
      <CardContent>
        {applicationCount > 0 && (
          <Typography variant="body2" color="textSecondary" component="p">
            <GroupIcon sx={{ mr: 0.5 }} /> Application Count: {applicationCount}
          </Typography>
        )}
        <Typography
          variant="h6"
          component="p"
          onClick={handleToggleHidden}
          sx={{ cursor: "pointer", marginBottom: 1 }}
        >
          <strong>Title: </strong>
          {Title && Title.includes("{")
            ? Title.substring(0, Title.indexOf("{"))
            : Title || "No Title"}
        </Typography>

        <Typography variant="body2" color="textSecondary" component="p">
          <strong>Description: </strong> {truncatedDescription}
        </Typography>
        <Typography variant="body2" color="textSecondary" component="p">
          <strong>Job Requirements: </strong> {truncatedJobRequirements}
        </Typography>
        {!hidden && (
          <>
            <div
              style={{
                overflowX: "auto", // Enable horizontal scrolling if content overflows
                display: "flex", // Display children elements in a row
                flexDirection: "row", // Align children in a row
                alignItems: "center", // Align items vertically center
                padding: "5%",
                margin: "5%",
                gap: "2px", // Add gap between children for spacing
                flexWrap: "nowrap", // Prevent wrapping to next row
              }}
            >
              {/* Each Typography component */}
              <Typography
                className="p-2"
                variant="body2"
                color="textSecondary"
                component="p"
                style={{ minWidth: "200px" }} // Set a minimum width to avoid collapsing
              >
                <strong>Experience: </strong> {Experience} Years
              </Typography>

              <Typography
                className="p-2"
                variant="body2"
                color="textSecondary"
                component="p"
                style={{ minWidth: "200px" }}
              >
                <FmdGoodIcon sx={{ mr: 0.5 }} /> <strong>Location: </strong>{" "}
                {location}
              </Typography>

              <Typography
                className="p-2"
                variant="body2"
                color="textSecondary"
                component="p"
                style={{ minWidth: "200px" }}
              >
                <strong>Employment Type: </strong> {employmentType}
              </Typography>

              <Typography
                className="p-2"
                variant="body2"
                color="textSecondary"
                component="p"
                style={{ minWidth: "200px" }}
              >
                <strong>Field: </strong> {fieldName}
              </Typography>

              <Typography
                className="p-2"
                variant="body2"
                color="textSecondary"
                component="p"
                style={{ minWidth: "200px" }}
              >
                <strong>Job Name : </strong> {jobName}
              </Typography>
            </div>

            <Box sx={{ overflowX: "auto", width: "100%" }}>
              <div style={{ display: "flex", flexDirection: "row" }}>
                {/* Left Section - Skills */}
                <Box
                  display="flex"
                  alignItems="center"
                  sx={{
                    minWidth: 0,
                    p: 2,
                    mr: 2,
                    flexShrink: 0,
                    flexBasis: "70%",
                    position: "relative", // Ensure the container is relative for tooltip positioning
                  }}
                >
                  <Grid item container justifyContent="center">
                    <Button
                      onClick={handleOpenShowSkillsModal}
                      type="submit"
                      variant="contained"
                      color="primary"
                    >
                      Show
                    </Button>
                  </Grid>
                  <Box
                    flexGrow={1}
                    sx={{ overflow: "hidden", textOverflow: "ellipsis" }}
                  >
                    <Typography
                      variant="body2"
                      color="textSecondary"
                      component="p"
                    >
                      <strong>Skills:</strong>
                    </Typography>
                    <ul
                      style={{
                        listStyleType: "none",
                        paddingInlineStart: 0,
                        marginBottom: 0,
                        whiteSpace: "nowrap",
                        overflow: "hidden",
                        textOverflow: "ellipsis",
                      }}
                    >
                      {skills.map((skill, index) => (
                        <li key={index}>
                          <Tooltip
                            title={
                              skill.length > MAX_DISPLAY_LENGTH
                                ? skill
                                : undefined
                            }
                            placement="top"
                          >
                            <span>
                              <Typography component="span" sx={{ mr: 1 }}>
                                &bull;
                              </Typography>
                              {truncateText(skill, MAX_DISPLAY_LENGTH)}
                            </span>
                          </Tooltip>
                        </li>
                      ))}
                    </ul>
                  </Box>
                </Box>

                {/* Right Section - Qualifications */}
                <Box
                  display="flex"
                  alignItems="center"
                  sx={{
                    minWidth: 0,
                    p: 2,
                    ml: 2,
                    flexShrink: 0,
                    flexBasis: "50%",
                    position: "relative", // Ensure the container is relative for tooltip positioning
                  }}
                >
                  <Box
                    flexGrow={1}
                    sx={{ overflow: "hidden", textOverflow: "ellipsis" }}
                  >
                    <Typography
                      variant="body2"
                      color="textSecondary"
                      component="p"
                    >
                      <strong>Qualifications:</strong>
                    </Typography>
                    <ul
                      style={{
                        listStyleType: "none",
                        paddingInlineStart: 0,
                        marginBottom: 0,
                        whiteSpace: "nowrap",
                        overflow: "hidden",
                        textOverflow: "ellipsis",
                      }}
                    >
                      {qualifications.map((qualification, index) => (
                        <li key={index}>
                          <Tooltip
                            title={
                              qualification.length > MAX_DISPLAY_LENGTH
                                ? qualification
                                : undefined
                            }
                            placement="top"
                          >
                            <span>
                              <Typography component="span" sx={{ mr: 1 }}>
                                &bull;
                              </Typography>
                              {truncateText(qualification, MAX_DISPLAY_LENGTH)}
                            </span>
                          </Tooltip>
                        </li>
                      ))}
                    </ul>
                  </Box>
                </Box>
              </div>
            </Box>
          </>
        )}
        {postImage && (
          <Box
            onClick={() => setOpenShowPostImageModal(true)}
            sx={{
              width: "100%",
              height: 450,
              overflow: "hidden",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              cursor: "pointer",
              borderRadius: 8,
              marginTop: 2,
            }}
          >
            <img
              src={postImage}
              alt="Post"
              style={{
                maxWidth: "100%",
                maxHeight: "100%",
                objectFit: "cover",
              }}
            />
          </Box>
        )}
      </CardContent>
      <Divider />
      <ShowApplicationsModal
        openShowApplicationsModal={openShowApplicationsModal}
        handleCloseShowApplicationsModal={() =>
          setOpenShowApplicationsModal(false)
        }
        postId={id}
      />
      <ShowPostImageModal
        openShowPostImageModal={openShowPostImageModal}
        handleCloseShowPostImageModal={() => setOpenShowPostImageModal(false)}
        postImage={postImage}
      />
      <PostModal
        openPostModal={openPostModal}
        handleClose={() => setOpenPostModal(false)}
        operationType="edit"
        id={id}
        fieldName={fieldName}
        Title={Title}
        description={description}
        jobRequirments={jobRequirements}
        location={location}
        employmentType={employmentType}
        skills={skills}
        qualifications={qualifications}
        field={field}
        Experience={Experience}
        postImage={postImage}
      />
      <ConfirmMessage
        openConfirmMessage={openConfirmMessage}
        handleCloseConfirmMessage={() => setOpenConfirmMessage(false)}
        response="Do you want to delete this post?"
        Title="DELETE CONFIRM"
        postId={id}
        operationType="deletePost"
      />
      <ShowSkillsModal
        openShowSkillsModal={openShowSkillsModal}
        handleCloseShowSkillsModal={handleCloseShowSkillsModal}
        skills={skills}
        qualifications={qualifications}
      />
    </Card>
  );
};

export default PostCardCompany;
