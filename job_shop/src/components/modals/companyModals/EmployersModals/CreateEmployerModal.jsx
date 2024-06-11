import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import { IconButton, InputAdornment, MenuItem, Slide } from "@mui/material";
import { Close, Visibility, VisibilityOff } from "@mui/icons-material";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axios from "axios";
import * as Yup from "yup";
import { useDispatch, useSelector } from "react-redux";
import { createPost } from "../../../../store/Post/Action";
import { createEmployer, getAllFields } from "../../../../store/company/Action";
import { useState } from "react";
import { AddCircleOutline as AddCircleOutlineIcon, RemoveCircleOutline as RemoveCircleOutlineIcon } from '@mui/icons-material';
import CloseIcon from '@mui/icons-material/Close';
import ShowEmployerModal from "./ShowEmployersModal";
import { fetchAllFields } from "../../../../store/fields/Action";

const style = {
  position: "absolute",
  top: "10%",
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
  overflowY: "auto", // Enable scrolling
};
const slideStyle = {
  height: '100%',
  overflowY: 'auto',
  scrollbarWidth: 'none', // Hide scrollbar for Firefox
  '&::-webkit-scrollbar': {
    display: 'none', // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function CreateEmployerModal({ openCreateEmployerModal, handleCloseCreateEmployerModal }) {


  var [filteredFields, setFilteredFields] = useState([]);
  const [filterInputFields, setFilterInputField] = useState("");
  const [fields, setFields] = useState([]);
  const [selectedField, setSelectedField] =useState([]);
  const comp=useSelector(state=>state.comp)
  const dispatch =useDispatch()
  const auth=useSelector(state=>state.auth)

  const [openShowEmployerModal,setOpenShowEmployerModal]=React.useState(false)
  const handleOpenShowEmployerModal=()=>setOpenShowEmployerModal(true);
  const handleCloseShowEmployerModal=()=>setOpenShowEmployerModal(false);

  const handleFilterFields=(input)=>
  {
    const filtered=fields.filter((field)=>
    {
      return field.fieldName.toLowerCase().includes(input.toLowerCase());
    });
      setFilteredFields(filtered);
      setFilterInputField(input);
  }

const handleAddContact = () => {
    formik.setFieldValue('contacts', [...formik.values.contacts, '']);
};

const handleRemoveContact = (index) => {
    const newContacts = [...formik.values.contacts];
    newContacts.splice(index, 1);
    formik.setFieldValue('contacts', newContacts);
};

const handleContactChange = (e, index) => {
    const newContacts = [...formik.values.contacts];
    newContacts[index] = e.target.value;
    formik.setFieldValue('contacts', newContacts);
};
  React.useEffect(() => {

    if(openCreateEmployerModal)
    {
        dispatch(getAllFields(auth.user.id));
    }
   
  }, [openCreateEmployerModal,dispatch,auth.user.id]); // Empty dependency array ensures the effect runs only once after initial render

  React.useEffect(()=>
{
    if(openCreateEmployerModal)
    {
        setFields(comp.fields);
    }
},[comp,openCreateEmployerModal])

const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
};

const [showPassword, setShowPassword] = useState(false);

  const handleFieldSelect = (event) => {
    const selectedFieldId = event.target.value;
    const field = fields.find((field) => field.id === Number(selectedFieldId));
    setSelectedField(field);
    if (selectedField) {
      formik.setFieldValue("field", selectedField); // Set the selected field value in Formik state
    }
  };

  const handleSubmit = async (values,actions) => {
    console.log("values : ",values);
    await dispatch(createEmployer(values));
    
    actions.resetFormik();
  };

  const validationSchema = Yup.object().shape({
    email: Yup.string().email("Invalid email").required("Email is Required"),
    password: Yup.string().required("Password is required"),
    confirmPassword: Yup.string()
        .required('Confirm Password is required')
        .oneOf([Yup.ref('password'), null], 'Passwords must match'),
    userType: Yup.string().required("User type is required"),
    userName: Yup.string().required("Username is required"),
    address: Yup.string().required("Address is required"),
    gender:Yup.string().required("Gender is required")
  });
  const fieldsToDisplay=filterInputFields === "" ? fields : filteredFields;
  const formik = useFormik({
    initialValues: {
        userName: '',
        password: '',
        email: '',
        userType: 'Employer',
        confirmPassword: "",
        contacts: [''],
        address:'',
        gender:'',
        employerFields: [
          { companyFieldId: 0 }
        ]
    },
    validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });
  const [isHovered, setIsHovered] = React.useState(false);

  const handleAddField = (fieldToAdd) => {
    if (!selectedField.some((field) => field.id === fieldToAdd.id)) {
      const updatedFields = [...selectedField, fieldToAdd];
      setSelectedField(updatedFields);
      if (updatedFields) {
        formik.setFieldValue("employerFields", updatedFields);
      }
    }
  };

  const handleRemoveField = (fieldToRemove) => {
    const updatedFields = selectedField.filter(
      (field) => field.id !== fieldToRemove.id
    );
    
    if (updatedFields) {
      setSelectedField(updatedFields);
      formik.setFieldValue("employerFields", updatedFields);
    }
  };


  return (
    <div>
      <Modal
        open={openCreateEmployerModal}
        onClose={handleCloseCreateEmployerModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Slide 
          direction="left"
          in={openCreateEmployerModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 800, exit: 800 }}
          transitionTimingFunction="ease-in-out" 
          style={slideStyle}
      >
        <Box sx={style}>
          <div className="modal-content-container flex items-center justify-between mb-4">
          <div className="flex items-center space-x-1 space-y-1 text-gray-500">
              <IconButton
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
                onClick={handleCloseCreateEmployerModal}
              >
                <CloseIcon style={{ color: isHovered ? 'red' : 'black' }} />
              </IconButton>
              <p className="">Create Employer</p>
            </div>
          </div>
          <form onSubmit={formik.handleSubmit}>
            <Grid container direction="column" spacing={2}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="userName"
                  name="userName"
                  label="Username"
                  value={formik.values.userName}
                  onChange={formik.handleChange}
                  error={formik.touched.userName && Boolean(formik.errors.userName)}
                  helperText={formik.touched.userName && formik.errors.userName}
                />
              </Grid>
              <Grid item xs={12}>
                        <TextField
                          fullWidth
                          select
                          id="gender"
                          name="gender"
                          label="Gender"
                          value={formik.values.gender}
                          onChange={formik.handleChange}
                          variant="outlined"
                          error={
                            formik.touched.gender &&
                            Boolean(formik.errors.gender)
                          }
                          helperText={
                            formik.touched.gender &&
                            formik.errors.gender
                          }
                        >
                          <MenuItem value="Male">Male</MenuItem>
                          <MenuItem value="Female">Female</MenuItem>
                        </TextField>
                      </Grid>
              <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Email"
                        variant='outlined'
                        size='large'
                        name="email"
                        value={formik.values.email}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.email && Boolean(formik.errors.email)}
                        helperText={formik.touched.email && formik.errors.email}
                    />
                </Grid>

              <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Password"
                        variant='outlined'
                        size='large'
                        type={showPassword ? 'text' : 'password'}
                        name="password"
                        value={formik.values.password}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.password && Boolean(formik.errors.password)}
                        helperText={formik.touched.password && formik.errors.password}
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <IconButton
                                        onClick={togglePasswordVisibility}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            ),
                        }}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Confirm Password"
                        variant='outlined'
                        size='large'
                        type={showPassword ? 'text' : 'password'}
                        name="confirmPassword"
                        value={formik.values.confirmPassword}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)}
                        helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
                    />
                </Grid>

                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Country/City"
                        variant='outlined'
                        size='large'
                        name="address"
                        value={formik.values.address}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.address && Boolean(formik.errors.address)}
                        helperText={formik.touched.address && formik.errors.address}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Contact"
                        variant='outlined'
                        size='large'
                        name="contact"
                        value={formik.values.contact}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.contact && Boolean(formik.errors.contact)}
                        helperText={formik.touched.contact && formik.errors.contact}
                    />
                    <IconButton onClick={handleAddContact} edge="end">
                        <AddCircleOutlineIcon />
                    </IconButton>
                </Grid>
                {formik.values.contacts.map((contact, index) => (
                    <Grid item xs={12} key={index}>
                        <TextField
                            fullWidth
                            variant='outlined'
                            label={`Contact ${index+1}`}
                            size='large'
                            value={contact}
                            onChange={(e) => handleContactChange(e, index)}
                            onBlur={formik.handleBlur}
                            error={formik.touched.contacts && Boolean(formik.errors.contacts && formik.errors.contacts[index])}
                            helperText={formik.touched.contacts && formik.errors.contacts && formik.errors.contacts[index]}
                        />
                        <IconButton onClick={() => handleRemoveContact(index)} edge="end">
                            <RemoveCircleOutlineIcon />
                        </IconButton>
                    </Grid>
                ))}


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
                
              {Array.isArray(fieldsToDisplay) && fieldsToDisplay.length > 0 && (
                fieldsToDisplay
                  .filter((field) => !selectedField.some((selected) => selected.id === field.id))
                  .map((field,index) => (
                    <Button 
                      key={index} 
                      variant="outlined" 
                      onClick={() => handleAddField(field)}
                    >
                      <div className="ml-2">{field.fieldName}</div>

                      <ul className="flex flex-wrap space-x-2 mt-3">
                        {field.skills && field.skills.length > 0 ? (
                          field.skills.map((skill, index) => (
                            <div className="flex" key={index}>
                              <li key={index}>- {skill}</li>
                            </div>
                          ))
                        ) : (
                          <div>No skills</div>
                        )}
                      </ul>
                    </Button>
                  ))
              )}
              </div>
              </Grid>
              <Grid item container justifyContent="center">
                <Button type="submit" variant="contained" color="primary">
                  Create
                </Button>
              </Grid>
              <Grid item container justifyContent="center" >
                 <Button 
                    onClick={() => handleOpenShowEmployerModal()}
                    variant="outlined" color="primary">
                      Show Employers
                </Button>
           </Grid>
              {/* <Grid item xs={12}>
                    <Button
                        sx={{ borderRadius: "29px", py: "15px", bgcolor: blue[500] }}
                        type="submit"
                        fullWidth
                        size="large"
                        variant='contained'
                    >
                        Signup
                    </Button>
                </Grid> */}
            </Grid>
          </form>
          <section>
          <ShowEmployerModal openShowEmployerModal={openShowEmployerModal} handleCloseShowEmployerModal={handleCloseShowEmployerModal}/>
        </section>
        </Box>
        </Slide>
      </Modal>
    </div>
  );
}
