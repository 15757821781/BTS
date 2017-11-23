$(document).ready(function() {
	$('#filemanagetable').bootstrapTable({
		url : '/TownManagement/filemanage/queryFileList',
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
			field : 'filetitle',
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
            align : 'center',
//            formatter:function(value,row,index){
//            	if(sysOfUserPermission==3){
//	            	var query = '<a href="javascript:void(0)" onclick="querydetail('+row.nocid+')">查看</a>';
//	            	var update = '<a href="javascript:void(0)" onclick="updateinfo('+row.nocid+')">修改</a>'
//	            	return query+"&nbsp"+update;
//            }else{
//            	return  '<a href="javascript:void(0)" onclick="querydetail('+row.nocid+')">查看</a>';
//            	}
//            }
		} ]
	});
//新增弹出框
	$('#fileSearch_bar').click(function() {
		$.ajax({
			url : "/TownManagement/pages/FileManagement/fileSearch.html",
			cache : false,
			success : function(html) {
				$('#fileinfobody').html(html);
				$('#fileform_search')[0].reset();
				$("#fileinfomodal").modal('show');
			}
		});
	});
})

//表格批量事件
	$('#delfile').click(function(){
		var obj = $('#filemanagetable').bootstrapTable('getSelections');
		var ids = [];
		$.each(obj,function(i){
			if(obj[i].fileid!=null&&obj[i].fileid!=''){
				ids.push(obj[i].fileid);
			}
		});
		tk.ajax({
			url : "/TownManagement/filemanage/updateFileState",
	        data : {"fileObj":ids},
	        succ :function(){
	        	$('#filemanagetable').bootstrapTable('refresh');
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
	temp = GetFormSearchData('fileform_search',temp);
	return temp;
}
//展示详情modal
function querydetail(id) {
	$.ajax({
		url : "/TownManagement/pages/FileManagement/fileDetail.html",
		cache : false,
		success : function(html) {
			$("#fileinfobody").html(html);
			
			tk.ajax({
				url : "/TownManagement/filemanage/queryFileDetail",
				data : {"fileid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#fileform',data);
					var filetexts=data.data[0].filetext;
					// 多行展示
					var files = filetexts.substring(0,filetexts.length-1).split(',');
					$(".addel-target:gt(0)").remove();
					for(var i=0;i<files.length;i++){
						var url = "'"+files[i]+"'";
						if(i==0){
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-3 control-label">附件</label>'
									+'<div class="col-sm-4">'
									+'<input "id="filetext" class="form-control" type="text" value='+files[i]+'></div>'
									+'<div class="col-sm-2">'
									+'<button type="button" class=" btn btn-primary" onclick="fileDownLoad('+url+')" >下载</button></div>'
									+'</div>').insertAfter(".addel-target:last");
						}else{
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-3 control-label"></label>'
									+'<div class="col-sm-4">'
									+'<input "id="filetext_'+i+'" class="form-control" type="text" value='+files[i]+'></div>'
									+'<div class="col-sm-2">'
									+'<button type="button" class=" btn btn-primary" onclick="fileDownLoad('+url+')" >下载</button></div>'
									+'</div>').insertAfter(".addel-target:last");
						}
						
					}
//					$('').html(html);
					$('#filefieldset').attr("disabled","disabled");
					$("#fileinfomodal").modal('show');
					$("#file_submit").hide();
					$("#file_update").hide();
				}
			});
		}
	});
}
//展示修改界面
function updateinfo(id){
	$.ajax({
		url : "/TownManagement/pages/FileManagement/fileEntry.html",
		cache : false,
		success : function(html) {
			$("#fileinfobody").html(html);
			$("#fileHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/filemanage/queryFileDetail",
				data : {"fileid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					fillForm('#fileform',data);
					var param={
							tbname : 'tb_wenjianguanli',
							field : 'filetext',
							id : 'fileid' ,
							value : data.data[0].filetext,
							showdelete : true
					}
					initDeatilFileInput('comcertificatepic',param);
					data = data.data[0];
					$("#filetitle").text(data.noctitle);
					$("#createtime").text(data.createtime);
					$("#fileinfomodal").modal('show');
					$("#file_submit").hide();
					$("#file_update").show();
				}
			});
		}
	});
}
//文件下载事件
function fileDownLoad(v){
	 window.open(v);
}

