<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
	// 添加图文介绍
	function saveDoctorImage() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx}/doctorimage/doctorimage-add', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					//从返回的json数据中获取结果信息
					//结果提示信息
					var appResult = eval("(" + result + ")");
					var message = appResult.msg;
					var success = appResult.code;
					if (success == 0 ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('${ctx}/doctorimage/to-doctorimage-list?doctorid=${doctorid}');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>图文介绍列表 <span class="divider">/</span>
		</li>
		<li class="active">添加图文介绍：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<input type="hidden" name="doctorId" value="${doctorid }"/>
				<td>图片描述：</td>
				<td><input type="text" name="des" /></td>
			</tr>
			<tr>
				<td>上传医生图片：</td>
				<td><input type="file" name="image" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveDoctorImage()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx}/doctorimage/to-doctorimage-list?doctorid=${doctorid}')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
