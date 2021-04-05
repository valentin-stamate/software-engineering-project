export default class UserController {
	static isLogged = false;
    static login (username = "", password = "") {
		var xhr = new XMLHttpRequest();
		var url = "http://188.34.167.200:8082/user/login";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var json = JSON.parse(xhr.responseText);
				alert(json.userId + ", " + json.username + ", " + json.email + ", " + json.profilePhotoLink);
				this.isLogged = true;
			}
		};
		var data = JSON.stringify({"username": username, "password": password});
		xhr.send(data);
    }

    static logout ()
    {
        window.localStorage.clear();
        window.location.reload();
        window.location.replace('/');
    }
}