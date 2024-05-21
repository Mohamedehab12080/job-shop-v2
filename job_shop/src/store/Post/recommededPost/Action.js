import { api } from "../../../config/api"
import { GET_RECOMMEDED_POSTS_FAILURE, GET_RECOMMEDED_POSTS_SUCCESS } from "./ActionType"

export const recommendedPosts=(ListOfSkills)=>async(dispatch)=>
    {
        try {
            const {data}=await api.post(`/api/Post/recommendation`,ListOfSkills)
            console.log("Recommeded Posts : ",data)
            dispatch({type:GET_RECOMMEDED_POSTS_SUCCESS,payload:data})
        } catch (error) {
            console.error("Error : ",error)
            dispatch({type:GET_RECOMMEDED_POSTS_FAILURE,payload:error.message})
        }
    }