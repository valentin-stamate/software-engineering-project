import LoginUtility from './LoginUtility'
import Client from './Client'
import FetchData from './FetchData'

class ClientLogin extends LoginUtility{
    //Credentials credentials -> Credentials.js
    static login(credentials){
        
        var data=this.fetchData(credentials);

        if("message" in data){
            return data["message"];
        }
        var client=new Client(data["username"],data["email"],data["profilePhotoLink"],data["authorizationToken"]);

        return client;
    }

    static fetchData(credentials){
        var data={
            login:credentials.uname,
            password:credentials.pword
        };

        var response=FetchData.makeRequest("https://euopendata.herokuapp.com/user/login","POST",data);
    
        return response;
    }
}

export default ClientLogin;