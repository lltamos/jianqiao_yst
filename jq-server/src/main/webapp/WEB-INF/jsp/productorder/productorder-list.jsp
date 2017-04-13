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
							"scrollX" : true,
							"scrollCollapse" : true,
							"jQueryUI" : true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "${ctx}/productorder/productorder-list-data",
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
										"data" : "payRelativeId",
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
										"data" : "recommPhone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "productName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "totalPrice",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data / 100
													: "";
										}
									},
									{
										"data" : "merchantName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "productRecommPhone",
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
										"data" : "payStatus",
										"render" : function(data, type, full,
												meta) {
											return data==null || data == 0?"待支付":
												data==1?"已支付定金":
													data==2?"已支付全款":
														data==3?"确认一期款":
															data==4?"确认二期款":
																data==5?"确认三期款":
																	data==6?"确认四期款":
																		data==7?"确认五期款":
																			data==8?"退一期款":
																				data==9?"退二期款":
																					data==10?"退三期款":
																						data==11?"退四期款":
																							data==12?"退五期款":
																								data==13?"订单完成":"支付状态错误";
										}
									},
								/* 	{
										"data" : "payType",
										"render" : function(data, type, full,
												meta) {
											return data == null ? "未支付":
												data == 0 ? "支付宝":
													data == 1 ? "银联": 
														data == 2 ? "京东":
															data == 3 ? "微信": "支付类型错误";
										}
									}, */
									{
										"data" : "createdTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='查看订单详情' onclick='view(\""
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
		pageGo("${ctx}/productorder/productorder-info?id="+id);
	}
	function search(){
		var payRelativeId = $("#payRelativeId").val();
		var customerPhone = $("#customerPhone").val();
		var recommPhone = $("#recommPhone").val();
		var productRecommPhone = $("#productRecommPhone").val();
		var url = "${ctx}/productorder/productorder-list-data?payRelativeId=" +encodeURI(payRelativeId)
		+ "&customerPhone="+encodeURI(customerPhone)
		+ "&recommPhone="+encodeURI(recommPhone)
		+ "&productRecommPhone="+encodeURI(productRecommPhone);
		oTable.ajax.url(url).load();
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a  href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li class="active">订单列表</li>
	</ul>
	<form class="form-horizontal" id="QForm" onSubmit="return false;">
			<label style="margin-left:25px;display: inline-block;" for="payRelativeId">订单号：</label>
			<input style="width:148px;" type="text" name="payRelativeId" id="payRelativeId" value="${payRelativeId }" />
			<label style="margin-left:25px;display: inline-block;" for="customerPhone">用户帐号：</label>
			<input style="width:148px;" type="text" name="customerPhone" id="customerPhone" value="${customerPhone }" />
			<label style="margin-left:25px;display: inline-block;" for="recommPhone">用户推荐人：</label>
			<input style="width:148px;" type="text" name="recommPhone" id="recommPhone" value="${recommPhone }" />
			<label style="margin-left:25px;display: inline-block;" for="productRecommPhone">项目推荐人：</label>
			<input style="width:148px;" style="margin-right: 60px;" type="text" name="productRecommPhone" id="productRecommPhone" value="${productRecommPhone }" />
			<button class="btn" type="button" onclick="search()" id="query">查询</button>
	</form>
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
					<!-- <th>支付方式</th> -->
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
</html>
