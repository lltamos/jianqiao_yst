<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
  <head>
    <%@ include file="/common/commonHead.jsp"%>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
  </head>

  <body class="http-error"> 
        <div class="row-fluid">
		    <div class="http-error">
		        <h1>Oops!</h1>
		        <p class="info"><s:property value="message"/></p>
		        <p><i class="icon-home"></i></p>
		        		<p><a href="">返回首页</a></p>
		        		<p><a href="javascript:history.back();">返回上一页</a></p>
		    </div>
	</div>
  </body>
</html>


