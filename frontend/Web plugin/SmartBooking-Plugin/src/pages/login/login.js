import UserController from './UserController.js';

document.getElementById("send-button").addEventListener("click", () => {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if (!(username === "" || password === "")) {
		UserController.login(username, password);
	}
	else if (username === ""){
		showAlert("Please complete your username!");
	}
	else {
		showAlert("Please complete your password!");
	}
});