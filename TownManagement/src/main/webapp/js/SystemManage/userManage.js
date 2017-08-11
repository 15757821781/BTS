$(document).ready(function() {
	//初始化下拉框
	$('.selectpicker').selectpicker({
		noneSelectedText : "请选择"
	});
	$('#usermanagetable').bootstrapTable({
		url : '/TownManagement/systemmanage/queryUserList',
		method : 'get', //请求方式（*）
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式
		queryParamsType : "undefined",
		queryParams : queryParams,//传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		search : true,
		showToggle : true,
		showRefresh : true,//显示刷新按钮
		strictSearch : true,//设置为 true启用 全匹配搜索，否则为模糊搜索
		clickToSelect : true, //是否启用点击选中行
		height : 650, //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
		//cardView : false, //是否显示详细视图
		//detailView : true, //是否显示父子表
		columns : [ {
			field : 'number',
			title : '用户编号',
			align : 'center',
		}, {
			field : 'name',
			title : '用户名称',
			align : 'center',
		}, {
			field : 'account',
			title : '用户账号',
			align : 'center',
		}, {
			field : 'rolename',
			title : '用户角色',
			align : 'center',
		}, {
			field : 'userdata',
			title : '数据权限',
			align : 'center',
			formatter:function(value,row,index){
            	if(value==1){
            		return "部分阅读权限";
            	}else if(value==2){
            		return "完全阅读权限";
            	}else if(value==3){
            		return "编辑权限";
            	}
            }
		}, {
            title : '操作',
            field : 'userid',
            align : 'center',
            formatter:function(value,row,index){
            	var query = '<a href="javascript:void(0)" onclick="queryDetail('+row.userid+')">查看</a>';
            	var update = '<a href="javascript:void(0)" onclick="updateInfo('+row.userid+')">修改</a>';
            	return query+"&nbsp"+update;
            }
		} ]
	});
	CreateCombo("roleid","systemmanage/queryRoleForCombo");
	// 添加用户表单验证
	validatorUserForm();
	//新增弹出框
	$('#import_userinfo').click(function() {
		$("#userfieldset").removeAttr("disabled");
		$("#account").removeAttr("disabled");
		$('#userform')[0].reset();
		$('#userform').bootstrapValidator('resetForm', false);
		$("#importuser_modal").modal('show');
		$("#user_submit").show();
		$("#user_update").hide();
	});
	//表单提交
	$('#user_submit').click(function() {
		formSubmit('#userform','systemmanage/insertUserInfo','',function(){
			$("#importuser_modal").modal('hide');
			$('#usermanagetable').bootstrapTable('refresh');
		});
	});
	$('#user_update').click(function() {
		formSubmit('#userform','systemmanage/updateUserInfo','',function(){
			$("#importuser_modal").modal('hide');
			$('#usermanagetable').bootstrapTable('refresh');
		});
	});
})
//查询方法
function queryParams(params){
	if (params.searchText == undefined) {
		params.searchText = "";
	}
	var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.pageSize, //页面大小
		offset : params.offset, //页码
		maxrows : params.limit,
		pageindex : params.pageNumber,
		search : encodeURI(params.searchText)
	};
	return temp;
}
//展示详情modal
function queryDetail(id) {
	$("#userfieldset").removeAttr("disabled");
	$('#userform').bootstrapValidator('resetForm', false);
	tk.ajax({
		url : "/TownManagement/systemmanage/queryUserDetail",
		data : {"userid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#userform',data);
			$("#importuser_modal").modal('show');
			$('#userfieldset').attr("disabled","disabled");
			$("#user_submit").hide();
			$("#user_update").hide();
		}
	});
}
//展示修改界面
function updateInfo(id){
	$("#userfieldset").removeAttr("disabled");
	$('#userform').bootstrapValidator('resetForm', false);
	tk.ajax({
		url : "/TownManagement/systemmanage/queryUserDetail",
		data : {"userid":id},
		dataType : 'JSON',
		cache : false,
		succ : function(data, status) {
			fillForm('#userform',data);
			$("#importuser_modal").modal('show');
			$('#account').attr("disabled","disabled");
			$("#user_submit").hide();
			$("#user_update").show();
		}
	});
}
function validatorUserForm(){
	$('#userform').bootstrapValidator({
		message : 'This value is not valid',
		excluded : [ ':disabled' ],
		feedbackIcons : {
	//			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : {
						message : '用户名称不能为空'
					}
				}
			},
			number : {
				validators : {
					notEmpty : {
						message : '用户编号不能为空'
					}
				}
			},
			account : {
				validators : {
					notEmpty : {
						message : '账号不能为空'
					},
					remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
                        url: '/TownManagement/systemmanage/queryAccountExist',//验证地址
                        message: '账号已存在',//提示消息
                        delay :  500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                        /**自定义提交数据，默认值提交当前input value
                         *  data: function(validator) {
                              return {
                                  password: $('[name="passwordNameAttributeInYourForm"]').val(),
                                  whatever: $('[name="whateverNameAttributeInYourForm"]').val()
                              };
                           }
                         */
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '账号由数字字母下划线和.组成'
                    }
				}
			},
			password : {
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					identical: {//相同
                        field: 'repassword', //需要进行比较的input name值
                        message: '两次密码不一致'
                    }
				}
			},
			repassword : {
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					identical: {//相同
                        field: 'password', //需要进行比较的input name值
                        message: '两次密码不一致'
                    }
				}
			},
			roleid : {
				validators : {
					notEmpty : {
						message : '所属角色不能为空'
					}
				}
			},
			userdata : {
				validators : {
					notEmpty : {
						message : '数据权限不能为空'
					}
				}
			}
		}
	});
	$('#userform').bootstrapValidator('resetForm', false);
}