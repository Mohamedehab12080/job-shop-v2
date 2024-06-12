import React from "react";
import { Grid } from "@mui/material";
import image from "../common/images/loginImage.jpg";
import { Button } from "@mui/material";
import { GoogleLogin } from "@react-oauth/google";
import AuthModal from "./AuthModal";
import { useSelector } from "react-redux";
import MessageModal from "../../responses/MessageModal";

const Authentication = () => {
  const [openAuthModal, setOpenAuthModal] = React.useState(false);

  const handleOpenAuthModal = () => setOpenAuthModal(true);
  const handleCloseAuthModal = () => setOpenAuthModal(false);

  return (
    <div>
      <Grid container>
        <Grid item className="hidden lg:block" lg={7}>
          <img className="w-50% h-screen" src={image} alt="" />
        </Grid>
        <Grid item className="px-10" lg={5} xs={12}>
          <h1 className="mt-10 font-bold text-7xl">Happening Now </h1>
          <h1 className="font-bold text-3xl py-12">Join JOB SHOB Today</h1>
          <div className="w-[60%]">
            <div className="w-full">
              <GoogleLogin width={330} />
              <p className="py-3 text-center">OR</p>
              <Button
                fullWidth
                onClick={handleOpenAuthModal}
                variant="contained"
                size="large"
                sx={{
                  borderRadius: "29px",
                  py: "7px",
                }}
              >
                Create Account
              </Button>
              <p className="text-sm mt-2">
                By Signing up, you agree to the Terms of service and privacy
                policy, including cookie use.
              </p>
            </div>
            <div className="mt-5">
              <h1 className="font-bold text-xl mb-5"> Already Have Account?</h1>
              <Button
                fullWidth
                onClick={handleOpenAuthModal}
                variant="outlined"
                size="large"
                sx={{
                  borderRadius: "29px",
                  py: "7px",
                }}
              >
                LOGIN
              </Button>
            </div>
          </div>
        </Grid>
      </Grid>
      <section>
        <AuthModal
          openAuthModal={openAuthModal}
          handleClose={handleCloseAuthModal}
        />
      </section>
     
    </div>
  );
};

export default Authentication;
