var tk = tk || {};
//对ajax进行封装
tk.ajax = function(options) {
	options = $.extend(true, {
		type : 'post',
//		form : null,
//		fields : '',
		dataType : '',
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
				$("#sys_alert").modal('show');
				// 隐藏后跳转到登录页
				$('#sys_alert').on('hidden.bs.modal',function() {
					window.location.href='/TownManagement';
				})
 	        	return false;
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
				// 系统错误弹框
				if (msg) {
					
				}
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