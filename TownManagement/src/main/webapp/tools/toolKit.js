var tk = tk || {};
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
var fillForm = function(form,data) {
	values = $(form).serializeArray();
	var dataStr=data.data[0];
	$.each(dataStr,function(i){
		var key = i;
		var value = dataStr[i];
		$.each(values,function(i){
			var id=values[i].name;
		    if(id==key){
		    	if($("#"+key).hasClass("selectpicker")){
		    		var arr=value.split(",");
					$('#'+key).selectpicker();
					$('#'+key).selectpicker('val', arr);
					if(key=="sys_province"){
						$("#sys_city option").remove();
						$("#sys_town option").remove();
						proOnChange("sys_province", "sys_city", "sys_town");
					}else if(key=="sys_city"){
						$("#sys_town option").remove();
						cityOnChange("sys_province", "sys_city", "sys_town");
					}
		    	}else{
		    		$('#'+key).val(value);
		    	}
		    	return false;
		    }else if($("#"+key).attr("multiple")=="multiple"&&$("#"+key).hasClass("selectpicker")){
	    		var arr=value.split(",");
				$('#'+key).selectpicker();
				$('#'+key).selectpicker('val', arr);
				return false;
		    }else if($("#"+key).attr("disabled")=="disabled"){
		    	$('#'+key).val(value);
		    }
		});  
	});
}
// 表单提交
/**
 * form,提交的表单
 * url,提交路径
 * target,完成后刷新的页面
 */
var formSubmit = function(form,url,target){
	$(form).data('bootstrapValidator').validate();
	if (!$(form).data('bootstrapValidator').isValid()) {
		return;
	}
	/*ie11以下不支持，formdata的方法  */
	var formData = new FormData($(form)[0]);
	tk.ajax({
		type : "post",
		url : '/TownManagement/' + url,
		data : formData,
		dataType : 'JSON',
		processData : false,
		contentType : false,
		succ : function(data, status) {
			$('#sys_alert').on('hidden.bs.modal', function() {
				$(".modal-backdrop").remove();
				if (target != null && target != "") {
					$.ajax({
						url : '/TownManagement/pages/' + target,
						cache : false,
						success : function(html) {
							$("#page-wrapper").html(html);
						}
					});
				}
			});
		}
	});
}
// 动态加载下拉框
var selectCreate = function(id,url,data){
	tk.ajax({
		url : "/TownManagement/"+url,
		data : data?data:"",
		cache : true,
		succ : function(result,state){
			 $("#" + id).find('option').remove();
			 var data=result.data;
			 if (data != null) {
				 $("#" + id).append("<option></option>");
				 $.each(data,function(i){
					 $("#"+id).append("<option value="+data[i].value+">"+data[i].name+"</option>");
				 })
			 }
			 $("#" + id).selectpicker('refresh');
		}
	});
}
// 创建区域下拉框
var createAreaSelect = function(province, city, town) {
	var html = "<option></option>";
	$("#"+city).append(html);
	$("#"+town).append(html);
	$.each(areadata, function(idx, item) {
		if (parseInt(item.level) == 0) {
			html += "<option value='" + item.code + "' exid='" + item.code
					+ "'>" + item.names + "</option>";
		}
	});
	$("#"+province).append(html);
	
	$("#"+province).change(function() {
		$("#"+city+" option").remove();
		$("#"+town+" option").remove();
		$("#"+town).selectpicker('refresh');
		if ($(this).val() == ""){
			return;
		}
		proOnChange(province, city, town);
	});

	$("#"+city).change(function() {
		$("#"+town+" option").remove();
		$("#"+town).selectpicker('refresh');
		if ($(this).val() == ""){
			return;
		}
		cityOnChange(province, city, town);
	});
	$("#" + province).selectpicker('refresh');
	$("#" + city).selectpicker('refresh');
	$("#" + town).selectpicker('refresh');
}
// 选择省份后触发事件
var proOnChange = function(province, city, town){
	var code = $('#'+province).find("option:selected").attr("exid");
	var html = "<option></option>";
	if(code!=undefined){
		code = code.substring(0, 2);
		$("#"+town).append(html);
		$.each(areadata, function(idx, item) {
			if (parseInt(item.level) == 1 && code == item.code.substring(0, 2)) {
				html += "<option value='" + item.code + "' exid='"
						+ item.code + "'>" + item.names + "</option>";
			}
		});
	}
	$("#"+city).append(html);
	$("#"+city).selectpicker('refresh');
}
// 选择城市后触发事件
var cityOnChange = function(province, city, town){
	$("#"+town+" option").remove();
	var code = $('#'+city).find("option:selected").attr("exid");
	var html = "<option></option>";
	if(code!=undefined){
		code = code.substring(0, 4);
		$.each(areadata, function(idx, item) {
			if (parseInt(item.level) == 2 && code == item.code.substring(0, 4)) {
				html += "<option value='" + item.code + "' exid='"
						+ item.code + "'>" + item.names + "</option>";
			}
		});
	}
	$("#"+town).append(html);
	$("#"+town).selectpicker('refresh');
}

//初始化fileinput控件
function initFileInput(ctrlName,msg,count) {
	var control = $('#' + ctrlName);
	control.fileinput({
		uploadAsync:false,
		browseLabel : msg,
		dropZoneEnabled: false,//是否显示拖拽区域
		language : 'zh', //设置语言
		allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ],//接收的文件后缀
		showUpload : false, //是否显示上传按钮
		showRemove: false,//是否显示删除按钮  
		showCaption: true,//是否显示输入框
		maxFileCount: count,
		validateInitialCount:true,
		overwriteInitial:false,
		previewSettings:{
			image:{width: "92%", height: "100%"}
		},
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
	});
}
//详情初始化
function initDeatilFileInput(ctrlName,param) {
	var local = window.location;
	var contextPath = local.pathname.split("/")[1];
	var basePath = local.protocol + "//" + local.host;
    // 预览图片json数据组  
    var preList = new Array();
    var preConfigList = new Array();
    var reData = param.value.split(",");
    for ( var i = 0; i < reData.length; i++) {
		if (reData[i] != "") {
			var name = reData[i].substring(reData[i].lastIndexOf("/"));
			name = name.replace("/", "")
			preList[i] = "<img src="+ basePath + reData[i]
					+ " class='file-preview-image' "
					+ "style='width: 92%;height:90%;' "
					+ "alt=" + name + " title=" + name + ">";
			var tjson = {
				// 展示的文件名
				url:'/TownManagement/conditionmanage/deletePic',
				caption : name,
		        key : reData[i],
		        showDelete : param.showdelete
			};
			preConfigList[i] = tjson;  
		}
    }
	var control = $('#' + ctrlName);
	control.fileinput('refresh',{
		initialPreview: preList,
        initialPreviewConfig: preConfigList,
        deleteExtraData : function() {
            return {
            value: $("#"+param.field).val(),
            tbname : param.tbname,
        	field : param.field,
        	id : $("#"+param.id).val(),
        	primary : param.id}
        }
	});
	control.on('filedeleted', function(event, data, result) {
		var value = JSON.parse(result.responseText);
		$("#"+param.field).val(value.data);
	});
}
