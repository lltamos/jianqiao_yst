function submitForm(_formId,_submitHandler,_needCallBack){
	if(validateForm($(_formId),_needCallBack)){
		var form = $(_formId);
		form.submit();
		var errors=$("input.error,select.error,textarea.error").length;
	 
		if(errors<=0)
			_submitHandler();
	}
}

function validateForm(_formId,_needCallBack) {

	var all = {};

	var rules = {};
	var msg = {};

	$(_formId).find("textarea,select,input[type='text'],input[type='password'],input[type='number'],input[type='date']").each(function() {

		var fieldname = $(this).attr("name");

		var validate2 = $(this).attr("validate") + "";

		if (validate2 != "undefined") {
			var tmpobj = parseValidateRolues(validate2, null);
			if (tmpobj) {
				rules[fieldname] = tmpobj;
			}

			var tmpobj2 = parseValidateMsg(validate2, $(this));
			if (tmpobj2) {
				msg[fieldname] = tmpobj2;
			}
		}

	});

	all["rules"] = rules;
	all["messages"] = msg;
	//all["hasError"]=true;
	if(_needCallBack){
		all["submitHandler"] =function(){
			
		}
	}
	//console.log(all);
	return $(_formId).validate(all);

}

function parseValidateMsg(_validate, _obj) {

	var rulemap = {};

	var list = _validate.split(",");

	var name = $(_obj).attr("msg");

	for (var i = 0; i < list.length; i++) {

		if (list[i] == "required") {
			rulemap["required"] = name + "不能为空";
		}

		if (list[i] == "code") {
			rulemap["code"] = "邮编格式不对";
		}

		if (list[i] == "phone") {
			rulemap["phone"] = name + "格式不对";
		}

		if (list[i] == "chinese") {
			rulemap["chinese"] = "只能输入中文";
		}
		if (list[i] == "English") {
			rulemap["English"] = "只能输入英文";
		}
		if (list[i] == "EnglishNumber") {
			rulemap["English"] = "只能输入英文和数字";
		}
		if (list[i].indexOf("minlength") > -1) {
			rulemap["minlength"] = "长度最小为"
					+ parseInt((list[i] + "").split("_")[1]);
		}
		if (list[i].indexOf("maxlength") > -1) {
			rulemap["maxlength"] = "长度最大为"
					+ parseInt((list[i] + "").split("_")[1]);
		}
		if (list[i].indexOf("digits") > -1) {
			rulemap["digits"] = "只能输入数字";
		}
		if (list[i].indexOf("number") > -1) {
			rulemap["number"] = "只能输入数字";
		}
		if (list[i].indexOf("range") > -1) {
			var arr = (list[i] + "").split("_");
			rulemap["range"] = "字符长度在" + parseInt(arr[1]) + "和"
					+ parseInt(arr[2]) + "之间";
		}

	}

	return rulemap;
}

function parseValidateRolues(_validate, _obj) {

	var rulemap = {};

	var list = _validate.split(",");

	var name = $(_obj).attr("name");

	for (var i = 0; i < list.length; i++) {
		/*是否必填*/
		if (list[i] == "required") {
			rulemap["required"] = true;
		}
		/*验证邮箱*/
		if (list[i] == "code") {
			rulemap["code"] = true;
		}
		/*验证电话号*/
		if (list[i] == "phone") {
			rulemap["phone"] = true;
		}
		/*只能输入数字*/
		if (list[i] == "digits") {
			rulemap["digits"] = true;
		}
		/*只能输入数字*/
		if (list[i] == "number") {
			rulemap["number"] = true;
		}
		/*两个输入框输入的是否相同*/
		if (list[i].indexOf("equalTo")>-1) {
			rulemap["equalTo"] = '#'+(list[i] + "").split("_")[1];
		}
		/*判断邮箱*/
		if (list[i] == "email") {
			rulemap["email"] = true;
		}
		/*只能输入中文*/
		if (list[i] == "chinese") {
			rulemap["chinese"] = true;
		}
		/*只能输入英文*/
		if (list[i] == "English") {
			rulemap["English"] = true;
		}
		/*只能输入英文和数字*/
		if (list[i] == "EnglishNumber") {
			rulemap["English"] = true;
		}
		/*控制字符长度*/
		if (list[i].indexOf("minlength") > -1) {
			rulemap["minlength"] = parseInt((list[i] + "").split("_")[1]);
		}

		/*控制字符长度*/
		if (list[i].indexOf("maxlength") > -1) {
			rulemap["maxlength"] = parseInt((list[i] + "").split("_")[1]);
		}

		/*字符从几到几控制长度*/
		if (list[i].indexOf("range") > -1) {
			rulemap["range"] = [];
			var arr = (list[i] + "").split("_");

			rulemap["range"].push((arr[1]));
			rulemap["range"].push((arr[2]));
		}

	}

	return rulemap;
}