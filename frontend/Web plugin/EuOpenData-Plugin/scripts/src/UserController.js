import User from './User.js';

export default class UserController {
    static login (username = "", password = "") {
        var isLogged = false;
        //to do
		
		var xhr = new XMLHttpRequest();
		var url = "http://188.34.167.200:8082/user/login";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var json = JSON.parse(xhr.responseText);
				console.log(json.email + ", " + json.password);
			}
		};
		var data = JSON.stringify({"username": username, "password": password});
		xhr.send(data);

        if (isLogged)
        {
            var user = null;
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

document.getElementById("send-button").addEventListener("click", () => {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	UserController.login(username, password);
});