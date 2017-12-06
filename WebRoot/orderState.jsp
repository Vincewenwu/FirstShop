<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>支付成功</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript">
function wodedingdan(){
 window.location="userMain.page?type=1";
}
</script>
</head>
  
 <body style="position:relative;">

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
    
    <!--内容开始-->
    <div class="payment w1200">
    	<div class="payment-hd">
        	<h3 class="success">支付成功</h3>
        </div>
        <div class="payment-ft">
        	<button class="btn2" onclick="wodedingdan()">我的订单</button>
        </div>
    </div>
    
      <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
  
</body>
</html>
