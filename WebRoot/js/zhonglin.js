/^[a-zA-z][a-zA-Z0-9_]{3,15}$/;
function round(num,dec){
			    var strNum = num + '';/*把要转换的小数转换成字符串*/
			    var index = strNum.indexOf("."); /*获取小数点的位置*/
			    if(index < 0) {
			        return num;/*如果没有小数点，那么无需四舍五入，返回这个整数*/
			    }
			    var n = strNum.length - index -1;/*获取当前浮点数，小数点后的位数*/
			    if(dec < n){ 
			    	/*把小数点向后移动要保留的位数，把需要保留的小数部分变成整数部分，只留下不需要保留的部分为小数*/ 
			        var e = Math.pow(10, dec);
			        num = num * e;
			        /*进行四舍五入，只保留整数部分*/
			        num = Math.round(num);
			        /*再把原来小数部分还原为小数*/
			        return num / e;
			    } else { 
			        return num;/*如果当前小数点后的位数等于或小于要保留的位数，那么无需处理，直接返回*/
			    } 
			}

$(document).ready(function() {
	/*鐐瑰嚮鏄剧ず浜岀淮鐮�*/
	$(".erweima").click(function(event) {
		$(".ewm-tu").toggle();
	});

	/*鍒囨崲鍩庡競 click 浜嬩欢*/
	$(".dianji-qh").click(function(event) {
		$(".select-city").show();
	});
	$(".close-select-city").click(function(event) {
		$(".select-city").hide();
	});
	$(".sl-city-con dd a").click(function(event) {
		var dqm = $(this).text();
		$(".dqm").text(dqm);
		$(".select-city").hide();
	});

	/*鍟嗗搧鍒嗙被蹇嵎 click 浜嬩欢*/
	$("[kj]").click(function(event) {
		$("[kj-sh]").toggle();
	});
	
	$("[mg]").hover(function(){
		$(this).addClass("hover-show-bg");
		var  a1 = $(this).attr("mg");
		$("[mg2="+a1+"]").show();
	},function(){
		$(this).removeClass("hover-show-bg");
		var  a1 = $(this).attr("mg");
		$("[mg2="+a1+"]").hide();
	});

	/*search 鍒囨崲*/
	$("[ss-search]").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
		var sh = $(this).attr("ss-search");
		$("[ss-search-show="+sh+"]").show().siblings().hide();
	});

//杞挱鍥�
	var tu=0;
	var spend=1000;
	/*鐐瑰嚮  鍙充晶*/
	$(".bnr-right").click(function(){
		dsq();
	});
	/*鐐瑰嚮  宸︿晶*/
	$(".bnr-left").click(function(){
		tu--;
		if(tu<0){
			tu=4;
			$(".banner ul").css("left",-6000);
		}
		$(".banner ul").animate({"left":-tu*1200},spend);
	});
	/*瀹氭椂鍣�寮�惎*/
	var time=setInterval(dsq,2000);
	function dsq(){
		tu++;
		if(tu>5){
			tu=1;
			$(".banner ul").css("left",0);
		}
		$(".banner ul").animate({"left":-tu*1200},spend);
	}
	$(".banner").hover(function(){
		clearInterval(time);	},function(){
		clearInterval(time);
		time=setInterval(dsq,2000);
	});
