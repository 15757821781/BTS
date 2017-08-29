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
        minView: 3,
        format: "yyyy-mm"
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
		$('#basiform').bootstrapValidator('revalidateField', 'countryyear');
	});
	createAreaSelect("countryprovince","countrycity");
	var loadpage="ProjectPlan/citycaseEntry.html";
//	$('#"next_submit"').click(function() {
//		formSubmit('#basicform','citymanage/insertCityInfo',loadpage);
//	});
	//表单提交
	$('#basicform_submit').click(function() {
		formSubmit('#basicform','citymanage/insertCityInfo',loadpage);
	});
	$('#basicform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			//			valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
		}
	});
	
});

function changeTab(){
	$('#BasicSituation_tab').removeClass("active");
	$('#BasicSituation').removeClass("active in");
	$('#ExploreSituation_tab').addClass("active");
	$('#ExploreSituation').addClass("active in");
}