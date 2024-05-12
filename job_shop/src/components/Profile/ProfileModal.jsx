import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useFormik } from 'formik';
import { IconButton, Slide } from '@mui/material';
import { Close } from '@mui/icons-material';
import logo from '../common/images/myPic.jpg';
import {Avatar} from '@mui/material';
import TextField from '@mui/material/TextField';
import './ProfileModal.css'
const style = {
  position: 'absolute',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  bgcolor: 'background.paper',
  border: 'none',
  boxShadow: 24,
  outline:'none',
  p: 4,
  borderRadius:4,
  maxHeight: '110vh',
  overflowY: 'auto',
  height: '100%',  // Ensure modal takes up full height for scrolling
};
const slideStyle = {
  height: '100%',
  overflowY: 'auto',
  scrollbarWidth: 'none', // Hide scrollbar for Firefox
  '&::-webkit-scrollbar': {
    display: 'none', // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function ProfileModal({open,handleClose}) {
  // const [open, setOpen] = React.useState(false);
  const [uploading,setUploading]=React.useState(false);

  const handleSubmit=(values)=>
  {
    console.log("handle submit",values);
  };
  const handleImageChange=(event)=>
  {
    setUploading(true);
    const {name} =event.target
    const file=event.target.files[0];
    formik.setFieldValue(name,file);
    setUploading(false);
  }
  const formik=useFormik
  (
    {
        initialValues:{
            userName:"",
            description:"",
            location:"",
            backgroundImage:"",
            image:""
        },
        onSubmit:handleSubmit
    }
    
  );
  return (
    <div>
      {/* <Button onClick={handleOpen}>Open modal</Button> */}
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
         <Slide
        direction="left"
        in={open}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 500, exit: 300 }}
        transitionTimingFunction="ease-in-out" 
        style={slideStyle}
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <div className='flex items-center justify-between'>
                <div className='flex items-center space-x-1 space-y-2 text-gray-500'>
                    <IconButton onClick={handleClose} aria-label='delete'/>
                    <Close        style={{
                                    cursor: 'pointer',
                                    transition: 'transform 0.3s ease-in-out, background-color 0.3s ease-in-out',
                                    borderRadius:50
                                }}
                                onMouseEnter={(e) => {
                                    e.target.style.transform = 'scale(1.2)';
                                    e.target.style.backgroundColor = '#e0e0e0'; // Change to desired color
                                }}
                                onMouseLeave={(e) => {
                                    e.target.style.transform = 'scale(1)';
                                    e.target.style.backgroundColor = 'transparent';
                                }}
                                    />
                    <IconButton />
                    <p className=''>Edit profile</p>
                </div>
                <Button type='submit'> 
                         Save
                    </Button>
                </div>
            <div className='hideScrollBar overflow-y-scroll overflow-x-hidden h-[80vh]'>
               <React.Fragment>
                  <div className='w-full'>


                    <div className='relative'>

                          <img className='w-full h-[12rem] object-cover object-center'
                          src="https://cdn.pixabay.com/photo/2024/03/08/16/06/building-8621170_1280.jpg" alt="" />
                        <input type='file' className='absolute top-0 left-0 w-full h-full opacity-0 cursor-pointer'
                        onChange={handleImageChange}
                        name='backgroundImage'
                        >
                                  
                        </input>
                    </div>
                  </div>
                  {/* flex items-start mt-5 h-[5rem] */}
                  <div className='w-full transform -translate-y-20 ml-4 h-[6rem]'>
                          <div className='relative'>
                          {/* transform -translate-y-24 */}
                           <Avatar className='' alt='BOB' src={logo}
                            sx={{width:"10rem",height:"10rem",border:"4px solid white"}}/> 
                            <input
                              className='absolute top-0 left-0 w-[10rem] h-full opacity-0 cursor-pointer'
                              onChange={handleImageChange}
                              name="image"
                              type='file'
                            />
                          </div>
                      </div>
                </React.Fragment>
                <div className='space-y-3'>
               
                      <TextField
                        fullWidth
                        id='userName'
                        name='userName'
                        label='User Name'
                        value={formik.values.userName}
                        onChange={formik.handleChange}
                        error={formik.touched.userName && Boolean(formik.errors.userName)}
                        helperText={formik.touched.userName && formik.errors.userName}
                        />

                        <TextField
                        fullWidth
                        multiline
                        rows={4}
                        id='description'
                        name='description'
                        label='Description'
                        value={formik.values.description}
                        onChange={formik.handleChange}
                        error={formik.touched.description && Boolean(formik.errors.description)}
                        helperText={formik.touched.description && formik.errors.description}
                        />

                        <TextField
                        fullWidth
                        id='location'
                        name='location'
                        label='Location'
                        value={formik.values.location}
                        onChange={formik.handleChange}
                        error={formik.touched.location && Boolean(formik.errors.location)}
                        helperText={formik.touched.location && formik.errors.location}
                        />
                        
                        <div className='my-3'> 
                                <p className='text-lg'>Birth date . Edit</p>
                                <p className='text-2xl'>October 26, 1999</p>
                        </div>
                        <p className='py-3 text-lg'>Edit Professional Profile</p>
                </div>
            </div>
          </form>
        </Box>
        </Slide>
      </Modal>
    </div>
  );
}