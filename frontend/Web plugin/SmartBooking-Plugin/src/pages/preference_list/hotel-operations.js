import UserController from '../login/UserController.js';

//experimental function
document.getElementById('save-list').addEventListener('click', () => {
    chrome.storage.sync.get('locations', handleHotelList );
});

async function handleHotelList (value) {
    console.log(value.locations);
    var hotelList = value.locations;
    for (var i = 0; i < hotelList.length; i++) {
        if (await UserController.save(hotelList[i])){
            alert("true");
        }
        else {
            alert("false");
        }
    }
}