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
	});
	// 加载省市县信息下拉框
	createAreaSelect("sys_province","sys_city","sys_town");
	// 加载气候下拉框
	selectCreate("weather",sysClimate);
	// 加载地形下拉框
	selectCreate("terrain",sysTerrain);
	// 加载优势产业下拉框
	selectCreate("currentindustry",sysAdvIndustry);
	// 加载产业方向下拉框
	selectCreate("industrialorientation",sysDirIndustry);
	// 查询
	$('#townentry_search').click(function() {
		$("#towninfomodal").modal('hide');
		$('#townmanagetable').bootstrapTable('refresh');
	});
});