import axios from "axios"
import { CREATE_POST_FAILURE, CREATE_POST_SUCCESS, FETCH_MATCHED_POSTS_FAILURE, FETCH_MATCHED_POSTS_SUCCESS } from "./ActionType"
import { API_BASE_URL } from "../../config/api"


export const fetchMatchedPosts=(userId)=>async(dispatch)=>
{
    try {
        const {data}=await axios.get(`${API_BASE_URL}/api/Post/findPostsWithProfileSkills/${userId}`)
        dispatch({type:FETCH_MATCHED_POSTS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:FETCH_MATCHED_POSTS_FAILURE,payload:error.message()})
    }
}

export const createPost=(postData)=>async(dispatch)=>
{
    try {
        const {data}=await axios.post(`${API_BASE_URL}/api/employer/post`,postData)
        console.log("Fetched Posts : ",data)
        dispatch({type:CREATE_POST_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:CREATE_POST_FAILURE,payload:error.message()})
    }
}