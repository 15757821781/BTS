$(document).ready(function() {
	$('#resManage').bootstrapTable({
		url : '/TownManagement/resitemmanage/queryresinfo',
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
		height : 520, //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
		//cardView : false, //是否显示详细视图
		//detailView : true, //是否显示父子表
		columns : [ {
			field : 'resnumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'resitemname',
			title : '项目名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			field : 'resarea',
			title : '所属地区',
			align : 'center',
			width : '20%'
		}, {
			field : 'rescompetentunit',
			title : '主管单位',
			align : 'center',
			width : '20%'
		}, {
            title : '操作',
            field : 'resid',
            width :	'20%',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.resid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.resid+')">修改</a>'
            	return query+"&nbsp"+update;
            }
		} ]
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
function querydetail(resid) {
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/resitem.html",
		cache : false,
		async: false,
		success : function(html) {
			$("#resbody").html(html);
			tk.ajax({
				url : "/TownManagement/resitemmanage/queryresitemdetail",
				async: false,
				data : {"resid":resid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#resitem',data);
				}
			});
			$("#resmodal").modal('show');
		}
	});
}
//展示修改界面
function updateinfo(resid){
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/resitem.html",
		cache : false,
		async: false,
		success : function(html) {
			$("#resbody").html(html);
			tk.ajax({
				url : "/TownManagement/resitemmanage/queryresitemdetail",
				async: false,
				data : {"resid":resid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#resitem',data);
				}
			});
			$("#resmodal").modal('show');
			$("#resitem_update").show();
		}
	});
}