
import * as React from 'react';
import WelcomeContent from './WelcomeContent';
import Public from './Public';
export default class AppContent extends React.Component{
    render()
    {
        return(
            <div>
                <WelcomeContent />
                <Public />
            </div>
        );
    };
}