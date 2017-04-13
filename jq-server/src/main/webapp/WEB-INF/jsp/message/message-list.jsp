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
								"url" : "${ctx}/message/getmessage",
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
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
									"data" : "image",
									"render" : function(data, type, full,
											meta) {
										return data != null ? "<img style='height: 150px;width: 200px' src=${img_service }/"+data+">" : "";
									}
									},
									{
										"data" : "title",
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
										"data" : "address",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											var deleteStr = "";
											(full.status == 0) ? deleteStr = "<button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.status+"\")'><i class='icon-trash'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='恢复' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.status+"\")'><i class='icon-refresh'></i></button>";
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"${ctx}/message/message-input?id="
													+ data + "\")'><i class='icon-pencil'></i></button>"+deleteStr;
													/* <button class='btn' type='button' title='查看详情' onclick='pageGo(\"${ctx}/message/message-info?id="
														+ data
														+ "\")'><i class='icon-eye-open'></i></button> */
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
	function setIdToConfirmDialog(id,status) {
		$("#deleteModal").modal({
			backdrop : "static"
		});
		$("#deleteModal").modal('show');
		if(status==0){//删除
			$("#confirmText").empty().append("您确认要删除吗？");
			status=1;
		}else{
			$("#confirmText").empty().append("您确认要恢复吗？");
			status=0;
		}
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.attr('onclick', "setIdToConfirm(this,'${ctx}/message/message-deleted?id="+ id+"&status="+status+"')");
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
					time : 3
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
		<li class="active">资讯列表</li>
	</ul>
	<div class="btn-toolbar">
		<button style="margin-right:60px; margin-left:25px;" class="btn" onclick="pageGo('${ctx}/message/message-input')"
			type="button">添加资讯</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>编号</th>
					<th>图片</th>
					<th>标题</th>
					<th>文字描述</th>
					<th>跳转地址</th>
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
