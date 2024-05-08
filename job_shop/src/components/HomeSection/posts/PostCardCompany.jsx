import React, { useState ,useRef} from "react";
import RepeatIcon from '@mui/icons-material/Repeat'
import { Avatar, Grid, Typography } from '@mui/material'
import defaultImg from '../../common/images/default.jpg'
import myImg from '../../common/images/myPic.jpg'
import { useNavigate } from 'react-router-dom'
import Button from '@mui/material/Button'
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Groups2Icon from '@mui/icons-material/Groups2';
import axios from "axios";
import { CheckCircle } from '@mui/icons-material';
import { ErrorOutline } from '@mui/icons-material';
import { LocationOn } from '@mui/icons-material';
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import GroupIcon from '@mui/icons-material/Group';
import ApplyModal from '../../modals/JobSeekerModal/ApplicationsModals/ApplyModal'
import AddSkillsModal from '../../modals/JobSeekerModal/skillsModals/AddSkillsModal'
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { deletePost, fetchCompanyPosts } from "../../../store/Post/Action";
import ShowApplicationsModal from "../../modals/companyModals/postModals/ShowApplicationsModal";
import ContactPageIcon from '@mui/icons-material/ContactPage';
import ShowPostImageModal from "./ShowPostImageModal";
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
    postImage
}) => {
    const [anchorEl, setAnchorEl] = useState(null);
    const openMenu = Boolean(anchorEl);
    const [openShowApplicationsModal, setOpenShowApplicationsModal] = useState(false);
    const handleOpenShowApplicationsModal = () => setOpenShowApplicationsModal(true);
    const handleCloseShowApplicationsModal = () => setOpenShowApplicationsModal(false);
    const navigate = useNavigate();
    const dispatch=useDispatch();
    const auth=useSelector(state=>state.auth);
    const [hidden,setHidden]=useState("hidden");
    const [openShowPostImageModal,setOpenShowPostImageModal]=React.useState(false);
    const handleOpenShowPostImageModal=()=>setOpenShowPostImageModal(true);
    const handleCloseShowPostImageModal=()=>setOpenShowPostImageModal(false);

    const handleClose = () => {
        setAnchorEl(null);
    };
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleHidden=(event)=>
    {
      if(hidden === "hidden")
        {
          setHidden("");
        }else {
          setHidden("hidden");
        }
    };
    const handleDeletePost=(postId)=>
    {
      dispatch(deletePost(postId));
    };
    const handlEditPost=(postId)=>
    {
      // dispatch(deletePost(postId));
    };
    const getDynamicImageUrl = (picture) => {
        if (picture && picture.length > 0) {
          // Determine image type based on magic number (file signature)
          const signature = picture.slice(0, 4); // Read the first 4 bytes
    
          // Check for known image file signatures (magic numbers)
          if (signature[0] === 0x89 && signature[1] === 0x50 && signature[2] === 0x4E && signature[3] === 0x47) {
            // PNG file
            return `data:image/png;base64,${btoa(String.fromCharCode(...picture))}`;
          } else if (signature[0] === 0xFF && signature[1] === 0xD8) {
            // JPEG file
            return `data:image/jpeg;base64,${btoa(String.fromCharCode(...picture))}`;
          } else {
            // Default to treating as a generic binary file (may not render as an image)
            return `data:application/octet-stream;base64,${btoa(String.fromCharCode(...picture))}`;
          }
        }
        return ''; // Return empty string if no picture or invalid data
      };
      const postRef = useRef(null);
    return (
        <div ref={postRef} className=''>
            <div className='flex space-x-5'>
            <Avatar
                onClick={() => navigate(`/profile/${profileId}`)}
                className='cursor-pointer'
                alt="userName"
                src={employerpicture}
                />


                <div className='w-full'>
                    <div className='flex justify-between items-center'>
                        <div className='flex cursor-pointer items-center space-x-2'>
                            <span className='font-semibold'>{employerUserName}</span>
                            <span className='text-gray-600'>{`@${companyName?.split(" ").join("_").toLowerCase()}`}</span>
                        </div>
                       {auth.user.userType==="Admin" ||auth.user.id===employerId &&(
                          <div>
                           
                          <Button
                              id="basic-button"
                              aria-controls={openMenu ? 'basic-menu' : undefined}
                              aria-haspopup="true"
                              aria-expanded={openMenu ? 'true' : undefined}
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
                                  'aria-labelledby': 'basic-button',
                                  }}
                          >
      
                                    
                                      {[
                                        <MenuItem key="delete" onClick={()=>handleDeletePost(id)}>Delete</MenuItem>,
                                        <MenuItem key="edit" onClick={()=>handlEditPost(id)}>Edit</MenuItem>,
                                        <MenuItem key="show" onClick={()=>handleOpenShowApplicationsModal()}>Show Applications</MenuItem>
                                        
                                      ]}
                                  
                                
      
                          </Menu>
      
                              </div>
      
                       )}
                
                    </div>
                   <div className='items-center mt-3'>
                     {applicationCount !==0 && (
                          <div className="font-semibold"> <GroupIcon />  Application Count : {applicationCount}</div>
                        )}
                          
                  </div>
