<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<%String doctor_id=request.getParameter("doctor_id");%>
<html>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='customer.phone']");
		if ($phone.val() == "") {
			$phone.tips({
				side : 2,
				msg : '名称不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$phone.focus();
			return false;
		} else {
			$phone.val(jQuery.trim($phone.val()));
		}
		return true;
	}
	// 添加科室
	function saveDoctorImage() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : 'serviceDesc!addAjax', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.error_info;
					var success = appResult.result;
					if (success != "SUCCESS" ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('serviceDesc.action');
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
		<li>服务描述列表<span class="divider">/</span>
		</li>
		<li class="active">添加服务描述：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>服务类型：</td>
				<td>
				<select name="type">
					<option value="1">疑难杂症</option>
					<option value="2">家庭服务</option>
					<option value="3">可购买的商品</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>服务图片：</td>
				<td><input type="file" name="image_url" /></td>
			</tr>
			<tr>
				<td>服务描述：</td>
				<td><textarea rows="" name="type_desc" rows="10" cols="20"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveDoctorImage()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('serviceDesc.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
