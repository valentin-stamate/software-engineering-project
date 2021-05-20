var locations = [];
var host_url = `https://euopendata.herokuapp.com/`;
chrome.runtime.onInstalled.addListener(() => {
    chrome.storage.sync.set({ "locations": locations });
});

//example of using a message handler from the inject scripts
chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (!request.sendStatistics) {
            var _data = {
                "hotelName": request.hotelName,
                "hotelLocation": request.hotelLocation,
                "hotelPath": request.hotelPath
            };
            chrome.storage.sync.get('locations', value => {
                locations = value.locations;

                if (!locations.some(loc => loc.hotelPath === _data.hotelPath)) {
                    locations.push(_data);
                    chrome.storage.sync.set({ "locations": locations });
                }
                sendResponse({ message: "preference sent successfully" });
            });
        } else {

            //json experimental cu statistici
            //var url = "https://betonrats.000webhostapp.com/hotel.json";

            let url1 = host_url + "forecast?locations=" + request.hotelLocation;
            let url2 = host_url + "covid_statistics?countries=" + "Romania";
            let url3 = host_url + "covid_news?locations=" + request.hotelLocation + "&max_results=2"
            let url4 = host_url + "pollution?locations=" + request.hotelLocation;
            let url5 = host_url + "criminality_statistics?locations=" + request.hotelLocation;
            let url6 = host_url + "hotel/reviews?hotel_identifier=" + "db21cd3c9a9b9951a22a146299d0cf88c0d44dd0"; // identifyier needed here
            let url7 = host_url + "costofliving_statistics?locations=" + request.hotelLocation;
            let url8 = host_url + "restaurants?locations=" + request.hotelLocation;
            let url9 = host_url + "gasoline_price?countries=Romania";

            responses = fetchDataAboutLocation(url1, url2, url3, url4, url5, url6, url7, url8, url9).then(
                ([forecast, covid, covid_news, air_pollution, criminality, rating, living_cost, restaurants, gasoline]) => {
                    let responses = {
                        covid: covid[0],
                        covid_news: covid_news[0].results,
                        forecast: forecast[0],
                        airPollution: air_pollution[0],
                        criminality: criminality[0],
                        rating: rating,
                        living_cost: living_cost[0],
                        restaurants: restaurants[0],
                        gasoline: gasoline[0]
                    };
                    sendResponse(responses);
                }).catch(err => {
                console.log(err);
            });
        }
        return true;
    });

function fetchStatisticsFrom(url, msg = "Fetch failed") {
    return fetch(url, {
        method: 'GET'
    }).then(response => response.json()).catch(err => {
        console.log(err);
        return { message: msg };
    });;
}

async function fetchDataAboutLocation(url_forecast, url_covid, url_covid_news, url_pollution, url_criminality, url_rating, url_living_cost, url_restaurants, url_gasoline) {
    const [forecastResp, covidResp, covidNewsResp, airPollutionResp, criminalityResp, ratingResp, livingCostResp, restaurantsResp, gasolineResp] = await Promise.all([
        fetchStatisticsFrom(url_forecast, "Forecast request failed"),
        fetchStatisticsFrom(url_covid, "Covid statistics request failed"),
        fetchStatisticsFrom(url_covid_news, "Covid news request failed"),
        fetchStatisticsFrom(url_pollution, "Air pollution request failed"),
        fetchStatisticsFrom(url_criminality, "criminality request failed"),
        fetchStatisticsFrom(url_rating, "reviews request failed"),
        fetchStatisticsFrom(url_living_cost, "living cost request failed"),
        fetchStatisticsFrom(url_restaurants, "restaurants request failed"),
        fetchStatisticsFrom(url_gasoline, "gasoline request failed"),
    ]);

    const forecast = forecastResp;
    const covid = covidResp;
    const covid_news = covidNewsResp;
    const airPollution = airPollutionResp;
    const criminality = criminalityResp;
    const rating = ratingResp;
    const livingCost = livingCostResp;
    const restaurants = restaurantsResp;
    const gasoline = gasolineResp;

    return [forecast, covid, covid_news, airPollution, criminality, rating, livingCost, restaurants, gasoline];
}