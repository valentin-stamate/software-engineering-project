async function getStatistics() {
    console.log("requesting statistics");
    var _data = {
        sendStatistics: true,
        hotelLocation: destination,
        hotelIdentifier: identifier,
        country: "Romania",
    };
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML = "<h3>Still fetching data from server!</h3>";
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(response);
        addStatistics(response);
    });
}
getStatistics();

async function addStatistics(_stats) {
    let stats_div = document.getElementById("statistics-container");
    stats_div.innerHTML = `
    <button style="cursor:pointer; width:96%; margin:auto; margin-top: 1px;" id="show_covid">
        <b>Toggle covid status chart</b> </button>
    <section class="hidden" id="covid-statistics">
    <canvas id="covid-chart"></canvas></section>`;
    stats_div.innerHTML += `<section id="covid-news"></section>`;
    stats_div.innerHTML += `<section id="weather-statistics"></section>`;
    stats_div.innerHTML += `<section id="pollution_statistics"></section>`;
    stats_div.innerHTML += `
    <button style="cursor:pointer; width:96%; margin:auto; margin-top: 1px;" id="show_co2">
        <b>Toggle CO2 emissions chart</b> </button>
    <section class="hidden" id="co2-statistics">
    <canvas id="co2_statistics-chart"></canvas></section>`;
    stats_div.innerHTML += `<section id="criminality_statistics"></section>`;
    stats_div.innerHTML += `<section id="living_cost_statistics"></section>`;
    stats_div.innerHTML += `<section id="restaurants_statistics"></section>`;
    stats_div.innerHTML += `<section id="healthcare_statistics"></section>`;
    stats_div.innerHTML += `<section id="food_statistics"></section>`;
    stats_div.innerHTML += `<section id="rating"></section>`;


    document.getElementById("show_co2").onclick = toggleCo2PollutionChart;
    document.getElementById("show_covid").onclick = toggleCovidPollutionChart;

    addCovidStatistics(_stats.covid);
    addCovidNews(_stats.covid_news);
    addForecastCards(_stats.forecast);
    addPolution(_stats.airPollution);
    addCo2Emissions(_stats.co2Pollution);
    addCriminality(_stats.criminality);
    addCostOfLiving(_stats.living_cost, _stats.gasoline);
    addRestaurants(_stats.restaurants);
    addFood(_stats.food);
    addHealthcare(_stats.healthcare);
    addRating(_stats.rating);
}

//add covid info section
async function addCovidStatistics(covid) {
    setCovidGlobalLabel();
    let covid_data = covid.items.slice(-nrCovidDays);
    addCovidChart(covid_data);
}

function toggleCovidPollutionChart() {
    let poll_chart = document.getElementById("covid-statistics");
    poll_chart.classList.toggle("hidden");
}

function toggleCo2PollutionChart() {
    let poll_chart = document.getElementById("co2-statistics");
    poll_chart.classList.toggle("hidden");
}

var co2labels = [];
var co2quantity = [];
async function addCo2Emissions(co2) {
    co2.forEach((item) => {
        co2labels.push(item.year);
        co2quantity.push(item.quantity);
    });
    addCo2Chart();
}

async function addCovidNews(covid_news) {
    let news = covid_news[0];
    if (!news) return;
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
        j++;
        addForecastItem(card_info);
        curr_date.setDate(curr_date.getDate() + 1);
    }
}

