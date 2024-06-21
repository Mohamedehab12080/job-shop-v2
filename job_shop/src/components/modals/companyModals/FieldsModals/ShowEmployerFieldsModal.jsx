import * as React from "react";
import {
  Box,
  Typography,
  Modal,
  Grid,
  Slide,
  TextField,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { getEmployerFields } from "../../../../store/company/Employer/Action";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { styled } from "@mui/material/styles";

const SlideStyle = styled(Slide)(({ theme }) => ({
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::-webkit-scrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
}));

const ModalBox = styled(Box)(({ theme }) => ({
  position: "absolute",
  top: "10%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "90%",
  maxWidth: 600,
  bgcolor: "background.paper",
  border: "none",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: 4,
  maxHeight: "95vh",
  overflowY: "auto",
}));

const FieldContainer = styled("div")(({ theme }) => ({
  marginBottom: theme.spacing(3),
  padding: theme.spacing(2),
  backgroundColor: theme.palette.background.default,
  borderRadius: theme.shape.borderRadius,
  boxShadow: theme.shadows[1],
}));

const FieldName = styled(Typography)(({ theme }) => ({
  fontWeight: theme.typography.fontWeightBold,
  fontSize: theme.typography.h6.fontSize,
}));

const FieldJobs = styled(Typography)(({ theme }) => ({
  marginLeft: theme.spacing(4),
}));

const NoJobs = styled(Typography)(({ theme }) => ({
  color: theme.palette.text.disabled,
  fontStyle: "italic",
}));

export default function ShowEmployerFieldsModal({
  openShowEmployerFieldsModal,
  handleCloseShowEmployerFields,
  isRequestUser,
  userId,
}) {
  const [filteredFields, setFilteredFields] = React.useState([]);
  const [filterInputField, setFilterInputField] = React.useState("");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const emp = useSelector((state) => state.emp);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [fetchedFields, setFetchedFields] = React.useState([]);

  const handleFilterFields = (input) => {
    const filtered = fetchedFields.filter((field) =>
      field.fieldName.toLowerCase().includes(input.toLowerCase())
    );
    setFilteredFields(filtered);
    setFilterInputField(input);
  };

  const handleEditField = (fieldId) => {
    // Handle Edit open the createFieldModal and make it for edit
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  React.useEffect(() => {
    if (openShowEmployerFieldsModal && isRequestUser) {
      dispatch(getEmployerFields(auth.user.id));
    } else if (openShowEmployerFieldsModal) {
      dispatch(getEmployerFields(userId));
    }
  }, [openShowEmployerFieldsModal, dispatch, auth.user.id, userId]);

  React.useEffect(() => {
    if (openShowEmployerFieldsModal && emp.fields) {
      setFetchedFields(emp.fields);
    }
  }, [emp.fields, openShowEmployerFieldsModal]);

  return (
    <Modal
      open={openShowEmployerFieldsModal}
      onClose={handleCloseShowEmployerFields}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <SlideStyle
        direction="up"
        in={openShowEmployerFieldsModal}
        mountOnEnter
        unmountOnExit
        timeout={{ enter: 1000, exit: 1000 }}
        transitionTimingFunction="ease-in-out"
      >
        <ModalBox>
          <Grid item xs={12} mb={2}>
            <TextField
              fullWidth
              sx={{
                backgroundColor: "lightblue", // Example color
                "& .MuiOutlinedInput-root": {
                  "& fieldset": {
                    borderColor: "transparent",
                  },
                  "&:hover fieldset": {
                    borderColor: "blue",
                  },
                  "&.Mui-focused fieldset": {
                    borderColor: "blue",
                  },
                },
              }}
              id="filterFields"
              name="filterFields"
              label="Filter Fields"
              value={filterInputField}
              onChange={(e) => handleFilterFields(e.target.value)}
              variant="outlined"
            />
          </Grid>
          <div>
            <hr />
          </div>
          <section>
            {(filterInputField === "" ? fetchedFields : filteredFields).map(
              (field) => (
                <FieldContainer key={field.id}>
                  <div className="flex justify-between items-center">
                    <div className="cursor-pointer items-center space-x-2">
                      <FieldName>{field.fieldName}</FieldName>
                      <IconButton
                        aria-label="more"
                        aria-controls="long-menu"
                        aria-haspopup="true"
                        onClick={handleClick}
                      >
                        <MoreHorizIcon />
                      </IconButton>
                      <Menu
                        id="long-menu"
                        anchorEl={anchorEl}
                        keepMounted
                        open={Boolean(anchorEl)}
                        onClose={() => setAnchorEl(null)}
                      >
                        <MenuItem onClick={() => handleEditField(field.id)}>
                          Edit
                        </MenuItem>
                      </Menu>
                    </div>
                  </div>
                  <div className="mt-2">
                    <div className="mt-2 flex items-center space-x-2 overflow-auto">
                      <div className="flex flex-col ml-3">
                        {field.companyFieldJobDTOs.length > 0 ? (
                          <>
                            <FieldJobs>* Field Jobs:</FieldJobs>
                            <ul className="list-disc pl-5 ml-5">
                              {field.companyFieldJobDTOs.map((job, index) => (
                                <li key={index} className="mt-1">
                                  {job.jobName}
                                </li>
                              ))}
                            </ul>
                          </>
                        ) : (
                          <NoJobs>No Jobs available</NoJobs>
                        )}
                      </div>
                    </div>
                    <div>
                      <hr />
                    </div>
                  </div>
                </FieldContainer>
              )
            )}
          </section>
        </ModalBox>
      </SlideStyle>
    </Modal>
  );
}
