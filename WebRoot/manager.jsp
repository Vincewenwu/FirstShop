<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>个人中心(消息详情)</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
</head>
  
 <body>
      <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
    <!--nav 开始-->
    <div style="border-bottom:2px solid #F09E0B;">
    	<div class="nav w1200">
    	<a href="JavaScript:;" class="sp-kj" kj="">
        	商品分类快捷
        </a>
     <div class="kj-show2 none" kj-sh="">
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
        <ul>
        	<li><a href="#">在线商城</a></li>
        	<li><a href="#">餐饮娱乐</a></li>
        	<li><a href="#">家政服务</a></li>
        	<li><a href="#">美容美发</a></li>
        	<li><a href="#">教育培训</a></li>
        	<li><a href="#">汽车房产</a></li>
        	<li><a href="#">家居建材</a></li>
        	<li><a href="#">二手市场</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div style="clear:both;"></div>
    </div>
    </div>
    
    <!--内容开始-->
    <div class="personal w1200">
    	<div class="personal-left f-l">
        	<div class="personal-l-tit">
            	<h3>个人中心</h3>
            </div>
            <ul>
            	<li class="current-li per-li1"><a href="#">消息中心<span>></span></a></li>
            	<li class="per-li2"><a href="select.user">个人资料<span>></span></a></li>
            	<li class="per-li3"><a href="userMain.page?type=1">我的订单</a></li>
            	<li class="per-li4"><a href="#">我的预约<span>></span></a></li>
            	<li class="per-li5"><a href="index.cart">购物车<span>></span></a></li>
            	<li class="per-li6"><a href="address.order">管理收货地址<span>></span></a></li>
            	<li class="per-li7"><a href="#">店铺收藏<span>></span></a></li>
            	<li class="per-li8"><a href="#">购买记录<span>></span></a></li>
            	<li class="per-li9"><a href="#">浏览记录<span>></span></a></li>
            	<li class="per-li10"><a href="#">会员积分<span>></span></a></li>
            </ul>
        </div>
    	<div class="personal-r f-r">
        	<div class="personal-right">
                <div class="personal-r-tit">
                    <h3>消息中心列表  <  "李三思与一校学生 的故事</h3>
                </div>
                <div class="pcm-con">
                	<div class="pcm-top">
                    	<h2>"李三思于一校学生 的故事</h2>
                        <p class="time">2008-08-08 08:08:08</p>
                        <p class="rq">
                            <span>2008年08月08日</span>
                            <span>08:08:08</span>
                            <span>来源： 新华网</span>
                        </p>
                    </div>
                    <div class="pcm-info">
                    	<p>新华网合肥５月１５日电（记者陈诺）"松鼠老爹"章燎原和他的创客团队，在２０１４年"双十一"网购狂欢节上，以１．０２亿元的单日销售额，刷新中国电商食品销售纪录。</p>
                        <p>位于安徽省芜湖市的食品电商——"三只松鼠"从零起步，短短几年时间，用小小的坚果敲开了无数"吃货"的胃，更敲响了平凡人的"创业梦"。</p>
                        <img src="images/pcm-con-tu.gif" />
                        <strong>>>>民房里走出"草根"团队</strong>
                        <p>２０１２年，分散在街头巷尾的炒货店、零食铺子等实体店面依然是主要销售渠道。已在安徽一传统坚果企业任职近１０年的章燎原却敏锐地嗅到了坚果的电子"商机"</p>
                        <p>"网络世界那么大，我想去看看。"放弃原有的营销总监职位，３６岁的章燎原离开老东家，重新创业。</p>
                        <p>当年２月，食品电商"三只松鼠"在芜湖市一座百余平方米的小区民房里成立，５位合伙人中，有做厨师的发小、老东家的下属，还有在网上发帖吐槽的离职少年。共同的"创业梦"，让他们集结于此。</p>
                        <p>创业初期，员工垒起硬纸板来代替办公桌，应聘者误以为走进传销公司匆忙逃离……</p>
                        <p>创新成为突破的关键。利用ＥＲＰ云数据处理，团队砍掉了累赘的加工环节，原产地订单式半成品直接运至芜湖总部的封装工厂进行质检和分装。</p>
                        <p>"因为不用在门店积压库存，周转期只有１５天，所以更新鲜，成本也相应减少。"食品安全品质部副经理李世艳告诉记者。</p>
                        <p>"瘦身"的"三只松鼠"跑得快，在天猫上线６５天后，便冲上了坚果类销量第一的宝座。</p>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
    
       <!--底部服务-->
   <%@ include file="jsp/footer.jsp" %>
</body>
</html>