async function addForecastItem(_forecast) {
    let index = Math.floor(_forecast.length / 2);
    let days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
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

    let weatherCard = `<div class="weather_card">
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

// covid chart section
var covidlabels = [];
var nrCovidDays = 14;

function setCovidGlobalLabel() {
    for (var i = nrCovidDays - 1; i >= 0; i--) {
        var today = new Date();
        today.setDate(today.getDate() - i - 1);
        var day = today.getDate() < 10 ? "0" + today.getDate() : today.getDate();
        var month = today.getMonth() + 1 < 10 ? "0" + (today.getMonth() + 1) : today.getMonth() + 1;
        var year = today.getFullYear();

        var result = day + "/" + month + "/" + year;
        covidlabels.push(result);
    }
}

var randomPossibleCase = [];
var randomDeathCases = [];

function initializeCovidData(covid_data) {
    covid_data.forEach((item) => {
        randomPossibleCase.push(item.newCases);
        randomDeathCases.push(item.newDeaths);
    });
}

function addCo2Chart() {
    const poll_chart = document.getElementById("co2_statistics-chart");
    let chart = new Chart(poll_chart, {
        type: "line",
        data: {
            labels: co2labels,
            datasets: [{
                label: "Co2 quantity in tones",
                fill: false,
                lineTension: 0.1,
                backgroundColor: "rgba(0, 0, 255, 0.5)",
                borderColor: "rgba(0, 0, 255, 1)",
                borderCapStyle: "butt",
                broderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: "mitter",
                pointBorderColor: "rgba(92, 86, 110, 1)",
                pointBackgoundColor: "#fff",
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(208, 86, 165, 0.86)",
                pointHoverBorderColor: "rgba(208, 86, 10, 0.86)",
                pointHoverBorderWidth: 2,
                pointRadius: 1,
                pointHitRadius: 10,
                data: co2quantity,
            }, ],
        },
        options: {
            responsive: true,
            scales: {
                xAxes: [{
                    stacked: true,
                }, ],
                yAxes: [{
                    stacked: true,
                }, ],
            },
            plugins: {
                legend: {
                    display: true,
                    position: "top",
                    align: "center",
                },
                title: {
                    display: false,
                },
            },
        },
    });
}

function addCovidChart(covid_data) {
    initializeCovidData(covid_data);
    const ch = document.getElementById("covid-chart");
    let chart = new Chart(ch, {
        type: "line",
        data: {
            labels: covidlabels,
            datasets: [{
                    label: "New cases",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "rgba(0, 0, 255, 0.5)",
                    borderColor: "rgba(0, 0, 255, 1)",
                    borderCapStyle: "butt",
                    broderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: "mitter",
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
                    borderCapStyle: "butt",
                    broderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: "mitter",
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
                },
            ],
        },
        options: {
            responsive: true,
            scales: {
                xAxes: [{
                    stacked: true,
                }, ],
                yAxes: [{
                    stacked: true,
                }, ],
            },
            plugins: {
                legend: {
                    display: true,
                    position: "top",
                    align: "center",
                },
                title: {
                    display: false,
                },
            },
        },
    });
}

async function addPolution(airPollution) {
    let pollution_container = document.getElementById("pollution_statistics");

    if (!airPollution) {
        airPollution = {
            airQualityIndex: "N/A",
            pm10ValueIndex: "N/A",
            pm25ValueIndex: "N/A",
            waterPollutionIndex: "N/A",
            o3ValueIndex: "N/A",
            no2ValueIndex: "N/A",
            so2ValueIndex: "N/A",
            covalueIndex: "N/A",
        };
    }
    let image = `
    <h1 class="card_title">Pollution</h1>
    <div id="pollution_card">
    <table>
        <tbody id="polution_card__container">
            <tr id="pollution_first"></tr>
            <tr id="pollution_second"></tr>
        </tbody>
    </table>
    <img id="pollution_icon" src="" alt="criminality">
    </div>
    `;

    pollution_container.innerHTML += image;
    let pollution_icon = document.getElementById("pollution_icon");
    pollution_icon.src = chrome.runtime.getURL("src/images/air_polution.png");

    pollution_container = document.getElementById("pollution_first");

    let air_quality_description = `
the air quality index runs from 0 to 500.
The higher the index value, the greater the level of air pollution and the greater the health concern.
For example, an index value of 50 or below represents good air quality, while
an index value over 300 represents hazardous air quality.
`;

    addPolutionItem(pollution_container, "air quality index", airPollution.airQualityIndex, air_quality_description);

    let pm10_description = `
PM1.0 refers to atmospheric particulate matter (PM) that have
a diameter of less than 1.0 micrometers, which is about 1% the
diameter of a human hair.

0 to 12.0	    Good
12.1 to 35.4	Moderate
35.5 to 55.4	Unhealthy for Sensitive Groups
55.5 to 150.4	Unhealthy
`;


    addPolutionItem(pollution_container, "pm1.0 value", airPollution.pm10ValueIndex, pm10_description);

    let pm25_description = `
PM2.5 refers to atmospheric particulate matter (PM) that have
a diameter of less than 2.5 micrometers, which is about 3% the
diameter of a human hair.

0 to 12.0	    Good
12.1 to 35.4	Moderate
35.5 to 55.4	Unhealthy for Sensitive Groups
55.5 to 150.4	Unhealthy
`;

    addPolutionItem(pollution_container, "pm2.5 value", airPollution.pm25ValueIndex, pm25_description);

    let water_pollution_description = `
Water pollution index is used for streams,
black waters (natural tea- and coffee-colored waters),
and springs, while TSI is used for lakes and estuaries.

0-45	Good
45-60	Fair
 >60    Poor
`;

    addPolutionItem(pollution_container, "water pollution", airPollution.waterPollutionIndex, water_pollution_description);

    pollution_container = document.getElementById("pollution_second");

    let o3_description = `
Ozone in the air we breathe can harm our health, especially
on hot sunny days when ozone can reach unhealthy levels.

  0 - 50	Good
 51 - 100	Moderate
101 – 150	Unhealthy for Sensitive Groups
151 – 200	Unhealthy
`;

    addPolutionItem(pollution_container, "O3 value", airPollution.o3ValueIndex, o3_description);

    let no2_description = `
Nitrogen dioxide comes from vehicles, power plants,
industrial emissions and off-road sources such as 
construction, lawn and gardening equipment.

 0-40       Good
90-120      Moderate
 >120       Unhealthy
`;

    addPolutionItem(pollution_container, "NO2 value", airPollution.no2ValueIndex, no2_description);


    let so2_description = `
SO2 is the component of greatest concern and
is used as the indicator for the larger group
of gaseous sulfur oxides.

  0-100     Good
200-350     Moderate
  >350      Unhealthy
`;

    addPolutionItem(pollution_container, "SO2 value", airPollution.so2ValueIndex, so2_description);

    let co2_description = `
Carbon dioxide (CO2) is a colourless, odourless and
non-poisonous gas formed by combustion
of carbon and in the respiration of living organisms
and is considered a greenhouse gas.

  0-100     Good
200-350     Moderate
  >350      Unhealthy
`;

    addPolutionItem(pollution_container, "CO value", airPollution.covalueIndex, co2_description);
}

async function addPolutionItem(pollution_container, text, value, description = "") {
    let pollution_item = ` <td>
    <div title="${description}" class="pollution_card__content">
        <p>${text}</p>
        <p class="pollution_card__content__value"><b>${value == null ? "N/A" : value}</b></p>
    </div></td>
    `;

    pollution_container.innerHTML += pollution_item;
}

async function addCriminality(criminality) {
    let criminality_container = document.getElementById("criminality_statistics");

    if (!criminality) {
        criminality = {
            crimeIndex: "N/A",
            robbingIndex: "N/A",
            brokenHomesIndex: "N/A",
            stolenCarsIndex: "N/A",
            attackedIndex: "N/A",
            drugsIndex: "N/A",
            violentCrimesIndex: "N/A",
            insultedIndex: "N/A",
        };
    }

    let image = `
    <h1 class="card_title">Criminality</h1>
    <div id="criminality_card">
    <table>
        <tbody id="criminality_card__container">
            <tr id="criminality_first"></tr>
            <tr id="criminality_second"></tr>
        </tbody>
    </table>
    <img id="criminality_icon" src="" alt="criminality">
    </div>
    `;

    criminality_container.innerHTML += image;

    let criminality_icon = document.getElementById("criminality_icon");

    criminality_icon.src = chrome.runtime.getURL("src/images/burglar.png");

    criminality_container = document.getElementById("criminality_first");

    addCriminalityItem(criminality_container, "crime index", criminality.crimeIndex);
    addCriminalityItem(criminality_container, "robbing index", criminality.robbingIndex);
    addCriminalityItem(criminality_container, "broken homes index", criminality.brokenHomesIndex);
    addCriminalityItem(criminality_container, "stolen cars index", criminality.stolenCarsIndex);

    criminality_container = document.getElementById("criminality_second");

    addCriminalityItem(criminality_container, "attacked index", criminality.attackedIndex);
    addCriminalityItem(criminality_container, "drugs index", criminality.drugsIndex);
    addCriminalityItem(criminality_container, "violent crimes index", criminality.violentCrimesIndex);
    addCriminalityItem(criminality_container, "insulted index", criminality.insultedIndex);
}

async function addCriminalityItem(criminality_container, text, value) {
    let description = `
The ${text} is based on the crime rate per 1,000 population
for all crimes in a specific neighborhood or city.
The crime index of ${value} means the neighborhood
is safer than ${value}% of the neighborhoods.
`
    let criminality_item = `
    <td>
        <div title = "${description}" class="criminality_card__content">
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
            length: 0,
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
    let star_file_name = checked ? "checked_star.png" : "unchecked_star.png";
    let source = chrome.runtime.getURL("src/images/" + star_file_name);
    rating_stars_container.innerHTML += `<img class="rating_star" src="${source}" alt=""></img>`;
}

