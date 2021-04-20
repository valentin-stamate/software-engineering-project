import UserController from '../login/UserController.js';

//experimental function
document.getElementById('save-list').addEventListener('click', handleUserHotelList);

async function handleUserHotelList() {
    console.log(hotelList);
    var hotelNodeList = document.getElementsByClassName("b-contain");
    if (hotelNodeList) {
        Array.prototype.slice.call(hotelNodeList).forEach(handleUserHotelElement);
    }
}

async function handleUserHotelElement(element) {
    let hotel_name = element.getElementById(hotel - name).innerText;
    let hotel_adress = element.getElementById(hotel - adress).innerText;
    let isSaved = element.children[3].hasAttribute(checked);
    if (!isSaved) {
        if (await UserController.save(hotel_name)) {
            checkCheckbox(element);
        }
        else {
            uncheckCheckbox(element);
        }
    }
}