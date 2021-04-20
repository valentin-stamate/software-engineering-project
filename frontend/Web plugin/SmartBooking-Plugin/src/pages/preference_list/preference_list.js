import UserController from '../login/UserController.js';

var loadedHotelList = [];
const loginstate = localStorage.getItem('loginstate');

if (loginstate == "true") {
    document.getElementById('save-list').classList.remove("hidden");
    document.getElementById('log-to-save').classList.add('hidden');
    loadedHotelList = await UserController.getUserHotels();
} else {
    document.getElementById('save-list').classList.add("hidden");
    document.getElementById('log-to-save').classList.remove('hidden');
    loadedHotelList = [];
}

function checkCheckbox(listElem) {
    listElem.children[3].setAttribute("checked", "checked");
}

function uncheckCheckbox(listElem) {
    listElem.children[3].removeAttribute("checked");
}

function getListElem(element = "hotel name", adress = "hotel adress", link = "#") {
    let hotel_list_elem = document.createElement("div");
    hotel_list_elem.classList.add("b-contain");
    let list_elem =
        `<span class="remove">X</span>
    <a href=${link} target="_blank" rel="noopener"><span class="hotel-name">${element}</span></a>
	<span class="hotel-adress" style="display:none">${adress}</span>
	<input type="checkbox" onclick="return false">
<div class="b-input"></div>`

    hotel_list_elem.innerHTML = list_elem;
    return hotel_list_elem;
}

var hotelList = [];
function addUserHotelList() {
    chrome.storage.sync.get('locations', handleUserHotelsLoad);
}
addUserHotelList();

async function handleUserHotelsLoad(value) {
    hotelList = value.locations;
    var control_group = document.getElementById("control-group");

    control_group.innerHTML = "Nothing here yet";
    if (hotelList) {
        control_group.innerHTML = "";
        hotelList.forEach(element => {
            console.log(element.hotelName);
            let hotel_list_elem = getListElem(element.hotelName, element.hotelLocation, element.bookingLink);
            uncheckCheckbox(hotel_list_elem);
            control_group.append(hotel_list_elem);
        });
        if (hotelList.length == 0) {
            control_group.innerHTML = "Nothing here yet";
        }
    }
    addRemoveButton();
}

function addRemoveButton() {
    var remove = document.getElementsByClassName("remove");
    for (var i = 0; i < remove.length; i++) {
        remove[i].addEventListener('click', (el) => {
            var label = el.target.parentElement;
            label.style.display = "none";
            removeLocation(label.getElementsByClassName("hotel-name")[0].innerText);
            label.remove();
        });
    }
}

function removeLocation(loc) {
    chrome.storage.sync.get('locations', value => {
        var hotelList = value.locations;

        for (var i = 0; i < hotelList.length; i++) {
            if (hotelList[i].hotelName == loc) {
                hotelList.splice(i, 1);
            }
        }

        if (hotelList.length == 0) {
            document.getElementById("control-group").innerText = "Nothing here yet";
        }
        chrome.storage.sync.set({ "locations": hotelList });
    });
}

document.getElementById("log-to-save").addEventListener('click', () => {
    window.location.href = "/src/pages/login/login.html";
});