$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
		});
	//-----------区域性项目---------------//
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
	//------------结束--------------------//
	//-----------招商项目---------------//
	// 加载区县信息下拉框
    createAreaSelect("invprovince","invcity","invtown");
//	
	// 动态增减行初始化
	 $('.addel-inv').addel({
			animation: {
				duration: 100
			},
		    events: {
		        added: function (event) {
		        	invAddFieldValidator();
		        }
		    }
	    });
	//初始化文件上传控件
	initFileInput("invfile1","城市背景图",1);
	initFileInput("invfile2","区县背景图",1);
	initFileInput("invfile3","规划范围图",1);
	initFileInput("invfile4","规划方案图",1);
	initFileInput("invfile5","总体规划图",1);
	initFileInput("invfile6","详细规划图",1);
	//------------结束-------------------//
	//-----------储备项目---------------//
	// 加载区县信息下拉框
    createAreaSelect("resprovince","rescity","restown");
//	
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
	//初始化文件上传控件
	initFileInput("resfile1","城市背景图",1);
	initFileInput("resfile2","区县背景图",1);
	initFileInput("resfile3","规划范围图",1);
	initFileInput("resfile4","规划方案图",1);
	initFileInput("resfile5","总体规划图",1);
	initFileInput("resfile6","详细规划图",1);
	//------------结束-------------------//
	var loadpage="ProjectLibrary/projectEntry.html";
	//特色小镇表单提交
