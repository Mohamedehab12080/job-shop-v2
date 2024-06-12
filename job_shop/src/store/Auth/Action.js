import axios from "axios";
import { API_BASE_URL } from "../../config/api";
import {
  GET_USER_PROFILE_FAILURE,
  REGISTER_USER_SUCCESS,
  GET_USER_PROFILE_SUCCESS,
  LOGIN_USER_FAILURE,
  LOGIN_USER_SUCCESS,
  REGISTER_USER_FAILURE,
  LOGOUT,
} from "./ActionType";
export const loginUser = (loginData) => async (dispactch) => {
  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/signin`, loginData);
    if (data.jwt) {
      localStorage.setItem("jwt", data.jwt);
    }
    console.log("data returned : ", data);
    dispactch({ type: LOGIN_USER_SUCCESS, payload: data.jwt });
  } catch (error) {
    console.log("error", error);
    dispactch({ type: LOGIN_USER_FAILURE, payload: error });
  }
};

export const sendMailVerificationPassword = (Email) => async (dispactch) => {
  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/sendMail`, Email);
    if (data.jwt) {
      localStorage.setItem("jwt", data.jwt);
    }
    console.log("data returned : ", data);
    dispactch({ type: LOGIN_USER_SUCCESS, payload: data.jwt });
  } catch (error) {
    console.log("error", error);
    dispactch({ type: LOGIN_USER_FAILURE, payload: error.message });
  }
};

export const registerJobSeekerUser =
  (registerJobSeekerData) => async (dispactch) => {
    try {
      const { data } = await axios.post(
        `${API_BASE_URL}/auth/jobSeeker/signup`,
        registerJobSeekerData
      );
      if (data.jwt) {
        localStorage.setItem("jwt", data.jwt);
      }
      dispactch({ type: REGISTER_USER_SUCCESS, payload: data.jwt });
    } catch (error) {
      console.log("error", error);
      dispactch({ type: REGISTER_USER_FAILURE, payload: error.message });
    }
  };
export const registrerCompanyUser =
  (registrerCompanyUserData) => async (dispactch) => {
    try {
      const { data } = await axios.post(
        `${API_BASE_URL}/auth/company/signup`,
        registrerCompanyUserData
      );
      if (data.jwt) {
        localStorage.setItem("jwt", data.jwt);
      }
      console.log("data returned : ", data);
      dispactch({ type: REGISTER_USER_SUCCESS, payload: data.jwt });
    } catch (error) {
      console.log("error", error);
      dispactch({ type: REGISTER_USER_FAILURE, payload: error.message });
    }
  };

export const getCompanyUserProfile = (jwt) => async (dispactch) => {
  try {
    const { data } = await axios.get(
      `${API_BASE_URL}/api/company/findProfile`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispactch({ type: GET_USER_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    console.log("error", error);
    dispactch({ type: GET_USER_PROFILE_FAILURE, payload: error.message });
  }
};

export const getJobSeekerUserProfile = (jwt) => async (dispactch) => {
  try {
    const { data } = await axios.get(
      `${API_BASE_URL}/api/jobSeekers/findProfile`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    dispactch({ type: GET_USER_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    console.log("error", error);
    dispactch({ type: GET_USER_PROFILE_FAILURE, payload: error.message });
  }
};

export const getUserProfile = (jwt) => async (dispactch) => {
  try {
    const { data } = await axios.get(`${API_BASE_URL}/api/user/findByJwt`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispactch({ type: GET_USER_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    console.log("error", error);
    dispactch({ type: GET_USER_PROFILE_FAILURE, payload: error.message });
  }
};

export const Logout = () => async (dispactch) => {
  localStorage.removeItem("jwt");
  dispactch({ type: LOGOUT, payload: null });
};
