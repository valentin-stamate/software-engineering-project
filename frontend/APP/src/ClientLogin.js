import LoginUtility from './LoginUtility'
import Client from './Client'

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
        //datele ce trebuie trimise pentru logare (username,password)
        var data={
            username:credentials.uname,
            password:credentials.pword
        };

        var url="http://188.34.167.200:8082/user/login";

        /*
        CORS error
        fetch(url,{
            method:"POST",
            body:JSON.stringify(data)
        })
        .then(response =>response.json())
        .then(data => {
            alert("Data "+data);
        })
        .catch(error => {
            alert("Error "+error);
        });*/

        /*return {
            message:"Login failed"
        };*/

        return {
            username:"JohnDoe",
            email:"john@gmail.com",
            profilePhotoLink:null,
            authorizationToken:null
        };
    }
}

export default ClientLogin;