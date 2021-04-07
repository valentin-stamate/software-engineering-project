import UserController from "./../../src/pages/login/UserController.js";

const loginstate = localStorage.getItem('loginstate');

if (loginstate)
{
	document.getElementById('logout-btn').classList.remove("hidden");
	document.getElementById('login-btn').classList.add('hidden');
}
else {
	document.getElementById('logout-btn').classList.add("hidden");
	document.getElementById('login-btn').classList.remove('hidden');
}

document.getElementById("logout-btn").addEventListener('click', () => {
    UserController.logout();
});