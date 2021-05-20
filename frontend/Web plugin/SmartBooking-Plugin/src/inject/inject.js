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
        hotelIdentifier: identifier
    }
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(response);
        //forecast:         response.forecast
        //covid:            response.covid
        //covid_news:       response.covid_news
        //air_pollution:    response.airPollution
        addStatistics(response);
    });
}
getStatistics();

async function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML = `<section id="covid-statistics"><canvas id="covid-chart"></canvas></section>`;
    stats_div.innerHTML += `<section id="covid-news"></section>`;
    stats_div.innerHTML += `<section id="weather-statistics"></section>`;
    stats_div.innerHTML += `<section id="pollution_card"></section>`;
    stats_div.innerHTML += `<section id="criminality_card"></section>`;
    stats_div.innerHTML += `<section id="living_cost_card"></section>`;
    stats_div.innerHTML += `<section id="restaurants_card"></section>`;
    stats_div.innerHTML += `<section id="rating"></section>`;

    addRestaurants(_stats.restaurants);
    addCostOfLiving(_stats.living_cost, _stats.gasoline);
    addRating(_stats.rating);
    addCriminality(_stats.criminality);
    addPolution(_stats.airPollution);
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
    if (!news)
        return;
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
    for (let i = 0; i < 5; i++) {
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
var nrCovidDays = 14;

function setGlobalLabel() {
    for (var i = nrCovidDays - 1; i >= 0; i--) {
        var today = new Date();
        today.setDate(today.getDate() - i - 1);
        var day = (today.getDate() < 10) ? '0' + today.getDate() : today.getDate();
        var month = (today.getMonth() + 1 < 10) ? '0' + (today.getMonth() + 1) : (today.getMonth() + 1);
        var year = today.getFullYear();

        var result = day + '/' + month + '/' + year;
        labels.push(result);
    }
}

var randomPossibleCase = [];
var randomDeathCases = [];

function initializeCovidData(covid_data) {
    covid_data.forEach((item) => {
        randomPossibleCase.push(item.newCases);
        randomDeathCases.push(item.newDeaths);
    })
}

function addCovidChart(covid_data) {
    initializeCovidData(covid_data);
    const ch = document.getElementById("covid-chart");
    let chart = new Chart(ch, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
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

async function addPolution(airPollution) {
    let pollution_container = document.getElementById("pollution_card");

    if (!airPollution) {
        airPollution = {
            airQualityIndex: 'N/A',
            pm10ValueIndex: 'N/A',
            pm25ValueIndex: 'N/A',
            waterPollutionIndex: 'N/A',
            o3ValueIndex: 'N/A',
            no2ValueIndex: 'N/A',
            so2ValueIndex: 'N/A',
            covalueIndex: 'N/A',
        }
    }

    let image = `
    <table>
        <tbody id="polution_card__container">
            <tr id="pollution_first"></tr>
            <tr id="pollution_second"></tr>
        </tbody>
    </table>
    <img id="pollution_icon" src="" alt="criminality">`;

    pollution_container.innerHTML += image;
    let pollution_icon = document.getElementById("pollution_icon");
    pollution_icon.src = chrome.runtime.getURL("src/images/air_polution.png");


    pollution_container = document.getElementById("pollution_first");
    addPolutionItem(pollution_container, 'air quality index', airPollution.airQualityIndex);
    addPolutionItem(pollution_container, 'pm10 value', airPollution.pm10ValueIndex);
    addPolutionItem(pollution_container, 'pm25 value', airPollution.pm25ValueIndex);
    addPolutionItem(pollution_container, 'water pollution', airPollution.waterPollutionIndex);

    pollution_container = document.getElementById("pollution_second");
    addPolutionItem(pollution_container, 'O3 value', airPollution.o3ValueIndex);
    addPolutionItem(pollution_container, 'NO2 value', airPollution.no2ValueIndex);
    addPolutionItem(pollution_container, 'SO2 value', airPollution.so2ValueIndex);
    addPolutionItem(pollution_container, 'CO value', airPollution.covalueIndex);


}

async function addPolutionItem(pollution_container, text, value) {
    let pollution_item = ` <td>
    <div class="pollution_card__content">
        <p>${text}</p>
        <p class="pollution_card__content__value"><b>${(value == null)?'N/A':value}</b></p>
    </div></td>
    `;

    pollution_container.innerHTML += pollution_item;

}

async function addCriminality(criminality) {
    let criminality_container = document.getElementById("criminality_card");

    if (criminality === null) {
        criminality = {
            crimeIndex: 'N/A',
            robbingIndex: 'N/A',
            brokenHomesIndex: 'N/A',
            stolenCarsIndex: 'N/A',
            attackedIndex: 'N/A',
            drugsIndex: 'N/A',
            violentCrimesIndex: 'N/A',
            insultedIndex: 'N/A'
        }
    }

    let image = `
    <table>
        <tbody id="criminality_card__container">
            <tr id="criminality_first"></tr>
            <tr id="criminality_second"></tr>
        </tbody>
    </table>
    <img id="criminality_icon" src="" alt="criminality">
    `;

    criminality_container.innerHTML += image;

    let criminality_icon = document.getElementById("criminality_icon");

    criminality_icon.src = chrome.runtime.getURL("src/images/burglar.png");

    criminality_container = document.getElementById("criminality_first");

    addCriminalityItem(criminality_container, 'crime index', criminality.crimeIndex);
    addCriminalityItem(criminality_container, 'robbing index', criminality.robbingIndex);
    addCriminalityItem(criminality_container, 'broken homes index', criminality.brokenHomesIndex);
    addCriminalityItem(criminality_container, 'stolen cars index', criminality.stolenCarsIndex);

    criminality_container = document.getElementById("criminality_second");

    addCriminalityItem(criminality_container, 'attacked index', criminality.attackedIndex);
    addCriminalityItem(criminality_container, 'drugs index', criminality.drugsIndex);
    addCriminalityItem(criminality_container, 'violent crimes index', criminality.violentCrimesIndex);
    addCriminalityItem(criminality_container, 'insulted index', criminality.insultedIndex);
}

async function addCriminalityItem(criminality_container, text, value) {
    let criminality_item = `
    <td>
        <div class="criminality_card__content">
            <p>${text}</p>
            <p class="criminality_card__content__value"><b>${value}</b></p>
        </div>
    </td>
    `;

    criminality_container.innerHTML += criminality_item;
}

async function addRating(reviews) {

    if (reviews.message == "Hotel not found") {
        reviews = {
            length: 0
        };
    }

    let rating = document.getElementById("rating");

    let rating_content = `
            <b>Hotel rating:</b>
            <p>( ${reviews.length} reviews )</p>
            <div id="rating__stars">
            </div>
    `;

    rating.innerHTML += rating_content;

    let rating_stars = document.getElementById("rating__stars");

    let sum = 0;
    for (i = 0; i < reviews.length; i++) {
        sum += reviews[i].userRating;
    }

    let mean = Math.round(sum / reviews.length);

    for (i = 1; i <= 10; i++) {
        let checked = i <= mean;
        addStar(rating_stars, checked);
    }

}

async function addStar(rating_stars_container, checked = false) {
    let star_file_name = checked ? 'checked_star.png' : 'unchecked_star.png';
    let source = chrome.runtime.getURL('src/images/' + star_file_name);
    rating_stars_container.innerHTML += `<img class="rating_star" src="${source}" alt=""></img>`
}

async function addCostOfLiving(living_cost, gasoline) {

    let living_cost_container = document.getElementById("living_cost_card");

    let container = `
    <table>
        <tbody id="living_cost_card__container">
            <tr id="living_cost_first"></tr>
            <tr id="living_cost_second"></tr>
        </tbody>
    </table>
    <img id="living_cost_icon" src="" alt="living_cost">
    `;

    living_cost_container.innerHTML += container;

    let living_cost_icon = document.getElementById("living_cost_icon");
    living_cost_icon.src = chrome.runtime.getURL("src/images/living.png");

    living_cost_container = document.getElementById("living_cost_first");

    addliving_costItem(living_cost_container, 'gasoline price', living_cost.gasolinePrice + " /l");
    addliving_costItem(living_cost_container, 'gasoline price country', gasoline.price + " " + gasoline.measure);
    addliving_costItem(living_cost_container, 'domestic beer', living_cost.domesticBeerPrice);
    addliving_costItem(living_cost_container, 'water price', living_cost.waterPrice);

    living_cost_container = document.getElementById("living_cost_second");

    addliving_costItem(living_cost_container, 'cigarettes price', living_cost.cigarettesPrice);
    addliving_costItem(living_cost_container, 'bus ticket', living_cost.busTicketPrice);
    addliving_costItem(living_cost_container, 'taxi', living_cost.taxiKmPrice + "/km");
    addliving_costItem(living_cost_container, 'monthly cost', living_cost.monthlyPersonCost);
}

async function addliving_costItem(living_cost_container, text, value) {
    let living_cost_item = `
    <td>
        <div class="living_cost_card__content">
            <p>${text}</p>
            <p class="living_cost_card__content__value"><b>${value}</b></p>
        </div>
    </td>
    `;

    living_cost_container.innerHTML += living_cost_item;
}


async function addRestaurants(restaurants) {

    let restaurants_container = document.getElementById("restaurants_card");

    let container = `
    <table>
        <tbody id="restaurants_card__container">
            <tr id="restaurants_first"></tr>
            <tr id="restaurants_second"></tr>
        </tbody>
    </table>
    <img id="restaurants_icon" src="" alt="restaurants">
    `;

    restaurants_container.innerHTML += container;

    let restaurants_icon = document.getElementById("restaurants_icon");
    restaurants_icon.src = chrome.runtime.getURL("src/images/restaurant.png");

    restaurants_container = document.getElementById("restaurants_first");

    addrestaurantsItem(restaurants_container, 'simple 1 person meal', restaurants.simpleMeal1PersonPrice);
    addrestaurantsItem(restaurants_container, 'full 2 persons meal', restaurants.fullMeal2PersonsPrice);
    addrestaurantsItem(restaurants_container, 'beer draught', restaurants.beerDraughtPrice);
    addrestaurantsItem(restaurants_container, 'beer bottle', restaurants.beerBottlePrice);

    restaurants_container = document.getElementById("restaurants_second");

    addrestaurantsItem(restaurants_container, 'capucciono', restaurants.cappuccinoPrice);
    addrestaurantsItem(restaurants_container, 'water', restaurants.waterPrice);
    addrestaurantsItem(restaurants_container, 'mc meal', restaurants.mcMealPrice);
    addrestaurantsItem(restaurants_container, 'coke', restaurants.cokePrice);
}

async function addrestaurantsItem(restaurants_container, text, value) {
    let restaurants_item = `
    <td>
        <div class="restaurants_card__content">
            <p>${text}</p>
            <p class="restaurants_card__content__value"><b>${value}</b></p>
        </div>
    </td>
    `;

    restaurants_container.innerHTML += restaurants_item;
}