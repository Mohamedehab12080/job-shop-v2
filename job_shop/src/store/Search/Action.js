import { API_BASE_URL, api } from "../../config/api";
import { SEARCH_USER_FAILURE, SEARCH_USER_SUCCESS } from "./ActionType";

export const searchUsers = (query) => async (dispatch) => {
  try {
    console.log("quert : ", query);
    const { data } = await api.post(
      `${API_BASE_URL}/api/user/search?query=${encodeURIComponent(query)}`
    );
    console.log("Search Users response : ", data);
    dispatch({ type: SEARCH_USER_SUCCESS, payload: data });
  } catch (error) {
    console.log("Search Users Error : ", error);
    dispatch({ type: SEARCH_USER_FAILURE, payload: error });
  }
};
