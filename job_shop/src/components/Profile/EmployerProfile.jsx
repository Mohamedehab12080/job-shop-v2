import React, { useEffect, useState } from "react";
import { KeyboardBackspace } from "@mui/icons-material";
import { useNavigate, useParams } from "react-router-dom";
import { Avatar, Box, Tab } from "@mui/material";
import { Button } from "@mui/material";
import BussinessCetnerIcon from "@mui/icons-material/BusinessCenter";
import LocationIcon from "@mui/icons-material/LocationOn";
import CalendarIcon from "@mui/icons-material/CalendarMonth";
import SkillsIcon from "@mui/icons-material/Attractions";
import { TabContext, TabList, TabPanel } from "@mui/lab";
import ProfileModal from "./ProfileModal";
import { useDispatch, useSelector } from "react-redux";
import ContactsModal from "../../responses/CotactsModal";
import ConnectWithoutContactIcon from "@mui/icons-material/ConnectWithoutContact";
import ShowFieldsModal from "../modals/companyModals/FieldsModals/ShowFieldsModal";
import { getEmployerProfile } from "../../store/company/Employer/Action";
import ShowEmployerFieldsModal from "../modals/companyModals/FieldsModals/ShowEmployerFieldsModal";
import ShowPostImageModal from "../HomeSection/posts/ShowPostImageModal";

const EmployerProfile = () => {
  const [tabValue, setTabValue] = useState("1");
  const [openProfileModal, setOpenProfileModal] = useState(false);
  const handleOpenProfileModel = () => setOpenProfileModal(true);
  const handleClose = () => setOpenProfileModal(false);
  const navigate = useNavigate();
  const handleBack = () => navigate(-1);
  const { id } = useParams();
  const auth=useSelector(state=>state.auth);
  const dispatch = useDispatch();
  const emp = useSelector((state) => state.emp);
  const [openShowPostImageModal,setOpenShowPostImageModal]=React.useState(false);

  const handleCloseShowPostImageModal=()=>setOpenShowPostImageModal(false);
  
  const [imageForShow,setImageForShow]=useState("");
  const handleOpenShowPostImageModal=(value)=>{
    setOpenShowPostImageModal(true);
    setImageForShow(value);
};
  const [employerData, setEmployerData] = useState(null);
  const [contactList, setContactList] = useState([]);
  const [isRequestUser, setIsRequestUser] = useState(false);
  const [openShowEmployerFieldsModal, setOpenShowEmployerFieldsModal] =
    useState(false);
  const handleOpenShowEmployerFieldsModal = () =>
    setOpenShowEmployerFieldsModal(true);
  const handleCloseShowEmployerFields = () =>
    setOpenShowEmployerFieldsModal(false);
  const [openContactsModal, setOpenContactsModal] = useState(false);
  const handleOpenContactsModal = () => setOpenContactsModal(true);
  const handleCloseContactsModal = () => setOpenContactsModal(false);

  const [companyAdminId,setCompanyAdminId]=useState(0);
  const [coverImage, setCoverImage] = useState("");
  const [profileImage, setProfileImage] = useState("");

  useEffect(() => {
    
    dispatch(getEmployerProfile(id));
  }, [dispatch, id]); // Dependency array ensures this effect runs only when id or dispatch changes

  // Log jobSeeker state changes
  useEffect(() => {
    setEmployerData(emp.employerData);
  }, [emp.employerData]); // Dependency array ensures this effect runs only when jobSeeker changes

  useEffect(() => {
    if (employerData !== null) {
      setProfileImage(employerData.picture);
      setContactList(employerData.contacts);
      setCoverImage(employerData.coverImage);
      setCompanyAdminId(employerData.companyAdministratorId);
      setIsRequestUser(employerData.req_user);
    }
    
    // setIsRequestUser(emp.isRequestUser);
  }, [emp.fields, employerData]);

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

  return (
    <div>
      <section className={`z-50 flex items-center sticky top-0 bg-opacity-95`}>
        <KeyboardBackspace className="cursor-pointer" onClick={handleBack} />
        <h1 className="py-5 text-xl font-bold opacity-90 ml-5">
          {employerData !== null && employerData.userName}
        </h1>
      </section>

      <section>
        <img
          onClick={()=>handleOpenShowPostImageModal(coverImage)}
          className="w-[100%] h-[15rem] object-cover cursor-pointer"
          src={coverImage}
          alt="Cover Image"
        />
      </section>

      <section className="pl-6">
        <div className="flex justify-between items-start mt-5 h-[5rem]">
          <Avatar
           onClick={()=>handleOpenShowPostImageModal(profileImage)} className='transform -translate-y-24 cursor-pointer'
            alt="BOB"
            src={profileImage}
            sx={{ width: "10rem", height: "10rem", border: "4px solid white" }}
          />
         {auth.user.id !== companyAdminId &&(
            <>
                 {employerData !== null && employerData.req_user ? (
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
                    {employerData !== null && employerData.followed
                        ? "Unfollow"
                        : "follow"}
                    </Button>
                )}
            </>
         ) }
        </div>
        <div>
          <div className="flex item-center">
            <h1 className="font-bold text-lg">
              {employerData !== null && employerData.userName}
            </h1>
            <div className="flex items-center space-x-20">
              <div className="ml-10 flex items-center space-x-1 font-semibold">
                <span>
                  {employerData !== null && employerData.followers && employerData.followers.length > 0
                    ? employerData.followers.length
                    : 0}
                </span>
                <span className="text-gray-500">Followers</span>
              </div>
              <div className="flex items-center space-x-1 font-semibold">
                <span>
                  {employerData !== null && employerData.followings &&employerData.followings.length > 0
                    ? employerData.followings.length
                    : 0}
                </span>
                <span className="text-gray-500">Following</span>
              </div>
            </div>
          </div>
          <div className="flex space-x-2">
            <p className="text-lg font-bold"> Email :</p>{" "}
            <p className="text-gray-500">
              {employerData !== null && employerData.email}
            </p>
          </div>
          <div className="flex space-x-2">
            <p className="text-lg font-bold"> Company :</p>{" "}
            <p className="text-gray-500">
              {employerData !== null && employerData.companyName}
            </p>
          </div>
        </div>

        <div className="mt-2 space-y-2">
          <>
            <p>{employerData !== null && employerData.description}</p>
            <div className="flex space-x-5 cursor-pointer">
              <div
                className="flex items-center text-gray-500"
                onClick={handleOpenShowEmployerFieldsModal}
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
                  {employerData !== null && employerData.address}
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
                <Tab
                  label={`Posts (${
                    employerData !== null && employerData.postCount
                  })`}
                  value="2"
                />
                <Tab label="Employers" value="3" />
              </TabList>
            </Box>
            <TabPanel value="1">Fields</TabPanel>
            <TabPanel value="2">Posts</TabPanel>
            <TabPanel value="3">Employers</TabPanel>
          </TabContext>
        </Box>
      </section>

      <section>
        <ProfileModal open={openProfileModal} handleClose={handleClose} data={employerData} Type={"employer"} />
      </section>

      <section>
        <ShowEmployerFieldsModal
          openShowEmployerFieldsModal={openShowEmployerFieldsModal}
          handleCloseShowEmployerFields={handleCloseShowEmployerFields}
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

      <ShowPostImageModal openShowPostImageModal={openShowPostImageModal} handleCloseShowPostImageModal={handleCloseShowPostImageModal} postImage={imageForShow} />

      </section>
    </div>
  );
};

export default EmployerProfile;
