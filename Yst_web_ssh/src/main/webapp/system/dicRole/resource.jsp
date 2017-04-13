<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	function check() {
		var checkeds =$('#jstree_checkbox').jstree().get_checked();
		var jstree =$('#jstree_checkbox');
		if(checkeds.length==0){
			jstree.tips({
				side : 2,
				msg : '请选择后在保存',
				bg : '#AE81FF',
				time : 3
			});
			return false;
		}
		return true;
	}
	// 添加科室
	function saveResource() {
		if (check()) {
			$('#checkeds').val($('#jstree_checkbox').jstree().get_checked());
			var form = $("#resourceInfoForm");
			var options = {
				url : 'role!resourceAjax', //提交给哪个执行
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
						pageGo('role.action');
					}
				} //显示操作提示
			};
			form.ajaxSubmit(options);
		}
	}
	function pageGo(href) {
		$("#index_div").load(href);
	}
	$(function() {
		//将jstree_main变成一棵树
		$("#jstree_checkbox").jstree(
				{
					"core" : {
						"themes" : {
							"name" : "default-dark"
						},
						"data" : {
							"url" : "role!showRoleTree?role_id=<s:property value="role_id" />",
							"dataType" : "json",
							"type" : "POST"
						},
					},
					"strings" : {
						"Loading ..." : "Please wait ..."
					},
					"plugins" : [ "wholerow", "themes", "html_data", "dnd",
							"checkbox" ]
				}).bind("loaded.jstree", function(e, data) {
				data.instance.open_all(1); // 1 opens all nodes in the container
		});
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>角色列表 <span class="divider">/</span>
		</li>
		<li class="active"><s:property value="role_name" />权限配置：</li>
	</ul>
	<form method="post" id="resourceInfoForm">
		<input  name="role_id" type="hidden" value="<s:property value="role_id" />"/>
		<input  name="checkeds" type="hidden" value="" id="checkeds"/>
		<table border="3" bordercolor="blue" width="90%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>权限列表：</td>
				<td width=90% height=100%>
					<div id="jstree_checkbox" style="width:100%;height: 100%">
						<!-- 树开始 -->

						<!-- 树结束-->
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="saveResource()" id="save">确定</button>
					<button class="btn" type="button"
						onclick="pageGo('role.action')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
</html>
