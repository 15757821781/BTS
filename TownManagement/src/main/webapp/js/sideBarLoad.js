//@laozhu 	
$(document).ready(function() {
  	    $.ajax({
 	        url: "/TownManagement/wflx/loadPages",
 	       	async: false,
 	       	dataType: "json",
 	        success: function(list){
 	        	if(list.state=="loginout"){
 	        		window.location.href='/TownManagement';
 	        		return false;
 	        	}else if(list.data.length!=0){
 	        		$.each(list.data, function(i) {
 	        		    if(list.data[i].pagelevel=="1"){
 	        		    	$('#side-menu').append('<li><a><i class="fa fa-dashboard fa-fw"></i> '+list.data[i].pagename+'<span class="fa arrow"></span></a><ul class="nav nav-second-level" id=pageName_'+list.data[i].pageid+'></ul></li>');
 	        		    }
 	        		    else if(list.data[i].pagelevel=="2"){
 	        		    	$('#pageName_'+list.data[i].parentid).append('<li><a target='+list.data[i].url+'>'+list.data[i].pagename+'</a></li>');
 	        		    }
 	        		}); 
 	        	}
	 	   	    $.ajax({
	 		         url: "/TownManagement/pages/town/townManage.html",
	 		         cache: false,
	 		         success: function(html){
	 		             $("#page-wrapper").html(html);
	 		         }
	 		     });	
 	        }
 	    });
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
	}) 