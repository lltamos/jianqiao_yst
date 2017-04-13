<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var name = $("input[name='prodcutId']");
		if (name.val() == "") {
			name.tips({
				side : 2,
				msg : '项目编号不得为空',
				bg : '#AE81FF',
				time : 2
			});

			name.focus();
			return false;
		} else {
			name.val(jQuery.trim(name.val()));
		}
		var $phone = $("input[name='prodcutName']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '项目名称不得为空',
				bg : '#AE81FF',
				time : 2
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		var $password = $("input[name='money']");
		if ($password.val() == "") {
			$password.tips({
				side : 2,
				msg : '价格不得为空',
				bg : '#AE81FF',
				time : 2
			});

			$password.focus();
			return false;
		} else {
			$password.val(jQuery.trim($password.val()));
		}
		return true;
	}
	function savePatientOrder() {
		if (check()) {
			var form = $("#userInfoForm");
			var options = {
				url : '${baseurl}/ydmvc/main/after/ajaxAddPatientOrder.do', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					$("#save").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
					if (success == "SUCCESS") {
						pageGo('${baseurl}/ydmvc/main/after/patientPage.do');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
</script>
<body>
	<ul class="breadcrumb">
		<li class="active">添加患者订单：</li>
	</ul>
	<br>
	<form class="form-horizontal" id="userInfoForm" method="post" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label" for="patientName">患者姓名：</label>
			<div class="controls">
				<input type="hidden" name="patientId" id="patientId" value="${patient.id }" />
				${patient.patientName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phone">电话：</label>
			<div class="controls">
			<input type="hidden" name="phone" id="phone" value="${patient.patientPhone }" />
				${patient.patientPhone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">地址：</label>
			<div class="controls">
			<input type="hidden" name="address" id="address" value="${patient.address }" />
				${patient.address }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.name">患者推荐人姓名：</label>
			<div class="controls">
				${patient.customer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="customer.phone">患者推荐人手机：</label>
			<div class="controls">
				${patient.customer.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="expertCustomer.name">专家推荐人姓名：</label>
			<div class="controls">
				${patient.expertCustomer.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="expertCustomer.phone">专家推荐人手机：</label>
			<div class="controls">
				${patient.expertCustomer.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="productId">项目编号：</label>
			<div class="controls">
					<select  name="productId" id="productId" style="width: 500px;">
						<c:forEach items="${products }" var="revar">
							<option value="${revar['product_id'] }">${revar['product_id'] },${revar['name'] },${revar['price']/100 }元,<c:if test="${revar['divide']>0 }">可分${revar['divide'] }期</c:if><c:if test="${revar['divide']<1 }">不可分期</c:if></option>
						</c:forEach>
					</select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn"
						type="button" onclick="savePatientOrder()" id="save">保存</button>
					<button class="btn" type="button" onclick="pageGo('${baseurl}/ydmvc/main/after/patientPage.do')">返回</button>
			</div>
		</div>
	</form>
</body>
</html>
