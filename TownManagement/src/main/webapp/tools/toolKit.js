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

// 展示成功modal
var showSucModal = function(id, message) {
	var ID=id?id:"sys_alert";
	$("#"+ID).html('<div class="modal-dialog" style="width: 300px;text-align: center;"><div class="alert alert-success">'+message+'</div></div>');
	$("#"+ID).modal('show');
}

// 展示失败modal
var showFailModal = function(id, message) {
	var ID=id?id:"sys_alert";
	$("#"+ID).html('<div class="modal-dialog" style="width: 300px;text-align: center;"><div class="alert alert-danger ">'+message+'</div></div>');
	$("#"+ID).modal('show');
}

// 展示警告modal
var showWarModal = function(id, message) {
	var ID=id?id:"sys_alert";
	$("#"+ID).html('<div class="modal-dialog" style="width: 300px;text-align: center;"><div class="alert alert-waring">'+message+'</div></div>');
	$("#"+ID).modal('show');
}

// 展示提示modal
var showInfoModal = function(id,message){
	var ID=id?id:"sys_alert";
	$("#"+ID).html('<div class="modal-dialog" style="width: 300px;text-align: center;"><div class="alert alert-info">'+message+'</div></div>');
	$("#"+ID).modal('show');
}

// 根据状态处理
var dealWidthState=function(result){
	// 登出,session过期
	if(result.state=="loginout"){
		showInfoModal("sys_alert",result.message);
		// 隐藏后跳转到登录页
		$('#sys_alert').on('hidden.bs.modal',function() {
			window.location.href='/TownManagement';
		});
     	return false;
     	// 登录成功
	} else if (result.state=="loginsuccess"){
		showSucModal("sys_alert",result.message);
		// 登录失败
	} else if (result.state=="loginfail"){
		showFailModal("sys_alert",result.message);
		return false;
		// 新增成功
	} else if (result.state=="insertsuccess"){
		showSucModal("sys_alert",result.message);
		// 删除成功
	} else if (result.state=="deletesuccess"){
		showSucModal("sys_alert",result.message);
		// 更新成功
	} else if (result.state=="updatesuccess"){
		showSucModal("sys_alert",result.message);
		// 新增失败
	} else if (result.state=="insertfail"){
		showFailModal("sys_alert",result.message);
		return false;
		// 删除失败
	} else if (result.state=="deletefail"){
		showFailModal("sys_alert",result.message);
		return false;
		// 更新失败
	} else if (result.state=="updatefail"){
		showFailModal("sys_alert",result.message);
		return false;
		// 操作失败
	} else if (result.state=="fail"){
		showFailModal("sys_alert",result.message);
		return false;
	}
	setTimeout(function() {
		$("#sys_alert").modal('hide');
	}, 1500);
}

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
			dealWidthState(result);
			var realParam = result;
			tk.execFn(options.succ, options.scope, realParam);
		},
		error : function(XMLHttpRequest, status, errorThrown) {
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
				showFailModal("sys_alert",msg);
				$('#sys_alert').on('hidden.bs.modal', function() {
					window.location.href='/TownManagement';
				});
			}
		}
	}, options);
	$.ajax(options);
};

//表单填充
var fillForm = function(formid,data) {
	values = $('#'+formid).serializeArray();
	var dataStr=data[0];
	$.each(dataStr,function(i){
		var key = i;
		var value = dataStr[i];
		$.each(values,function(i){
			var id=values[i].name;
		    if(id==key){
		    	$('#'+id).val(value);
		    // 如果是复选框
		    }else if($("#"+key).attr("multiple")=="multiple"){
	    		var arr=value.split(",");
				$('#'+key).selectpicker();
				$('#'+key).selectpicker('val', arr);
	    	}
		});  
	});
}