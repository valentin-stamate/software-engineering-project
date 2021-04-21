import UserController from '../login/UserController.js';

//experimental function
document.getElementById('save-list').addEventListener('click', handleUserHotelList);

var hotels = [];
async function handleUserHotelList() {
    console.log(hotelList);
    var hotelNodeList = document.getElementsByClassName("b-contain");
    if (hotelNodeList) {
        Array.prototype.slice.call(hotelNodeList).forEach(handleUserHotelElement);
        if (await UserController.save(hotels)) {
            showAlert("Save was succesful");
        }
        else {
            showAlert("Save was unsuccesful");
        }
    }
}

function handleUserHotelElement(element) {
    let hotel_name = element.getElementsByClassName("hotel-name")[0].innerText;
    let hotel_adress = element.getElementsByClassName("hotel-adress")[0].innerText;
    let hotel_link = element.getElementsByClassName("hotel-link")[0].getAttribute("href");
    let regex = /hotel\/([a-zA-Z0-9-]+\/[a-zA-Z0-9-]+\.?[a-zA-Z0-9-]*)\.html/g;
    let hotel_id = regex.exec(hotel_link)[1];

    let isSaved = element.children[3].hasAttribute(checked);
    let hotel = {
        identifier: hotel_id,
        hotelName: hotel_name,
        locationName: hotel_adress
    }
    if (!isSaved) {
        hotels.add(hotel);
        checkCheckbox(element);
    }
}