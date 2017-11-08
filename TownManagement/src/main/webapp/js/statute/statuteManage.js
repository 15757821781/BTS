$(document).ready(function() {
	$('#stamanagetable').bootstrapTable({
		url : '/TownManagement/statutemanage/queryStaList',
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
			field : 'statitle',
			title : '标题',
			align : 'center',
			width : '20%'
		}, {
			field : 'stanumber',
			title : '发文字号',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			field : 'statheme',
			title : '主题分类',
			editable : true,
			align : 'center',
			width : '20%',
			formatter:function(value,row,index){
	            if(value=="农业"){
	            	return "农业";
	            }else if(value=="工业"){
	            	return "工业";
	            }else if(value=="体育"){
	            	return "体育";
	            }else if(value=="旅游"){
	            	return "旅游";
	            }else if(value=="教育"){
	            	return "教育";
	            }else if(value=="金融"){
	            	return "金融";
	            }else if(value=="7"){
	            	return "文化";
	            }else if(value=="健康"){
	            	return "健康";
	            }else if(value=="科技"){
	            	return "科技";
	            }else if(value=="服务"){
	            	return "服务";
	            }else if(value=="财政"){
	            	return "财政";
	            }else if(value=="土地"){
	            	return "土地";
	            }else if(value=="环境"){
	            	return "环境";
	            }else if(value=="ppp"){
	            	return "ppp";
	            }else if(value=="城镇化"){
	            	return "城镇化";
	            }else if(value=="特色小镇"){
	            	return "特色小镇";
	            }else if(value=="智慧城市"){
	            	return "智慧城市";
	            }else if(value=="人工智能"){
	            	return "人工智能";
	            }else if(value=="国际关系"){
	            	return "国际关系";
	            }
	          }
		}, {
			field : 'stainscribe',
			title : '落款',
			editable : true,
			align : 'center',
			width : '20%'
          
		}, {
            title : '操作',
            width :	'20%',
            field : 'operation',
            align : 'center',
//            formatter:function(value,row,index){
//            	if(sysOfUserPermission==3){
//	            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.staid+')">查看</a>';
//	            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.staid+')">修改</a>'
//	            	return query+"&nbsp"+update;
//            }else{
//            	return  '<a href="javascript:void(0)" onclick="querydetail('+row.staid+')">查看</a>';
//            	}
//            }
		} ]
	});
//新增弹出框
	$('#statuteSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/Statute/statuteSearch.html",
			cache : false,
			success : function(html) {
				$('#stainfobody').html(html);
				$('#staform_search')[0].reset();
				$("#stainfomodal").modal('show');
			}
		});
	});
})
//表格批量事件
	$('#delstatute').click(function(){
		var obj = $('#stamanagetable').bootstrapTable('getSelections');
		var ids = [];
		$.each(obj,function(i){
			if(obj[i].staid!=null&&obj[i].staid!=''){
				ids.push(obj[i].staid);
			}
		});
		tk.ajax({
			url : "/TownManagement/statutemanage/updateStaState",
	        data : {"staObj":ids},
	        succ :function(){
	        	$('#stamanagetable').bootstrapTable('refresh');
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
	temp = GetFormSearchData('staform_search',temp);
	return temp;
}
//展示详情modal
function querydetail(id) {
	$.ajax({
		url : "/TownManagement/pages/Statute/statuteDetail.html",
		cache : false,
		success : function(html) {
			$("#stainfobody").html(html);
			$("#staHeader").remove();
			
			
			$("#stafieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/statutemanage/queryStaDetail",
				data : {"staid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					data = data.data[0];
					$("#statitle").text(data.statitle);
					$("#createtime").text(data.createtime);
					$("#statext").html(data.statext);
					$("#stanumber").text(data.stanumber);
					$("#statheme").text(data.statheme);
					$("#stainscribe").text(data.stainscribe);
					$('#stafieldset').attr("disabled","disabled");
					$("#stainfomodal").modal('show');
					$("#statute_submit").hide();
					$("#statute_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(id){
	$.ajax({
		url : "/TownManagement/pages/Statute/statuteEntry.html",
		cache : false,
		success : function(html) {
			$("#stainfobody").html(html);
			$("#staHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/statutemanage/queryStaDetail",
				data : {"staid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					$("#editor").html(data.data[0].statext);
					fillForm('#staform',data);
					$("#stainfomodal").modal('show');
					$("#statute_submit").hide();
					$("#statute_update").show();
				}
			});
		}
	});
}