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
							"scrollX": true,
							"scrollCollapse": true,
        					"jQueryUI": true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "${baseurl}/ydmvc/main/after/findPatientManagerPage.do",
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
										"data" : "patientPhone",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									} ,
									
									{
										"data" : "patientName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "sex",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "address",//家庭住址
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "relationId",
										"render" : function(data, type, full,
												meta) {
											return data == 1 ? "家庭成员" : data == 2 ? "亲戚" : 
											data == 3 ? "朋友" : data == 4 ? "其它" : 
											data == 5 ? "本人" : "";
										}
									},
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
											return "<button class='btn' type='button' title='用药记录' onclick='pageGo(\"${baseurl}/ydmvc/main/after/toRelativeMedicineRecordPage.do?patient_id="
											+ data + "\")'><i class='icon-eye-open'></i></button>";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='病例记录' onclick='pageGo(\"${baseurl}/ydmvc/main/after/toIllnessRecordPage.do?patient_id="
											+ data + "\")'><i class='icon-eye-open'></i></button>";
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
	function patientsView(url){
		pageGo(url);
	}
	function queryInfo() {
		var startTime=$("#start").val();
		var url = "ProductOrder!getSupplierOrderList.action?" + getQueryParamInfo();
		oTable1.ajax.url(url).load();
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">患者列表</li>
	</ul>
	<div class="control-group" style="padding-left:20px;">
		<div class="controls">
			订单号：
			<input type="text" style="width: 200px; margin-right:20px;" id="orderId"/>
			用户账户：
			<input type="text" style="width: 200px; margin-right:20px;" id="customerName"/>
			商品名称：
			<input type="text" style="width: 200px; margin-right:20px;" id="productName"/>
		    <input type="button" class="btn"  value="查询" onclick="queryInfo();" style="width: 56px;height: 31px; margin-left: 30px; margin-top:-10px;"/>
		</div>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
				    <th>用户账号</th>
					<th>患者姓名</th>
					<th>性别</th>
					<th>地址</th>
					<th>病患关系</th>
					<th>时间</th>
					<th>用药记录</th>
					<th>病例记录</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
</html>
