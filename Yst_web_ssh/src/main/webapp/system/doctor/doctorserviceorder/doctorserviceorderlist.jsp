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
							"scrollCollapse" : false,
							"jQueryUI" : true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "ydmvc/main/after/doctorserviceorder/findDoctorServiceOrderByPage.do",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data.indexOf("&columns"))
											+ data.substring(data.indexOf("start") - 1, data.indexOf("&search") + 7)
											+ data.substring(data.indexOf("&search%5Bvalue%5D") + 18,data.indexOf("&search%5Bregex%5D"))
											//+"&search_EQ_customerId="+${sessionScope.dbCustomer.customer_id}
											; 
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
										"data" : "customer.phone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pay_type",
										"render" : function(data, type, full,
												meta) {
											//支付类型 0 为系统内支付,1 为支付宝, 2 为银联
											return data == 0 ? "系统内支付" : data == 1 ? "支付宝" : data == 2 ? "银联" : "";
										}
									},
									{
										"data" : "price",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											//支付状态 0 为未支付， 1 为已支付  3为等待确认，到家服务的订单提交之后改为3(针对到家和会诊服务)，后台确认后改为0
											return data == 0 ? "未支付" : data == 1 ? "已支付" : data == 3 ? "等待确认" : "";
										}
									},
									{
										"data" : "",
										"render" : function(data, type, full,
												meta) {
											//支付状态 0 为未支付， 1 为已支付  3为等待确认，到家服务的订单提交之后改为3(针对到家和会诊服务)，后台确认后改为0
											return "";
										}
									}],
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
	 //查询
     function serach()
    {
		var url = "ydmvc/shareMoney/shareMoney.do";
		url=url+"?search_LIKE_projectName="+($("#nameText").val()).trim();
		oTable.ajax.url(url).load();
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li class="active">分润记录</li>
	</ul>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
				    <th>订单号</th>
					<th>用户账号</th>
					<th>订单类型</th>
					<th>订单金额</th>
					<th>分润金额</th>
					<th>订单状态</th>
					<th>分润时间</th>
				</tr>
			</thead>
			<tbody></tbody>
			
		</table>
	</div>
</body>
</html>