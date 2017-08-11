$(document).ready(function() {
	$('#orgmanagetable').bootstrapTable({
		url : '/TownManagement/orgmanage/queryOrgList',
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
			field : 'orgnumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'orgname',
			title : '社会组织单位名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			title : '归属地',
			align : 'center',
			width : '20%',
			  formatter:function(value,row,index){
	            	return row.orgprovince+"/"+row.orgcity+"/"+row.orgtown;
	            }
		}, {
			field : 'orgcategory',
			title : '单位类别',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	if(value=="1"){
            		return "研究机构";
            	}else if(value=="2"){
            		return "高校";
            	}else if(value=="3"){
            		return "社会团体";
            	}else if(value=="4"){
            		return "民办非企业单位";
            	}else if(value=="5"){
            		return "基金会";
            	}
            }
		}, {
            title : '操作',
            field : 'operation',
            width :	'20%',
            align : 'center',
//            formatter:function(value,row,index){
//            	if(sysOfUserPermission==3){
//	            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.orgid+')">查看</a>';
//	            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.orgid+')">修改</a>'
//	            	return query+"&nbsp"+update;
//            }else{
//            	return '<a href="javascript:void(0)" onclick="querydetail('+row.orgid+')">查看</a>';
//            	}
//            }
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
function querydetail(id) {
	$.ajax({
		url : "/TownManagement/pages/Alliance/organizeEntry.html",
		cache : false,
		success : function(html) {
			$("#orginfobody").html(html);
			$("#orgHeader").remove();
			
			$("#orgfieldset").removeAttr("disabled");
			tk.ajax({
				url : "/TownManagement/orgmanage/queryOrgDetail",
				data : {"orgid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#orgform',data);
					// 二级联动
					var select = {
							value : data.data[0].orgnature
					}
					natureChanage(select);
					var arr=(data.data[0].orgcategory).split(",");
					$('#orgcategory').selectpicker();
					$('#orgcategory').selectpicker('val', arr);
					var arr=(data.data[0].orgtype).split(",");
					$('#orgtype').selectpicker();
					$('#orgtype').selectpicker('val', arr);
//					var param={
//							tbname : 'tb_shehuizuzhidanwei',
//							field : 'comcertificate',
//							id : 'comid' ,
//							value : data.data[0].comcertificate,
//							showdelete : false
//					}
//					initDeatilFileInput('comcertificatepic',param);
					var orgcontact=data.data[0].orgcontact.split(",");
					var orgpost=data.data[0].orgpost.split(",");
					var orgcontacttel=data.data[0].orgcontacttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(orgcontact,function(i,item){
						if(i==0){
							$("#orgcontact").val(orgcontact[i]);
							$("#orgpost").val(orgpost[i]);
							$("#orgcontacttel").val(orgcontacttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="orgcontact" id="orgcontact"  class="form-control" type="text" value='+orgcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="orgpost" id="orgpost" class="form-control" type="text" value='+orgpost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="orgcontacttel" id="orgcontacttel" class="form-control" type="text" value='+orgcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
						}
					});
					$('#orgfieldset').attr("disabled","disabled");
					$("#orginfomodal").modal('show');
					$("#orgentry_submit").hide();
					$("#orgentry_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(id){
	$.ajax({
		url : "/TownManagement/pages/Alliance/organizeEntry.html",
		cache : false,
		success : function(html) {
			$("#orginfobody").html(html);
			$("#orgHeader").remove();
			
			$("#orgfieldset").removeAttr("disabled");
			$('#orgform').bootstrapValidator('resetForm', false);
			tk.ajax({
				url : "/TownManagement/orgmanage/queryOrgDetail",
				data : {"orgid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#orgform',data);
					// 二级联动
					var select = {
							value : data.data[0].orgnature
					}
					natureChanage(select);
					var arr=(data.data[0].orgcategory).split(",");
					$('#orgcategory').selectpicker();
					$('#orgcategory').selectpicker('val', arr);
					var arr=(data.data[0].orgtype).split(",");
					$('#orgtype').selectpicker();
					$('#orgtype').selectpicker('val', arr);
					// 图片初始化
//					var param={
//							tbname : 'tb_qiyedanwei',
//							field : 'comcertificate',
//							id : 'comid' ,
//							value : data.data[0].comcertificate,
//							showdelete : true
//					}
//					initDeatilFileInput('comcertificatepic',param);
					// 动态行初始化
					var orgcontact=data.data[0].orgcontact.split(",");
					var orgpost=data.data[0].orgpost.split(",");
					var orgcontacttel=data.data[0].orgcontacttel.split(",");
					$(".addel-target:gt(0)").remove();
					$.each(orgcontact,function(i,item){
						if(i==0){
							$("#orgcontact").val(orgcontact[i]);
							$("#orgpost").val(orgpost[i]);
							$("#orgcontacttel").val(orgcontacttel[i]);
						}else{
							$('<div class="form-group addel-target has-feedback">'
								+'<div class="col-sm-2" style="text-align: right;">'
								+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
								+'<i class="fa fa-plus"></i></button>'
								+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
								+'<i class="fa fa-remove"></i></button>'
								+'<label class="control-label">联系人</label></div>'
								+'<div class="col-sm-2">'
								+'<input name="orgcontact" id="orgcontact"  class="form-control" type="text" value='+orgcontact[i]+'></div>'
								+'<label class="col-sm-2 control-label">职务</label>'
								+'<div class="col-sm-2"><input name="orgpost" id="orgpost" class="form-control" type="text" value='+orgpost[i]+'>'
								+'</div><label class="col-sm-2 control-label">联系电话</label>'
								+'<div class="col-sm-2"><input name="orgcontacttel" id="orgcontacttel" class="form-control" type="text" value='+orgcontacttel[i]+'>'
								+'</div></div>').insertAfter(".addel-target:last");
						}
					});
					orgAddFieldValidator();
					// 展示
					$("#orginfomodal").modal('show');
					$("#orgentry_submit").hide();
					$("#orgentry_update").show();
				}
			});
		}
	});
}