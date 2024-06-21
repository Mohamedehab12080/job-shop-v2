import "./App.css";
import Home from "./HomePage/Home";
import Authentication from "./Authentication/Authentication";
import { Route, Routes } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUserProfile } from "../store/Auth/Action";
import { useTheme } from "../ThemeContext";
import { Brightness4 } from "@mui/icons-material";
import RequestReset from "./Authentication/RequestReset";
import ResetPassword from "./Authentication/ResetPassword";

function App() {
  const jwt = localStorage.getItem("jwt");
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const { theme, toggleTheme } = useTheme();

  useEffect(() => {
    if (jwt) {
      dispatch(getUserProfile(jwt));
    }
  }, [jwt, dispatch]);

  return (
    <div className={`app ${theme}`}>
      <header className="app-header">
        <Brightness4 className="ml-3 cursor-pointer" onClick={toggleTheme} />
        <Routes>
          <Route path="/reset-password/:token" element={<ResetPassword />} />
          <Route path="/request-reset" element={<RequestReset />} />
          <Route
            path="/*"
            element={auth.user ? <Home /> : <Authentication />}
          />
        </Routes>
      </header>
    </div>
  );
}

export default App;
