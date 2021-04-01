console.log('sal');
document.body.insertAdjacentHTML('beforeend', `
<div id="main-popup">
    <header class="header">
		<button id="hide_button">&#8213</button>
	</header>
	<div id="popup">
		<h1 style="color:green">
			Inserat din plugin
		</h1>
	</div>
</div>`
);

var show = true;

document.getElementById("hide_button").addEventListener('click', () => {
    var popup = document.getElementById("popup");
	var btn = document.getElementById("hide_button");
	if (show){
		popup.style.display = "none";
	}
	else {
		popup.style.display = "inline-block";
	}
	show = !show;
});
