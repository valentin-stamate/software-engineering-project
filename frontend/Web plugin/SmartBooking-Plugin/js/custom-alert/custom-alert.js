function includeAlert(){
	var banner = document.querySelector(".banner");
	if (banner)
	{
		banner.parentNode.removeChild(banner);
	}
	document.getElementById("main").insertAdjacentHTML("beforeend", 
		`<div class="banner banner-top alert-primary" role="alert">
			<span id="alert-msg"></span>
			<span class="banner-close">OK</span>
		</div>`
	);
}

function showAlert(msg, goHome = false)
{
	includeAlert();
	document.getElementById("main").style.pointerEvents = "none";
	var alert_msg = document.getElementById("alert-msg");
	alert_msg.innerText = msg;
    var btnClose = document.querySelector(".banner-close");
    btnClose.addEventListener("click", function(closeEvt) {
        var banner = document.querySelector(".banner");
        banner.parentNode.removeChild(banner);
		document.getElementById("main").style.pointerEvents = "auto";
		if (goHome){
			window.location.href = "/src/pages/popup.html";
		}
    });
}