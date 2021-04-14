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
		}).then(handleLoginresponse).catch(function(err){
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

function handleLoginresponse(response){
	response.json().then(function(json){
		if (response.status !== 200)
		{
			localStorage.setItem('loginstate', "false");
			showAlert("login failed - " + json.message);
		}else {
			window.localStorage.setItem('loginstate', "true");
			window.localStorage.setItem('token', json.token);
			showAlert(json.message + ", " + json.token);
			window.location.href = "/src/pages/popup.html";
		}
	});
}
	