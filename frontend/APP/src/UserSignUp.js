import SignUpUtility from './SignUpUtility';
import ClientInfo from './ClientInfo';
import FetchData from './FetchData'

class ClientSignUp extends SignUpUtility{
    static signup(info){
        var data=this.fetchData(info);
        return data.message;
   }

    static fetchData(info){
        var data={
            username:info.username,
            email:info.email,
            password:info.password,
            profilePhotoLink:info.profilePhotoLink
        };

        var response=FetchData.makeRequest("https://euopendata.herokuapp.com/user/register","POST",data);

        return response;
    }
}

export default ClientSignUp;