//杞挱鍥�END

	$("[hover]").hover(function() {
		var name = $(this).attr("hover");
		$("[hover-after="+name+"]").show();
	}, function() {
		var name = $(this).attr("hover");
		$("[hover-after="+name+"]").stop().hide();
	});
	
	/*鍟嗗搴楅摵椤甸潰 js 寮�*/
	$("[click]").click(function(){
		var ck = $(this).attr("click");
		if (ck) {
			$("[click-chage="+ck+"]").slideToggle(500);
		}
	});

	/*涓汉璧勬枡锛堜笂浼犲ご鍍忥級*/
	$("[shangchuang]").click(function(event) {
		$(".tanchuang").show();
	});
	$("[delete]").click(function(event) {
		$(".tanchuang").hide();
	});
	
	/*鎴戠殑璁㈠崟(绉诲叆鏀惰棌澶�*/
	$(".favorites .fa-left li").hover(function() {
		$(this).addClass('current').siblings().removeClass('current');
		$(".fa-right .fa-r-con .fa-con-uls").eq($(this).index()).show().siblings().hide();
	});
	
	$("[uls1] li").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		var us1 = $(this).parent("[uls1]").attr("uls1");
		$("[uls2="+us1+"] .ul1").eq($(this).index()).show().siblings().hide();
	});

	/*鎴戠殑璁㈠崟(鏌ョ湅鐗╂祦)*/
	$("[ckwl]").click(function(){
		$(".view-logistics").show();
	});
	$("[guanbi]").click(function(){
		$(".view-logistics").hide();
	});
	
	/*璐墿杞�js*/
	$(".info-mid .mid-guige").hover(function() {
		$(this).addClass('mid-guige2');
	}, function() {
		$(this).removeClass('mid-guige2');
	});
		/*瑙勬牸寮圭獥*/
	$("[xg]").click(function(event) {
		var th = $(this).attr("xg");
		$("[xg-g="+th+"]").show();
	});
	$("[qx]").click(function(event) {
		var th = $(this).attr("qx");
		$("[xg-g="+th+"]").hide();
	});
	$(".xg-left dd a").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
	});
		/*鏁伴噺鍔犲噺*/
	
	$(".mid-sl .sl-right").click(function(event) {
		var vl = $(this).siblings("input").val();
		var id = $(this).attr("gid");
		var text=$("#" + id + "jiage").val();
		if (vl>0) {
			vl++;
			var total = text*vl;
			$("#"+id+"total").text(round(total, 2));
			$(this).siblings("input").val(vl);
			showTotal();
			$.ajax({
				type:'post',
				url:'jiaCart.goods',
				data:{id:id,num:vl,goodsPrice:total},
			success:function(data){
			}
			});
		}
	});
	$(".mid-sl .sl-left").click(function(event) {
		var vl = $(this).siblings("input").val();
		var id = $(this).attr("gid");
		var text=$("#" + id + "jiage").val();
		if (vl>1) {
			vl--;
			var total = text*vl;
			$("#"+id+"total").text(round(total, 2));
			$(this).siblings("input").val(vl);
			showTotal();
			$.ajax({
				type:'post',
				url:'jianCart.goods',
				data:{id:id,num:vl,goodsPrice:total},
			success:function(data){
			}
			});
		}
	});
	
	$(".mid-chaozuo .ordering").click(function(event) {
	 	  var id = $(this).siblings("input").val();
	 		var jiage=$("#" + id + "jiage").val();
	 		var num=$("#" + id + "num").val();
	 	  var text1=round(jiage*num, 2);
	 	 $.ajax({
				type:'post',
				url:'tijiao.cart',
				data:{total:text1,id:id},
				dataType: "json",
			success:function(data){
				alert(data.result);
				if(data.result==1){
				  window.location.href="zfdd.order?orderId="+data.orderId+"&orderitem="+"001";
				}
			}
			});
	 	});
	
	/*纭璁㈠崟锛堟柊澧炲湴鍧�級 js*/
	$(".pay-xdz-btn").click(function(event) {
		$("[xgdz1]").show();
		
	});
	$("[dz-guan]").click(function(event) {
		$("[xgdz1]").hide();
	});
	
	/*修改地址*/
	$("[xiugai]").click(function(event) {
		var id = $(this).attr("gid");
		$("[xgdz2]").show();
		var dz1 = $(this).siblings("h3").children('.sp1').text();  /*重庆*/
		var dz2 = $(this).siblings("h3").children('.sp2').text();  /*巴南区*/
		var dz3 = $(this).siblings("h3").children('.sp3').text();  /*赵珍珍*/
		var dz4 = $(this).siblings("p").children('.sp1').text();  /*详细地址*/
		var dz5 = $(this).siblings("p").children('.sp2').text();  /*电话*/
		$("[xgdz2] .dz-left p").text(dz1);
		$("[xgdz2] .dz-right p").text(dz2);
		$("[xgdz2] .tc-con2 li .manyaddress").val(dz4);
		$("[xgdz2] .tc-con2 li .shxm").val(dz3);
		$("[xgdz2] .tc-con2 li .lxdh").val(dz5);
	});
	$("[dz-guan]").click(function(event) {
		$("[xgdz2]").hide();
	});
		/*淇敼鍦板潃*/
		/*宸﹁竟 js*/
	$(".dz-left p").click(function(event) {
		$(".dz-left ul").show();
	});
	$(".dz-left li").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
		$(".dz-left p").text($(this).text());
		$(".dz-left ul").hide();
	});
		/*鍙宠竟 js*/
	$(".dz-right p").click(function(event) {
		$(".dz-right ul").show();
	});
	$(".dz-right li").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
		$(".dz-right p").text($(this).text());
		$(".dz-right ul").hide();
	});

		/*纭璁㈠崟 鍦板潃鍒囨崲*/
	$(".pay-dz li").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
	});

	/*鍟嗗搧璇︽儏 js*/
	$("[qie-xiao] li").hover(function() {
		$(this).addClass('current').siblings().removeClass('current');
		$("[qie-da] li").eq($(this).index()).show().siblings().hide();
	});

	var dtu=0;
	var xtu=0;
	var spend=1000;
	$(".dt-qie-right").click(function(event) {
		xtu++;
		if(xtu>3){
			xtu=0;
			$("[qie-xiao]").css("left",0);
		}
		$("[qie-xiao]").animate({"left":-xtu*72},spend);
	});
	$(".dt-qie-left").click(function(event) {
		xtu--;
		if(xtu<0){
			xtu=3;
			$("[qie-xiao]").css("left",-72);
		}
		$("[qie-xiao]").animate({"left":-xtu*72},spend);
	});
		/*鍐呭-p-涓棿*/
	$(".dt-if-dd1 a").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
	});

			/*鍐呭鍒囨崲*/
	$("[com-det]").click(function(event) {
		var dt = $(this).attr("com-det");
		$(this).addClass('current').siblings().removeClass("current");
		$("[com-det-show]").hide();
		$("[com-det-show="+dt+"]").show();
	});
			/*dt-if1-m*/
	$(".dt-ifm-box3 .box3-right").click(function(event) {
		var val = $(".dt-ifm-box3 input").val();
		var id = $(this).attr("gid");
	var shuliang=$("#" + id + "shulaing").text();
	
		if(val>0){
			if(val!=shuliang){
				val++;
			}else{
				alert(最大商品数量值了);
			}
		}
		$(".dt-ifm-box3 input").val(val);
	});
	$(".dt-ifm-box3 .box3-left").click(function(event) {
		var val = $(".dt-ifm-box3 input").val();
		if(val>1){
			val--;
		}
		$(".dt-ifm-box3 input").val(val);
	});

	/*婊氬姩鏍�/
	$("[dw]").hover(function() {
		var w = $(this).attr("dw");
		$("[dw-show="+w+"]").show();
	}, function() {
		var w = $(this).attr("dw");
		$("[dw-show="+w+"]").hide();
	});

	/*鍟嗗搧璇︽儏(閰掑簵) js*/
	$("[chakan]").click(function(event) {
		var ck = $(this).attr("chakan");
		$("[chakan-tg="+ck+"]").slideToggle(500);
	});

	/*缇庡缇庡彂鎽勫奖棣栭〉  js*/
	$("[at]").click(function(event) {
		var ap = $(this).parents().siblings("[ap]").text();
		if(ap>0){
			ap++;
		}
		$(this).parents().siblings("[ap]").text(ap);
	});
	$("[ab]").click(function(event) {
		var ap = $(this).parents().siblings("[ap]").text();
		if(ap>1){
			ap--;
		}
		$(this).parents().siblings("[ap]").text(ap);
	});

	$(".img1").change(function(){
		if($(this).val()){
			var objUrl = gebjectURL(this.files[0]) ;
			console.log("objUrl = "+objUrl) ;
			var objUrl = gebjectURL(this.files[0]) ;
			$(this).prev("img").attr("src",objUrl)
		}
	})
	
	function gebjectURL(file) {
		var url = null ; 
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
	}

	/*浼氬憳绉垎 js*/
	$(".mem-info2 li").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		var jfhq = $(this).attr("jfhq");
		if(jfhq=="qb"){
			$("[hpjf]").show();
		}else{
			$("[hpjf="+jfhq+"]").show().siblings().hide();
		}
	});

	/*绉垎鍟嗗煄*/
	$(".integral-left li").click(function(event) {
		$(this).addClass('int-current').siblings().removeClass('int-current');
		var intli = $(this).index();
		$(".int-r-con .int-con-ul1").eq(intli).show().siblings(".int-con-ul1").hide();
	});
	/*鍟嗗煄娲诲姩*/
	$(".integral-left li").click(function(event) {
		$(this).addClass('int-current').siblings().removeClass('int-current');
		var intli = $(this).index();
		$(".int-r-con .int-con-ul2").eq(intli).show().siblings(".int-con-ul2").hide();
	});

	/*鏀惰棌鐨勫簵閾�js*/
	$("[shuxing1] li").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		$("[shuxingsw1] ul").eq($(this).index()).show().siblings().hide();
	});
	$("[shuxing2] li").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		$("[shuxingsw2] ul").eq($(this).index()).show().siblings().hide();
	});
	$("[shuxing3] li").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		$("[shuxingsw3] ul").eq($(this).index()).show().siblings().hide();
	});
	/*鎼滅储鍒楄〃椤�鏈夊搧鐗� js*/
	/*$("[ppgd]").click(function(event) {
		var ht = $(".hover-brand ul").css("height");
		if(ht=="106px"){
			$(".hover-brand ul").css('height','212');
		}else if(ht!="106px"){
			$(".hover-brand ul").css('height','106');
		}
	});*/
	$("[ppgd]").click(function(event) {
		$(".hover-brand").hide();
		$(".re-brand").show();
	});
	$("[quxiao1]").click(function(event) {
		$(".hover-brand").show();
		$(".re-brand").hide();
	});
});










