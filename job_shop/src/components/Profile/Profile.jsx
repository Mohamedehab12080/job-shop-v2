import React, { useEffect, useState } from 'react'
import { KeyboardBackspace } from '@mui/icons-material'
import { useNavigate, useParams } from 'react-router-dom';
import { Avatar, Box, Tab } from '@mui/material';
import { Button } from '@mui/material';
import BussinessCetnerIcon from '@mui/icons-material/BusinessCenter'
import LocationIcon from '@mui/icons-material/LocationOn'
import CalendarIcon from '@mui/icons-material/CalendarMonth'
import SkillsIcon from '@mui/icons-material/Attractions';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import ProfileModal from './ProfileModal';
import { useDispatch, useSelector } from 'react-redux';
import { getInfo } from '../../store/JobSeeker/Action';
import MessageModal from '../../responses/MessageModal'
import ShowSkillsmodal from './ShowSkillsModal';
import ContactsModal from '../../responses/CotactsModal';
import ConnectWithoutContactIcon from '@mui/icons-material/ConnectWithoutContact';  
import ShowPostImageModal from '../HomeSection/posts/ShowPostImageModal';
const Profile = () => { 
    const [tabValue,setTabValue]=useState("1")
    const [openProfileModal,setOpenProfileModal]=useState(false);
    const handleOpenProfileModel=()=>setOpenProfileModal(true);
    const handleClose=()=>setOpenProfileModal(false);
    const navigate=useNavigate();
    const handleBack=()=>navigate(-1);
    const {id}=useParams();
    const auth=useSelector(state=>state.auth);
    const dispatch=useDispatch();
    const jobSeeker=useSelector(state=>state.jobSeeker);
    const [skills,setSkills]=useState([]);
    const [qualifications,setQualifications]=useState([]);
    const [jobSeekerData,setJobSeekerData]=useState(null);
    const [contactList,setContactList]=useState([]);
    const [isRequestUser,setIsRequestUser]=useState(false);
    const [openEductationModal,setOpenEducationModal]=useState(false);
    const  handleOpenEducationModal=()=>setOpenEducationModal(true);
    const handleCloseEducationModal=()=>setOpenEducationModal(false);
    const [openShowSkillsModal,setOpenShowSkillsModal]=useState(false);
    const  handleOpenShowSkillsModal=()=>setOpenShowSkillsModal(true);
    const handleCloseShowSkillsModal=()=>setOpenShowSkillsModal(false);
    const [openContactsModal,setOpenContactsModal]=useState(false);
    const  handleOpenContactsModal=()=>setOpenContactsModal(true);
    const handleCloseContactsModal=()=>setOpenContactsModal(false);
       
    const [openShowPostImageModal,setOpenShowPostImageModal]=React.useState(false);

    const handleCloseShowPostImageModal=()=>setOpenShowPostImageModal(false);


    const [imageForShow,setImageForShow]=useState("");
    const [coverImage,setCoverImage]=useState("");
    const [profileImage,setProfileImage]=useState("");
    const handleOpenShowPostImageModal=(value)=>{
        setOpenShowPostImageModal(true);
        setImageForShow(value);
    };
  useEffect(() => {

        dispatch(getInfo(id));
    
  }, [auth.user.userType,dispatch, id]); // Dependency array ensures this effect runs only when id or dispatch changes

  // Log jobSeeker state changes
  useEffect( () => {
        setJobSeekerData(jobSeeker.jobSeekerData);
        console.log("Job Seeker Data From profile : ",jobSeeker.jobSeekerData);
  }, [auth.user.userType,jobSeeker.jobSeekerData]); // Dependency array ensures this effect runs only when jobSeeker changes

  useEffect(()=>
{

        setSkills(jobSeeker.skills);
        setQualifications(jobSeeker.qualifications);
        if(jobSeekerData !==null )
        {
            setProfileImage(jobSeekerData.picture);
            setContactList(jobSeekerData.contacts);
            setCoverImage(jobSeekerData.coverImage);
        }
        setIsRequestUser(jobSeeker.isRequestUser);
    
},[jobSeeker.skills,
    jobSeeker.qualifications,
    jobSeekerData,
    jobSeeker.isRequestUser,
    ])

    const handleFollowUser=()=>
    {
        console.log("Follow");
    };

    const handleChange = (event,newValue)=>
    {
        setTabValue(newValue)

        if(newValue===4)
        {
            console.log("")
        }else if(newValue==1) 
        {
            console.log("Applications")
        }
    }
    return (

    <div>
        
        <section className={`z-50 flex items-center sticky top-0 bg-opacity-95`}>
            <KeyboardBackspace className='cursor-pointer' onClick={handleBack} />
            <h1 className='py-5 text-xl font-bold opacity-90 ml-5'>{jobSeekerData !== null && jobSeekerData.userName}</h1>
        </section>

        <section>
            <img onClick={()=>handleOpenShowPostImageModal(coverImage)} className='w-[100%] h-[15rem] object-cover' src={coverImage} alt="Cover Image" />
        </section>

        <section className='pl-6'>
            
            <div className='flex justify-between items-start mt-5 h-[5rem]'>
                <Avatar onClick={()=>handleOpenShowPostImageModal(profileImage)} className='transform -translate-y-24' alt='BOB' src={profileImage}
                sx={{width:"10rem",height:"10rem",border:"4px solid white"}}/> 
             {jobSeekerData !==null && jobSeekerData.req_user ? (<Button
             onClick={handleOpenProfileModel}
             variant='contained' sx={{borderRadius:"20px"}}
            >Edit Profile</Button>
            ):(
            <Button
             onClick={handleFollowUser}
             variant='contained' sx={{borderRadius:"20px"}}
            >{jobSeekerData !==null && jobSeekerData.followed ? "Unfollow":"follow"}</Button>)}
            </div>
            <div>
                    <div className='flex item-center'>
                        <h1 className='font-bold text-lg'>{jobSeekerData !== null && jobSeekerData.userName}</h1>
            <div className='flex items-center space-x-20'>
                <div className='ml-10 flex items-center space-x-1 font-semibold'>
                        <span>{jobSeekerData!==null && jobSeekerData.followers.length > 0 ? jobSeekerData.followers.length : 0}</span>
                        <span className='text-gray-500'>Followers</span>
                    </div>
                    <div className='flex items-center space-x-1 font-semibold'>
                    <span>{jobSeekerData!==null && jobSeekerData.followings.length > 0 ? jobSeekerData.followings.length : 0}</span>
                        <span className='text-gray-500'>Following</span>
                    </div>
                </div>
                    </div>
                    <p className='text-gray-500'>{jobSeekerData !== null && jobSeekerData.email}</p>
            </div>

            <div className='mt-2 space-y-2'>
                    <>
                        <p>{jobSeekerData!==null && jobSeekerData.description}</p>
                        <div className='flex space-x-5 cursor-pointer'>
                            <div className='flex items-center text-gray-500' onClick={handleOpenShowSkillsModal}>
                                <SkillsIcon />
                                <p className='ml-2 mt-3'>Skills</p>
                            </div>
                            <div className='flex items-center text-gray-500 cursor-pointer' onClick={handleOpenEducationModal}>
                            <BussinessCetnerIcon />
                                <p className='ml-2 mt-3'>Education</p>
                            </div>

                            <div className='flex items-center text-gray-500 cursor-pointer' onClick={handleOpenContactsModal}>
                                 <ConnectWithoutContactIcon/>
                                <p className='ml-2 mt-3' sx={{color:'blue'}}>Contacts Info</p>
                            </div>

                            <div className='flex items-center text-gray-500'>
                                <LocationIcon />
                                <p className='ml-2 mt-3'>{jobSeekerData !== null && jobSeekerData.address}</p>
                            </div>

                            <div className='flex items-center text-gray-500'>
                                <CalendarIcon />
                                <p className='ml-2 mt-3'>Joined Jun 2024</p>
                            </div>
                        </div>
                        
                    </>
               
            </div>
        </section>
        <section>
            <ShowSkillsmodal openShowSkillsModal={openShowSkillsModal} handleCloseShowSkillsModal={handleCloseShowSkillsModal} skills={skills} qualifications={qualifications}/>
        </section>
        <section className='py-5'>
            <Box sx={{width:'100%',typography:'body1' }}>
            <TabContext value={tabValue}>
                 <Box sx={{borderBottom:1,borderColor:'divider'}}>
                     <TabList onChange={handleChange} aria-label="lab API tabs example">
                         <Tab label="Skills" value="1" />
                         <Tab label="Posts" value="2" />
                         <Tab label="Courses" value="3" />
                     </TabList> 
                 </Box>
                 <TabPanel value='1'>Skills</TabPanel>
                 <TabPanel value='2'>Posts</TabPanel>
                 <TabPanel value='3'>Courses</TabPanel>
             </TabContext>
            </Box>
        </section>

        <section>
            <ProfileModal open={openProfileModal} handleClose={handleClose} data={jobSeekerData} Type={"jobSeeker"}/>
        </section>

        <section> 
            <MessageModal openMessageModal={openEductationModal} handleCloseMessageModal={handleCloseEducationModal} response={jobSeekerData !==null && jobSeekerData.education} Title={"Education"}/>
        </section>
      <section>
        <ContactsModal openContactsModal={openContactsModal} handleCloseContactsModal={handleCloseContactsModal} contactsList={contactList} isRequestUser={isRequestUser}/>
      </section>

      <section>
              <ShowPostImageModal openShowPostImageModal={openShowPostImageModal} handleCloseShowPostImageModal={handleCloseShowPostImageModal} postImage={imageForShow} />
     </section>
    </div>
  )
}

export default Profile