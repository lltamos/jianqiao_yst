/*var jianqiao = {
	init: function(){
		jianqiao.banner();
		jianqiao.jianqiaonew();
	},
	banner: function(){
		$('.banner').flexslider({
			animation: 'slide',
			animationLoop: 'true',
			slideshow: 'true',
			slideshowSpeed: 2000,
			touch: 'false',
			directionNav: 'false'
		});
	},
	jianqiaonew: function(){
		$('.newbox').flexslider({
			animation: 'slide',
			directionNav: 'false',
			slideshow: 'true',
		});
	}
	
};

$(function(){
	jianqiao.init();
});*/

/*
$(function(){
    $('.navlinks a').hover(function(){
        $(this).css('background','#0084bc');
    },function(){
        $('.navlinks a').css('background','none');
    });
});
*/

$(function(){
    var timer=null;
    $('.cityw').mouseover(function(){
        clearTimeout(timer);
        $('.selectcity').show();
    })
    $('.cityw ').mouseout(function(){
         timer=setTimeout(function(){$('.selectcity').hide()},150);
    });
    $('.selectcity').mouseover(function(){
        clearTimeout(timer);
        $('.selectcity').show();
    })
    $('.selectcity ').mouseout(function(){
        timer=setTimeout(function(){
            $('.selectcity').hide();
        },150);
    });
});










































