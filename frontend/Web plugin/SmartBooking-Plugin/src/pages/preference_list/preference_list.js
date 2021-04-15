const loginstate = localStorage.getItem('loginstate');

if (loginstate == "true") {
    document.getElementById('save-list').classList.remove("hidden");
    document.getElementById('log-to-save').classList.add('hidden');
} else {
    document.getElementById('save-list').classList.add("hidden");
    document.getElementById('log-to-save').classList.remove('hidden');
}

function checkCheckbox(listElem) {
    listElem.children[3].setAttribute("checked", "checked");
}

function getListElem(element = "hotel name", adress = "hotel adress") {
    let list_elem =
        `<div class="b-contain">
	<span class="remove">X</span>
	<span class="hotel-name">${element}</span>
	<span class="hotel-adress" style="display:none">${adress}</span>
	<input type="checkbox" onclick="return false">
	<div class="b-input"></div>
</div>`
    return list_elem;
}

function addLocationList() {
    chrome.storage.sync.get('locations', value => {
        var hotelList = value.locations;
        var control_group = document.getElementById("control-group");

        control_group.innerText = "Nothing here yet";
        if (hotelList) {
            control_group.innerText = "";
            hotelList.forEach(element => {
                console.log(element.hotelName);
                control_group.insertAdjacentHTML('beforeend', getListElem(element.hotelName, element.hotelLocation));
            });
            if (hotelList.length == 0) {
                control_group.innerText = "Nothing here yet";
            }
        }
        addRemoveButton();
    });
}
addLocationList();

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