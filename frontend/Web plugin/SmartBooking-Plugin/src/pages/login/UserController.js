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
            return false;
        });
    }

    static async logout() {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }

    static async saveUserHotel(hotels) {
        if (hotels.length == 0) {
            return true;
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
            return false;
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
            return {message:"Error loading user hotel list"};
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
            return false;
        });
    }
}

async function handleLoginResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            console.log(json);
            let token = json.authorizationToken;
            let username = json.username;
            window.localStorage.setItem('loginstate', "true");
            window.localStorage.setItem('token', token);
            showAlert("login succesful - " + username + ", " + token, true);
            return true;
        } else {
            window.localStorage.setItem('loginstate', "false");
            showAlert("login failed - " + json.message);
            return false;
        }
    });
}

async function handleSaveResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            showAlert(json.message);
            return true;
        } else {
            showAlert("saving failed - " + json.message);
            return false;
        }
    });
}

async function handleLoadResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            return json;
        } else {
            showAlert("Failed to get the hotels - " + json.message);
            return json;
        }
    });
}

async function handleDeleteResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            alert(json.message);
            return true;
        } else {
            alert("deleting failed - " + json.message);
            return false;
        }
    });
}