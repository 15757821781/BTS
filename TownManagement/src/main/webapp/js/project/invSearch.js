$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 加载区县信息下拉框
    createAreaSelect("invprovince","invcity","invtown");
    
	// 动态增减行初始化
	$('.addel').addel({
		 animation: {
			 duration: 100
		},
		events: {
			added: function (event) {
				invAddFieldValidator();
			}
		}
	});
	//查询
	 $('#invitems_search').click(function() {
			$("#invmodal").modal('hide');
			$('#invManage').bootstrapTable('refresh');
		});
});
function invAddFieldValidator(){
	$('#invitem').bootstrapValidator('addField', 'invcontact', {
		validators : {
//			notEmpty : {
//				message : '联系人不能为空'
//			},
			regexp : {
				regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
				message : '请输入中文或字母'
			}
		}
	});
	$('#invitem').bootstrapValidator('addField', 'invpost', {
		validators : {
//			notEmpty : {
//				message : '职务不能为空'
//			},
			regexp : {
				regexp : /^[^,]*$/,
				message : '请输入正确的职务'
			}
		}
	});  
	$('#invitem').bootstrapValidator('addField', 'invcontacttel', {
		validators : {
//			notEmpty : {
//				message : '联系电话不能为空'
//			},
			regexp : {
				regexp : /^[^,]*$/,
				message : '请输入正确的号码'
			}
		}
	});
}