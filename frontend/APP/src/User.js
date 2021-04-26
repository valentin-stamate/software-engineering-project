class User{
    constructor(username,email,profilePic,auth){
        this.username=username;
        this.email=email;
        this.profilePic=profilePic;
        this.auth=auth;
    }

    login(){
        throw new Error("Method must be implemented");
    }

    signup(){
        throw new Error("Method must be implemented");
    }
}

export default User;