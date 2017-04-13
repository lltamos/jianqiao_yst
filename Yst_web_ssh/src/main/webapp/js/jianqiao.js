
	$(function(){
    $('.tent .sou').mousemove(function(){
       $('.tent .sou').removeClass('enl_2') ;
        $(this).addClass('enl_2');
    });
//-----------------------------------------------
    $('.city .city_3').mousemove(function(){
        $('.city .city_3').removeClass('city_2');
        $(this).addClass('city_2');
    });
//-----------------------------------------------
    $('.cont .nt_2').mousemove(function(){
       $('.cont .nt_2').removeClass('cont_1');
       $(this).addClass('cont_1');
    });
//---------------------------------------------
    $('.nav li').mousemove(function(){
       	$('.nav li').removeClass('blue');
        $(this).addClass('blue');
    });
    
    
});