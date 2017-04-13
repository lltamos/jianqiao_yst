<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
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
				url : 'store!updateAjax', //提交给哪个执行
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
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>商店列表 <span class="divider">/</span>
		</li>
		<li class="active">添加商店信息：</li>
	</ul>

	<form method="post" id="storeInfoForm">
		<input type="hidden" name="store_id"
			value="<s:property value="store_id" />" />
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>所属商铺：</th>
				<td><select name="merchant_id">
						<s:iterator value="#merchants">
							<option value="<s:property value="merchant_id"/>"
								<s:if test="merchant_id ==merchant.merchant_id">selected</s:if>>
								<s:property value="name" /></option>
						</s:iterator>
				</select></td>
			</tr>
			<tr>
				<th>商店名称：</th>
				<td><input type="text" name="name"
					value="<s:property value="name" />" /></td>
			</tr>
			<tr>
				<th>商店介绍：</th>
				<td><input type="text" name="des"
					value="<s:property value="des" />" /></td>
			</tr>
			<tr>
				<th>商店电话：</th>
				<td><input type="text" name="store_phone"
					value="<s:property value="store_phone" />" /></td>
			</tr>
			<tr>
				<th>省：</th>
				<td><input type="text" name="province"
					value="<s:property value="province" />" /></td>
			</tr>
			<tr>
				<th>市：</th>
				<td><input type="text" name="city"
					value="<s:property value="city" />" /></td>
			</tr>
			<tr>
				<th>详细地址：</th>
				<td><input type="text" name="address"
					value="<s:property value="address" />" /></td>
			</tr>
			<tr>
				<th>经度：</th>
				<td><input type="text" name="latitude" id="latitude"
					value="<s:property value="latitude" />" /></td>
			</tr>
			<tr>
				<th>纬度：</th>
				<td><input type="text" name="longitude" id="longitude"
					value="<s:property value="longitude" />" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<iframe name="mapIframe" id="mapIframe" width="800px" height="600px" src="system/store/map.jsp">
					</iframe>
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
