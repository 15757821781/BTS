$(document).ready(function() {
	$('#featuretownManage').bootstrapTable({
		url : '/TownManagement/featuretownmanage/queryfeaturetown',
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
			field : 'feanumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'feaname',
			title : '中心镇名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			title : '归属地',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	return row.feaprovince+"/"+row.feacity+"/"+row.featown;
            }
		}, {
			field : 'fealevel',
			title : '小镇等级',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	var result="";
            	if(value=="0"){
            		result="非省市级";
            	}else if(value=="1"){
            		result="市级";
            	}else if(value=="2"){
            		result="省级";
            	}
            	return result;
            }
		}, {
            title : '操作',
            width :	'20%',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="queryDetail('+row.feaid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateInfo('+row.feaid+')">修改</a>'
            	return query+"&nbsp"+update;
            }
		} ]
	});
	$.ajax({
		url : "/TownManagement/pages/FeatureTown/featuretown.html",
		cache : false,
		success : function(html) {
			$("#featuretownbody").html(html);
			$("#feaHeader").remove();
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
function queryDetail(id) {
	tk.ajax({
		url : "/TownManagement/featuretownmanage/queryfeaturetowndetail",
		data : {"feaid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#featuretown',data);
			var param1={
					tbname : 'tb_tesexiaozhen',
					field : 'feacitypic',
					id : 'feaid' ,
					value : data.data[0].feacitypic,
					showdelete : false
			}
			var param2={
					tbname : 'tb_tesexiaozhen',
					field : 'featownpic',
					id : 'feaid' ,
					value : data.data[0].featownpic,
					showdelete : false
			}
			var param3={
					tbname : 'tb_tesexiaozhen',
					field : 'feascopeopic',
					id : 'feaid' ,
					value : data.data[0].feascopeopic,
					showdelete : false
			}
			var param4={
					tbname : 'tb_tesexiaozhen',
					field : 'feaplanpic',
					id : 'feaid' ,
					value : data.data[0].feaplanpic,
					showdelete : false
			}
			var param5={
					tbname : 'tb_tesexiaozhen',
					field : 'featotalplanpic',
					id : 'feaid' ,
					value : data.data[0].featotalplanpic,
					showdelete : false
			}
			var param6={
					tbname : 'tb_tesexiaozhen',
					field : 'feadetailplanpic',
					id : 'feaid' ,
					value : data.data[0].feadetailplanpic,
					showdelete : false
			}
			initDeatilFileInput('feafile1',param1);
			initDeatilFileInput('feafile2',param2);
			initDeatilFileInput('feafile3',param3);
			initDeatilFileInput('feafile4',param4);
			initDeatilFileInput('feafile5',param5);
			initDeatilFileInput('feafile6',param6);
			// 处理多行展示
			var feacontact=data.data[0].feacontact.split(",");
			var feapost=data.data[0].feapost.split(",");
			var feacontacttel=data.data[0].feacontacttel.split(",");
			$(".addel-target:gt(0)").remove();
			$.each(feacontact,function(i,item){
				if (i == 0) {
					$("#feacontact").val(feacontact[i]);
					$("#feapost").val(feapost[i]);
					$("#feacontacttel").val(feacontacttel[i]);
				} else {
					$('<div class="form-group addel-target">'
						+'<div class="col-sm-2" style="text-align: right;">'
						+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
						+'<i class="fa fa-plus"></i></button>'
						+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
						+'<i class="fa fa-remove"> </i></button>'
						+'<label class="control-label">联系人</label>'
						+'</div><div class="col-sm-2">'
						+'<input id="feacontact" name="feacontact" class="form-control"'
						+'type="text"  value='+feacontact[i]+'></div>'
						+'<label class="col-sm-2 control-label">职务</label>'
						+'<div class="col-sm-2">'
						+'<input id="feapost" name="feapost" class="form-control"'
						+'type="text"  value='+feapost[i]+'></div>'
						+'<label class="col-sm-2 control-label">联系电话</label>'
						+'<div class="col-sm-2"><'
						+'input id="feacontacttel" name="feacontacttel" class="form-control"'
						+'type="text"  value='+feacontacttel[i]+'></div></div>').insertAfter(".addel-target:last");
				}
			});
			// 合作禁用选项
			if (data.data[0].feacooperate == "0") {
				$(".feapart").attr("disabled", "disabled");
			} else {
				$(".feapart").removeAttr("disabled");
			}
			$("#featuretownmodal").modal('show');
			$("#featuretown_submit").hide();
			$("#featuretown_update").hide();
		}
	});
}
//展示修改界面
function updateInfo(id){
	tk.ajax({
		url : "/TownManagement/featuretownmanage/queryfeaturetowndetail",
		data : {"feaid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#featuretown',data);
			var param1={
					tbname : 'tb_tesexiaozhen',
					field : 'feacitypic',
					id : 'feaid' ,
					value : data.data[0].feacitypic,
					showdelete : true
			}
			var param2={
					tbname : 'tb_tesexiaozhen',
					field : 'featownpic',
					id : 'feaid' ,
					value : data.data[0].featownpic,
					showdelete : true
			}
			var param3={
					tbname : 'tb_tesexiaozhen',
					field : 'feascopeopic',
					id : 'feaid' ,
					value : data.data[0].feascopeopic,
					showdelete : true
			}
			var param4={
					tbname : 'tb_tesexiaozhen',
					field : 'feaplanpic',
					id : 'feaid' ,
					value : data.data[0].feaplanpic,
					showdelete : true
			}
			var param5={
					tbname : 'tb_tesexiaozhen',
					field : 'featotalplanpic',
					id : 'feaid' ,
					value : data.data[0].featotalplanpic,
					showdelete : true
			}
			var param6={
					tbname : 'tb_tesexiaozhen',
					field : 'feadetailplanpic',
					id : 'feaid' ,
					value : data.data[0].feadetailplanpic,
					showdelete : true
			}
			initDeatilFileInput('feafile1',param1);
			initDeatilFileInput('feafile2',param2);
			initDeatilFileInput('feafile3',param3);
			initDeatilFileInput('feafile4',param4);
			initDeatilFileInput('feafile5',param5);
			initDeatilFileInput('feafile6',param6);
			// 处理多行展示
			var feacontact=data.data[0].feacontact.split(",");
			var feapost=data.data[0].feapost.split(",");
			var feacontacttel=data.data[0].feacontacttel.split(",");
			$(".addel-target:gt(0)").remove();
			$.each(feacontact,function(i,item){
				if (i == 0) {
					$("#feacontact").val(feacontact[i]);
					$("#feapost").val(feapost[i]);
					$("#feacontacttel").val(feacontacttel[i]);
				} else {
					$('<div class="form-group addel-target">'
						+'<div class="col-sm-2" style="text-align: right;">'
						+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
						+'<i class="fa fa-plus"></i></button>'
						+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
						+'<i class="fa fa-remove"> </i></button>'
						+'<label class="control-label">联系人</label>'
						+'</div><div class="col-sm-2">'
						+'<input id="feacontact" name="feacontact" class="form-control"'
						+'type="text"  value='+feacontact[i]+'></div>'
						+'<label class="col-sm-2 control-label">职务</label>'
						+'<div class="col-sm-2">'
						+'<input id="feapost" name="feapost" class="form-control"'
						+'type="text"  value='+feapost[i]+'></div>'
						+'<label class="col-sm-2 control-label">联系电话</label>'
						+'<div class="col-sm-2"><'
						+'input id="feacontacttel" name="feacontacttel" class="form-control"'
						+'type="text"  value='+feacontacttel[i]+'></div></div>').insertAfter(".addel-target:last");
				}
			});
			if (data.data[0].feacooperate == "0") {
				$(".feapart").attr("disabled", "disabled");
			} else {
				$(".feapart").removeAttr("disabled");
			}
			// 展示
			$("#featuretownmodal").modal('show');
			$("#featuretown_submit").hide();
			$("#featuretown_update").show();
		}
	});
}