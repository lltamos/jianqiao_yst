<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<style>
#tip {
	background-color: #fff;
	border: 1px solid #ccc;
	padding-left: 10px;
	padding-right: 2px;
	min-height: 65px;
	top: 10px;
	left: 10px;
	font-size: 12px;
	border-radius: 3px;
	overflow: hidden;
	line-height: 20px;
	min-width: 400px;
}

#tip input[type="button"] {
	background-color: #0D9BF2;
	height: 25px;
	text-align: center;
	line-height: 25px;
	color: #fff;
	font-size: 12px;
	border-radius: 3px;
	outline: none;
	border: 0;
	cursor: pointer;
}

#tip input[type="text"] {
	height: 25px;
	border: 1px solid #ccc;
	padding-left: 5px;
	border-radius: 3px;
	outline: none;
}

#result1 {
	max-height: 300px;
}
</style>
<script type="text/javascript">
	function check() {
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var des = $("input[name='des']");
		if (des.val() == "") {
			des.tips({
				side : 2,
				msg : '介绍不得为空',
				bg : '#AE81FF',
				time : 3
			});

			des.focus();
			return false;
		} else {
			des.val(jQuery.trim(des.val()));
		}
		var store_phone = $("input[name='store_phone']");
		if (store_phone.val() == "") {
			store_phone.tips({
				side : 2,
				msg : '电话不得为空',
				bg : '#AE81FF',
				time : 3
			});

			store_phone.focus();
			return false;
		} else {
			store_phone.val(jQuery.trim(store_phone.val()));
		}
		var province = $("input[name='province']");
		if (province.val() == "") {
			province.tips({
				side : 2,
				msg : '省份不得为空',
				bg : '#AE81FF',
				time : 3
			});

			province.focus();
			return false;
		} else {
			province.val(jQuery.trim(province.val()));
		}
		var city = $("input[name='city']");
		if (city.val() == "") {
			city.tips({
				side : 2,
				msg : '城市不得为空',
				bg : '#AE81FF',
				time : 3
			});

			city.focus();
			return false;
		} else {
			city.val(jQuery.trim(city.val()));
		}
		var address = $("input[name='address']");
		if (address.val() == "") {
			address.tips({
				side : 2,
				msg : '地址不得为空',
				bg : '#AE81FF',
				time : 3
			});

			address.focus();
			return false;
		} else {
			address.val(jQuery.trim(address.val()));
		}
		var latitude = $("input[name='latitude']");
		if (latitude.val() == "") {
			latitude.tips({
				side : 2,
				msg : '经度不得为空',
				bg : '#AE81FF',
				time : 3
			});

			latitude.focus();
			return false;
		} else {
			if (isNaN(latitude.val())) {
				latitude.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 3
				});
				latitude.focus();
				return false;
			} else {
				latitude.val(jQuery.trim(latitude.val()));
			}
		}
		var longitude = $("input[name='longitude']");
		if (longitude.val() == "") {
			longitude.tips({
				side : 2,
				msg : '纬度不得为空',
				bg : '#AE81FF',
				time : 3
			});

			longitude.focus();
			return false;
		} else {
			if (isNaN(longitude.val())) {
				longitude.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 3
				});
				longitude.focus();
				return false;
			} else {
				longitude.val(jQuery.trim(longitude.val()));
			}
		}
		return true;
	}
	// 保存用户信息
	function saveStore() {
		if (check()) {
			var form = $("#storeInfoForm");
			var options = {
				url : 'store!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS") {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('store.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	var d = new Date();
	var windowsArr = [];
	var marker = [];
	var mapObj = new AMap.Map("mapDiv", {
		resizeEnable : true,
		keyboardEnable : false
	}).on("click", getLnglat);
	function getLnglat(e) {
		var latY = $("#latitude");
		var lngX = $("#longitude");
		lngX.val(e.lnglat.getLng());
		latY.val(e.lnglat.getLat());
	}
	document.getElementById("keyword").onkeyup = keydown;
	//输入提示
	function autoSearch() {
		var keywords = document.getElementById("keyword").value;
		var auto;
		//加载输入提示插件
		AMap.service([ "AMap.Autocomplete" ], function() {
			var autoOptions = {
				city : "" //城市，默认全国
			};
			auto = new AMap.Autocomplete(autoOptions);
			//查询成功时返回查询结果
			if (keywords.length > 0) {
				auto.search(keywords, function(status, result) {
					autocomplete_CallBack(result);
				});
			} else {
				document.getElementById("result1").style.display = "none";
			}
		});
	}

	//输出输入提示结果的回调函数
	function autocomplete_CallBack(data) {
		var resultStr = "";
		var tipArr = data.tips;
		if (tipArr && tipArr.length > 0) {
			for (var i = 0; i < tipArr.length; i++) {
				resultStr += "<div id='divid"
						+ (i + 1)
						+ "' onmouseover='openMarkerTipById("
						+ (i + 1)
						+ ",this)' onclick='selectResult("
						+ i
						+ ")' onmouseout='onmouseout_MarkerStyle("
						+ (i + 1)
						+ ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\""
						+ "data=" + tipArr[i].adcode + ">" + tipArr[i].name
						+ "<span style='color:#C1C1C1;'>" + tipArr[i].district
						+ "</span></div>";
			}
		} else {
			resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
		}
		document.getElementById("result1").curSelect = -1;
		document.getElementById("result1").tipArr = tipArr;
		document.getElementById("result1").innerHTML = resultStr;
		document.getElementById("result1").style.display = "block";
	}

	//输入提示框鼠标滑过时的样式
	function openMarkerTipById(pointid, thiss) { //根据id打开搜索结果点tip 
		thiss.style.background = '#CAE1FF';
	}

	//输入提示框鼠标移出时的样式
	function onmouseout_MarkerStyle(pointid, thiss) { //鼠标移开后点样式恢复 
		thiss.style.background = "";
	}

	//从输入提示框中选择关键字并查询
	function selectResult(index) {
		if (index < 0) {
			return;
		}
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			document.getElementById("keyword").onpropertychange = null;
			document.getElementById("keyword").onfocus = focus_callback;
		}
		//截取输入提示的关键字部分
		var text = document.getElementById("divid" + (index + 1)).innerHTML
				.replace(/<[^>].*?>.*<\/[^>].*?>/g, "");
		var cityCode = document.getElementById("divid" + (index + 1))
				.getAttribute('data');
		document.getElementById("keyword").value = text;
		document.getElementById("result1").style.display = "none";
		//根据选择的输入提示关键字查询
		mapObj.plugin([ "AMap.PlaceSearch" ], function() {
			var msearch = new AMap.PlaceSearch(); //构造地点查询类
			AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
			msearch.setCity(cityCode);
			msearch.search(text); //关键字查询查询
		});
	}

	//定位选择输入提示关键字
	function focus_callback() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			document.getElementById("keyword").onpropertychange = autoSearch;
		}
	}

	//输出关键字查询结果的回调函数
	function placeSearch_CallBack(data) {
		//清空地图上的InfoWindow和Marker
		windowsArr = [];
		marker = [];
		mapObj.clearMap();
		var resultStr1 = "";
		var poiArr = data.poiList.pois;
		var resultCount = poiArr.length;
		for (var i = 0; i < resultCount; i++) {
			resultStr1 += "<div id='divid"
					+ (i + 1)
					+ "' onmouseover='openMarkerTipById1("
					+ i
					+ ",this)' onmouseout='onmouseout_MarkerStyle("
					+ (i + 1)
					+ ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/"
					+ (i + 1) + ".png\"></td>"
					+ "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name
					+ "</font></h3>";
			resultStr1 += TipContents(poiArr[i].type, poiArr[i].address,
					poiArr[i].tel)
					+ "</td></tr></table></div>";
			addmarker(i, poiArr[i]);
		}
		mapObj.setFitView();
	}

	//鼠标滑过查询结果改变背景样式，根据id打开信息窗体
	function openMarkerTipById1(pointid, thiss) {
		thiss.style.background = '#CAE1FF';
		windowsArr[pointid].open(mapObj, marker[pointid]);
	}

	//添加查询结果的marker&infowindow   
	function addmarker(i, d) {
		var lngX = d.location.getLng();
		var latY = d.location.getLat();
		var markerOption = {
			map : mapObj,
			icon : "http://webapi.amap.com/images/" + (i + 1) + ".png",
			position : new AMap.LngLat(lngX, latY)
		};
		var mar = new AMap.Marker(markerOption);
		marker.push(new AMap.LngLat(lngX, latY));

		var infoWindow = new AMap.InfoWindow({
			content : "<h3><font color=\"#00a6ac\">  " + (i + 1) + ". "
					+ d.name + "</font></h3>"
					+ TipContents(d.type, d.address, d.tel),
			size : new AMap.Size(300, 0),
			autoMove : true,
			offset : new AMap.Pixel(0, -30)
		});
		windowsArr.push(infoWindow);
		var aa = function(e) {
			infoWindow.open(mapObj, mar.getPosition());
		};
		AMap.event.addListener(mar, "mouseover", aa);
	}

	//infowindow显示内容
	function TipContents(type, address, tel) { //窗体内容
		var str = "  地址：" + address || "暂无" + "<br />  电话：" + tel || "暂无"
				+ " <br />  类型：" + type || "暂无";
		return str;
	}
	function keydown(event) {
		var key = (event || window.event).keyCode;
		var result = document.getElementById("result1");
		var cur = result.curSelect;
		if (key === 40) {//down
			if (cur + 1 < result.childNodes.length) {
				if (result.childNodes[cur]) {
					result.childNodes[cur].style.background = '';
				}
				result.curSelect = cur + 1;
				result.childNodes[cur + 1].style.background = '#CAE1FF';
				document.getElementById("keyword").value = result.tipArr[cur + 1].name;
			}
		} else if (key === 38) {//up
			if (cur - 1 >= 0) {
				if (result.childNodes[cur]) {
					result.childNodes[cur].style.background = '';
				}
				result.curSelect = cur - 1;
				result.childNodes[cur - 1].style.background = '#CAE1FF';
				document.getElementById("keyword").value = result.tipArr[cur - 1].name;
			}
		} else if (key === 13) {
			var res = document.getElementById("result1");
			if (res && res['curSelect'] !== -1) {
				selectResult(document.getElementById("result1").curSelect);
			}
		} else {
			autoSearch();
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>商店列表 <span class="divider">/</span>
		</li>
		<li class="active">添加商店信息：</li>
	</ul>
	<form method="post" id="storeInfoForm">
		<table border="3" bordercolor="blue" width="800px" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>所属商铺：</th>
				<td><select name="merchant_id">
						<s:iterator value="#merchants">
							<option value="<s:property value="merchant_id"/>">
								<s:property value="name" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr>
				<th>商店名称：</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>商店介绍：</th>
				<td><input type="text" name="des" /></td>
			</tr>
			<tr>
				<th>商店电话：</th>
				<td><input type="text" name="store_phone" /></td>
			</tr>
			<tr>
				<th>省：</th>
				<td><input type="text" name="province" /></td>
			</tr>
			<tr>
				<th>市：</th>
				<td><input type="text" name="city" /></td>
			</tr>
			<tr>
				<th>详细地址：</th>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<th>经度：</th>
				<td><input type="text" name="latitude" id="latitude" /></td>
			</tr>
			<tr>
				<th>纬度：</th>
				<td><input type="text" name="longitude" id="longitude" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="tip">
						<b>请输入关键字：</b> <input type="text" id="keyword" name="keyword"
							value="" onkeydown='keydown(event)' style="width: 95%;" />
						<div id="result1" name="result1"></div>
					</div>
					<div id="mapDiv" style="width:800px; height:600px"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveStore()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('store.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
