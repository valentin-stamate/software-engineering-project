console.log('sal');

var name_element = document.getElementById('hp_hotel_name');
var name = name_element ? name_element.innerText : "";

document.body.insertAdjacentHTML('afterbegin', `
<div id="main-popup">
    <header class="header">
		<button id="hide-btn">&#8213</button>
	</header>
	<div id="popup">
	    <h1 id="hotel-name-header">
			${name}
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
    var hotel_name = document.getElementById('hp_hotel_name').innerText;
    chrome.extension.sendMessage(hotel_name.trim());
});