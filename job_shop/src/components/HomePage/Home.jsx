import React from 'react';
import { Grid } from '@mui/material';
import Navigation from '../Navigation/Navigation';
import Header from '../Header';
import HomeSection from '../HomeSection/HomeSection';
import Profile from '../Profile/Profile';
import RightSection from '../RightPart/RightSection';
import { Route, Routes } from 'react-router-dom';
import PostDetails from '../PostDetails/PostDetails';
import Authentication from '../Authentication/Authentication';

export const Home = () => {
  return (
    <Grid container className='px-5 lg:px-36 justify-between'>
      {/* Left Navigation */}
      <Grid item xs={12} lg={3} className='hidden lg:block w-full relative'>
        <Navigation />
      </Grid>

      {/* Main Content */}
      <Grid item xs={12} lg={6} className='px-5 lg:px-9 w-full relative'>
        <Routes>
          <Route path="/" element={<HomeSection />} />
          <Route path="/home" element={<HomeSection />} />
          <Route path="/profile/:id" element={<Profile />} />
          <Route path="/postDetails/:id" element={<PostDetails />} />
        </Routes>
      </Grid>

      {/* Right Section */}
      <Grid item xs={12} lg={3} className='hidden lg:block w-full relative'>
        <RightSection />
      </Grid>
    </Grid>
  );
};

export default Home;
