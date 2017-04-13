<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var $phone = $("input[name='name']");
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
	// 保存用户信息
	function saveResource() {
		if (check()) {
			var form = $("#resourceInfoForm");
			var options = {
				url : 'resource!updateAjax', //提交给哪个执行
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
						window.location = "user!index";
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
		<li>菜单列表 <span class="divider">/</span>
		</li>
		<li class="active">修改菜单信息：</li>
	</ul>
	<form class="form-horizontal" id="resourceInfoForm" method="POST">
		<input name="menu_id" value="<s:property value="menu_id" />"
			type="hidden" /> 

		<table border="3" bordercolor="blue" width="40%" cellspacing="1"
			cellpadding="1">
			
			<tr>
				<th>菜单名称：</th>
				<td><input name="name" value="<s:property value="name" />" type="text" /></td>
			</tr>

			<tr>
				<th>网络地址：</th>
				<td><input name="url" value="<s:property value="url" />" type="text" /></td>
			</tr>
			<tr>
				<th>上级节点：</th>
				<td>
				<select name="parent.menu_id" >
				<s:set value="parent.menu_id" name="parent_menu_id"/>
						<option value="0">无</option>
						<s:iterator value="#menus" >
							<option value=<s:property value="menu_id"/> <s:if test="menu_id ==#parent_menu_id">selected</s:if> >
							<s:property value="name" /></option>
						</s:iterator>
				</select>
				</td>
			</tr>
			<tr>
				<th>默认排序：</th>
				<td><input name="order_index" value="<s:property value="order_index" />" type="text" /></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><input name="des" value="<s:property value="des" />" type="text" /></td>
			</tr>
			<tr>
				<th>菜单序列号：</th>
				<td><input name="sn" value="<s:property value="sn" />" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn" type="button"
						onclick="saveResource()" id="save">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('resource.action')">返回</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
