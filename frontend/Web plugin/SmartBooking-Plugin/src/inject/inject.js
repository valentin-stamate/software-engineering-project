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
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(response);
        //forecast:   response.forecast
        //covid:      response.covid
        addStatistics(response);
    });
}
getStatistics();

async function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML =
        `<section id="covid-statistics" style="width:100% height:100%"><canvas id="covid-chart"></canvas></section>`;
    stats_div.innerHTML += `<section id="weather-statistics"></section>`

    addPolution();
    addForecastCards(_stats.forecast);
    addCovidStatistics(_stats.covid);
}

//add covid info section
async function addCovidStatistics(covid) {
    //TO DO
    setGlobalLabel();
    addCovidChart(covid);
}

//add forecast section
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
        } else {
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

// button events
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
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(JSON.stringify(response));
    });
}

// covid chart section
var labels = [];

function setGlobalLabel() {
    for (var i = 13; i >= 0; i--) {
        var today = new Date();
        today.setDate(today.getDate() - i);
        var day = today.getDate();
        var month = today.getMonth() + 1;
        var year = today.getFullYear();

        if (month < 10) {
            var result = day + '/0' + month;
        } else {
            var result = day + '/' + month;
        }
        labels.push(result);
    }
}

// folosim functia cand userul nu mai are cursorul pe hotel ca sa putem adauga alte valori
function clearLabel() {
    labels = [];
}

var randomPossibleCase = [400, 388, 9999, 5444, 100, 399, 564, 324, 234, 234, 123, 424, 342, 655];
var randomDeathCase = [10, 255, 246, 888, 343, 4900, 3233, 3423, 4234, 2323, 2344, 2332, 1000, 879];

//random values added by force

function addCovidChart(covid) {
    const ch = document.getElementById("covid-chart");
    let chart = new Chart(ch, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                    label: "Current cases",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "rgba(0, 0, 255, 0.5)",
                    borderColor: "rgba(0, 0, 255, 1)",
                    borderCapStyle: 'butt',
                    broderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'mitter',
                    pointBorderColor: "rgba(92, 86, 110, 1)",
                    pointBackgoundColor: "#fff",
                    pointBorderWidth: 1,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(208, 86, 165, 0.86)",
                    pointHoverBorderColor: "rgba(208, 86, 10, 0.86)",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: randomPossibleCase,
                },
                {
                    label: "Current deaths",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "rgba(255, 0, 0, 0.5)",
                    borderColor: "rgba(255, 0, 0, 1)",
                    borderCapStyle: 'butt',
                    broderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'mitter',
                    pointBorderColor: "rgba(92, 86, 110, 1)",
                    pointBackgoundColor: "#fff",
                    pointBorderWidth: 1,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(208, 86, 165, 0.86)",
                    pointHoverBorderColor: "rgba(208, 86, 10, 0.86)",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: randomDeathCase,
                }
            ]
        },
        options: {
            responsive: true,
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true
                }]
            },
            plugins: {
                legend: {
                    display: true,
                    position: 'top',
                    align: 'center'
                },
                title: {
                    display: true,
                    text: 'Covid statistics'
                }
            },
        }
    });
}

function addPolution() {
    let container = `
        <div id="pollution_card">
        </div>
    `;

    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML += container;

    let pollution_container = document.getElementById("pollution_card");

    addPolutionItem(pollution_container, 10);
    addPolutionItem(pollution_container, 12);
    addPolutionItem(pollution_container, 16);

    let image = `
        <img id="pollution_icon" src="" alt="air pollution">
    `;

    pollution_container.innerHTML += image;

    let pollution_icon = document.getElementById("pollution_icon");

    pollution_icon.src = chrome.runtime.getURL("src/images/air_polution.png");

}

function addPolutionItem(pollution_container, value) {
    let pollution_item = `
    <div class="pollution_card__content">
        <p>air quality index</p>
        <p class="pollution_card__content__value"><b>${value}</b></p>
    </div>
    `;

    pollution_container.innerHTML += pollution_item;
}