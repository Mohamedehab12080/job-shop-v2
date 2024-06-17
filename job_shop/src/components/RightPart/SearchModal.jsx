import * as React from "react";
import { Avatar, Box, Grid, Modal, Slide, TextField } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { searchUsers } from "../../store/Search/Action";

const modalStyle = {
  position: "absolute",
  top: "10%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "none",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: 4,
  maxHeight: "80vh",
  overflowY: "auto",
};

const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none",
  "&::-webkit-scrollbar": {
    display: "none",
  },
};

export default function SearchModal({
  openShowSearchUsersModal,
  handleCloseShowSearchUsersModal,
  searchValue,
}) {
  const [filteredUsers, setFilteredUsers] = React.useState([]);
  const [filterInputUser, setFilterInputUser] = React.useState("");
  const auth = useSelector((state) => state.auth);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const searchResults = useSelector((state) => state.searchRed);

  const handleFilterUsers = (input) => {
    dispatch(searchUsers(input));
  };

  React.useEffect(() => {
    if (openShowSearchUsersModal) {
      setFilterInputUser(searchValue);
    }
  }, [searchValue, openShowSearchUsersModal]);

  React.useEffect(() => {
    if (openShowSearchUsersModal && searchResults.users) {
      setFilteredUsers(searchResults.users);
    }
  }, [searchResults, openShowSearchUsersModal]);

  return (
    <Modal
      open={openShowSearchUsersModal}
      onClose={handleCloseShowSearchUsersModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Slide
        direction="left"
        in={openShowSearchUsersModal}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 800, exit: 800 }}
        transitionTimingFunction="ease-in-out"
        style={slideStyle}
      >
        <Box sx={modalStyle}>
          <Grid item xs={12}>
            <TextField
              fullWidth
              id="filterUsers"
              name="filterUsers"
              label="Filter Users"
              value={filterInputUser}
              onChange={(e) => handleFilterUsers(e.target.value)}
            />
          </Grid>
          <section>
            {filterInputUser !== "" &&
              filteredUsers.map((user, index) => (
                <div key={index}>
                  <hr />
                  <div className="flex space-x-5">
                    <Avatar
                      onClick={() => {
                        const userType = auth.user.userType;
                        const profileRoute =
                          userType === "jobSeeker"
                            ? `/profile/${user.id}`
                            : userType === "Employer"
                            ? `/employerProfile/${user.id}`
                            : `/companyProfile/${user.id}`;
                        navigate(profileRoute);
                      }}
                      className="cursor-pointer"
                      alt={user.userName}
                      src={user.picture}
                    />
                    <div className="w-full">
                      <div className="flex justify-between items-center">
                        <div className="flex cursor-pointer items-center space-x-2">
                          <span className="font-semibold">{user.userName}</span>
                          <span className="text-gray-600">@{user.email}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
          </section>
        </Box>
      </Slide>
    </Modal>
  );
}
