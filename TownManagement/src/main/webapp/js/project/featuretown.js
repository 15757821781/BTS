$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	//特色小镇表单提交
	$('#featuretown_update').click(function() {
		formSubmit('#featuretown','featuretownmanage/updatefeaturetown','ProjectLibrary/featuretownManage.html');
	});
	//特色小镇表单验证
	$('#featuretown').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				featuretownname : {
					validators : {
						notEmpty : {
							message : '小镇名不能为空'
							}
					}
			},
			featuretownnumber : {
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
			attributionarea : {
				validators : {
					notEmpty : {
						message : '所属地区不能为空'
						}
					}
			},
			townlevel : {
				validators : {
					notEmpty : {
						message : '小镇等级不能为空'
						}
					}
			},
			foundbatch : {
				 validators : {
					notEmpty : {
						message : '创建批次不能为空'
						}
				}
			},
			towngenre : {
				validators : {
					notEmpty : {
						message : '小镇类型不能为空'
						}
					}
			},
			position : {
				validators : {
					notEmpty : {
						message : '地理位置不能为空'
						}
					}
			},
			developer : {
				validators : {
					notEmpty : {
						message : '开发主体不能为空'
						}
					}
			},
			developername : {
				validators : {
					notEmpty : {
						message : '负责人名字不能为空'
						}
					}
			},
			developertel : {
				validators : {
					notEmpty : {
						message : '负责人电话不能为空'
						},
						regexp : {
							regexp : /^[0-9]*$/,
							message : '请输入整数'
							}
					}
			},
			partner : {
				validators : {
					notEmpty : {
						message : '合作单位不能为空'
						}
					}
			},partnername : {
				validators : {
					notEmpty : {
						message : '负责人名字不能为空'
					}
				}
			},partnertel : {
				validators : {
					notEmpty : {
						message : '负责人电话不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '请输入整数'
					}
				}
			},cooperatecondition : {
				validators : {
					notEmpty : {
						message : '合作条件不能为空'
					}
				}
			},
			functions : {
				validators : {
					notEmpty : {
						message : '功能定位不能为空'
					}
				}
			},
			industrygenre : {
				validators : {
					notEmpty : {
						message : '产业类别不能为空'
					}
				}
			},
			planarea : {
				validators : {
					notEmpty : {
						message : '规划面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			planinvest : {
				validators : {
					notEmpty : {
						message : '计划总投资不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			schedule : {
				validators : {
					notEmpty : {
						message : '当前进度不能为空'
					}
				}
			},
			plancontent : {
				validators : {
					notEmpty : {
						message : '规划内容不能为空'
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
			statemap : {
				validators : {
					notEmpty : {
						message : '现状地图不能为空'
					}
				}
			},
			programme : {
				validators : {
					notEmpty : {
						message : '规划方案不能为空'
					}
				}
			},
			contacts : {
				validators : {
					notEmpty : {
						message : '对接人不能为空'
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
			planmap : {
				validators : {
					notEmpty : {
						message : '规划图不能为空'
					}
				}
			}
		}
	});
})