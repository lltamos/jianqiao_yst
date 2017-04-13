<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
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
							"searching" : true,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "doctorServiceOrder!listAjax.action",
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
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {
											return full.order_id != null ? full.order_id : "";
										}
									},
									{
										"data" : "str_customer_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "str_doctor_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "order_date",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "str_service_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "price",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									/* 支付状态 0 为未支付， 1 为已支付 */
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data == 0? "未支付":"已支付" : "";
										}
									},
									{
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {

													var	deleteStr = "<button class='btn' type='button' title='删除' onclick='pageGo(\"doctorServiceOrder!deleteAjax?order_id="
													+ data
													+ "\")'><i class='icon-trash'></i></button>";
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"doctorServiceOrder!showPage?order_id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button>"+"<button class='btn' type='button' title='编辑' onclick='pageGo(\"doctorServiceOrder!updatePage?order_id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
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
	function pageGo(href) {
		$("#index_div").load(href);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">订单记录列表</li>
	</ul>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>编号</th>
					<th>所属客户</th>
					<th>所属医生</th>
					<th>订单日期</th>
					<th>服务种类</th>
					<th>价格</th>
					<th>支付状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
			
		</table>
	</div>
</body>
</html>
