var locations = []

chrome.runtime.onInstalled.addListener(() => {
  chrome.storage.sync.set({ "message": locations });
});

//example of using a message handler from the inject scripts
chrome.extension.onMessage.addListener(
  function(request, sender, sendResponse) {
  	console.log(request);
	if (!locations.includes(request)){
		locations.push(request);
		chrome.storage.sync.set({ "message": locations });
	}
  });