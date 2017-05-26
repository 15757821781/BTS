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
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
        $('#regionitem').bootstrapValidator('revalidateField', 'regbegtime');
        $('#regionitem').bootstrapValidator('revalidateField', 'regendtime');
	});
	$('#regendtime').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 4,
        startView : 4,
        format: "yyyy"
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
        $('#regionitem').bootstrapValidator('revalidateField', 'regbegtime');
        $('#regionitem').bootstrapValidator('revalidateField', 'regendtime');
	});
	createAreaSelect("regprovince","regcity","regtown");
	//初始化文件上传控件
	initFileInput("regfile1","城市背景图",1);
	initFileInput("regfile2","区县背景图",1);
	initFileInput("regfile3","规划范围图",1);
	initFileInput("regfile4","规划方案图",1);
	initFileInput("regfile5","总体规划图",1);
	initFileInput("regfile6","详细规划图",1);
	//优势产业
	selectCreate("regnowindustry","conditionmanage/queryAdvIndustry");
	// 产业方向
	selectCreate("regprimeindustry","conditionmanage/queryDirIndustry");
	//区域性项目表单更新
	$('#regitem_update').click(function() {
		formSubmit('#regionitem','regionmanage/updateregion','ProjectLibrary/regionManage.html');
	});
	setTimeout(function() {
		validatorRegForm();
	}, 500);
});
//
function addText(v){
	$('.addel-delete').hide();
	var num = $('.add_reg').length;
	$('<div class="form-group add_reg">'
		+'<div class="col-sm-2" style="text-align: right;">'
		+'<button type="button" class="btn btn-danger addel-delete" style="margin-right:4px;" onClick="deleteText(this)">'
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
	$('.addel-delete:last').show();
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
function validatorRegForm(){
	$('#regionitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			regname : {
				validators : {
					notEmpty : {
						message : '项目名称不能为空'
					}
				}
			},
			regprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			regtownship : {
				validators : {
					notEmpty : {
						message : '乡镇街道不能为空'
					}
				}
			},
			regposition : {
				validators : {
					notEmpty : {
						message : '地理位置不能为空'
					}
				}
			},
			regschedule : {
				validators : {
					notEmpty : {
						message : '项目阶段不能为空'
					}
				}
			},
			regrelation : {
				validators : {
					notEmpty : {
						message : '关系情况不能为空'
					}
				}
			},
			regplanarea : {
				validators : {
					notEmpty : {
						message : '规划面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regplaninvest : {
				validators : {
					notEmpty : {
						message : '规划面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			reglandarea : {
				validators : {
					notEmpty : {
						message : '规划面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regbasic : {
				validators : {
					notEmpty : {
						message : '基本情况不能为空'
					}
				}
			},
			regspeed : {
				validators : {
					notEmpty : {
						message : '进度情况不能为空'
					}
				}
			},
			regnowindustry : {
				validators : {
					notEmpty : {
						message : '现状产业不能为空'
					}
				}
			},
			regprimeindustry : {
				validators : {
					notEmpty : {
						message : '主导产业不能为空'
					}
				}
			},
			regdeveloper : {
				validators : {
					notEmpty : {
						message : '牵头单位不能为空'
					}
				}
			},
			regcharge : {
				validators : {
//					notEmpty : {
//						message : '负责人不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			regchargetel : {
				validators : {
//					notEmpty : {
//						message : '电话不能为空'
//					},
					regexp : {
						regexp : /^[^,]*$/,
						message : '请输入正确的号码'
					}
				}
			},
			regdevelopment : {
				validators : {
					notEmpty : {
						message : '合作开发情况不能为空'
					}
				}
			},
			regpartner : {
				validators : {
					notEmpty : {
						message : '合作开发单位不能为空'
					}
				}
			},
			regpartcharge : {
				validators : {
//					notEmpty : {
//						message : '负责人不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			regparttel : {
				validators : {
//					notEmpty : {
//						message : '电话不能为空'
//					},
					regexp : {
						regexp : /^[^,]*$/,
						message : '请输入正确的号码'
					}
				}
			},
			reinvest : {
				validators : {
					notEmpty : {
						message : '合作投资额不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regterms : {
				validators : {
					notEmpty : {
						message : '合作方式不能为空'
					}
				}
			},
			regbegtime : {
				validators : {
					notEmpty : {
						message : '合作时间不能为空'
					},  
					callback: {
						message: '开始日期不能大于结束日期',
						callback:function(value, validator,$field,options){
							var end = $('#regionitem').find("input[name='regendtime']").val();
							return value<=end;
						}
					}
				}
			},
			regendtime : {
				validators : {
					notEmpty : {
						message : '合作时间不能为空'
					},  
					callback: {
						message: '结束日期不能小于开始日期',
						callback:function(value, validator,$field){
							var begin = $('#regionitem').find("input[name='regbegtime']").val();
							return value>=begin;
						}
					}
				}
			},
			regcontent : {
				validators : {
					notEmpty : {
						message : '合作内容不能为空'
					}
				}
			},
			regcontact : {
				validators : {
//					notEmpty : {
//						message : '联系人不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			regpost : {
				validators : {
//					notEmpty : {
//						message : '职务不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			regcontenttel : {
				validators : {
//					notEmpty : {
//						message : '联系电话不能为空'
//					},
					regexp : {
						regexp : /^[^,]*$/,
						message : '请输入正确的号码'
					}
				}
			}
		}
	});
	$('#regionitem').bootstrapValidator('resetForm', false);
}