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
								"url" : "${baseurl}/patient/patient-list",
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
										"data" : "patientName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "patientPhone",
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
										"data" : "customer.name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "customer.phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "expertCustomer.name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "expertCustomer.phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return  "<button class='btn' type='button' title='添加订单' onclick='addOrder(\""
													+ data +"\")'><i class='icon-plus'></i></button><button class='btn' type='button' title='查看订单列表' onclick='orderList(\""
													+ data +"\")'><i class='icon-eye-open'></i></button>";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='查看档案详情' onclick='view(\""
													+ data +"\")'><i class='icon-eye-open'></i></button>";
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
	function view(id){
		pageGo("${ctx}/patient/patient-view?id="+id);
	}
	function addOrder(id){
		//pageGo("${ctx}/patientorder/to-patientOrder-list?patientId="+id);
	}
	function orderList(id){
		pageGo("${ctx}/patientorder/to-patientOrder-list?patientId="+id);
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">患者列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" style="margin-right:60px; margin-left:20px;" onclick="pageGo('${ctx}/patient/to-patient-add')" type="button">添加患者信息
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>姓名</th>
					<th>电话</th>
					<th>家庭住址</th>
					<th>患者推荐人名称</th>
					<th>患者推荐人手机</th>
					<th>专家推荐人名称</th>
					<th>专家推荐人手机</th>
					<th>订单记录</th>
					<th>档案详情</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
