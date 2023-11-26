//오버시
function showSubMenu(n){
	 document.getElementById("subMenu_"+n).style.display = "block";
 }
//아웃시
function showSubHidden(n){
	document.getElementById("subMenu_"+n).style.display = "none";
}
	
$(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            $('#MOVE_TOP_BTN').fadeIn();
        } else {
            $('#MOVE_TOP_BTN').fadeOut();
        }
    });
    
    $("#MOVE_TOP_BTN").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
});