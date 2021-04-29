var locations = [];
var host_url = `https://euopendata.herokuapp.com/`;
chrome.runtime.onInstalled.addListener(() => {
    chrome.storage.sync.set({ "locations": locations });
});

//example of using a message handler from the inject scripts
chrome.runtime.onMessage.addListener(
    function (request, sender, sendResponse) {
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

            responses = fetchDataAboutLocation(url1, url2, url3).then(
                ([forecast, covid, covid_news]) => {
                    let responses = {
                        covid: covid[0],
                        covid_news: covid_news[0].results,
                        forecast: forecast[0]
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

async function fetchDataAboutLocation(url_forecast, url_covid, url_covid_news) {
    const [forecastResp, covidResp, covidNewsResp] = await Promise.all([
        fetchStatisticsFrom(url_forecast, "Forecast request failed"),
        fetchStatisticsFrom(url_covid, "Covid statistics request failed"),
        fetchStatisticsFrom(url_covid_news, "Covid news request failed")
    ]);

    const forecast = forecastResp;
    const covid = covidResp;
    const covid_news = covidNewsResp;

    return [forecast, covid, covid_news];
}