import User from './User';
import UserController from './UserController';

export default class App {
    constructor(){
        this.user = null;
    }

    isUserConnected ()
    {
        if (this.user == null)
        {
            return false;
        }
        else {
            return true;
        }
    }

    tryLogin (uname, passwd)
    {
        user = UserController.login (uname, passwd);
        if (user != null)
            return true;
        else return false;
    }



}