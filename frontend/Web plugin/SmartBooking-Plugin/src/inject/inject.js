function getHotelAdress(){
    var location_element= document.getElementsByClassName("hp_address_subtitle");
    var adress = location_element[0].innerText;
    return adress.trim().replace("\n", "");
};

function getName(){
    var name_element = document.getElementById('hp_hotel_name');
    var name = name_element ? name_element.childNodes[2].nodeValue : "";
    return name.trim().replace("\n", "");
}
var hotelName = getName();
var hotelAdress = getHotelAdress();
console.log("Hotel name = " + hotelName);
console.log("Hotel adress = " + hotelAdress);

function addPopupHtml()
{
	let popup_str =
`<div id="main-popup">
	<header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
		<div id="statistics-container"></div>
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`
	document.body.getElementsByClassName("hp-description")[0].insertAdjacentHTML('beforebegin', popup_str);
	document.getElementById("send-btn").addEventListener('click', sendPreferences);
	document.getElementById("hide-btn").addEventListener('click', hidePopup);
}

function addStatistics(_stats)
{
	let stats_div = document.getElementById("statistics-container");
	
	let hotelInfoSection =
`<section id="hotel-info">
	<h3>${_stats.hotel.hotelName}</h3>
	<h5>${_stats.hotel.locationName}</h5>
</section>`;
	let covidInfoSection =
`<section id="covid-info">
	<h4>Covid information:</h4>
	<ul id="covid_info_list">
	</ul>
</section>`;
	let weatherInfoSection =
`<section id="weather-info">
	<h4>Weather information:</h4>
	<ul id="weather_info_list">
		<li>Coordonates - { Latitude: ${_stats.weather.coord.lat}, Longitude: ${_stats.weather.coord.lon} }</li>
		<li>Temperature data:
			<ul>
				<li>Avg. temperature: ${_stats.weather.main.temp}&#186;C</li>
				<li>Max temperature: ${_stats.weather.main.temp_max}&#186;C</li>
				<li>Min temperature: ${_stats.weather.main.temp_min}&#186;C</li>
				<li>Feels like: ${_stats.weather.main.feels_like}&#186;C</li>
			</ul>
		</li>
	</ul>
</section>`;
	let airPolutionSection =
`<section id="air-pollution">
	<h4>Air pollution information:</h4>
	<ul id="air_info_list">
		<li>Air humidity: ${_stats.weather.main.humidity}</li>
		<li>Air pressure: ${_stats.weather.main.pressure}</li>
		<li>Air quality index: ${_stats.airPollution.airQualityIndex}</li>
		<li>Air particle matter (up to 10 micrometers): ${_stats.airPollution.pm10Value}</li>
	</ul>
</section>`;
	
    stats_div.innerHTML = hotelInfoSection + "\n" + covidInfoSection + "\n" +
							"\n" + weatherInfoSection + "\n" + airPolutionSection;	
}

// Experimental function
function getStatistics() {
    console.log("requesting statistics");
	var _data = {
		sendStatistics: true,
	    hotelName : hotelName,
		hotelLocation: destination
	}
    chrome.runtime.sendMessage(_data, function(response) {
		console.log(response);
		addPopupHtml();
		addStatistics(response);
	});
}

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

getStatistics();



