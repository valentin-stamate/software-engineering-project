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
        //covid_news: response.covid_news
        addStatistics(response);
    });
}
getStatistics();

async function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML =
        `<section id="covid-statistics"><canvas id="covid-chart"></canvas></section>`;
    stats_div.innerHTML += `<section id="covid-news"></section>`;
    stats_div.innerHTML += `<section id="weather-statistics"></section>`;

    addCovidStatistics(_stats.covid);
    addCovidNews(_stats.covid_news);
    addForecastCards(_stats.forecast);
}

//add covid info section
async function addCovidStatistics(covid) {
    //TO DO
    setGlobalLabel();
    let covid_data = covid.items.slice(-nrCovidDays);
    addCovidChart(covid_data);
}

async function addCovidNews(covid_news) {
    let news = covid_news[0];
    //TO DO
    let covidNews = document.getElementById("covid-news");

    let covidNewsCard = `<h4 style="padding:0; margin:2px 0;">${news.title}</h4>
    <p style="padding:0; margin:2px 0;">${news.snippet}</p>
    <a href="${news.link}">${news.displayLink}</a>`;

    covidNews.innerHTML += covidNewsCard;
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
    chrome.runtime.sendMessage(_data, function (response) {
        console.log(JSON.stringify(response));
    });
}

// covid chart section
var labels = [];
var nrCovidDays = 14;

function setGlobalLabel(){
  for(var i=nrCovidDays-1;i>=0;i--){
    var today = new Date();
    today.setDate(today.getDate()-i-1);
    var day = (today.getDate()<10)? '0' + today.getDate() : today.getDate();
    var month = (today.getMonth()+1<10)? '0' + (today.getMonth()+1) : (today.getMonth()+1);
    var year = today.getFullYear();
    
    var result = day + '/' + month + '/' + year;
    labels.push(result);
  }
}

var randomPossibleCase = [];
var randomDeathCases = [];

function initializeCovidData(covid_data)
{
    covid_data.forEach((item) => {
        randomPossibleCase.push(item.newCases);
        randomDeathCases.push(item.newDeaths);
    })
}


function addCovidChart(covid_data) {
    initializeCovidData(covid_data);
	const ch = document.getElementById("covid-chart");
	let chart = new Chart(ch,{
		type: 'line',
		data: {
			labels: labels,
			datasets: [
				{
					label: "New cases",
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
					label: "New deaths",
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
					data: randomDeathCases,
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
				display: false
			  }
			},
		}
	});
}