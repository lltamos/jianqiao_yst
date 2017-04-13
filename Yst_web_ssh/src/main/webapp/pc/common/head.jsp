<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<meta name="viewport" content="width=device-width; initial-scale=0.55;  minimum-scale=0.55; maximum-scale=0.55"/>
	<meta name="MobileOptimized" content="240"/>
	<title>健桥</title>
	<link rel="stylesheet" href="${baseurl }/pc/css/base.css">
	<link rel="stylesheet" href="${baseurl }/pc/css/common.css">
	<script src="${baseurl }/pc/js/jquery-1.11.3.min.js"></script>
	<script src="${baseurl }/pc/js/slider.js"></script>
	<script type="text/javascript">
	function loading(){
		$('body').prepend('<div class="loading zezhao_1" ><span>努力加载,请稍后...</span></div><div class="zezhao loading"></div>');
	}
	
	function cancelloading(){
		$('.loading').fadeOut();
		$('.loading').remove();
	}
	/**
	 * 禁用按钮20秒，防止重复提交
	 * @param e 按钮对象
	 */
	function disBut(e){
		loading();
		var onclick=$(e).attr("onclick");
		$(e).attr("onclick","");
		setTimeout(function(){
			$(e).attr("onclick",onclick);
			cancelloading();
		},5000);
	}
	function pageGo(href) {
		if($('#loading').length==0){
			loading();
		}
		$("#index_div").load(href,function(){
			 cancelloading(); 
		});
		/* $.ajax({
			url : "${baseurl}/user!getSession?url="+href+"&t="+Math.random(),
			type : 'POST',
			success : function(result) {
				if(result=="false"){
					alert("登录超时，请重新登录");
					parent.window.location.href="${baseurl}/user!loginPage";
					cancelloading(); 
				}else{
				}
			}
		}); */
	}
</script>
</head>
	<div class="header clearfix">
		<div class="cityicon">
			<div class="icon"></div>
		</div>
		<span class="cityw">选择城市</span>
		<div class="headerright">
			<span class="welcome">欢迎您来到健桥官方网站！！</span>
			<a href="pc/html/1.home登录页面.html" class="pleaselogin">请登录</a>
			<a href="2.zhuce注册.html" class="register">免费注册</a>
			<div class="appiconbox">
				<span class="appicon"></span>
			</div>
			<a href="javascript:;" class="txt">健桥APP</a>
			<div class="weixiniconbox">
				<span class="weixinicon"></span>
			</div>
			<a href="javascript:;" class="txt">微信公众号</a>
			<div class="shareiconbox">
				<span class="shareicon"></span>
			</div>
			<a href="javascript:;" class="txt">分享论坛</a>
		</div>
		
		<div class="selectcity" style="display: none">
			<div class="cityline">
				<span>北京</span>
				<span>上海</span>
				<span>天津</span>
				<span>重庆</span>
			</div>
			<div class="cityline">
				<span>黑龙江</span>
				<span>吉林</span>
				<span>辽宁</span>
				<span>江苏</span>
				<span>安徽</span>
			</div>
			<div class="cityline">
				<span>河北</span>
				<span>河南</span>
				<span>湖北</span>
				<span>湖南</span>
				<span>江西</span>
			</div>
			<div class="cityline">
				<span>山西</span>
				<span>四川</span>
				<span>青海</span>
				<span>海南</span>
				<span>广东</span>
			</div>
			<div class="cityline">
				<span>贵州</span>
				<span>浙江</span>
				<span>福建</span>
				<span>台湾</span>
				<span>甘肃</span>
			</div>
			<div class="cityline">
				<span>云南</span>
				<span>内蒙古</span>
				<span>宁夏</span>
				<span>新疆</span>
				<span>西藏</span>
			</div>
			<div class="cityline">
				<span>广西</span>
				<span>香港</span>
				<span>澳门</span>
			</div>
		</div>
	</div>
	<div class="navbox">
		<div class="nav clearfix">
			<h1 class="logo">
				<a href="javascript:;">
					<img src="pc/img/logo.png" alt="">
				</a>
			</h1>
			<ul class="navlinks clearfix">
				<li>
					<a href="javascript:;" class="bluedl">首页</a>
				</li>
				<li>
					<a href="javascript:;">疑难杂症导医</a>
				</li>
				<li>
					<a href="javascript:;">健康服务包</a>
				</li>
				<li>
					<a href="javascript:;">名医高手</a>
				</li>
				<li>
					<a href="javascript:;">患者求医</a>
				</li>
				<li>
					<a href="javascript:;">关于我们</a>
				</li>
			</ul>
		</div>
	</div>