export default class UserController {
    static login (username = "", password = "") {
		var url = "http://188.34.167.200:8082/user/login";
		
		let _data = {
			"username": username, 
			"password": password
		};
		
		fetch(url, {
		  method: "POST",
		  body: JSON.stringify(_data),
		  headers: {
			  "Content-type": "application/json; charset=UTF-8"
		  }
		}).then(handleLoginResponse).catch(err => {
			console.log(err);
		});
    }

    static logout ()
    {
        window.localStorage.clear();
        window.location.reload();
        window.location.href = '/src/pages/popup.html';
    }
}

function handleLoginResponse(response){
	response.json().then(function(json){
		if (response.status == 200){
			console.log(json);
			var token = json.authorizationToken;
			var username = json.username;
			window.localStorage.setItem('loginstate', "true");
			window.localStorage.setItem('token', token);
			showAlert("login succesful - " + username + ", " + token, true);
		}else {
			localStorage.setItem('loginstate', "false");
			showAlert("login failed - " + json.message);
		}
	});
}
	