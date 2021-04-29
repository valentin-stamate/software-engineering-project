var destination = document.getElementById("ss").value;

var tmpAdults = document.getElementById("group_adults");
var numberOfAdults = tmpAdults.options[tmpAdults.selectedIndex].text;

var tmpChildren = document.getElementById("group_children");
var numberOfChildren = tmpChildren.options[tmpChildren.selectedIndex].text;

var tmpRooms = document.getElementById("no_rooms");
var numberOfRooms = tmpRooms.options[tmpRooms.selectedIndex].text;

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

function setInValues(){
    var wordsIn =  checkInText.split(' ');
    var tmpIn = wordsIn[0].split(',');
    dayOfWeekIn = tmpIn[0];
    dayOfMonthIn = wordsIn[1];
    monthIn = wordsIn[2];
    yearIn = wordsIn[3];

    var wordsOut = checkOutText.split(' ');
    var tmpOut = wordsOut[0].split(',');
    dayOfWeekOut = tmpOut[0];
    dayOfMonthOut = wordsOut[1];
    monthOut = wordsOut[2];
    yearOut = wordsOut[3];
}
setInValues();

console.log("Date In = " + checkInText);
console.log("Date Out = " + checkOutText);
console.log("Destination = " + destination);
console.log("Number of adults = " + numberOfAdults);
console.log("Number of children = " + numberOfChildren);
console.log("Number of Rooms = " + numberOfRooms);

function sameDay(d1, d2) {
    return d1.getFullYear() === d2.getFullYear() &&
      d1.getMonth() === d2.getMonth() &&
      d1.getDate() === d2.getDate();
  }