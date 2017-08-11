var PageForRole;
$(document).ready(function() {
//	//初始化下拉框
//	$('.selectpicker').selectpicker({
//		noneSelectedText : "请选择"
//	});
	$('#rolemanagetable').bootstrapTable({
		url : '/TownManagement/systemmanage/queryRoleList',
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
			field : 'name',
			title : '角色名称',
			align : 'center'
		}, {
            title : '操作',
            field : 'userid',
            align : 'center',
            formatter:function(value,row,index){
//            	var query = '<a href="javascript:void(0)" onclick="queryDetail('+row.userid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateInfo('+row.id+')">修改</a>';
            	return update;
            }
		} ]
	});
	// 添加用户表单验证
	validatorRoleForm();
	//新增弹出框
	$('#import_roleinfo').click(function() {
		PageForRole=createRoleTree();
		$('#roleform')[0].reset();
		$('#roleform').bootstrapValidator('resetForm', false);
		$("#importrole_modal").modal('show');
		$("#role_submit").show();
		$("#role_update").hide();
	});
	//表单提交
	$('#role_submit').click(function() {
		$('#roleform').data('bootstrapValidator').validate();
		if (!$('#roleform').data('bootstrapValidator').isValid()) {
			return;
		}
		var nodes = PageForRole.getCheckedNodes(true);
		// 若选中节点,取出节点中的id和name
		var nodeList = [];
		if(nodes.length>0){
			$.each(nodes,function(i){
				var id = nodes[i].id;
				var name = nodes[i].name;
				nodeList.push({
					'id':id,
					'name':name
				})
			})
		}
		var data={
				'nodes':nodeList,
				'name':$('#name').val()
		}
		tk.ajax({
			type : "post",
			url : '/TownManagement/systemmanage/insertRole',
			cache : false,
			data : data,
			dataType : 'JSON',
			succ : function(data, status) {
				$('#sys_alert').on('hidden.bs.modal', function() {
					$(".modal-backdrop").remove();
					$("#importrole_modal").modal('hide');
					$('#rolemanagetable').bootstrapTable('refresh');
				});
			}
		});
	});
	// 更新操作
	$('#role_update').click(function() {
		$('#roleform').data('bootstrapValidator').validate();
		if (!$('#roleform').data('bootstrapValidator').isValid()) {
			return;
		}
		var nodes = PageForRole.getCheckedNodes(true);
		// 若选中节点,取出节点中的id和name
		var nodeList = [];
		if(nodes.length>0){
			$.each(nodes,function(i){
				var id = nodes[i].id;
				var name = nodes[i].name;
				nodeList.push({
					'id':id,
					'name':name
				})
			})
		}
		var data={
				'nodes':nodeList,
				'id':$('#id').val(),
				'name':$('#name').val()
		}
		tk.ajax({
			type : "post",
			url : '/TownManagement/systemmanage/updateRoleInfo',
			cache : false,
			data : data,
			dataType : 'JSON',
			succ : function(data, status) {
				$('#sys_alert').on('hidden.bs.modal', function() {
					$(".modal-backdrop").remove();
					$("#importrole_modal").modal('hide');
					$('#rolemanagetable').bootstrapTable('refresh');
				});
			}
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

//展示修改界面
function updateInfo(id){
	$('#roleform').bootstrapValidator('resetForm', false);
	tk.ajax({
		url : "/TownManagement/systemmanage/queryRoleDetail",
		data : {"id":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#roleform',data);
			createRoleTree(id);
			$("#importrole_modal").modal('show');
			$("#role_submit").hide();
			$("#role_update").show();
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
function createRoleTree(otherdata){
	var setting = {
			data:{
				simpleData:{
					enable:true,
					idKey:'id',
					pIdKey:'parentid',
					rootPId:null
				}
			},
			check:{
				enable: true,
			}	
	};
	$.ajax({
		url : "/TownManagement/systemmanage/queryMenusPage",
		data : {"roleid":otherdata},
		dataType : 'JSON',
		type : 'get',
		success : function(data, status) {
			// 页面树
			PageForRole = $.fn.zTree.init($("#treeDemo"),setting,data);
		}
	})
	
}