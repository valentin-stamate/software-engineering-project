chrome.storage.local.get('message', value => {
    var locations = value.message;

	if (locations){
		locations.forEach(element => {
			console.log(element);
				
			document.getElementById("control-group").insertAdjacentHTML('beforeend',
				`<label class="control control-checkbox">${element}
				<input type="checkbox"/>
				<div class="control_indicator"></div>
				</label>`)
		});
	}
});