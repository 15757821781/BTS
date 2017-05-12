$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
		});
	//-----------区域性项目---------------//
	$('#regbegtime').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 4,
        startView : 4,
        format: "yyyy"
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
        $('#regionitem').bootstrapValidator('revalidateField', 'regbegtime');
        $('#regionitem').bootstrapValidator('revalidateField', 'regendtime');
	});
	$('#regendtime').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 4,
        startView : 4,
        format: "yyyy"
	}).on('hide', function(e) {  
        // 当用户改变值的时候进行验证
        $('#regionitem').bootstrapValidator('revalidateField', 'regbegtime');
        $('#regionitem').bootstrapValidator('revalidateField', 'regendtime');
	});
	createAreaSelect("regprovince","regcity","regtown");
	//初始化文件上传控件
	initFileInput("regfile1","城市背景图",1);
	initFileInput("regfile2","区县背景图",1);
	initFileInput("regfile3","规划范围图",1);
	initFileInput("regfile4","规划方案图",1);
	initFileInput("regfile5","总体规划图",1);
	initFileInput("regfile6","详细规划图",1);
    $('.addel-reg').addel({
		animation: {
			duration: 100
		},
	    events: {
	        added: function (event) {
//	        	$('#comform').bootstrapValidator('addField', 'comcontact', {
//	        		validators : {
//	        			notEmpty : {
//	        				message : '联系人不能为空'
//	        			},
//						regexp : {
//							regexp : /[\u4e00-\u9fa5]/,
//							message : '请输入中文'
//						}
//	        		}
//	        	});
//	        	$('#comform').bootstrapValidator('addField', 'compost', {
//	        		validators : {
//	        			notEmpty : {
//	        				message : '职务不能为空'
//	        			},
//						regexp : {
//							regexp : /[\u4e00-\u9fa5]/,
//							message : '请输入中文'
//						}
//	        		}
//	        	});  
//	        	$('#comform').bootstrapValidator('addField', 'comcontacttel', {
//	        		validators : {
//	        			notEmpty : {
//	        				message : '联系电话不能为空'
//	        			},
//						regexp : {
//							regexp : /^[0-9]*$/,
//							message : '请输入整数'
//						}
//	        		}
//	        	});
	        }
	    }
    }).on('addel:add', function (event) {
       (event.target)[0]="111";
    });
	//------------结束--------------------//
	//-----------招商项目---------------//
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
	//------------结束-------------------//
	var loadpage="ProjectLibrary/projectEntry.html";
	//特色小镇表单提交
//	$('#featuretown_submit').click(function() {
//		formSubmit('#featuretown','featuretownmanage/insertfeaturetown',loadpage);
//	});
	//区域性项目表单提交
	$('#regitem_submit').click(function() {
		formSubmit('#regionitem','regionmanage/insertregion',loadpage);
	});
	//招商项目表单提交
	$('#invitem_submit').click(function() {
		formSubmit('#invitem','invitemmanage/insertinvitem',loadpage);
	});
	//储备项目表单提交
//	$('#resitem_submit').click(function() {
//		formSubmit('#resitem','resitemmanage/insertresitem',loadpage);
//	});
	//区域性项目验证
	$('#regionitem').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			regname : {
				validators : {
					notEmpty : {
						message : '项目名称不能为空'
					}
				}
			},
			regnumber : {
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
			regarea : {
				validators : {
					notEmpty : {
						message : '所属地区不能为空'
					}
				}
			},
			regproperty : {
				validators : {
					notEmpty : {
						message : '项目属性不能为空'
					}
				}
			},
			regschedule : {
				validators : {
					notEmpty : {
						message : '当前进度不能为空'
					}
				}
			},
			regposition : {
				validators : {
					notEmpty : {
						message : '地理位置不能为空'
					}
				}
			},
			regdeveloper : {
				validators : {
					notEmpty : {
						message : '开发主体不能为空'
					}
				}
			},
			regcharge : {
				validators : {
					notEmpty : {
						message : '开发负责人不能为空'
					}
				}
			},
			regchargetel : {
				validators : {
					notEmpty : {
						message : '负责人电话不能为空'
					}
				}
			},
			regpartner : {
				validators : {
					notEmpty : {
						message : '合作单位不能为空'
					}
				}
			},
			regpartcharge : {
				validators : {
					notEmpty : {
						message : '负责人不能为空'
					}
				}
			},
			regparttel : {
				validators : {
					notEmpty : {
						message : '负责人电话不能为空'
					}
				}
			},
			regterms : {
				validators : {
					notEmpty : {
						message : '合作条件不能为空'
					}
				}
			},
			regtimebegin : {
				validators : {
					notEmpty : {
						message : '合作时间不能为空'
					},  
					callback: {
						message: '开始日期不能大于结束日期',
						callback:function(value, validator,$field,options){
							var end = $('#regionitem').find("input[name='regtimeend']").val();
							return value<=end;
						}
					}
				}
			},
			regtimeend : {
				validators : {
					notEmpty : {
						message : '合作时间不能为空'
					},  
					callback: {
						message: '结束日期不能小于开始日期',
						callback:function(value, validator,$field){
							var begin = $('#regionitem').find("input[name='regtimebegin']").val();
							return value>=begin;
						}
					}
				}
			},
			regprotocol : {
				validators : {
					notEmpty : {
						message : '协议情况不能为空'
					}
				}
			},
			regadvisory : {
				validators : {
					notEmpty : {
						message : '咨询顾问单位不能为空'
					}
				}
			},
			regadvisorypro : {
				validators : {
					notEmpty : {
						message : '协议情况不能为空'
					}
				}
			},
			regplanarea : {
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
			regplaninvest : {
				validators : {
					notEmpty : {
						message : '计划投资不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			reglandarea : {
				validators : {
					notEmpty : {
						message : '征地面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regfirstplanarea : {
				validators : {
					notEmpty : {
						message : '一期征地面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regfirstplaninvest : {
				validators : {
					notEmpty : {
						message : '一期计划投资不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regfirstlandarea : {
				validators : {
					notEmpty : {
						message : '征地面积不能为空'
					},
					regexp : {
						regexp : /^[0-9]+(.[0-9]{1,3})?$/,
						message : '请输入最多3位小数的数字'
					}
				}
			},
			regprimeindustry : {
				validators : {
					notEmpty : {
						message : '主导产业不能为空'
					}
				}
			},
			regplancontent : {
				validators : {
					notEmpty : {
						message : '规划内容不能为空'
					}
				}
			},
			regstatemap : {
				validators : {
					notEmpty : {
						message : '现状地图不能为空'
					}
				}
			},
			regplanscheme : {
				validators : {
					notEmpty : {
						message : '规划方案不能为空'
					}
				}
			},
			regcontacts : {
				validators : {
					notEmpty : {
						message : '对接人不能为空'
					}
				}
			},
			regplanmap : {
				validators : {
					notEmpty : {
						message : '规划图不能为空'
					}
				}
			}
		}
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
					notEmpty : {
						message : '联系人不能为空'
					}
				}
			},
			invpost : {
				validators : {
					notEmpty : {
						message : '职务不能为空'
					}
				}
			},
			invcontacttel : {
				validators : {
					notEmpty : {
						message : '联系电话不能为空'
					}
				}
			}
		}
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
});