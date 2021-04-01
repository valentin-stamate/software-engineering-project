var locations = []

chrome.storage.local.get("message", value => {

    var tempLocations = value["message"];

    tempLocations.forEach(element => {
        if (!locations.includes(element)) {
            locations.push(element);
        }
    });

    locations.forEach(element => {
        console.log(element);
        document.getElementById("control-group").insertAdjacentHTML("afterbegin",
            `<label class="control control-checkbox">
            ${element}
            <input type="checkbox"/>
            <div class="control_indicator"></div>
            </label>`)
    });
});