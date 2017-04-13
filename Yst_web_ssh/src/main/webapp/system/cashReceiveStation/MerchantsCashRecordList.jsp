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
								"url" : "ydmvc/cashReceiveStation/cashReceiveStation.do",
								"type" : "POST",
								"cache":false,
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data.indexOf("&columns"))
											+ data.substring(data.indexOf("start") - 1, data.indexOf("&search") + 7)
											+ data.substring(data.indexOf("&search%5Bvalue%5D") + 18,data.indexOf("&search%5Bregex%5D"))
											+"&search_EQ_userId="+${sessionScope.dbCustomer.customer_id};
								}
							},
							"columns" : [
									 {
										"data" : "name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									 {
										"data" : "tel",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "status",//1已申请 2打款成功 3打款失败
										"render" : function(data, type, full,
												meta) {
											return (data == 1 ? "已申请":data == 2 ? "打款成功":data == 3 ? "打款失败": "");
										}
									},
									{
										"data" : "updateTime",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"ydmvc/cashReceiveStation/cashReceiveStationShow.do?id="
													+ data+"&type=2"
													+ "\")'><i class='icon-eye-open'></i></button>";
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
	 //查询
     function serach()
    {
		var url = "ydmvc/cashReceiveStation/cashReceiveStation.do";
		url=url+"?search_LIKE_name="+encodeURI(($("#nameText").val()).trim())+"&search_EQ_status="+(($("#status").val()).trim()==-1 ? "" : $("#status").val());
		oTable.ajax.url(url).load();
	}
	function cash() {
		pageGo("ydmvc/cashReceiveStation/cashReceiveStationFormPage.do");
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li class="active">提现申请记录</li>
	</ul>
	<div style="font-size: 20px;line-height: 50px;padding-left: 160px;">
		<span>分润总金额：${fenrun/100 }元</span>
		<span style="padding-left: 70px;">可提现金额：${ketiixan/100}元</span>
		<span style="padding-left: 70px;">已提现金额：${yitixian/100}元</span>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
		
		名称：<input type="text" style="width: 200px; height: 30px;" id="nameText"/>
		提现状态：<select name="status" id="status" >
		    <option value="-1">全部</option>
		    <option value="1">已申请</option>
		    <option value="2">打款成功</option>
		    <option value="3">打款失败</option>
 		    </select>
		<input type="button" value="查询" onclick="serach();"/>
		<input type="button" value="提现" style="width: 150px;height: 50px; float: right;" onclick="cash();">
			<thead>
				<tr>
				    <th>名称</th>
					<th>电话</th>
					<th>提现状态</th>
					<th>打款时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody></tbody>
			
		</table>
	</div>
</body>
</html>