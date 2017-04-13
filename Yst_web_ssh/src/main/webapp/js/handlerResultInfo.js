//统一封装方法，作用：提示信息
function message_alert(data){
	//从返回的json数据中获取结果信息
	var data_v = data.resultInfo;
	//提交结果类型
	var type= data_v.type;
	//结果提示信息
	var message = data_v.message;
	if(type == 0){
		//如果type等于0表示失败，调用 jquery easyui的信息提示组件
		swal(message, "", "error");
	}else if(type == 1){
		swal(message, "", "success");
	}else if(type == 2){
		swal(message, "", "error");
	}else if(type == 3){
		swal(message, "", "error");
	}
}