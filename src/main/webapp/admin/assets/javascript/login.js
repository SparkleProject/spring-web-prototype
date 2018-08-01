function $id(option){
	return document.getElementById(option);
}
var gWindow = {};
function fCalc(){
	gWindow.width = typeof window.innerWidth == "undefined" ? document.documentElement.clientWidth : window.innerWidth;
	gWindow.height = typeof window.innerHeight == "undefined" ? document.documentElement.clientHeight : window.innerHeight;
	gWindow.height = gWindow.height <= 435 ? 435 : gWindow.height;
}
fCalc();
function fChangeBg(){
	var oBgImg = $id("themeImg");
	var nImgHeight = oBgImg.height;
	var nImgWidth = oBgImg.width;
	if(typeof gWindow.height == "undefined"){
		fCalc();
	}
	oBgImg.style.top = (gWindow.height - nImgHeight)/2 + "px";
	oBgImg.style.left =  (gWindow.width - nImgWidth)/2 + "px";
}

window.onresize = function(){
	fCalc();
	fChangeBg();
};
$(document).ready(function(){
	$('input').placeholder();
});
if(top!=this){
	top.location.href="/admin/login";
}