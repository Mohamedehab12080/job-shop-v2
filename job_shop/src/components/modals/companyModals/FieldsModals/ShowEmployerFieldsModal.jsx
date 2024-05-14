import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { deleteField, getAllFields } from '../../../../store/company/Action';
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import {  Grid, MenuItem, Slide, TextField } from '@mui/material';
import Menu from "@mui/material/Menu";
import { getEmployerFields } from '../../../../store/company/Employer/Action';
const slideStyle = {
  height: '100%',
  overflowY: 'auto',
  scrollbarWidth: 'none', // Hide scrollbar for Firefox
  '&::WebkitScrollbar': {
    display: 'none', // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function ShowEmployerFieldsModal({openShowEmployerFieldsModal,handleCloseShowEmployerFields,isRequestUser,userId}) {
  
  var [filteredFields,setFilteredFields] = React.useState([]);
  const [filterInputField, setFilterInputField] = React.useState("");
  const auth=useSelector(state=>state.auth);
  const dispatch=useDispatch();
  const emp=useSelector(state=>state.emp);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [fetchedFields,setFetchedFields]=React.useState([])

  const handleFilterFields=(input)=>
  {
    const filtered=fetchedFields.filter((field)=>
    {
      return field.fieldName.toLowerCase().includes(input.toLowerCase());
    });
      setFilteredFields(filtered);
      setFilterInputField(input);
  }

  const handleEditField=(fieldId)=>
  {
    /// Handle Edit open the createFieldModal and make it for edit 
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  React.useEffect(() => {
    if (openShowEmployerFieldsModal && isRequestUser) {
      console.log("User Employer ID : ",auth.user.id);
      dispatch(getEmployerFields(auth.user.id));
    }else if(openShowEmployerFieldsModal){
      console.log("User ID : ",userId)
      dispatch(getEmployerFields(userId));
    }
  }, [openShowEmployerFieldsModal, dispatch, auth.user.id,userId]);
  
  // Add comp as a dependency to useEffect to ensure that the log executes 
  // with the updated value. 
  React.useEffect(() => {
    if (openShowEmployerFieldsModal && emp.fields) {
      setFetchedFields(emp.fields); // Set your fields with the updated value here
    }
  }, [emp.fields, openShowEmployerFieldsModal]);


//  React.useEffect(()=>
// {
//     if(openShowEmployerFieldsModal)
//     {
//       dispatch(getAllFields(auth.user.id));
//       console.log("fields : ",comp.fields.length)
//       setFields();
//     }

// },[
//   openShowEmployerFieldsModal,
//   dispatch
//  ]);

  

  return (
    <div>
      <Modal
        open={openShowEmployerFieldsModal}
        onClose={handleCloseShowEmployerFields}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
    <Slide
          direction="up"
          in={openShowEmployerFieldsModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 1000, exit: 1000 }}
          transitionTimingFunction="ease-in-out" 
          style={slideStyle}
      >
        <Box  sx={{
          position: "absolute",
          top: "10%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 500,
          bgcolor: "background.paper",
          border: "none",
          boxShadow: 24,
          p: 4,
          outline: "none",
          borderRadius: 4,
          maxHeight: "95vh",
          overflowY: "auto",
        }}
        >
  <Grid item xs={12}>
      <TextField
        fullWidth
        id="filterFields"
        name="filterFields"
        label="Filter Fields"
        value={filterInputField}
        onChange={(e) => handleFilterFields(e.target.value)}
      />
    </Grid>
    <div><hr></hr></div>
           <section>
           {(filterInputField === "" ? fetchedFields : filteredFields).map((field, index) => (
           <>
      <div key={field.id} className='flex space-x-5'>
         <div className='w-full'>
             <div className='flex justify-between items-center'>
                 <div className=' cursor-pointer items-center space-x-2'>
                     <span className='font-bold text-xl'>{field.fieldName}</span>
                   
                 </div>
             </div>
             <div className="mt-2">
             <div className='mt-2 flex items-center space-x-2 overflow-auto'>
                  <div className='flex flex-col ml-3'>
                    {field.skills.length > 0 ? (
                      <>
                      <p className='mr-2 text-xl font-semibold'>* Field Skills:</p>
                        {field.skills.map((skill, index) => (
                           <React.Fragment key={index}>
                              
                              <p key={index} className='ml-2 mt-1 text-xl text-gray-600'>{index >= 0 && (<>- </>)}{skill}</p>

                           </React.Fragment>
                            
                          ))}
                      </>
                    ) : (
                      <p className='ml-2 mt-1 text-gray-400'>No skills available</p>
                    )}
                 </div>
            </div>
            
                     <div className='flex items-center space-x-2 overflow-auto'>
                     <div className='flex flex-col ml-3'>

                     {field.qualifications.length > 0 ? 
                       (
                        
                        <>
                        <p className='text-xl font-semibold '>* Field Qualifications :</p>
                          { field.qualifications.map(qual=>(
                           <>
                              {field.qualifications.map((qual, index) => (
                                <React.Fragment key={index}>
                                    
                                    <p key={index} className='ml-2 mt-1 text-xl text-gray-600'>{index >= 0 && (<>- </>)}{qual}</p>

                                </React.Fragment>
                              
                            ))}
                           </>
                         ))}
                        </>
                       ):(
                         <></>
                       )}
                       
                     </div>
                       
                     </div>
                         <div><hr></hr></div>
                       </div>
                    </div>
                  </div>  
           </>
          ))}
           </section>
        </Box>
        </Slide>
      </Modal>
    </div>
  );
}
