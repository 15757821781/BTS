$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 时间选择器初始化
	$('#resdockingtime').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 3,
        format: "yyyy-mm"
	});
	// 时间选择器初始化
	$('#resfeedbacknode').datetimepicker({
		language : "zh-CN",
		autoclose : true,// 选中之后自动隐藏日期选择框
		todayBtn : true,// 今日按钮
		startView : 4,
        minView: 2,
        format: "yyyy-mm-dd"
	});
	// 加载区县信息下拉框
    createAreaSelect("resprovince","rescity","restown");
    //储备项目表单提交
	$('#resitem_submit').click(function() {
		formSubmit('#resitem','resitemmanage/insertresitem','ProjectLibrary/resitem.html');
	});
	//储备性项目表单更新
	$('#resitem_update').click(function() {
		formSubmit('#resitem','resitemmanage/updateres','ProjectLibrary/resManage.html');
	});
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
//			rescity : {
//				validators : {
//					notEmpty : {
//						message : '城市不能为空'
//					}
//				}
//			},
//			restown : {
//				validators : {
//					notEmpty : {
//						message : '区县不能为空'
//					}
//				}
//			},
			restownship : {
				validators : {
					notEmpty : {
						message : '街道不能为空'
					}
				}
			},
			resplanarea : {
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
			resplaninvest : {
				validators : {
//					notEmpty : {
//						message : '计划投资不能为空'
//					},
					regexp : {
						regexp : /^\d+(\.\d{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			reslandarea : {
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
//			resposition : {
//				validators : {
//					notEmpty : {
//						message : '地理位置不能为空'
//					}
//				}
//			},
//			rebasic : {
//				validators : {
//					notEmpty : {
//						message : '基本情况不能为空'
//					}
//				}
//			},
//			resbuildcontent : {
//				validators : {
//					notEmpty : {
//						message : '规划建设内容不能为空'
//					}
//				}
//			},
//			resintentions : {
//				validators : {
//					notEmpty : {
//						message : '开发意向情况不能为空'
//					}
//				}
//			},
//			rescompetentunit : {
//				validators : {
//					notEmpty : {
//						message : '业主单位不能为空'
//					}
//				}
//			},
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
});
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
				regexp :/^([\u4E00-\u9FA5]|[A-Za-z])+$/,
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