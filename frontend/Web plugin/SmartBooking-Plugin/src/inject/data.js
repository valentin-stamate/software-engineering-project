var checkin = document.getElementsByClassName("sb-date-field__display")[0];
var checkout = document.getElementsByClassName("sb-date-field__display")[1];

var textSelectedIn = checkin.innerText;
var textSelectedOut = checkout.innerText;

console.log(textSelectedIn);
console.log(textSelectedOut);

var dayOfWeekIn = null;
var dayOfMonthIn = null;
var monthIn = null;
var yearIn = null;

var dayOfWeekOut = null;
var dayOfMonthOut = null;
var monthOut = null;
var yearOut = null;

function setInValues(){
    var wordsIn = textSelectedIn.split(' ');
    var tmpIn = wordsIn[0].split(',');
    dayOfWeekIn = tmpIn[0];
    dayOfMonthIn = wordsIn[1];
    monthIn = wordsIn[2];
    yearIn = wordsIn[3];

    var wordsOut = textSelectedOut.split(' ');
    var tmpOut = wordsOut[0].split(',');
    dayOfWeekOut = tmpOut[0];
    dayOfMonthOut = wordsOut[1];
    monthOut = wordsOut[2];
    yearOut = wordsOut[3];
}

setInValues();