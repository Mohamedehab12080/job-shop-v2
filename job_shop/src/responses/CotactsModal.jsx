import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Slide from '@mui/material/Slide';
import { CheckCircle, Email } from '@mui/icons-material';
import BussinessCetnerIcon from '@mui/icons-material/BusinessCenter'
import EmailIcon from '@mui/icons-material/Email';
import LanguageIcon from '@mui/icons-material/Language';
import RecentActorsIcon from '@mui/icons-material/RecentActors';
import ConnectWithoutContactIcon from '@mui/icons-material/ConnectWithoutContact';
const style = {
  position: 'absolute',
  top: '10%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  outline:'none',
  border: 'none',
  boxShadow: 24,
  p: 4,
  maxHeight: '60vh',
  borderRadius:4,
  overflowY: 'auto',
  height: '100%',  
};

const slideStyle = {
    height: '100%',
    overflowY: 'auto',
    scrollbarWidth: 'none', // Hide scrollbar for Firefox
    '&::-webkit-scrollbar': {
      display: 'none', // Hide scrollbar for Chrome, Safari, Edge
    },
  };
  
export default function ContactsModal({ 
    openContactsModal,
     handleCloseContactsModal,
      contactsList,
      isRequestUser}) {
   
   
    return (
      <div>
        <Modal
          open={openContactsModal}
          onClose={handleCloseContactsModal}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
          closeAfterTransition  // This ensures the modal closes only after transition
          BackdropProps={{ timeout: 100 }} // Adjust timeout as needed
        >
          <Slide 
            direction="left"
            in={openContactsModal}
            mountOnEnter
            unmountOnExit
            timeout={{ enter: 500, exit: 300 }}
            transitionTimingFunction="ease-in-out" 
            style={slideStyle}
          >
            <Box sx={style}>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                <ConnectWithoutContactIcon/> Contacts Info
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2, ml: 3 }}>
            {contactsList && contactsList.length > 0 ? (
                <>
                {contactsList.map((contact) => (
                    <div key={contact}> {/* Add a unique key for each mapped item */}
                    {contact.includes("@") || contact.includes("http") ? (
                        <>
                        {contact.includes("@") ? (
                            <div className='flex space-x-2'>
                            <EmailIcon />
                            <a href={`mailto:${contact}`} className='text-xl font-bold'>Email</a>
                            </div>
                        ) : (
                            <div className='flex space-x-2'>
                            <LanguageIcon />
                            <a href={contact} className='text-xl font-bold'>Website</a>
                            </div>
                        )}
                        </>
                    ) : (
                        <div className='flex space-x-2'>
                            <RecentActorsIcon />
                            <p className='text-xl font-bold'>Contact: </p>
                            <p className='text-xl font-semibold'>{contact}</p>
                        </div>
                    )}
                    </div>
                ))}
                </>
            ) : (
                <></> 
            )}
</Typography>

            </Box>
          </Slide>
        </Modal>
      </div>
    );
  }
  