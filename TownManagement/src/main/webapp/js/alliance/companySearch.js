$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 时间选择器初始化
	$('#comestablish').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 2,
        format: "yyyy-mm-dd"
	})
	$('#comdatayear').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 4,
        format: "yyyy"
	})
	// 加载省市县信息下拉框
	createAreaSelect("comprovince","comcity","comtown");
	// 加载省市县信息下拉框
	createAreaSelect("comregpro","comregcity","comregtown");
	// 加载主要产业下拉框
	selectCreate("commajorindustry",sysMajorIndustry);
	// 加载发展方向下拉框
	selectCreate("comdevelop",sysDevelopDir);
	// 加载行业方向下拉框
	selectCreate("comindustry",sysBusinessDir);
	// 动态增减行初始化
    $('.addel').addel({
		animation: {
			duration: 100
		},
	    events: {
	        added: function (event) {
	        	comAddFieldValidator();
	        }
	    }
    });
	//查询
    $('#comentry_search').click(function() {
		$("#cominfomodal").modal('hide');
		$('#commanagetable').bootstrapTable('refresh');
	});
});