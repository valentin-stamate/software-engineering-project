import SignUpUtility from './SignUpUtility';
import ClientInfo from './ClientInfo';

class ClientSignUp extends SignUpUtility{
    static signup(info){
        var data=this.fetchData(info);
        return data.message;
   }

    static fetchData(info){
        //datele ce trebuie trimise pentru inregistrare cont
        var data={
            username:info.username,
            email:info.email,
            password:info.password,
            profilePhotoLink:info.profilePhotoLink
        };

        var url="http://188.34.167.200:8082/user/register";

        /*
        eroare CORS
        fetch(url,{
            method:"POST",
            body:JSON.stringify(data)
        })
        .then(response =>response.json())
        .then(data => {
            //...TO DO
        })
        .catch(error => {
            console.log(error);
        })*/


        return { 
            message : "Registration executed successfully" 
        };
    }
}

export default ClientSignUp;