$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	$('#regbegtime').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 4,
        startView : 4,
        format: "yyyy"
	})
	$('#regendtime').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 4,
        startView : 4,
        format: "yyyy"
	})
	$('#regdockingtime').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 3,
        format: "yyyy-mm"
	})
	$('#regcontractdate').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 2,
        format: "yyyy-mm-dd"
	})
	// 动态增减行初始化
	$('.addel-reg').addel({
		animation: {
			duration: 100
		},
	    events: {
	        added: function (event) {
	        	regAddFieldValidator();
	        }
	    }
    });
	createAreaSelect("regprovince","regcity","regtown");
	//优势产业
	selectCreate("regnowindustry",sysAdvIndustry);
	// 产业方向
	selectCreate("regprimeindustry",sysDirIndustry);
	//查询
	 $('#regitem_search').click(function() {
			$("#regionmodal").modal('hide');
			$('#regionManage').bootstrapTable('refresh');
		});
});
//
function addText(v){
	$('.plan_delete').hide();
	var num = $('.add_reg').length;
	$('<div class="form-group add_reg">'
		+'<div class="col-sm-2" style="text-align: right;">'
		+'<button type="button" class="btn btn-danger plan_delete" style="margin-right:4px;" onClick="deleteText(this)">'
		+'<i class="fa fa-remove"> </i></button>'
		+'<label class="control-label">'+num+'期规划面积(平方公里)</label>'
		+'</div><div class="col-sm-2">'
		+'<input id="regplanareas" name="regplanareas" class="form-control"'
		+'type="text"></div>'
		+'<label class="col-sm-2 control-label">'+num+'期计划投资(亿元)</label>'
		+'<div class="col-sm-2">'
		+'<input id="regplaninvests" name="regplaninvests"'
		+'class="form-control" type="text"></div>'
		+'<label class="col-sm-2 control-label">'+num+'期征地面积(平方公里)</label>'
		+'<div class="col-sm-2">'
		+'<input id="reglandareas" name="reglandareas" class="form-control" '
		+'type="text"></div>').insertAfter(".add_reg:last");
		addRegValidator();
}
//
function deleteText(v){
	$(v).parent().parent(".add_reg").remove();
	$('.plan_delete:last').show();
}
//
function readyOnly(v) {
	if(v.value=="0"){
		$(".regpart").attr("disabled","disabled");
	}else{
		$(".regpart").removeAttr("disabled");
	}
}
function addRegValidator(){
	$('#regionitem').bootstrapValidator('addField', 'regplanareas', {
		validators : {
			regexp : {
				regexp : /^(?:0|[1-9]\d*)(\.\d{1,3})?$/,
				message : '请输入最多3位小数的数字'
			}
		}
	});
	$('#regionitem').bootstrapValidator('addField', 'regplaninvests', {
		validators : {
			regexp : {
				regexp : /^(?:0|[1-9]\d*)(\.\d{1,3})?$/,
				message : '请输入最多3位小数的数字'
			}
		}
	});
	$('#regionitem').bootstrapValidator('addField', 'reglandareas', {
		validators : {
			regexp : {
				regexp : /^(?:0|[1-9]\d*)(\.\d{1,3})?$/,
				message : '请输入最多3位小数的数字'
			}
		}
	});
}
function regAddFieldValidator(){
	$('#regionitem').bootstrapValidator('addField', 'regcontact', {
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
	$('#regionitem').bootstrapValidator('addField', 'regpost', {
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
	$('#regionitem').bootstrapValidator('addField', 'regcontenttel', {
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