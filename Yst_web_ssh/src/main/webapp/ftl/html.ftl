<!DOCTYPE html>
<html>
<head>
<title>详情</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="Your description">
<meta name="keywords" content="Your keywords">
<meta name="author" content="Your name">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/zerogrid.css" type="text/css"
	media="all">
<link rel="stylesheet" href="css/responsive.css" type="text/css"
	media="all">
<script src="js/jquery.js"></script>
<script src="js/jquery-migrate-1.1.1.js"></script>
<script src="js/bgstretcher.js"></script>
<script type="text/javascript" src="js/css3-mediaqueries.js"></script>

<script>
	$(function(){
      //  Initialize Backgound Stretcher
      $('BODY').bgStretcher({
        images: [
        <#list imagelist as desc>'../${desc.desc_content}',</#list>
		], 
		imageWidth: 600, 
		imageHeight: 800, 
		resizeProportionally:true,
			slideDirection: 'N',
			slideShowSpeed: 1000,
			transitionEffect: 'fade',
			sequenceMode: 'normal',		
			pagination: '#nav'
       });	
    });
    </script>

<!--[if lt IE 8]>
   <div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div>  
 	<![endif]-->
<!--[if lt IE 9]>   
    <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
   <script src="js/html5shiv.js"></script>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:600' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:700' rel='stylesheet' type='text/css'>  
  <![endif]-->

</head>
<body>
	<div class="extra-block1">
		<!--==============================row-top=================================-->


		<!--==============================header=================================-->

		<header> </header>
	</div>
	<div class="block">
		<div class="nav-buttons">
			<div id="nav">&nbsp;</div>
		</div>
		<div class="row-1">
			<div class="zerogrid">
				<div class="wrapper">
					<#list imagelist as desc>
					<article class="col-5-6">
						<div class="wrap-col">
							<figure class="box-1">
								<img src="../${desc.desc_content}" alt="" />
								<figcaption> <h2>Slide 1 Caption</h2>
              					<p>Dapiensociis temper donec auctortortis cumsan et curabitur condis lorem loborttis leo. Ipsumcommodo libero nunc at in velis tincidunt pellentum tincidunt vel lorem pellus sed mauris enim.</p>
								</figcaption>
							</figure>
						</div>
					</article>
					</#list>
				</div>
			</div>
		</div>

		<!--==============================footer================================-->
		<footer> </footer>
	</div>
</body>
</html>