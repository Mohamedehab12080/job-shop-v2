import { api } from "../../config/api";
import { GET_JOBS_FAILURE, GET_JOBS_SUCCESS } from "./ActionType";

export const findAllJobs = () => async (dispatch) => {
  try {
    const { data } = await api.get(
      `/api/jobModel/findAllAndCheckForEachCompany`
    );
    console.log("Fetched jobs from back : ", data);
    dispatch({ type: GET_JOBS_SUCCESS, payload: data });
  } catch (error) {
    console.log("Error : ", error);
    dispatch({ type: GET_JOBS_FAILURE, payload: error });
  }
};
