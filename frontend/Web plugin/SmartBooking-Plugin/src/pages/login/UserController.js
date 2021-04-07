export default class UserController {
    static login (username = "", password = "") {
		var xhr = new XMLHttpRequest();
		var url = "http://188.34.167.200:8082/user/login";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				if (xhr.responseText != ""){
					var json = JSON.parse(xhr.responseText);
					localStorage.setItem('loginstate', true);
					alert(json.userId + ", " + json.username + ", " + json.email + ", " + json.profilePhotoLink);
					window.location.href = "/src/pages/popup.html";
				}
				else {
					localStorage.setItem('loginstate', false);
					alert("login failed");
				}
			}
		};
		var data = JSON.stringify({"username": username, "password": password});
		xhr.send(data);
    }

    static logout ()
    {
        window.localStorage.clear();
        window.location.reload();
        window.location.replace('/src/pages/popup.html');
    }
}