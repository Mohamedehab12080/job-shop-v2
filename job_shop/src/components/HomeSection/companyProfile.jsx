import  React, { useState } from "react";
import RepeatIcon from '@mui/icons-material/Repeat'
import { Avatar } from '@mui/material'
import defaultImg from '../common/images/default.jpg'
import { useNavigate } from 'react-router-dom'
import Button from '@mui/material/Button'
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
const PostCard = () => {
    const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

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
  
    const navigate=useNavigate();
  return (
    <div className=''>
        {/* <div className='flex items-center font-semibold text-gray-700 py-2'>
            <RepeatIcon/>
            
        </div> */}
        <div className='flex space-x-5'>
            <Avatar 
                onClick={()=>navigate(`/profile/${6}`)}
                className='cursor-pointer'
                alt="userName"
                src={defaultImg}
            />
            <div className='w-full'>
            <div className='justify-between items-center'>
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
                <div className="mt-2 ml-0 border border-gray-400 p-2 rounded-md">
                   
                    <div className="flex space-x-5">
                    <Avatar 
                        onClick={()=>navigate(`/profile/${6}`)}
                        className='cursor-pointer'
                        alt="userName"
                        src={defaultImg}
                    />
                    <div className='flex justify-between items-center'> 
                   <div className='flex cursor-pointer items-center space-x-2'>
                        <span className='font-semibold'>Abdullah Ayman</span>
                        <span className='text-gray-600'>@abdullah .2m</span>
                    </div>
                </div>
                    </div>
                    <div className="cursor-pointer p-7">
                        <p className="mb-2 p-0">Job about Full-stack react and spring boot</p>
                        <img className="w-[30rem] border border-gray-400 p-5 rounded-md"src={defaultImg} alt="" />
                    </div>
                    <div className="py-5 flex flex-wrap justify-between items-center">
                            <div className="space-x-3 flex items-center text-gray-600">
                                
                            </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default PostCard