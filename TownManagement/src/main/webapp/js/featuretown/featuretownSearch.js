$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	$('.datepicker').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
		minView : 4,
		format : 'yyyy'
	})
	// 动态增减行初始化
//    $('.addel').addel({
//		animation: {
//			duration: 100
//		},
//	    events: {
//	        added: function (event) {
//	        	feaAddFieldValidator();
//	        }
//	    }
//    });
	//初始化省市县联动
	createAreaSelect("feaprovince","feacity","featown");
	//查询
	$('#featuretownform_search').click(function() {
		
	});
});