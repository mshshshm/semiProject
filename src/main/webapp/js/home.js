
var i=0;
$(function(){
	//bxslider적용하기
	$("#slider").bxSlider({
		slideWidth:1300//슬라이드 폭
		,slideHeight:400// 슬라이드 높이
		,speed: 500//화면전환속도(밀리초)
		,auto: true //자동시작 true, false
		,autoHover: true
	});
});
$( document ).ready( function() {
	$('.main_recipe_list').slick({
	  dots: true,
	  infinite: true,
	  speed: 500,
	  slidesToShow: 4,
	  slidesToScroll: 4,
	  arrows: false
	});
});
