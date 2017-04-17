var tk = tk || {};
//对ajax进行封装
tk.ajax = function(options) {
	options = $.extend(true, {
		type : 'post',
//		form : null,
//		fields : '',
		dataType : '',
//		validate : false,
		responseType : 'json',
//		timeout : 120000,
		showErrorMsg : true,
		mask : {
			target : null,
			msg : '正在加载数据...'
		},
		data : {},
		success : function(result) {
			if (options.isBlocked) {
				$(options.mask.target).unblock();
			}
			if (result == 'OverdueSession') {
				top.window.location = '/common/jsp/error.jsp';
				return;
			}
			var realParam = result;
			if (result != '' && options.responseType == 'json') {
				try {
					realParam = result.toJson();
				} catch (e) {
				}
			}
			if (realParam.exception) {
				if (options.exception) {
					tk.execFn(options.exception, options.scope, realParam);
				} else {
					tk.showWindow({
						url : '/power/common/jsp/exception.jsp',
						title : '系统异常',
						width : 600,
						height : 500,
						response : realParam
					});
				}
			} else {
				tk.execFn(options.succ, options.scope, realParam);
			}
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
				if (msg) {
					if (options.failure) {
						msgOpt.onclose = function() {
							tk.execFn(options.failure, options.scope, status);
						};
					}
					$.err(msg);
				}
			} else if (options.failure) {
				tk.execFn(options.failure, options.scope, status);
			}
			if (options.isBlocked) {
				$(options.mask.target).unblock();
			}
		}
	}, options);
//	if (options.form) {
//		var form = $(options.form);
//		if (options.validate && !form.validateForm()) {
//			return;
//		}
//		var formJson = form.form2json();
//		$.extend(options.data, formJson);
//	}
//	if (options.fields) {
//		if (options.validate == true && !tk.validateFields(options.fields)) {
//			return;
//		}
//		var formJson = tk.fields2json(options.fields);
//		$.extend(options.data, formJson);
//	}
//	for (var key in options.data) {
//		if (typeof(options.data[key]) == 'object') {
//			options.data[key] = null;
//		}
//	}
//	if (options.mask && options.mask.target) {
//
//		$(options.mask.target).mask({
//			message : options.mask.msg,
//			timeout : null
//		});
//		options.isBlocked = true;
//	}
	$.ajax(options);
};