import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { Button, Grid, IconButton, InputAdornment, TextField, Container, Typography } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import { blue } from '@mui/material/colors';
import { API_BASE_URL } from '../../config/api';

const ResetPassword = () => {
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    
    const { token } = useParams(); // Extract token from URL path

    const navigate=useNavigate();
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log(API_BASE_URL); // Check if this prints the correct backend URL
            console.log("Password and token : ",newPassword,"::::: Token : ",token);
            const response = await axios.post(`${API_BASE_URL}/auth/reset`,{ token, newPassword });
            setMessage('Password reset successful.');
        } catch (error) {
            console.log("ERror : ",error)
            setMessage('Error resetting password.',error.message);
        }
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" align="center" gutterBottom>
                Reset Password
            </Typography>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={2} justifyContent="center">
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            label="New Password"
                            variant="outlined"
                            type={showPassword ? 'text' : 'password'}
                            value={newPassword}
                            onChange={(e) => setNewPassword(e.target.value)}
                            InputProps={{
                                style: {
                                    color: '#000', // Text color
                                    backgroundColor: '#fff', // Background color
                                    borderRadius: '5px', // Border radius
                                },
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton onClick={togglePasswordVisibility} edge="end">
                                            {showPassword ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    </InputAdornment>
                                ),
                            }}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button
                            fullWidth
                            sx={{
                                borderRadius: "29px",
                                py: "10px",
                                bgcolor: blue[500],
                                color: 'white'
                            }}
                            type="submit"
                            size="large"
                            variant="contained"
                        >
                            Reset Password
                        </Button>
                    </Grid>
                    <Grid item xs={12}>
                        {message && <Typography variant="body2" color="textSecondary" align="center">{message} <a onClick={navigate(`/`)}>Back to Login..</a></Typography>}
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default ResetPassword;
