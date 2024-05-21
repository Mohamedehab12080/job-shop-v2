import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './components/App';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { store } from './store/store';
import {GoogleOAuthProvider} from '@react-oauth/google'
import { ThemeProvider } from './ThemeContext';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
   <React.StrictMode>
    <BrowserRouter>
       <Provider store={store}>
         <GoogleOAuthProvider>
         <ThemeProvider>
            <App />
         </ThemeProvider>
         </GoogleOAuthProvider>
       </Provider>
    </BrowserRouter>
   </React.StrictMode>
   );
