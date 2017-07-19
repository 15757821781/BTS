$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	$('#rolemanagetable').bootstrapTable({
		url : '/TownManagement/systemmanage/queryUserList',
		method : 'get', //请求方式（*）
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式
		queryParamsType : "undefined",
		queryParams : queryParams,//传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : true,
		showToggle : true,
		showRefresh : true,//显示刷新按钮
		strictSearch : true,//设置为 true启用 全匹配搜索，否则为模糊搜索
		clickToSelect : true, //是否启用点击选中行
		height : 650, //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
		//cardView : false, //是否显示详细视图
		//detailView : true, //是否显示父子表
		columns : [ {
			field : 'number',
			title : '用户编号',
			align : 'center',
		}, {
			field : 'name',
			title : '用户名称',
			align : 'center',
		}, {
			field : 'account',
			title : '用户账号',
			align : 'center',
		}, {
			field : 'role',
			title : '用户角色',
			align : 'center',
		}, {
			field : 'userdata',
			title : '数据权限',
			align : 'center',
		}, {
            title : '操作',
            field : 'userid',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="queryDetail('+row.userid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateInfo('+row.userid+')">修改</a>';
            	return query+"&nbsp"+update;
            }
		} ]
	});
	// 页面树
	var PageForRole = $.fn.zTree.init($("#treeDemo"), {
		async:{
			enable: true,
			url: "http://host/getNode.php",
			autoParam: ["id"]
		},
		check:{
			enable: true,
		}
	}, {});
	
	// 添加用户表单验证
	validatorRoleForm();
	//新增弹出框
	$('#import_roleinfo').click(function() {
		$("#rolefieldset").removeAttr("disabled");
		$('#roleform')[0].reset();
		$('#roleform').bootstrapValidator('resetForm', false);
		$("#importrole_modal").modal('show');
	});
	//表单提交
	$('#user_submit').click(function() {
		formSubmit('#roleform','systemmanage/insertUserInfo','',function(){
			$("#importrole_modal").modal('hide');
			$('#usermanagetable').bootstrapTable('refresh');
		});
	});
	$('#user_update').click(function() {
		formSubmit('#roleform','systemmanage/updateUserInfo','',function(){
			$("#importrole_modal").modal('hide');
			$('#usermanagetable').bootstrapTable('refresh');
		});
	});
})
//查询方法
function queryParams(params){
	if (params.searchText == undefined) {
		params.searchText = "";
	}
	var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.pageSize, //页面大小
		offset : params.offset, //页码
		maxrows : params.limit,
		pageindex : params.pageNumber,
		search : encodeURI(params.searchText)
	};
	return temp;
}
//展示详情modal
function queryDetail(id) {
	$("#userfieldset").removeAttr("disabled");
	$('#userform').bootstrapValidator('resetForm', false);
	tk.ajax({
		url : "/TownManagement/systemmanage/queryUserDetail",
		data : {"userid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#userform',data);
			$("#importuser_modal").modal('show');
			$('#userfieldset').attr("disabled","disabled");
			$("#user_submit").hide();
			$("#user_update").hide();
		}
	});
}
//展示修改界面
function updateInfo(id){
	$("#userfieldset").removeAttr("disabled");
	$('#userform').bootstrapValidator('resetForm', false);
	tk.ajax({
		url : "/TownManagement/systemmanage/queryUserDetail",
		data : {"userid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#userform',data);
			$("#importuser_modal").modal('show');
			$('#account').attr("disabled","disabled");
			$("#user_submit").hide();
			$("#user_update").show();
		}
	});
}
function validatorRoleForm(){
	$('#roleform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
	//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : {
						message : '角色名称不能为空'
					}
				}
			}
		}
	});
	$('#roleform').bootstrapValidator('resetForm', false);
}