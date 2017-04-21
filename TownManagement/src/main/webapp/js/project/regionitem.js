$(document).ready(function() {
			//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	$('.datepicker').datetimepicker({
		language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        todayBtn: true,//今日按钮
        minView: 2,
        format: "yyyy-mm-dd"
	}).on('hide', function(e) {  
        // Revalidate the date when user change it  
        $('#regionitem').bootstrapValidator('revalidateField', 'regtimebegin');
        $('#regionitem').bootstrapValidator('revalidateField', 'regtimeend');
	});
	//区域性项目表单更新
	$('#regionitem_update').click(function() {
		formSubmit('#regionitem','regionmanage/updateregion','ProjectLibrary/regionManage.html');
//		$('#regionitem').data('bootstrapValidator').validate();
//		if (!$('#regionitem').data('bootstrapValidator').isValid()) {
//			return;
//			}
//		/*ie11一下不支持，formdata的方法  */
//		var formData = new FormData($("#regionitem")[0]);
//		$.ajax({
//			type : "post",
//			url : "/TownManagement/regionmanage/updateregion",
//			data : formData,
//			dataType : 'JSON',
//			cache : false,
//			processData : false,
//			contentType : false,
//			success : function(data,status) {
//				if (data.returnInfo == "success") {
//					$("#regionmodal").modal('hide');
//					setTimeout(function(){
//						$("#region-success").modal('show');
//						$('#regionManage').bootstrapTable('refresh');
//					},200);
//				} else {
//					$("#region-fail").modal('show');
//				}
//			},
//			error : function() {
//				$("#region-fail").modal('show');
//			}
//		});
	});
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
})