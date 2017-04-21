$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
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
			townlevel : {
				validators : {
					notEmpty : {
						message : '中心镇等级不能为空'
					}
				}
			},
			attributionarea : {
				validators : {
					notEmpty : {
						message : '所属地区不能为空'
					}
				}
			},
			cooperation : {
				validators : {
					notEmpty : {
						message : '合作情況不能为空'
					}
				}
			},
			townarea : {
				validators : {
					notEmpty : {
						message : '镇域面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			builtuparea : {
				validators : {
					notEmpty : {
						message : '建成区面积不能为空'
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
						message : '镇域人口不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			floatepopulation : {
				validators : {
					notEmpty : {
						message : '流动人口不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
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
						message : '区县级财政收入不能为空'
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
						message : '镇级财政总收入不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
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
			citypilot : {
				validators : {
					notEmpty : {
						message : '小城市试点不能为空'
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
			partycommittee : {
				validators : {
					notEmpty : {
						message : '镇党委书记不能为空'
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
						message : '现状产业不能为空'
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
			honorarytitle : {
				validators : {
					notEmpty : {
						message : '荣誉称号不能为空'
					}
				}
			},
			historyculture : {
				validators : {
					notEmpty : {
						message : '历史文化及名人不能为空'
					}
				}
			},
			statuspic : {
				validators : {
					notEmpty : {
						message : '现状地图不能为空'
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
			docking : {
				validators : {
					notEmpty : {
						message : '对接人不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
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
})