function getHotelAdress() {
    var location_element = document.getElementsByClassName("hp_address_subtitle");
    var adress = location_element[0].innerText;
    return adress.trim().replace("\n", "");
};

function getName() {
    var name_element = document.getElementById('hp_hotel_name');
    var name = name_element ? name_element.childNodes[2].nodeValue : "";
    return name.trim().replace("\n", "");
}

function addPopupHtml() {
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
addPopupHtml();

var hotelName = getName();
var hotelAdress = getHotelAdress();
console.log("Hotel name = " + hotelName);
console.log("Hotel adress = " + hotelAdress);

function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML = "";

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
    let weatherInfoSection = `
    <h4>Weather information:</h4>
    <div id="weather_info_list">
    </div>
    `

    let weatherCard = `
    <div class="weather_card">
        <h2 class="weather_card__day">Monday</h2>
        <div class="weather_card__info_main">
            <h3 class="weather_card__description">Cloudy</h3>
            <div>
                <h3 class="weather_card__description weather_card__info">Wind 10km/h</h3>
                <h3 class="weather_card__description weather_card__info">Precip 0%</h3>
                <h3 class="weather_card__description weather_card__info">Air pollution 10%</h3>
                <h3 class="weather_card__description weather_card__info">Air pressure 10 bar</h3>
            </div>
        </div>
        <div class="weather_card__main">
            <div class="weather_card__sky">
                <img src="http://openweathermap.org/img/wn/10d@2x.png">
            </div>
            <h1 class="weather_card__avg_temp">23°</h1>
        </div>
        <table class="weather_card__data">
            <tr class="weather_card__hours">
                <td></td>
                <td>3:00</td>
                <td>6:00</td>
                <td>9:00</td>
                <td>12:00</td>
            </tr>
            <tr class="weather_card__temp_am">
                <td>AM</td>
                <td>30°</td>
                <td>34°</td>
                <td>36°</td>
                <td>34°</td>
            </tr>
            <tr class="weather_card__temp_pm">
                <td>PM</td>
                <td>17°</td>
                <td>22°</td>
                <td>19°</td>
                <td>23°</td>
            </tr>
        </table>
    `;

    /*
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
*/
    stats_div.innerHTML = hotelInfoSection + "\n" + covidInfoSection + "\n" +
        "\n" + weatherInfoSection + "\n"; //+ airPolutionSection;

    let weatherInfo = document.getElementById("weather_info_list");

    // aici inserez de 5 ori weather_card
    for (i = 0; i < 5; i++) {
        weatherInfo.innerHTML += weatherCard;
    }
}

// Experimental function
function getStatistics() {
    console.log("requesting statistics");
    var _data = {
        sendStatistics: true,
        hotelName: hotelName,
        hotelLocation: destination
    }
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(response);
        addStatistics(response);
    });
}
getStatistics();

var show = true;

function hidePopup() {
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
    let hotel_path = window.location.pathname;
    alert(hotel_id);
    var _data = {
        sendStatistics: false,
        hotelName: hotelName,
        hotelLocation: destination,
        hotelPath: hotel_path
    }
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(JSON.stringify(response));
    });
}