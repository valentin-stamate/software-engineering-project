var locations = []
chrome.runtime.onInstalled.addListener(() => {
  chrome.storage.sync.set({ "message": locations });
});

//example of using a message handler from the inject scripts
chrome.extension.onMessage.addListener(
  function(request, sender, sendResponse) {
	chrome.storage.sync.get('message', value => {
		locations = value.message;
	});
  	console.log(request);
	if (!locations.includes(request)){
		locations.push(request);
		chrome.storage.sync.set({ "message": locations });
	}
  });