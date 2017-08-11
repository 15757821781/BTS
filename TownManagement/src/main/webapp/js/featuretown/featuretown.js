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
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
        $('#featuretown').bootstrapValidator('revalidateField', 'fearegtime');
        $('#featuretown').bootstrapValidator('revalidateField', 'feaendtime');
	});
	// 动态增减行初始化
    $('.addel').addel({
		animation: {
			duration: 100
		},
	    events: {
	        added: function (event) {
	        	feaAddFieldValidator();
	        }
	    }
    });
	//初始化省市县联动
	createAreaSelect("feaprovince","feacity","featown");
	//初始化文件上传控件
	initFileInput("feafile1","城市背景图",1);
	initFileInput("feafile2","区县背景图",1);
	initFileInput("feafile3","规划范围图",1);
	initFileInput("feafile4","规划方案图",1);
	initFileInput("feafile5","总体规划图",1);
	initFileInput("feafile6","详细规划图",1);
	
	//特色小镇表单提交
	$('#featuretown_submit').click(function() {
		formSubmit('#featuretown','featuretownmanage/insertfeaturetown','FeatureTown/featuretown.html');
	});
	$('#featuretown_update').click(function() {
		formSubmit('#featuretown','featuretownmanage/updatefeaturetown','FeatureTown/featuretownManage.html');
	});
	//特色小镇表单验证
	$('#featuretown').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			feaname : {
				validators : {
					notEmpty : {
						message : '小镇名不能为空'
					}
				}
			},
			fealevel : {
				validators : {
					notEmpty : {
						message : '小镇等级不能为空'
					}
				}
			},
			feabatch : {
				validators : {
					notEmpty : {
						message : '创建批次不能为空'
					}
				}
			},
			fearelation : {
				validators : {
					notEmpty : {
						message : '关系情况不能为空'
					}
				}
			},
			feaprovince : {
				validators : {
					notEmpty : {
						message : '所在省份不能为空'
					}
				}
			},
//			feagenre : {
//				validators : {
//					notEmpty : {
//						message : '乡镇街道不能为空'
//					}
//				}
//			},
			feaindustry : {
				validators : {
					notEmpty : {
						message : '产业类别不能为空'
					}
				}
			},
			feaplaninvest : {
				validators : {
//					notEmpty : {
//						message : '投资金额不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			feaschedule : {
//				validators : {
//					notEmpty : {
//						message : '投资进度不能为空'
//					}
//				}
//			},
//			featarget : {
//				validators : {
//					notEmpty : {
//						message : '规划目标不能为空'
//					}
//				}
//			},
			feaplanarea : {
				validators : {
//					notEmpty : {
//						message : '规划面积不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
//			feaplancontent : {
//				validators : {
//					notEmpty : {
//						message : '规划内容不能为空'
//					}
//				}
//			},
			feacoreindustry : {
				validators : {
					notEmpty : {
						message : '核心产业和企业不能为空'
					}
				}
			},
//			fealeadcom : {
//				validators : {
//					notEmpty : {
//						message : '牵头开发单位不能为空'
//					}
//				}
//			},
			fealeadname : {
				validators : {
//					notEmpty : {
//						message : '负责人名字不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			fealeadtel : {
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
			feacooperate : {
				validators : {
					notEmpty : {
						message : '合作开发情况不能为空'
					}
				}
			},
//			feapartner : {
//				validators : {
//					notEmpty : {
//						message : '合作开发单位不能为空'
//					}
//				}
//			},
			feapartname : {
				validators : {
//					notEmpty : {
//						message : '负责人名字不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			feaparttel : {
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
//			feapartway : {
//				validators : {
//					notEmpty : {
//						message : '合作方式不能为空'
//					}
//				}
//			},
			feapartmoney : {
				validators : {
//					notEmpty : {
//						message : '合作投金额不能为空'
//					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			fearegtime : {
				validators : {
//					notEmpty : {
//						message : '合作开始时间不能为空'
//					},  
					callback: {
						message: '开始日期不能大于结束日期',
						callback:function(value, validator,$field,options){
							var end = $('#featuretown').find("input[name='feaendtime']").val();
							return value<=end;
						}
					}
				}
			},
			feaendtime : {
				validators : {
//					notEmpty : {
//						message : '合作结束时间不能为空'
//					},  
					callback: {
						message: '结束日期不能小于开始日期',
						callback:function(value, validator,$field){
							var begin = $('#featuretown').find("input[name='fearegtime']").val();
							return value>=begin;
						}
					}
				}
			},
//			feapartconten : {
//				validators : {
//					notEmpty : {
//						message : '合作内容不能为空'
//					}
//				}
//			},
			feacontact : {
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
			feapost : {
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
			feacontacttel : {
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
});
//
function readyOnly(v) {
	if(v.value=="0"){
		$(".feapart").attr("disabled","disabled");
	}else{
		$(".feapart").removeAttr("disabled");
	}
}
function feaAddFieldValidator(){
	$('#featuretown').bootstrapValidator('addField', 'feacontact', {
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
	$('#featuretown').bootstrapValidator('addField', 'feapost', {
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
	$('#featuretown').bootstrapValidator('addField', 'feacontacttel', {
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