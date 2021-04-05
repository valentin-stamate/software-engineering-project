chrome.storage.local.get('message', value => {
    var locations = value.message;

	if (locations){
		var control_group = document.getElementById("control-group");
		control_group.innerHTML = "";
		locations.forEach(element => {
			console.log(element);
				
		control_group.insertAdjacentHTML('beforeend',
				`<label class="control control-checkbox">${element}
				<input type="checkbox"/>
				<div class="control_indicator"></div>
				</label>`)
		});
	}
});