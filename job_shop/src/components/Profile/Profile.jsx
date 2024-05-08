import React, { useState } from 'react'
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
import { useDispatch } from 'react-redux';
import { getInfo } from '../../store/JobSeeker/Action';

const Profile = () => { 
    const [tabValue,setTabValue]=useState("1")
    const [openProfileModal,setOpenProfileModal]=useState(false);
    const handleOpenProfileModel=()=>setOpenProfileModal(true);
    const handleClose=()=>setOpenProfileModal(false);
    const navigate=useNavigate();
    const handleBack=()=>navigate(-1);
    const {id}=useParams();
    const dispatch=useDispatch();


    React.useEffect(()=>
    {
        dispatch(getInfo(id));
    },[dispatch,id])

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
            <h1 className='py-5 text-xl font-bold opacity-90 ml-5'>Mohamed Ehab</h1>
        </section>

        <section>
            <img className='w-[100%] h-[15rem] object-cover' src="https://cdn.pixabay.com/photo/2024/03/08/16/06/building-8621170_1280.jpg" alt="" />
        </section>

        <section className='pl-6'>
            
            <div className='flex justify-between items-start mt-5 h-[5rem]'>
                <Avatar className='transform -translate-y-24' alt='BOB' src=''
                sx={{width:"10rem",height:"10rem",border:"4px solid white"}}/> 
             {true ? (<Button
             onClick={handleOpenProfileModel}
             variant='contained' sx={{borderRadius:"20px"}}
            >Edit Profile</Button>
            ):(
            <Button
             onClick={handleFollowUser}
             variant='contained' sx={{borderRadius:"20px"}}
            >{true ? "Follow":"Unfollow"}</Button>)}
            </div>
            <div>
                    <div className='flex item-center'>
                        <h1 className='font-bold text-lg'> Mohamed Ehab</h1>
            <div className='flex items-center space-x-20'>
                <div className='ml-10 flex items-center space-x-1 font-semibold'>
                        <span>500</span>
                        <span className='text-gray-500'>Followers</span>
                    </div>
                    <div className='flex items-center space-x-1 font-semibold'>
                        <span>500</span>
                        <span className='text-gray-500'>Following</span>
                    </div>
                </div>
                    </div>
                    <p className='text-gray-500'>@BOB</p>
            </div>

            <div className='mt-2 space-y-2'>
                <p>Hello, i am Mohamed ,yor will find the full stack project here</p>
                <div className='flex space-x-5'>
                    <div className='flex items-center text-gray-500'>
                        <SkillsIcon />
                        <p className='ml-2 mt-3'>Skills</p>
                    </div>
                    <div className='flex items-center text-gray-500'>
                        <BussinessCetnerIcon />
                        <p className='ml-2 mt-3'>Education</p>
                    </div>

                    <div className='flex items-center text-gray-500'>
                        <LocationIcon />
                        <p className='ml-2 mt-3'>Egypt</p>
                    </div>

                    <div className='flex items-center text-gray-500'>
                        <CalendarIcon />
                        <p className='ml-2 mt-3'>Joined Jun 2024</p>
                    </div>
                </div>
                
            </div>
        </section>

        <section className='py-5'>
            <Box sx={{width:'100%',typography:'body1' }}>
                <TabContext value={tabValue}>
                    <Box sx={{borderBottom:1,borderColor:'divider'}}>
                        <TabList onChange={handleChange} aria-label="lab API tabs example">
                            <Tab label="Skills" value="1" />
                            <Tab label="Applications" value="2" />
                            <Tab label="Courses" value="3" />
                        </TabList> 
                    </Box>
                    <TabPanel value='1'>Skills</TabPanel>
                    <TabPanel value='2'>Applications</TabPanel>
                    <TabPanel value='3'>Courses</TabPanel>
                </TabContext>
            </Box>
        </section>

        <section>
            <ProfileModal open={openProfileModal} handleClose={handleClose}/>
        </section>
    </div>
  )
}

export default Profile