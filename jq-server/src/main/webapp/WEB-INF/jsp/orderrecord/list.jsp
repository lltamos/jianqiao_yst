<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%String doctor_id=request.getParameter("doctor_id");%>
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
								"url" : "${cxt}/doctorserviceorder/doctorserviceorder-list",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search")+7)+ data
													.substring(
															data
																	.indexOf("&search%5Bvalue%5D") + 18,
															data
																	.indexOf("&search%5Bregex%5D"));
								}
							},
							"columns" : [
									
									{
										/* "data" : "order_id", */
										"data" : "orderId",
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
									{
										"data" : "doctorPhone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									/* {
										"data" : "serviceType",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data == 1? "在线咨询":"在线预约":"";
										}
									}, */
									{
										"data" : "price",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data/100 : "";
										}
									},
									/* 支付状态 0 为未支付， 1 为已支付 */
									{
										"data" : "payStatus",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data == 0? "未支付":"已支付" : "";
										}
									},
									{
										"data" : "createdTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									}
									/* {
										"data" : "id",
										"render" : function(data, type, full,
												meta) {

													var	deleteStr = "<button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
														+ data +"\",\""+full.status+"\")'><i class='icon-trash'></i></button>";
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"${ctx}/doctorserviceorder/doctorserviceorder-view?id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button>"+"<button class='btn' type='button' title='编辑' onclick='pageGo(\"${ctx}/doctorserviceorder/doctorserviceorder-update?id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
													+ deleteStr;
									}
									}  */
									],
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
	function pageGo(href) {
		$("#index_div").load(href);
	}
	function queryInfo() {
		var orderId = $("#orderId").val();
		var customerPhone = $("#customerPhone").val();
		var doctorPhone = $("#doctorPhone").val();
		var serviceType = $("#serviceType").val();
		var payStatus = $("#payStatus").val();
		var url = "${ctx}/doctorserviceorder/doctorserviceorder-list?orderId=" +encodeURI(orderId)
				+ "&customerPhone="+encodeURI(customerPhone)
				+ "&doctorPhone="+encodeURI(doctorPhone)
				+ "&payStatus="+encodeURI(payStatus);
		oTable.ajax.url(url).load();
	}
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
		$confirmDeleteDialogBut.attr('onclick', "setIdToConfirm(this,'${ctx}/doctorserviceorder/doctorserviceorder-delete?id="+ id+"&status="+status+"')");
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
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li class="active">订单记录列表</li>
	</ul>
	<form class="form-horizontal" id="QForm" onSubmit="return false;">
			<label style="margin-left:25px;display: inline-block;" for="orderId">订单号：</label>
			<input style="width:148px;" type="text" name="orderId" id="orderId" value="${orderId }" />
			<label style="margin-left:1px;display: inline-block;" for="customerPhone">用户帐号：</label>
			<input style="width:148px;" type="text" name="customerPhone" id="customerPhone" value="${customerPhone }" />
			<label style="margin-left:1px;display: inline-block;" for="doctorPhone">医生账号：</label>
			<input style="width:148px;" type="text" name="doctorPhone" id="doctorPhone" value="${doctorPhone }" />
			<label style="margin-left:-1px;display: inline-block;" for="payStatus">订单状态：</label>
			<select style="width:148px;" id="payStatus" name="payStatus">
					<option ></option>
					<option value="0">未支付</option>
					<option value="1">已支付 </option>
			</select>
			<button class="btn" type="button" onclick="queryInfo()" id="query">查询</button>
	</form>
	<!-- <div class="controls">
				<label style="display: inline-block;" for="name">用户名称：</label>
				<input type="text" name="name" id="name" />
				<button class="btn" type="button" onclick="queryInfo()" id="query">查询</button>
	</div> -->
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>订单编号</th>
					<th>用户账号</th>
					<th>医生账号</th>
					<!-- <th>服务类型</th> -->
					<th>订单金额</th>
					<th>订单状态</th>
					<th>订单时间</th>
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
