<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--top 开始-->
    <div class="top">
    	<div class="top-con w1200">
    	 <c:if test="${userName==null }" >
        	<p class="t-con-l f-l">您好，请登录宅客微购</p>
         </c:if>
          <c:if test="${userName!=null }">
        	<p class="t-con-l f-l">${userName}您好， 欢迎来到宅客微购&nbsp;<a rel="nofollow" href="logout.user">${font }</a></p>
         </c:if>
            <ul class="t-con-r f-r">
            	<li><a href="manager.jsp">我 (个人中心)</a></li>
            	<li><a href="index.cart">我的购物车</a></li>
            		<li><a href="userMain.page?type=1">我的订单</a></li>
            	 <li class="erweima">
                	<a href="#">扫描二维码</a>
                    <div class="ewm-tu">
                    	<a href="#"><img src="images/erweima-tu.jpg" /></a>
                    </div>
                </li>
                <div style="clear:both;"></div>
            </ul>
            <div style="clear:both;"></div>
        </div>
    </div>
    
     <!--logo search 开始-->
    <div class="hd-info1 w1200">
    	<div class="logo f-l">
        	<h1><a href="main.jsp" title="中林网站"><img src="images/logo.jpg" /></a></h1>
        </div>
        <div class="dianji f-r">
        	<div class="btn1">
            	<button class="btn1-l"><a href="rege.jsp" style="font-size: 25px">注册</a></button>
                <button class="btn1-r" ><a href="login.jsp" style="font-size: 25px">登录</a></button><!--  -->
                <div style="clear:both;"></div>
            </div>
            <button class="btn2">商家入口    ></button>
        </div>
        <div class="search f-r">
        	<ul class="sp">
            	<li class="current" ss-search="sp"><a href="JavaScript:;">商品</a></li>
                <li ss-search="dp"><a href="JavaScript:;">店铺</a></li>
                <div style="clear:both;"></div>
            </ul>
            <div class="srh">
            	<div class="ipt f-l">
                	<input type="text" placeholder="搜索商品..." ss-search-show="sp" />
                    <input type="text" placeholder="搜索店铺..." ss-search-show="dp" style="display:none;" />
                </div>
                <button class="f-r">搜 索</button>
                <div style="clear:both;"></div>
            </div>
            <ul class="sp2">
                <li><a href="#">绿豆</a></li>
                <li><a href="#">大米</a></li>
                <li><a href="#">驱蚊</a></li>
                <li><a href="#">洗面奶</a></li>
                <li><a href="#">格力空调</a></li>
                <li><a href="#">洗发护发</a></li>
                <li><a href="#">葡萄 </a></li>
                <li><a href="#">脉动</a></li>
                <li><a href="#">海鲜水产</a></li>
                <div style="clear:both;"></div>
            </ul>
        </div>
        
        <div style="clear:both;"></div>
    </div>
  
<script type="text/javascript">
</script>
