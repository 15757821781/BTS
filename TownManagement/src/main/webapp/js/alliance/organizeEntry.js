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
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
		$('#orgform').bootstrapValidator('revalidateField', 'orgestablish');
	});
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
	//表单提交
	$('#orgentry_submit').click(function() {
		formSubmit('#orgform','orgmanage/insertOrgInfo','Alliance/organizeEntry.html');
	});
	$('#orgentry_update').click(function() {
		formSubmit('#orgform','orgmanage/updateOrgInfo','Alliance/organizeManage.html');
	});
	//表单验证
	$('#orgform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			orgname : {
				validators : {
					notEmpty : {
						message : '单位名称不能为空'
					}
				}
			},
			orgnature : {
				validators : {
					notEmpty : {
						message : '单位性质不能为空'
					}
				}
			},
			orgcategory : {
				validators : {
					notEmpty : {
						message : '单位类别不能为空'
					}
				}
			},
			orgtype : {
				validators : {
					notEmpty : {
						message : '单位类型不能为空'
					}
				}
			},
			orgsponsor : {
				validators : {
					notEmpty : {
						message : '发起人不能为空'
					}
				}
			},
//			orgrelation : {
//				validators : {
//					notEmpty : {
//						message : '关系情况不能为空'
//					}
//				}
//			},
			orgcompetent : {
				validators : {
					notEmpty : {
						message : '主管部门不能为空'
					}
				}
			},
			orgrepresent : {
				validators : {
//					notEmpty : {
//						message : '法人代表不能为空'
//					}
				}
			},
			orgprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
//			orgcity : {
//				validators : {
//					notEmpty : {
//						message : '城市不能为空'
//					}
//				}
//			},
//			orgtown : {
//				validators : {
//					notEmpty : {
//						message : '区县不能为空'
//					}
//				}
//			},
//			orgaddress : {
//				validators : {
//					notEmpty : {
//						message : '具体地址不能为空'
//					}
//				}
//			},
			orgestablish : {
				validators : {
//					notEmpty : {
//						message : '成立时间不能为空'
//					}
				}
			},
//			orgoffice : {
//				validators : {
//					notEmpty : {
//						message : '办公地点不能为空'
//					}
//				}
//			},
			orgcreditcode: {
				validators : {
//					notEmpty : {
//						message : '信用代码不能为空'
//					}
				}
			},
			orgscope : {
				validators : {
					notEmpty : {
						message : '业务范围不能为空'
					}
				}
			},
			orgadvantaget: {
				validators : {
					notEmpty : {
						message : '机构优势不能为空'
					}
				}
			},
			orgcontact: {
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
			orgpost : {
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
			orgcontacttel : {
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
var orgcategory = [ {
	value : "1",
	name : "研究机构",
	parid : "1"
}, {
	value : "2",
	name : "高校",
	parid : "1"
}, {
	value : "3",
	name : "社会团体",
	parid : "2"
}, {
	value : "4",
	name : "民办非企业单位",
	parid : "2"
}, {
	value : "5",
	name : "基金会",
	parid : "2"
} ];
var orgtype = [ {
	value : "1",
	name : "部属",
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
	value : "5",
	name : "行业协会",
	parid : "2"
},{
	value : "6",
	name : "综合协会",
	parid : "2"
},{
	value : "7",
	name : "民非单位",
	parid : "2"
},{
	value : "8",
	name : "公募基金会",
	parid : "2"
},{
	value : "9",
	name : "非公募基金会",
	parid : "2"
} ]
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
function orgAddFieldValidator(){
	$('#orgform').bootstrapValidator('addField', 'orgcontact', {
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
	$('#orgform').bootstrapValidator('addField', 'orgpost', {
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
	$('#orgform').bootstrapValidator('addField', 'orgcontacttel', {
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