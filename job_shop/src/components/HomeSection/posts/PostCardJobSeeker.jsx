import React, { useState, useRef } from "react";
import RepeatIcon from "@mui/icons-material/Repeat";
import {
  Avatar,
  Box,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  Typography,
} from "@mui/material";
import defaultImg from "../../common/images/default.jpg";
import myImg from "../../common/images/myPic.jpg";
import { useNavigate } from "react-router-dom";
import Button from "@mui/material/Button";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Groups2Icon from "@mui/icons-material/Groups2";
import axios from "axios";
import { CheckCircle } from "@mui/icons-material";
import { ErrorOutline } from "@mui/icons-material";
import { LocationOn } from "@mui/icons-material";
import FmdGoodIcon from "@mui/icons-material/FmdGood";
import GroupIcon from "@mui/icons-material/Group";
import ApplyModal from "../../modals/JobSeekerModal/ApplicationsModals/ApplyModal";
import AddSkillsModal from "../../modals/JobSeekerModal/skillsModals/AddSkillsModal";
import ShowPostImageModal from "./ShowPostImageModal";
const PostCardJobSeeker = ({
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
  profileId,
  skills,
  qualifications,
  field,
  employerpicture,
  fieldName,
  createdDate,
  remainedSkills,
  remainedQualifications,
  state,
  matchedSkills,
  matchedQulifications,
  applicationCount,
  jobSeekerId,
  postImage,
  statusCode,
  jobName,
}) => {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const [openApplyModal, setOpenApplyModal] = useState(false);
  const handleOpenApplyModal = () => setOpenApplyModal(true);
  const handleCloseApplyModal = () => setOpenApplyModal(false);
  const [openAddSkillsModal, setOpenAddSkillsModal] = useState(false);
  const handleOpenAddSkillsModal = () => setOpenAddSkillsModal(true);
  const handleCloseAddSkillsModal = () => setOpenAddSkillsModal(false);
  const navigate = useNavigate();
  const [hidden, setHidden] = useState("hidden");
  const [openShowPostImageModal, setOpenShowPostImageModal] =
    React.useState(false);
  const handleOpenShowPostImageModal = () => setOpenShowPostImageModal(true);
  const handleCloseShowPostImageModal = () => setOpenShowPostImageModal(false);

  const handleToggleHidden = () => {
    setHidden(!hidden);
  };
  const handleHidden = (event) => {
    if (hidden === "hidden") {
      setHidden("");
    } else {
      setHidden("hidden");
    }
  };

  // You might want to make this dynamic too, depending on your application logic
  const handleClose = () => {
    setAnchorEl(null);
  };
  const postRef = useRef(null);

  const truncatedJobRequirements =
    jobRequirements && jobRequirements.length > 300
      ? `${jobRequirements.substring(0, 300)}...`
      : jobRequirements;
  const truncatedDescription =
    description && description.length > 300
      ? `${description.substring(0, 300)}...`
      : description;

  return (
    <div sx={{ width: "100%" }}>
      <Card
        sx={{
          mb: 4,
          border: "1px solid #e0e0e0",
          borderRadius: 8,
        }}
      >
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
            <Button
              variant="contained"
              id="basic-button"
              onClick={handleOpenApplyModal}
              disabled={!state}
            >
              {statusCode && (
                <>
                  Apply with{" "}
                  {statusCode.substring(
                    statusCode.indexOf("("),
                    statusCode.indexOf(")")
                  )}
                  {")"}
                </>
              )}
            </Button>
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
              <GroupIcon sx={{ mr: 0.5 }} /> Application Count:{" "}
              {applicationCount}
            </Typography>
          )}
          {!state ? <div>You are not qualified</div> : <div></div>}
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
          <Typography
            className="mb-3"
            variant="body2"
            color="textSecondary"
            component="p"
          >
            <strong>Description: </strong> {truncatedDescription}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            <strong>Job Requirements: </strong> {truncatedJobRequirements}
          </Typography>
          {!hidden && (
            <>
              <Divider sx={{ my: 2, backgroundColor: "black" }} />
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

              <Box display="flex" alignItems="center" className="mb-3 mt-4">
                <Box flexGrow={1}>
                  {matchedSkills && matchedSkills.length > 0 && (
                    <div className="flex items-center text-gray-600 mr-0">
                      <CheckCircle style={{ color: "green" }} />
                      <Typography
                        className="mt-2"
                        variant="subtitle1"
                        fontWeight="bold"
                        mb={1}
                      >
                        Matched Skills:
                      </Typography>
                      <ol>
                        {matchedSkills.map((skill, index) => (
                          <li
                            key={index}
                            style={{
                              listStyleType: "disc",
                              marginLeft: "0.5em", // Reduced margin to bring content closer to the disc
                            }}
                          >
                            {skill}
                          </li>
                        ))}
                      </ol>
                    </div>
                  )}
                </Box>
                <Divider
                  orientation="vertical"
                  flexItem
                  sx={{
                    mx: 2,
                    height: "80%", // Adjust the height as per your design
                    backgroundColor: "black", // Change the color to black
                    borderLeft: "1px solid black", // Add a border for visibility
                  }}
                />
                <Box flexGrow={1}>
                  {remainedSkills && remainedSkills.length > 0 && (
                    <div className="flex items-center text-gray-600 mr-3">
                      <ErrorOutline style={{ color: "red" }} />
                      <Typography
                        className="mt-2"
                        variant="subtitle1"
                        fontWeight="bold"
                        mb={1}
                      >
                        Missed Skills:
                      </Typography>
                      <ol>
                        {remainedSkills.map((skill, index) => (
                          <li
                            key={index}
                            className="mr-1"
                            onClick={() => handleOpenAddSkillsModal()}
                            style={{
                              cursor: "pointer",
                              textDecoration: "underline",
                              listStyleType: "none",
                              display: "flex",
                              alignItems: "center",
                            }}
                          >
                            <span
                              style={{
                                content: '"\u25E6"', // Unicode for empty circle
                                marginRight: "0.5em",
                              }}
                            >
                              &#9675;
                            </span>
                            {skill}
                          </li>
                        ))}
                      </ol>
                    </div>
                  )}
                </Box>
              </Box>
              <Box display="flex" alignItems="center">
                <Box flexGrow={1}>
                  {matchedQulifications && matchedQulifications.length > 0 && (
                    <div className="flex items-center text-gray-600 mr-3">
                      <CheckCircle style={{ color: "green" }} />
                      <Typography
                        className="mt-2"
                        variant="subtitle1"
                        fontWeight="bold"
                        mb={1}
                      >
                        Matched Qualifications:
                      </Typography>

                      <ul>
                        {matchedQulifications.map((qualification, index) => (
                          <li key={index} className="mr-1">
                            - {qualification}{" "}
                          </li>
                        ))}
                      </ul>
                    </div>
                  )}
                </Box>
                <Divider
                  orientation="vertical"
                  flexItem
                  sx={{
                    mx: 2,
                    height: "80%", // Adjust the height as per your design
                    backgroundColor: "black", // Change the color to black
                    borderLeft: "1px solid black", // Add a border for visibility
                  }}
                />
                <Box flexGrow={1}>
                  {remainedQualifications &&
                    remainedQualifications.length > 0 && (
                      <div className="flex items-center text-gray-600 mr-3">
                        <ErrorOutline style={{ color: "red" }} />
                        <Typography
                          className="mt-2 text-xl"
                          variant="subtitle1"
                          fontWeight="bold"
                          mb={1}
                        >
                          Missed Qualifications:
                        </Typography>

                        <ul>
                          {remainedQualifications.map((remainedQual, index) => (
                            <li
                              key={index}
                              className="text-lg"
                              onClick={() => handleOpenAddSkillsModal()}
                              style={{
                                cursor: "pointer",
                                textDecoration: "underline",
                              }}
                            >
                              {`->`} {remainedQual}
                            </li>
                          ))}
                        </ul>
                      </div>
                    )}
                </Box>
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
        <ApplyModal
          field={fieldName}
          postIdd={id}
          openApplyModal={openApplyModal}
          handleClose={handleCloseApplyModal}
          skills={matchedSkills}
          Qualifications={matchedQulifications}
          id={jobSeekerId}
          postRef={postRef} // Receive the postRef
        />

        <AddSkillsModal
          openAddSkillsModal={openAddSkillsModal}
          id={jobSeekerId}
          skills={remainedSkills}
          Qualifications={remainedQualifications}
          handleCloseAddSkillsModal={handleCloseAddSkillsModal}
        />

        <ShowPostImageModal
          openShowPostImageModal={openShowPostImageModal}
          handleCloseShowPostImageModal={handleCloseShowPostImageModal}
          postImage={postImage}
        />
      </Card>
    </div>
  );
};

export default PostCardJobSeeker;
