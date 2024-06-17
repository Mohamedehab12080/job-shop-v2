import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import Slide from "@mui/material/Slide";
import { Edit, Email } from "@mui/icons-material";
import EmailIcon from "@mui/icons-material/Email";
import LanguageIcon from "@mui/icons-material/Language";
import RecentActorsIcon from "@mui/icons-material/RecentActors";
import ConnectWithoutContactIcon from "@mui/icons-material/ConnectWithoutContact";
import { IconButton } from "@mui/material";
import EditeContactsModal from "./EditeContactsModal";

const style = {
  position: "absolute",
  top: "10%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  outline: "none",
  border: "none",
  boxShadow: 24,
  p: 4,
  maxHeight: "60vh",
  borderRadius: 4,
  overflowY: "auto",
  height: "100%",
};

const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none",
  "&::-webkit-scrollbar": {
    display: "none",
  },
};

export default function ContactsModal({
  openContactsModal,
  handleCloseContactsModal,
  contactsList,
  isRequestUser,
}) {
  const [openEditContacts, setOpenEditContacts] = React.useState(false);

  const handleOpenEditContacts = () => setOpenEditContacts(true);
  const handleCloseEditContacts = () => setOpenEditContacts(false);

  return (
    <div>
      <Modal
        open={openContactsModal}
        onClose={handleCloseContactsModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        closeAfterTransition
        BackdropProps={{ timeout: 100 }}
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
            <div className="flex items-center justify-between">
              <Typography id="modal-modal-title" variant="h6" component="h2">
                <ConnectWithoutContactIcon /> Contacts Info
              </Typography>
              {isRequestUser && (
                <div className="modal-content-container flex items-center mb-4">
                  <IconButton
                    onClick={handleOpenEditContacts}
                    aria-label="Edit"
                  >
                    <Edit />
                  </IconButton>
                </div>
              )}
            </div>
            <Typography id="modal-modal-description" sx={{ mt: 2, ml: 3 }}>
              {contactsList && contactsList.length > 0 ? (
                contactsList.map((contact, index) => (
                  <div key={index} className="flex space-x-2">
                    {contact.includes("@") ? (
                      <>
                        <EmailIcon />
                        <a
                          href={`mailto:${contact}`}
                          className="text-xl font-bold"
                        >
                          Email
                        </a>
                      </>
                    ) : contact.includes("http") ? (
                      <>
                        <LanguageIcon />
                        <a
                          href={contact}
                          target="_blank"
                          className="text-xl font-bold"
                        >
                          Website
                        </a>
                      </>
                    ) : (
                      <>
                        <RecentActorsIcon />
                        <p className="text-xl font-bold">Contact: </p>
                        <p className="text-xl font-semibold">{contact}</p>
                      </>
                    )}
                  </div>
                ))
              ) : (
                <p>No contacts available</p>
              )}
            </Typography>
            <section>
              <EditeContactsModal
                openEditeContactsModal={openEditContacts}
                handleCloseEditeContactsModal={handleCloseEditContacts}
                contactsList={contactsList}
              />
            </section>
          </Box>
        </Slide>
      </Modal>
    </div>
  );
}
