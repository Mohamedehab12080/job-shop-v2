import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { deleteEmployer, deleteField, getAllFields, getEmployers } from '../../../../store/company/Action';
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import {  Avatar, Grid, MenuItem, TextField } from '@mui/material';
import Menu from "@mui/material/Menu";
import { useNavigate } from 'react-router-dom';

export default function ShowEmployerModal({openShowEmployerModal,handleCloseShowEmployerModal}) {
  
  var [filteredEmployers,setFilteredEmployers] = React.useState([]);
  const [filterInputEmployer, setFilterInputField] = React.useState("");
  const auth=useSelector(state=>state.auth);
  const dispatch=useDispatch();
  const dispatch2=useDispatch();
  const comp=useSelector(state=>state.comp);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openMenu = Boolean(anchorEl);
  const [fetchedEmployers,setFetchedEmployers]=React.useState([])
  const navigate=useNavigate();

  const handleFilterEmployers=(input)=>
  {
    const filtered=fetchedEmployers.filter((employer)=>
    {
      return employer.userName.toLowerCase().includes(input.toLowerCase());
    });
      setFilteredEmployers(filtered);
      setFilterInputField(input);
  }

  const handleEditEmployer=(fieldId)=>
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
    if (openShowEmployerModal) {
      dispatch(getEmployers(auth.user.id));
    }
  }, [openShowEmployerModal, dispatch, auth.user.id]);
  
  // Add comp as a dependency to useEffect to ensure that the log executes 
  // with the updated value. 
  React.useEffect(() => {
    if (openShowEmployerModal) {
      setFetchedEmployers(comp.employers); // Set your fields with the updated value here
    }
  }, [comp, openShowEmployerModal]);

  const handleDeleteEmployer=(employerId)=>
  {
    // console.log("Field For Delete : ",fieldId)
    dispatch2(deleteEmployer(employerId));
    setFetchedEmployers(prevEmps => prevEmps.filter(emp => emp.id !== employerId));
  };
  // const [employerpicture,setEmployerPicture]=React.useState(null);
  // const uint8Array = new Uint8Array(employerpicture);

  // // Convert the Uint8Array to a Blob
  // const blob = new Blob([uint8Array]);
  // const imageUrl = URL.createObjectURL(blob);
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
  return (
    <div>
      <Modal
        open={openShowEmployerModal}
        onClose={handleCloseShowEmployerModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box  sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 600,
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
  <Grid item xs={12}>
      <TextField
        fullWidth
        id="filterEmployers"
        name="filterEmployers"
        label="Filter Employers"
        value={filterInputEmployer}
        onChange={(e) => handleFilterEmployers(e.target.value)}
      />
    </Grid>
        <section>
           {(filterInputEmployer === "" ? fetchedEmployers : filteredEmployers).map((emp, index) => (
           <>
           <div><hr></hr></div>
           <div key={emp.id} className='flex space-x-5'>
           <Avatar
                onClick={() => navigate(`/profile/${emp.id}`)}
                className='cursor-pointer'
                alt="userName"
                src={getDynamicImageUrl(emp.picture)}
           />
            <div className='w-full'>

            <div className='flex justify-between items-center'>
                    <div className='flex cursor-pointer items-center space-x-2'>
                        <span className='font-semibold'>{emp.userName}</span>
                        <span className='text-gray-600'>@{emp.email}</span>
                    </div>
                    
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
                            <MenuItem onClick={()=>handleDeleteEmployer(emp.id)}>Delete</MenuItem>
                            <MenuItem onClick={()=>handleEditEmployer(emp.id)}>Edit</MenuItem>
                    </Menu>
                    </div>
                   
                </div>
                <div className='flex'>
                <p className='font-semibold text-gray-500'> Fields :</p>
                {emp.fieldsNames && emp.fieldsNames.length > 0 ? (
                  emp.fieldsNames.map((empField, index) => (
                    <>
                    <div className='flex'> 
                    <p className='ml-2'>{index > 0 && " , "}</p>
                    <p className='ml-2 text-gray-600'>{empField}</p>
                    </div>
                    </>
                    
                  ))
                ) : (
                  <>Nooo</> // Placeholder for rendering when emp.employerFields is empty
                )}  
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
