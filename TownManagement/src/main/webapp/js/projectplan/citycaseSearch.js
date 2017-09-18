$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
		});
	$('#countryyear').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 4,
        format: "yyyy"
	});
	createAreaSelect("countryprovince","countrycity");
	var loadpage="ProjectPlan/citycaseEntry.html";
	
});
//查询
$('#basicform_search').click(function() {
	$("#citycasemodal").modal('hide');
	$('#citymanage').bootstrapTable('refresh');
});
function changeTab(){
	$('#BasicSituation_tab').removeClass("active");
	$('#BasicSituation').removeClass("active in");
	$('#ExploreSituation_tab').addClass("active");
	$('#ExploreSituation').addClass("active in");
}