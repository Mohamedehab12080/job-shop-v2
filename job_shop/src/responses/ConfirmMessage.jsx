import React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Slide from '@mui/material/Slide';
import { CheckCircle, BusinessCenter } from '@mui/icons-material';
import { Button, Grid } from '@mui/material';
import { useDispatch } from 'react-redux';
import { deletePost } from '../store/Post/Action';
import { Logout } from '../store/Auth/Action';
const style = {
  position: "absolute",
  top: "10%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "none",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: 4,
  maxHeight: "30vh",
  overflowY: "auto",
};
const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none",
  "&::WebkitScrollbar": {
    display: "none",
  },
};
export default function ConfirmMessage({
   openConfirmMessage, 
   handleCloseConfirmMessage,
    response,
     Title,
      postId,
       operationType }) {
  const dispatch = useDispatch();

  const handleDispatchExecution = () => {
    if (operationType === "deletePost") {
      dispatch(deletePost(postId));
    }

    if(operationType==="Logout")
      {
        dispatch(Logout());
      }
    handleCloseConfirmMessage();
  };

  return (
    <Modal
      open={openConfirmMessage}
      onClose={handleCloseConfirmMessage}
      aria-labelledby="modal-title"
      aria-describedby="modal-description"
      closeAfterTransition
    >
        <Slide
          direction="down"
          in={openConfirmMessage}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 300 }}
          style={slideStyle}
        >
        <Box sx={style}>
          <Typography id="modal-title" variant="h6" component="h2">
            {Title === "Education" ? (
              <div style={{ display: 'flex', alignItems: 'center' }}>
                <BusinessCenter style={{ color: 'green', marginRight: 8 }} />
                {Title}
              </div>
            ) : (
              Title
            )}
          </Typography>
          <Typography id="modal-description" sx={{ mt: 2, ml: 3 }}>
            {response}
          </Typography>
          <Grid container justifyContent="space-between" sx={{ mt: 2 }}>
            <Button
              onClick={handleDispatchExecution}
              variant="contained"
              color="primary"
              sx={{ mx: 1 }}
            >
              {operationType}
            </Button>
            <Button
              onClick={handleCloseConfirmMessage}
              variant="contained"
              color="secondary"
              sx={{ mx: 1 }}
            >
              Cancel
            </Button>
          </Grid>
        </Box>
      </Slide>
    </Modal>
  );
}
