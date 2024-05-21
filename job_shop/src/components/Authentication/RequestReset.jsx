import React, { useState } from 'react';
import axios from 'axios';
import { Button, Grid, TextField } from '@mui/material';
import { blue } from '@mui/material/colors';
import { API_BASE_URL } from '../../config/api';
import MessageModal from '../../responses/MessageModal';

const RequestReset = () => {
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [openMessageModal,setOpenMessageModal]=useState(false);
    const handleOpenMessageModal=()=>setOpenMessageModal(true);
    const handleCloseMessageModal=()=>setOpenMessageModal(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${API_BASE_URL}/auth/request/${email}`,);
            console.log("Returned response : ",response);
            setMessage('Reset link sent to your email.');
            handleOpenMessageModal();
        } catch (error) {
            setMessage('Error sending reset link.',error);
        }
    };

    return (
        <div>
            <h2>Request Password Reset</h2>
            <form onSubmit={handleSubmit}>

    <Grid container spacing={2}>
          
          <Grid item xs={12}>
                    <TextField
                        label="Email"
                        variant='outlined'
                        size='large'
                        name="email"
                        sx={{width:'800px',justifyContent:'center'}}
                        value={email}
                        onChange={(e)=>setEmail(e.target.value)}
                        required
                        InputProps={{
                            style: {
                                color: '#000', // Text color
                                backgroundColor: '#fff', // Background color
                                borderRadius: '5px', // Border radius
                            }                                           
                        }}
                    />
                </Grid>

                <Grid item xs={12}>
                    <Button
                        fullWidth
                        className='mt-3'
                        sx={{ borderRadius: "29px", py: "5px", bgcolor: blue[500],width:'800px',justifyContent:'center'}}
                        type="submit"
                        size="large"
                        variant='contained'
                    >
                        Send Email for Reset Password
                    </Button>
                </Grid>
                <Grid item xs={12}>
                     {message && <p>{message}</p>}           
                </Grid>
          </Grid>
            </form>
           <section>
                 <MessageModal openMessageModal={openMessageModal} handleCloseMessageModal={handleCloseMessageModal} response={message} Title={"Password Reset Request"}/>
           </section>
        </div>
    );
};

export default RequestReset;
