$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 时间选择器初始化
	$('#orgestablish').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 4,
        format: "yyyy"
	})
	// 加载区县信息下拉框
	createAreaSelect("orgprovince","orgcity","orgtown");
	// 加载机构优势下拉框
	selectCreate("orgadvantaget",sysAdvantage);
	// 动态增减行初始化
	 $('.addel').addel({
			animation: {
				duration: 100
			},
		    events: {
		        added: function (event) {
		        	orgAddFieldValidator();
		        }
		    }
	    });
	//查询
	 $('#orgentry_search').click(function() {
			$("#orginfomodal").modal('hide');
			$('#orgmanagetable').bootstrapTable('refresh');
		});
});
function natureChanage(v){
	var id = v.value;
	$("#orgcategory option").remove();
	$("#orgcategory").append("<option></option>");
	$("#orgtype option").remove();
	$("#orgtype").append("<option></option>");
	$.each(orgcategory, function(i, item) {
		if(item.parid==id){
			$("#orgcategory").append("<option value="+item.value+">"+item.name+"</option>")
		}
	});
	$.each(orgtype, function(i, item) {
		if(item.parid==id){
			$("#orgtype").append("<option value="+item.value+">"+item.name+"</option>")
		}
	});
	$("#orgcategory").selectpicker('refresh');
	$("#orgtype").selectpicker('refresh');
}