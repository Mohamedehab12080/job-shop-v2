import  React, { useEffect, useState } from "react";
import { navigation } from "./NavigationMenu";
import logo from "../common/images/logo.png";
import userLogo from "../common/images/default.jpg";
import { useNavigate } from "react-router-dom";
import { Button, Avatar} from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import PostModal from "../modals/companyModals/postModals/PostModal";
import { useDispatch, useSelector } from "react-redux";
import { Logout } from "../../store/Auth/Action";
import CreateFieldModal from "../modals/companyModals/FieldsModals/CreateFieldModal";
import CreateEmployerModal from "../modals/companyModals/EmployersModals/CreateEmployerModal";
import AddSkillsModal from "../modals/JobSeekerModal/skillsModals/AddSkillsModal";
import ShowApplicationsModal from "../modals/companyModals/postModals/ShowApplicationsModal";
import GiveEmployerFields from "../modals/companyModals/FieldsModals/GiveEmployerFields";
import ConfirmMessage from "../../responses/ConfirmMessage";
import RecommendationModal from "../modals/companyModals/postModals/RecommedationModal";
// import GiveEmployerFields from "../modals/companyModals/FieldsModals/GiveEmployerFields";
export const Navigation = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  const auth = useSelector(state => state.auth);
  const open = Boolean(anchorEl);
  const [openPostModal,setOpenPostModal]=useState(false);
  const handleOpenPostModal=()=>setOpenPostModal(true);
  const handleClosePostModal=()=>setOpenPostModal(false);

  const [openCreateEmployerModal,setOpenCreateEmployerModal]=useState(false);
  const handleOpenCreateEmployerModal=()=>setOpenCreateEmployerModal(true);
  const handleCloseCreateEmployerModal=()=>setOpenCreateEmployerModal(false);

  
  const [openCreateFieldModal,setOpenCreateFieldModal]=useState(false);
  const handleOpenCreateFieldModal=()=>setOpenCreateFieldModal(true);
  const handleCloseCreateFieldModal=()=>setOpenCreateFieldModal(false);

  const [openGiveEmployerFields,setOpenGiveEmployerFields]=useState(false);
  const handleOpenGiveEmployerFields=()=>setOpenGiveEmployerFields(true);
  const handleCloseGiveEmployerFields=()=>setOpenGiveEmployerFields(false);

  const [openAddSkillsModal,setOpenAddSkillsModal]=useState(false);
  const handleOpenAddSkillsModal=()=>setOpenAddSkillsModal(true);
  const handleCloseAddSkillsModal=()=>setOpenAddSkillsModal(false);

  const [openShowApplicationsModal,setOpenShowApplicationsModal]=useState(false);
  const handleOpenShowApplicationsModal=()=>setOpenShowApplicationsModal(true);
  const handleCloseShowApplicationsModal=()=>setOpenShowApplicationsModal(false);

  const [navigationValue,setNavigationValue]=useState("");
  const dispatch=useDispatch()

  useEffect(()=>
  {
    if(auth.user.userType === "jobSeeker")
      {
        setNavigationValue(`/profile/${auth.user.id}`);
      }else if(auth.user.userType === "Employer")
        {
          setNavigationValue(`/employerProfile/${auth.user.id}`);
        }else 
        {
          setNavigationValue(`/companyProfile/${auth.user.id}`);
        }
  },[])
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const navigate = useNavigate();
  // const handleLogout=()=>
  // {
  //   dispatch(Logout());
  // }

  const [openConfirmMessage,setOpenConfirmMessage]=useState(false);
  const handleOpenConfirmMessage=()=>setOpenConfirmMessage(true);
  const handleCloseConfirmMessage=()=>setOpenConfirmMessage(false);

  const [openRecommendationModal,setOpenRecommendationModal]=useState(false);
  const handleOpenRecommendationModal=()=>setOpenRecommendationModal(true);
  const handleCloseRecommendationModal=()=>setOpenRecommendationModal(false);

  // Define a separate function to handle item click navigation
