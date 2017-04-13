<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>Sprint HTML5 Template</title>
<meta name="description" content="">
<!--

Sprint Template 

http://www.templatemo.com/free-website-templates/401-sprint

-->
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/normalize.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/templatemo_misc.css">
<link rel="stylesheet" href="css/templatemo_style.css">

<script src="js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->


	<div id="product-promotion" class="content-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-left">
					<h5 class="section-title-my">购买须知</h5>
				</div>
				<div class="col-md-12 text-left">
					<h5 class="text-primary">内容介绍</h5>
					<h5 class="text-muted"><#if product.des1??>${product.des1}</#if></h5>
				</div>
				<div class="col-md-12 text-left">
					<h5 class="text-primary">项目原价</h5>
					<h5><del class="text-danger"><#if product.price??>${product.price/100}元</#if></del></h5>
				</div>
				<div class="col-md-12 text-left">
					<h5 class="text-primary">健桥美价</h5>
					<h5 class="text-danger"><#if product.price??>${product.price/100}元</#if></h5>
				</div>
				<div class="col-md-12 col-sm-6">
						<h5 class="text-primary">图文详情</h5>
						<div class="item-large-content" id="detail_html">
							<#if product.detail_html??>${product.detail_html}</#if>
						</div>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-md-12 text-left">
					<h5 class="section-service">服务信息</h5>
				</div>
				<div class="col-md-12 text-left">
					<h5 data-toggle="collapse" data-target="#demo3" onclick="changeImg(3)">
					<img src="images/3@2x.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务流程
					<img class="pull-right text-center" src="images/down@2x.png" id="img3"/>
					</h5>
					<div id="demo3" class="collapse"><h5 class="section-content text-muted"><#if product.service_process??>${product.service_process}</#if></h5></div>
				</div>
				<div class="col-md-12 text-left">
					<h5 data-toggle="collapse" data-target="#demo1" onclick="changeImg(1)">
					<img src="images/1@2x.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如何预约
					<img class="pull-right text-center" src="images/down@2x.png" id="img1"/>
					</h5>
					<div id="demo1" class="collapse"><h5 class="section-content text-muted" ><#if product.des2??>${product.des2}</#if></h5></div>
				</div>
				<div class="col-md-12 text-left">
					<h5 data-toggle="collapse" data-target="#demo2" onclick="changeImg(2)">
					<img src="images/2@2x.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如何退款
					<img class="pull-right text-center" src="images/down@2x.png" id="img2"/>
					</h5>
					<div id="demo2" class="collapse"><h5 class="section-content text-muted"><#if product.refund_process??>${product.refund_process}</#if></h5></div>
				</div>
				<div class="col-md-12 text-left">
					<h5 data-toggle="collapse" data-target="#demo4" onclick="changeImg(4)">
					<img src="images/4@2x.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特别提示
					<img class="pull-right text-center" src="images/down@2x.png" id="img4"/>
					</h5>
					<div id="demo4" class="collapse"><h5 class="section-content text-muted"><#if product.special??>${product.special}</#if></h5></div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /#product-promotion -->


	<script src="js/vendor/jquery-1.10.1.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/vendor/jquery-1.10.1.min.js"><\/script>')
	</script>
	<script src="js/jquery.easing-1.3.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#detail_html img").each(function(){
			$(this).attr("src","../"+$(this).attr("src"));
			$(this).attr("style","max-width:100%;height:auto");
		});
	});
	function changeImg(id){
		($('#img'+id).attr('src').indexOf('down')!=-1)?$('#img'+id).attr('src','images/up@2x.png'):$('#img'+id).attr('src','images/down@2x.png');
		//$('html,body').animate({scrollTop:($('#demo'+id).offset().top+500)}, 800);
	}
	</script>
	<!-- templatemo 401 sprint -->
</body>
</html>