//	$('#featuretown_submit').click(function() {
//		formSubmit('#featuretown','featuretownmanage/insertfeaturetown',loadpage);
//	});
	//区域性项目表单提交
	$('#regitem_submit').click(function() {
		formSubmit('#regionitem','regionmanage/insertregion',loadpage);
	});
	//招商项目表单提交
	$('#invitem_submit').click(function() {
		formSubmit('#invitem','invitemmanage/insertinvitem',loadpage);
	});
	//储备项目表单提交
	$('#resitem_submit').click(function() {
		formSubmit('#resitem','resitemmanage/insertresitem',loadpage);
	});
	setTimeout(function() {
		validatorProjectForm();
	}, 500);
});
//
function addText(v){
	$('.addel_delete').hide();
	var num = $('.add_reg').length;
	$('<div class="form-group add_reg">'
		+'<div class="col-sm-2" style="text-align: right;">'
		+'<button type="button" class="btn btn-danger addel_delete" style="margin-right:4px;" onClick="deleteText(this)">'
		+'<i class="fa fa-remove"> </i></button>'
		+'<label class="control-label">'+num+'期规划面积(平方公里)</label>'
		+'</div><div class="col-sm-2">'
		+'<input id="regplanareas" name="regplanareas" class="form-control"'
		+'type="text" data-bv-field="regplanareas"></div>'
		+'<label class="col-sm-2 control-label">'+num+'期计划投资(亿元)</label>'
		+'<div class="col-sm-2">'
		+'<input id="regplaninvests" name="regplaninvests"'
		+'class="form-control" type="text" data-bv-field="regplaninvests"></div>'
		+'<label class="col-sm-2 control-label">'+num+'期征地面积(平方公里)</label>'
		+'<div class="col-sm-2">'
		+'<input id="reglandareas" name="reglandareas" class="form-control" '
		+'type="text" data-bv-field="reglandareas"></div>').insertAfter(".add_reg:last");
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
//
function deleteText(v){
	$(v).parent().parent(".add_reg").remove();
	$('.addel_delete:last').show();
}
//
function readyOnly(v) {
	if(v.value=="0"){
		$(".regpart").attr("disabled","disabled");
	}else{
		$(".regpart").removeAttr("disabled");
	}
}
function validatorProjectForm(){
	//区域性项目验证
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
//					notEmpty : {
//						message : '地理位置不能为空'
//					}
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
//					notEmpty : {
//						message : '规划面积不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regplaninvest : {
				validators : {
//					notEmpty : {
//						message : '规划面积不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			reglandarea : {
				validators : {
//					notEmpty : {
//						message : '征地面积不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regbasic : {
				validators : {
//					notEmpty : {
//						message : '基本情况不能为空'
//					}
				}
			},
			regspeed : {
				validators : {
//					notEmpty : {
//						message : '进度情况不能为空'
//					}
				}
			},
			regnowindustry : {
				validators : {
//					notEmpty : {
//						message : '现状产业不能为空'
//					}
				}
			},
			regprimeindustry : {
				validators : {
//					notEmpty : {
//						message : '产业方向不能为空'
//					}
				}
			},
//			regdeveloper : {
//				validators : {
//					notEmpty : {
//						message : '牵头单位不能为空'
//					}
//				}
//			},
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
//			regpartner : {
//				validators : {
//					notEmpty : {
//						message : '合作开发单位不能为空'
//					}
//				}
//			},
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
//					notEmpty : {
//						message : '合作投资额不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			regterms : {
//				validators : {
//					notEmpty : {
//						message : '合作方式不能为空'
//					}
//				}
//			},
			regbegtime : {
				validators : {
//					notEmpty : {
//						message : '合作时间不能为空'
//					},  
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
//					notEmpty : {
//						message : '合作时间不能为空'
//					},  
					callback: {
						message: '结束日期不能小于开始日期',
						callback:function(value, validator,$field){
							var begin = $('#regionitem').find("input[name='regbegtime']").val();
							return value>=begin;
						}
					}
				}
			},
//			regcontent : {
//				validators : {
//					notEmpty : {
//						message : '合作内容不能为空'
//					}
//				}
//			},
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
						regexp : /^[^,]*$/,
						message : '请输入正确的职务'
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
	//政府招商项目
	$('#invitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			invname : {
				validators : {
					notEmpty : {
						message : '项目名称不能为空'
					}
				}
			},
			invprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
//			invcity : {
//				validators : {
//					notEmpty : {
//						message : '城市不能为空'
//					}
//				}
//			},
//			invtown : {
//				validators : {
//					notEmpty : {
//						message : '区县不能为空'
//					}
//				}
//			},
			invtownship : {
				validators : {
					notEmpty : {
						message : '街道不能为空'
					}
				}
			},
			invcharge : {
				validators : {
					notEmpty : {
						message : '主管单位不能为空'
					}
				}
			},
			invjoinway : {
				validators : {
					notEmpty : {
						message : '合作方式不能为空'
					}
				}
			},
//			invindustry : {
//				validators : {
//					notEmpty : {
//						message : '所属行业不能为空'
//					}
//				}
//			},
			invlandscale : {
				validators : {
//					notEmpty : {
//						message : '用地规模不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			invplotratio : {
				validators : {
//					notEmpty : {
//						message : '容积率不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			invplanuse : {
//				validators : {
//					notEmpty : {
//						message : '土地用途不能为空'
//					}
//				}
//			},
			investment : {
				validators : {
//					notEmpty : {
//						message : '投资强度不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			investmentall : {
				validators : {
//					notEmpty : {
//						message : '投资总额不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			invexpectbuild : {
				validators : {
//					notEmpty : {
//						message : '预计建设期不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			invplanaddress : {
//				validators : {
//					notEmpty : {
//						message : '规划地址不能为空'
//					}
//				}
//			},
//			invbuildcontent : {
//				validators : {
//					notEmpty : {
//						message : '建设内容不能为空'
//					}
//				}
//			},
//			invbuildcondition : {
//				validators : {
//					notEmpty : {
//						message : '建设条件不能为空'
//					}
//				}
//			},
//			invincentives : {
//				validators : {
//					notEmpty : {
//						message : '优惠政策不能为空'
//					}
//				}
//			},
//			invessential : {
//				validators : {
//					notEmpty : {
//						message : '要素分析不能为空'
//					}
//				}
//			},
//			invbenefit : {
//				validators : {
//					notEmpty : {
//						message : '效益分析不能为空'
//					}
//				}
//			},
			invcontact : {
				validators : {
//					notEmpty : {
//						message : '联系人不能为空'
//					},
					regexp : {
						regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
						message : '请输入中文或字母'
					}
				}
			},
			invpost : {
				validators : {
//					notEmpty : {
//						message : '职务不能为空'
//					},
					regexp : {
						regexp : /^[^,]*$/,
						message : '请输入正确的职务'
					}
				}
			},
			invcontacttel : {
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
	//储备项目验证
	$('#resitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			resitemname : {
				validators : {
					notEmpty : {
						message : '项目名称不能为空'
					}
				}
			},
//			resnumber : {
//				validators : {
//					notEmpty : {
//						message : '编号不能为空'
//					},
//					regexp : {
//						regexp : /^[0-9]*$/,
//						message : '请输入整数'
//					}
//				}
//			},
			resprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			rescity : {
				validators : {
					notEmpty : {
						message : '城市不能为空'
					}
				}
			},
			restown : {
				validators : {
					notEmpty : {
						message : '区县不能为空'
					}
				}
			},
			restownship : {
				validators : {
					notEmpty : {
						message : '街道不能为空'
					}
				}
			},
			resplanarea : {
				validators : {
					notEmpty : {
						message : '规划面积不能为空'
					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resplaninvest : {
				validators : {
					notEmpty : {
						message : '计划投资不能为空'
					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			reslandarea : {
				validators : {
					notEmpty : {
						message : '征地面积不能为空'
					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resposition : {
				validators : {
					notEmpty : {
						message : '地理位置不能为空'
					}
				}
			},
			rebasic : {
				validators : {
					notEmpty : {
						message : '基本情况不能为空'
					}
				}
			},
			resbuildcontent : {
				validators : {
					notEmpty : {
						message : '规划建设内容不能为空'
					}
				}
			},
			resintentions : {
				validators : {
					notEmpty : {
						message : '开发意向情况不能为空'
					}
				}
			},
			rescompetentunit : {
				validators : {
					notEmpty : {
						message : '业主单位不能为空'
					}
				}
			},
			rescharge : {
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
			reschargetel : {
				validators : {
//					notEmpty : {
//						message : '负责人电话不能为空'
//					},
					regexp : {
						regexp : /^[^,]*$/,
						message : '请输入正确的号码'
					}
				}
			},
			resjoinway : {
				validators : {
					notEmpty : {
						message : '合作方向不能为空'
					}
				}
			},
			rescontactunit : {
				validators : {
//					notEmpty : {
//						message : '联系单位不能为空'
//					}
					regexp : {
						regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
						message : '请输入中文或字母'
					}
				}
			},
			rescontacts : {
				validators : {
//					notEmpty : {
//						message : '联系人不能为空'
//					},
					regexp : {
						regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
						message : '请输入中文或字母'
					}
				}
			},
			rescontactway : {
				validators : {
//					notEmpty : {
//						message : '联系方式不能为空'
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
	$('#invitem').bootstrapValidator('resetForm', false);
	$('#resitem').bootstrapValidator('resetForm', false);
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
	$('#regionitem').bootstrapValidator('addField', 'regcontacttel', {
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
function invAddFieldValidator(){
	$('#invitem').bootstrapValidator('addField', 'invcontact', {
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
	$('#invitem').bootstrapValidator('addField', 'invpost', {
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
	$('#invitem').bootstrapValidator('addField', 'invcontacttel', {
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
				regexp : /^([、]|[a-zA-Z]|[\u4e00-\u9fa5])+$/,
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