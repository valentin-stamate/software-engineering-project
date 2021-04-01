
chrome.runtime.onInstalled.addListener(tab => {
    chrome.tabs.executeScipt(tab.id, {file: "./scrpts/foreground.js"});
});