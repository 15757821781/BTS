$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 时间选择器初始化
	$('#resdockingtime').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 3,
        format: "yyyy-mm"
	});
	// 时间选择器初始化
	$('#resfeedbacknode').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 2,
        format: "yyyy-mm-dd"
	});
	// 加载区县信息下拉框
    createAreaSelect("resprovince","rescity","restown");
	// 动态增减行初始化
	 $('.addel-res').addel({
		animation: {
			duration: 100
		},
	    events: {
	        added: function (event) {
	        	resAddFieldValidator();
	        }
	    }
    });
	//查询
	 $('#resitems_search').click(function() {
			$("#resmodal").modal('hide');
			$('#resManage').bootstrapTable('refresh');
		});
});
function resAddFieldValidator(){
	$('#resitem').bootstrapValidator('addField', 'rescontactunit', {
		validators : {
//			notEmpty : {
//				message : '联系单位不能为空'
//			},
			regexp : {
				regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
				message : '请输入中文或字母'
			}
		}
	});
	$('#resitem').bootstrapValidator('addField', 'rescontacts', {
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
	$('#resitem').bootstrapValidator('addField', 'rescontactway', {
		validators : {
//			notEmpty : {
//				message : '联系方式不能为空'
//			},
			regexp : {
				regexp : /^[^,]*$/,
				message : '请输入正确的号码'
			}
		}
	});
}