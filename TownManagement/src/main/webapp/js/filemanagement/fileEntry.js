$(document).ready(function() {
	//初始化文件上传控件
	initFileInput("comcertificatepic","选择",5,[]);
	//表单提交
	$('#file_submit').click(function() {
		formSubmit('#fileform','filemanage/insertFileInfo','FileManagement/fileEntry.html');
	});
	$('#file_update').click(function() {
		formSubmit('#fileform','filemanage/updateFileInfo','FileManagement/fileManage.html');
	});
	$('#fileform').bootstrapValidator({
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
})
