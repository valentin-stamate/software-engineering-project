import UserController from './UserController.js';

document.getElementById("send-button").addEventListener("click", () => {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	UserController.login(username, password);
});