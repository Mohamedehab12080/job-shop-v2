
import * as React from 'react';

import { request } from './axios_helper';

export default class Public extends React.Component{

    constructor(props)
    {
        super(props);
        this.state={
            data:[]
        };
    };

    componentDidMount()
    {
        request(
            "GET",
            "/homeRest/Entered",
            {}
        ).then((response)=>
        {
            this.setState({data:response.data})
        });
    };

    render()
    {
        return (
            <div>
                {this.state.data.id}
            </div>
        );
        
    };
}