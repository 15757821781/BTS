$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	initToolbarBootstrapBindings();  
	$('#sta_editor').wysiwyg(); 
	//查询
    $('#staentry_search').click(function() {
		$("#stainfomodal").modal('hide');
		$('#stamanagetable').bootstrapTable('refresh');
	});
    $('#staform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
	//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			statitle : {
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
	  var sta_editorOffset = $('#sta_editor').offset();
	  $('#voiceBtn').css('position','absolute').offset({top: sta_editorOffset.top, left: sta_editorOffset.left+$('#sta_editor').innerWidth()-35});
	} else {
	  $('#voiceBtn').hide();
	}
};