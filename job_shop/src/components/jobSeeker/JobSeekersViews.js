import React, { useEffect, useState } from 'react'
import axios from 'axios';

const JobSeekersView = () => {
  const[profile,setProfile]=useState(null)
  const[realUser,setRealUser]=useState(null);
  useEffect(()=>
  {
    loadUser();
  },[]);

  useEffect(()=>
  {
    if (realUser && realUser.id) {
    loadProfile(realUser.id);
    }
  },[realUser]);

  const loadUser=async()=>
  {
    try{
    //   const cookie = document.cookie
    //   .split('; ')
    //   .find(row => row.startsWith('JSESSIONID='));
    //   const sessionId = cookie ? cookie.split('=')[1] : null;
    //   // Set session cookie in request headers
    // if (sessionId) {
    //   axios.defaults.headers.common['Cookie'] = `JSESSIONID=${sessionId}`;
    // }
    const responseGet = await axios.get(`http://localhost:8089/homeRest/Entered`);
      setRealUser(responseGet.data);
    }catch(error)
    {
      console.error("Failed : "+error);
    }
  }
  const loadProfile=async(userId)=>
  {
    try {
      
        console.log("loading profile...");
       
          console.log("user user test value : "+userId);
          if (userId) {
            console.log("user id : " + userId);
            const response = await axios.get(`http://localhost:8089/jobSeekerProfile/getInfo/${userId}`);
            console.log("response received:", response);
            setProfile(response.data);
          } else {
            console.log("userId is null or undefined");
          }
        
        
      } catch (error) {
        console.error('Error fetching profile:', error);
      }
  }
  return (

      <div>
      {realUser &&(
        <div>
          User ID :<input id="userID" type='text' value={realUser.id} readOnly></input></div>
        
      )}

      {profile && (
        <div>
          <div>
            {profile.jobSeeker &&(
              <div> 
                <div>{profile.jobSeeker.picture}</div>
                User Name :{profile.jobSeeker.userName}</div>
            )}
          </div>
          {profile.followers.map((follower, index) => (
            <div key={index}>
              <div>{index + 1}</div>
              <div>{follower.userName}</div>
            </div>
          ))}
        </div>
      )}
    </div>  
    
  );
};
export default JobSeekersView
