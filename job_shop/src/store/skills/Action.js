import axios from "axios"
import { API_BASE_URL, api } from "../../config/api"
import { GET_SKILLS_FAILURE, GET_SKILLS_SUCCESS } from "./ActionType";


export const findAllSkills=()=>async(dispatch)=>
{
    try {
        const response=await api.get(`/api/skills/findAll`);
        dispatch({type:GET_SKILLS_SUCCESS,payload:response.data});
    } catch (error) {
        console.log("error",error);
        dispatch({ type: GET_SKILLS_FAILURE, payload: error.message })
    }
}