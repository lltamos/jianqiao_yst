<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>疑难杂症导医服务包</title>
    <link rel="stylesheet" href="${cxt }/css/base.css"/>
    <link rel="stylesheet" href="${cxt }/css/erweima_1.css"/>
</head> 
<body>
    <div class="logo_6">
        <div class="checkstand">
            <img src="${cxt }/img/logo_2_03.png" onclick="toIndex()" alt=""/>
            <h1>收银台</h1>
        </div>
    </div>
    <c:if test="${result.code ==5 }">
    <div class="order_num">
    	<a href="javascript:;" onclick="back()"> <<返回 </a>
        <p>请您及时付款，以便订单尽快处理！订单号：${result.content }</p>
    </div>
    <div class="weixin">
        <p><span>微信支付</span><b>距离二维码过期还剩<i class="DaojiShi">120</i>秒，过期后请刷新页面重新获取二维码。</b></p>
        <div class="weixin_img">
            <div class="weixin_imgleft">
                <img style="width: 380px;margin-left: -41px;" class="erweima_1" src="${result.msg }" alt=""/>
                <img src="${cxt }/img/weixin_1.png" alt=""/>
            </div>
            <div class="weixin_imgright">
                <img src="${cxt }/img/weixin_2.png" alt=""/>
            </div>
        </div>
    </div>
    </c:if>
    
    <c:if test="${result.code !=5 }">
    <div class="order_num">
        <p>${result.msg }</p>
    </div>
    <div class="weixin">
        <p><span>微信支付</span> </p>
        <div class="weixin_img">
            <div class="weixin_imgleft">
                <img class="erweima_1" src="" alt=""/>
                <img src="${cxt }/img/weixin_1.png" alt=""/>
            </div>
            <div class="weixin_imgright">
                <img src="${cxt }/img/weixin_2.png" alt=""/>
            </div>
        </div>
    </div>
    </c:if>
    <script>
    	var dd=120;
    	setTime=setInterval(function(){
    		if(dd<=0){
    			clearInterval(setTime); 
    			return false;
    		}else{
        		dd--
    			$('.DaojiShi').html(dd);
    		}
    	},1000)
     function toIndex(){
		window.location.href="${cxt }/pc/view/customer/toIndex";
	 }
    	function back(){
    		window.history.go(-1);  //返回上一页
    	 }
    </script>
</body>
</html>