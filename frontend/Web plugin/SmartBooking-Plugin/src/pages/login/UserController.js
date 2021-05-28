var host_url = `https://euopendata.herokuapp.com/`;
export default class UserController {
    static autoLogin = true;
    static async login(login = "", password = "") {
        let url = host_url + "user/login";
        console.log(login);
        console.log(password);

        let _data = {
            "login": login,
            "password": password
        };

        console.log(_data);

        let logged = await fetch(url, {
            method: "POST",
            body: JSON.stringify(_data),
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            return handleLoginResponse(response);
        }).catch(err => {
            console.log(err);
            return { succes: false, message: "Login failed! (fetch error)" };
        });

        if (logged.succes) {
            window.localStorage.setItem('credentials', JSON.stringify(_data));
        } else {
            window.localStorage.setItem('credentials', "undefined");
        }

        return logged;
    }

    static logout() {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }

    static async saveUserHotel(hotels) {
        console.log(hotels);
        if (hotels.length == 0) {
            return { succes: false, message: "Nothing to save!" };
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
            return { succes: false, message: "Failed to save hotel! (fetch error)" };
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
            return { succes: false, message: "Failed to load hotels! (fetch error)" };
        });
    }

    static async deleteUserHotel(hotelId) {
        let token = window.localStorage.getItem('token');
        let url = host_url + "user/delete_hotel?hotel_id=" + hotelId;

        return await fetch(url, {
            method: "DELETE",
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(response => {
            return handleDeleteResponse(response);
        }).catch(err => {
            console.log(err);
            return { succes: false, message: "Failed to delete hotel! (fetch error)" };
        });
    }
}

async function handleLoginResponse(response) {
    console.log(response);
    return await response.json().then(function(json) {
        if (response.status == 200) {
            console.log(json);
            let token = json.authorizationToken;
            window.localStorage.setItem('loginstate', "true");
            window.localStorage.setItem('token', token);
            return { succes: true, message: "Login succesful" };
        } else {
            window.localStorage.setItem('loginstate', "false");
            return { succes: false, message: `Login failed - ${json.message}!` };
        }
    });
}

async function handleSaveResponse(response) {
    return await response.text().then(function(text) {
        if (response.status == 200) {
            return { succes: true, message: "Hotels saved succesfuly!" };
        } else {
            return { succes: false, message: "Failed to save hotels!" };
        }
    });
}

async function handleLoadResponse(response) {
    return await response.json().then(function(json) {
        if (response.status == 200) {
            return { succes: true, message: json };
        } else {
            return { succes: false, message: "Failed to load hotels!" };
        }
    });
}

async function handleDeleteResponse(response) {
    return await response.text().then(function(text) {
        if (response.status == 200) {
            return { succes: true, message: "Hotel deleted succesfuly!" };
        } else {
            return { succes: false, message: "Error deleting user hotel!" };
        }
    });
}