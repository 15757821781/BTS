$(document).ready(function() {
	$('#townmanagetable').bootstrapTable({
		url : '/TownManagement/townmanage/querytowninfo',
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
			field : 'number',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'centertownname',
			title : '中心镇名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
//			field : 'attributionarea',
			title : '归属地',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	var area="";
            	$.each(areadata, function(idx, item) {
            		if (item.code == row.sys_province) {
            			area+=item.names+"/";
            		}else if(item.code == row.sys_city){
            			area+=item.names+"/";
            		}else if(item.code == row.sys_town){
            			area+=item.names;
            		}
            	});
            	return area;
            }
		}, {
			field : 'townlevel',
			title : '小镇等级',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	if(value=="0"){
            		return "非中心镇";
            	}else if(value=="1"){
            		return "市级";
            	}else if(value=="2"){
            		return "省级";
            	}
            }
		}, {
            title : '操作',
            field : 'centertownid',
            width :	'20%',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="querytowndetail('+row.centertownid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updatetowninfo('+row.centertownid+')">修改</a>'
            	return query+"&nbsp"+update;
            }
		} ]
	});
	$.ajax({
		url : "/TownManagement/pages/town/townEntry.html",
		cache : false,
		success : function(html) {
			$("#towninfobody").html(html);
		}
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
function querytowndetail(centertownid) {
	tk.ajax({
		url : "/TownManagement/townmanage/querytowndetail",
		data : {"centertownid":centertownid},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#townform',data);
			var param1={
					tbname : 'tb_zhongxinzhen',
					field : 'citypic',
					id : 'centertownid' ,
					value : data.data[0].citypic,
					showdelete : false
			}
			initDeatilFileInput('statusfile1',param1);
			var param2={
					tbname : 'tb_zhongxinzhen',
					field : 'townpic',
					id : 'centertownid' ,
					value : data.data[0].townpic,
					showdelete : false
			}
			initDeatilFileInput('statusfile2',param2);
			var param3={
					tbname : 'tb_zhongxinzhen',
					field : 'scopeopic',
					id : 'centertownid' ,
					value : data.data[0].scopeopic,
					showdelete : false
			}
			initDeatilFileInput('statusfile3',param3);
			var param4={
					tbname : 'tb_zhongxinzhen',
					field : 'totalplanpic',
					id : 'centertownid' ,
					value : data.data[0].totalplanpic,
					showdelete : false
			}
			initDeatilFileInput('planfile1',param4);
			var param5={
					tbname : 'tb_zhongxinzhen',
					field : 'detailplanpic',
					id : 'centertownid' ,
					value : data.data[0].detailplanpic,
					showdelete : false
			}
			initDeatilFileInput('planfile2',param5);
			$("#towninfomodal").modal('show');
			$("#townentry_submit").hide();
			$("#townentry_update").hide();
		}
	});
}
//展示修改界面
function updatetowninfo(centertownid){
	tk.ajax({
		url : "/TownManagement/townmanage/querytowndetail",
		data : {"centertownid":centertownid},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#townform',data);
			var param1={
					tbname : 'tb_zhongxinzhen',
					field : 'citypic',
					id : 'centertownid' ,
					value : data.data[0].citypic,
					showdelete : true
			}
			initDeatilFileInput('statusfile1',param1);
			var param2={
					tbname : 'tb_zhongxinzhen',
					field : 'townpic',
					id : 'centertownid' ,
					value : data.data[0].townpic,
					showdelete : true
			}
			initDeatilFileInput('statusfile2',param2);
			var param3={
					tbname : 'tb_zhongxinzhen',
					field : 'scopeopic',
					id : 'centertownid' ,
					value : data.data[0].scopeopic,
					showdelete : true
			}
			initDeatilFileInput('statusfile3',param3);
			var param4={
					tbname : 'tb_zhongxinzhen',
					field : 'totalplanpic',
					id : 'centertownid' ,
					value : data.data[0].totalplanpic,
					showdelete : true
			}
			initDeatilFileInput('planfile1',param4);
			var param5={
					tbname : 'tb_zhongxinzhen',
					field : 'detailplanpic',
					id : 'centertownid' ,
					value : data.data[0].detailplanpic,
					showdelete : true
			}
			initDeatilFileInput('planfile2',param5);
			$("#towninfomodal").modal('show');
			$("#townentry_submit").hide();
			$("#townentry_update").show();
		}
	});
}