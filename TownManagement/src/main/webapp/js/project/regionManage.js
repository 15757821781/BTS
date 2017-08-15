$(document).ready(function() {
	$('#regionManage').bootstrapTable({
		url : '/TownManagement/regionmanage/queryregioninfo',
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
			field : 'regnumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'regname',
			title : '项目名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			title : '所属地区',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	return row.regprovince+"/"+row.regcity+"/"+row.regtown;
            }
		}, {
			field : 'regschedule',
			title : '项目阶段',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	if(value=="1"){
            		return "谋划阶段";
            	}else if (value=="2"){
            		return "实施阶段";
            	}
            }
		}, {
            title : '操作',
            field : 'operation',
            width :	'20%',
            align : 'center',
//            formatter:function(value,row,index){
//            	if(sysOfUserPermission==3){
//	            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.regid+')">查看</a>';
//	            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.regid+')">修改</a>'
//	            	return query+"&nbsp"+update;
//            }else{
//            	return '<a href="javascript:void(0)" onclick="querydetail('+row.regid+')">查看</a>';
//            	}
//            }
		} ]
	});
	//新增弹出框
	$('#regionSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/ProjectLibrary/regionSearch.html",
			cache : false,
			success : function(html) {
				$('#regionbody').html(html);
				$('#regionitem_search')[0].reset();
				$("#regionmodal").modal('show');
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
	temp = GetFormSearchData('regionitem_search',temp);
	return temp;
}
//展示详情modal
function querydetail(regid) {
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/regionitem.html",
		cache : false,
		success : function(html) {
			$("#regionbody").html(html);
			$("#regHeader").remove();
			
			$("#regfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/regionmanage/queryregionitemdetail",
				cache : false,
				data : {"regid":regid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#regionitem',data);
					var param1={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regcitypic',
							id : 'regid' ,
							value : data.data[0].regcitypic,
							showdelete : false
					}
					var param2={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regtownpic',
							id : 'regid' ,
							value : data.data[0].regtownpic,
							showdelete : false
					}
					var param3={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regscopeopic',
							id : 'regid' ,
							value : data.data[0].regscopeopic,
							showdelete : false
					}
					var param4={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regplanpic',
							id : 'regid' ,
							value : data.data[0].regplanpic,
							showdelete : false
					}
					var param5={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regallplanpic',
							id : 'regid' ,
							value : data.data[0].regallplanpic,
							showdelete : false
					}
					var param6={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regdetailplanpic',
							id : 'regid' ,
							value : data.data[0].regdetailplanpic,
							showdelete : false
					}
					initDeatilFileInput('regfile1',param1);
					initDeatilFileInput('regfile2',param2);
					initDeatilFileInput('regfile3',param3);
					initDeatilFileInput('regfile4',param4);
					initDeatilFileInput('regfile5',param5);
					initDeatilFileInput('regfile6',param6);
					$(".add_reg:gt(0)").remove();
					// 处理多行展示
					if(data.data[0].regplanareas!=null){
						var regplanareas=data.data[0].regplanareas.split(",");
						var regplaninvests=data.data[0].regplaninvests.split(",");
						var reglandareas=data.data[0].reglandareas.split(",");
						$.each(regplanareas,function(i,item){
							$('.plan_delete').hide();
							var num = $('.add_reg').length;
							$('<div class="form-group add_reg">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-danger plan_delete" style="margin-right:4px;" onClick="deleteText(this)">'
								+'<i class="fa fa-remove"> </i></button>'
								+'<label class="control-label">'+num+'期规划面积(平方公里)</label>'
								+'</div><div class="col-sm-2">'
								+'<input id="regplanareas" name="regplanareas" class="form-control"'
								+'type="text" value='+regplanareas[i]+'></div>'
								+'<label class="col-sm-2 control-label">'+num+'期计划投资(亿元)</label>'
								+'<div class="col-sm-2">'
								+'<input id="regplaninvests" name="regplaninvests"'
								+'class="form-control" type="text" value='+regplaninvests[i]+'></div>'
								+'<label class="col-sm-2 control-label">'+num+'期征地面积(平方公里)</label>'
								+'<div class="col-sm-2">'
								+'<input id="reglandareas" name="reglandareas" class="form-control" '
								+'type="text" value='+reglandareas[i]+'></div>').insertAfter(".add_reg:last");
						});
						addRegValidator();
					}
					var regcontact=data.data[0].regcontact.split(",");
					var regpost=data.data[0].regpost.split(",");
					var regcontenttel=data.data[0].regcontenttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(regcontact,function(i,item){
						if(i==0){
							$("#regcontact").val(regcontact[i]);
							$("#regpost").val(regpost[i]);
							$("#regcontenttel").val(regcontenttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="regcontact" id="regcontact"  class="form-control" type="text" value='+comcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="regpost" id="regpost" class="form-control" type="text" value='+compost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="regcontenttel" id="regcontenttel" class="form-control" type="text" value='+comcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
						}
					});
					// 合作禁用选项
					if (data.data[0].regdevelopment == "0") {
						$(".regpart").attr("disabled", "disabled");
					} else {
						$(".regpart").removeAttr("disabled");
					}
					$('#regfieldset').attr("disabled","disabled");
					$("#regionmodal").modal('show');
					$("#regitem_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(regid){
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/regionitem.html",
		cache : false,
		success : function(html) {
			$("#regionbody").html(html);
			$("#regHeader").remove();
			
			$("#regfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/regionmanage/queryregionitemdetail",
				cache : false,
				data : {"regid":regid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#regionitem',data);
					var param1={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regcitypic',
							id : 'regid' ,
							value : data.data[0].regcitypic,
							showdelete : true
					}
					var param2={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regtownpic',
							id : 'regid' ,
							value : data.data[0].regtownpic,
							showdelete : true
					}
					var param3={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regscopeopic',
							id : 'regid' ,
							value : data.data[0].regscopeopic,
							showdelete : true
					}
					var param4={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regplanpic',
							id : 'regid' ,
							value : data.data[0].regplanpic,
							showdelete : true
					}
					var param5={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regallplanpic',
							id : 'regid' ,
							value : data.data[0].regallplanpic,
							showdelete : true
					}
					var param6={
							tbname : 'tb_quyuxingxiangmu',
							field : 'regdetailplanpic',
							id : 'regid' ,
							value : data.data[0].regdetailplanpic,
							showdelete : true
					}
					initDeatilFileInput('regfile1',param1);
					initDeatilFileInput('regfile2',param2);
					initDeatilFileInput('regfile3',param3);
					initDeatilFileInput('regfile4',param4);
					initDeatilFileInput('regfile5',param5);
					initDeatilFileInput('regfile6',param6);
					// 处理多行展示
					$(".add_reg:gt(0)").remove();
					if(data.data[0].regplanareas!=null){
						var regplanareas=data.data[0].regplanareas.split(",");
						var regplaninvests=data.data[0].regplaninvests.split(",");
						var reglandareas=data.data[0].reglandareas.split(",");
						$.each(regplanareas,function(i,item){
							$('.plan_delete').hide();
							var num = $('.add_reg').length;
							$('<div class="form-group add_reg">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-danger plan_delete" style="margin-right:4px;" onClick="deleteText(this)">'
								+'<i class="fa fa-remove"> </i></button>'
								+'<label class="control-label">'+num+'期规划面积(平方公里)</label>'
								+'</div><div class="col-sm-2">'
								+'<input id="regplanareas" name="regplanareas" class="form-control"'
								+'type="text" value='+regplanareas[i]+'></div>'
								+'<label class="col-sm-2 control-label">'+num+'期计划投资(亿元)</label>'
								+'<div class="col-sm-2">'
								+'<input id="regplaninvests" name="regplaninvests"'
								+'class="form-control" type="text" value='+regplaninvests[i]+'></div>'
								+'<label class="col-sm-2 control-label">'+num+'期征地面积(平方公里)</label>'
								+'<div class="col-sm-2">'
								+'<input id="reglandareas" name="reglandareas" class="form-control" '
								+'type="text" value='+reglandareas[i]+'></div>').insertAfter(".add_reg:last");
						});
						addRegValidator();
					}
					var regcontact=data.data[0].regcontact.split(",");
					var regpost=data.data[0].regpost.split(",");
					var regcontenttel=data.data[0].regcontenttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(regcontact,function(i,item){
						if(i==0){
							$("#regcontact").val(regcontact[i]);
							$("#regpost").val(regpost[i]);
							$("#regcontenttel").val(regcontenttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="regcontact" id="regcontact"  class="form-control" type="text" value='+comcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="regpost" id="regpost" class="form-control" type="text" value='+compost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="regcontenttel" id="regcontenttel" class="form-control" type="text" value='+comcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
						}
					});
					regAddFieldValidator();
					// 合作禁用选项
					if (data.data[0].regdevelopment == "0") {
						$(".regpart").attr("disabled", "disabled");
					} else {
						$(".regpart").removeAttr("disabled");
					}
					$("#regionmodal").modal('show');
					$("#regitem_update").show();
				}
			});
		}
	});
}