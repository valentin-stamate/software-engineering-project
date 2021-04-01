chrome.runtime.onInstalled.addListener(function() {});

var locations = [];

chrome.runtime.onMessage.addListener(request => {
    if (!locations.includes(request)) {
        locations.push(request);
        chrome.storage.local.set({ "message": locations });
    }

})