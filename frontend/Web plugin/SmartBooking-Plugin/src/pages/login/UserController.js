var host_url = `https://euopendata.herokuapp.com/`;
export default class UserController {
    static async login(username = "", password = "") {
        let url = host_url + "user/login";

        let _data = {
            "login": username,
            "password": password
        };

        return await fetch(url, {
            method: "POST",
            body: JSON.stringify(_data),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => { 
            return handleLoginResponse(response); 
        }).catch(err => {
            console.log(err);
            return {succes:false, message:"Login failed!"};
        });
    }

    static async logout() {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }

    static async saveUserHotel(hotels) {
        if (hotels.length == 0) {
            return {succes:false, message:"Nothing to save!"};
        }
        let token = window.localStorage.getItem('token');
        let url = host_url + "user/add_hotels";

        return await fetch(url, {
            method: "POST",
            body: JSON.stringify(hotels),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(response => { 
            return handleSaveResponse(response);
        }).catch(err => {
            console.log(err);
            return {succes:false, message:"Failed to save hotel!"};
        });
    }

    static async getUserHotels() {
        let token = window.localStorage.getItem('token');
        let url = host_url + "user/hotels";

        return await fetch(url, {
            method: "GET",
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(response => {
            return handleLoadResponse(response); 
        }).catch(err => {
            console.log(err);
            return {succes:false, message:"Failed to load hotels!"};
        });
    }

    static async deleteUserHotel(hotel) {
        let token = window.localStorage.getItem('token');
        let url = host_url + "user/delete_hotel";

        let _data = {
            "hotelName": hotel.hotelName
        };

        return await fetch(url, {
            method: "DELETE",
            body: JSON.stringify(_data),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(response => {
            return handleDeleteResponse(response);
        }).catch(err => {
            console.log(err);
            return {succes:false, message:"Failed to delete hotel!"};
        });
    }
}

async function handleLoginResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            console.log(json);
            let token = json.authorizationToken;
            window.localStorage.setItem('loginstate', "true");
            window.localStorage.setItem('token', token);
            return {succes:true, message:"Login succesful"};
        } else {
            window.localStorage.setItem('loginstate', "false");
            return {succes:false, message:`Login failed - ${json.message}!` };
        }
    });
}

async function handleSaveResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            return {succes:true, message:"Hotels saved succesfuly!"};s
        } else {
            return {succes:false, message:"Failed to save hotels!"};
        }
    });
}

async function handleLoadResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            return {succes:false, message:json};
        } else {
            return {succes:false, message:"Failed to load hotels!"};
        }
    });
}

async function handleDeleteResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            return {succes:true, message:"Hotel deleted succesfuly!"};
        } else {
            return {succes:false, message:"Error deleting user hotel!"};
        }
    });
}