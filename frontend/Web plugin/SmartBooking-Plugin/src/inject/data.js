addPopupHtml();

var destination = document.getElementById("ss").value;

/*var tmpAdults = document.getElementById("group_adults");
var numberOfAdults = tmpAdults.options[tmpAdults.selectedIndex].text;

var tmpChildren = document.getElementById("group_children");
var numberOfChildren = tmpChildren.options[tmpChildren.selectedIndex].text;

var tmpRooms = document.getElementById("no_rooms");
var numberOfRooms = tmpRooms.options[tmpRooms.selectedIndex].text;*/

var dayOfWeekIn = "";
var dayOfMonthIn = "";
var monthIn = "";
var yearIn = "";

var dayOfWeekOut = "";
var dayOfMonthOut = "";
var monthOut = "";
var yearOut = "";

var checkin = document.getElementsByClassName("sb-date-field__display")[0];
var checkout = document.getElementsByClassName("sb-date-field__display")[1];
var checkInText = checkin.innerText;
var checkOutText = checkout.innerText;
var identifier = "";

function setInValues() {
    var wordsIn = checkInText.split(" ");
    var tmpIn = wordsIn[0].split(",");
    dayOfWeekIn = tmpIn[0];
    dayOfMonthIn = wordsIn[1];
    monthIn = wordsIn[2];
    yearIn = wordsIn[3];

    var wordsOut = checkOutText.split(" ");
    var tmpOut = wordsOut[0].split(",");
    dayOfWeekOut = tmpOut[0];
    dayOfMonthOut = wordsOut[1];
    monthOut = wordsOut[2];
    yearOut = wordsOut[3];
    identifier = sha1(window.location.href);
}
setInValues();

console.log("Date In = " + checkInText);
console.log("Date Out = " + checkOutText);
console.log("Destination = " + destination);
//console.log("Number of adults = " + numberOfAdults);
//console.log("Number of children = " + numberOfChildren);
//console.log("Number of Rooms = " + numberOfRooms);

function sameDay(d1, d2) {
    return d1.getFullYear() === d2.getFullYear() && d1.getMonth() === d2.getMonth() && d1.getDate() === d2.getDate();
}

function getHotelAdress() {
    var location_element = document.getElementsByClassName("hp_address_subtitle");
    var adress = location_element[0].innerText;
    return adress.trim().replace("\n", "");
}

function getName() {
    var name_element = document.getElementById("hp_hotel_name");
    var name = name_element ? name_element.childNodes[2].nodeValue : "";
    return name.trim().replace("\n", "");
}

function getCountry() {
    let bar = document.getElementById('breadcrumb');
    let country = bar.childNodes[1].childNodes[9].childNodes[1].childNodes[1].innerHTML;
    return country.trim().replace("\n", "");
}

var country = getCountry();
var hotelName = getName();
var hotelAdress = getHotelAdress();
console.log("Hotel name = " + hotelName);
console.log("Hotel adress = " + hotelAdress);

function addPopupHtml() {
    let popup_str = `<div id="main-popup">
	<header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
		<div id="statistics-container"></div>
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`;
    document.body.getElementsByClassName("hp-description")[0].insertAdjacentHTML("beforebegin", popup_str);
    document.getElementById("send-btn").addEventListener("click", sendPreferences);
    document.getElementById("hide-btn").addEventListener("click", hidePopup);
}

var show = true;
// button events
function hidePopup() {
    var popup = document.getElementById("popup");
    var btn = document.getElementById("hide-btn");
    if (show) {
        btn.innerHTML = "+";
        popup.style.display = "none";
    } else {
        btn.innerHTML = "&#8213";
        popup.style.display = "block";
    }
    show = !show;
}

function sendPreferences() {
    console.log("sending preference");
    let hotel_path = window.location.pathname;
    var _data = {
        sendStatistics: false,
        hotelName: hotelName,
        hotelLocation: destination,
        hotelPath: hotel_path
    };
    chrome.runtime.sendMessage(_data, function(response) {
        console.log(JSON.stringify(response));
    });
}