export default class UserController {
    static login (username = "", password = "") {
		var xhr = new XMLHttpRequest();
		var url = "http://188.34.167.200:8082/user/login";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.setRequestHeader("Authorization", "");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var json = JSON.parse(xhr.responseText);
				window.localStorage.setItem('loginstate', "true");
				window.localStorage.setItem('token', json.token);
				alert(json.message + ", " + json.token);
				window.location.href = "/src/pages/popup.html";
			}else if (xhr.readyState === 4) {
				var json = JSON.parse(xhr.responseText ? xhr.responseText : "{message:error}");
				localStorage.setItem('loginstate', "false");
				showAlert("login failed - " + json.message);
			}
		};
		var data = JSON.stringify({"username": username, "password": password});
		xhr.send(data);
    }

    static logout ()
    {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }
}