import UserController from './UserController.js';

document.getElementById("send-button").addEventListener("click", () => {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if (!(username === "" || password === "")) {
		if (password.length > 7) {
			UserController.login(username, password);
		}
		else {
			showAlert("The password must be at least 8 characters long!");
		}
	}
	else if (username === ""){
		showAlert("Please complete your username!");
	}
	else {
		showAlert("Please complete your password!");
	}
});