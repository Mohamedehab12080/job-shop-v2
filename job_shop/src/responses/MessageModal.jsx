import React, { useEffect } from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import Slide from "@mui/material/Slide";
import BusinessCenterIcon from "@mui/icons-material/BusinessCenter";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ErrorIcon from "@mui/icons-material/Error";
import { useState } from "react";

const style = (state) => ({
  position: "absolute",
  top: "10%",
  left: "0%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  color: "black",
  border: "none",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: 4,
  maxHeight: "20vh",
  overflowY: "auto",
});

const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none",
  "&::WebkitScrollbar": {
    display: "none",
  },
};

export default function MessageModal({
  openMessageModal,
  handleCloseMessageModal,
  response,
  Title,
  state,
}) {
  const [isOpen, setIsOpen] = useState(openMessageModal);

  useEffect(() => {
    setIsOpen(openMessageModal);
  }, [openMessageModal]);

  useEffect(() => {
    if (isOpen) {
      const timer = setTimeout(() => {
        handleCloseMessageModal();
      }, 3000); // Adjust the timeout duration as needed
      return () => clearTimeout(timer);
    }
  }, [isOpen, handleCloseMessageModal]);

  return (
    <div>
      <Modal
        open={openMessageModal}
        onClose={handleCloseMessageModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        closeAfterTransition
      >
        <Slide
          direction="down"
          in={openMessageModal}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 500, exit: 500 }}
          transitiontimingfunction="ease-in-out"
          style={slideStyle}
        >
          <Box sx={style(state)}>
            <Typography id="modal-modal-title" variant="h6" component="h2">
              <div style={{ display: "flex", alignItems: "center" }}>
                {/* {state && response.toLowercase().includes("success") ? (
                  <CheckCircleIcon style={{ marginRight: 8, color: "green" }} />
                ) : (
                  <ErrorIcon style={{ marginRight: 8, color: "red" }} />
                )} */}
                {Title === "Education" && (
                  <BusinessCenterIcon
                    style={{ color: "inherit", marginRight: 8 }}
                  />
                )}
                {Title}
              </div>
            </Typography>
            <Typography id="modal-modal-description" sx={{ mt: 2, ml: 3 }}>
              {response}
            </Typography>
          </Box>
        </Slide>
      </Modal>
    </div>
  );
}
