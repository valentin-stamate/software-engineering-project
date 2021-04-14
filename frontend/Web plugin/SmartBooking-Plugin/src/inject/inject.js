var statistics = {};

function getLocation(){
    var location_element= document.getElementsByClassName("hp_address_subtitle");
    var location = location_element[0].innerText;
    console.log(location);
    return location;
};

function getName(){
    var name_element = document.getElementById('hp_hotel_name');
    var name = name_element ? name_element.innerText : "";
    console.log(name);
    return name;
}
var hotelName = getName().trim();
var hotelLocation = getLocation().trim();

// Experimental function
function getStatistics() {
    console.log("requesting statistics");
	var _data = {
		sendStatistics: true,
	    hotelName : hotelName,
		hotelLocation: hotelLocation
	}
    chrome.runtime.sendMessage(_data, function(response) {
		statistics = response;
		console.log(JSON.stringify(response));
	});
}
getStatistics();

document.body.getElementsByClassName("hp-description")[0].insertAdjacentHTML('beforebegin', `
<div id="main-popup">
    <header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`);

var imgURL = chrome.extension.getURL("/src/images/background.webp");
document.getElementById("popup").style.backgroundImage = `url(${imgURL})`;

var show = true;

var hidePopup = function(){
	var popup = document.getElementById("popup");
    if (show) {
        popup.style.display = "none";
    } else {
        popup.style.display = "block";
    }
    show = !show;
}

var sendPreferences = function() {
	console.log("sending preference");
	var _data = {
		sendStatistics: false,
	    hotelName : hotelName,
		hotelLocation: hotelLocation
	}
    chrome.runtime.sendMessage(_data, function(response) {
		console.log(JSON.stringify(response));
	});
}

document.getElementById("hide-btn").addEventListener('click', hidePopup);
document.getElementById("send-btn").addEventListener('click', sendPreferences);