const handleItemClick = (item) => {
  if (auth.user.userType === "jobSeeker") {
    if (item.title === "Profile") {
      navigate(`/profile/${auth.user.id}`);
    } else {
      navigate(item.path);
    }
    // else if (item.title ==="Recommend me"){
    //   handleOpenRecommendationModal();
    // }
  } else  if(auth.user.userType==="Admin"){
    if (item.title === "companyProfile") {
      navigate(`/companyProfile/${auth.user.id}`);
    } else {
      navigate(item.path);
    }
  }else if(auth.user.userType==="Employer")
    {
      if (item.title === "employerProfile") {
        navigate(`/employerProfile/${auth.user.id}`);
      } else {
        navigate(item.path);  
      }
    }
};

  return (
    <div className="h-screen sticky overflowY top-0">
      <div>
        <div className="py-2">
          <div className="flex">
            <img src={logo} className="App-logo" alt="logo" />
            <p className="text-xl" style={{ fontWeight: 'bold',marginTop:'22px'}}>JOBSHOP</p>
          </div>
          <hr className="relative"></hr>
        </div>
        <div className="space-y-2">
          {navigation.map((item, index) => (
            <div
              key={index} // Use a unique key (e.g., index) or a specific identifier from the item object
              className="cursor-pointer flex space-x-1 space-y-0 items-center"
              onClick={() => handleItemClick(item)}
            >
              {item.title === "Recommend me" && auth.user.userType === "jobSeeker" ? (
                <>
                  {item.icon}
                  <p className="text-xl">{item.title}</p>
                </>
              ):item.title !== "Recommend me" && item.title !== "employerProfile" && item.title !== "companyProfile" && auth.user.userType === "jobSeeker" ?(
                <>
                {item.icon}
                <p className="text-xl">{item.title}</p>
               </>
              ): item.title !== "Recommend me" && auth.user.userType === "Admin" ?(
                <>
                  {item.title !=="Profile" && item.title !=="employerProfile"  ? (
                    <>
                      {item.icon}
                     <p className="text-xl">{item.title}</p>
                    </>
                  ):(
                   <></>
                  )}
                </>
              ):(
                <>
                   {item.title !=="Profile" && item.title !=="Recommend me" && auth.user.userType === "Employer" ? (
                    <>
                      {item.icon}
                     <p className="text-xl">{item.title}</p>
                    </>
                  ):(
                   <></>
                  )}
                </>
              )}
            </div>
          ))}
        </div>

       {auth.user?.userType==="Employer" &&(   
              <div className="py-10">
              <Button
                onClick={handleOpenPostModal}
                variant="contained"  
                sx={{
                  width: "100%",
                  borderRadius: "29px",
                  py: "15px",
                  bgcolor: "#1e88e5",
                }}
              >
                JOB POST
          </Button>
          </div>
       )}

       {auth.user?.userType==="Admin" &&(
         <>
          <div className="py-2">
                <Button
                  onClick={handleOpenCreateFieldModal}
                  variant="contained"  
                  sx={{
                    width: "100%",
                    borderRadius: "29px",
                    py: "15px",
                    bgcolor: "#1e88e5",
                  }}
                >
                  CREATE FIELD
            </Button>
            </div>
            <div className="py-1">
                <Button
                  onClick={handleOpenGiveEmployerFields}
                  variant="contained"  
                  sx={{
                    width: "100%",
                    borderRadius: "29px",
                    py: "15px",
                    bgcolor: "#1e88e5",
                  }}
                >
                Give Employers FIELD
            </Button>
            </div>
            <div className="py-1">
                <Button
                  onClick={handleOpenCreateEmployerModal}
                  variant="contained"  
                  sx={{
                    width: "100%",
                    borderRadius: "29px",
                    py: "15px",
                    bgcolor: "#1e88e5",
                  }}
                >
                  CREATE EMPLOYER
            </Button>
            </div>
         </>
       )}


{auth.user?.userType==="jobSeeker" &&(   
          <>
          <div className="py-1">
              <Button
                onClick={handleOpenAddSkillsModal}
                variant="contained"  
                sx={{
                  width: "100%",
                  borderRadius: "29px",
                  py: "15px",
                  bgcolor: "#1e88e5",
                }}
              >
                Add Skills
          </Button>
          </div>
          <div className="py-1">
              <Button
                onClick={handleOpenShowApplicationsModal}
                variant="contained"  
                sx={{
                  width: "100%",
                  borderRadius: "29px",
                  py: "15px",
                  bgcolor: "#1e88e5",
                }}
              >
                Show Applications
          </Button>
          </div>
          </>
       )}

        <div className="mt-3 flex items-center py-1 space-x-5 ">
          <Avatar 
            onClick={() => navigate(navigationValue)}
            alt="username" 
            src={auth.user.picture}  
          />
         
          <div className="py-1 items-center" >
            <p className="mb-0">{auth.user?.userName}</p>
            <div>
              {/* <span className="mt-1 opacity-70">@{auth.user?.email.split(" ").join("_").toLowerCase()}</span> */}
              </div>
          </div>
      <div>
              
      {/* <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem onClick={handleLogout}>Logout</MenuItem>
      </Menu> */}

                </div>        
          </div>

          <div>
          <Button
            id="basic-button"
            onClick={handleOpenConfirmMessage}
          >
            LOGOUT

          </Button>
          
          </div>
      </div>

      <section>
            <PostModal openPostModal={openPostModal} handleClose={handleClosePostModal} operationType={"ADD"}/>
      </section>

       <section>
            <CreateEmployerModal openCreateEmployerModal={openCreateEmployerModal} handleCloseCreateEmployerModal={handleCloseCreateEmployerModal}/>
      </section> 

      <section>
            <CreateFieldModal  openCreateFieldModal={openCreateFieldModal} handleCloseCreateFieldModal={handleCloseCreateFieldModal}/>
      </section>

      <section>
        <AddSkillsModal openAddSkillsModal={openAddSkillsModal} handleCloseAddSkillsModal={handleCloseAddSkillsModal}/>
      </section>

<section>
  <ShowApplicationsModal openShowApplicationsModal={openShowApplicationsModal} handleCloseShowApplicationsModal={handleCloseShowApplicationsModal}/>
</section>
<section>
  <RecommendationModal 
  openRecommendationModal={openRecommendationModal} 
  handleCloseRecommendationModal={handleCloseRecommendationModal}/>
</section>
<section>
  <ConfirmMessage openConfirmMessage={openConfirmMessage} handleCloseConfirmMessage={handleCloseConfirmMessage} response={"Do you want to signout ? "} Title={"LOGOUT"} operationType={"Logout"}/>
</section>
 <section>
  <GiveEmployerFields openGiveEmployerFields = {openGiveEmployerFields} handleCloseGiveEmployerFields={handleCloseGiveEmployerFields} />
</section> 
    </div>
  );
};

export default Navigation;
