$(document).ready(function(){
//页面初始化
	tk.ajax({
		url : "/TownManagement/homepagemanage/queryHomePage",
		dataType : 'JSON',
		succ : function(data, status) {
			var statute = data.data.statute;
			var html='';
			for(var i in statute){
				html+='<div class="list-group" id="homestatute">'
					+'<a href="#" class="list-group-item" onclick="staview('+statute[i].staid+')">'
					+'<i class="fa fa-navicon fa-fw"></i>'+statute[i].statitle
					+'<span class="pull-right text-muted small"><em>'+statute[i].createtime+'</em> </span>'
					+'</a></div>';
			}
			$('#homestatute').html(html);
			var file = data.data.file;
			var html='';
			for(var i in file){
				html+='<div class="list-group" id="homefile">'
					+'<a href="#" class="list-group-item" onclick="fileview('+file[i].fileid+')">'
					+'<i class="fa fa-navicon fa-fw"></i>'+file[i].filetitle
					+'<span class="pull-right text-muted small"><em>'+file[i].createtime+'</em> </span>'
					+'</a></div>';
				
			}
			$('#homefile').html(html);
			var notice = data.data.notice;
			var html='';
			for(var i in notice){
				html+='<div class="list-group" id="homenotice">'
					+'<a href="#" class="list-group-item" onclick="noticeview('+notice[i].nocid+')">'
					+'<i class="fa fa-navicon fa-fw"></i>'+notice[i].noctitle
					+'<span class="pull-right text-muted small"><em>'+notice[i].createtime+'</em> </span>'
					+'</a></div>';
				
			}
			$('#homenotice').html(html);
		}
	});
})
//点击首页查看政策法规详情页面
function staview(id){
	$.ajax({
		url : "/TownManagement/pages/Statute/statuteDetail.html",
		cache : false,
		success : function(html) {
			$("#homeinfobody").html(html);
			$("#staHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/statutemanage/queryStaDetail",
				data : {"staid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					data = data.data[0];
					var stafiles=data.stafile;
					var files=stafiles.substring(0,stafiles.length-1).split(',');
					$(".addel-target:gt(0)").remove();
					for(var i=0;i<files.length;i++){
						var url = "'"+files[i]+"'";
						var filenameurl=files[i].substring(files[i].lastIndexOf("/") + 1, files[i] .length);
						if(i==0){
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-2 control-label">附件</label>'
									+'<div class="col-sm-8" id="statutefile">'
									+'<a href="#"onclick="fileDownLoad('+url+')">'
									+'<i></i>'+filenameurl
									+'</a></div>').insertAfter(".addel-target:last");
						}else{
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-2 control-label"></label>'
									+'<div class="col-sm-8" id="statutefile">'
									+'<a href="#" onclick="fileDownLoad('+url+')">'
									+'<i id="stafile'+i+'"></i>'+filenameurl
									+'</a></div>').insertAfter(".addel-target:last");
						}
						
					}
					$("#statitle").text(data.statitle);
					$("#createtime").text(data.createtime);
					$("#statext").html(data.statext);
					$("#stanumber").text(data.stanumber);
					$("#statheme").text(data.statheme);
					$("#stainscribe").text(data.stainscribe);
					$("#homemodal").modal('show');
					$("#statute_submit").hide();
					$("#statute_update").hide();
				}
			});
		}
	});
}
//首页文件详情
function fileview(id) {
	$.ajax({
		url : "/TownManagement/pages/FileManagement/fileDetail.html",
		cache : false,
		success : function(html) {
			$("#homeinfobody").html(html);
			
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
						var filenameurl=files[i].substring(files[i].lastIndexOf("/") + 1, files[i] .length);
						if(i==0){
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-3 control-label">附件</label>'
									+'<div class="col-sm-4">'
									+'<input "id="filetext" class="form-control" type="text" value='+filenameurl+'></div>'
									+'<div class="col-sm-2">'
									+'<button type="button" class=" btn btn-primary" onclick="fileDownLoad('+url+')" >下载</button></div>'
									+'</div>').insertAfter(".addel-target:last");
						}else{
							$('<div class="form-group addel-target has-feedback">'
									+'<label class="col-sm-3 control-label"></label>'
									+'<div class="col-sm-4">'
									+'<input "id="filetext_'+i+'" class="form-control" type="text" value='+filenameurl+'></div>'
									+'<div class="col-sm-2">'
									+'<button type="button" class=" btn btn-primary" onclick="fileDownLoad('+url+')" >下载</button></div>'
									+'</div>').insertAfter(".addel-target:last");
						}
						
					}
//					$('').html(html);
					$('#filefieldset').attr("disabled","disabled");
					$("#homemodal").modal('show');
					$("#file_submit").hide();
					$("#file_update").hide();
				}
			});
		}
	});
}
//首页公告详情
function noticeview(id) {
	$.ajax({
		url : "/TownManagement/pages/SystemNotice/noticeDetail.html",
		cache : false,
		success : function(html) {
			$("#homeinfobody").html(html);
			$("#nocHeader").remove();
			
			tk.ajax({
				url : "/TownManagement/noticemanage/queryNocDetail",
				data : {"nocid":id},
				dataType : 'JSON',
				cache : false,
				succ : function(data, status) {
					data = data.data[0];
					$("#noctitle").text(data.noctitle);
					$("#createtime").text(data.createtime);
					$("#noctext").html(data.noctext);
					$("#homemodal").modal('show');
					$("#noctute_submit").hide();
					$("#noctute_update").hide();
				}
			});
		}
	});
}