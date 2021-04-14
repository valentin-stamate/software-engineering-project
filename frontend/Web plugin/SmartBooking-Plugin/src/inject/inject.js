var getLocation = function(){
    var location_element= document.getElementsByClassName("hp_address_subtitle");
    var location = location_element[0].innerText;
    console.log(location);
    return location;
};

var getName = function(){
    var name_element = document.getElementById('hp_hotel_name');
    var name = name_element ? name_element.innerText : "";
    console.log(name);
    return name;
}

console.log('sal');

document.body.getElementsByClassName("hp-description")[0].insertAdjacentHTML('beforebegin', `
<div id="main-popup">
    <header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`);

var imgURL = chrome.extension.getURL("/src/images/background.webp");
document.getElementById("popup").style.backgroundImage = `url(${imgURL})`;

var show = true;

var hidePopup = function(){
	var popup = document.getElementById("popup");
	var main = document.getElementById("main-popup"); 
    if (show) {
		main.style.width = "25px";
        popup.style.display = "none";
    } else {
		main.style.width = "100%";
        popup.style.display = "block";
    }
    show = !show;
}

getLocation();
var sendPreferences = function() {
	console.log("click");
    var hotel_name = getName();
    chrome.extension.sendMessage(hotel_name.trim());
}

document.getElementById("hide-btn").addEventListener('click', hidePopup);
document.getElementById("send-btn").addEventListener('click', sendPreferences);

// Experimental function
function getStatistics(hotelName) {
    // aici o sa fie partea de request pentru statistici
    var xhr = new XMLHttpRequest();
    var url = "https://betonrats.000webhostapp.com/hotel.json";
	
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
            window.localStorage.setItem("statistics", xhr.responseText);
            console.log(xhr.responseText);
        }
    }
    xhr.send();
}

getStatistics(getName());