function getHotelAdress(){
    var location_element= document.getElementsByClassName("hp_address_subtitle");
    var adress = location_element[0].innerText;
    return adress;
};

function getName(){
    var name_element = document.getElementById('hp_hotel_name');
    var name = name_element ? name_element.innerText : "";
    return name;
}
var hotelName = getName().trim();
var hotelAdress = getHotelAdress().trim();
console.log("Hotel name = " + hotelName);
console.log("Hotel adress = " + hotelAdress);

var statistics = {};

// Experimental function
function getStatistics() {
    console.log("requesting statistics");
	var _data = {
		sendStatistics: true,
	    hotelName : hotelName,
		hotelLocation: destination
	}
    chrome.runtime.sendMessage(_data, function(response) {
		statistics = response;
		console.log(JSON.stringify(response));
	});
}
getStatistics();

function getPopupHtml(statistics)
{
	let popup_str =
`<div id="main-popup">
	<header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`
	return popup_str;
}

document.body.getElementsByClassName("hp-description")[0].insertAdjacentHTML('beforebegin', getPopupHtml(statistics));

var show = true;

function hidePopup(){
	var popup = document.getElementById("popup");
	var btn = document.getElementById("hide-btn");
    if (show) {
		btn.innerHTML = "+"
        popup.style.display = "none";
    } else {
		btn.innerHTML = "&#8213";
        popup.style.display = "block";
    }
    show = !show;
}

document.getElementById("hide-btn").addEventListener('click', hidePopup);

function sendPreferences() {
	console.log("sending preference");
	var _data = {
		sendStatistics: false,
	    hotelName : hotelName,
		hotelLocation: destination
	}
    chrome.runtime.sendMessage(_data, function(response) {
		console.log(JSON.stringify(response));
	});
}

document.getElementById("send-btn").addEventListener('click', sendPreferences);
