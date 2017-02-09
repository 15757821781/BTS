//@laozhu 	
$(document).ready(function() {
  	    $.ajax({
 	        url: "wflx/loadPages",
 	       	async: false,
 	       	dataType: "json",
 	        success: function(list){
 	        	if(list.length!=0){
 	        		$.each(list, function(i) {     
 	        		    if(list[i].pagelevel=="1"){
 	        		    	$('#side-menu').append('<li><a><i class="fa fa-dashboard fa-fw"></i> '+list[i].pagename+'<span class="fa arrow"></span></a><ul class="nav nav-second-level" id=pageName_'+list[i].pageid+'></ul></li>');
 	        		    }
 	        		    else if(list[i].pagelevel=="2"){
 	        		    	$('#pageName_'+list[i].parentid).append('<li><a target='+list[i].url+'>'+list[i].pagename+'</a></li>');
 	        		    }
 	        		}); 
 	        	}
 	        }
 	    });
	    $.ajax({
	         url: "pages/town/townManage.html",
	         cache: false,
	         success: function(html){
	             $("#page-wrapper").html(html);
	         }
	     });	      
	    $('#side-menu li').click(function(){
	        var current = $(this),  
	        target = current.find('a').attr('target');
	        if(target!=null&&target!=undefined){
		        $.get(target,function(data){
		            $("#page-wrapper").html(data);   
		         });
	        }
	    }); 
	}) 