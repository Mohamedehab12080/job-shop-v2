import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import {
  Grid,
  IconButton,
  InputAdornment,
  InputLabel,
  MenuItem,
  Select,
  Slide,
} from "@mui/material";
import {
  Close,
  GridView,
  Visibility,
  VisibilityOff,
} from "@mui/icons-material";
import logo from "../common/images/myPic.jpg";
import { Avatar } from "@mui/material";
import TextField from "@mui/material/TextField";
import "./ProfileModal.css";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSpinner,
  faCheckCircle,
  faExclamationCircle,
  faCloudUploadAlt,
} from "@fortawesome/free-solid-svg-icons";
import { library } from "@fortawesome/fontawesome-svg-core";
import { uploadToCloudnary } from "../../Utils/UploadToCloudnary.";
import { useDispatch } from "react-redux";
import {
  AddCircleOutline as AddCircleOutlineIcon,
  RemoveCircleOutline as RemoveCircleOutlineIcon,
} from "@mui/icons-material";
import * as Yup from "yup";
import CloseIcon from "@mui/icons-material/Close";
import { updateProfile } from "../../store/JobSeeker/Action";
import { updateCompanyProfile } from "../../store/company/Action";
import { updateEmployerProfile } from "../../store/company/Employer/Action";

const style = {
  position: "absolute",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "none",
  boxShadow: 24,
  outline: "none",
  p: 4,
  borderRadius: 4,
  maxHeight: "110vh",
  overflowY: "auto",
  height: "100%", // Ensure modal takes up full height for scrolling
};
const slideStyle = {
  height: "100%",
  overflowY: "auto",
  scrollbarWidth: "none", // Hide scrollbar for Firefox
  "&::WebkitScrollbar": {
    display: "none", // Hide scrollbar for Chrome, Safari, Edge
  },
};
export default function ProfileModal({ open, handleClose, data, Type }) {
  // const [open, setOpen] = React.useState(false);
  const [uploading, setUploading] = React.useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadStatus, setUploadStatus] = useState(null);
  const [uploadProgress2, setUploadProgress2] = useState(0);
  const [uploadStatus2, setUploadStatus2] = useState(null); // 'uploading', 'success', 'error'
  const [imageUrl, setImageUrl] = useState("");
  const [imageUrl2, setImageUrl2] = useState("");
  const [selectedImage, setSelectedImage] = useState("");
  const [selectedImage2, setSelectedImage2] = useState("");

  const [uploadingImage, setUploadingImage] = useState(false);
  const [uploadingImage2, setUploadingImage2] = useState(false);

  const [userName, setUsername] = useState("");
  const [address, setAddress] = useState("");
  const [employmentState, setEmploymentState] = useState("");
  const [education, setEducation] = useState("");
  const [description, setDescription] = useState("");
  const [experience, setExperience] = useState("");
  const [gender, setGender] = useState("");
  const [stringDate, setStringDate] = useState("");
  const [companyName, setCompanyName] = useState("");
  const dispatch = useDispatch();
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const [preview, setPreview] = useState("");
  const [preview2, setPreview2] = useState("");

  React.useEffect(() => {
    if (data !== null && open) {
      setPreview(data.picture);
      setPreview2(data.coverImage);
      setUsername(data.userName);
      setAddress(data.address);
      setDescription(data.description);
      setGender(data.gender);
      console.log("Password : ", data.password);
      if (Type === "jobSeeker") {
        setExperience(data.experience);
        setEmploymentState(data.employmentState);
        setEducation(data.education);
        setStringDate(data.birthDate);
      } else if (Type === "Admin") {
        setCompanyName(data.companyName);
      }
    }
  }, [data, open, Type]);

  React.useEffect(() => {
    if (data !== null && open) {
      formik.setFieldValue("coverImage", preview2 || "");
      formik.setFieldValue("description", description || "");
      formik.setFieldValue("userName", userName || "");
      formik.setFieldValue("address", address || "");
      formik.setFieldValue("picture", preview || "");
      formik.setFieldValue("gender", gender || "");
      if (Type === "jobSeeker") {
        formik.setFieldValue("employmentState", employmentState || "");
        formik.setFieldValue("experience", experience || "");
        formik.setFieldValue("education", education || "");
        formik.setFieldValue("birthDate", {
          ...formik.values.birthDate,
          ["year"]: stringDate.substring(0, 4),
          ["month"]: stringDate.substring(5, 7),
          ["day"]: stringDate.substring(8, 10),
        });
      } else if (Type === "Admin") {
        formik.setFieldValue("companyName", companyName || "");
      }
    }
  }, [userName, address, experience, description, education]);
  const handleFileUpload = (e) => {
    setUploadProgress(0); // Reset progress when a new file is selected
    setUploadStatus(null); // Reset status
    const file = e.target.files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
      setPreview(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const handleFileUpload2 = (e) => {
    setUploadProgress2(0); // Reset progress when a new file is selected
    setUploadStatus2(null); // Reset status
    const file = e.target.files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
      setPreview2(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const handleSelectImage = async () => {
    try {
      if (!preview) {
        return;
      } else if (data !== null && preview !== data.picture) {
        setUploadingImage(true);
        setUploadStatus("uploading");
        const imgUrl = await uploadToCloudnary(preview, {
          onUploadProgress: (progressEvent) => {
            const progress = Math.round(
              (progressEvent.loaded / progressEvent.total) * 100
            );
            setUploadProgress(progress);
          },
        });

        setImageUrl(imgUrl);
        console.log("image url : ", imgUrl);
        setSelectedImage(imgUrl);
        setUploadingImage(false);
        setUploadStatus("success");
        return imgUrl; // Set status to 'success' after successful upload
      } else {
        setUploadingImage(true);
        setUploadStatus("uploading");
        setImageUrl(preview);
        console.log("image url : ", preview);
        setSelectedImage(preview);
        setUploadingImage(false);
        setUploadStatus("success");
        return preview;
      }
    } catch (error) {
      console.error("Error uploading file:", error);
      setUploadStatus("error"); // Set status to 'error' if upload fails
    }
  };
  const handleSelectImage2 = async () => {
    try {
      if (!preview2 || preview2 === "") {
        return;
      } else if (data !== null && preview2 !== data.coverImage) {
        setUploadingImage2(true);
        setUploadStatus2("uploading");
        const imgUrl = await uploadToCloudnary(preview2, {
          onUploadProgress: (progressEvent) => {
            const progress = Math.round(
              (progressEvent.loaded / progressEvent.total) * 100
            );
            setUploadProgress2(progress);
          },
        });

        setImageUrl2(imgUrl);
        console.log("image url2 : ", imgUrl);
        setSelectedImage2(imgUrl);
        setUploadingImage2(false);
        setUploadStatus2("success");
        return imgUrl; // Set status to 'success' after successful upload
      } else {
        setUploadingImage2(true);
        setUploadStatus2("uploading");
        setImageUrl2(preview2);
        console.log("image url : ", preview2);
        setSelectedImage2(preview2);
        setUploadingImage2(false);
        setUploadStatus2("success");
        return preview2;
      }
    } catch (error) {
      console.error("Error uploading file:", error);
      setUploadStatus2("error"); // Set status to 'error' if upload fails
    }
  };
  const handleSubmit = async (values) => {
    const imageUrl = await handleSelectImage();
    const imageUrl2 = await handleSelectImage2();
    if (imageUrl !== "" && imageUrl2 !== "") {
      const updatedValues = {
        ...values,
        picture: imageUrl,
        coverImage: imageUrl2,
      };
      const { day, month, year } = updatedValues.birthDate;

      if (data !== null) {
        console.log("Data From job Seeker Data ", data.birthDate);
      }
      const birthDate = `${year}-${month}-${day}`;
      updatedValues.birthDate = birthDate;
      const formData = {
        ...updatedValues,
        contacts: updatedValues.contacts.filter((contact) => contact !== ""),
      };
      console.log("form data : ", formData);
      // Dispatch action based on userType
      if (Type === "jobSeeker") {
        dispatch(updateProfile(formData));
      } else if (Type === "company") {
        dispatch(updateCompanyProfile(formData));
      } else {
        dispatch(updateEmployerProfile(formData));
      }
    } else {
      if (Type === "jobSeeker") {
        const { day, month, year } = values.birthDate;

        if (data !== null) {
          console.log("Data From job Seeker Data ", data.birthDate);
        }
        const birthDate = `${year}-${month}-${day}`;
        values.birthDate = birthDate;
      }
      const formData = {
        ...values,
        contacts: values.contacts.filter((contact) => contact !== ""),
      };
      console.log("form data : ", formData);
      // Dispatch action based on userType
      if (Type === "jobSeeker") {
        dispatch(updateProfile(formData));
      } else if (Type === "company") {
         dispatch(updateCompanyProfile(formData));
      } else {
         dispatch(updateEmployerProfile(formData));
      }
    }
  };

  const renderIcon = () => {
    if (uploadStatus === "uploading") {
      return <FontAwesomeIcon icon="spinner" spin />; // Display spinner icon while uploading
    } else if (uploadStatus === "success") {
      return <FontAwesomeIcon icon="check-circle" />; // Display checkmark icon on successful upload
    } else if (uploadStatus === "error") {
      return <FontAwesomeIcon icon="exclamation-circle" />; // Display error icon if upload fails
    } else {
      return <FontAwesomeIcon icon="cloud-upload-alt" />; // Default upload icon
    }
  };

  const currentYear = new Date().getFullYear();
  const years = Array.from({ length: 100 }, (_, i) => currentYear - i);
  const days = Array.from({ length: 31 }, (_, i) => i + 1);
  const months = [
    { value: "01", label: "January" },
    { value: "02", label: "February" },
    { value: "03", label: "March" },
    { value: "04", label: "April" },
    { value: "05", label: "May" },
    { value: "06", label: "June" },
    { value: "07", label: "July" },
    { value: "08", label: "August" },
    { value: "09", label: "September" },
    { value: "10", label: "October" },
    { value: "11", label: "November" },
    { value: "12", label: "December" },
  ];

  const validationSchema = Yup.object().shape({
    email: Yup.string().email("Invalid email").required("Email is Required"),
    password: Yup.string().required("Password is required"),
    confirmPassword: Yup.string()
      .required("Confirm Password is required")
      .oneOf([Yup.ref("password"), null], "Passwords must match"),
    contacts: Yup.array().min(1, "At least one contact is required"),
    userName: Yup.string().required("Username is required"),
    address: Yup.string().required("Address is required"),

    ...(Type === "company" && {
      companyName: Yup.string().required("company name is required"),
      description: Yup.string().required("description is required"),
    }),
    ...(Type === "jobSeeker" && {
      birthDate: Yup.object().shape({
        day: Yup.string().required("Day is required"),
        month: Yup.string().required("Month is required"),
        year: Yup.string().required("Year is required"),
      }),
    }),
  });
  const [isHovered, setIsHovered] = React.useState(false);

  const handleDateChange = (name) => (event) => {
    formik.setFieldValue("birthDate", {
      ...formik.values.birthDate,
      [name]: event.target.value,
    });
  };

  const formik = useFormik({
    initialValues: {
      userName: "",
      email: "",
      address: "",
      contacts: [""],
      password: "",
      userType: "",
      birthDate: {
        day: "",
        month: "",
        year: "",
      },
      experience: "",
      employmentState: "",
      description: "",
      education: "",
      companyName: "",
      gender: "",
    },
    // validationSchema: validationSchema,
    onSubmit: handleSubmit,
  });

  // const experienceValue = formik.values.experience || ""; // Ensure a default value if null

  return (
    <div>
      {/* <Button onClick={handleOpen}>Open modal</Button> */}
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Slide
          direction="left"
          in={open}
          mountOnEnter
          unmountOnExit
          timeout={{ enter: 1000, exit: 1200 }}
          transitiontimingfunction="ease-in-out"
          style={slideStyle}
        >
          <Box sx={style}>
            <form onSubmit={formik.handleSubmit}>
              <div className="flex items-center justify-between">
                <div className="flex items-center space-x-1 space-y-2 text-gray-500">
                  <div className="modal-content-container flex items-center justify-between mb-4">
                    <div className="flex items-center space-x-1 space-y-1 text-gray-500">
                      <IconButton
                        onMouseEnter={() => setIsHovered(true)}
                        onMouseLeave={() => setIsHovered(false)}
                        onClick={handleClose}
                      >
                        <CloseIcon
                          style={{ color: isHovered ? "red" : "black" }}
                        />
                      </IconButton>
                      <p className="">Edit profile </p>
                    </div>
                  </div>
                </div>
                <div>
                  {renderIcon()}
                  {uploadStatus === "uploading" && (
                    <span>Uploading: {uploadProgress}%</span>
                  )}
                </div>
                <Button type="submit">Save</Button>
              </div>
              <div className="hideScrollBar overflow-y-scroll overflow-x-hidden h-[80vh]">
                <React.Fragment>
                  <div className="w-full">
                    <div className="relative">
                      <img
                        className="w-full h-[12rem] object-cover object-center"
                        src={preview2}
                        alt=""
                      />
                      <input
                        type="file"
                        className="absolute top-0 left-0 w-full h-full opacity-0 cursor-pointer"
                        onChange={handleFileUpload2}
                        name="backgroundImage"
                      ></input>
                    </div>
                  </div>
                  {/* flex items-start mt-5 h-[5rem] */}
                  <div className="w-full transform -translate-y-20 ml-4 h-[6rem]">
                    <div className="relative">
                      {/* transform -translate-y-24 */}
                      <Avatar
                        className=""
                        alt="BOB"
                        src={preview}
                        sx={{
                          width: "10rem",
                          height: "10rem",
                          border: "4px solid white",
                        }}
                      />
                      <input
                        className="absolute top-0 left-0 w-[10rem] h-full opacity-0 cursor-pointer"
                        onChange={handleFileUpload}
                        name="image"
                        type="file"
                      />
                    </div>
                  </div>
                </React.Fragment>
                <div className="space-y-3">
                  <Grid container spacing={2}>
                    <Grid item xs={12}>
                      <TextField
                        fullWidth
                        id="userName"
                        name="userName"
                        label="User Name"
                        value={formik.values.userName}
                        onChange={formik.handleChange}
                        error={
                          formik.touched.userName &&
                          Boolean(formik.errors.userName)
                        }
                        helperText={
                          formik.touched.userName && formik.errors.userName
                        }
                      />
                    </Grid>

                    <Grid item xs={12}>
                      <TextField
                        fullWidth
                        label="Change Password"
                        variant="outlined"
                        size="large"
                        type={showPassword ? "text" : "password"}
                        name="password"
                        value={formik.values.password}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={
                          formik.touched.password &&
                          Boolean(formik.errors.password)
                        }
                        helperText={
                          formik.touched.password && formik.errors.password
                        }
                        InputProps={{
                          endAdornment: (
                            <InputAdornment position="end">
                              <IconButton
                                onClick={togglePasswordVisibility}
                                edge="end"
                              >
                                {showPassword ? (
                                  <VisibilityOff />
                                ) : (
                                  <Visibility />
                                )}
                              </IconButton>
                            </InputAdornment>
                          ),
                        }}
                      />
                    </Grid>
                    {Type !== "employer" && (
                      <Grid item xs={12}>
                        <TextField
                          fullWidth
                          multiline
                          rows={4}
                          id="description"
                          name="description"
                          label="Description"
                          value={formik.values.description}
                          onChange={formik.handleChange}
                          error={
                            formik.touched.description &&
                            Boolean(formik.errors.description)
                          }
                          helperText={
                            formik.touched.description &&
                            formik.errors.description
                          }
                        />
                      </Grid>
                    )}
                    <Grid item xs={12}>
                      <TextField
                        fullWidth
                        label="Country/City"
                        variant="outlined"
                        size="large"
                        name="address"
                        value={formik.values.address}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={
                          formik.touched.address &&
                          Boolean(formik.errors.address)
                        }
                        helperText={
                          formik.touched.address && formik.errors.address
                        }
                      />
                    </Grid>

                    <Grid item xs={12}>
                      <TextField
                        fullWidth
                        select
                        id="gender"
                        name="gender"
                        label="Gender"
                        value={formik.values.gender}
                        onChange={formik.handleChange}
                        variant="outlined"
                        error={
                          formik.touched.gender && Boolean(formik.errors.gender)
                        }
                        helperText={
                          formik.touched.gender && formik.errors.gender
                        }
                      >
                        <MenuItem value="Male">Male</MenuItem>
                        <MenuItem value="Female">Female</MenuItem>
                      </TextField>
                    </Grid>

                    {Type === "jobSeeker" ? (
                      <>
                        <Grid item xs={12}>
                          <TextField
                            fullWidth
                            label="Education"
                            variant="outlined"
                            size="large"
                            name="education"
                            value={formik.values.education}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            error={
                              formik.touched.education &&
                              Boolean(formik.errors.education)
                            }
                            helperText={
                              formik.touched.education &&
                              formik.errors.education
                            }
                          />
                        </Grid>
                        <Grid item xs={12}>
                          <TextField
                            fullWidth
                            select
                            id="experience"
                            name="experience"
                            label="Experience"
                            value={formik.values.experience}
                            onChange={formik.handleChange}
                            variant="outlined"
                            error={
                              formik.touched.experience &&
                              Boolean(formik.errors.experience)
                            }
                            helperText={
                              formik.touched.experience &&
                              formik.errors.experience
                            }
                          >
                            <MenuItem value="No">No</MenuItem>
                            <MenuItem value="1-2">1-2</MenuItem>
                            <MenuItem value="2-5">2-5</MenuItem>
                            <MenuItem value="5-8">5-8</MenuItem>
                            <MenuItem value="8-10">8-10</MenuItem>
                            <MenuItem value="more than 10">
                              more than 10
                            </MenuItem>
                          </TextField>
                        </Grid>
                        <Grid item xs={12}>
                          <TextField
                            fullWidth
                            label="Employment State"
                            variant="outlined"
                            size="large"
                            name="employmentState"
                            value={formik.values.employmentState}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                          />
                        </Grid>
                        <Grid item xs={4}>
                          <InputLabel>Day</InputLabel>
                          <Select
                            name="day"
                            fullWidth
                            onChange={handleDateChange("day")}
                            onBlur={formik.handleBlur}
                            value={formik.values.birthDate.day}
                          >
                            {days.map((day) => (
                              <MenuItem key={day} value={day}>
                                {day}
                              </MenuItem>
                            ))}
                          </Select>
                        </Grid>
                        <Grid item xs={4}>
                          <InputLabel>Month</InputLabel>
                          <Select
                            name="month"
                            fullWidth
                            onChange={handleDateChange("month")}
                            onBlur={formik.handleBlur}
                            value={formik.values.birthDate.month}
                          >
                            {months.map((month) => (
                              <MenuItem key={month.value} value={month.value}>
                                {month.label}
                              </MenuItem>
                            ))}
                          </Select>
                        </Grid>
                        <Grid item xs={4}>
                          <InputLabel>Year</InputLabel>
                          <Select
                            name="year"
                            fullWidth
                            onChange={handleDateChange("year")}
                            onBlur={formik.handleBlur}
                            value={formik.values.birthDate.year}
                          >
                            {years.map((year) => (
                              <MenuItem key={year} value={year}>
                                {year}
                              </MenuItem>
                            ))}
                          </Select>
                        </Grid>
                      </>
                    ) : (
                      Type === "Admin" && (
                        <>
                          <Grid item xs={12}>
                            <TextField
                              fullWidth
                              label="Company Name"
                              variant="outlined"
                              size="large"
                              name="companyName"
                              value={formik.values.companyName}
                              onChange={formik.handleChange}
                              onBlur={formik.handleBlur}
                              error={
                                formik.touched.companyName &&
                                Boolean(formik.errors.companyName)
                              }
                              helperText={
                                formik.touched.companyName &&
                                formik.errors.companyName
                              }
                            />
                          </Grid>
                        </>
                      )
                    )}
                  </Grid>
                </div>
              </div>
            </form>
          </Box>
        </Slide>
      </Modal>
    </div>
  );
}
