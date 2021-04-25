import UserController from '../login/UserController.js';
import { checkCheckbox } from './preference_list.js';
//experimental function
document.getElementById('save-list').addEventListener('click', handleUserHotelList);

var hotels = [];
async function handleUserHotelList() {
    var hotelNodeList = document.getElementsByClassName("b-contain");
    if (hotelNodeList) {
        Array.from(hotelNodeList).forEach(handleUserHotelElement);
        let msg = await UserController.saveUserHotel(hotels);
        showAlert(msg.message);
    }
}

function handleUserHotelElement(element) {
    let hotel_name = element.getElementsByClassName("hotel-name")[0].innerText;
    let hotel_adress = element.getElementsByClassName("hotel-adress")[0].innerText;
    let hotel_link = element.getElementsByClassName("hotel-link")[0].getAttribute("href");
    let regex = /hotel\/([a-zA-Z0-9-]+\/[a-zA-Z0-9-]+\.?[a-zA-Z0-9-]*)\.html/g;
    let hotel_id = regex.exec(hotel_link)[1];

    let isSaved = element.children[3].hasAttribute("checked");
    let hotel = {
        identifier: hotel_id,
        hotelName: hotel_name,
        locationName: hotel_adress
    }
    if (!isSaved) {
        hotels.push(hotel);
        checkCheckbox(element);
    }
}