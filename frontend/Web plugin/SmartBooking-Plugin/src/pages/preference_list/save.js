import UserController from '../login/UserController.js';

document.getElementById('save-list').addEventListener('click', () => {
    chrome.storage.sync.get('locations', value => {
        console.log(value);
        var hotelList = value.locations;
        for (var i = 0; i < hotelList.length; i++) {
            UserController.save(hotelList[i]);
        }
    });
});