chrome.runtime.onInstalled.addListener(function() {});

var locations = [];

chrome.runtime.onMessage.addListener(request => {
	console.log(request);
	chrome.storage.local.get('message', value => {
		if (value.message){
			locations = value.message;
		}
	})
	
	if (!locations.includes(request)){
		locations.push(request);
		chrome.storage.local.set({ "message": locations });
	}
})