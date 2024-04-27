import  React, { useState } from "react";
import { navigation } from "./NavigationMenu";
import logo from "../common/images/logo.png";
import userLogo from "../common/images/default.jpg";
import { useNavigate } from "react-router-dom";
import { Button, Avatar} from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import PostModal from "../modals/companyModals/PostModal";
import { useDispatch, useSelector } from "react-redux";
import { Logout } from "../../store/Auth/Action";
import CreateFieldModal from "../modals/companyModals/CreateFieldModal";
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

  const dispatch=useDispatch()
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  const navigate = useNavigate();
  const handleLogout=()=>
  {
    console.log("logout");
    handleClose()
    dispatch(Logout())
  }
  return (
    <div className="h-screen sticky top-0">
      <div>
        <div className="py-2">
          <div className="flex">
            <img src={logo} className="App-logo" alt="logo" />
            <p className="text-xl" style={{ fontWeight: 'bold',marginTop:'22px'}}>JOBSHOP</p>
          </div>
          <hr className="relative"></hr>
        </div>
        <div className="space-y-6">
          {navigation.map((item, index) => (
            <div
              key={index} // Use a unique key (e.g., index) or a specific identifier from the item object
              className="cursor-pointer flex space-x-1 space-y-0 items-center"
              onClick={() =>
                item.title === "Profile"
                  ? navigate(`/profile/${5}`)
                  : navigate(item.path)
              }
            >
              {item.title !== "Recommend me" && auth.user !== "jobSeeker" ? (
                <>
                  {item.icon}
                  <p className="text-xl">{item.title}</p>
                </>
              ) : item.title === "Recommend me" && auth.user === "jobSeeker" && (
                <>
                  {item.icon}
                  <p className="text-xl">{item.title}</p>
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
                POST
          </Button>
          </div>
       )}

       {auth.user?.userType==="Admin" &&(
         <>
          <div className="py-5">
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

        <div className="mt-4 flex items-center py-2 space-x-5">
          <Avatar alt="username" src={userLogo} />
         
          <div className="py-2 items-center" >
            <p className="mb-0">{auth.user?.userName}</p>
            <div>
              <span className="mt-1 opacity-70">@{auth.user?.email.split(" ").join("_").toLowerCase()}</span>
              </div>
          </div>
          

      <Button
        id="basic-button"
        aria-controls={open ? 'basic-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
       
        <MoreHorizIcon />

      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem onClick={handleLogout}>Logout</MenuItem>
      </Menu>
        </div>
      </div>

      <section>
            <PostModal openPostModal={openPostModal} handleClose={handleClosePostModal}/>
      </section>

      {/* <section>
            <CreateEmployerModal openCreateEmployerModal={openCreateEmployerModal} handleClose={handleCloseCreateEmployerModal}/>
      </section> */}

      <section>
            <CreateFieldModal  openCreateFieldModal={openCreateFieldModal} handleCloseCreateFieldModal={handleCloseCreateFieldModal}/>
      </section>
    </div>
  );
};

export default Navigation;
