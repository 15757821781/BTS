$(document).ready(function() {
	$('#citymanage').bootstrapTable({
		url : '/TownManagement/citymanage/queryCityList',
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
		columns : [  {
			checkbox : true,
			width : '2%'
		},{
			field : 'countryyear',
			title : '年度',
			editable : true,
			align : 'center',
			width : '20%'
		},{
			field : 'countryname',
			title : '县市名称',
			editable : true,
			align : 'center',
			width : '20%'
		},{
			title : '归属地',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	return row.countryprovince+"/"+row.countrycity;
            }
		},{
            title : '操作',
            width :	'20%',
            field : 'operation',
            align : 'center',
		} ]
	});
	//新增弹出框
	$('#citycaseSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/ProjectPlan/citycaseSearch.html",
			cache : false,
			success : function(html) {
				$('#citycasebody').html(html);
				$('#form_search')[0].reset();
				$("#citycasemodal").modal('show');
			}
		});
	});
	//表格事件
	$('#delcitycase').click(function(){
		var obj = $('#citymanage').bootstrapTable('getSelections');
		var ids = [];
		$.each(obj,function(i){
			if(obj[i].countryid!=null&&obj[i].countryid!=''){
				ids.push(obj[i].countryid);
			}
		});
		tk.ajax({
			url : "/TownManagement/citymanage/updateCityState",
	        data : {"cityObj":ids},
	        succ :function(){
	        	$('#citymanage').bootstrapTable('refresh');
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
	temp = GetFormSearchData('form_search',temp);
	return temp;
	}
});
//展示详情modal
function querydetail(id) {
	$.ajax({
		url : "/TownManagement/pages/ProjectPlan/citycaseEntry.html",
		cache : false,
		success : function(html) {
			$("#citycasebody").html(html);
//			$("#basicform").remove();
			
//			$("#comfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/citymanage/queryCityDetail",
				data : {"countryid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#basicform',data);
					$('#cityfieldset').attr("disabled","disabled");
					$("#citycasemodal").modal('show');
					$("#basicform_submit").hide();
					$("#basicform_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(id){
	$.ajax({
		url : "/TownManagement/pages/ProjectPlan/citycaseEntry.html",
		cache : false,
		success : function(html) {
			$("#citycasebody").html(html);
//			$("#basicform").remove();
			
//			$("#comfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/citymanage/queryCityDetail",
				data : {"countryid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#basicform',data);
					$("#citycasemodal").modal('show');
					$("#basicform_submit").hide();
					$("#basicform_update").show();
				}
			});
		}
	});
}