async function addCostOfLiving(living_cost, gasoline) {
    if (!living_cost) {
        living_cost = {
            gasolinePrice: "N/A",
            domesticBeerPrice: "N/A",
            waterPrice: "N/A",
            cigarettesPrice: "N/A",
            busTicketPrice: "N/A",
            taxiKmPrice: "N/A",
            monthlyPersonCost: "N/A",
        };
    }

    if (!gasoline) {
        gasoline = {
            price: "N/A",
            measure: "N/A",
        };
    }

    let living_cost_container = document.getElementById("living_cost_statistics");

    let container = `
    <h1 class="card_title">Living cost</h1>
    <div id="living_cost_card">
    <table>
        <tbody id="living_cost_card__container">
            <tr id="living_cost_first"></tr>
            <tr id="living_cost_second"></tr>
        </tbody>
    </table>
    <img id="living_cost_icon" src="" alt="living_cost">
    </div>
    `;

    living_cost_container.innerHTML += container;

    let living_cost_icon = document.getElementById("living_cost_icon");
    living_cost_icon.src = chrome.runtime.getURL("src/images/living.png");

    living_cost_container = document.getElementById("living_cost_first");

    addliving_costItem(living_cost_container, "gasoline price", living_cost.gasolinePrice + " /l");
    addliving_costItem(living_cost_container, "gasoline price country", gasoline.price + " " + gasoline.measure);
    addliving_costItem(living_cost_container, "domestic beer", living_cost.domesticBeerPrice);
    addliving_costItem(living_cost_container, "water price", living_cost.waterPrice);

    living_cost_container = document.getElementById("living_cost_second");

    addliving_costItem(living_cost_container, "cigarettes price", living_cost.cigarettesPrice);
    addliving_costItem(living_cost_container, "bus ticket", living_cost.busTicketPrice);
    addliving_costItem(living_cost_container, "taxi cost", living_cost.taxiKmPrice + "/km");
    addliving_costItem(living_cost_container, "monthly cost", living_cost.monthlyPersonCost);
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
    let restaurants_container = document.getElementById("restaurants_statistics");

    if (!restaurants) {
        restaurants = {
            simpleMeal1PersonPrice: "N/A",
            fullMeal2PersonsPrice: "N/A",
            beerDraughtPrice: "N/A",
            beerBottlePrice: "N/A",
            cappuccinoPrice: "N/A",
            waterPrice: "N/A",
            mcMealPrice: "N/A",
            cokePrice: "N/A",
        };
    }

    let container = `
    <h1 class="card_title">Restaurant prices</h1>
    <div id="restaurants_card">
    <table>
        <tbody id="restaurants_card__container">
            <tr id="restaurants_first"></tr>
            <tr id="restaurants_second"></tr>
        </tbody>
    </table>
    <img id="restaurants_icon" src="" alt="restaurants">
    </div>
    `;

    restaurants_container.innerHTML += container;

    let restaurants_icon = document.getElementById("restaurants_icon");
    restaurants_icon.src = chrome.runtime.getURL("src/images/restaurant.png");

    restaurants_container = document.getElementById("restaurants_first");

    addrestaurantsItem(restaurants_container, "simple 1 person meal", restaurants.simpleMeal1PersonPrice);
    addrestaurantsItem(restaurants_container, "full 2 persons meal", restaurants.fullMeal2PersonsPrice);
    addrestaurantsItem(restaurants_container, "beer draught", restaurants.beerDraughtPrice);
    addrestaurantsItem(restaurants_container, "beer bottle", restaurants.beerBottlePrice);

    restaurants_container = document.getElementById("restaurants_second");

    addrestaurantsItem(restaurants_container, "capucciono", restaurants.cappuccinoPrice);
    addrestaurantsItem(restaurants_container, "water price", restaurants.waterPrice);
    addrestaurantsItem(restaurants_container, "mc meal", restaurants.mcMealPrice);
    addrestaurantsItem(restaurants_container, "coke price", restaurants.cokePrice);
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

async function addHealthcare(healthcare) {
    let healthcare_container = document.getElementById("healthcare_statistics");

    if (!healthcare) {
        healthcare = {
            skillAndCompetencyOfMedicalStaff: "N/A",
            speedInCompletingExaminationAndReports: "N/A",
            equipmentForModernDiagnosisAndTreatment: "N/A",
            accuracyAndCompletenessInFillingOutReports: "N/A",
            friendlinessAndCourtesyOfTheStaff: "N/A",
            satisfactionWithResponsivenessInMedicalInstitutions: "N/A",
            satisfactionWithCostToYou: "N/A",
            convenienceOfLocationForYou: "N/A",
        };
    }

    let container = `
    <h1 class="card_title">Healthcare</h1>
    <div id="healthcare_card">
    <table>
        <tbody id="healthcare_card__container">
            <tr id="healthcare_first"></tr>
            <tr id="healthcare_second"></tr>
        </tbody>
    </table>
    <img id="healthcare_icon" src="" alt="healthcare">
    </div>
    `;

    healthcare_container.innerHTML += container;

    let healthcare_icon = document.getElementById("healthcare_icon");
    healthcare_icon.src = chrome.runtime.getURL("src/images/healthcare.png");

    healthcare_container = document.getElementById("healthcare_first");

    addhealthcareItem(healthcare_container, "skill and competency", healthcare.skillAndCompetencyOfMedicalStaff);
    addhealthcareItem(healthcare_container, "examination reports", healthcare.speedInCompletingExaminationAndReports);
    addhealthcareItem(healthcare_container, "modern diagnosis", healthcare.equipmentForModernDiagnosisAndTreatment);
    addhealthcareItem(healthcare_container, "report filling", healthcare.accuracyAndCompletenessInFillingOutReports);

    healthcare_container = document.getElementById("healthcare_second");

    addhealthcareItem(healthcare_container, "friendliness", healthcare.friendlinessAndCourtesyOfTheStaff);
    addhealthcareItem(healthcare_container, "satisfaction with responsivness", healthcare.satisfactionWithResponsivenessInMedicalInstitutions);
    addhealthcareItem(healthcare_container, "satisfaction to you", healthcare.satisfactionWithCostToYou);
    addhealthcareItem(healthcare_container, "convenience", healthcare.convenienceOfLocationForYou);
}

async function addhealthcareItem(healthcare_container, text, value) {
    let description = `
The ${text} index is an estimation of the overall
quality of the health care system.
The ${text} index of ${value} means the healthcare
is better than ${value}% of the neighborhoods.
`;


    let healthcare_item = `
    <td>
        <div title="${description}" class="healthcare_card__content">
            <p>${text}</p>
            <p class="healthcare_card__content__value"><b>${value}</b></p>
        </div>
    </td>
    `;

    healthcare_container.innerHTML += healthcare_item;
}

async function addFood(food) {
    let food_container = document.getElementById("food_statistics");

    let container = `
    <h1 class="card_title">Grocery prices</h1>
    <div id="food_card">
        <table>
            <tbody id="food_card__container">
                <tr id="food_first"></tr>
                <tr id="food_second"></tr>
            </tbody>
        </table>
        <img id="food_icon" src="" alt="food">
    </div>
    `;

    food_container.innerHTML += container;

    let food_icon = document.getElementById("food_icon");
    food_icon.src = chrome.runtime.getURL("src/images/groceries.png");

    food_container1 = document.getElementById("food_first");
    food_container2 = document.getElementById("food_second");

    for (i = 0; i < 4; i++) {
        addfoodItem(food_container1, food[i].name, food[i].price);
        addfoodItem(food_container2, food[4 + i].name, food[4 + i].price);
    }
}

async function addfoodItem(food_container, text, value) {
    let food_item = `
    <td>
        <div class="food_card__content">
            <p>${text}</p>
            <p class="food_card__content__value"><b>${value}</b></p>
        </div>
    </td>
    `;

    food_container.innerHTML += food_item;
}