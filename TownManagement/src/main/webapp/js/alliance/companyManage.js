$(document).ready(function() {
	$('#commanagetable').bootstrapTable({
		url : '/TownManagement/commanage/queryComList',
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
			field : 'comnumber',
			title : '编号',
			align : 'center',
			width : '20%'
		}, {
			field : 'comname',
			title : '中心镇名称',
			editable : true,
			align : 'center',
			width : '20%'
		}, {
			title : '归属地',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	return row.comprovince+"/"+row.comcity+"/"+row.comtown;
            }
		}, {
			field : 'comcategory',
			title : '单位类别',
			align : 'center',
			width : '20%',
            formatter:function(value,row,index){
            	if(value=="1"){
            		return "国有企业";
            	}else if(value=="2"){
            		return "集体企业";
            	}else if(value=="3"){
            		return "民营企业";
            	}else if(value=="4"){
            		return "三资外企";
            	}
            }
		}, {
            title : '操作',
            width :	'20%',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.comid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.comid+')">修改</a>'
            	return query+"&nbsp"+update;
            }
		} ]
	});
	$.ajax({
		url : "/TownManagement/pages/Alliance/companyEntry.html",
		cache : false,
		success : function(html) {
			$("#cominfobody").html(html);
			$("#comHeader").remove();
		}
	});
	$('#btsubmit').click(function() {
		/* $('#uploadForm').submit(); */
		var formData = new FormData($("#uploadForm")[0]);
		$.ajax({
			type : "post",
			url : "/TownManagement/commanage/importComInfo",
			data : formData,
			dataType : 'JSON',
			cache : false,
			processData : false,
			contentType : false,
			success : function(data, status) {
				alert(yyy);
			},
			error : function() {

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
	return temp;
}
//展示详情modal
function querydetail(id) {
	tk.ajax({
		url : "/TownManagement/commanage/queryComDetail",
		data : {"comid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#comform',data);
			// 二级联动
			var select = {
					value : data.data[0].comcategory
			}
			typeChanage(select);
			var arr=(data.data[0].comtype).split(",");
			$('#comtype').selectpicker();
			$('#comtype').selectpicker('val', arr);
			// 处理图片
			var param={
					tbname : 'tb_qiyedanwei',
					field : 'comcertificate',
					id : 'comid' ,
					value : data.data[0].comcertificate,
					showdelete : false
			}
			initDeatilFileInput('comcertificatepic',param);
			// 多行展示
			var comcontact=data.data[0].comcontact.split(",");
			var compost=data.data[0].compost.split(",");
			var comcontacttel=data.data[0].comcontacttel.split(",");
			$(".addel-target:gt(0)").remove();
			$.each(comcontact,function(i,item){
				if(i==0){
					$("#comcontact").val(comcontact[i]);
					$("#compost").val(compost[i]);
					$("#comcontacttel").val(comcontacttel[i]);
				}else{
					$('<div class="form-group addel-target has-feedback">'
						+'<div class="col-sm-2" style="text-align: right;">'
						+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
						+'<i class="fa fa-plus"></i></button>'
						+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
						+'<i class="fa fa-remove"></i></button>'
						+'<label class="control-label">联系人</label></div>'
						+'<div class="col-sm-2">'
						+'<input name="comcontact" id="comcontact"  class="form-control" type="text" value='+comcontact[i]+'></div>'
						+'<label class="col-sm-2 control-label">职务</label>'
						+'<div class="col-sm-2"><input name="compost" id="compost" class="form-control" type="text" value='+compost[i]+'>'
						+'</div><label class="col-sm-2 control-label">联系电话</label>'
						+'<div class="col-sm-2"><input name="comcontacttel" id="comcontacttel" class="form-control" type="text" value='+comcontacttel[i]+'>'
						+'</div></div>').insertAfter(".addel-target:last");
				}
			});
			$("#cominfomodal").modal('show');
			$("#comentry_submit").hide();
			$("#comentry_update").hide();
		}
	});
}
//展示修改界面
function updateinfo(id){
	tk.ajax({
		url : "/TownManagement/commanage/queryComDetail",
		data : {"comid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#comform',data);
			// 二级联动
			var select = {
					value : data.data[0].comcategory
			}
			typeChanage(select);
			var arr=(data.data[0].comtype).split(",");
			$('#comtype').selectpicker();
			$('#comtype').selectpicker('val', arr);
			// 图片初始化
			var param={
					tbname : 'tb_qiyedanwei',
					field : 'comcertificate',
					id : 'comid' ,
					value : data.data[0].comcertificate,
					showdelete : true
			}
			initDeatilFileInput('comcertificatepic',param);
			// 动态行初始化
			var comcontact=data.data[0].comcontact.split(",");
			var compost=data.data[0].compost.split(",");
			var comcontacttel=data.data[0].comcontacttel.split(",");
			$(".addel-target:gt(0)").remove();
			$.each(comcontact,function(i,item){
				if(i==0){
					$("#comcontact").val(comcontact[i]);
					$("#compost").val(compost[i]);
					$("#comcontacttel").val(comcontacttel[i]);
				}else{
					$('<div class="form-group addel-target has-feedback">'
						+'<div class="col-sm-2" style="text-align: right;">'
						+'<button type="button" class="btn btn-success addel-add" style="margin-right:4px;">'
						+'<i class="fa fa-plus"></i></button>'
						+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;">'
						+'<i class="fa fa-remove"></i></button>'
						+'<label class="control-label">联系人</label></div>'
						+'<div class="col-sm-2">'
						+'<input name="comcontact" id="comcontact"  class="form-control" type="text" value='+comcontact[i]+'></div>'
						+'<label class="col-sm-2 control-label">职务</label>'
						+'<div class="col-sm-2"><input name="compost" id="compost" class="form-control" type="text" value='+compost[i]+'>'
						+'</div><label class="col-sm-2 control-label">联系电话</label>'
						+'<div class="col-sm-2"><input name="comcontacttel" id="comcontacttel" class="form-control" type="text" value='+comcontacttel[i]+'>'
						+'</div></div>').insertAfter(".addel-target:last");
				}
			});
			// 展示
			$("#cominfomodal").modal('show');
			$("#comentry_submit").hide();
			$("#comentry_update").show();
		}
	});
}