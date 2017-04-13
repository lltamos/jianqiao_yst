<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
								"url" : "${ctx}/customer/customer-list",
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
								}
							},
							"columns" : [
									{
										"data" : "phone",
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
										"data" : "lastLoginTime",
										"render" : function(data, type, full,
												meta) {
											/* return format(data, 'yyyy-MM-dd HH:mm:ss') != null ? format(data, 'yyyy-MM-dd HH:mm:ss') : ""; */
												return data != null ? format(data, 'yyyy-MM-dd HH:mm:ss') : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {

											var deleteStr = "";
											(full.deleted == 0) ? deleteStr = "<button class='btn' type='button' title='禁用' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-trash'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='恢复' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-refresh'></i></button>";
											return ""
													+ deleteStr;
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
		/*  var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.bind('click', function() {
			pageGo("${ctx}/customer/customer-delete?id="+ id+"&deleted="+deleted);
		});  */
		var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
		$confirmDeleteDialogBut.attr('onclick', "setIdToConfirm(this,'${ctx}/customer/customer-delete?id="+ id+"&deleted="+deleted+"')");
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
	function queryInfo() {
		var query1 = $("#phone");
		var url = "${ctx}/customer/customer-list?phone=" + encodeURI(query1.val());
		oTable.ajax.url(url).load();
	}
	
	var format = function(time, format){
	    var t = new Date(time);
	    var tf = function(i){return (i < 10 ? '0' : '') + i};
	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
	        switch(a){
	            case 'yyyy':
	                return tf(t.getFullYear());
	                break;
	            case 'MM':
	                return tf(t.getMonth() + 1);
	                break;
	            case 'mm':
	                return tf(t.getMinutes());
	                break;
	            case 'dd':
	                return tf(t.getDate());
	                break;
	            case 'HH':
	                return tf(t.getHours());
	                break;
	            case 'ss':
	                return tf(t.getSeconds());
	                break;
	        }
	    })
	}

</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li class="active">客户列表</li>
		
	</ul>
<%-- 	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('${ctx}/customer/add-customer')" type="button">添加客户
		</button>
	</div> --%>
	<div class="controls">
				<label style="display: inline-block;" for="name">用户电话：</label>
				<input type="text" name="phone" id="phone" />
				<button class="btn" type="button" onclick="queryInfo()" id="query">查询</button>
			</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>电话</th>
					<th>是否禁用</th>
					<th>上次登录时间</th>
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
