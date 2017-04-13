<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<script type="text/javascript">
	login_name = "${sessionScope.alqsoft.loginName}";
	$(function() {
		oTable = $("#table")
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"scrollX" : true,
							"scrollCollapse" : true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "${ctx}/user/user-list-data",
								"type" : "post",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search") + 7)
											+ data
													.substring(
															data
																	.indexOf("&search%5Bvalue%5D") + 18,
															data
																	.indexOf("&search%5Bregex%5D"))+"&login_name=${login_name}";
								},
								"complete":function(xhr,ts){
									if(ts=="parsererror"){
										alert("登录超时，请重新登录！");
										window.location="${ctx}/user/user-logout";
									}
								}
							},
							"columns" : [
									{
										"data" : "loginName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "deleted",
										"render" : function(data, type, full,
												meta) {
											return data != null ? (data == 0) ? "未禁用"
													: "已禁用"
													: "已禁用";
										}
									},
									{
										"data" : "createdTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "updateTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											var isAdminStr = "";
											var deleteStr = "";
											if(full.login_name!=login_name){
												(full.is_admin == 0) ? isAdminStr = "<button class='btn' type='button' title='设为超级管理员' onclick='setAdminToConfirmDialog(\""
														+ data
														+ "\",\""
														+ full.is_admin
														+ "\")'><i class='icon-star-empty'></i></button>"
														: isAdminStr = "<button class='btn' type='button' title='设为普通管理员' onclick='setAdminToConfirmDialog(\""
																+ data
																+ "\",\""
																+ full.is_admin
																+ "\")'><i class='icon-star'></i></button>";
												(full.deleted == 0) ? deleteStr = "<button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
													+ data
													+ "\",\""
													+ full.deleted
													+ "\")'><i class='icon-trash'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='恢复' onclick='setIdToConfirmDialog(\""
															+ data
															+ "\",\""
															+ full.deleted
															+ "\")'><i class='icon-refresh'></i></button>";
											}
											return "<button class='btn' type='button' title='重置密码' onclick='pageGo(\"user!updatePage?system_user_id="
													+ data
													+ "\")'><i class='icon-repeat'></i></button>"+isAdminStr
													+  deleteStr;
										}
									} ],
							"language" : {
								"emptyTable" : "没有数据",
								"info" : "显示 第 _START_ 到 _END_ 条数据  总共 _TOTAL_ 条数据 ",
								"infoEmpty" : "没有数据",
								"infoFiltered" : "(总共  _MAX_ 条数据)",
								"infoPostFix" : "",
								"thousands" : ",",
								"lengthMenu" : "每页显示条数 _MENU_ ",
								"loadingRecords" : "加载中...",
								"processing" : "处理中...",
								"search" : "搜索:",
								"zeroRecords" : "找不到匹配的数据",
								"paginate" : {
									"first" : "首页",
									"last" : "尾页",
									"next" : "下一页",
									"previous" : "上一页"
								}
							}
						});

	});
	function queryUser(){
			var url = "${ctx}/user/user-list-data";
			url=url+"?search_LIKE_loginName=" +encodeURI($("#login_name").val().trim());
			oTable.ajax.url(url).load();
	}
	function setIdToConfirm(url){
			$.ajax({
				url : url,
				type : 'POST',
				success : function(result) {
					//刷新数据
					oTable.draw(false);
				} //显示操作提示
			});
	}
	function setIdToConfirmDialog(id, deleted) {
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		if (deleted == 0) {//删除
			$("#confirmText").empty().append("您确认要禁用吗？");
			deleted = 1;
		} else {
			$("#confirmText").empty().append("您确认要恢复吗？");
			deleted = 0;
		}
		$("#confirmDeleteDialogBut").attr("onclick","setIdToConfirm('user!disable?system_user_id=" + id + "&deleted="+ deleted+"')");
	}
	function setAdminToConfirm(url){
			$.ajax({
				url : url,
				type : 'POST',
				success : function(result) {
					//刷新数据
					oTable.draw(false);
				} //显示操作提示
			});
	}
	function setAdminToConfirmDialog(id, is_admin) {
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		if (is_admin == 0) {//普通
			$("#confirmText").empty().append("您确认要此用户设为超级管理员吗？");
			is_admin = 1;
		} else {
			$("#confirmText").empty().append("您确认要此用户设为普通管理员吗？");
			is_admin = 0;
		}
		$("#confirmDeleteDialogBut").attr("onclick","setAdminToConfirm('user!setAdmin?system_user_id=" + id + "&is_admin="+ is_admin+"')");
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a></li>
		<li class="active">管理员列表</li>
	</ul>
	<form class="form-horizontal" id="InfoForm" onSubmit="return false;">
	<button style="margin-right:60px; margin-left:25px;" class="btn" onclick="pageGo('user!addPage')" type="button">添加新用户</button>
			<label style="display: inline-block;" for="login_name">用户名：</label>
				<input style="margin-right: 60px;" type="text" name="login_name" id="login_name" value="${login_name }" />
				<button class="btn"
						type="button" onclick="queryUser()" id="query">查询</button>
	</form>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>用户名</th>
					<th>是否禁用</th>
					<th>创建时间</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
	<%-- dialog start --%>
	<div class="modal small hide fade" id="deleteModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">请确认</h3>
		</div>
		<div class="modal-body">
			<p class="error-text">
				<i class="icon-warning-sign modal-icon"></i>
			<p id="confirmText">您确认要禁用吗?</p>
		</div>
		<div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal"
				id="confirmDeleteDialogBut">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
	<%-- dialog end --%>
</body>
</html>
