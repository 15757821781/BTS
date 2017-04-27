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
		$('#townform').bootstrapValidator('revalidateField', 'towndatayear');
	});
	//初始化文件上传控件
	initFileInput("statuspic");
	// 加载省市县信息下拉框
	createAreaSelect("sys_province","sys_city","sys_town");
	// 加载气候下拉框
	selectCreate("weather","conditionmanage/queryClimate");
	// 加载地形下拉框
	selectCreate("terrain","conditionmanage/queryTerrain");
	// 加载优势产业下拉框
	selectCreate("currentindustry","conditionmanage/queryAdvIndustry");
	// 加载优势产业下拉框
	selectCreate("industrialorientation","conditionmanage/queryDirIndustry");
	$('#townform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			centertownname : {
				validators : {
					notEmpty : {
						message : '小镇名不能为空'
					}
				}
			},
			number : {
				validators : {
					notEmpty : {
						message : '编号不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			citypilot : {
				validators : {
					notEmpty : {
						message : '小城市试点不能为空'
					}
				}
			},
			townlevel : {
				validators : {
					notEmpty : {
						message : '等级不能为空'
					}
				}
			},
			citypilot : {
				validators : {
					notEmpty : {
						message : '城市试点不能为空'
					}
				}
			},
			townfeature : {
				validators : {
					notEmpty : {
						message : '特色小镇不能为空'
					}
				}
			},
			sys_province : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
//			towncity : {
//				validators : {
//					notEmpty : {
//						message : '市区不能为空'
//					}
//				}
//			},
//			towntown : {
//				validators : {
//					notEmpty : {
//						message : '乡镇不能为空'
//					}
//				}
//			},
			cooperation : {
				validators : {
					notEmpty : {
						message : '关系情况不能为空'
					}
				}
			},
			towndatayear : {
				validators : {
					notEmpty : {
						message : '数据年度不能为空'
					}
				}
			},
			hundredcounties : {
				validators : {
					notEmpty : {
						message : '全国百强区县不能为空'
					}
				}
			},
			countygdp : {
				validators : {
					notEmpty : {
						message : '区县级GDP不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countyrevenue : {
				validators : {
					notEmpty : {
						message : '区县财政收入不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			townpopulation : {
				validators : {
					notEmpty : {
						message : '区县人口不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			townpgdi : {
				validators : {
					notEmpty : {
						message : '人均可支配收入不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			townarea : {
				validators : {
					notEmpty : {
						message : '行政面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			community : {
				validators : {
					notEmpty : {
						message : '社区数量不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			adminvillage : {
				validators : {
					notEmpty : {
						message : '行政村数量不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			townlocalgdp : {
				validators : {
					notEmpty : {
						message : '地方GDP不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			townrevenue : {
				validators : {
					notEmpty : {
						message : '财政总收入不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			totalpopulation : {
				validators : {
					notEmpty : {
						message : '总人口不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			farmingoutvalue : {
				validators : {
					notEmpty : {
						message : '农业总产值不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			industryoutvalue : {
				validators : {
					notEmpty : {
						message : '工业总产值不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			serviceoutvalue : {
				validators : {
					notEmpty : {
						message : '服务业总产值不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			weather : {
				validators : {
					notEmpty : {
						message : '气候条件不能为空'
					}
				}
			},
			terrain : {
				validators : {
					notEmpty : {
						message : '地形条件不能为空'
					}
				}
			},
			traffic : {
				validators : {
					notEmpty : {
						message : '交通条件不能为空'
					}
				}
			},
			currentindustry : {
				validators : {
					notEmpty : {
						message : '优势产业不能为空'
					}
				}
			},
			industrialorientation : {
				validators : {
					notEmpty : {
						message : '产业方向不能为空'
					}
				}
			},
			historyculture : {
				validators : {
					notEmpty : {
						message : '历史文化和旅游资源不能为空'
					}
				}
			},
			honorarytitle : {
				validators : {
					notEmpty : {
						message : '荣誉称号不能为空'
					}
				}
			},
			partycommittee : {
				validators : {
					notEmpty : {
						message : '党委书记不能为空'
					}
				}
			},
			committelnumber : {
				validators : {
					notEmpty : {
						message : '联系电话不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			committel : {
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			mayor : {
				validators : {
					notEmpty : {
						message : '镇长不能为空'
					}
				}
			},
			mayortelnumber : {
				validators : {
					notEmpty : {
						message : '联系电话不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			mayortel : {
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			contacts : {
				validators : {
					notEmpty : {
						message : '联系人不能为空'
					}
				}
			},
			post : {
				validators : {
					notEmpty : {
						message : '职务不能为空'
					}
				}
			},
			contactstel : {
				validators : {
					notEmpty : {
						message : '联系电话不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},
			statuspic : {
				validators : {
					notEmpty : {
						message : '区位图不能为空'
					}
				}
			},
			planpic : {
				validators : {
					notEmpty : {
						message : '规划图不能为空'
					}
				}
			},
			creator : {
				validators : {
					notEmpty : {
						message : '规划图不能为空'
					}
				}
			}
		}
	});
	//表单提交
	$('#townentry_submit').click(function() {
		formSubmit('#townform','townmanage/inserttowninfo','town/townEntry.html');
	});
	$('#townentry_update').click(function() {
		formSubmit('#townform','townmanage/updatetowninfo','town/townManage.html');
	});
});
//初始化fileinput控件
function initFileInput(ctrlName) {
	var control = $('#' + ctrlName);
	control.fileinput({
		uploadUrl: '/TownManagement/townmanage/uploadStatuspic', 
		uploadAsync:true,
		dropZoneEnabled: false,//是否显示拖拽区域
		language : 'zh', //设置语言
		allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ],//接收的文件后缀
		showUpload : true, //是否显示上传按钮
		showRemove: false,//是否显示删除按钮  
		showCaption: true,//是否显示输入框
		maxFileCount: 3,
		enctype: 'multipart/form-data',
		layoutTemplates:{
			actionUpload: '',
		},
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
	});
}