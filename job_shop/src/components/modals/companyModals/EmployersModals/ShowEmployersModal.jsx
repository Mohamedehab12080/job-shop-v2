import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useDispatch, useSelector } from "react-redux";
import {
  deleteEmployer,
  deleteField,
  getAllFields,
  getEmployers,
} from "../../../../store/company/Action";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Avatar, Grid, MenuItem, TextField } from "@mui/material";
import Menu from "@mui/material/Menu";
import { useNavigate } from "react-router-dom";

export default function ShowEmployerModal({
  openShowEmployerModal,
  handleCloseShowEmployerModal,
  create,
  employerEmail,
}) {
  var [filteredEmployers, setFilteredEmployers] = React.useState([]);
  const [filterInputEmployer, setFilterInputField] = React.useState("");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();
  const comp = useSelector((state) => state.comp);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openMenu = Boolean(anchorEl);
  const [fetchedEmployers, setFetchedEmployers] = React.useState([]);
  const navigate = useNavigate();

  const handleFilterEmployers = (input) => {
    const filtered = fetchedEmployers.filter((employer) => {
      return employer.userName.toLowerCase().includes(input.toLowerCase());
    });
    setFilteredEmployers(filtered);
    setFilterInputField(input);
  };

  const handleEditEmployer = (fieldId) => {
    /// Handle Edit open the createFieldModal and make it for edit
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  React.useEffect(() => {
    if (create) {
    }
  }, [create]);
  React.useEffect(() => {
    if (openShowEmployerModal) {
      dispatch(getEmployers(auth.user.id));
    }
  }, [openShowEmployerModal, dispatch, auth.user.id]);

  // Add comp as a dependency to useEffect to ensure that the log executes
  // with the updated value.
  React.useEffect(() => {
    if (openShowEmployerModal) {
      setFetchedEmployers(comp.employers); // Set your fields with the updated value here
    }
  }, [comp, openShowEmployerModal]);

  const handleDeleteEmployer = (employerId) => {
    // console.log("Field For Delete : ",fieldId)
    dispatch2(deleteEmployer(employerId));
    setFetchedEmployers((prevEmps) =>
      prevEmps.filter((emp) => emp.id !== employerId)
    );
  };

  return (
    <div>
      <Modal
        open={openShowEmployerModal}
        onClose={handleCloseShowEmployerModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 700,
            bgcolor: "background.paper",
            border: "none",
            boxShadow: 24,
            p: 4,
            outline: "none",
            borderRadius: 4,
            maxHeight: "80vh",
            overflowY: "auto",
          }}
        >
          <Grid item xs={12}>
            <TextField
              fullWidth
              id="filterEmployers"
              name="filterEmployers"
              label="Filter Employers"
              value={filterInputEmployer}
              onChange={(e) => handleFilterEmployers(e.target.value)}
            />
          </Grid>
          <section>
            {(filterInputEmployer === "" ? fetchedEmployers : filteredEmployers)
              // Ensure the newly created employer appears first
              .sort((a, b) => {
                if (create && a.email === employerEmail) return -1;
                if (create && b.email === employerEmail) return 1;
                return 0;
              })
              .map((emp, index) => (
                <>
                  <div>
                    <hr></hr>
                  </div>
                  <div
                    key={index}
                    className={`flex space-x-5 ${
                      create && emp.email === employerEmail
                        ? "bg-yellow-100"
                        : ""
                    }`}
                  >
                    <Avatar
                      onClick={() => navigate(`/employerProfile/${emp.id}`)}
                      className="cursor-pointer"
                      alt="userName"
                      src={emp.picture}
                    />
                    <div className="w-full">
                      <div className="flex justify-between items-center">
                        <div className="flex cursor-pointer items-center space-x-2">
                          <span className="font-semibold">{emp.userName}</span>
                          <span className="text-gray-600">@{emp.email}</span>
                        </div>

                        <div>
                          <Button
                            id="basic-button"
                            aria-controls={openMenu ? "basic-menu" : undefined}
                            aria-haspopup="true"
                            aria-expanded={openMenu ? "true" : undefined}
                            onClick={handleClick}
                          >
                            <MoreHorizIcon />
                          </Button>
                          <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={openMenu}
                            onClose={handleClose}
                            MenuListProps={{
                              "aria-labelledby": "basic-button",
                            }}
                          >
                            <MenuItem
                              onClick={() => handleDeleteEmployer(emp.id)}
                            >
                              Delete
                            </MenuItem>
                            <MenuItem
                              onClick={() => handleEditEmployer(emp.id)}
                            >
                              Edit
                            </MenuItem>
                          </Menu>
                        </div>
                      </div>
                      <div className="flex">
                        <p className="font-semibold text-gray-500"> Fields :</p>
                        <ul className="flex flex-wrap">
                          {emp.fieldsNames && emp.fieldsNames.length > 0 ? (
                            emp.fieldsNames.map((empField, index) => (
                              <li key={index}>
                                <p className="ml-2 text-gray-600">
                                  - {empField}
                                </p>
                              </li>
                            ))
                          ) : (
                            <>Nooo</> // Placeholder for rendering when emp.employerFields is empty
                          )}
                        </ul>
                      </div>
                    </div>
                  </div>
                </>
              ))}
          </section>
        </Box>
      </Modal>
    </div>
  );
}
