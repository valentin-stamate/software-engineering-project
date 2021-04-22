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

            let url1 = host_url + "forecast?locations=" + encodeURIComponent(request.hotelLocation);
            let url2 = host_url + "covid_statistics?countries=" + "Romania";

            responses = fetchForecastAndCovid(url1, url2).then(([forecast, covid]) => {
                let responses = {
                    covid: covid[0],
                    forecast: forecast[0]
                };
                sendResponse(responses);
            }).catch(err => {
                console.log(err);
            });
        }
        return true;
    });

function getForecast(url) {
    return fetch(url, {
        method: 'GET'
    }).then(response => response.json()).catch(err => {
        console.log(err);
        return { message: "Forecast request failed" };
    });;
}

function getCovidStatistics(url) {
    return fetch(url, {
        method: 'GET'
    }).then(response => response.json()).catch(err => {
        console.log(err);
        return { message: "Covid statistics request failed" };
    });;
}

async function fetchForecastAndCovid(url1, url2) {
    const [forecastResp, covidResp] = await Promise.all([
        getForecast(url1),
        getCovidStatistics(url2)
    ]);

    const forecast = forecastResp;
    const covid = covidResp;

    return [forecast, covid];
}