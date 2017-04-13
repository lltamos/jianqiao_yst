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
								"url" : "productOrder!listAjax.action",
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
										"data" : "pay_relative_id",
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
										"data" : "recomm_phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "product_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "price",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data / 100
													: "";
										}
									},
									{
										"data" : "merchant_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "product_recomm_phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "divide",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											return ((data == 0) ? "未" : "已")
													+ "支付";
										}
									},
									{
										"data" : "pay_type",
										"render" : function(data, type, full,
												meta) {
											return data == null ? "未支付":data == 0 ? "系统内支付"
													: data == 1 ? "支付宝支付": "银联支付";
										}
									},
									{
										"data" : "create_date",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "product_order_id",
										"render" : function(data, type, full,
												meta) {
											var sendStr = "";
											if (full.product_for == 1
													&& full.send_status == 0) {
												sendStr = "<button class='btn' type='button' title='发货' onclick='pageGo(\"productOrder!sendPage?product_order_id="
														+ data
														+ "\")'><i class='icon-shopping-cart'></i></button>";
											}
											var deleteStr = "";
											(full.deleted == 0) ? deleteStr = "<button class='btn' type='button' title='删除' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-trash'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='恢复' onclick='setIdToConfirmDialog(\""
													+ data +"\",\""+full.deleted+"\")'><i class='icon-refresh'></i></button>";
											return "<button class='btn' type='button' title='查看订单详情' onclick='view(\""
												+ data +"\")'><i class='icon-eye-open'></i></button>"+sendStr + deleteStr;
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
			pageGo("productOrder!disable?product_order_id="+ id+"&deleted="+deleted);
		});
	}
	function view(id){
		pageGo("${baseurl}/ydmvc/main/after/viewProductOrderPage.do?id="+id);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">订单列表</li>
	</ul>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>订单号</th>
					<th>用户帐号</th>
					<th>用户推荐人</th>
					<th>项目名称</th>
					<th>项目总价</th>
					<th>总院</th>
					<th>项目推荐人</th>
					<th>项目分期</th>
					<th>订单状态</th>
					<th>支付方式</th>
					<th>时间</th>
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
