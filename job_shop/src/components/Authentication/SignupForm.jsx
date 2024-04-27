import { Grid, TextField, Select, MenuItem, InputLabel, IconButton, InputAdornment } from '@mui/material';
import { Button } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import { blue } from '@mui/material/colors';
import { useFormik } from 'formik';
import React, { useState } from 'react';
import * as Yup from 'yup';
import { AddCircleOutline as AddCircleOutlineIcon, RemoveCircleOutline as RemoveCircleOutlineIcon } from '@mui/icons-material';
import { useDispatch } from 'react-redux';
import { registerJobSeekerUser, registrerCompanyUser } from '../../store/Auth/Action';

const SignupForm = () => {
    const dispatch=useDispatch();
    const [showPassword, setShowPassword] = useState(false);
    const [userType, setUserType] = useState("jobSeeker"); // Default user type

    const validationSchema = Yup.object().shape({
        email: Yup.string().email("Invalid email").required("Email is Required"),
        password: Yup.string().required("Password is required"),
        confirmPassword: Yup.string()
            .required('Confirm Password is required')
            .oneOf([Yup.ref('password'), null], 'Passwords must match'),
        userType: Yup.string().required("User type is required"),
        contacts: Yup.array().min(1, "At least one contact is required"),
        userName: Yup.string().required("Username is required"),
        address: Yup.string().required("Address is required"),
        birthDate: Yup.object().shape({
            day: Yup.string().required('Day is required'),
            month: Yup.string().required('Month is required'),
            year: Yup.string().required('Year is required')
        }),
        ...(userType === "company" && {
            companyName: Yup.string().required("company name is required"),
            description: Yup.string().required("description is required"),
        })
    });

    const currentYear = new Date().getFullYear();
    const years = Array.from({ length: 100 }, (_, i) => currentYear - i);
    const days = Array.from({ length: 31 }, (_, i) => i + 1);
    const months = [
        { value: 1, label: "January" },
        { value: 2, label: "February" },
        { value: 3, label: "March" },
        { value: 4, label: "April" },
        { value: 5, label: "May" },
        { value: 6, label: "June" },
        { value: 7, label: "July" },
        { value: 8, label: "August" },
        { value: 9, label: "September" },
        { value: 10, label: "October" },
        { value: 11, label: "November" },
        { value: 12, label: "December" },
    ];

    const handleSubmit = (values) => {
        console.log("form values: ", values);
        const { day, month, year } = values.birthDate;
        const birthDate = `${year}-${month}-${day}`;
        values.birthDate = birthDate;
        const formData = { ...values, contacts: values.contacts.filter(contact => contact !== '') };
        console.log("form values: ", formData);
        console.log("condition of jobSeeker : A7A")
        if(formData.userType==="jobSeeker")
        {
            
            dispatch(registerJobSeekerUser(formData))
        }else 
        {
            console.log("condition of admin : ",formData.userType)
            dispatch(registrerCompanyUser(formData))
        }
    };

    const formik = useFormik({
        initialValues: {
            userName: "",
            email: "",
            address: "",
            contacts: [''],
            password: "",
            confirmPassword: "",
            userType: "",
            birthDate: {
                day: '',
                month: '',
                year: ''
            },
            experience: "",
            employmentState: "",
            description: "",
            education: "",
            companyName:""
        },
        // validationSchema: validationSchema,
        onSubmit: handleSubmit
    });
    const handleUserTypeChange = (event) => {
        setUserType(event.target.value);
        formik.setFieldValue('userType', event.target.value);
    };
    const handleAddContact = () => {
        formik.setFieldValue('contacts', [...formik.values.contacts, '']);
    };
    
    const handleRemoveContact = (index) => {
        const newContacts = [...formik.values.contacts];
        newContacts.splice(index, 1);
        formik.setFieldValue('contacts', newContacts);
    };
    
    const handleContactChange = (e, index) => {
        const newContacts = [...formik.values.contacts];
        newContacts[index] = e.target.value;
        formik.setFieldValue('contacts', newContacts);
    };

    const handleDateChange = (name) => (event) => {
        formik.setFieldValue("birthDate", {
            ...formik.values.birthDate,
            [name]: event.target.value,
        });
    };

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <form onSubmit={formik.handleSubmit}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Select
                        fullWidth
                        variant='outlined'
                        size='large'
                        name="userType"
                        value={formik.values.userType}
                        onChange={handleUserTypeChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.userType && Boolean(formik.errors.userType)}
                        helperText={formik.touched.userType && formik.errors.userType}
                    >
                        <InputLabel id="user-type-label">Type</InputLabel>
                        <MenuItem value="jobSeeker">Job Seeker</MenuItem>
                        <MenuItem value="company">Company</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Username"
                        variant='outlined'
                        size='large'
                        name="userName"
                        value={formik.values.userName}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.userName && Boolean(formik.errors.userName)}
                        helperText={formik.touched.userName && formik.errors.userName}
                    />
                </Grid>

                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Email"
                        variant='outlined'
                        size='large'
                        name="email"
                        value={formik.values.email}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.email && Boolean(formik.errors.email)}
                        helperText={formik.touched.email && formik.errors.email}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Password"
                        variant='outlined'
                        size='large'
                        type={showPassword ? 'text' : 'password'}
                        name="password"
                        value={formik.values.password}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.password && Boolean(formik.errors.password)}
                        helperText={formik.touched.password && formik.errors.password}
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <IconButton
                                        onClick={togglePasswordVisibility}
                                        edge="end"
                                    >
                                        {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            ),
                        }}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Confirm Password"
                        variant='outlined'
                        size='large'
                        type={showPassword ? 'text' : 'password'}
                        name="confirmPassword"
                        value={formik.values.confirmPassword}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)}
                        helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Country/City"
                        variant='outlined'
                        size='large'
                        name="address"
                        value={formik.values.address}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.address && Boolean(formik.errors.address)}
                        helperText={formik.touched.address && formik.errors.address}
                    />
                </Grid>
               {formik.values.userType==="jobSeeker" ? (
                    <>
                    <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Experience"
                        variant='outlined'
                        size='large'
                        name="experience"
                        value={formik.values.experience}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Education"
                        variant='outlined'
                        size='large'
                        name="education"
                        value={formik.values.education}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.education && Boolean(formik.errors.education)}
                        helperText={formik.touched.education && formik.errors.education}
                    />
                </Grid>

                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Employment State"
                        variant='outlined'
                        size='large'
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
                            <MenuItem key={day} value={day}>{day}</MenuItem>
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
                            <MenuItem key={month.value} value={month.value}>{month.label}</MenuItem>
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
                            <MenuItem key={year} value={year}>{year}</MenuItem>
                        ))}
                    </Select>
                </Grid>
                    </>
               ):(
                    <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Company Name"
                        variant='outlined'
                        size='large'
                        name="companyName"
                        value={formik.values.companyName}
                        onChange={formik.handleChange} 
                        onBlur={formik.handleBlur} 
                        error={formik.touched.companyName && Boolean(formik.errors.companyName)}
                        helperText={formik.touched.companyName && formik.errors.companyName}
                    />
                </Grid>
               )}
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Contact"
                        variant='outlined'
                        size='large'
                        name="contact"
                        value={formik.values.contact}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.contact && Boolean(formik.errors.contact)}
                        helperText={formik.touched.contact && formik.errors.contact}
                    />
                    <IconButton onClick={handleAddContact} edge="end">
                        <AddCircleOutlineIcon />
                    </IconButton>
                </Grid>
                {formik.values.contacts.map((contact, index) => (
                    <Grid item xs={12} key={index}>
                        <TextField
                            fullWidth
                            variant='outlined'
                            label={`Contact ${index+1}`}
                            size='large'
                            value={contact}
                            onChange={(e) => handleContactChange(e, index)}
                            onBlur={formik.handleBlur}
                            error={formik.touched.contacts && Boolean(formik.errors.contacts && formik.errors.contacts[index])}
                            helperText={formik.touched.contacts && formik.errors.contacts && formik.errors.contacts[index]}
                        />
                        <IconButton onClick={() => handleRemoveContact(index)} edge="end">
                            <RemoveCircleOutlineIcon />
                        </IconButton>
                    </Grid>
                ))}

                    <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Description"
                        variant='outlined'
                        size='large'
                        name="description"
                        value={formik.values.description}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.description && Boolean(formik.errors.description)}
                        helperText={formik.touched.description && formik.errors.description}
                        multiline  // Allow multiple lines
                        rows={4}   // Set to 4 rows
                    />
                </Grid>
                    <Grid item xs={12}>
                    <Button
                        sx={{ borderRadius: "29px", py: "15px", bgcolor: blue[500] }}
                        type="submit"
                        fullWidth
                        size="large"
                        variant='contained'
                    >
                        Signup
                    </Button>
                </Grid>
            </Grid>
        </form>
    );
}

export default SignupForm;
