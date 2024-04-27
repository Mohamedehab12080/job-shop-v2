import './App.css';
import Home from './HomePage/Home';
import logo from './common/images/logo.png';
import Header from './Header';
import JobSeekersView from './jobSeeker/JobSeekersViews';
import AppContent from './AppContent';
import Authentication from './Authentication/Authentication';
import {Route,Routes} from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { getUserProfile } from '../store/Auth/Action';
function App() {

  const jwt=localStorage.getItem("jwt");
  const auth = useSelector(state => state.auth);
  const dispatch=useDispatch();
  
useEffect(()=>
{
  if(jwt){
    dispatch(getUserProfile(jwt))
  }
},[auth.jwt]
)
  return (
    <div className="App">
      <Routes>
     
        <Route path="/*" element={auth.user?<Home />:<Authentication/>}></Route>
      </Routes>
      {/* 
     <div className="container-fluid">
      <div className="row">
        <div className="col">
        <AppContent />
        </div>
      </div>
     </div>
      <Home />
      <JobSeekersView /> */}
    </div>
  );
}

export default App;
