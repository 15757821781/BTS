$(document).ready(function() {
	
	initToolbarBootstrapBindings();  
	$('#editor').wysiwyg(); 
	
	//表单提交
	$('#notice_submit').click(function() {
		var html = $('#editor').html();
		$('#noctext').val(html);
		formSubmit('#nocform','noticemanage/insertNocInfo','SystemNotice/noticeEntry.html');
	});
	$('#notice_update').click(function() {
		var html = $('#editor').html();
		$('#noctext').val(html);
		formSubmit('#nocform','noticemanage/updateNocInfo','SystemNotice/noticeManage.html');
	});
	$('#nocform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
	//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			filetitle : {
				validators : {
					notEmpty : {
						message : '标题不能为空'
					}
				}
			}
		}
	});
});
function initToolbarBootstrapBindings() {
	var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
                 'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
                 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                 'Times New Roman', 'Verdana'],
                 fontTarget = $('[title="字体"]').siblings('.dropdown-menu');
    $.each(fonts, function (idx, fontName) {
        fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
    });
    $('a[title]').tooltip({container:'body'});
  	$('.dropdown-menu input').click(function() {return false;}).change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
      .keydown('esc', function () {this.value='';$(this).change();});
    $('[data-role=magic-overlay]').each(function () { 
      var overlay = $(this), target = $(overlay.data('target')); 
      overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
    });
    $('#voiceBtn').hide();
	if ("onwebkitspeechchange"  in document.createElement("input")) {
	  var editorOffset = $('#editor').offset();
	  $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
	} else {
	  $('#voiceBtn').hide();
	}
};