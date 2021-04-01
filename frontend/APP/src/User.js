class User{
    constructor(username,password){
        this.username=username;
        this.password=password;
        this.loginStatus=false;
    }

    login(){
        throw new Error("Method must be implemented");
    }

    signup(){
        throw new Error("Method must be implemented");
    }
}

export default User;