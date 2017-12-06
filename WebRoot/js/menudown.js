$(function(){
	//频道页，内容页商品分类子类的显示与隐藏
	$('.all-goods .item').hover(function(){
		$(this).addClass('active').find('s').hide();
		$(this).find('.product-wrap').show();
	},function(){
		$(this).removeClass('active').find('s').show();
		$(this).find('.product-wrap').hide();
	});
	});

	//tab菜单切换
$(document).ready(function () {
	  $(".note ul li").mouseenter(function(){
		  $(this).addClass("selected").siblings().removeClass("selected");
   });
 $(".note ul li").mouseenter(function(){
	 var index=$(".note ul li").index(this);
	 $("#msg>div").eq(index).css("display", "block").siblings().css("display", "none");
	 });
  });
	

/*$(document).ready(function () {
	  $("h3.tab a").mouseenter(function(){
		  $(this).addClass("selected").siblings().removeClass("selected");
   });
	 $("h3.tab a").mouseenter(function(){
		 var index=$("h3.tab a").index(this);
		 $(".tab_view>div").eq(index).css("display", "block").siblings().css("display", "none"); 
	});
        });*/


$(document).ready(function(){
	  $("a.search_btn").click(function(){
		 var words=$("input.search").val();
		  if(words){
			  document.Searchform.submit();
		  }
		  else{
			  alert("搜索内容不能为空");
		  }
	  });
});

