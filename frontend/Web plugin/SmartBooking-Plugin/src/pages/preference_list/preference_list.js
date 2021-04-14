const loginstate = localStorage.getItem('loginstate');

if (loginstate == "true")
{
	document.getElementById('save-list').classList.remove("hidden");
	document.getElementById('log-to-save').classList.add('hidden');
}
else {
	document.getElementById('save-list').classList.add("hidden");
	document.getElementById('log-to-save').classList.remove('hidden');
}

var locations = [];
chrome.storage.sync.get('locations', value => {
    locations = value.locations;
	var control_group = document.getElementById("control-group");
	
	if (locations){
		control_group.innerHTML = "";
		locations.forEach(element => {
			console.log(element);
			control_group.insertAdjacentHTML('beforeend',
				`<label class="b-contain">
					<span class="remove">X</span>
					<span class="hotel-name">${element}</span>
					<input type="checkbox">
					<div class="b-input"></div>
				</label>`)
		});
		if (locations.length == 0) {
			control_group.innerText = "Nothing here yet";
		}
	}
	
	addRemoveButton();
});


function addRemoveButton(){
	var remove = document.getElementsByClassName("remove");
	var i;
	for (i = 0; i < remove.length; i++) {
		remove[i].addEventListener('click', (el) => {
			var label = el.target.parentElement;
			label.style.display = "none";
			removeLocation(label.getElementsByClassName("hotel-name")[0].innerText);
			label.remove();
		});
	}
}

function removeLocation(loc)
{
	for( var i = 0; i < locations.length; i++){
		if ( locations[i] == loc) { 
			locations.splice(i, 1); 
		}
	}
	
	if (locations.length == 0) {
		document.getElementById("control-group").innerText = "Nothing here yet";
	}
	chrome.storage.sync.set({ "locations": locations });
}

document.getElementById("log-to-save").addEventListener('click', () => {
    window.location.href = "/src/pages/login/login.html";
});

