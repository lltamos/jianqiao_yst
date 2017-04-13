<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
								"url" : "${ctx}/merchant/merchant-list-data",
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
																	.indexOf("&search%5Bregex%5D"));
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
										"data" : "name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "customerPhone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									/* {
										"data" : "customerName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									}, */
									/*{
										"data" : "verify",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data == 0 ? "待审核"
													: data == 1 ? "已通过" : "已拒绝，原因："+full.reject_reason
													: "";
										}
									},*/
									{
										"data" : "deleted",
										"render" : function(data, type, full,
												meta) {
											return ((data == 0) ? "未" : "已")
													+ "禁用";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											var deleteStr = "";
											(full.deleted == 0) ? deleteStr = "<button class='btn' type='button' title='禁用' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-ban-circle'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='启用' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-ok-circle'></i></button>";
											return "<button class='btn' type='button' title='查看详情' onclick='pageGo(\"${ctx}/merchant/merchant-info?id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button><button class='btn' type='button' title='编辑' onclick='pageGo(\"${ctx}/merchant/merchant-input?id="
													+ data + "\")'><i class='icon-pencil'></i></button>"+deleteStr;
													/*+((full.verify == 0)?"<button class='btn' type='button' onclick='pageGo(\"merchant!verifyPage?merchant_id="
													+ data + "\")'>审核</button>":(full.verify == 2)?"<button class='btn' type='button' onclick='pageGo(\"merchant!verifyPage?merchant_id="
													+ data + "\")'>重新审核</button>":"") */
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
			$("#confirmText").empty().append("您确认要启用吗？");
			deleted=0;
		}
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.attr('onclick', "setIdToConfirm(this,'${ctx}/merchant/merchant-deleted?id="+ id+"&deleted="+deleted+"')");
	}
	function setIdToConfirm(e,url){
		$.ajax({
			url : url,
			type : 'POST',
			success : function(result) {
				var result = JSON.parse(result);
				var message = result.msg;
				var code = result.code;
				$(e).tips({
					side : 2,
					msg : message,
					bg : '#68B500',
					time : 2
				});
				//刷新数据
				oTable.draw(false);
			} //显示操作提示
		});
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li class="active">总院列表</li>
	</ul>
	<div class="btn-toolbar">
		<button style="margin-right:60px; margin-left:25px;" class="btn" onclick="pageGo('${ctx}/merchant/merchant-input')"
			type="button">添加总院</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>总院名称</th>
					<th>所属用户手机号</th>
					<!-- <th>所属用户姓名</th> -->
					<!--  <th>审核状态</th>-->
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
