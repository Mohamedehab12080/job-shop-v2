import React, { useEffect, useState } from "react";
import { KeyboardBackspace } from "@mui/icons-material";
import { useNavigate, useParams } from "react-router-dom";
import { Avatar, Box, Grid, IconButton, Tab, TextField } from "@mui/material";
import { Button } from "@mui/material";
import BussinessCetnerIcon from "@mui/icons-material/BusinessCenter";
import LocationIcon from "@mui/icons-material/LocationOn";
import CalendarIcon from "@mui/icons-material/CalendarMonth";
import SkillsIcon from "@mui/icons-material/Attractions";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import ProfileModal from "./ProfileModal";
import { useDispatch, useSelector } from "react-redux";
import { getCompanyInfo } from "../../store/company/Action";
import ContactsModal from "../../responses/CotactsModal";
import ConnectWithoutContactIcon from "@mui/icons-material/ConnectWithoutContact";
import ShowFieldsModal from "../modals/companyModals/FieldsModals/ShowFieldsModal";
import ShowPostImageModal from "../HomeSection/posts/ShowPostImageModal";
import PostCardCompany from "../HomeSection/posts/PostCardCompany";
import { Close } from "@mui/icons-material";

const CompanyProfile = () => {
  const [tabValue, setTabValue] = useState("1");
  const [openProfileModal, setOpenProfileModal] = useState(false);
  const handleOpenProfileModel = () => setOpenProfileModal(true);
  const handleClose = () => setOpenProfileModal(false);
  const navigate = useNavigate();
  const handleBack = () => navigate(-1);
  const { id } = useParams();
  const dispatch = useDispatch();
  const comp = useSelector((state) => state.comp);
  const [companyData, setCompanyData] = useState(null);
  const [fieldsNames, setFieldsNames] = useState([]);
  const [employersUserNames, setEmployersUserNames] = useState([]);
  const [contactList, setContactList] = useState([]);
  const [isRequestUser, setIsRequestUser] = useState(false);
  const [openShowFieldsModal, setOpenShowFieldsModal] = useState(false);
  const handleOpenShowFieldsModal = () => setOpenShowFieldsModal(true);
  const handleCloseShowFieldsModal = () => setOpenShowFieldsModal(false);
  const [openContactsModal, setOpenContactsModal] = useState(false);
  const handleOpenContactsModal = () => setOpenContactsModal(true);
  const handleCloseContactsModal = () => setOpenContactsModal(false);

  const [openShowPostImageModal, setOpenShowPostImageModal] =
    React.useState(false);

  const handleCloseShowPostImageModal = () => setOpenShowPostImageModal(false);

  const [imageForShow, setImageForShow] = useState("");
  const handleOpenShowPostImageModal = (value) => {
    setOpenShowPostImageModal(true);
    setImageForShow(value);
  };
  const [coverImage, setCoverImage] = useState("");
  const [profileImage, setProfileImage] = useState("");
  const [filterInputPost, setFilterInputPost] = React.useState("");
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [fetchedCompanyPosts, setFetchedCompanyPosts] = useState([]);

  const [selectedFilterFields, setSelectedFiterFields] = useState([]);

  const [filteredPostsByField, setFilteredPostsByField] = useState([]);

  useEffect(() => {
    dispatch(getCompanyInfo(id));
  }, [dispatch, id]); // Dependency array ensures this effect runs only when id or dispatch changes

  // Log jobSeeker state changes
  useEffect(() => {
    setCompanyData(comp.companyData);
  }, [comp.companyData]); // Dependency array ensures this effect runs only when jobSeeker changes

  useEffect(() => {
    setFieldsNames(comp.fields);
    setEmployersUserNames(comp.employers);
    if (companyData !== null) {
      setProfileImage(companyData.picture);
      setContactList(companyData.contacts);
      setCoverImage(companyData.coverImage);
    }
    setIsRequestUser(comp.isRequestUser);
  }, [comp.fields, comp.employers, comp.isRequestUser, companyData]);

  useEffect(() => {
    if (comp) {
      setFetchedCompanyPosts(comp.companyPosts);
    }
  }, [comp]);

  const handleFilterPosts = (input) => {
    const filtered = fetchedCompanyPosts.filter((post) => {
      return post.title.toLowerCase().includes(input.toLowerCase());
    });
    console.log("Filtered : ", filtered);
    setFilteredPosts(filtered);
    setFilterInputPost(input);
  };

  const handleFollowUser = () => {
    console.log("Follow");
  };

  const handleChange = (event, newValue) => {
    setTabValue(newValue);

    if (newValue === 4) {
      console.log("");
    } else if (newValue == 1) {
      console.log("Applications");
    }
  };

  const getUniqueFieldNames = () => {
    // Use Set to store unique fieldNames
    const uniqueFieldNames = new Set();
    // Iterate through filteredPosts and add each fieldName to the Set
    fetchedCompanyPosts.forEach((p) => {
      uniqueFieldNames.add(p.fieldName);
    });
    // Convert Set to array and return
    return [...uniqueFieldNames];
  };

  const handleFilterByField = (fieldName) => {
    if (fieldName === "") {
      setFilteredPostsByField(filteredPosts);
    } else {
      const filtered = fetchedCompanyPosts.filter(
        (p) => p.fieldName === fieldName
      );
      setSelectedFiterFields(fieldName);
      setFilteredPostsByField(filtered);
    }
  };

  const handleRemoveField = () => {
    setSelectedFiterFields("");
  };
  return (
    <div>
      <section className={`z-50 flex items-center top-0 bg-opacity-95`}>
        <KeyboardBackspace className="cursor-pointer" onClick={handleBack} />
        <h1 className="py-5 text-xl font-bold opacity-90 ml-5">
          {companyData !== null && companyData.userName}
        </h1>
      </section>

      <section>
        <img
          onClick={() => handleOpenShowPostImageModal(coverImage)}
          className="w-[100%] h-[15rem] object-cover"
          src={coverImage}
          alt="Cover Image"
        />
      </section>

      <section className="pl-6">
        <div className="flex justify-between items-start mt-5 h-[5rem]">
          <Avatar
            onClick={() => handleOpenShowPostImageModal(profileImage)}
            className="transform -translate-y-24"
            alt="BOB"
            src={profileImage}
            sx={{ width: "10rem", height: "10rem", border: "4px solid white" }}
          />
          {companyData !== null && companyData.req_user ? (
            <Button
              onClick={handleOpenProfileModel}
              variant="contained"
              sx={{ borderRadius: "20px" }}
            >
              Edit Profile
            </Button>
          ) : (
            <Button
              onClick={handleFollowUser}
              variant="contained"
              sx={{ borderRadius: "20px" }}
            >
              {companyData !== null && companyData.followed
                ? "Unfollow"
                : "follow"}
            </Button>
          )}
        </div>
        <div>
          <div className="flex item-center">
            <h1 className="font-bold text-lg">
              {companyData !== null && companyData.userName}
            </h1>
            <div className="flex items-center space-x-20">
              <div className="ml-10 flex items-center space-x-1 font-semibold">
                <span>
                  {companyData !== null && companyData.followers.length > 0
                    ? companyData.followers.length
                    : 0}
                </span>
                <span className="text-gray-500">Followers</span>
              </div>
              <div className="flex items-center space-x-1 font-semibold">
                <span>
                  {companyData !== null && companyData.followings.length > 0
                    ? companyData.followings.length
                    : 0}
                </span>
                <span className="text-gray-500">Following</span>
              </div>
            </div>
          </div>
          <p className="text-gray-500">
            {companyData !== null && companyData.email}
          </p>
        </div>

        <div className="mt-2 space-y-2">
          <>
            <p>{companyData !== null && companyData.description}</p>
            <div className="flex space-x-5 cursor-pointer">
              <div
                className="flex items-center text-gray-500"
                onClick={handleOpenShowFieldsModal}
              >
                <SkillsIcon />
                <p className="ml-2 mt-3">Fields</p>
              </div>
              <div className="flex items-center text-gray-500 cursor-pointer">
                <BussinessCetnerIcon />
                <p className="ml-2 mt-3">Employers</p>
              </div>

              <div
                className="flex items-center text-gray-500 cursor-pointer"
                onClick={handleOpenContactsModal}
              >
                <ConnectWithoutContactIcon />
                <p className="ml-2 mt-3" sx={{ color: "blue" }}>
                  Contacts Info
                </p>
              </div>

              <div className="flex items-center text-gray-500">
                <LocationIcon />
                <p className="ml-2 mt-3">
                  {companyData !== null && companyData.address}
                </p>
              </div>

              <div className="flex items-center text-gray-500">
                <CalendarIcon />
                <p className="ml-2 mt-3">Joined Jun 2024</p>
              </div>
            </div>
          </>
        </div>
      </section>
      {/* <section>
            <ShowSkillsmodal openShowSkillsModal={openShowSkillsModal} handleCloseShowSkillsModal={handleCloseShowSkillsModal} skills={skills} qualifications={qualifications}/>
        </section> */}
      <section className="py-5">
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={tabValue}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
                <Tab label="Fields" value="1" />
                <Tab label="Posts" value="2" />
                <Tab label="Employers" value="3" />
              </TabList>
            </Box>
            <TabPanel value="1">Fields</TabPanel>
            <TabPanel value="2">
              <Grid item>
                {selectedFilterFields !== "" && (
                  <>
                    <div>
                      <span>{selectedFilterFields}</span>
                      <IconButton
                        onClick={() => handleRemoveField()}
                        aria-label="delete"
                        size="small"
                      >
                        <Close />
                      </IconButton>
                    </div>
                  </>
                )}
              </Grid>
              <Grid container spacing={2} alignItems="center">
                {/* Map through unique fieldNames to render buttons */}
                {getUniqueFieldNames()
                  .filter((fieldName) => fieldName !== selectedFilterFields)
                  .map((fieldName, index) => (
                    <Grid item key={index}>
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleFilterByField(fieldName)}
                      >
                        {fieldName}
                      </Button>
                    </Grid>
                  ))}
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    className="mb-4"
                    id="filterInputPost"
                    name="filterInputPost"
                    label="Filter Posts"
                    value={filterInputPost}
                    onChange={(e) => handleFilterPosts(e.target.value)}
                  />
                </Grid>
                {/* Render filteredPostsByField if it's not empty, otherwise render filteredPosts */}
                {filteredPostsByField.length > 0
                  ? filteredPostsByField.map((p, index) => (
                      <Grid item xs={12} key={index}>
                        <PostCardCompany
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
                          experience={p.experience}
                          postImage={p.postImage}
                          jobName={p.jobName}
                        />
                      </Grid>
                    ))
                  : // Render filteredPosts if filteredPostsByField is empty, otherwise render fetchedCompanyPosts
                  filterInputPost !== ""
                  ? filteredPosts.map((p, index) => (
                      <Grid item xs={12} key={index}>
                        <PostCardCompany
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
                          experience={p.experience}
                          postImage={p.postImage}
                          jobName={p.jobName}
                        />
                      </Grid>
                    ))
                  : fetchedCompanyPosts.map((p, index) => (
                      <Grid item xs={12} key={index}>
                        <PostCardCompany
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
                          experience={p.experience}
                          postImage={p.postImage}
                          jobName={p.jobName}
                        />
                      </Grid>
                    ))}
              </Grid>
            </TabPanel>
            <TabPanel value="3">Employers</TabPanel>
          </TabContext>
        </Box>
      </section>

      <section>
        <ProfileModal
          open={openProfileModal}
          handleClose={handleClose}
          data={companyData}
          Type={"Admin"}
        />
      </section>

      <section>
        <ShowFieldsModal
          openShowFieldsModal={openShowFieldsModal}
          handleCloseShowFieldsModal={handleCloseShowFieldsModal}
          isRequestUser={isRequestUser}
          userId={id}
        />
      </section>
      {/* <section> 
            <MessageModal openMessageModal={openEductationModal} handleCloseMessageModal={handleCloseEducationModal} response={jobSeekerData !==null && jobSeekerData.education} Title={"Education"}/>
        </section> */}
      <section>
        <ContactsModal
          openContactsModal={openContactsModal}
          handleCloseContactsModal={handleCloseContactsModal}
          contactsList={contactList}
          isRequestUser={isRequestUser}
        />
      </section>
      <section>
        <ShowPostImageModal
          openShowPostImageModal={openShowPostImageModal}
          handleCloseShowPostImageModal={handleCloseShowPostImageModal}
          postImage={imageForShow}
        />
      </section>
    </div>
  );
};

export default CompanyProfile;
