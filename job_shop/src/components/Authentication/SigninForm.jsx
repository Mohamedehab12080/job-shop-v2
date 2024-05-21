import { Grid, TextField,IconButton,InputAdornment } from '@mui/material';
import { Button } from '@mui/material';
import { blue } from '@mui/material/colors';
import { useFormik } from 'formik'
import { Visibility, VisibilityOff } from '@mui/icons-material';
import React from 'react'
import * as Yup from 'yup';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../store/Auth/Action';
import { useNavigate } from 'react-router-dom';

const SigninForm = () => {
    const dispatch=useDispatch();
    const [showPassword, setShowPassword] = React.useState(false);
    const validationSchema = Yup.object().shape({
        email: Yup.string().email("Invalid email").required("Email is Required"),
        password: Yup.string().required("Password is required")
    });

    const navigate=useNavigate();
    const formik = useFormik({
        initialValues: {
            email: "",
            password: "",
        },
        validationSchema,
        onSubmit: (values) => {
            dispatch(loginUser(values))
            console.log("form values: ", values)
        }
    });
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };
    return (
        <form onSubmit={formik.handleSubmit}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Email"
                        name="email"
                        variant='outlined'
                        size='large'
                        value={formik.values.email}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        error={formik.touched.email && Boolean(formik.errors.email)}
                        helperText={formik.touched.email && formik.errors.email}
                        sx={{ width: '100%' }} // Set width to 100%
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
               

                <Grid className='mt-20' item xs={12}>
                    <Button
                        sx={{ borderRadius: "29px", py: "15px", bgcolor: blue[500], width: '100%' }} // Set width to 100%
                        type="submit"
                        fullWidth
                        size="large"
                        variant='contained'>Login</Button>
                </Grid>
                <Grid item xs={12} > 
                        <a className='ml-3 cursor-pointer' onClick={() => navigate(`/request-reset`)}>Forgot password ?</a>
                </Grid>
            </Grid>
        </form>
    )
}

export default SigninForm;
