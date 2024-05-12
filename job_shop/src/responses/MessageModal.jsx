import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Slide from '@mui/material/Slide';
import { CheckCircle } from '@mui/icons-material';
import BussinessCetnerIcon from '@mui/icons-material/BusinessCenter'

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function MessageModal({ openMessageModal, handleCloseMessageModal, response, Title }) {
   
   
    return (
      <div>
        <Modal
          open={openMessageModal}
          onClose={handleCloseMessageModal}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
          closeAfterTransition  // This ensures the modal closes only after transition
          BackdropProps={{ timeout: 100 }} // Adjust timeout as needed
        >
          <Slide direction="up" in={openMessageModal} mountOnEnter unmountOnExit>
            <Box sx={style}>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                {Title === "Education" ?(
                 <div className='flex'>
                   <BussinessCetnerIcon style={{ color: 'green' }} />
                    {Title}
                  </div>
                ):(
                  <>
                    {Title}
                  </>
                )}
  
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2, ml:3}}>
                {response}
              </Typography>
            </Box>
          </Slide>
        </Modal>
      </div>
    );
  }
  