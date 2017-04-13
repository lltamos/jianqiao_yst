<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
								"url" : "${cxt}/balance/balancedoctor-list",
								"type" : "POST",
								"cache":false,
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data.indexOf("&columns"))
											+ data.substring(data.indexOf("start") - 1, data.indexOf("&search") + 7)
											+ data.substring(data.indexOf("&search%5Bvalue%5D") + 18,data.indexOf("&search%5Bregex%5D"))
											;
								}
							},
							"columns" : [
									 {
										"data" : "merSeqId",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									 {
										"data" : "money",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data/100 : "";
										}
									},
									{
										"data" : "feeIncome",
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
										"data" : "createdTime",
										"render" : function(data, type, full,
												meta) {
											return format(data, 'yyyy-MM-dd HH:mm:ss') != null ? format(data, 'yyyy-MM-dd HH:mm:ss') : "";
										}
									},
									{
										"data" : "updateTime",
										"render" : function(data, type, full,
												meta) {
											return format(data, 'yyyy-MM-dd HH:mm:ss') != null ? format(data, 'yyyy-MM-dd HH:mm:ss') : "";
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
		var nameText1 = $("#nameText");
		var nameText2 = $("input[id=nameText]").val();
		var status1 = $("#status").val(); 
		var status2 = $("input[id=status]").val();
		//var url = "${ctx}/balance/balance-list?name="+nameText2+"&status="+status1;
		var url = "${ctx}/balance/balance-list";
		//url=url+"?search_LIKE_name="+($("#nameText").val()).trim()+"&search_EQ_status="+(($("#status").val()).trim()==-1 ? "" : $("#status").val());
		url=url+"?search_LIKE_name="+($("input[id=nameText]").val()).trim()+"&search_EQ_status="+(($("#status").val()).trim()==-1 ? "" : $("#status").val());
		oTable.ajax.url(url).load();
	}
	function cash() {
		pageGo("${cxt}/balance/goto-doctorcashreceive");
	}
	var format = function(time, format){
	    var t = new Date(time);
	    var tf = function(i){return (i < 10 ? '0' : '') + i};
	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
	        switch(a){
	            case 'yyyy':
	                return tf(t.getFullYear());
	                break;
	            case 'MM':
	                return tf(t.getMonth() + 1);
	                break;
	            case 'mm':
	                return tf(t.getMinutes());
	                break;
	            case 'dd':
	                return tf(t.getDate());
	                break;
	            case 'HH':
	                return tf(t.getHours());
	                break;
	            case 'ss':
	                return tf(t.getSeconds());
	                break;
	        }
	    })
	}

</script>
<body>
	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span class="divider">/</span></li>
		<li class="active">提现申请记录</li>
	</ul>
<%-- 	 <div style="font-size: 20px;line-height: 50px;padding-left: 160px;">
		<span>分润总金额：${fenrun/100 }元</span>
		<span style="padding-left: 70px;">可提现金额：${ketiixan/100}元</span>
		<span style="padding-left: 70px;">已提现金额：${yitixian/100}元</span>
	</div>  --%>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
		
		<!-- 名称：<input type="text" style="width: 200px; height: 30px;" id="nameText"/>
		提现状态：<select name="status" id="status" >
		    <option value="-1">全部</option>
		    <option value="1">已申请</option>
		    <option value="2">打款成功</option>
		    <option value="3">打款失败</option>
 		    </select>
		<input type="button" value="查询" onclick="serach();"/> -->
		<!-- <input type="button" value="提现" style="width: 150px;height: 50px; float: right;" onclick="cash();"> -->
			<thead>
				<tr>
				    <th>订单编号</th>
					<th>提现金额</th>
					<th>手续费</th>
					<th>提现状态</th>
					<th>申请时间</th>
					<th>打款时间</th>
				</tr>
			</thead>
			<tbody></tbody>
			
		</table>
	</div>
</body>
<%-- <button class='btn' type='button' title='查看' onclick='pageGo(\"${cxt}/balance/balance-view?id="
													+ data+"&type=1"
													+ "\")'><i class='icon-eye-open'></i></button> --%>
</html>