import { GET_SKILLS_FAILURE, GET_SKILLS_REQUEST, GET_SKILLS_SUCCESS } from "./ActionType"

const initialState = {
    loading: false,
    error: null,
    skills: [],
    skill: null,
    response: null
}

export const skillReducer = (state = initialState, action) => {
    switch (action.type) {
        case GET_SKILLS_REQUEST:
            return { ...state, loading: true, error: null };
        case GET_SKILLS_FAILURE:
            return { ...state, loading: false, error: action.payload };
        case GET_SKILLS_SUCCESS:
            return {
                ...state,
                loading: false,
                error: null,
                skills: action.payload
            };
        default:
            // console.log("Data From the case Default : ", action.payload)
            return state;
    }
}
