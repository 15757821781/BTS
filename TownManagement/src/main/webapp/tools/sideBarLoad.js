//@laozhu 
$(document).ready(function() {
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress=doKey;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown=doKey;
    tk.ajax({
        url: "/TownManagement/wflx/queryPages",
       	dataType: "json",
        succ: function(list){
        	if(list.data.length!=0){
        		sysOfUserPermission = list.data[0].userdata;
        		$.each(list.data, function(i) {
        		    if(list.data[i].pagelevel=="1"){
        		    	if(list.data[i].url!=""&&list.data[i].url!=null){
        		    		$('#side-menu').append('<li><a target='+list.data[i].url+'><i class="fa fa-dashboard fa-fw"></i> '+list.data[i].pagename+'</a></li>');
        		    	}else{
        		    		$('#side-menu').append('<li><a><i class="fa fa-dashboard fa-fw"></i> '+list.data[i].pagename+'<span class="fa arrow"></span></a><ul class="nav nav-second-level" id=pageName_'+list.data[i].pageid+'></ul></li>');
        		    	}
        		    }
        		    else if(list.data[i].pagelevel=="2"){
        		    	if(list.data[i].url!=""&&list.data[i].url!=null){
        		    		$('#pageName_'+list.data[i].parentid).append('<li><a target='+list.data[i].url+'>'+list.data[i].pagename+'</a></li>');
        		    	}else{
        		    		$('#pageName_'+list.data[i].parentid).append('<li><a>'+list.data[i].pagename+'<span class="fa arrow"></span></a><ul class="nav nav-third-level" id=pageName_'+list.data[i].pageid+'></ul></li>');
        		    	}
        		    }else if(list.data[i].pagelevel=="3"){
        		    	$('#pageName_'+list.data[i].parentid).append('<li><a target='+list.data[i].url+'>'+list.data[i].pagename+'</a></li>');
        		    }
        		}); 
        	}
    	    $('#side-menu li').click(function(){
    	        var current = $(this),  
    	        target = current.find('a').attr('target');
    	        if(target!=null&&target!=undefined){
    	        	$.ajax({
    	   	         url: target,
    	   	         cache: false,
    	   	         success: function(html){
    	   	             $("#page-wrapper").html(html);
    	   	         }
    	   	     });
    	        }
    	    }); 
        	$('#side-menu').metisMenu();
        }
    });
    $.ajax({
         url: "/TownManagement/pages/HomePage/homePage.html",
         cache: false,
         success: function(html){
             $("#page-wrapper").html(html);
         }
     });
    // 点击提醒加载提醒菜单
    $('#top_alert').click(function(){
    	tk.ajax({
    		url: "/TownManagement/conditionmanage/queryNotice",
	       	dataType: "json",
	        succ: function(data){
	        	$('.dropdown-alerts').html(data.data);
	        }
    	});
    });
});
function showAlertDetail(s){
	var id = s.id;
	var html = '<div class="form-group"><label class="col-sm-2 control-label">项目类型:</label>';
	html += '<label class="col-sm-4 control-label">'+$('#'+id).attr('message')+'</label>';
	html += '<label class="col-sm-2 control-label">通知状态:</label>';
	html += '<label class="col-sm-4 control-label">'+$('#'+id).attr('state')+'</label>';
	html += '</div><div class="form-group"><label class="col-sm-2 control-label">名称:</label>';
	html += '<label class="col-sm-4 control-label">'+$('#'+id).attr('name')+'</label>';
	html += '<label class="col-sm-2 control-label">编号:</label>';
	html += '<label class="col-sm-4 control-label">'+$('#'+id).attr('number')+'</label></div>';
	html += '<div class="form-group"><label class="col-sm-5 control-label"></label>';
	html += '<button type="button" class="col-sm-2 btn btn-primary" style="width: auto; min-width: 90px; margin-top: 20px;" onclick="sysAlertRead('+id+')">已阅</button></div>';
	$('#sys_alertForm').html(html);
	$("#sys_alertModal").modal('show');
}
function sysAlertRead(s){
	tk.ajax({
		url: "/TownManagement/conditionmanage/updateNoticeState",
       	dataType: "json",
       	data:{'id':s.id.substring(11)},
        succ: function(data){
        	$("#sys_alertModal").modal('hide');
        }
	});
}