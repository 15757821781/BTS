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
        minView: 4,
        format: "yyyy"
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
	$('#basicform_update').click(function() {
		formSubmit('#basicform','citymanage/updateCityInfo',"ProjectPlan/citycaseManage.html");
	});
	$('#basicform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			//			valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			countryname : {
				validators : {
//					notEmpty : {
//						message : '县市名称不能为空'
//					}
				}
			},
			countryyear : {
				validators : {
//					notEmpty : {
//						message : '统计年度不能为空'
//					}
				}
			},
			countryprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			countrycity : {
				validators : {
					notEmpty : {
						message : '城市不能为空'
					}
				}
			},
			countryposition : {
				validators : {
					notEmpty : {
						message : '地理位置不能为空'
					}
				}
			},	
			countryarea : {
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
			countryallpeople : {
				validators : {
					notEmpty : {
						message : '全县总人口不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countrygdp : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryrate : {
				validators : {
					regexp : {
						regexp : /^\-?([0-9]+(.[0-9]{1,3})?)$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryfinance : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryrates : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryfisheries : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryindustry : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryinvest : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryratess : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countryincome : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			countrytownincome : {
				validators : {
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			}
		}
	});
	
});

function changeTab(){
	$('#BasicSituation_tab').removeClass("active");
	$('#BasicSituation').removeClass("active in");
	$('#ExploreSituation_tab').addClass("active");
	$('#ExploreSituation').addClass("active in");
}