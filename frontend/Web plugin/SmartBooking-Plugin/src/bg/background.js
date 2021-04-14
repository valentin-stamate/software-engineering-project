var locations = []
chrome.runtime.onInstalled.addListener(() => {
  chrome.storage.sync.set({ "locations": locations });
});

//example of using a message handler from the inject scripts
chrome.runtime.onMessage.addListener(
  function(request, sender, sendResponse) {
	if (!request.sendStatistics) {
		chrome.storage.sync.get('locations', value => {
			locations = value.locations;
		});
		if (!locations.includes(request.hotelName)){
			locations.push(request.hotelName);
			chrome.storage.sync.set({ "locations": locations });
		}
		sendResponse({message: "preference sent successfully"});
	}else {
		//json experimental cu statistici
		var url = "https://betonrats.000webhostapp.com/hotel.json";
		fetch(url).then(function(response) {
		  response.json().then(function(json) {
			sendResponse(json);
		  });
		});
		return true;
	}
});