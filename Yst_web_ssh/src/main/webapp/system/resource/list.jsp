<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
	$(function() {
		oTable = $("#table")
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"scrollX": true,
							"scrollCollapse": true,
        					"jQueryUI": true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "resource!listAjax.action",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search"));
								}
							},
							"columns" : [
									{
										"data" : "menu_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "url",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pid",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "order_index",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "des",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "sn",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "menu_id",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"resource!updatePage?menu_id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button><button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
													+ data + "\")'><i class='icon-trash'></i></button>";
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
	function setIdToConfirmDialog(id) {
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.bind('click', function() {
			pageGo("resource!delete?menu_id=" + id);
		});
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li class="active">管理员列表</li>
	</ul>
	
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('resource!addPage')" type="button">添加菜单
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>菜单ID</th>
					<th>菜单名称</th>
					<th>网络地址</th>
					<th>上级节点</th>
					<th>默认排序</th>
					<th>备注</th>
					<th>菜单序列号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
	<!-- dialog start -->
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
			<p id="confirmText">您确认要删除吗?</p>
			</p>
		</div>
		<div class="modal-footer">
			<button class="btn btn-danger" data-dismiss="modal"
				id="confirmDeleteDialogBut">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
	<!-- dialog end -->
</body>
</html>
