import  React, { useState } from "react";
import RepeatIcon from '@mui/icons-material/Repeat'
import { Avatar } from '@mui/material'
import defaultImg from '../common/images/default.jpg'
import myImg from '../common/images/myPic.jpg'
import { useNavigate } from 'react-router-dom'
import Button from '@mui/material/Button'
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Groups2Icon from '@mui/icons-material/Groups2';

const PostCard = () => {
    const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const [opneApplyModal,setOpenApplyModal]=useState(false);
  const handleOpenApplyModal=()=>setOpenApplyModal(true);
  const handleCloseApplyModal=()=>setOpenApplyModal(false);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleDeletePost=()=>
  {
    console.log("logout");
    handleClose()
  }
  
  const handleApplied= () =>{
    console.log("applied icon");
  }
    const navigate=useNavigate();
  return (
    <div className=''>
        {/* <div className='flex items-center font-semibold text-gray-700 py-2'>
            <RepeatIcon/>
            
        </div> */}
        <div className='flex space-x-5' onClick={()=>navigate(`/PostDetails/${6}`)}>
            <Avatar 
                onClick={()=>navigate(`/profile/${6}`)}
                className='cursor-pointer'
                alt="userName"
                src={defaultImg}
            />
            <div className='w-full'>
                <div className='flex justify-between items-center'>
                    <div className='flex cursor-pointer items-center space-x-2'>
                        <span className='font-semibold'>Mohamed Ehab</span>
                        <span className='text-gray-600'>@BOB .2m</span>
                    </div>
                    
                    <div>

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
                            <MenuItem onClick={handleDeletePost}>Delete</MenuItem>
                            <MenuItem onClick={handleDeletePost}>Edit</MenuItem>
                    </Menu>
                    </div>
                </div>
                <div className="mt-2 hover:bg-gray-200">
                    <div className="cursor-pointer">
                        <p className="mb-2 p-0">Job about Full-stack react and spring boot</p>
                        <img className="w-[28rem] border border-gray-400 p-5 rounded-md"src={myImg} alt="" />
                    </div>
                    <div className="py-2 flex flex-wrap items-center">
                            <div className="space-x-3 flex items-center text-gray-600">
                               <div className="space-x-3 flex items-center text-gray-600">
                               <Groups2Icon className="cursor-pointer" onClick={handleApplied}/>
                               <span>20</span>
                               </div>
                            </div>
                    </div>
                    <div><hr></hr></div>
                </div>
            </div>
        </div>
        <section>
            <applyModal openPostModal={opneApplyModal} handleClose={handleCloseApplyModal}/>
      </section>
    </div>
  )
}

export default PostCard