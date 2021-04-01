chrome.storage.local.get("message", value => {

    var locations = value["message"];

    locations.forEach(element => {
        console.log(element);
        document.getElementById("control-group").insertAdjacentHTML("afterbegin",
            `<label class="control control-checkbox ">
            ${element}
            <input type="checkbox "/>
            <div class="control_indicator "></div>
            </label>`)
    });
});