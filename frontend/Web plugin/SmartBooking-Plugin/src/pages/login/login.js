import UserController from './UserController.js';

document.getElementById("send-button").addEventListener("click", () => {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if (!(username === "" || password === "")) {
		if (password.length > 7) {
			UserController.login(username, password);
		}
		else {
			alert("The password must be at least 8 characters long!");
		}
	}
	else if (username === ""){
		alert("Please complete your username!");
	}
	else {
		alert("Please complete your password!");
	}
});