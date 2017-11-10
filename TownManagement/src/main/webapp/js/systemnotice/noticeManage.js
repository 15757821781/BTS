$(document).ready(function() {
	$('#nocmanagetable').bootstrapTable({
		url : '/TownManagement/noticemanage/queryNocList',
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
		columns : [{
			checkbox : true
		},{
			field : 'noctitle',
			title : '标题',
			align : 'center',
			width : '30%'
		},{
			field : 'createtime',
			title : '发布时间',
			editable : true,
			align : 'center',
			width : '30%'
          
		}, {
            title : '操作',
            width :	'30%',
            field : 'operation',
            align : 'center'
		} ]
	});
//新增弹出框
	$('#noticeSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/SystemNotice/noticeSearch.html",
			cache : false,
			success : function(html) {
				$('#nocinfobody').html(html);
				$('#nocform_search')[0].reset();
				$("#nocinfomodal").modal('show');
			}
		});
	});
})
//表格批量事件
$('#delnotice').click(function(){
	var obj = $('#nocmanagetable').bootstrapTable('getSelections');
	var ids = [];
	$.each(obj,function(i){
		if(obj[i].nocid!=null&&obj[i].nocid!=''){
			ids.push(obj[i].nocid);
		}
	});
	tk.ajax({
		url : "/TownManagement/noticemanage/updateNocState",
        data : {"nocObj":ids},
        succ :function(){
        	$('#nocmanagetable').bootstrapTable('refresh');
        }
	})
});
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
	temp = GetFormSearchData('nocform_search',temp);
	return temp;
}
//展示详情modal
function querydetail(id) {
	$.ajax({
		url : "/TownManagement/pages/SystemNotice/noticeDetail.html",
		cache : false,
		success : function(html) {
			$("#nocinfobody").html(html);
			$("#nocHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/noticemanage/queryNocDetail",
				data : {"nocid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					data = data.data[0];
					$("#noctitle").text(data.noctitle);
					$("#createtime").text(data.createtime);
					$("#noctext").html(data.noctext);
					$("#nocinfomodal").modal('show');
					$("#noctute_submit").hide();
					$("#noctute_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(id){
	$.ajax({
		url : "/TownManagement/pages/SystemNotice/noticeEntry.html",
		cache : false,
		success : function(html) {
			$("#nocinfobody").html(html);
			$("#nocHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/noticemanage/queryNocDetail",
				data : {"nocid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					$("#noc_editor").html(data.data[0].noctext);
					fillForm('#nocform',data);
					$("#nocinfomodal").modal('show');
					$("#notice_submit").hide();
					$("#notice_update").show();
				}
			});
		}
	});
}