import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Slide from '@mui/material/Slide';
import { Button, Grid, IconButton, TextField } from '@mui/material';
import { useFormik } from 'formik';
import { useDispatch, useSelector } from 'react-redux';
import { updateEmployerContacts } from '../store/company/Employer/Action';
import { updateCompanyContacts } from '../store/company/Action';
import { updateJobSeekerContacts } from '../store/JobSeeker/Action';
import { AddCircleOutline as AddCircleOutlineIcon, RemoveCircleOutline as RemoveCircleOutlineIcon } from '@mui/icons-material';
import MessageModal from './MessageModal';
import { useState } from 'react';
import { useEffect } from 'react';

const style = {
  position: 'absolute',
  top: '10%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  outline: 'none',
  border: 'none',
  boxShadow: 24,
  p: 4,
  maxHeight: '60vh',
  borderRadius: 4,
  overflowY: 'auto',
};

const slideStyle = {
  height: '100%',
  overflowY: 'auto',
  scrollbarWidth: 'none',
  '&::-webkit-scrollbar': {
    display: 'none',
  },
};

export default function EditContactsModal({
  openEditeContactsModal,
  handleCloseEditeContactsModal,
  contactsList = [],
}) {
  const [message, setMessage] = useState("");
  const [openMessageModal, setOpenMessageModal] = useState(false);
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const handleOpenMessageModal = () => setOpenMessageModal(true);
  const handleCloseMessageModal = () => setOpenMessageModal(false);
  const [messageState,setState]=useState(false);
  useEffect(() => {
    if (contactsList) {
      formik.setFieldValue("contacts", contactsList);
    }
  }, [contactsList]);

  const handleSubmit =  async (values) => {
    try {
      const formData = { ...values, contacts: values.contacts.filter(contact => contact !== '') };
      if (auth.user.userType === "jobSeeker") {
         await dispatch(updateJobSeekerContacts(formData));
        setState(true);
        setMessage("Updated Successfully...");
      } else if (auth.user.userType === "Employer") {
        await dispatch(updateEmployerContacts(formData));
        setState(true);
        setMessage("Updated Successfully...");
      } else {
        await dispatch(updateCompanyContacts(formData));
        setState(true);
        setMessage("Updated Successfully...");
      }
    
    } catch (error) {
      setState(false);
      setMessage(`ERROR update contacts: ${error.message}`);
    } finally {
      handleOpenMessageModal();
    }
  };

  const formik = useFormik({
    initialValues: {
      contacts: ['']
    },
    onSubmit: handleSubmit
  });

  const handleContactChange = (e, index) => {
    const newContacts = [...formik.values.contacts];
    newContacts[index] = e.target.value;
    formik.setFieldValue('contacts', newContacts);
  };

  const handleRemoveContact = (index) => {
    const newContacts = [...formik.values.contacts];
    newContacts.splice(index, 1);
    formik.setFieldValue('contacts', newContacts);
  };

  const handleAddContact = () => {
    formik.setFieldValue('contacts', [...formik.values.contacts, '']);
  };

  return (
    <div>
      <Modal
        open={openEditeContactsModal}
        onClose={handleCloseEditeContactsModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        closeAfterTransition
      >
        <Slide
          direction="left"
          in={openEditeContactsModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 300 }}
          style={slideStyle}
        >
          <Box sx={style}>
            <form onSubmit={formik.handleSubmit}>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                Edit Contacts
              </Typography>
              <Grid container spacing={2}>
                {formik.values.contacts.map((contact, index) => (
                  <Grid item xs={12} key={index}>
                    <TextField
                      fullWidth
                      variant="outlined"
                      label={`Contact ${index + 1}`}
                      size="large"
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
                  <IconButton onClick={handleAddContact} edge="end">
                    <AddCircleOutlineIcon />
                  </IconButton>
                </Grid>
                <Grid item container justifyContent="center">
                  <Button type="submit" variant="contained" color="primary">
                    Submit
                  </Button>
                </Grid>
              </Grid>
            </form>
          </Box>
        </Slide>
      </Modal>
      <MessageModal openMessageModal={openMessageModal} 
      handleCloseMessageModal={handleCloseMessageModal} 
      response={message} Title={"UPDATE CONTACTS"} 
      state={messageState} />
    </div>
  );
}
