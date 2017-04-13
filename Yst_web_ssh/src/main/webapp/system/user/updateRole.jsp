<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var checkeds = $("#checkbox:checked");
		if (checkeds.size() == 0) {
			$("#save").tips({
				side : 2,
				msg : '请选择后在保存',
				bg : '#AE81FF',
				time : 3
			});
			return false;
		}
		return true;
	}
	// 保存用户角色信息
	function saveRole() {
		if (check()) {
			checkeds = "";
			var flg = false;
			$("#checkbox:checked").each(function() {
				if (flg) {
					checkeds += ",";
				} else {
					flg = true;
				}
				checkeds += $(this).val();
			});
			$("#checkeds").val(checkeds);
			var form = $("#userInfoForm");
			var options = {
				url : 'user!updateRole', //提交给哪个执行
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
						pageGo('user.action');
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
		<li>管理员列表 <span class="divider">/</span>
		</li>
		<li class="active">分配角色：</li>
	</ul>
	<form class="form-horizontal" id="userInfoForm" method="POST">
		<input name="user_id" value="<s:property value="user_id" />"
			type="hidden" /> <input id="checkeds" name="checkeds" value="" type="hidden" />
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户名：</th>
				<td colspan="2"><s:property value="login_name" /></td>
			</tr>
			<s:iterator value="#rolelist">
				<s:set var="flag" value="true"></s:set>
				<tr>
					<th>角色信息：</th>
					<td><input id="checkbox" value="<s:property value="role_id"/>" type="checkbox"
						<s:iterator value="roles">
								<s:if test="role_id==id">
								checked
								</s:if>
							</s:iterator> />
						<s:property value="role_name" />：<s:property value="des" /></td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="3" align="center"><button class="btn"
						type="button" onclick="saveRole()" id="save">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('user.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
