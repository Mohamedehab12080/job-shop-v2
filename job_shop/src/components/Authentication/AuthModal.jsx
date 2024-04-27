import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import SigninForm from './SigninForm';
import { useLocation, useNavigate } from 'react-router-dom';
import SignupForm from './SignupForm';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 4,
  borderRadius: 4,
  outline: "none"
};

const contentStyle = {
  maxHeight: 600, // Set the max height you want for the modal content
  overflowY: 'auto', // Enable vertical scrolling when content exceeds maxHeight
};

export default function AuthModal({
  openAuthModal,
  handleClose
}) {

  const location = useLocation()
  const navigate = useNavigate();
  const handleNavigate = () => {
    const path = location.pathname === "/signup" ? "/signin" : "/signup"
    navigate(path)
  }
  return (
    <div>

      <Modal
        open={openAuthModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className="items-center" style={contentStyle}> {/* Wrap the content inside a div with fixed height and overflow */}
            <h1 className='text-2xl py-9 text-center'>{location.pathname === "/signup" ? "Create Your Account" : "Login"}</h1>
            {location.pathname === "/signup" ? <SignupForm /> : <SigninForm />}
            <h1 className='text-center py-3 font-semibold text-lg text-gray-500'>
              {location.pathname === "/signup" ? "Already have account" : "If you don't have account"}
            </h1>

            <Button
              fullWidth
              variant='outlined'
              onClick={handleNavigate}
              sx={{ borderRadius: "29px", py: "15px" }}
            > {location.pathname === "/signup" ? "signin" : "signup"}</Button>
          </div>
        </Box>
      </Modal>
    </div>
  );
}
