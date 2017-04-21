$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	//政府招商项目表单更新
	$('#invitem_update').click(function() {
		formSubmit('#invitem','invitemmanage/updateinv','ProjectLibrary/invManage.html');
	});
	//政府招商项目
	$('#invitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
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
			invnumber : {
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
			invarea : {
				validators : {
					notEmpty : {
						message : '所属地区不能为空'
					}
				}
			},
			invlocal : {
				validators : {
					notEmpty : {
						message : '所在地不能为空'
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
			invcharge : {
				validators : {
					notEmpty : {
						message : '主管单位不能为空'
					}
				}
			},
			invindustry : {
				validators : {
					notEmpty : {
						message : '所属行业不能为空'
					}
				}
			},
			invlandscale : {
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
			invplotratio : {
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
			invplanuse : {
				validators : {
					notEmpty : {
						message : '规划用途不能为空'
					}
				}
			},
			investment : {
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
			investmentall : {
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
			invexpectbuild : {
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
			invplanaddress : {
				validators : {
					notEmpty : {
						message : '规划地址不能为空'
					}
				}
			},
			invbuildcontent : {
				validators : {
					notEmpty : {
						message : '建设内容不能为空'
					}
				}
			},
			invbuildcondition : {
				validators : {
					notEmpty : {
						message : '建设条件不能为空'
					}
				}
			},
			invincentives : {
				validators : {
					notEmpty : {
						message : '优惠政策不能为空'
					}
				}
			},
			invessential : {
				validators : {
					notEmpty : {
						message : '要素分析不能为空'
					}
				}
			},
			invbenefit : {
				validators : {
					notEmpty : {
						message : '效益分析不能为空'
					}
				}
			},
			invunit : {
				validators : {
					notEmpty : {
						message : '联系单位不能为空'
					}
				}
			},
			invcontact : {
				validators : {
					notEmpty : {
						message : '联系人不能为空'
					}
				}
			},
			invcontactway : {
				validators : {
					notEmpty : {
						message : '联系方式不能为空'
					}
				}
			}
		}
	});
});