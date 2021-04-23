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

var hotelName = getName();
var hotelAdress = getHotelAdress();
console.log("Hotel name = " + hotelName);
console.log("Hotel adress = " + hotelAdress);

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

async function getStatistics() {
    console.log("requesting statistics");
    var _data = {
        sendStatistics: true,
        hotelLocation: destination,
    }
    chrome.runtime.sendMessage(_data, function (response) {
        console.log(response);
        //forecast:   response.forecast
        //covid:      response.covid
        addStatistics(response);
    });
}
getStatistics();

function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML = `<section id="weather-statistics"></section>`;
    stats_div.innerHTML += `<section id="weather-statistics"></section>`

    addForecastCards(_stats.forecast);
    addCovidStatistics(_stats.covid);
}

async function addCovidStatistics(covid){
    //TO DO
}

async function addForecastCards(forecast) {
    if (!forecast) return;
    
    let list = forecast.list;

    let curr_date = new Date();

    let j = 0;
    for (let i = 0; i < 4; i++) {
        let card_info = [];
        while (sameDay(curr_date, new Date(list[j].dt * 1000))) {
            card_info.push(list[j]);
            j++;
        }
        card_info.push(list[j]);
        j++
        addForecastItem(card_info);
        curr_date.setDate(curr_date.getDate() + 1);
    }
}

async function addForecastItem(_forecast) {
    let index = Math.floor(_forecast.length / 2);
    let days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    let day = days[new Date(_forecast[0].dt * 1000).getDay()];

    let avg_temp = (_forecast[index].main.temp - 273.15).toFixed(2);
    let icon = _forecast[index].weather[0].icon;
    let title = _forecast[index].weather[0].description;
    let wind_speed = _forecast[index].wind.speed;
    let humidity = _forecast[index].main.humidity;
    let pressure = _forecast[index].main.pressure;

    let temps = [];

    for (i = 7; i >= 0; i--) {
        if (i < 8 - _forecast.length) {
            temps.push("-");
        }
        else {
            let temp = (_forecast[_forecast.length - (8 - i)].main.temp - 273.15).toFixed(1);
            temps.push(temp);
        }
    }

    let weatherCard =
        `<div class="weather_card">
    <h2 class="weather_card__day">${day}</h2>
    <div class="weather_card__info_main">
        <h3 class="weather_card__description">${title}</h3>
        <div class="weather_card__infos">
            <h3 class="weather_card__description weather_card__info">Wind ${wind_speed}m/s</h3>
            <h3 class="weather_card__description weather_card__info">Humidity ${humidity}%</h3>
            <h3 class="weather_card__description weather_card__info">Air pressure ${pressure}hPa</h3>
        </div>
    </div>
    <div class="weather_card__main">
        <div class="weather_card__sky">
            <img src="http://openweathermap.org/img/wn/${icon}.png">
        </div>
        <h1 class="weather_card__avg_temp">${avg_temp}°</h1>
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
            <td>${temps[7]}°</td>
            <td>${temps[6]}°</td>
            <td>${temps[5]}°</td>
            <td>${temps[4]}°</td>
        </tr>
        <tr class="weather_card__temp_pm">
            <td>PM</td>
            <td>${temps[3]}°</td>
            <td>${temps[2]}°</td>
            <td>${temps[1]}°</td>
            <td>${temps[0]}°</td>
        </tr>
    </table>
</div>`;

    let weatherInfo = document.getElementById("weather-statistics");

    weatherInfo.innerHTML += weatherCard;
}

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
    var _data = {
        sendStatistics: false,
        hotelName: hotelName,
        hotelLocation: destination,
        hotelPath: hotel_path
    }
    chrome.runtime.sendMessage(_data, function (response) {
        console.log(JSON.stringify(response));
    });
}