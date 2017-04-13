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
							"scrollX" : true,
							"scrollCollapse" : true,
							"jQueryUI" : true,
							"paging" : true,
							"ordering" : false,
							"searching" : true,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "comment!listAjax.action",
								"type" : "POST",
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
																	.indexOf("&search%5Bregex%5D"))
											+ "&comment_for_type=<s:property value="comment_for_type" />&comment_for_id=<s:property value="comment_for_id " />";
								}
							},
							"columns" : [
									{
										"data" : "user_comment_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "customer_phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "customer_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "customer_comment",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "comment_date",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "comment_for_type",
										"render" : function(data, type, full,
												meta) {
											return data != null ? (data == 1) ? "主题活动"
													: (data == 2) ? "知识库"
															: (data == 3) ? "视频"
																	: (data == 4) ? "服务包"
																			: ""
													: "";
										}
									},
									{
										"data" : "comment_for_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "deleted",
										"render" : function(data, type, full,
												meta) {
											return ((data == 0) ? "未" : "已")
													+ "禁用";
										}
									},
									{
										"data" : "user_comment_id",
										"render" : function(data, type, full,
												meta) {

											var deleteStr = "";
											(full.deleted == 0) ? deleteStr = "<button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-trash'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='恢复' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-refresh'></i></button>";
											return deleteStr;
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
	function setIdToConfirmDialog(id,deleted) {
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		if(deleted==0){//删除
			$("#confirmText").empty().append("您确认要禁用吗？");
			deleted=1;
		}else{
			$("#confirmText").empty().append("您确认要恢复吗？");
			deleted=0;
		}
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.bind('click', function() {
			pageGo("comment!disable?user_comment_id="+ id+"&deleted="+deleted);
		});
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">评论列表</li>
	</ul>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>编号</th>
					<th>手机号</th>
					<th>用户名称</th>
					<th>评论内容</th>
					<th>评论时间</th>
					<th>评论类型</th>
					<th>评论对象ID</th>
					<th>是否禁用</th>
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
			<p id="confirmText">您确认要禁用吗?</p>
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
