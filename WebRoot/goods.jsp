<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <title>${goodsBean.name }</title>
 <title>商品详情(房车)</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript">
 function buy(i){
            var gid=i;
			var url='addShoppingCart.goods';//地址
			var gnum=$(".gnum").val();//数量
			var price=$("#price").val();//价格
			var src=$("#image").attr("src");//图片
			var color=$("#color").val();//颜色
			$.ajax({
			type:'post', //传送的方式,get/post
			url:url, //发送数据的地址
			data:{
				id:gid,num:gnum,price:price,color:color
			 },//,i:parameters,sort:string
			dataType: "json",
			success:function(data){
			if(data.newrow==1){
			$('#showIncludeCart').fadeIn();
		   }else{
		   return;
		   }
			}
	     });
 }
  function buyNow(i){
  	    var gnum=$(".gnum").val();//数量
		var price=$("#price").val();//价格
	 	  var text1=round(price*gnum, 2);
	 	 $.ajax({
				type:'post',
				url:'zhijiegoumai.cart',
				data:{total:text1,id:i,num:gnum},
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
   	<!--top 开始-->
   <%@ include file="jsp/top.jsp" %>
    <!--logo search 开始-->
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
        	<li><a href="#">首页</a></li>
        	<li><a href="#">美食</a></li>
        	<li><a href="#">外卖</a></li>
        	<li><a href="#">休闲娱乐</a></li>
        	<li><a href="#">酒店</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div style="clear:both;"></div>
    </div>
    </div>
    
    <!--内容开始-->
    <div class="details w1200">
    	<div class="deta-info1">
        	<div class="dt-if1-l f-l">
            	<div class="dt-if1-datu">
                	<ul qie-da="">
                       <li><a href="#"><img src="${goodsBean.proPic}" width="350"  /></a></li>
                      
                       <div style="clear:both;"></div>
                    </ul>
                </div>
                <div class="dt-if1-qietu">
                	<a class="dt-qie-left f-l" href="JavaScript:;"><img src="images/dt-if1-qietu-left.gif" /></a>
                    <div class="dt-qie-con f-l">
                    	<ul qie-xiao="">
                        	<li class="current"><a href="#"><img id="image" src="${goodsBean.proPic}" width="60px;" height="60px" /></a></li>
                        	
            				<div style="clear:both;"></div>
                        </ul>
                    </div>
                	<a class="dt-qie-right f-r" href="JavaScript:;"><img src="images/dt-if1-qietu-right.gif" /></a>
                </div>
                <div class="dt-if1-fx">
                	<span>商品编码:128618586</span>
                    <p>分享到：<a href="#"><img src="images/dt-xl.gif" /></a><a href="#"><img src="images/dt-kj.gif" /></a><a href="#"><img src="images/dt-wx.gif" /></a></p>
                </div>
            </div>
            
        	<div class="dt-if1-m f-l">
            	<div class="dt-ifm-hd">
                	<h3><a href="#">${goodsBean.name}</a></h3>
                </div>
                <div class="dt-ifm-gojia">
                	<dl>
                    	<dt>宅购价</dt>
                        <dd>
                        	<p class="p1">
                               <span class="sp1">¥ ${goodsBean.price}</span><span class="sp2">${goodsBean.price+(goodsBean.price*0.2)}</span>
                             <input style="display: none;" id="price" value="${goodsBean.price}" >
                            </p>
                            <p class="p2">
                            	<span class="sp1"><img src="images/dt-ifm-sp1-img.gif" />5分</span><span class="sp2">共有 2 条评价</span>
                            </p>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
                <dl class="dt-ifm-spot">
                	<dt>活动</dt>
                    <dd>
                    	<P><span>满赠</span>本店满500.00元赠300.00元礼品（随机赠送）</P>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl class="dt-ifm-box1">
                	<dt>时间</dt>
                    <dd>
                    	<select>
                        	<option>请选择预约日期</option>
                        	<option>2015-8-31</option>
                        	<option>2015-8-32</option>
                        </select>
                    	<select>
                        	<option>请选择预约时段</option>
                        	<option>上午</option>
                        	<option>下午</option>
                        </select>
                        <p></p>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl class="dt-ifm-box3">
                	<dt>数量</dt>
                	<p style="display: none;"  id="${goodsBean.id }shulaing" >${goodsBean.stock }</p>
                    <dd>
                    	<a class="box3-left" gid=${goodsBean.id } href="JavaScript:;">-</a>
                        <input type="text"   class="gnum" value="1" >
                    	<a class="box3-right" gid=${goodsBean.id } href="JavaScript:;">+</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <div class="dt-ifm-box5">
                	<p>颜色：${goodsBean.color} </p>
                	<input style="display: none;"  id="color" value="${goodsBean.color} ">
                </div>
                <div class="dt-ifm-box4">
                	<button class="btn1" onclick="buyNow(<%=request.getParameter("id") %>)" >立即购买</button>
                	<button class="btn2"  onclick="buy(<%=request.getParameter("id") %>)" >加入购物车</button>
                	<button class="btn3">收藏</button>
                </div>
       <div id="showIncludeCart" style="display:none;"> 
           <a class="buy_pop_close" onclick="jQuery('#showIncludeCart').hide();" title="关闭"></a> 
           <div class="buy_icon"></div> 
           <div class="buy_pop_top">
            <div class="title">
             此商品已成功放入购物车
            </div> 
            <div class="font">
             购物车共 
             <font id="totalnum" class="red"></font> 件宝贝
             <span>合计 <font class="red" id="fee"></font></span>
            </div> 
            <div class="btn_continue"> 
             <div class="pop_btn_r">
              <a onclick="jQuery('#showIncludeCart').hide();return false;" href="javascript:vod(0);">继续购物</a>
             </div> 
             <div class="btn_cart">
              <a href="index.cart">去购物车结算</a>
             </div> 
            </div> 
           </div> 
          </div> 
            </div>
            
        	<div class="dt-if1-r f-r">
            	<div class="dt-ifr-hd">
                	<div class="dt-ifr-tit">
                    	<h3>三只松鼠百货专营店</h3>
                    </div>
                    <ul class="dt-ifr-ul1">
                    	<li>
                        	<p class="p1">4.61 ↑</p>
                        	<p class="p2">商品评分</p>
                        </li>
                    	<li>
                        	<p class="p1">4.61 ↑</p>
                        	<p class="p2">商品评分</p>
                        </li>
                    	<li>
                        	<p class="p1">4.61 ↑</p>
                        	<p class="p2">商品评分</p>
                        </li>
            			<div style="clear:both;"></div>
                    </ul>
                    <div class="dt-ifr-tel">
                    	<p>地址：重庆渝北区高新园昆仑大道60号　　　龙头寺火车站旁</p>
                        <p>TEL：18616854445</p>
                    </div>
                    <button class="dt-r-btn1">进入店铺</button>
                    <button class="dt-r-btn2">收藏店铺</button>
                </div>
                <div class="dt-ifr-fd">
                	<div class="dt-ifr-tit">
                    	<h3>同类推荐</h3>
                    </div>
                    <dl>
                    	<dt><a href="#"><img src="images/dt-ifr-fd-dt-tu.gif" /></a></dt>
                        <dd>
                        	<a href="#">【观音桥】罗兰钢管舞舞蹈体验</a>
                            <p>¥9.90</p>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                    	<dt><a href="#"><img src="images/dt-ifr-fd-dt-tu.gif" /></a></dt>
                        <dd>
                        	<a href="#">【观音桥】罗兰钢管舞舞蹈体验</a>
                            <p>¥9.90</p>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        
        <div class="deta-info2">
        	<div class="dt-if2-l f-l">
            	<div class="if2-l-box1">
                	<div class="if2-tit">
                    	<h3>浏览记录</h3>
                    </div>
                    <ul>
                    	<li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li style="border-bottom:0;">
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                    </ul>
                </div>
                <div class="if2-l-box1" style="margin-top:18px;">
                	<div class="if2-tit">
                    	<h3>看了又看</h3>
                    </div>
                    <ul>
                    	<li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li style="border-bottom:0;">
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="dt-if2-r f-r">
                <ul class="if2-tit2">
                    <li class="current" com-det="dt1"><a href="JavaScript:;">产品信息</a></li>
                    <li com-det="dt2"><a href="JavaScript:;">商品评论</a></li>
                    <li com-det="dt3"><a href="JavaScript:;">商家信息</a></li>
                    <div style="clear:both;"></div>
                </ul>
                <div style="border:1px solid #DBDBDB;" com-det-show="dt1">
                	<div class="if2-tu1">
                        <img src="images/if2-tu1.gif" />
                        <img src="images/if2-tu2.gif" style="margin-top:47px;" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="if2-tu2">
                        <img src="images/if2-tu3.gif" />
                        <div style="clear:both;"></div>
                    </div>
                    <div class="if2-tu3">
                        <img src="images/if2-tu4.gif" />
                    </div>
                    <ul class="if2-tu4">
                        <li><img src="images/if2-tu5.gif" /></li>
                        <li><img src="images/if2-tu6.gif" /></li>
                        <li><img src="images/if2-tu7.gif" /></li>
                        <div style="clear:both;"></div>
                    </ul>
                </div>
                <div style="display:none;" com-det-show="dt2">
                	<dl class="if2-r-box2">
                	<dt>
                    	<p class="box2-p1">好评率</p>
                    	<p class="box2-p2">96.5%</p>
                    	<p class="box2-p3">共539人评论</p>
                    </dt>
                    <dd>
                    	<P>买过的人觉得</P>
                        <ul>
                        	<li><a href="#">香脆可口(198)</a></li>
                        	<li><a href="#">口感不错(160)</a></li>
                        	<li><a href="#">分量足(84)</a></li>
                        	<li><a href="#">味道不错(47)</a></li>
                        	<li><a href="#">价格便宜(34)</a></li>
                        	<li><a href="#">包装不错(30)</a></li>
                        	<li><a href="#">吃货必备(26)</a></li>
                        	<li><a href="#">送货快(14)</a></li>
                        	<li><a href="#">孩子喜欢(4)</a></li>
            				<div style="clear:both;"></div>
                        </ul>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                	<div class="if2-r-box3">
                	<ul>
                    	<li class="current-li"><a href="#">全部（539）</a></li>
                    	<li><a href="#">好评（536）</a></li>
                    	<li><a href="#">中评（2）</a></li>
                    	<li><a href="#">差评（1）</a></li>
                    	<li><a href="#">图片（1）</a></li>
                    	<li><a href="#">追加评论（1）</a></li>
                        <div style="clear:both;"></div>
                    </ul>
                    <dl>
                    	<dt>
                        	<a href="#"><img src="images/box3-dt-tu.gif" /></a>
                        </dt>
                        <dd>
                        	<a href="#">胡**</a>
                            <p class="b3-p1">赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！</p>
                            <p class="b3-p2">2015-12-12    16:57:22  </p>
                        </dd>
            			<div style="clear:both;"></div>
                    </dl>
                    <dl>
                    	<dt>
                        	<a href="#"><img src="images/box3-dt-tu.gif" /></a>
                        </dt>
                        <dd>
                        	<a href="#">胡**</a>
                            <p class="b3-p1">赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！</p>
                            <p class="b3-p2"><span></span><span></span>2015-12-12    16:57:22</p>
                            <div style="clear:both;"></div>
                            <div class="b3-zuijia">
                            	<p class="zj-p1">追加评论：</p>
                            	<p class="zj-p2">赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！</p>
                            	<p class="zj-p3">2015-12-12    16:57:22</p>
                            </div>
                        </dd>
            			<div style="clear:both;"></div>
                    </dl>
                    
                    <!--分页-->
           			<div class="paging">
            	<div class="pag-left f-l">
                	<a href="#" class="about left-r f-l"><</a>
                    <ul class="left-m f-l">
                    	<li><a href="#">1</a></li>
                        <li class="current"><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">...</a></li>
                        <li><a href="#">100</a></li>
                        <div style="clear:both;"></div>
                    </ul>
                	<a href="#" class="about left-l f-l">></a>
                    <div style="clear:both;"></div>
                </div>
            	<div class="pag-right f-l">
                	<div class="jump-page f-l">
                        到第<input type="text" />页
                    </div>
                    <button class="f-l">确定</button>
                    <div style="clear:both;"></div>
                </div>
                <div style="clear:both;"></div>
            </div>
                </div>
                </div>
                <div class="if2-r-box4" style="display:none;" com-det-show="dt3">
                	<div class="b4-tit">
                    	<h3>店铺所有商品</h3>
                    </div>
                    <div class="b4-con1">
                    	<a href="#">饼干糕点</a>
                    	<a href="#">坚果果仁</a>
                    	<a href="#">海味肉食</a>
                    	<a href="#">糖果巧克力</a>
                    	<a href="#">豆干炒货</a>
                    	<a href="#">休闲膨化</a>
                    	<a href="#">饮品冲调</a>
                    	<a href="#">蜜饯果脯</a>
                    	<a href="#">方便速食</a>
                    	<a href="#">饼干糕点</a>
                    	<a href="#">坚果果仁</a>
                    	<a href="#">海味肉食</a>
                    	<a href="#">糖果巧克力</a>
                    	<a href="#">豆干炒货</a>
                    	<a href="#">休闲膨化</a>
                    	<a href="#">饮品冲调</a>
                    	<a href="#">蜜饯果脯</a>
                    	<a href="#">方便速食</a>
                    </div>
                	<div class="b4-tit">
                    	<h3>店铺热销商品</h3>
                    </div>
                    <ul>
                    	<li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
                        <li>
                        	<a href="#"><img src="images/if2-l-box1-tu1.gif" /></a>
                            <a href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
                            <p>¥6.90</p>
                        </li>
            			<div style="clear:both;"></div>
                    </ul>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
    
    	 <!--底部服务-->
   <%@ include file="jsp/footer.jsp"  %>
  </body>
</html>
