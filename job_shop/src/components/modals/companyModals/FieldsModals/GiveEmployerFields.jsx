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
  giveEmployerFields,
} from "../../../../store/company/Action";
import { Avatar, Grid, MenuItem, Slide, TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { Formik, useFormik } from "formik";
import { Close } from "@mui/icons-material";
import { IconButton } from "@mui/material";
import MessageModal from "../../../../responses/MessageModal";

const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::-webkit-scrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function GiveEmployerFields({
  openGiveEmployerFields,
  handleCloseGiveEmployerFields,
}) {
  var [filteredEmployers, setFilteredEmployers] = React.useState([]);
  const [filterInputEmployer, setFilterInputEmployer] = React.useState("");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();
  const dispatch3 = useDispatch();
  const dispatch4 = useDispatch();
  const [selectedEmployer, setSelectedEmployer] = React.useState(null);
  const [fetchedCompanyFields, setFetchedCompanyFields] = React.useState([]);
  const comp = useSelector((state) => state.comp);
  const [fetchedEmployers, setFetchedEmployers] = React.useState([]);
  const navigate = useNavigate();
  const [selectedField, setSelectedField] = React.useState([]);
  var [filteredFields, setFilteredFields] = React.useState([]);
  const [filterInputFields, setFilterInputField] = React.useState("");
  const [openMessageModal, setOpenMessageModal] = React.useState(false);
  const handleOpenMessageModal = () => setOpenMessageModal(true);
  const handleCloseMessageModal = () => setOpenMessageModal(false);
  const [responseMessage, setResponseMessage] = React.useState("");
  const [fieldNames, setFieldNames] = React.useState([]);

  const handleFilterEmployers = (input) => {
    const filtered = fetchedEmployers.filter((employer) => {
      if (
        employer.userName.toLowerCase().includes(input.toLowerCase()) ||
        employer.email.toLowerCase().includes(input.toLowerCase)
      ) {
        return employer;
      }
    });
    setFilteredEmployers(filtered);
    setFilterInputEmployer(input);
  };

  const handleRemoveField = (fieldToRemove) => {
    const updatedFields = selectedField.filter(
      (field) => field.id !== fieldToRemove.id
    );

    const objectofSetFields = updatedFields.map((field) => ({
      id: field.id,
      employerId: selectedEmployer.id,
    }));

    if (objectofSetFields) {
      setSelectedField(updatedFields);
      formik.setFieldValue("employerFields", objectofSetFields);
    }
  };
  React.useEffect(() => {
    if (openGiveEmployerFields) {
      dispatch(getEmployers(auth.user.id));
    }
  }, [openGiveEmployerFields, dispatch, auth.user.id]);

  // Add comp as a dependency to useEffect to ensure that the log executes
  // with the updated value.
  React.useEffect(() => {
    if (openGiveEmployerFields) {
      setFetchedEmployers(comp.employers); // Set your fields with the updated value here
    }
  }, [comp, openGiveEmployerFields]);

  React.useEffect(() => {
    if (openGiveEmployerFields) {
      dispatch3(getAllFields(auth.user.id));
    }
  }, [dispatch3, openGiveEmployerFields, auth.user.id]);

  React.useEffect(() => {
    if (openGiveEmployerFields) {
      setFetchedCompanyFields(comp.fields); // Set your fields with the updated value here
    }
  }, [comp, openGiveEmployerFields]);

  const handleAddField = (fieldToAdd) => {
    if (
      !selectedField.some((field) => field.id === fieldToAdd.id) &&
      !fieldNames.includes(fieldToAdd.fieldName) &&
      selectedEmployer !== null
    ) {
      const updatedFields = [...selectedField, fieldToAdd];
      setSelectedField(updatedFields);
      const objectofSetFields = updatedFields.map((field) => ({
        id: field.id,
        employerId: selectedEmployer.id,
      }));

      if (objectofSetFields) {
        formik.setFieldValue("employerFields", objectofSetFields);
      }
    }
  };

  const handleSubmit = async (values) => {
    console.log("values : ", values);
    dispatch4(giveEmployerFields(values));
    console.log("Returned Response: ", comp.response);
    setResponseMessage(comp.response);
    // setResponseMessage();
    setSelectedEmployer(null);
    handleOpenMessageModal();
    setSelectedField([]);
    formik.resetForm();
  };
  const formik = useFormik({
    initialValues: {
      employerFields: [
        {
          id: 0,
          employerId: 0,
        },
      ],
    },
    onSubmit: handleSubmit,
  });
  const handleDeleteEmployer = (employerId) => {
    // console.log("Field For Delete : ",fieldId)
    dispatch2(deleteEmployer(employerId));
    setFetchedEmployers((prevEmps) =>
      prevEmps.filter((emp) => emp.id !== employerId)
    );
  };
  const employersToDisplay =
    filterInputEmployer === "" ? fetchedEmployers : filteredEmployers;
  const fieldsToDisplay1 =
    filterInputFields === "" ? fetchedCompanyFields : filteredFields;
  const [fieldsToDisplay, setFieldsToDisplay] = React.useState([]);
  React.useEffect(() => {
    setFieldsToDisplay(fieldsToDisplay1);
  }, [fieldsToDisplay1]);

  const handleSelecteEmployer = (value) => {
    console.log("Field Names : ", value.fieldsNames);
    if (
      value !== null &&
      value.fieldsNames &&
      Array.isArray(value.fieldsNames)
    ) {
      setSelectedEmployer(value);
      setFieldNames(value.fieldsNames);
      const newFieldsToDisplay = fieldsToDisplay.filter(
        (field) => !value.fieldsNames.includes(field.fieldName)
      );
      setFieldsToDisplay(newFieldsToDisplay);
      console.log("NEW FIELDS TO DISPLAY : ", fieldsToDisplay);
    } else {
      // Handle cases where value or value.fieldNames might be undefined or not an array
      console.error("Invalid value or fieldNames:", value);
    }
    // setFilteredEmployers(value);
  };

  const handleFilterFields = (input) => {
    if (fieldNames.length > 0) {
      const lowerCaseFieldNames = fieldNames.map((name) => name.toLowerCase());
      const filtered = fetchedCompanyFields.filter((field) => {
        const lowerCaseFieldName = field.fieldName.toLowerCase();
        const lowerCaseInput = input.toLowerCase();
        // Check if input is included in fieldName and if fieldName is not in fieldNames array
        return (
          lowerCaseFieldName.includes(lowerCaseInput) &&
          !lowerCaseFieldNames.includes(lowerCaseFieldName)
        );
      });
      setFilteredFields(filtered);
      setFilterInputField(input);
    } else {
      const filtered = fetchedCompanyFields.filter((field) => {
        const lowerCaseFieldName = field.fieldName.toLowerCase();
        const lowerCaseInput = input.toLowerCase();
        // Check if input is included in fieldName and if fieldName is not in fieldNames array
        return lowerCaseFieldName.includes(lowerCaseInput);
      });
      setFilteredFields(filtered);
      setFilterInputField(input);
    }
  };
  const handleCancelSelecteEmployer = () => {
    setSelectedEmployer(null);
    setFieldNames([]);
  };

  return (
    <div>
      <Modal
        open={openGiveEmployerFields}
        onClose={handleCloseGiveEmployerFields}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Slide
          direction="left"
          in={openGiveEmployerFields}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 800, exit: 800 }}
          transitionTimingFunction="ease-in-out"
          style={slideStyle}
        >
          <Box
            sx={{
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
            <form onSubmit={formik.handleSubmit}>
              <Grid item container justifyContent="center">
                <Button type="submit" variant="contained" color="primary">
                  Save
                </Button>
              </Grid>

              <section>
                {selectedEmployer !== null ? (
                  <>
                    <div>
                      <hr></hr>
                    </div>
                    <div key={selectedEmployer.id} className="flex space-x-5">
                      <Avatar
                        onClick={() =>
                          navigate(`/employerProfile/${selectedEmployer.id}`)
                        }
                        className="cursor-pointer"
                        alt="userName"
                        src={selectedEmployer.picture}
                      />
                      <div className="w-full">
                        <div className="flex justify-between items-center">
                          <div className="flex cursor-pointer items-center space-x-2">
                            <span className="font-semibold">
                              {selectedEmployer.userName}
                            </span>
                            <span className="text-gray-600">
                              @{selectedEmployer.email}
                            </span>
                          </div>

                          <div>
                            <Button
                              id="basic-button"
                              onClick={() => handleCancelSelecteEmployer()}
                            >
                              Diselect
                            </Button>
                          </div>
                        </div>
                        <div className="flex">
                          <p className="font-semibold text-gray-500">
                            {" "}
                            Fields :
                          </p>
                          <ul>
                            {Array.isArray(selectedEmployer.fieldsNames) &&
                            selectedEmployer.fieldsNames &&
                            selectedEmployer.fieldsNames.length > 0 ? (
                              selectedEmployer.fieldsNames.map(
                                (empField, index) => (
                                  <>
                                    <li
                                      key={index}
                                      className="ml-2 text-gray-600"
                                    >
                                      - {empField}
                                    </li>
                                  </>
                                )
                              )
                            ) : (
                              <>Nooo</> // Placeholder for rendering when emp.employerFields is empty
                            )}
                          </ul>
                        </div>
                      </div>
                    </div>
                  </>
                ) : (
                  <>
                    {Array.isArray(employersToDisplay) &&
                    employersToDisplay.length > 0 ? (
                      employersToDisplay.map((emp, index) => (
                        <>
                          <div>
                            <hr></hr>
                          </div>
                          <div key={index} className="flex space-x-5">
                            <Avatar
                              onClick={() => navigate(`/profile/${emp.id}`)}
                              className="cursor-pointer"
                              alt="userName"
                              src={emp.picture}
                            />
                            <div className="w-full">
                              <div className="flex justify-between items-center">
                                <div className="flex cursor-pointer items-center space-x-2">
                                  <span className="font-semibold">
                                    {emp.userName}
                                  </span>
                                  <span className="text-gray-600">
                                    @{emp.email}
                                  </span>
                                </div>

                                <div>
                                  <Button
                                    id="basic-button"
                                    onClick={() => handleSelecteEmployer(emp)}
                                  >
                                    Select
                                  </Button>
                                </div>
                              </div>
                              <div className="flex">
                                <p className="font-semibold text-gray-500">
                                  {" "}
                                  Fields :
                                </p>
                                <ul>
                                  {emp.fieldsNames &&
                                  emp.fieldsNames.length > 0 ? (
                                    emp.fieldsNames.map((empField, index) => (
                                      <>
                                        <li
                                          key={index}
                                          className="text-gray-600"
                                        >
                                          - {empField}
                                        </li>
                                      </>
                                    ))
                                  ) : (
                                    <>Nooo</> // Placeholder for rendering when emp.employerFields is empty
                                  )}
                                </ul>
                              </div>
                            </div>
                          </div>
                        </>
                      ))
                    ) : (
                      <div>No employers available.</div>
                    )}
                  </>
                )}
              </section>
              <section>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    id="filterFields"
                    name="filterFields"
                    label="Filter Fields"
                    value={filterInputFields}
                    onChange={(e) => handleFilterFields(e.target.value)}
                  />
                </Grid>
                <Grid item xs={12}>
                  <div className="selected-skills-container space-x-2 mt-2">
                    <ul className="flex flex-wrap">
                      {selectedField.length > 0 ? (
                        selectedField.map((field, index) => (
                          <div key={index} className="selected-skill">
                            <li key={index}>
                              <span>{field.fieldName}</span>
                              <IconButton
                                onClick={() => handleRemoveField(field)}
                                aria-label="delete"
                                size="small"
                              >
                                <Close />
                              </IconButton>
                            </li>
                          </div>
                        ))
                      ) : (
                        <div className="error-message">
                          At least one field is required
                        </div>
                      )}
                    </ul>
                  </div>
                </Grid>
                <Grid item xs={12} container>
                  <div
                    className="skills-scroll-container mt-4"
                    style={{
                      maxHeight: "200px",
                      overflowY: "auto",
                      padding: "8px",
                      border: "1px solid #ddd",
                      borderRadius: "4px",
                    }}
                  >
                    {Array.isArray(fieldsToDisplay) &&
                      fieldsToDisplay.length > 0 &&
                      fieldsToDisplay
                        .filter(
                          (field) =>
                            !selectedField.some(
                              (selected) => selected.id === field.id
                            )
                        )
                        .map((field) => (
                          <Button
                            key={field.id}
                            variant="outlined"
                            style={{
                              marginBottom: "8px",
                              textAlign: "left",
                              width: "100%",
                              display: "flex",
                              flexDirection: "column",
                              alignItems: "flex-start",
                              padding: "12px",
                              boxShadow: "0px 1px 3px rgba(0, 0, 0, 0.2)",
                            }}
                            onClick={() => handleAddField(field)}
                          >
                            <div
                              className="ml-2"
                              style={{ fontWeight: "bold" }}
                            >
                              {field.fieldName}
                            </div>

                            <ul
                              className="flex flex-wrap space-x-3 mt-3"
                              style={{
                                marginTop: "8px",
                                paddingLeft: "16px",
                                listStyleType: "disc",
                              }}
                            >
                              {field.jobs && field.jobs.length > 0 ? (
                                field.jobs.map((job, index) => (
                                  <div className="flex" key={index}>
                                    <li>- {job}</li>
                                  </div>
                                ))
                              ) : (
                                <div style={{ color: "#999" }}>No Jobs</div>
                              )}
                            </ul>
                          </Button>
                        ))}
                  </div>
                </Grid>
              </section>
            </form>
            <section>
              <MessageModal
                openMessageModal={openMessageModal}
                handleCloseMessageModal={handleCloseMessageModal}
                response={responseMessage}
                Title={"Give Employer Fields"}
              />
            </section>
          </Box>
        </Slide>
      </Modal>
    </div>
  );
}
