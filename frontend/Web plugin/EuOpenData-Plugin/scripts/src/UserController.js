import User from './User';

export default class UserController {
    static login (email = "", passwd = "") {
        var isLogged = false;
        //to do

        if (isLogged)
        {
            var user = User(email, passwd);
            return user;
        }
        else {
            return null;
        }
    }

    static logout ()
    {
        window.localStorage.clear();
        window.location.reload();
        window.location.replace('/');
    }
}