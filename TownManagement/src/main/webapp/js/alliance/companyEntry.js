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
		$('#comform').bootstrapValidator('revalidateField', 'comestablish');
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
		$('#comform').bootstrapValidator('revalidateField', 'comdatayear');
	});
	//初始化文件上传控件
	initFileInput("comcertificatepic","选择",5);
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
	//表单提交
	$('#comentry_submit').click(function() {
		formSubmit('#comform','commanage/insertComInfo','Alliance/companyEntry.html');
	});
	$('#comentry_update').click(function() {
		formSubmit('#comform','commanage/updateComInfo','Alliance/companyManage.html');
	});
	setTimeout(function() {
		validatorComForm();
	}, 500);
});
var comtype = [ {
	value : "1",
	name : "央企",
	parid : "1"
}, {
	value : "2",
	name : "省属",
	parid : "1"
}, {
	value : "3",
	name : "市属",
	parid : "1"
}, {
	value : "4",
	name : "区县属",
	parid : "1"
}, {
	value : "1",
	name : "经济合作社",
	parid : "2"
}, {
	value : "2",
	name : "股份经济合作社",
	parid : "2"
}, {
	value : "1",
	name : "股份制企业",
	parid : "3"
}, {
	value : "2",
	name : "私营企业",
	parid : "3"
}, {
	value : "3",
	name : "联营企业",
	parid : "3"
}, {
	value : "1",
	name : "外资独资",
	parid : "4"
}, {
	value : "2",
	name : "中外合资",
	parid : "4"
}, {
	value : "3",
	name : "中外合作",
	parid : "4"
} ]
function typeChanage(v){
	var id = v.value;
	$("#comtype option").remove();
	$("#comtype").append("<option></option>");
	$.each(comtype, function(i, item) {
		if(item.parid==id){
			$("#comtype").append("<option value="+item.value+">"+item.name+"</option>")
		}
	});
	$("#comtype").selectpicker('refresh');
}
function validatorComForm(){
	$('#comform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
	//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			comname : {
				validators : {
					notEmpty : {
						message : '单位名称不能为空'
					}
				}
			},
			comnature : {
				validators : {
					notEmpty : {
						message : '单位性质不能为空'
					}
				}
			},
			comcategory : {
				validators : {
					notEmpty : {
						message : '单位类别不能为空'
					}
				}
			},
			comtype : {
				validators : {
					notEmpty : {
						message : '单位类型不能为空'
					}
				}
			},
			combustype : {
				validators : {
					notEmpty : {
						message : '业务类型不能为空'
					}
				}
			},
			comlisted : {
				validators : {
					notEmpty : {
						message : '上市情况不能为空'
					}
				}
			},
//			comstockcode : {
//				validators : {
//					notEmpty : {
//						message : '股票代码不能为空'
//					}
//				}
//			},
			comrelation : {
				validators : {
					notEmpty : {
						message : '关系情况不能为空'
					}
				}
			},
			comworldfive : {
				validators : {
					notEmpty : {
						message : '该信息不能为空'
					}
				}
			},
			comcountryfive : {
				validators : {
					notEmpty : {
						message : '该信息不能为空'
					}
				}
			},
			comprivatefive : {
				validators : {
					notEmpty : {
						message : '该信息不能为空'
					}
				}
			},
			comrepresent : {
				validators : {
					notEmpty : {
						message : '法人代表不能为空'
					}
				}
			},
			comcapital : {
				validators : {
					notEmpty : {
						message : '注册资本不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			comcapitalunit : {
				validators : {
					notEmpty : {
						message : '货币单位不能为空'
					}
				}
			},
			comestablish : {
				validators : {
					notEmpty : {
						message : '成立时间不能为空'
					}
				}
			},
			comprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			comaddress : {
				validators : {
					notEmpty : {
						message : '具体地址不能为空'
					}
				}
			},
			comoffice : {
				validators : {
					notEmpty : {
						message : '办公地点不能为空'
					}
				}
			},
//			comshareholder : {
//				validators : {
//					notEmpty : {
//						message : '股东情况不能为空'
//					}
//				}
//			},
			comindustrytype : {
				validators : {
					notEmpty : {
						message : '产业类别不能为空'
					}
				}
			},
			commajorindustry : {
				validators : {
					notEmpty : {
						message : '主要产业不能为空'
					}
				}
			},
//			comcreditcode : {
//				validators : {
//					notEmpty : {
//						message : '信用代码不能为空'
//					}
//				}
//			},
			comscope : {
				validators : {
					notEmpty : {
						message : '经营范围不能为空'
					}
				}
			},
			comabstract : {
				validators : {
					notEmpty : {
						message : '单位简介不能为空'
					}
				}
			},
//			comhonor : {
//				validators : {
//					notEmpty : {
//						message : '单位荣誉不能为空'
//					}
//				}
//			},
			comdatayear : {
				validators : {
//					notEmpty : {
//						message : '数据年度不能为空'
//					}
				}
			},
			comlassets : {
				validators : {
//					notEmpty : {
//						message : '资产总额不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			comlassetsunit : {
//				validators : {
//					notEmpty : {
//						message : '请选择货币单位'
//					}
//				}
//			},
			comliabilities : {
				validators : {
//					notEmpty : {
//						message : '负债总额不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			comliabunit : {
//				validators : {
//					notEmpty : {
//						message : '请选择货币单位'
//					}
//				}
//			},
			comincomeyear : {
				validators : {
//					notEmpty : {
//						message : '营业收入不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			cominyearunit : {
//				validators : {
//					notEmpty : {
//						message : '请选择货币单位'
//					}
//				}
//			},
			comnetprofiyear : {
				validators : {
//					notEmpty : {
//						message : '净利润不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			comnetyearunit : {
//				validators : {
//					notEmpty : {
//						message : '请选择货币单位'
//					}
//				}
//			},
			comtaxesyear : {
				validators : {
//					notEmpty : {
//						message : '纳税额不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			comtaxyearunit : {
//				validators : {
//					notEmpty : {
//						message : '请选择货币单位'
//					}
//				}
//			},
			comdevelop : {
				validators : {
//					notEmpty : {
//						message : '发展方向不能为空'
//					}
				}
			},
//			comindustry : {
//				validators : {
//					notEmpty : {
//						message : '投资偏好不能为空'
//					}
//				}
//			},
//			cominvestment : {
//				validators : {
//					notEmpty : {
//						message : '投资规模不能为空'
//					}
//				}
//			},
			comcontact : {
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
			compost : {
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
			comcontacttel : {
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
	$('#comform').bootstrapValidator('resetForm', false);
}
function comAddFieldValidator(){
   	$('#comform').bootstrapValidator('addField', 'comcontact', {
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
	$('#comform').bootstrapValidator('addField', 'compost', {
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
	$('#comform').bootstrapValidator('addField', 'comcontacttel', {
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