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

                if (!locations.some(loc => loc.hotelName === _data.hotelName)) {
                    locations.push(_data);
                    chrome.storage.sync.set({ "locations": locations });
                }
                sendResponse({ message: "preference sent successfully" });
            });
        } else {
            var _data = {
                "hotelName": request.hotelName,
                "hotelLocation": request.hotelLocation
            };
            //json experimental cu statistici
            var url = "https://betonrats.000webhostapp.com/hotel.json";

            fetch(url, {
                method: 'GET'
            }).then(response => {
                response.json().then(json => {
                    sendResponse(json);
                }).catch(err => {
                    console.log(err);
                });
            }).catch(err => {
                console.log(err);
            });
        }
        return true;
    });