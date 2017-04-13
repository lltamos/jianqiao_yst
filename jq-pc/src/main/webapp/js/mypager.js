/*
  一个分页展示按钮控件,mypager V1.2
创建人：王振川
*/
var mypager = {		  
		pagerid 			: 'mypager', //divID
		mode				: 'click', //模式(link 或者 click)
		pno					: 1, //当前页码
		total				: 1, //总页码
		totalRecords		: 0, //总数据条数
		isShowPrePageBtn	: true, //是否显示上一页按钮
		isShowNextPageBtn	: true, //是否显示下一页按钮
		hrefFormer			: '', //链接前部
		hrefLatter			: '', //链接尾部
		gopageWrapId		: 'mypager_gopage_wrap',
		gopageButtonId		: 'mypager_btn_go',
		gopageTextboxId		: 'mypager_btn_go_input',
		lang				: {
			firstPageText			: '首页',
			firstPageTipText		: '首页',
			lastPageText			: '尾页',
			lastPageTipText			: '尾页',
			prePageText				: '上一页',
			prePageTipText			: '上一页',
			nextPageText			: '下一页',
			nextPageTipText			: '下一页',
			totalPageBeforeText		: '共',
			totalPageAfterText		: '页',
			totalRecordsAfterText	: '条数据',
			gopageBeforeText		: '转到',
			gopageButtonOkText		: '确定',
			gopageAfterText			: '页',
			buttonTipBeforeText		: '第',
			buttonTipAfterText		: '页'
		},
		
		//链接算法（当处于link模式）,参数n为页码
		getLink	: function(n){
			if(n == 1){
				return this.hrefFormer + this.hrefLatter;
			}else{
				return this.hrefFormer + '_' + n + this.hrefLatter;
			}
		},
		
		//页码单击事件处理函数（当处于mode模式）,参数n为页码
		click	: function(n){
			//这里自己实现
			//这里可以用this或者mypager访问mypager对象
			return false;
		},
		//获取href的值（当处于mode模式）,参数n为页码
		getHref	: function(n){
			//默认返回'#'
			return '#';
		},
		
		//跳转框页面跳转
		gopage: function () {		  
			var str_page = $('#'+this.gopageTextboxId).val();			
			var n = parseInt(str_page);
			if(n < 1) n = 1;
			if(n > this.total) n = this.total;
			if(this.mode == 'click'){
				this._clickHandler(n);
			}else{
				window.location = this.getLink(n);
			}
		},
		//不刷新页面直接手动调用选中某一页码
		selectPage : function(n){
			this._config['pno'] = n;
			this.generPageHtml(this._config,true);
		},
		//生成控件代码
		generPageHtml : function(config,enforceInit){
			if(enforceInit || !this.inited){
				this.init(config);
			}
		
			var str_first='',str_prv='',str_next='',str_last='';
		
			if(this.isShowPrePageBtn){
			    if (this.hasPrv) {
                    //可跳转的上一页
			        str_prv = '<a ' + this._getHandlerStr(this.prv) + ' class="ui-paging-prev" title="'
						+ (this.lang.prePageTipText || this.lang.prePageText) + '"><i class="iconfont" title="左三角形"></i>' + this.lang.prePageText + '</a>';
			    } else {
                    //不可跳转的上一页（一般为第一页）
			        str_prv = '<span class="ui-paging-prev"><i class="iconfont" title="左三角形"></i>' + this.lang.prePageText + '</span>';					
				}
			}
			if(this.isShowNextPageBtn){
				if(this.hasNext){
				    str_next = '<a ' + this._getHandlerStr(this.next) + ' class="ui-paging-next"  title="'
						+ (this.lang.nextPageTipText || this.lang.nextPageText) + '">' + this.lang.nextPageText + '<i class="iconfont" title="右三角形"></i></a>';
				} else {
				    str_next = '<span class="ui-paging-next">' + this.lang.nextPageText + '<i class="iconfont" title="右三角形"></i></span>';					
				}
			}
			
			var str = '';
			var dot = '<span>...</span>';

			var total_info='';
		

			gopage_info = '<span class="ui-paging-info">' +
                '共<span class="ui-paging-bold">' + this.total + '</span>页</span>' +
                '<span class="ui-paging-which">到第' +
                '<input type="text" value="'+ this.pno + '" id="' + this.gopageTextboxId + '" >页' +
            '</span>' +            
			'<a class="ui-paging-info ui-paging-goto" href="#" onclick="mypager.gopage()">确定</a>';
		
			
			//分页处理
			if(this.total <= 8){
				for(var i=1;i<=this.total;i++){
				    str += '<a ' + this._getHandlerStr(i) + ' class="ui-paging-item';
				    if(this.pno == i){
				        str += ' ui-paging-current';
				    }
				    str += '" title="'
                               + this.lang.buttonTipBeforeText + i + this.lang.buttonTipAfterText + '">' + i + '</a>';                    			
				}
			}else{
				if(this.pno <= 5){
				    for (var i = 1; i <= 7; i++) {
				        str += '<a ' + this._getHandlerStr(i) + ' class="ui-paging-item';
				        if (this.pno == i) {
				            str += ' ui-paging-current';
				        }
				        str += '" title="' +
                               this.lang.buttonTipBeforeText + i + this.lang.buttonTipAfterText + '">' + i + '</a>';					
					}
					str += dot;
				}else{
				    str += '<a ' + this._getHandlerStr(1) + ' class="ui-paging-item" title="'
						+this.lang.buttonTipBeforeText + '1' + this.lang.buttonTipAfterText+'">1</a>';
				    str += '<a ' + this._getHandlerStr(2) + ' class="ui-paging-item" title="'
						+this.lang.buttonTipBeforeText + '2' + this.lang.buttonTipAfterText +'">2</a>';
					str += dot;
					
					var begin = this.pno - 2;
					var end = this.pno + 2;
					if(end > this.total){
						end = this.total;
						begin = end - 4;
						if(this.pno - begin < 2){
							begin = begin-1;
						}
					}else if(end + 1 == this.total){
						end = this.total;
					}
					for (var i = begin; i <= end; i++) {
					    str += '<a ' + this._getHandlerStr(i) + ' class="ui-paging-item';
					    if (this.pno == i) {
					        str += ' ui-paging-current';
					    }
					    str += '" title="'
                               + this.lang.buttonTipBeforeText + i + this.lang.buttonTipAfterText + '">' + i + '</a>';


					}
					if(end != this.total){
						str += dot;
					}
				}
			}
			
			str = "&nbsp;" + str_first + str_prv + str + str_next + str_last + total_info + gopage_info;
			$("#"+this.pagerid).html(str);
		},
		//分页按钮控件初始化
		init : function(config){
			this.pno = isNaN(config.pno) ? 1 : parseInt(config.pno);
			this.total = isNaN(config.total) ? 1 : parseInt(config.total);
			this.totalRecords = isNaN(config.totalRecords) ? 0 : parseInt(config.totalRecords);
			if(config.pagerid){this.pagerid = config.pagerid;}
			if(config.mode){this.mode = config.mode;}
			if(config.gopageWrapId){this.gopageWrapId = config.gopageWrapId;}
			if(config.gopageButtonId){this.gopageButtonId = config.gopageButtonId;}
			if(config.gopageTextboxId){this.gopageTextboxId = config.gopageTextboxId;}
			
			if(config.isShowPrePageBtn != undefined){this.isShowPrePageBtn=config.isShowPrePageBtn;}
			if(config.isShowNextPageBtn != undefined){this.isShowNextPageBtn=config.isShowNextPageBtn;}
	
			if(config.lang){
				for(var key in config.lang){
					this.lang[key] = config.lang[key];
				}
			}
			this.hrefFormer = config.hrefFormer || '';
			this.hrefLatter = config.hrefLatter || '';
			if(config.getLink && typeof(config.getLink) == 'function'){this.getLink = config.getLink;}
			if(config.click && typeof(config.click) == 'function'){this.click = config.click;}
			if(config.getHref && typeof(config.getHref) == 'function'){this.getHref = config.getHref;}
			if(!this._config){
				this._config = config;
			}
			//validate
			if(this.pno < 1) this.pno = 1;
			this.total = (this.total <= 1) ? 1: this.total;
			if(this.pno > this.total) this.pno = this.total;
			this.prv = (this.pno<=2) ? 1 : (this.pno-1);
			this.next = (this.pno >= this.total-1) ? this.total : (this.pno + 1);
			this.hasPrv = (this.pno > 1);
			this.hasNext = (this.pno < this.total);
			
			this.inited = true;
		},
		_getHandlerStr : function(n){
			if(this.mode == 'click'){
				return 'href="'+this.getHref(n)+'" onclick="return mypager._clickHandler('+n+')"';
			}
			//link模式，也是默认的
			return 'href="'+this.getLink(n)+'"';
		},
		_clickHandler	: function(n){
			var res = false;
			if(this.click && typeof this.click == 'function'){
				this.selectPage(n);
				res = this.click.call(this,n) || false;
			}
			return res;
		}
};