import { Close } from "@mui/icons-material";
import { IconButton } from "@mui/material";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import React from "react";

export default function ShowPostImageModal({
  openShowPostImageModal,
  handleCloseShowPostImageModal,
  postImage
}) {
  const [imageDimensions, setImageDimensions] = React.useState({ width: 0, height: 0 });
  const [isHovered, setIsHovered] = React.useState(false);

  React.useEffect(() => {
    const img = new Image();
    img.onload = () => {
      setImageDimensions({ width: img.width, height: img.height });
    };
    img.src = postImage;
  }, [postImage]);

  return (
    <Modal
      open={openShowPostImageModal}
      onClose={handleCloseShowPostImageModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box
        sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: "90vw", // Use viewport width for modal width
          maxWidth: "800px", // Set maximum width for larger screens
          maxHeight: "90vh", // Use viewport height for modal height
          bgcolor: "background.paper",
          border: "none",
          boxShadow: 24,
          p: 4,
          outline: "none",
          borderRadius: 4,
          overflow: "auto", // Enable scrolling
        }}
      >
        <div className="modal-content-container flex items-center justify-between mb-4">
          <div className="flex items-center space-x-1 space-y-1 text-gray-500">
            <IconButton
              onMouseEnter={() => setIsHovered(true)}
              onMouseLeave={() => setIsHovered(false)}
              onClick={handleCloseShowPostImageModal}
            >
              <Close style={{ color: isHovered ? 'red' : 'black' }} />
            </IconButton>
            <p className="font-bold">Image View</p>
          </div>
        </div>
        
        {postImage && (
          <div style={{ textAlign: "center" }}>
            <img
              src={postImage}
              alt="Image"
              style={{
                maxWidth: "100%",
                maxHeight: "70vh", // Limit image height for responsiveness
                objectFit: "contain", // Maintain aspect ratio without cropping
                borderRadius: "4px", // Optional: Apply rounded corners
              }}
            />
          </div>
        )}
      </Box>
    </Modal>
  );
}
