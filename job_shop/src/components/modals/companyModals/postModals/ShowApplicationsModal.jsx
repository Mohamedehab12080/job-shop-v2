import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import {  Avatar, Grid, MenuItem, TextField, Typography } from '@mui/material';
import Menu from "@mui/material/Menu";
import { useNavigate } from 'react-router-dom';
import { acceptApplication, deleteApplication, fetchBestPostApplications, fetchPostApplications, rejectApplication } from '../../../../store/Post/Action';
import { deleteJobSeekerApplication, findJobSeekerApplications } from '../../../../store/JobSeeker/Action';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import EditIcon from '@mui/icons-material/Edit';
export default function ShowApplicationsModal(
    {openShowApplicationsModal,handleCloseShowApplicationsModal,postId}
) {
  
  var [filteredApplicants,setFilteredApplicants] = React.useState([]);
  const [filterInputApplicants, setFilterInputApplicants] = React.useState("");
  const auth=useSelector(state=>state.auth);
  const dispatch=useDispatch();
  const dispatch2=useDispatch();
  const dispatch3=useDispatch();
  const dispatch4=useDispatch();

  const comp=useSelector(state=>state.comp);
  const post=useSelector(state=>state.post);
  const jobSeeker=useSelector(state=>state.jobSeeker);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openMenu = Boolean(anchorEl);
  const [fetchedApplications,setFetchedApplications]=React.useState([]);
  const navigate=useNavigate();

  const handleFilterApplications=(input)=>
  {
      if(auth.user.userType!=="jobSeeker")
      {
        const filtered=fetchedApplications.filter((application)=>
          {
            return application.jobSeekerUserName.toLowerCase().includes(input.toLowerCase());
          });
            setFilteredApplicants(filtered);
            setFilterInputApplicants(input);
      }else {
        const filtered=fetchedApplications.filter((application)=>
          {
            if(application.companyName.toLowerCase().includes(input.toLowerCase()) || application.postTitle.toLowerCase().includes(input.toLowerCase()))
              {
                return application;
              }
          });
            setFilteredApplicants(filtered);
            setFilterInputApplicants(input);
      }
  };

  const handleEditApplications=(fieldId)=>
  {
    /// Handle Edit open the createFieldModal and make it for edit 
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  React.useEffect(() => {
    if (openShowApplicationsModal) {
      if(auth.user.userType!=="jobSeeker")
        {
          dispatch(fetchBestPostApplications(postId));
        }else 
        {
          dispatch(findJobSeekerApplications(auth.user.id));
        }
    }
  }, [openShowApplicationsModal, dispatch,postId,auth.user.id]);
  
  // Add comp as a dependency to useEffect to ensure that the log executes 
  // with the updated value. 
  React.useEffect(() => {
    if (openShowApplicationsModal) {
     if(auth.user.userType !=="jobSeeker")// Set your fields with the updated value here
      {
        setFetchedApplications(post.applications);
      } 
    }
  }, [post, openShowApplicationsModal]);

  React.useEffect(() => {
    if (openShowApplicationsModal) {
     if(auth.user.userType === "jobSeeker")// Set your fields with the updated value here
      {
        setFetchedApplications(jobSeeker.applications);
      } 
    }
  }, [jobSeeker, openShowApplicationsModal]);

  const handleDeleteApplication=(applicationId)=>
  {
        dispatch2(deleteJobSeekerApplication(applicationId));
        handleClose();
  };
  const handleRejectApplication=(applicationId)=>
    {
        dispatch4(rejectApplication(applicationId));
        handleClose();
    };
  const handleAcceptApplication=(applicationId)=>
  {
    // console.log("Field For Delete : ",fieldId)
   if(auth.user.userType!=="jobSeeker")
    {
      dispatch3(acceptApplication(applicationId));
      handleClose();
    }
    setFetchedApplications(post.applications);
  };

  // const getDynamicImageUrl = (picture) => {
  //   if (picture && picture.length > 0) {
  //     // Determine image type based on magic number (file signature)
  //     const signature = picture.slice(0, 4); // Read the first 4 bytes

  //     // Check for known image file signatures (magic numbers)
  //     if (signature[0] === 0x89 && signature[1] === 0x50 && signature[2] === 0x4E && signature[3] === 0x47) {
  //       // PNG file
  //       return `data:image/png;base64,${btoa(String.fromCharCode(...picture))}`;
  //     } else if (signature[0] === 0xFF && signature[1] === 0xD8) {
  //       // JPEG file
  //       return `data:image/jpeg;base64,${btoa(String.fromCharCode(...picture))}`;
  //     } else {
  //       // Default to treating as a generic binary file (may not render as an image)
  //       return `data:application/octet-stream;base64,${btoa(String.fromCharCode(...picture))}`;
  //     }
  //   }
  //   return ''; // Return empty string if no picture or invalid data
  // };



  return (
    <div>
      <Modal
        open={openShowApplicationsModal}
        onClose={handleCloseShowApplicationsModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box  sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 800,
          bgcolor: "background.paper",
          border: "none",
          boxShadow: 24,
          p: 4,
          outline: "none",
          borderRadius: 4,
          maxHeight: "80vh",
          overflowY: "auto",
        }}
        >
  <Grid item key={1} xs={12}>
      <TextField
        fullWidth
        id="filterApplications"
        name="filterApplications"
        label="Filter Applications"
        value={filterInputApplicants}
        onChange={(e) => handleFilterApplications(e.target.value)}
      />
    </Grid>
    
    <section>
           {(filterInputApplicants === "" ?   fetchedApplications : filteredApplicants).map((app, index) => (
           <>
            <div><hr></hr></div>
           <div key={app.id} className='flex'>
            <Avatar
                  onClick={() => navigate(`/profile/${app.id}`)}
                  className='cursor-pointer'
                  alt="userName"
                  src={app.jobSeekerPicture}
            />
            <div className='w-full'>
            <div className='flex justify-between items-center'>
                <ul>
                        <li className='flex cursor-pointer items-center space-x-2'>
                                <span className='font-semibold'>{app.jobSeekerUserName}</span>
                                <span className='text-gray-600'>@{app.jobSeekerEmail}</span>
                        </li>
                        <li className=''>
                            <span className='text-gray-600'><AccessTimeIcon /> {app.createdDate}</span>
                        </li>
                </ul>
                    {auth.user.userType !=="jobSeeker" ?(
                      <div>
                       {app.statuseCode !== null &&(
                        <>
                           {app.statuseCode.includes("Matched") ? (
                              <p className="font-semibold" style={{ color: 'green' }}>{app.statuseCode}</p>
                            ) : app.statuseCode.includes("Not match") ? (
                              <p className="font-semibold" style={{ color: 'red' }}>{app.statuseCode}</p>
                            ) : app.statuseCode.includes ("Accepted") ? (
                              <p className="font-semibold" style={{ color: 'green' }}>{app.statuseCode}</p>
                            ):null}
                        </>
                       )}
                      </div>
                    ):(
                      <div>
                          {app.statuseCode !== null &&(
                            <>
                              {app.statuseCode.includes ("Accepted") ? (
                                <p className="font-semibold" style={{ color: 'green' }}>{app.statuseCode}</p>
                              ) : app.statuseCode.includes("Rejected") ? (
                                <p className="font-semibold" style={{ color: 'red' }}>{app.statuseCode}</p>
                              ) : null}
                            </>
                          )}
                      </div>
                    )}
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

                           {auth.user.userType !=="jobSeeker" ?(
                             
                              [
                                <MenuItem key="accept" 
                              onClick={()=>handleAcceptApplication(app.id)}>
                                <CheckIcon style={{ color: 'green' }}/> Accept</MenuItem>,
                              <MenuItem key="reject" onClick={()=>handleRejectApplication(app.id)}><ClearIcon style={{ color: 'red' }}/> Reject</MenuItem>
                                
                              ]
                             
                             
                            ):(
                            
                              [
                              <MenuItem  key="delete" onClick={()=>handleDeleteApplication(app.id)}><ClearIcon style={{ color: 'red' }}/> Delete</MenuItem>,
                              <MenuItem  key="edit" onClick={()=>handleEditApplications(app.id)}><EditIcon style={{ color: 'gray' }}/> Edit</MenuItem>
                              
                                ]

                              
                           )}
                            
                    </Menu>
                    </div>
                   
                </div>
                
<div className='flex space-x-5'>
  {/* Left Column */}
  <div className='flex flex-col'>
    <div className='ml-10'>
      <div className='flex'>
      <Typography variant="subtitle1" fontWeight="bold" mb={1}>
      - Application Experience:
      </Typography>
        <p className='text-xl'>{app.experience}</p>
      </div>
     {app.companyName !== "" &&(
      
        <div className='ml-3 flex items-center'>
        <Typography variant="subtitle2" fontWeight="bold" mb={1}>
            {`>`} Company Name :
        </Typography>
        <p className='text-xl ml-2'>{app.companyName}</p>
        </div>
     )}
      <div className='flex flex-wrap py-4 mt-1 items-center'>
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
         - Application Skills:
        </Typography>
        {app.skills && app.skills.length > 0 ? (
          <ul>
            {app.skills.map((appSkill, index) => (
              <li key={index} className='text-xl'>{`->`} {appSkill}</li>
            ))}
          </ul>
        ) : (
          <>No Skills</>
        )}
      </div>
      
      <hr className='my-4'></hr>
      
      <div className='flex flex-wrap py-4 mt-1 items-center'>
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
          - Application Qualifications:
        </Typography>
        {app.qualifications && app.qualifications.length > 0 ? (
          <ul>
            {app.qualifications.map((appQual, index) => (
              <li key={index} className='text-gray-600'>{`->`}{appQual}</li>
            ))}
          </ul>
        ) : (
          <>No Qualifications</>
        )}
      </div>
    </div>
  </div>

  {/* Vertical Line */}
  <div className="border-4 border-gray-700 h-auto"></div>

  {/* Right Column */}
  <div className='flex flex-col'>
    <div className=''>
      {post.postTitle !=="" &&(
        <div>
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
            {`>`} Post Title: {app.postTitle}
        </Typography>
        </div>
      )}
      <div className='flex'>
        {app.postExperienc === app.experience ? 
        (
          <Typography variant="subtitle1" fontWeight="bold" mb={1}>
              <CheckIcon style={{ color: 'green' }}/> Post Experience: {app.postExperienc}
          </Typography>
        ):(
          
          <Typography variant="subtitle1" fontWeight="bold" mb={1}>
          {`>`} Post Experience: {app.postExperienc}
          </Typography>
        )}
      </div>
      
      <div className='flex flex-wrap py-4 mt-1 items-center'>
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
          - Post Skills:
        </Typography>
        {app.postSkills && app.postSkills.length > 0 ? (
          <ul>
            {app.postSkills.map((appSkill, index) => (
              <li key={index} className='text-xl'>{app.skills.includes(appSkill) ? (<CheckIcon style={{ color: 'green' }}/>):(<>{`->`}</>)} {appSkill}</li>
            ))}
          </ul>
        ) : (
          <>No Skills</>
        )}
      </div>
      
      <hr className='my-2'></hr>
      
      <div className='flex py-4 items-center'>
        <Typography variant="subtitle1" fontWeight="bold" mb={1}>
         - Post Qualifications:
        </Typography>
        {app.postQualifications && app.postQualifications.length > 0 ? (
          <ul>
            {app.postQualifications.map((appQual, index) => (
              <li key={index} className='text-gray-600 text-xl'>
                {app.qualifications.length > 0 && app.qualifications.includes(appQual) ? (
                <CheckIcon style={{ color: 'green' }}/>
              ):(
              <>{`->`}</>
            )} {appQual}</li>
            ))}
          </ul>
        ) : (
          <>No Qualifications</>
        )}
      </div>
    </div>
  </div>
</div>

            </div>
            
           </div>
           </>
          ))}
           </section>
        </Box>
      </Modal>
    </div>
  );
}
