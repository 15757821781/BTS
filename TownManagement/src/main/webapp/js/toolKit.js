var tk = tk || {};
// success alert
var success_content='<div class="alert alert-success" id="success_content">alert content</div>';
// fail alert
var fail_content='<div class="alert alert-fail" id="success_content">alert content</div>'
tk.getFn = function(fnName, target, arg0, arg1, arg2, arg3) {
	var fn = null;
	var type = typeof(fnName);
	if (target == null) {
		target = {};
	}
	if (type == 'string') {// 事件以字符串方式传递一个方法名
		fn = function(fnName, target, arg0, arg1, arg2, arg3) {
			return function() {
				try {
					if (fnName.indexOf('(') != -1) {
						return eval(fnName);
					} else {
						var items = fnName.split('.');
						var length = items.length;
						if (length == 0) {
							return null;
						}
						var fun = window;
						for (var i = 0; i < length; i++) {
							fun = fun[items[i]];
						}
						return fun.call(target, arg0, arg1, arg2, arg3);
					}
				} catch (ee) {
				}
			};
		};
	} else if (type == 'function') {// 事件以方法方式直接传递
		fn = function(fnName, target, arg0, arg1, arg2, arg3) {
			return function() {
				return fnName.call(target, arg0, arg1, arg2, arg3);
			};
		};
	}
	if (fn == null) {
		return null;
	}
	return fn(fnName, target, arg0, arg1, arg2, arg3);
};

tk.execFn = function(fnName, target, arg0, arg1, arg2, arg3) {
	var fn = tk.getFn(fnName, target, arg0, arg1, arg2, arg3);
	if (fn != null) {
		return fn();
	}
};



//对ajax进行封装
tk.ajax = function(options) {
	options = $.extend(true, {
		type : 'post',
//		form : null,
//		fields : '',
		dataType : 'json',
//		validate : false,
//		responseType : 'json',
		showErrorMsg : true,
//		mask : {
//			target : null,
//			msg : '正在加载数据...'
//		},
		data : {},
		success : function(result,status) {
			// 登出
			if(result.state=="loginout"){
				// 展示登出dialog框
				$("#sys_alert_content").html('<div class="alert alert-fail" id="alert_content">'+result.message+'</div>');
				$("#sys_alert").modal('show');
				// 隐藏后跳转到登录页
				$('#sys_alert').on('hidden.bs.modal',function() {
					window.location.href='/TownManagement';
				});
 	        	return false;
			} else if (result.state=="success"){
				// 展示成功dialog框
				$("#sys_alert_content").html('<div class="alert alert-success" id="alert_content">'+result.message+'</div>');
				$("#sys_alert").modal('show');
			} else if (result.state=="fail"){
				// 展示失败dialog框
				$("#sys_alert_content").html('<div class="alert alert-fial" id="alert_content">'+result.message+'</div>');
				$("#sys_alert").modal('show');
			}
			var realParam = result;
			tk.execFn(options.succ, options.scope, realParam);
		},
		error : function(XMLHttpRequest, status, errorThrown) {
			if (options.showErrorMsg) {
				var msg = null;
				if (XMLHttpRequest.status == 0) {
					msg = '网络已断开或请求超时，请联系管理员！';
				} else if (XMLHttpRequest.status == 500) {
					msg = '系统内部出错，请联系管理员！';
				} else if (XMLHttpRequest.status == 404) {
					msg = '请求无法找到系统资源，请联系管理员！';
				}
				// 系统错误弹框
				if (msg) {
					
				}
			} 
		}
	}, options);
	$.ajax(options);
};