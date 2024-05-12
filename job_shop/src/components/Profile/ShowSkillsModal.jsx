import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Slide from '@mui/material/Slide';
import SkillsIcon from '@mui/icons-material/Attractions';
import { blue } from '@mui/material/colors';

const modalStyle = {
  position: 'absolute',
  top: '20%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  border: 'none',
  borderRadius: 4,
  outline: 'none',
  boxShadow: 24,
  p: 4,
  maxHeight: '70vh',
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
  
export default function ShowSkillsModal({ 
  openShowSkillsModal,
  handleCloseShowSkillsModal,
  skills,
  qualifications
}) {
  return (
    <Modal
      open={openShowSkillsModal}
      onClose={handleCloseShowSkillsModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
      closeAfterTransition
      BackdropProps={{ timeout: 100 }}
    >
      <Slide
        direction="left"
        in={openShowSkillsModal}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 500, exit: 300 }}
        transitionTimingFunction="ease-in-out" 
        style={slideStyle}
      >
        <Box sx={modalStyle}>
          <Typography variant="h6"
           component="h2" 
           sx={{ display: 'flex', alignItems: 'center', color: blue[500] }}>
            <SkillsIcon sx={{ marginRight: '8px' }} />
            Skills:
          </Typography>
          <Typography sx={{ mt: 2, ml: 3 }}>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              {skills && skills.length > 0 ? (
                skills.map((skill, index) => (
                  <li key={index}>- {skill}</li>
                ))
              ) : (
                <li>No Skills...</li>
              )}
            </ul>
          </Typography>
          <Typography variant="h6" component="h2" sx={{ display: 'flex', alignItems: 'center', color: blue[500] }}>
            <SkillsIcon sx={{ marginRight: '8px' }} />
            Qualifications:
          </Typography>
          <Typography sx={{ mt: 2, ml: 3 }}>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              {qualifications && qualifications.length > 0 ? (
                qualifications.map((qual, index) => (
                  <li key={index}>- {qual}</li>
                ))
              ) : (
                <li>No Qualifications...</li>
              )}
            </ul>
          </Typography>
        </Box>
      </Slide>
    </Modal>
  );
}
