<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <title>个人资料</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
</head>

<body style="position:relative;">

	<!--top 开始-->
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
            	<li class="per-li2"><a href="#">个人资料<span>></span></a></li>
            	<li class="per-li3"><a href="#">我的订单<span>></span></a></li>
            	<li class="per-li4"><a href="#">我的预约<span>></span></a></li>
            	<li class="per-li5"><a href="#">购物车<span>></span></a></li>
            	<li class="per-li6"><a href="#">管理收货地址<span>></span></a></li>
            	<li class="per-li7"><a href="#">店铺收藏<span>></span></a></li>
            	<li class="per-li8"><a href="#">购买记录<span>></span></a></li>
            	<li class="per-li9"><a href="#">浏览记录<span>></span></a></li>
            	<li class="per-li10"><a href="#">会员积分<span>></span></a></li>
            </ul>
        </div>
    	<div class="personal-r f-r">
        	<div class="personal-right">
                <div class="personal-r-tit">
                    <h3>个人资料</h3>
                </div>
                <div class="data-con">
                	<div class="dt1">
                    	<p class="f-l">当前头像：</p>
                        <div class="touxiang f-l">
                        	<div class="tu f-l">
                            	<a href="#">
                                	<img src="images/data-tu.gif" />
                                    <input type="file" name="" id="" class="img1" />
                                </a>
                            </div>
                            <a href="JavaScript:;" class="sc f-l" shangchuang="">上传头像</a>
                            <div style="clear:both;"></div>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1">
                    	<p class="dt-p f-l">昵称：</p>
                        <input type="text" placeholder="RH了" value="${user.trueName }" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1">
                    	<p class="dt-p f-l">用户名：</p>
                        <input type="text" value="${user.userName }" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1 dt2">
                    	<p class="dt-p f-l">性别：</p>
                        <input type="radio" name="hobby" value="nan"></input><span>男</span>
                        <input type="radio" name="hobby" value="nan"></input><span>女</span>
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1">
                    	<p class="dt-p f-l">年龄：</p>
                        <input type="text" value="" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1 dt3">
                    	<p class="dt-p f-l">手机号：</p>
                        <input type="text" value="" />
                        <button>获取短信验证码</button>
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1">
                    	<p class="dt-p f-l">验证码：</p>
                        <input type="text" value="" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1">
                    	<p class="dt-p f-l">邮箱：</p>
                        <input type="text" value="" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="dt1 dt4">
                    	<p class="dt-p f-l">密码：</p>
                        <input type="text" value="" />
                        <button>修改密码</button>
                        <div style="clear:both;"></div>
                    </div>
                    <button class="btn-pst">保存</button>
                </div>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
    
    <!--上传头像弹窗
    <div class="tanchuang">
    	<div class="t-c-bg"></div>
    	<div class="t-c-con">
        	<div class="tc-tit">
            	<h3>上传头像</h3>
                <a href="JavaScript:;" delete=""><img src="images/t-c-del.gif" /></a>
                <div style="clear:both;"></div>
            </div>
            <div class="tc-con">
            	<a href="#"><img src="images/tc-tu.gif" /></a>
                <button>保存头像</button>
            </div>
        </div>
    </div>-->
    
    <!--底部服务-->
    <div class="ft-service">
    	<div class="w1200">
        	<div class="sv-con-l2 f-l">
            	<dl>
                	<dt><a href="#">新手上路</a></dt>
                    <dd>
                    	<a href="#">购物流程</a>
                    	<a href="#">在线支付</a>
                    </dd>
                </dl>
                <dl>
                	<dt><a href="#">配送方式</a></dt>
                    <dd>
                    	<a href="#">货到付款区域</a>
                    	<a href="#">配送费标准</a>
                    </dd>
                </dl>
                <dl>
                	<dt><a href="#">购物指南</a></dt>
                    <dd>
                    	<a href="#">常见问题</a>
                    	<a href="#">订购流程</a>
                    </dd>
                </dl>
                <dl>
                	<dt><a href="#">售后服务</a></dt>
                    <dd>
                    	<a href="#">售后服务保障</a>
                    	<a href="#">退款说明</a>
                    	<a href="#">新手选购商品总则</a>
                    </dd>
                </dl>
                <dl>
                	<dt><a href="#">关于我们</a></dt>
                    <dd>
                    	<a href="#">投诉与建议</a>
                    </dd>
                </dl>
                <div style="clear:both;"></div>
            </div>
        	<div class="sv-con-r2 f-r">
            	<p class="sv-r-tle">187-8660-5539</p>
            	<p>周一至周五9:00-17:30</p>
            	<p>（仅收市话费）</p>
            	<a href="#" class="zxkf">24小时在线客服</a>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
    
    <!--底部 版权-->
    <div class="footer w1200">
    	<p>
        	<a href="#">关于我们</a><span>|</span>
        	<a href="#">友情链接</a><span>|</span>
        	<a href="#">宅客微购社区</a><span>|</span>
        	<a href="#">诚征英才</a><span>|</span>
        	<a href="#">商家登录</a><span>|</span>
        	<a href="#">供应商登录</a><span>|</span>
        	<a href="#">热门搜索</a><span>|</span>
        	<a href="#">宅客微购新品</a><span>|</span>
        	<a href="#">开放平台</a>
        </p>
        <p>
        	沪ICP备13044278号<span>|</span>合字B1.B2-20130004<span>|</span>营业执照<span>|</span>互联网药品信息服务资格证<span>|</span>互联网药品交易服务资格证
        </p>
    </div>
</body>
