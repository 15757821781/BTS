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
					+'<a href="#" class="list-group-item">'
					+'<i class="fa fa-navicon fa-fw"></i>'+statute[i].statitle
					+'<span class="pull-right text-muted small"><em>'+statute[i].createtime+'</em> </span>'
					+'</a></div>';
			}
			$('#homestatute').html(html);
			var file = data.data.file;
			var html='';
			for(var i in file){
				html+='<div class="list-group" id="homefile">'
					+'<a href="#" class="list-group-item">'
					+'<i class="fa fa-navicon fa-fw"></i>'+file[i].filetitle
					+'<span class="pull-right text-muted small"><em>'+file[i].createtime+'</em> </span>'
					+'</a></div>';
				
			}
			$('#homefile').html(html);
			var notice = data.data.notice;
			var html='';
			for(var i in notice){
				html+='<div class="list-group" id="homenotice">'
					+'<a href="#" class="list-group-item">'
					+'<i class="fa fa-navicon fa-fw"></i>'+notice[i].noctitle
					+'<span class="pull-right text-muted small"><em>'+notice[i].createtime+'</em> </span>'
					+'</a></div>';
				
			}
			$('#homenotice').html(html);
		}
	});
})