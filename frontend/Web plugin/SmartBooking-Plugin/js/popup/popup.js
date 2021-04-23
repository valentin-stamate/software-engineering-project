import UserController from "./../../src/pages/login/UserController.js";

const loginstate = window.localStorage.getItem('loginstate');

async function toggleLoginlogout() {
	if (loginstate == "true") {
		document.getElementById('logout-btn').classList.remove("hidden");
		document.getElementById('login-btn').classList.add('hidden');
		let credentials = window.localStorage.getItem('credentials');
		if (credentials !== "undefined" && UserController.autoLogin) {
			console.log(credentials);
			UserController.login(credentials.login, credentials.password);
		}
	}
	else {
		document.getElementById('logout-btn').classList.add("hidden");
		document.getElementById('login-btn').classList.remove('hidden');
	}
}
toggleLoginlogout();

document.getElementById("logout-btn").addEventListener('click', () => {
	UserController.logout();
});