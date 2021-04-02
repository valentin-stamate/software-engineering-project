import Preference from './Preference.js';

export default class User {
    constructor (email = "", passwd = "")
    {
        this.preferenceList = [];
        this.email = email;
        this.passwd = passwd;
    }

    getUserName (){
        return this.uname;
    }

    getUserEmail ()
    {
        return this.email;
    }

    getPrefernceList ()
    {
        return this.preferenceList;
    }

    addToPreference (preference){
        this.preferenceList.push(preference);
    }


}