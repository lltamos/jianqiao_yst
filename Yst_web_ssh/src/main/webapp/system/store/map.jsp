<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<head>
<%@ include file="/common/commonHead.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>输入提示后查询</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=4e41bbf36fac940fc829bf539af7a800&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <style type="text/css">
    #myPageTop {
		position: absolute;
		top: 5px;
		right: 10px;
		background: #fff none repeat scroll 0 0;
		border: 1px solid #ccc;
		margin: 10px 90px auto;
		padding:6px;
		font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
		font-size: 14px;
	}
	#myPageTop label {
		margin: 0 20px 0 0;
		color: #666666;
		font-weight: normal;
	}
	#myPageTop input {
		width: 600px;
	}
	#myPageTop .column2{
		padding-left: 25px;
	}
    </style>
</head>
<body>
<div id="container"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
                <label >请输入关键字：</label>
            </td>
        </tr>
        <tr>
            <td>
                <input id="tipinput" />
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    //地图加载
    var d = new Date();
    var map= new AMap.Map("container", {
		resizeEnable : true,
		view: new AMap.View2D({
        	center:new AMap.LngLat($("#longitude",window.parent.document).val(),$("#latitude",window.parent.document).val()),//地图中心点
        	zoom:18 //地图显示的缩放级别
        })
	}).on("click", getLnglat);
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
        map: map
    });  //构造地点查询类
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        placeSearch.setCity(e.poi.adcode);
        placeSearch.search(e.poi.name);  //关键字查询查询
    }
    function getLnglat(e) {
		var latY = $("#latitude",window.parent.document);
		var lngX = $("#longitude",window.parent.document);
		lngX.val(e.lnglat.getLng());
		latY.val(e.lnglat.getLat());
	}
    var marker = new AMap.Marker({
        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
        position: [$("#longitude",window.parent.document).val(),$("#latitude",window.parent.document).val()]
    });
    marker.setMap(map);
</script>
</body>
</html>
