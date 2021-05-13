import SignUpUtility from './SignUpUtility';
import FetchData from './FetchData'

class HotelAdminSignUp extends SignUpUtility{
    static signup(info) {
        var data = {
            username: info.username,
            email: info.email,
            password: info.password,
            profilePhotoLink: info.profilePhotoLink
        };

        var response = FetchData.makeAuthRequest("https://euopendata.herokuapp.com/owner/register", "POST", data, this.auth);
        return response.message;
    }

}

export default HotelAdminSignUp;