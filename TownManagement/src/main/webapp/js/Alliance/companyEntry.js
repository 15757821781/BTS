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
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
//		$('#townform').bootstrapValidator('revalidateField', 'towndatayear');
	});
	$('#comdatayear').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 4,
        format: "yyyy"
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
//		$('#townform').bootstrapValidator('revalidateField', 'towndatayear');
	});
	
	// 加载省市县信息下拉框
	createAreaSelect("sys_province","sys_city","sys_town");
	// 加载主要产业下拉框
	selectCreate("commajorindustry","conditionmanage/queryMajorIndustry");
	// 加载发展方向下拉框
	selectCreate("comdevelop","conditionmanage/queryDirIndustry");
	//表单提交
	$('#comentry_submit').click(function() {
		formSubmit('#comform','commanage/insertcominfo','Alliance/companyEntry.html');
	});
	$('#comentry_update').click(function() {
		formSubmit('#comform','commanage/updatecominfo','Alliance/companyManage.html');
	});
})
