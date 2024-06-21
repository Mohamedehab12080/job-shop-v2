import React from "react";
import { Grid } from "@mui/material";
import Navigation from "../Navigation/Navigation";
import Header from "../Header";
import HomeSection from "../HomeSection/HomeSection";
import Profile from "../Profile/Profile";
import RightSection from "../RightPart/RightSection";
import { Route, Routes } from "react-router-dom";
import PostDetails from "../PostDetails/PostDetails";
import CompanyProfile from "../Profile/CompanyProfile";
import EmployerProfile from "../Profile/EmployerProfile";
import RecommendationPath from "../HomeSection/RecommendationPath";

export const Home = () => {

  

  return (
    <Grid container className="px-5 lg:px-36 justify-between">
      {/* Left Navigation */}
      <Grid item xs={4} lg={2} className="hidden lg:block w-full relative">
        <Navigation />
      </Grid>

      {/* Main Content */}
      <Grid item xs={12} lg={7} className="px-5 lg:px-9 w-full relative">
        <Routes>
          <Route path="/" element={<HomeSection />} />
          <Route path="/home" element={<HomeSection />} />
          <Route path="/profile/:id" element={<Profile />} />
          <Route path="/companyProfile/:id" element={<CompanyProfile />} />
          <Route path="/employerProfile/:id" element={<EmployerProfile />} />
          <Route path="/postDetails/:id" element={<PostDetails />} />
          <Route path="/Recommendation" element={<RecommendationPath />} />

          {/* <Route path="/request-reset" component={<RequestReset />} /> 
           <Route path="/reset-password/:token" component={ResetPassword} /> {/* Updated route */}
        </Routes>
      </Grid>

      {/* Right Section */}
      <Grid
        item
        xs={12}
        lg={3}
        className="hidden lg:block w-full relative sticky"
      >
        <RightSection />
      </Grid>
    </Grid>
  );
};

export default Home;
