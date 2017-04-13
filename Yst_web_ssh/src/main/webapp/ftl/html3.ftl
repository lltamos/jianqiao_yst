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
				<div class="col-md-8 col-sm-6">
					<div class="item-large">
						<!-- /.item-header -->
						<div class="item-large-content" id="detail_html">
							<#if advertise.detail_html??>${advertise.detail_html}</#if>
						</div>
						<!-- /.item-large-content -->
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
	<script type="text/javascript">
	$(function(){
		$("#detail_html img").each(function(){
			$(this).attr("src","../"+$(this).attr("src"));
		});
	});
	</script>
	<!-- templatemo 401 sprint -->
</body>
</html>