<div className="mt-4">
  <div className="cursor-pointer" onClick={handleHidden}>
    <p className="mb-2 p-0 mt-2 font-bold"> <strong>{`>`} Title: </strong> {Title.includes("{") ? Title.substring(0,Title.indexOf("{")) : Title}</p>
    <p className="mb-2 p-0 text-gray-600"> <strong>{`>`} Description: </strong>  {description}</p>
    {hidden==="hidden" &&(
      <>
        <Button
        id="basic-button"
        onClick={handleHidden}
        >

        <MoreHorizIcon />

      </Button>
      </>
    )}
    <div className={`flex ${hidden}`}>
      <p className="mb-2 p-0 text-gray-600">
        <strong>{`>`} Experience: </strong>
      </p>
      <p>{Experience} Years</p>
    </div>
     <div className={`flex flex-wrap items-center ${hidden}`}>
      <p className="mb-2 p-0 text-gray-600 mr-3">
      <FmdGoodIcon className="text-[#1d9bf0]"/>
        <strong>Location:</strong> {location}
      </p>
      <p className="mb-2 p-0 text-gray-600 mr-3">
        <strong>{`>`} Employment Type:</strong> {employmentType}
      </p>
      {/* Render other details here */}
    </div>
    {/* Render more details here */}
    {/* <img className="w-[28rem] border border-gray-400 p-5 rounded-md" src={myImg} alt="" /> */}
  </div>

  <div className={`flex flex-wrap items-center ${hidden}`}>
    <p className="mb-2 p-0 text-gray-600"><strong>{`>`} Field :</strong> {fieldName}</p>
    </div>
  <div className={`flex flex-wrap items-center ${hidden}`}>
   
    {skills && skills.length > 0 && (
      <div className="flex items-center text-gray-600 mr-3">
          <Typography variant="subtitle1" fontWeight="bold" mb={1}>
          {`>`} Skills:
          </Typography>
          <ul>
          {skills.map((skill, index) => (
            <li key={index}><p className="text-xl">- {skill}</p></li>
        ))}
          </ul>
       
      </div>
    )}
   
  </div>

<hr></hr>

  <div className={`flex flex-wrap items-center ${hidden}`}>
 {/* Render qualifications if available */}
 {qualifications && qualifications.length > 0 && (
      <div className="flex items-center text-gray-600 mr-3">
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
        {`>`} Qualifications : 
        </Typography>
        <ul>
        {qualifications.map((qualification, index) => (
         
          <li key={index}><p className="text-xl">- {qualification}</p></li>
        ))}
        </ul>
       
      </div>
    )}
    {/* Render more additional details here */}
  </div>
  <Grid item>
    {postImage && (
    <div
    className="cursor-pointer"
        style={{
        width: 500,
        height: 450,
        overflow: 'hidden',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        }}
    >
        <img
        onClick={handleOpenShowPostImageModal}
        src={postImage}
        className="w-[35rem] border border-gray-400 p-5 rounded-md"
        alt="Image"
        style={{ maxWidth: '100%', maxHeight: '100%', objectFit: 'cover' }}
        />
    </div>
    )}
</Grid>
 
  <div>  <hr style={{ 
  border: 'none',
  height: '2px', /* Increase height for better visibility */
  backgroundColor: '#012', // Change to your desired color
  margin: '20px 0', // Adjust margins as needed
  borderRadius: '2px', // Add border radius for a softer appearance
  boxShadow: '0 2px 2px rgba(0, 0, 0, 0.1)' // Add a subtle shadow for depth
}} />
</div>
</div>

                </div>
          
          </div>
          <section>
                <ShowApplicationsModal openShowApplicationsModal={openShowApplicationsModal} handleCloseShowApplicationsModal={handleCloseShowApplicationsModal} postId={id}/>
            </section>
            <section>
              <ShowPostImageModal openShowPostImageModal={openShowPostImageModal} handleCloseShowPostImageModal={handleCloseShowPostImageModal} postImage={postImage} />
            </section>
        </div>
    )
}

export default PostCardCompany;