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
								"url" : "productOrder!findProductOrderList",
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
										"data" : "product.name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "price",//项目总价
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "pay_status",
										"render" : function(data, type, full,
												meta) {
											// 0 待支付定金 1已支付定金 2已支付全款3确认一期款 
											// 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款
											// 9退二期款 10退三期款 11退四期款 12退五期款 13订单完成
											return data == 0 ? "待支付定金" : data == 1 ? "已支付全款" : 
												data == 3 ? "确认一期款" : data == 4 ? "确认二期款" :
												data == 5 ? "确认三期款" : data == 6 ? "确认四期款" : 
												data == 7 ? "确认五期款" : data == 8 ? "退一期款" : 
												data == 9 ? "退二期款" : data == 10 ? "退三期款" :
												data == 11 ? "退四期款" : data == 12 ? "退五期款" : 
												data == 13 ? "订单完成" : "";
										}
									},
									{
										"data" : "share_money",//分润金额
										"render" : function(data, type, full,
												meta) {
											return data != null ? data / 100: "";
										}
									},
									{
										"data" : "share_time",//分润时间
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
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
			已分润总金额:0000+188<br/>
			已提现总金额:2000<br/>
			可提现总金额:5000+188<br/>
	           项目名称：<input type="text" style="width: 200px; height: 30px;" id="nameText"/>
		<input type="button" value="查询" onclick="serach();"/>
		<input type="button" value="提现" />
			<thead>
				<tr>
				    <th>订单号</th>
					<th>用户账号</th>
					<th>项目名称</th>
					<th>项目总价</th>
					<th>项目分期</th>
					<!-- <th>该期金额</th> -->
					<th>订单状态</th>
					<th>分润金额</th>
					<th>分润时间</th>
				</tr>
			</thead>
			<tbody></tbody>
			
		</table>
	</div>
</body>
</html>