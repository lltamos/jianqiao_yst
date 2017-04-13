function check(_form) {
		
	var flag=true;
		$(_form).find("select,input").each(function(){
			var validate=$(this).attr("validate")+"";
			var tag=$(this).attr("tag");
			if(validate.indexOf("required")>-1){
				if ($(this).val() == "") {
					$(this).tips({
						side : 2,
						msg : tag+'不能为空',
						bg : '#AE81FF',
						time : 2
					});
					$(this).focus();
					flag=false;
				}else{
					$(this).val(jQuery.trim($(this).val()));
				}
			}
		});
		
		return flag;
}

function getLocalTime(nS) { 
	return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' '); 
} 

/** 
 * 时间戳格式化函数 
 * @param  {string} format    格式 
 * @param  {int}    timestamp 要格式化的时间 默认为当前时间 
 * @return {string}           格式化的时间字符串 
 */
function date(format, timestamp){  
    var jsdate=((timestamp) ? new Date(timestamp) : new Date()); 
     
    return  jsdate.Format(format)
}

/**
 * 扩展date属性
 */
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//获得本周的开始日期     
function getWeekStartDate(_format,_status,_start) {  
    //获取当前时间  
    var currentDate=this.getCurrentDate(); 
    //返回date是一周中的某一天  
    var week=currentDate.getDay(); 
    
    //一天的毫秒数  
    var millisecond=1000*60*60*24; 
    //减去的天数  
    var minusDay=week!=0?week-1:6; 
    //alert(minusDay);  
    //本周 周一  
    var monday=new Date(currentDate.getTime()-(minusDay*millisecond)); 
    
    var _startTime=new Date().getTime();
    if(_start!="")
    	_startTime= new Date(_start).getTime();
    //已截止 本周周一改为下周周一
    /*if(_status==3){
    	monday=new Date(monday.getTime()+(7*millisecond)); 
    }else{
    	var _mondayTime=new Date(new Date(monday.getTime()+(7*millisecond)).Format("yyyy-MM-dd 00:00:00")).getTime(); 
    	if(_startTime >=_mondayTime ){//编辑下周的时间
    		monday=new Date(monday.getTime()+(7*millisecond)); 
    	}
    }*/
  
    return monday.Format(_format);      
}      
    
var getCurrentDate=function(){
  return new Date();
}

//获得本周的结束日期     
function getWeekEndDate(_format,_status,_end) {      
	//获取当前时间  
    var currentDate=getCurrentDate(); 
    //返回date是一周中的某一天  
    var week=currentDate.getDay(); 
    
    //一天的毫秒数  
    var millisecond=1000*60*60*24; 
    //减去的天数  
    var minusDay=week!=0?week-1:6; 
    //本周 周一  
    var monday=new Date(currentDate.getTime()-(minusDay*millisecond));
	//本周 周日  
    var sunday=new Date(monday.getTime()+(7*millisecond)); 
    var _endTime=0;
    	
    if(_end!="")
       _endTime=new Date(_end).getTime();
    else 
       _endTime=0;
    
	  /*  //已截止 本周周日改为下周周日
	    if(_status==3){
	    	sunday=new Date(sunday.getTime()+(7*millisecond)); 
	    }else{
	    	var _mondayTime=new Date(new Date(sunday.getTime()).Format("yyyy-MM-dd 00:00:00")).getTime(); 
	    	var _sundayTime=new Date(new Date(sunday.getTime()+(7*millisecond)).Format("yyyy/MM/dd 00:00:00")).getTime(); 
	    	if(_endTime<_sundayTime&&_endTime>_mondayTime){//编辑下周的时间
	    		sunday=new Date(sunday.getTime()+(7*millisecond)); 
	    	}
	    }*/
    
    return sunday.Format(_format);     
}    
 

function  deleteInfo(_url,_backurl,_isdeleted){
	$("#deleteModal").modal({
		backdrop : "static"
	});
	if(_isdeleted){
		$("#confirmText").empty().append("您确认要启用吗？");
	}else
	{
		$("#confirmText").empty().append("您确认要禁用吗？");
	}
	$("#deleteModal").modal('show');
	 
	var $confirmDeleteDialogBut = $("#confirmDeleteDialogBut");
	//判断是否绑定了click事件
	var objEvt = $._data($confirmDeleteDialogBut[0], "events");
	if (objEvt && objEvt["click"]) {
		$confirmDeleteDialogBut.trigger("click");
	}
	else {
		$confirmDeleteDialogBut.bind('click', function() {
			$.ajax({
				url:_url,
				dataType:"json",
				success:function(result){
					if(result.code=="200")
					{
						$(".modal-backdrop").remove();
						alert("修改成功!");
						pageGo(_backurl);
					}else{
						alert(result.msg);
						location.reload();
					}
				}
			});
		});
	}
	
	
}