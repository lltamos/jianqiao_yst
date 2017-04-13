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
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "doctorHomeServiceOrders!approvalListAjax.action",
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
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "str_customer_phone",
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
										"data" : "str_service_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									/*支付状态 0 为未支付， 1 为已支付  3为等待确认，到家服务的订单提交之后改为3，后台确认后改为0*/
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											if(data == null){
												return "";	
											}
											if(data == 3&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
												return "否";	
											}
											if(data == 0&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
												return "是";
											}
											if(data == 1&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
												return "已支付";
											}
											if(data == 4&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
												return "已过期";
											}
										}
									},
									{
										"data" : "order_id",
										"render" : function(data, type, full,
												meta) {
												var	deleteStr ="";
													if(full.pay_status == 3&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
														deleteStr = "<button class='btn' type='button' title='审核' onclick='pageGo(\"doctorHomeServiceOrders!toApprovalDoctorHome?order_id="
														+ data
														+ "\")'><i class='icon-check'></i></button>";
													}
													if((full.pay_status == 0)&&(full.dicServiceType.service_type_id==3||full.dicServiceType.service_type_id==2||full.dicServiceType.service_type_id==4)){
													deleteStr = "<button class='btn' type='button' title='重新审核' onclick='pageGo(\"doctorHomeServiceOrders!toApprovalDoctorHome?order_id="
													+ data + "&verify=0\")'><i class='icon-share'></i></button>";
												}
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"doctorHomeServiceOrders!showApproval?order_id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button>"+deleteStr;
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
					<th>订单编号</th>
					<th>用户号码</th>
					<th>医生姓名</th>
					<th>类型</th>
					<th>是否审核</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
			
		</table>
	</div>
</body>
</html>
