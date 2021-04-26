const react = require("react");

class NotFoundPage extends react.Component{
    constructor(props){
        super(props);
    }

    render(){
        return(<h1>Error 404 : Page Not Found!</h1>);
    }
}

export default NotFoundPage;