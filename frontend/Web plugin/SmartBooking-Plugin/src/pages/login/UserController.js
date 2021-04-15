export default class UserController {
    static login(username = "", password = "") {
        let url = "http://188.34.167.200:8082/user/login";

        let _data = {
            "username": username,
            "password": password
        };

        fetch(url, {
            method: "POST",
            body: JSON.stringify(_data),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(handleLoginResponse).catch(err => {
            console.log(err);
        });
    }

    static logout() {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }

    static saveHotel(hotel) {
        let token = window.localStorage.getItem('token');
        let url = "http://188.34.167.200:8082/user/add_hotel";

        let _data = {
            "hotelName": hotel.hotelName,
            "locationName": hotel.hotelLocation
        };

        fetch(url, {
            method: "POST",
            body: JSON.stringify(_data),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(handleSaveResponse).catch(err => {
            console.log(err);
        });
    }

    static getHotels() {
        let token = window.localStorage.getItem('token');
        let url = "http://188.34.167.200:8082/user/hotels";

        fetch(url, {
            method: "GET",
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(handleLoadResponse).catch(err => {
            console.log(err);
        });
    }

    static deleteHotel(hotel) {
        let token = window.localStorage.getItem('token');
        let url = "http://188.34.167.200:8082/user/delete_hotel";

        let _data = {
            "hotelName": hotel.hotelName
        };

        fetch(url, {
            method: "DELETE",
            body: JSON.stringify(_data),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Authorization": token
            }
        }).then(handleDeleteResponse).catch(err => {
            console.log(err);
        });
    }
}

function handleLoginResponse(response) {
    response.json().then(function(json) {
        if (response.status == 200) {
            console.log(json);
            let token = json.authorizationToken;
            let username = json.username;
            window.localStorage.setItem('loginstate', "true");
            window.localStorage.setItem('token', token);
            showAlert("login succesful - " + username + ", " + token, true);
        } else {
            localStorage.setItem('loginstate', "false");
            showAlert("login failed - " + json.message);
        }
    });
}

function handleSaveResponse(response) {
    response.json().then(function(json) {
        if (response.status == 200) {
            showAlert(json.message);
        } else {
            showAlert("saving failed - " + json.message)
        }
    });
}

function handleLoadResponse(response) {
    response.json().then(function(json) {
        if (response.status == 200) {
            console.log(JSON.stringify(json));
        } else {
            console.log("Failed to get the hotels - " + json.message)
        }
    });
}

function handleDeleteResponse(response) {
    response.json().then(function(json) {
        if (response.status == 200) {
            console.log(json.message);
        } else {
            console.log("saving failed - " + json.message)
        }
    });
}