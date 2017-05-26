$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	// 加载区县信息下拉框
    createAreaSelect("invprovince","invcity","invtown");
    
//		
	//初始化文件上传控件
	initFileInput("invfile1","城市背景图",1);
	initFileInput("invfile2","区县背景图",1);
	initFileInput("invfile3","规划范围图",1);
	initFileInput("invfile4","规划方案图",1);
	initFileInput("invfile5","总体规划图",1);
	initFileInput("invfile6","详细规划图",1);
	//表单提交
	$('#invitem_submit').click(function() {
		formSubmit('#invitem','invitemmanage/queryinvinfo','ProjectLibrary/invitem.html');
	});
	//政府招商项目表单更新
	$('#invitem_update').click(function() {
		formSubmit('#invitem','invitemmanage/updateinv','ProjectLibrary/invManage.html');
	});
	// 动态增减行初始化
	 $('.addel').addel({
			animation: {
				duration: 100
			},
		    events: {
		        added: function (event) {
		        	$('#invitem').bootstrapValidator('addField', 'invcontact', {
		        		validators : {
//		        			notEmpty : {
//		        				message : '联系人不能为空'
//		        			},
							regexp : {
								regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
								message : '请输入中文或字母'
							}
		        		}
		        	});
		        	$('#invitem').bootstrapValidator('addField', 'invpost', {
		        		validators : {
//		        			notEmpty : {
//		        				message : '职务不能为空'
//		        			},
							regexp : {
								regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
								message : '请输入中文或字母'
							}
		        		}
		        	});  
		        	$('#invitem').bootstrapValidator('addField', 'invcontacttel', {
		        		validators : {
//		        			notEmpty : {
//		        				message : '联系电话不能为空'
//		        			},
							regexp : {
								regexp : /^[^,]*$/,
								message : '请输入正确的号码'
							}
		        		}
		        	});
		        }
		    }
	    });
		setTimeout(function() {
			validatorInvForm();
		}, 500);
});
function validatorInvForm(){
	//政府招商项目
	$('#invitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
//			valid : 'glyphicon glyphicon-ok',
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
			invprovince : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			invcity : {
				validators : {
					notEmpty : {
						message : '城市不能为空'
					}
				}
			},
			invtown : {
				validators : {
					notEmpty : {
						message : '区县不能为空'
					}
				}
			},
			invtownship : {
				validators : {
					notEmpty : {
						message : '街道不能为空'
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
			invjoinway : {
				validators : {
					notEmpty : {
						message : '合作方式不能为空'
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
						message : '土地用途不能为空'
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
			invcontact : {
				validators : {
//					notEmpty : {
//						message : '联系人不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			invpost : {
				validators : {
//					notEmpty : {
//						message : '职务不能为空'
//					},
					regexp : {
						regexp : /^[a-zA-Z\u4e00-\u9fa5]+$/,
						message : '请输入中文或字母'
					}
				}
			},
			invcontacttel : {
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
	$('#invitem').bootstrapValidator('resetForm', false);
}