$(function(){
	airBalloon('div.air-balloon');
});


function airBalloon(balloon){
	var viewSize = [] , viewWidth = 0 , viewHeight = 0 ;
	resize();	
	$(balloon).each(function(){
		$(this).css({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) });
		fly(this);
	});	
	$(window).resize(function(){
		resize()
		$(balloon).each(function(){
			$(this).stop().animate({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) } ,1000 , function(){
				fly(this);
			});
		});
	});
	function resize(){
		viewSize = getViewSize();
		viewWidth = $(document).width() ;
		viewHeight = viewSize[1] ;
	}
	function fly(obj){
		var $obj = $(obj);
		var currentTop = parseInt($obj.css('top'));
		var currentLeft = parseInt($obj.css('left') );
		var targetLeft = rand( 10 , viewWidth - $obj.width() );
		var targetTop = rand(40, viewHeight /2 );
		/*������֮��ľ���*/
		var removing = Math.sqrt( Math.pow( targetLeft - currentLeft , 2 )  + Math.pow( targetTop - currentTop , 2 ) );		
		/*ÿ���ƶ�24px ����������Ҫ��ʱ�䣬�Ӷ����� ������ٶȺ㶨*/
		var moveTime = removing / 24;		
		$obj.animate({ top : targetTop , left : targetLeft} , moveTime * 1000 , function(){
			setTimeout(function(){
				fly(obj);
			}, rand(1000, 3000) );
		});	
	}
};