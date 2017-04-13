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
								"url" : "${ctx}/sharemoney/doctor-list-data",
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
										"data" : "orderNumber",
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
											return data != null ? data/100+"元" : "";
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
										"data" : "fee",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data/100+"元" : "";
										}
									},
									{
										"data" : "orderStatues",
										"render" : function(data, type, full,
												meta) {
											return data==null ?"暂无":
														data==1?"确认一期款":
															data==2?"确认二期款":
																data==3?"确认三期款":
																	data==4?"确认四期款":
																		data==5?"确认五期款":"暂无";
										}
									},
									{
										"data" : "shareMoney",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data/100+"元" : "";
										}
									},
									{
										"data" : "createdTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									}
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
	function cash() {
		pageGo("${cxt}/balance/goto-doctorcashreceive");
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx}/user/user-index">主页</a></li>
		<li class="active">分润记录列表</li>
	</ul>
	<form class="form-horizontal" id="QFORM" name="QFORM" onsubmit="return false;">
			<label style="margin-left:25px;display: inline-block;" for="search_EQ_payRelativeId">订单号：</label>
			<input type="text" name="search_EQ_payRelativeId" id="search_EQ_payRelativeId" value="${search_EQ_payRelativeId }" />
			<label style="margin-left:25px;display: inline-block;" for="search_EQ_customerPhone">用户帐号：</label>
			<input type="text" name="search_EQ_customerPhone" id="search_EQ_customerPhone" value="${search_EQ_customerPhone }" />
			<%-- <label style="margin-left:25px;display: inline-block;" for="search_EQ_orderStatues">订单状态：</label>
			<select name="search_EQ_orderStatues" id="search_EQ_orderStatues">
				<option value="" >全部</option>
				<option value="1" <c:if test="${search_EQ_orderStatus==1 }">selected</c:if>>确认一期款</option>
				<option value="2" <c:if test="${search_EQ_orderStatus==2 }">selected</c:if>>确认二期款</option>
				<option value="3" <c:if test="${search_EQ_orderStatus==3 }">selected</c:if>>确认三期款</option>
				<option value="4" <c:if test="${search_EQ_orderStatus==4 }">selected</c:if>>确认四期款</option>
				<option value="5" <c:if test="${search_EQ_orderStatus==5 }">selected</c:if>>确认五期款</option>
			</select> --%>
			<button class="btn" type="button" onclick="oTable.ajax.url('${ctx}/sharemoney/doctor-list-data?'+$('#QFORM').serialize()).load();">查询</button>
	</form>
	<div class="btn-toolbar">
		<label style="margin-left:25px;display: inline-block;">已分润总金额：<fmt:formatNumber pattern="0.00" value="${balance.depositFee/100+balance.haveWithdrawalFee/100 }"/>元</label>
		<label style="margin-left:25px;display: inline-block;">已提现总金额：<fmt:formatNumber pattern="0.00" value="${balance.haveWithdrawalFee/100 }"/>元</label>
		<label style="margin-left:25px;display: inline-block;">可提现总金额：<fmt:formatNumber pattern="0.00" value="${balance.depositFee/100 }"/>元</label>
		<%-- <button style="margin-left:25px;" class="btn" type="button" onclick="cash();>提现</button> --%>
		<input type="button" value="提现" style="width: 150px;height: 50px; float: right;" onclick="cash();">
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>订单号</th>
					<th>用户帐号</th>
					<th>项目名称</th>
					<th>项目总价</th>
					<th>项目分期</th>
					<th>本期金额</th>
					<th>订单状态</th>
					<th>分润金额</th>
					<th>分润时间</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
</html>
