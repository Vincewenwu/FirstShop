<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
 <title>在线商城首页</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>

<script type="text/javascript">
 function buy(id){
			var url='addShoppingCart.goods';//地址
			var gnum=$("#"+id+"num").text();//数量
			var price=$("#"+id+"shangpinjiage").val();//价格
			var color=$("#"+id+"yanse").text;//颜色
			$.ajax({
			type:'post', //传送的方式,get/post
			url:url, //发送数据的地址
			data:{
				id:id,num:gnum,price:price,color:color
			 },//,i:parameters,sort:string
			dataType: "json",
			success:function(data){
			if(data.newrow==1){
			alert("收藏成功");
		   }else{
		   return;
		   }
			}
	     });
 }
 
 
function buyNow(i){
            var gid=i;
			var url='zhijiegoumai.cart';//地址
			var gnum=$("#"+gid+"num").text();//数量
			var price=$("#"+gid+"shangpinjiage").val();//价格
			var text1=round(gnum*price,2);
			$.ajax({
			type:'post', //传送的方式,get/post
			url:url, //发送数据的地址
			data:{total:text1,id:gid,num:gnum},
			dataType: "json",
			success:function(data){
				if(data.result==1){
				  window.location.href="zfdddangezhifu.order?orderId="+data.orderId+"&orderitem="+"001";
				}
			}
	     });
 }
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
</script>
  </head>
  <body>
  <div>
	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
    
    <!--切换城市-->
    <div class="switch-city w1200">
    	<a href="#" class="dianji-qh">切换城市</a>
        <span class="dqm">重庆市</span>
        <div class="select-city">
        	<div class="sl-city-top">
            	<p class="f-l">切换城市</p>
                <a class="close-select-city f-r" href="#">
                	<img src="images/close-select-city.gif" />
                </a>
            </div>
            <div class="sl-city-con">
            	<p>西北</p>
                <dl>
                	<dt>重庆市</dt>
                    <dd>
                    	<a href="#">长寿区</a>
                        <a href="#">巴南区</a>
                        <a href="#">南岸区</a>
                        <a href="#">九龙坡区</a>
                        <a href="#">沙坪坝区</a>
                        <a href="#">北碚</a>
                        <a href="#">江北区</a>
                        <a href="#">渝北区</a>
                        <a href="#">大渡口区</a>
                        <a href="#">渝中区</a>
                        <a href="#">万州</a>
                        <a href="#">武隆</a>
                        <a href="#">晏家</a>
                        <a href="#">长寿湖</a>
                        <a href="#">云集</a>
                        <a href="#">华中</a>
                        <a href="#">林封</a>
                        <a href="#">双龙</a>
                        <a href="#">石回</a>
                        <a href="#">龙凤呈祥</a>
                        <a href="#">朝天门</a>
                        <a href="#">中华</a>
                        <a href="#">玉溪</a>
                        <a href="#">云烟</a>
                        <a href="#">红塔山</a>
                        <a href="#">真龙</a>
                        <a href="#">天子</a>
                        <a href="#">娇子</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>新疆</dt>
                    <dd>
                    	<a href="#">齐乌鲁木</a>
                        <a href="#">昌吉</a>
                        <a href="#">巴音</a>
                        <a href="#">郭楞</a>
                        <a href="#">伊犁</a>
                        <a href="#">阿克苏</a>
                        <a href="#">喀什</a>
                        <a href="#">哈密</a>
                        <a href="#">克拉玛依</a>
                        <a href="#">博尔塔拉</a>
                        <a href="#">吐鲁番</a>
                        <a href="#">和田</a>
                        <a href="#">石河子</a>
                        <a href="#">克孜勒苏</a>
                        <a href="#">阿拉尔</a>
                        <a href="#">五家渠</a>
                        <a href="#">图木舒克</a>
                        <a href="#">库尔勒</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>甘肃</dt>
                    <dd>
                    	<a href="#">兰州</a>
                        <a href="#">天水</a>
                        <a href="#">巴音</a>
                        <a href="#">白银</a>
                        <a href="#">庆阳</a>
                        <a href="#">平凉</a>
                        <a href="#">酒泉</a>
                        <a href="#">张掖</a>
                        <a href="#">武威</a>
                        <a href="#">定西</a>
                        <a href="#">金昌</a>
                        <a href="#">陇南</a>
                        <a href="#">临夏</a>
                        <a href="#">嘉峪关</a>
                        <a href="#">甘南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>宁夏</dt>
                    <dd>
                    	<a href="#">银川</a>
                        <a href="#">吴忠</a>
                        <a href="#">石嘴山</a>
                        <a href="#">中卫</a>
                        <a href="#">固原</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>青海</dt>
                    <dd>
                    	<a href="#">西宁</a>
                        <a href="#">海西</a>
                        <a href="#">海北</a>
                        <a href="#">果洛</a>
                        <a href="#">海东</a>
                        <a href="#">黄南</a>
                        <a href="#">玉树</a>
                        <a href="#">海南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
            </div>
        </div>
    </div>
    
    <!--nav 开始-->
    <div class="nav w1200">
    	<a href="JavaScript:;" class="sp-kj" kj="">
        	商品分类快捷
        </a>
         <div class="kj-show2">
            <c:forEach items="${floor }" var="bigType">
               <div class="kj-info1" mg="${bigType.id}">
                 <dl class="kj-dl1">
                    <dt><a>${bigType.name}</a></dt>
                    <dd></dd>
                 </dl>
               <div class="kj-if-show" mg2="${bigType.id}">
	                 <c:forEach items="${bigType.smallTypeList }" var="smallType">
                  <dl>
					<dt>${smallType.name}</dt>
					 </dl>
					 <div style="clear:both;"></div>
				</c:forEach>
                </div>
               </div>
           </c:forEach>
           </div>
        </div>
    
    
    <!--banner 开始-->
    <div class="banner-box">
    	<div class="banner w1200">
        	<ul>
            	<c:forEach items="${slideList }" var="u">
				<li><a > <img src="${u.proPic }" style="height:560px;width: 1200px;" /></a></li>
				</c:forEach>
				<c:forEach items="${slideList }" var="u">
				<li><a > <img src="${u.proPic }" style="height:560px;width: 1200px;" /></a></li>
				</c:forEach>
            </ul>
            <a href="JavaScript:;" class="bnr bnr-left"><</a>
            <a href="JavaScript:;" class="bnr bnr-right">></a>
        </div>
    </div>
    
    <!--热门推荐-->
    <div class="hot-recommend w1200">
    	<h3>热门推荐</h3>
        <ul class="">
        	<li class="ys1">
            	<a href="#"><img src="images/hot-tu1.jpg" /></a>
                <div class="ys1-opt"></div>
                <div class="ys1-ft">
                	<p>最唯美<br /><span>时尚酒店</span></p>
                    <a href="#">点击有实惠</a>
                </div>
            </li>
            <li class="ys2">
            	<p>汽车保养</p>
                <a href="#" class="ys2-a1" style="margin-bottom:25px;">上门汽车保养1一元钱</a>
            	<a href="#"><img src="images/hot-tu2.jpg" /></a>
            </li>
            <li class="ys2">
            	<p>汽车保养</p>
                <a href="#" class="ys2-a1">上门汽车保养1一元钱</a>
                <a href="#"><img src="images/hot-tu3.jpg" /></a>
            </li>
            <li class="ys2" style=" width:298px;">
            	<p>汽车保养</p>
                <a href="#" class="ys2-a1">上门汽车保养1一元钱</a>
            	<a href="#"><img src="images/hot-tu4.jpg" /></a>
            </li>
            <li class="ys1">
            	<a href="#"><img src="images/hot-tu5.jpg" /></a>
                <div class="ys1-opt"></div>
                <div class="ys1-ft">
                	<p>最实惠KTV<br /><span>最佳组合</span></p>
                    <a href="#">点击有实惠</a>
                </div>
            </li>
            <li class="ys1">
            	<a href="#"><img src="images/hot-tu6.jpg" /></a>
                <div class="ys1-opt"></div>
                <div class="ys1-ft">
                	<p>最贴心家政<br /><span>包您满意</span></p>
                    <a href="#">点击有实惠</a>
                </div>
            </li>
            <li class="ys2">
            	<p>汽车保养</p>
                <a href="#" class="ys2-a1" style="margin-bottom:12px;">上门汽车保养1一元钱</a>
            	<a href="#"><img src="images/hot-tu7.jpg" /></a>
            </li>
            <li class="ys2" style="width:298px;">
            	<p>汽车保养</p>
                <a href="#" class="ys2-a1" style="margin-bottom:15px;">上门汽车保养1一元钱</a>
            	<a href="#"><img src="images/hot-tu8.jpg" /></a>
            </li>
            <div style="clear:both;"></div>
        </ul>
    </div>
    
    <!--商品内容页面-->
    <div class="shopping-content w1200">
    <!-- 楼层循环go -->
		<c:forEach items="${floor}" var="bigType" varStatus="big">
			<div class="sp-con-info">
			<h3 class="sp-info-tit"><span class="floor">${big.index+1 }F</span><span>${bigType.name
							}</span>
			</h3>
			<div class="sp-info-l f-l">
			<a target="_blank">
			<img class="lazy" src="${bigType.imgUrl}" data-original="${bigType.imgUrl}"style="height: 464px;width: 231px" /> </a>
			<c:forEach items="${bigType.smallTypeList }" var="sm">
			 <div class="sp-l-b">
			<a target="_blank" title="${sm.name }"
			href="search?sid=${sm.id }&p=1"> ${sm.name }</a>
			</div>
			</c:forEach>
			 </div>
		<ul class="sp-info-r f-r">
			<c:forEach items="${bigType.goods }" var="goods">
			 <li>
			<div class="li-top">
             <a class="yershop_img" href="goodsPageServlet?id=${goods.id }"
              target="_blank" title="${goods.name }"> 
              <input type="hidden" id="goodid" value="${goods.id }" >
		    	<img class="lazy" style="height: 154px;width: 238px" src="${goods.proPic }" data-original="${goods.proPic }" /><em></em></a>
                <p class="jiage"><span class="sp1">${goods.price }</span><span class="sp2">${goods.price+goods.price*0.2}</span>
         <input type="hidden" id="${goods.id }tupaindelujing" value="${goods.proPic }">
         <input type="hidden" id="${goods.id }shangpinjiage" value="${goods.price }">
           &nbsp; &nbsp; &nbsp;
             <span class="sp3">颜色：</span>
                <span class="sp4">${goods.color}</span>
        <input type="hidden" id="${goods.id }yanse" value="${goods.color}">
                </p>
            
             </div>
              <p class="miaoshu">${goods.name}</p>
              <div class="li-md">
                 <div class="md-l f-l">
                    <p class="md-l-l f-l" id="${goods.id }num" ap="">1</p>
                     <div class="md-l-r f-l">
                     <a href="JavaScript:;" class="md-xs" at="">∧</a>
                     <a href="JavaScript:;" class="md-xx" ab="">∨</a>
                    </div>
                    <div style="clear:both;"></div>
                 </div>
                     <div class="md-r f-l">
                         <button class="md-l-btn1" onclick="buyNow(${goods.id})">立即购买</button>
                       <button class="md-l-btn2" onclick="buy(${goods.id})" >加入购物车</button>
                    </div>
                    <div style="clear:both;"></div>
                </div>
               <p class="pingjia">88888评价</p>
                <p class="weike">${goods.brand }</p>
                </li>
			</c:forEach>
		  
		</ul>
	     <div style="clear:both;"></div>
		</div>
			</c:forEach>
	</div>
	
	 <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
   </div>
</body>

</html>
