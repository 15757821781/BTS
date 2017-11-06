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
		height :650, //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
		//cardView : false, //是否显示详细视图
		//detailView : true, //是否显示父子表
		columns : [ {
			checkbox : true
		},{
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
			width : '20%',
			 formatter:function(value,row,index){
	            	return row.resprovince+"/"+row.rescity+"/"+row.restown;
	            }
		}, {
			field : 'rescompetentunit',
			title : '业主单位',
			align : 'center',
			width : '20%'
		}, {
            title : '操作',
            field : 'operation',
            width :	'20%',
            align : 'center',
//            formatter:function(value,row,index){
//            	if(sysOfUserPermission==3){
//            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.resid+')">查看</a>';
//            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.resid+')">修改</a>'
//            	return query+"&nbsp"+update;
//            }else{
//            	return '<a href="javascript:void(0)" onclick="querydetail('+row.resid+')">查看</a>';
//            	}
//            }
		} ]
	});
	//新增弹出框
	$('#resSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/ProjectLibrary/resSearch.html",
			cache : false,
			success : function(html) {
				$('#resbody').html(html);
				$('#resitem_search')[0].reset();
				$("#resmodal").modal('show');
			}
		});
	});
})
//表格事件
	$('#delres').click(function(){
		var obj = $('#resManage').bootstrapTable('getSelections');
		var ids = [];
		$.each(obj,function(i){
			if(obj[i].resid!=null&&obj[i].resid!=''){
				ids.push(obj[i].resid);
			}
		});
		tk.ajax({
			url : "/TownManagement/resitemmanage/updateresitemState",
	        data : {"resObj":ids},
	        succ :function(){
	        	$('#resManage').bootstrapTable('refresh');
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
	temp = GetFormSearchData('resitem_search',temp);
	return temp;
}
//展示详情modal
function querydetail(resid) {
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/resitem.html",
		cache : false,
		success : function(html) {
			$("#resbody").html(html);
			$("#resHeader").remove();
			
			$("#resfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/resitemmanage/queryresitemdetail",
				cache : false,
				data : {"resid":resid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#resitem',data);
					var param1={
							tbname : 'tb_chubeixiangmu',
							field : 'rescitypic',
							id : 'resid' ,
							value : data.data[0].rescitypic,
							showdelete : false
					}
					var param2={
							tbname : 'tb_chubeixiangmu',
							field : 'restownpic',
							id : 'resid' ,
							value : data.data[0].restownpic,
							showdelete : false
					}
					var param3={
							tbname : 'tb_chubeixiangmu',
							field : 'resscopeopic',
							id : 'resid' ,
							value : data.data[0].resscopeopic,
							showdelete : false
					}
					var param4={
							tbname : 'tb_chubeixiangmu',
							field : 'resplanpic',
							id : 'resid' ,
							value : data.data[0].resplanpic,
							showdelete : false
					}
					var param5={
							tbname : 'tb_chubeixiangmu',
							field : 'resallplanpic',
							id : 'resid' ,
							value : data.data[0].resallplanpic,
							showdelete : false
					}
					var param6={
							tbname : 'tb_chubeixiangmu',
							field : 'resdetailplanpic',
							id : 'resid' ,
							value : data.data[0].resdetailplanpic,
							showdelete : false
					}
					initDeatilFileInput('resfile1',param1);
					initDeatilFileInput('resfile2',param2);
					initDeatilFileInput('resfile3',param3);
					initDeatilFileInput('resfile4',param4);
					initDeatilFileInput('resfile5',param5);
					initDeatilFileInput('resfile6',param6);
					// 处理多行展示
					var rescontactunit=data.data[0].rescontactunit.split(",");
					var rescontacts=data.data[0].rescontacts.split(",");
					var rescontactway=data.data[0].rescontactway.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(rescontactunit,function(i,item){
						if(i==0){
							$("#rescontactunit").val(rescontactunit[i]);
							$("#rescontacts").val(rescontacts[i]);
							$("#rescontactway").val(rescontactway[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系单位</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="rescontactunit" id="rescontactunit"  class="form-control" type="text" value='+rescontactunit[i]+'></div>'
								+'<label class="col-sm-2 control-label">联系人</label>'
								+'<div class="col-sm-2"><input name="rescontacts" id="rescontacts" class="form-control" type="text" value='+rescontacts[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系方式</label>'
								+'<div class="col-sm-2"><input name="rescontactway" id="rescontactway" class="form-control" type="text" value='+rescontactway[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
				}
			});
			$('#resfieldset').attr("disabled","disabled");
			$("#resmodal").modal('show');
			$("#resitem_submit").hide();
			$("#resitem_update").hide();
		}
	});
		}
	});
}
//展示修改界面
function updateinfo(resid){
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/resitem.html",
		cache : false,
		success : function(html) {
			$("#resbody").html(html);
			$("#resHeader").remove();
			
			$("#resfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/resitemmanage/queryresitemdetail",
				cache : false,
				data : {"resid":resid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#resitem',data);
					var param1={
							tbname : 'tb_chubeixiangmu',
							field : 'rescitypic',
							id : 'resid' ,
							value : data.data[0].rescitypic,
							showdelete : true
					}
					var param2={
							tbname : 'tb_chubeixiangmu',
							field : 'restownpic',
							id : 'resid' ,
							value : data.data[0].restownpic,
							showdelete : true
					}
					var param3={
							tbname : 'tb_chubeixiangmu',
							field : 'resscopeopic',
							id : 'resid' ,
							value : data.data[0].resscopeopic,
							showdelete : true
					}
					var param4={
							tbname : 'tb_chubeixiangmu',
							field : 'resplanpic',
							id : 'resid' ,
							value : data.data[0].resplanpic,
							showdelete : true
					}
					var param5={
							tbname : 'tb_chubeixiangmu',
							field : 'resallplanpic',
							id : 'resid' ,
							value : data.data[0].resallplanpic,
							showdelete : true
					}
					var param6={
							tbname : 'tb_chubeixiangmu',
							field : 'resdetailplanpic',
							id : 'resid' ,
							value : data.data[0].resdetailplanpic,
							showdelete : true
					}
					initDeatilFileInput('resfile1',param1);
					initDeatilFileInput('resfile2',param2);
					initDeatilFileInput('resfile3',param3);
					initDeatilFileInput('resfile4',param4);
					initDeatilFileInput('resfile5',param5);
					initDeatilFileInput('resfile6',param6);
					// 处理多行展示
					var rescontactunit=data.data[0].rescontactunit.split(",");
					var rescontacts=data.data[0].rescontacts.split(",");
					var rescontactway=data.data[0].rescontactway.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(rescontactunit,function(i,item){
						if(i==0){
							$("#rescontactunit").val(rescontactunit[i]);
							$("#rescontacts").val(rescontacts[i]);
							$("#rescontactway").val(rescontactway[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系单位</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="rescontactunit" id="rescontactunit"  class="form-control" type="text" value='+rescontactunit[i]+'></div>'
								+'<label class="col-sm-2 control-label">联系人</label>'
								+'<div class="col-sm-2"><input name="rescontacts" id="rescontacts" class="form-control" type="text" value='+rescontacts[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系方式</label>'
								+'<div class="col-sm-2"><input name="rescontactway" id="rescontactway" class="form-control" type="text" value='+rescontactway[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
						}
					});
					resAddFieldValidator();
					$("#resmodal").modal('show');
					$("#resitem_submit").hide();
					$("#resitem_update").show();
				}
			});
		}
	});
}