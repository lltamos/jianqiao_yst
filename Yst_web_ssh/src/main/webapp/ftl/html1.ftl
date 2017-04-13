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

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet">

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
				<div class="col-md-8 col-sm-6">
					<div class="item-large">
						<div class="item-header">
							<h1 class="section-title">图文详情</h1>
							<span class="pull-right">评价: <i class="fa fa-star"></i><i
								class="fa fa-star"></i><i class="fa fa-star"></i><i
								class="fa fa-star"></i> <i class="fa fa-star-half-o"></i></span>
							<div class="clearfix"></div>
						</div>
						<!-- /.item-header -->
						<#list dbDescList as desc>
						<#if desc.desc_for == 1>
						<div class="item-large-content">
							<p>${desc.desc_content}</p>
						</div>
						<#else>
						<img src="../${desc.desc_content}" alt="">
						</#if> 
						
						<!-- /.item-large-content -->

						</#list>
					</div>
					<!-- /.item-large -->
				</div>
				<!-- /.col-md-8 -->
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
	<!-- templatemo 401 sprint -->
</body>
</html>