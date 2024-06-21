import axios from "axios";
import { FETCH_LOCATIONS_FAILURE, FETCH_LOCATIONS_SUCCESS } from "./ActionType";
import { API_BASE_URL } from "../../config/api";

export const fetchLocations = () => async (dispatch) => {
  try {
    const { data } = await axios.get(`${API_BASE_URL}/auth/findAllValues`);
    console.log("fetched Locations : ", data);
    dispatch({ type: FETCH_LOCATIONS_SUCCESS, payload: data });
  } catch (error) {
    console.log("Error : ", error);
    dispatch({ type: FETCH_LOCATIONS_FAILURE, payload: error.message });
  }
};
