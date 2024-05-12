import React from 'react';
import { Box, Modal, IconButton, Typography, Grid, Button } from '@mui/material';
import { Close } from '@mui/icons-material';
import { useSelector } from 'react-redux';
import ShowApplicationsModal from '../../companyModals/postModals/ShowApplicationsModal';

export default function ShowApplicationResultsModal({
  openShowApplicationResultsModal,
  handleCloseShowApplicationResultsModal
}) {
  const post = useSelector(state => state.post);
  const [openShowApplicationsModal, setOpenShowApplicationsModal] = React.useState(false);
  const handleOpenShowApplicationsModal = () => setOpenShowApplicationsModal(true);
  const handleCloseShowApplicationsModal = () => setOpenShowApplicationsModal(false);
  return (
    <Modal
      open={openShowApplicationResultsModal}
      onClose={handleCloseShowApplicationResultsModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: 800,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 4,
          borderRadius: 8,
          maxHeight: '80vh',
          overflowY: 'auto',
          scrollbarWidth: 'none', // Hide scrollbar for Firefox
          '&::-webkit-scrollbar': {
            display: 'none', // Hide scrollbar for Chrome, Safari, Edge
          },
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', marginBottom: 16 }}>
          <IconButton onClick={handleCloseShowApplicationResultsModal}>
            <Close />
          </IconButton>
          <Typography variant="h5" fontWeight="bold">
            Application Results
          </Typography>
        </div>

        {post.remainedSkills.length > 0 || post.remainedQualifications.length > 0 ? (
          <div>
            {post.stateOfApplication ? (
              <Typography variant="subtitle1" fontWeight="bold" mb={2}>
                Your application was saved successfully.
              </Typography>
            ) : (
              <Typography variant="subtitle1" fontWeight="bold" mb={2}>
                Your application couldn't be saved because you need to complete the following:
              </Typography>
            )}

            {post.remainedSkills.length > 0 && (
              <div style={{ marginBottom: 16 }}>
                <Typography variant="subtitle1" fontWeight="bold" mb={1}>
                  Remained Skills:
                </Typography>
                <ul>
                  {post.remainedSkills.map((skill, index) => (
                    <li key={index}>{skill}</li>
                  ))}
                </ul>
              </div>
            )}

            {post.remainedQualifications.length > 0 && (
              <div>
                <Typography variant="subtitle1" fontWeight="bold" mb={1}>
                  Remained Qualifications:
                </Typography>
                <ul>
                  {post.remainedQualifications.map((qual, index) => (
                    <li key={index}>{qual}</li>
                  ))}
                </ul>
              </div>
            )}
          </div>
        ) : (
          <Typography variant="subtitle1" fontStyle="italic">
            No additional information to display.
          </Typography>
        )}
         <Grid item container justifyContent="center" >
                 <Button 
                    onClick={() => handleOpenShowApplicationsModal()}
                    variant="outlined" color="primary">
                      Show Applications
                </Button>
           </Grid>
        <section>
          <ShowApplicationsModal openShowApplicationsModal={openShowApplicationsModal} handleCloseShowApplicationsModal={handleCloseShowApplicationsModal}/>
        </section>
      </Box>
    </Modal>
  );
}
