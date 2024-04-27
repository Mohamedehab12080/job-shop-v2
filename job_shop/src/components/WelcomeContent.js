
import * as React from'react';
export default class WelcomeContent extends React.Component
{
    render()
    {
        return(
          <div className="row justify-content-md-center">
             <div className="jumbotron jumbotron-fluid">
                <div className="container">
                <h1 className="display-4">Welcome to our JOBSHOP</h1>
                <p className="lead">You will Reach your job Dreem</p>
                </div>
             </div>
          </div>
        );
    };
}