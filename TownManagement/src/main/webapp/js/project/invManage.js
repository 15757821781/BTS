$(document).ready(function() {
	$('#invManage').bootstrapTable({
		url : '/TownManagement/invitemmanage/queryinvinfo',
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
			field : 'invnumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'invname',
			title : '项目名称',
			editable : true,
			align : 'center',
			width : '20%'
		},
		{
			title : '归属地',
			align : 'center',
			width : '20%',
			  formatter:function(value,row,index){
	            	return row.invprovince+"/"+row.invcity+"/"+row.invtown;
	            }
		},{
			field : 'invcharge',
			title : '主管单位',
			align : 'center',
			width : '20%'
		}, {
            title : '操作',
            field : 'invid',
            width :	'20%',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.invid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.invid+')">修改</a>'
            	return query+"&nbsp"+update;
            }
		} ]
	});
	$.ajax({
		url : "/TownManagement/pages/ProjectLibrary/invitem.html",
		cache : false,
		success : function(html) {
			$("#invbody").html(html);
			$("#invHeader").remove();
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
function querydetail(invid) {
	$("#invfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/invitemmanage/queryinvitemdetail",
				async: false,
				data : {"invid":invid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#invitem',data);
					var param1={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invcitypic',
							id : 'invid' ,
							value : data.data[0].invcitypic,
							showdelete : false
					}
					var param2={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invtownpic',
							id : 'invid' ,
							value : data.data[0].invtownpic,
							showdelete : false
					}
					var param3={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invscopeopic',
							id : 'invid' ,
							value : data.data[0].invscopeopic,
							showdelete : false
					}
					var param4={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invplanpic',
							id : 'invid' ,
							value : data.data[0].invplanpic,
							showdelete : false
					}
					var param5={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invallplanpic',
							id : 'invid' ,
							value : data.data[0].invallplanpic,
							showdelete : false
					}
					var param6={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invdetailplanpic',
							id : 'invid' ,
							value : data.data[0].invdetailplanpic,
							showdelete : false
					}
					initDeatilFileInput('invfile1',param1);
					initDeatilFileInput('invfile2',param2);
					initDeatilFileInput('invfile3',param3);
					initDeatilFileInput('invfile4',param4);
					initDeatilFileInput('invfile5',param5);
					initDeatilFileInput('invfile6',param6);
					var invcontact=data.data[0].invcontact.split(",");
					var invpost=data.data[0].invpost.split(",");
					var invcontacttel=data.data[0].invcontacttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(invcontact,function(i,item){
						if(i==0){
							$("#invcontact").val(invcontact[i]);
							$("#invpost").val(invpost[i]);
							$("#invcontacttel").val(invcontacttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="invcontact" id="invcontact"  class="form-control" type="text" value='+invcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="invpost" id="invpost" class="form-control" type="text" value='+invpost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="invcontacttel" id="invcontacttel" class="form-control" type="text" value='+invcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
				}
			});
			$('#invfieldset').attr("disabled","disabled");
			$("#invmodal").modal('show');
			$("#invitem_submit").hide();
			$("#invitem_update").hide();
		}
	});
}
//展示修改界面
function updateinfo(invid){
			$("#invfieldset").removeAttr("disabled");
			$('#invitem').bootstrapValidator('resetForm', false);
			tk.ajax({
				url : "/TownManagement/invitemmanage/queryinvitemdetail",
				async: false,
				data : {"invid":invid},
				dataType : 'JSON',
				succ : function(data, status) {
					fillForm('#invitem',data);
					var param1={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invcitypic',
							id : 'invid' ,
							value : data.data[0].invcitypic,
							showdelete : true
					}
					var param2={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invtownpic',
							id : 'invid' ,
							value : data.data[0].invtownpic,
							showdelete : true
					}
					var param3={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invscopeopic',
							id : 'invid' ,
							value : data.data[0].invscopeopic,
							showdelete : true
					}
					var param4={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invplanpic',
							id : 'invid' ,
							value : data.data[0].invplanpic,
							showdelete : true
					}
					var param5={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invallplanpic',
							id : 'invid' ,
							value : data.data[0].invallplanpic,
							showdelete : true
					}
					var param6={
							tbname : 'tb_zhaoshangxiangmu',
							field : 'invdetailplanpic',
							id : 'invid' ,
							value : data.data[0].invdetailplanpic,
							showdelete : true
					}
					initDeatilFileInput('invfile1',param1);
					initDeatilFileInput('invfile2',param2);
					initDeatilFileInput('invfile3',param3);
					initDeatilFileInput('invfile4',param4);
					initDeatilFileInput('invfile5',param5);
					initDeatilFileInput('invfile6',param6);
					var invcontact=data.data[0].invcontact.split(",");
					var invpost=data.data[0].invpost.split(",");
					var invcontacttel=data.data[0].invcontacttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(invcontact,function(i,item){
						if(i==0){
							$("#invcontact").val(invcontact[i]);
							$("#invpost").val(invpost[i]);
							$("#invcontacttel").val(invcontacttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="invcontact" id="invcontact"  class="form-control" type="text" value='+invcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="invpost" id="invpost" class="form-control" type="text" value='+invpost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="invcontacttel" id="invcontacttel" class="form-control" type="text" value='+invcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
				}
			});
			invAddFieldValidator();
			$("#invmodal").modal('show');
			$("#invitem_submit").hide();
			$("#invitem_update").show();
		}
	});
}
