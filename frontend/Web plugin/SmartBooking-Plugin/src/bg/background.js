// if you checked "fancy-settings" in extensionizr.com, uncomment this lines

// var settings = new Store("settings", {
//     "sample_setting": "This is how you use Store.js to remember values"
// });

var locations = []

//example of using a message handler from the inject scripts
chrome.extension.onMessage.addListener(
  function(request, sender, sendResponse) {
  	console.log(request);
	if (!locations.includes(request)){
		locations.push(request);
		chrome.storage.local.set({ "message": locations });
	}
  });