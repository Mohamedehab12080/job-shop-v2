import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { deleteEmployer, deleteField, getAllFields, getEmployers, giveEmployerFields } from '../../../../store/company/Action';
import {  Avatar, Grid, MenuItem, TextField } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useFormik } from 'formik';
import { Close } from "@mui/icons-material";
import {IconButton} from "@mui/material";
export default function GiveEmployerFields({openGiveEmployerFields,handleCloseGiveEmployerFields}) {
  
  var [filteredEmployers,setFilteredEmployers] = React.useState([]);
  const [filterInputEmployer, setFilterInputEmployer] = React.useState("");
  const auth=useSelector(state=>state.auth);
  const dispatch=useDispatch();
  const dispatch2=useDispatch();
  const dispatch3=useDispatch();
  const dispatch4=useDispatch();
  const [selectedEmployer,setSelectedEmployer]=React.useState(null);
  const [fetchedCompanyFields,setFetchedCompanyFields]=React.useState([]);
  const comp=useSelector(state=>state.comp);
  const [fetchedEmployers,setFetchedEmployers]=React.useState([])
  const navigate=useNavigate();
  const [selectedField, setSelectedField] =React.useState([]);
  var [filteredFields, setFilteredFields] =React.useState([]);
  const [filterInputFields, setFilterInputField] = React.useState("");
  const handleFilterFields=(input)=>
    {
      const filtered=fetchedCompanyFields.filter((field)=>
      {
        return field.fieldName.toLowerCase().includes(input.toLowerCase());
      });
        setFilteredFields(filtered);
        setFilterInputField(input);
    }
  const handleSelecteEmployer=(value)=>{
    setSelectedEmployer(value);
    formik.setFieldValue("employerId",value.id);
  }
  
  const handleCancelSelecteEmployer=()=>{
    setSelectedEmployer(null);
  }
  const handleFilterEmployers=(input)=>
  {
    const filtered=fetchedEmployers.filter((employer)=>
    {
      if(employer.userName.toLowerCase().includes(input.toLowerCase()) ||employer.email.toLowerCase().includes(input.toLowerCase))
        {
            return employer;
        }
    });
      setFilteredEmployers(filtered);
      setFilterInputEmployer(input);
  }

  const handleSubmit = async (values) => {
    console.log("values : ",values);
    dispatch4(giveEmployerFields(values));
  };
  const handleRemoveField = (fieldToRemove) => {
    const updatedFields = selectedField.filter(
      (field) => field.id !== fieldToRemove.id
    );
    
    const objectofSetFields = updatedFields.map((field) => ({
        id: field.id,
        employerId: selectedEmployer.id
      }));
    
      if (objectofSetFields) {
        setSelectedField(fieldToRemove);
        formik.setFieldValue("employerFields", objectofSetFields);
      }
  };
  React.useEffect(() => {
    if (openGiveEmployerFields) {
      dispatch(getEmployers(auth.user.id));
    }
  }, [openGiveEmployerFields, dispatch, auth.user.id]);
  
  // Add comp as a dependency to useEffect to ensure that the log executes 
  // with the updated value. 
  React.useEffect(() => {
    if (openGiveEmployerFields) {
      setFetchedEmployers(comp.employers); // Set your fields with the updated value here
    }
  }, [comp, openGiveEmployerFields]);

  React.useEffect(()=>
{
    if(openGiveEmployerFields)
    {
        dispatch3(getAllFields(auth.user.id));
    }
},[dispatch3,openGiveEmployerFields,auth.user.id])

React.useEffect(() => {
    if (openGiveEmployerFields) {
      setFetchedCompanyFields(comp.fields); // Set your fields with the updated value here
    }
  }, [comp, openGiveEmployerFields]);

  const handleAddField = (fieldToAdd) => {
    if (!selectedField.some((field) => field.id === fieldToAdd.id)) {
      const updatedFields = [...selectedField, fieldToAdd];
      setSelectedField(fieldToAdd.id);
      const objectofSetFields = updatedFields.map((field) => ({
        id: field.id,
        employerId: selectedEmployer.id
      }));
    
      if (objectofSetFields) {
        formik.setFieldValue("employerFields", objectofSetFields);
      }
    }
  };
 const formik=useFormik({
    initialValues:{
        employerFields: [
            { 
              id: 0 ,
              employerId:0
            }
          ]
    },
    onSubmit:handleSubmit,
 })
  const handleDeleteEmployer=(employerId)=>
  {
    // console.log("Field For Delete : ",fieldId)
    dispatch2(deleteEmployer(employerId));
    setFetchedEmployers(prevEmps => prevEmps.filter(emp => emp.id !== employerId));
  };

  return (
    <div>
      <Modal
        open={openGiveEmployerFields}
        onClose={handleCloseGiveEmployerFields}
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
           {(selectedEmployer !== null ? selectedEmployer : filterInputEmployer==="" ? fetchedEmployers : filteredEmployers).map((emp, index) => (
           <>
           <div><hr></hr></div>
           <div key={emp.id} className='flex space-x-5'>
           <Avatar
                onClick={() => navigate(`/profile/${emp.id}`)}
                className='cursor-pointer'
                alt="userName"
                src={emp.picture}
           />
            <div className='w-full'>

            <div className='flex justify-between items-center'>
                    <div className='flex cursor-pointer items-center space-x-2'>
                        <span className='font-semibold'>{emp.userName}</span>
                        <span className='text-gray-600'>@{emp.email}</span>
                    </div>
                    
                    <div>

                        {selectedEmployer !==null ? (
                            <Button
                            id="basic-button"
                            onClick={() => handleSelecteEmployer(emp)}
                        >
                            Select
                        </Button>
                        ):(
                            <Button
                                id="basic-button"
                                onClick={() => handleCancelSelecteEmployer()}
                            >
                                Select
                            </Button>
                        )}
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



          <section>
            
          </section>
        <section>
        <Grid item xs={12}>
                <TextField
                    fullWidth
                    id="filterFields"
                    name="filterFields"
                    label="Filter Fields"
                    value={filterInputFields}
                    onChange={(e) => handleFilterFields(e.target.value)}
                />
         </Grid>
        <Grid item xs={12}>
                <div className="selected-skills-container">
                  {selectedField.length > 0 ? (
                    selectedField.map((field) => (
                      <div key={field.id} className="selected-skill">
                        <span>{field.fieldName}</span>
                        <IconButton
                          onClick={() => handleRemoveField(field)}
                          aria-label="delete"
                          size="small"
                        >
                          <Close />
                        </IconButton>
                      </div>
                    ))
                  ) : (
                    <div className="error-message">
                      At least one field is required
                    </div>
                  )}
                </div>
              </Grid>
              <Grid item xs={12}>
              <div className="skills-scroll-container sapce-y-2" style={{ maxHeight: "200px", overflowY: "auto" }}>
                {/* Filter and map through fields to display buttons */}
                {(filterInputFields === "" ? fetchedCompanyFields : filteredFields)
                  .filter((field) => !selectedField.some((selected) => selected.id === field.id))
                  .map((field) => (
                    <Button key={field.id} variant="outlined"
                     onClick={() => handleAddField(field)}>
                      {/* Display field name */}
                      <>
                      <div className="ml-2">{field.fieldName}</div>
                       </>
                      
                    <ul>

                    {field.skills && field.skills.length > 0 ? (
                        field.skills.map((skill, index) => (
                          <div className="flex" key={index}>
                             <li className="">- {skill}</li>
                            
                            </div>
                        ))
                      ) : (
                        <div>No skills</div>
                      )}
                    </ul>
                      {/* Display skills if available, otherwise show "No skills" */}
                    
                    </Button>
                  ))}
              </div>
              </Grid>
        </section>
        </Box>
      </Modal>
    </div>
  );
}
