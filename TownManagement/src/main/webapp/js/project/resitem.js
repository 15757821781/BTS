$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	//区域性项目表单更新
	$('#resitem_update').click(function() {
		formSubmit('#resitem','resitemmanage/updateres','ProjectLibrary/resManage.html');
	});
	//储备项目验证
	$('#resitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
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
			resnumber : {
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
			resarea : {
				validators : {
					notEmpty : {
						message : '所属地区不能为空'
					}
				}
			},
			reslocal : {
				validators : {
					notEmpty : {
						message : '所在地不能为空'
					}
				}
			},
			resjoinway : {
				validators : {
					notEmpty : {
						message : '合作方式不能为空'
					}
				}
			},
			rescompetentunit : {
				validators : {
					notEmpty : {
						message : '主管单位不能为空'
					}
				}
			},
			restrade : {
				validators : {
					notEmpty : {
						message : '所属行业不能为空'
					}
				}
			},
			reslandscale : {
				validators : {
					notEmpty : {
						message : '用地规模不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resvolumerate : {
				validators : {
					notEmpty : {
						message : '容积率不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resplanuses : {
				validators : {
					notEmpty : {
						message : '规划用途不能为空'
					}
				}
			},
			resinvest : {
				validators : {
					notEmpty : {
						message : '投资强度不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resgrossassets : {
				validators : {
					notEmpty : {
						message : '投资总额不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resplantime : {
				validators : {
					notEmpty : {
						message : '预计建设期不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			resplanaddress : {
				validators : {
					notEmpty : {
						message : '规划地址不能为空'
					}
				}
			},
			resplanideas : {
				validators : {
					notEmpty : {
						message : '建设内容不能为空'
					}
				}
			},
			resbuildterm : {
				validators : {
					notEmpty : {
						message : '建设条件不能为空'
					}
				}
			},
			respolicy : {
				validators : {
					notEmpty : {
						message : '优惠政策不能为空'
					}
				}
			},
			reselement : {
				validators : {
					notEmpty : {
						message : '要素分析不能为空'
					}
				}
			},
			rescontactunit : {
				validators : {
					notEmpty : {
						message : '联系单位不能为空'
					}
				}
			},
			rescontacts : {
				validators : {
					notEmpty : {
						message : '联系人不能为空'
					}
				}
			},
			rescontactway : {
				validators : {
					notEmpty : {
						message : '联系方式不能为空'
					}
				}
			}
		}
	});
})