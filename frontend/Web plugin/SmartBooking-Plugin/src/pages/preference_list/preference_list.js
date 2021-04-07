import UserController from './../login/UserController.js';

const loginstate = localStorage.getItem('loginstate');

if (loginstate)
{
	document.getElementById('save-list').classList.remove("hidden");
	document.getElementById('log-to-save').classList.add('hidden');
}
else {
	document.getElementById('save-list').classList.add("hidden");
	document.getElementById('log-to-save').classList.remove('hidden');
}

chrome.storage.local.get('message', value => {
    var locations = value.message;
	var control_group = document.getElementById("control-group");
	
	if (locations){
		control_group.innerHTML = "";
		locations.forEach(element => {
			console.log(element);
			control_group.insertAdjacentHTML('beforeend',
				`<label class="b-contain">
					<span class="remove">X</span>
					<span>${element}</span>
					<input type="checkbox">
					<div class="b-input"></div>
				</label>`)
		});
	}
	control_group.innerHTML = "Nothing here yet";
	addRemoveButton();
});


function addRemoveButton(){
	var remove = document.getElementsByClassName("remove");
	var i;
	for (i = 0; i < remove.length; i++) {
		remove[i].addEventListener('click', (el) => {
			var label = el.target.parentElement;
			label.style.display = "none";
			removeLocation(label.children[1].innerHTML);
		});
	}
}

function removeLocation(loc)
{
	chrome.storage.local.get('message', value => {
		var locations = value.message;
		
		for( var i = 0; i < locations.length; i++){
			if ( locations[i] == loc) { 
				locations.splice(i, 1); 
			}
		}
		chrome.storage.local.set({ "message": locations });
	});
}

document.getElementById("log-to-save").addEventListener('click', () => {
    window.location.href = "/src/pages/login/login.html";
});

