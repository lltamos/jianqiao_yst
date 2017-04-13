<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.3&key=4e41bbf36fac940fc829bf539af7a800&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style type="text/css">
#myPageTop {
	position: absolute;
	top: 5px;
	right: 10px;
	background: #fff none repeat scroll 0 0;
	border: 1px solid #ccc;
	margin: 10px 90px auto;
	padding: 6px;
	font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
	font-size: 14px;
}

#myPageTop label {
	margin: 0 20px 0 0;
	color: #666666;
	font-weight: normal;
	line-height: 25px;
}

#myPageTop input {
	width: 300px;
	line-height: 25px;
}

#myPageTop .column2 {
	padding-left: 25px;
}
</style>
</head>
<body>
	<div id="container"></div>
	<div id="myPageTop">
		<table>
			<tr>
				<td><label>请输入关键字：</label></td>
			</tr>
			<tr>
				<td><input id="tipinput" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		//地图加载
		var d = new Date();
		var initLatY = $("#longitude", window.parent.document).val() == "" ? 116.397428
				: $("#longitude", window.parent.document).val();
		var initLngX = $("#latitude", window.parent.document).val() == "" ? 39.90923
				: $("#latitude", window.parent.document).val();
		var initZoom = $("#longitude", window.parent.document).val() == "" ? 11
				: 18;
		var map = new AMap.Map("container", {
			resizeEnable : true,
			view : new AMap.View2D({
				center : new AMap.LngLat(initLatY, initLngX),//地图中心点
				zoom : initZoom
			//地图显示的缩放级别
			})
		}).on("click", getLnglat);
		//输入提示
		var autoOptions = {
			input : "tipinput"
		};
		var auto = new AMap.Autocomplete(autoOptions);
		var placeSearch = new AMap.PlaceSearch({
			map : map
		}); //构造地点查询类
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
		function select(e) {
			placeSearch.setCity(e.poi.adcode);
			placeSearch.search(e.poi.name); //关键字查询查询
		}
		function getLnglat(e) {
			var latY = $("#latitude", window.parent.document);
			var lngX = $("#longitude", window.parent.document);
			lngX.val(e.lnglat.getLng());
			latY.val(e.lnglat.getLat());
		}
		var marker = new AMap.Marker({
			icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			position : [ initLatY, initLngX ]
		});
		marker.setMap(map);
	</script>
</body>
</html>
