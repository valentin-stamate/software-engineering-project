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
getLocation();

document.body.insertAdjacentHTML('afterbegin', `
<div id="main-popup">
    <header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
	    <h1 id="hotel-name-header">
			${getName()}
		</h1>
		<button id="send-btn" style="cursor:pointer">Add preference</button>
	</div>
</div>`);



var imgURL = chrome.extension.getURL("/src/images/background.webp");
document.getElementById("popup").style.backgroundImage = `url(${imgURL})`;

var show = true;

document.getElementById("hide-btn").addEventListener('click', () => {
    var popup = document.getElementById("popup");
    if (show) {
        popup.style.display = "none";
    } else {
        popup.style.display = "inline-block";
    }
    show = !show;
});

document.getElementById("send-btn").addEventListener('click', () => {
    console.log("click");
    var hotel_name = getName();
    chrome.extension.sendMessage(hotel_name.trim());
});