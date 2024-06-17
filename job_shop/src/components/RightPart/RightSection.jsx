import React, { useEffect, useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import { Brightness4, Close, Search } from "@mui/icons-material";
import {
  Button,
  Grid,
  IconButton,
  MenuItem,
  Slide,
  TextField,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { fetchAllFields } from "../../store/fields/Action";
import { useFormik } from "formik";
import { findFilteredPosts } from "../../store/Post/Action";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import { fetchLocations } from "../../store/location/Action";
import SearchModal from "./SearchModal";
const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::WebkitScrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
const RightSection = () => {
  const auth = useSelector((state) => state.auth);
  const fieldReducer = useSelector((state) => state.fieldReducer);
  const [filterInputFields, setFilterInputField] = useState("");
  var [filteredFields, setFilteredFields] = useState([]);
  const [fields, setFields] = useState([]);
  const [displayedFields, setDisplayedFields] = useState([]);
  const [selectedField, setSelectedField] = useState("");
  const [openSearch, setOpenSearch] = useState(false);
  const handleCloseSearch = () => setOpenSearch(false);
  const handleOpenSearch = () => setOpenSearch(true);
  const [locations, setLocations] = useState([]);
  const locationReducer = useSelector((state) => state.locationReducer);
  const dispatch3 = useDispatch();
  const [openSearchModal, setOpenSearchModal] = useState(false);
  const handleOpenSearchModal = () => setOpenSearchModal(true);
  const handleCloseSearchModal = () => setOpenSearchModal(false);
  const handleRemoveField = () => {
    setSelectedField("");
    formik.setFieldValue("fieldName", "");
  };
  const dispatch = useDispatch();
  const dispatch2 = useDispatch();

  const handleAddField = (value) => {
    setSelectedField(value);
    formik.setFieldValue("fieldName", value);
  };

  React.useEffect(() => {
    const fetchData = async () => {
      try {
        await dispatch3(fetchLocations());
      } catch (error) {
        console.error("Error fetching locations:", error);
      }
    };

    fetchData();
  }, [dispatch3]);

  useEffect(() => {
    let updatedLocations = [...locationReducer.locations];

    // Add the specific value to the locations
    const specificValue = "Cancel";
    updatedLocations.push(specificValue);

    // Set the updated locations to the state
    setLocations(updatedLocations);
  }, [locationReducer.locations]);

  useEffect(() => {
    dispatch(fetchAllFields());
  }, [dispatch]);

  useEffect(() => {
    setFields(fieldReducer.fields);
  }, [fieldReducer.fields]);

  useEffect(() => {
    setDisplayedFields(fields.slice(0, 10));
  }, []);
  const handleFilterFields = (input) => {
    const filtered = fields.filter((field) => {
      return field.toLowerCase().includes(input.toLowerCase());
    });
    setFilteredFields(filtered);
    setFilterInputField(input);
  };
  const handleSubmit = async (values, actions) => {
    if (values.location === "Cancel" || values.location === "") {
      const updatedValues = {
        ...values,
        location: "",
      };
      console.log("values Form : ", updatedValues);
      dispatch2(findFilteredPosts(updatedValues));
    } else {
      console.log("values Form : ", values);
      dispatch2(findFilteredPosts(values));
    }
  };
  const fieldsToDisplay =
    filterInputFields === "" ? displayedFields : filteredFields;
  const formik = useFormik({
    initialValues: {
      title: "",
      location: "",
      employmentType: "",
      createdDate: "",
      companyName: "",
      fieldName: "",
    },
    onSubmit: handleSubmit,
  });
  return (
    <div className="h-screen sticky overflowY top-0">
      <div className="py-5 sticky top">
        <div className="relative flex items-center">
          <input
            onChange={handleOpenSearchModal}
            type="text"
            placeholder="Filter Accounts"
            className="py-3 rounded-full text-gray-500 w-full pl-12"
          />
          <div className="absolute top-0 left-0 pl-3 pt-3">
            <SearchIcon className="text-gray-500" />
          </div>
        </div>
        <section className="ml-5">
          <h1 className="text-xl font-bold"></h1>
        </section>
      </div>

      {auth.user.userType === "jobSeeker" && (
        <>
          <Grid container direction="column" spacing={2}>
            <Grid
              item
              xs={12}
              container
              className="mt-2"
              justifyContent="center"
            >
              {openSearch ? (
                <IconButton
                  onClick={handleCloseSearch}
                  aria-label="show"
                  size="larg"
                >
                  <Search /> <ArrowForwardIosIcon />
                </IconButton>
              ) : (
                <IconButton
                  onClick={handleOpenSearch}
                  aria-label="show"
                  size="larg"
                >
                  <ArrowBackIosIcon /> <Search />
                </IconButton>
              )}
            </Grid>
            <Slide
              direction="left"
              in={openSearch}
              mountOnEnter
              unmountOnExit
              timeout={{ enter: 1200, exit: 1000 }}
              transitiontimingfunction="ease-in-out"
              style={slideStyle}
            >
              <form onSubmit={formik.handleSubmit}>
                <div className="py-5 ml-3">
                  <Grid item xs={12} container justifyContent={"center"}>
                    <TextField
                      fullWidth
                      id="filterFields"
                      name="filterFields"
                      label="Filter Fields"
                      value={filterInputFields}
                      onChange={(e) => handleFilterFields(e.target.value)}
                    />
                  </Grid>
                  {selectedField !== "" && (
                    <Grid
                      item
                      xs={12}
                      container
                      className="mt-2"
                      justifyContent="center"
                    >
                      <span>{selectedField}</span>
                      <IconButton
                        onClick={() => handleRemoveField(selectedField)}
                        aria-label="delete"
                        size="small"
                      >
                        <Close />
                      </IconButton>
                    </Grid>
                  )}
                  <Grid item xs={12} container>
                    <div
                      className="skills-scroll-container sapce-y-2 ml-3 mt-3 "
                      style={{ maxHeight: "200px", overflowY: "auto" }}
                    >
                      {Array.isArray(fieldsToDisplay) &&
                        fieldsToDisplay.length > 0 &&
                        fieldsToDisplay
                          .filter(
                            (field) =>
                              field !== "" && !selectedField.includes(field)
                          )
                          .map((field, index) => (
                            <Button
                              className="m-2"
                              key={index}
                              variant="outlined"
                              onClick={() => handleAddField(field)}
                            >
                              {field}
                            </Button>
                          ))}
                    </div>
                  </Grid>
                  <Grid
                    item
                    xs={12}
                    className="mt-3"
                    container
                    justifyContent="center"
                  >
                    <TextField
                      fullWidth
                      id="title"
                      name="title"
                      label="Title"
                      value={formik.values.title}
                      onChange={formik.handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} className="mt-3">
                    <TextField
                      fullWidth
                      select
                      id="location"
                      name="location"
                      label="Select a location"
                      value={formik.values.location}
                      onChange={formik.handleChange}
                      variant="outlined"
                    >
                      {Array.isArray(locations) &&
                        locations.length > 0 &&
                        locations.map((location, index) => (
                          <MenuItem key={index} value={location}>
                            {location}
                          </MenuItem>
                        ))}
                    </TextField>
                  </Grid>
                  <Grid>
                    
                  </Grid>
                  <div className="py-5">
                    <Grid item container justifyContent="center">
                      <Button type="submit" variant="contained" color="primary">
                        <Search /> Submit
                      </Button>
                    </Grid>
                  </div>
                </div>
              </form>
            </Slide>
            <SearchModal
              openShowSearchUsersModal={openSearchModal}
              handleCloseShowSearchUsersModal={handleCloseSearchModal}
            />
          </Grid>
        </>
      )}
    </div>
  );
};

export default RightSection;
