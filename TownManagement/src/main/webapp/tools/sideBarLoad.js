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
        		$.each(list.data, function(i) {
        		    if(list.data[i].pagelevel=="1"){
        		    	$('#side-menu').append('<li><a><i class="fa fa-dashboard fa-fw"></i> '+list.data[i].pagename+'<span class="fa arrow"></span></a><ul class="nav nav-second-level" id=pageName_'+list.data[i].pageid+'></ul></li>');
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
         url: "/TownManagement/pages/town/townManage.html",
         cache: false,
         success: function(html){
             $("#page-wrapper").html(html);
         }
     });
});