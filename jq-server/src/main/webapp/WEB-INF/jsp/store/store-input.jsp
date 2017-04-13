<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var merchantId = $("#merchantId");
		if (merchantId.val() == "") {
			merchantId.tips({
				side : 2,
				msg : '请选择所属总院',
				bg : '#AE81FF',
				time : 1
			});
			merchantId.focus();
			return false;
		}
		var name = $("input[name='name']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '分院名称不得为空',
				bg : '#AE81FF',
				time : 1
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var hospitalTypeId = $("#hospitalTypeId");
		if (hospitalTypeId.val() == "") {
			hospitalTypeId.tips({
				side : 2,
				msg : '请选择所属总院',
				bg : '#AE81FF',
				time : 1
			});
			hospitalTypeId.focus();
			return false;
		}
		var des = $("input[name='des']");
		if (des.val() == "") {
			des.tips({
				side : 2,
				msg : '分院介绍不得为空',
				bg : '#AE81FF',
				time : 1
			});

			des.focus();
			return false;
		} else {
			des.val(jQuery.trim(des.val()));
		}
		var phone = $("input[name='storePhone']");
		if (phone.val() == "") {
			phone.tips({
				side : 2,
				msg : '分院电话不得为空',
				bg : '#AE81FF',
				time : 1
			});

			phone.focus();
			return false;
		} else if (isNaN(phone.val())) {
			phone.tips({
				side : 2,
				msg : '请输入正确的手机号！',
				bg : '#AE81FF',
				time : 1
			});

			phone.focus();
			return false;
		} else {
			phone.val(jQuery.trim(phone.val()));
		}
		var prov_id = $("#provSel");
		if (prov_id.val() == "") {
			prov_id.tips({
				side : 2,
				msg : '请选择省份',
				bg : '#AE81FF',
				time : 1
			});
			prov_id.focus();
			return false;
		}
		$("#province").val($("#provSel option:selected").text());
		var city_id = $("#citySel");
		if (city_id.val() == "") {
			city_id.tips({
				side : 2,
				msg : '请选择城市',
				bg : '#AE81FF',
				time : 1
			});

			city_id.focus();
			return false;
		}
		$("#city").val($("#citySel option:selected").text());
		var address = $("input[name='address']");
		if (address.val() == "") {
			address.tips({
				side : 2,
				msg : '详细地址不得为空',
				bg : '#AE81FF',
				time : 1
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
				msg : '请在地图中点击所在位置获取坐标',
				bg : '#AE81FF',
				time : 1
			});

			latitude.focus();
			return false;
		} else {
			if (isNaN(latitude.val())) {
				latitude.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 1
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
				msg : '请在地图中点击所在位置获取坐标',
				bg : '#AE81FF',
				time : 1
			});

			longitude.focus();
			return false;
		} else {
			if (isNaN(longitude.val())) {
				longitude.tips({
					side : 2,
					msg : '请输入数字',
					bg : '#AE81FF',
					time : 1
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
				url : '${ctx }/store/store-save', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (code==1) {
					pageGo('${ctx }/store/store-list'); 
						/*  window.location.href ="${ctx }/store/store-list"; */
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function provChange() {
		var city = $.ajax({
			url : "${ctx}/store/store-city?provId=" + $("#provSel").val(),
			type : 'POST',
			async: false,
			success : function(result) {
				var data = eval("(" + result + ")");
				$("#citySel").empty().append(
						"<option value=''>请选择市</option>");
				$.each(data, function() {
					$("#citySel").append(
							"<option value="+this.id+">" + this.name
									+ "</option>");
				});
			}
		});
	}
	function checkCity(){
		var prov_id = $("#provSel");
		if (prov_id.val() == "") {
			prov_id.tips({
				side : 2,
				msg : '请选择省份',
				bg : '#AE81FF',
				time : 1
			});
			prov_id.focus();
			return false;
		}
	}
	$(function(){
		 provChange();
		 var city="${store.city}";
		 if(city!=""){
			 $("#citySel option:contains('"+city+"')").attr("selected", true);
		 }
	});
	//检查分院是否存在
	$("#name").blur(function(){
		var merchantId = $("#merchantId").val();
		var name = $("#name").val();
		if(name!=""){
			$.ajax({
				url : "${ctx}/store/store-check?merchantId="+merchantId+"&name="+name,
				type : 'POST',
				success : function(result) {
					var result = JSON.parse(result);
					var message = result.msg;
					var code = result.code;
					if(code==0){
						$("#name").tips({
							side : 2,
							msg : '分院已经存在',
							bg : '#AE81FF',
							time : 1
						});
						$("#name").val("");
					}
					//刷新数据
					oTable.draw(false);
				} //显示操作提示
			});
		} 
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li>分院列表</li>
		<li class="active">分院信息：</li>
	</ul>
	<form class="form-horizontal" id="storeInfoForm" method="post">
		<input type="hidden" name="id" id="id" value="${store.id }"/>
		<div class="control-group">
			<label class="control-label" for="merchantId">所属总院：</label>
			<div class="controls">
				<select name="merchantId" id="merchantId">
					<option value="" >请选择</option>
					<c:forEach items="${merchants }" var="merchant">
						<option id="merchantId" value="${merchant.id }" <c:if test="${store.merchantId==merchant.id }">selected</c:if>>${merchant.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">分院名称：</label>
			<div class="controls">
				<input type="text" name="name" id="name" value="${store.name }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="hospitalTypeId">分院等级：</label>
			<div class="controls">
				<select name="hospitalTypeId" id="hospitalTypeId">
					<option value="" >请选择</option>
					<c:forEach items="${hospitalType }" var="hospitalType">
						<option id="hospitalTypeId" value="${hospitalType.id }" <c:if test="${store.hospitalTypeId==hospitalType.id }">selected</c:if>>${hospitalType.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="des">分院介绍：</label>
			<div class="controls">
				<input type="text" name="des" id="des" value="${store.des }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="storePhone">分院电话：</label>
			<div class="controls">
				<input type="text" name="storePhone" id="storePhone" value="${store.storePhone }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="province">省：</label>
			<div class="controls">
				<input type="hidden" name="province" id="province" />
				<select name="provSel" id="provSel" onchange="provChange()">
					<option value="" >请选择省</option>
					<c:forEach items="${provs }" var="prov">
						<option value="${prov.id }" <c:if test="${store.province==prov.name }">selected</c:if>>${prov.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="city">市：</label>
			<div class="controls">
				<input type="hidden" name="city" id="city" />
				<select name="citySel" id="citySel" onclick="checkCity()">
					<option value="" >请选择市</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">详细地址：</label>
			<div class="controls">
				<input type="text" name="address" id="address" value="${store.address }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="latitude">经度：</label>
			<div class="controls">
				<input type="text" name="latitude" id="latitude" readonly="readonly" value="${store.latitude }"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="longitude">纬度：</label>
			<div class="controls">
				<input type="text" name="longitude" id="longitude" readonly="readonly" value="${store.longitude }"/>
			</div>
		</div>
			<iframe name="mapIframe" id="mapIframe" width="500px" height="300px" src="${ctx}/store/store-map">
			</iframe>
		<div class="control-group">
			<div class="controls">
				<button class="btn"
						type="button" onclick="saveStore()" id="save">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/store/store-list')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
