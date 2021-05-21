var locations = [];
var host_url = `https://euopendata.herokuapp.com/`;
chrome.runtime.onInstalled.addListener(() => {
    chrome.storage.sync.set({ locations: locations });
});

//handle the request from inject.js (script injected on booking.com/hotel)
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    if (!request.sendStatistics) {
        var _data = {
            hotelName: request.hotelName,
            hotelLocation: request.hotelLocation,
            hotelPath: request.hotelPath,
        };
        chrome.storage.sync.get("locations", (value) => {
            locations = value.locations;

            if (!locations.some((loc) => loc.hotelPath === _data.hotelPath)) {
                locations.push(_data);
                chrome.storage.sync.set({ locations: locations });
            }
            sendResponse({ message: "preference sent successfully" });
        });
    } else {
        let uris = {
            url_forecast: host_url + "forecast?locations=" + request.hotelLocation,
            url_covid: host_url + "covid_statistics?countries=" + request.country,
            url_covid_news: host_url + "covid_news?locations=" + request.hotelLocation + "&max_results=2",
            url_pollution: host_url + "pollution?locations=" + request.hotelLocation,
            url_co2: host_url + "co2_emissions?countries=" + request.country,
            url_criminality: host_url + "criminality_statistics?locations=" + request.hotelLocation,
            url_rating: host_url + "hotel/reviews?hotel_identifier=" + request.hotelIdentifier,
            url_living_cost: host_url + "costofliving_statistics?locations=" + request.hotelLocation,
            url_restaurants: host_url + "restaurants?locations=" + request.hotelLocation,
            url_gasoline: host_url + "gasoline_price?countries=" + request.country,
            url_healthcare: host_url + "healthcare?locations=" + request.hotelLocation,
            url_food: host_url + "food_price?locations=" + request.hotelLocation
        };

        responses = fetchDataAboutLocation(uris)
            .then(function(results) {
                let responses = {
                    covid: results.covid[0],
                    covid_news: results.covidNews[0].results,
                    forecast: results.forecast[0],
                    airPollution: results.airPollution[0],
                    co2Pollution: results.co2[0].list,
                    criminality: results.criminality[0],
                    rating: results.rating,
                    living_cost: results.livingCost[0],
                    restaurants: results.restaurants[0],
                    gasoline: results.gasoline[0],
                    healthcare: results.healthcare[0],
                    food: results.food
                };
                sendResponse(responses);
            })
            .catch((err) => {
                console.log(err);
            });
    }
    return true;
});

function fetchStatisticsFrom(url, msg = "Fetch failed") {
    return fetch(url, {
            method: "GET",
        })
        .then((response) => response.json())
        .catch((err) => {
            console.log(err);
            return { message: msg };
        });
}

async function fetchDataAboutLocation(uris) {
    const [
        forecastResp,
        covidResp,
        covidNewsResp,
        airPollutionResp,
        co2Resp,
        criminalityResp,
        ratingResp,
        livingCostResp,
        restaurantsResp,
        gasolineResp,
        healthcareResp,
        foodResp
    ] = await Promise.all([
        fetchStatisticsFrom(uris.url_forecast, "Forecast request failed"),
        fetchStatisticsFrom(uris.url_covid, "Covid statistics request failed"),
        fetchStatisticsFrom(uris.url_covid_news, "Covid news request failed"),
        fetchStatisticsFrom(uris.url_pollution, "Air pollution request failed"),
        fetchStatisticsFrom(uris.url_co2, "CO2 pollution request failed"),
        fetchStatisticsFrom(uris.url_criminality, "criminality request failed"),
        fetchStatisticsFrom(uris.url_rating, "reviews request failed"),
        fetchStatisticsFrom(uris.url_living_cost, "living cost request failed"),
        fetchStatisticsFrom(uris.url_restaurants, "restaurants request failed"),
        fetchStatisticsFrom(uris.url_gasoline, "gasoline request failed"),
        fetchStatisticsFrom(uris.url_healthcare, "healthcare request failed"),
        fetchStatisticsFrom(uris.url_food, "food request failed")
    ]);

    const results = {
        forecast: forecastResp,
        covid: covidResp,
        covidNews: covidNewsResp,
        airPollution: airPollutionResp,
        co2: co2Resp,
        criminality: criminalityResp,
        rating: ratingResp,
        livingCost: livingCostResp,
        restaurants: restaurantsResp,
        gasoline: gasolineResp,
        healthcare: healthcareResp,
        food: foodResp
    };

    return results;
}