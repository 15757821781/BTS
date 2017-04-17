$.browser = $.browser || {};
$.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
$.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
$.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
$.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());
jQuery.browser = jQuery.browser || {};
jQuery.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
jQuery.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
jQuery.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
jQuery.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());
$.TagNames = new Array('input', 'select', 'textarea');
$.Events = new Array('click', 'dblclick', 'keydown', 'keypress', 'keyup', 'change', 'blur', 'focus');
document.onkeydown = function() {
	// 如果按下的是退格键
	if (event.keyCode == 8) {
		// 如果是在textarea内不执行任何操作
		var srcElement = event.srcElement;
		var tagName = srcElement.tagName.toLowerCase();
		if (tagName != "input" && tagName != "textarea" && tagName != "password") {
			event.returnValue = false;
		}
		// 如果是readOnly或者disable不执行任何操作
		if (srcElement.readOnly == true || srcElement.disabled == true) {
			event.returnValue = false;
		}
	}
};
function openEditor(title, url) {
	top.realOpenEditor(title, url, window);
}
function closeEditor() {
	top.realCloseEditor();
}
$(function() {
	$('body').delegate('.btn-search', 'mouseenter', function() {
		$(this).addClass('btn-search-hover');
	}).delegate('.btn-search', 'mouseleave', function() {
		$(this).removeClass('btn-search-hover');
	});

	$("body").delegate('.ctree', 'mouseenter', function() {
		if (!$(this).hasClass("showIcon")) {
			$(this).addClass("showIcon");
		}
	}).delegate('.ctree', 'mouseleave', function() {
		$(this).removeClass("showIcon");
	});
});
// 非法字符数组
var ILLEGAL_CHARS_ARRAY = new Array('\\', '/', ':', '*', '?', '\"', '<', '|', '\'', '%', '>');

var tk = tk || {};
String.prototype.replaceAll = function(s1, s2) {
	var s = this;
	return s.replace(new RegExp(s1, "gm"), s2);
};

String.prototype.toDate = function() {
	return new Date(this.replace(/-/g, "/"));
};
/**
 * 验证字符串是否以某一子串结尾
 * 
 * @param {}
 *            value 待验证字符串
 * @param {}
 *            str 子串
 * @return {Boolean}
 */
String.prototype.endWith = function(str) {
	var value = this;
	if (str == null || str == "" || value.length == 0 || str.length > value.length)
		return false;
	if (value.substring(value.length - str.length) == str)
		return true;
	else
		return false;
	return true;
};
/**
 * 验证字符串是否以某一子串开始
 * 
 * @param {}
 *            value 待验证字符串
 * @param {}
 *            str 子串
 * @return {Boolean}
 */
String.prototype.startWith = function(str) {
	var value = this;
	if (str == null || str == "" || value.length == 0 || str.length > value.length)
		return false;
	if (value.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
};

/**
 * 计算字符串长度，中文为2，英文为1，以此累计
 * 
 * @param {}
 *            s
 */
String.prototype.charLength = function() {
	var totalLength = 0;
	var charCode;

	for (i = 0; i < this.length; i++) {
		charCode = this.charCodeAt(i);
		if (charCode > 256) {
			totalLength += 2;
		} else {
			totalLength++;
		}
	}
	return totalLength;
};
String.prototype.substringByCharLength = function(start, end) {
	var totalLength = 0;
	var charCode;
	var s = -1;
	var e = -1;
	for (i = 0; i < this.length; i++) {
		charCode = this.charCodeAt(i);
		if (totalLength >= start && s == -1) {
			s = i;
		}
		if (charCode > 256) {
			totalLength += 2;
		} else {
			totalLength++;
		}
		if (totalLength >= end && e == -1) {
			e = i;
		}
	}
	if (e == -1) {
		e = this.length;
	}
	return this.substring(s, e);
};

String.prototype.valIllegalChars = function(excludes) {
	var value = this;
	var isIllegal = false;
	if (this) {
		var valIllegal = {};
		$.each(ILLEGAL_CHARS_ARRAY, function(index, temp) {
			if (excludes && excludes.length > 0) {
				var exclude = false;
				$.each(excludes, function(index, item) {
					if (temp == item) {
						exclude = true;
						return false;
					}
				});
				if (exclude) {
					return true;
				}
			}
			if (temp) {
				if (value.indexOf(temp) > -1) {
					isIllegal = true;
					valIllegal[temp] = 1;
				}
			}

		});
		if (isIllegal) {
			var array = [];
			for (var key in valIllegal) {
				array.push(key);
			}
			var tempMsg = array.join('，');
			tempMsg = tempMsg.replace('&', '&amp');
			tempMsg = tempMsg.replace('<', '&lt');
			tempMsg = tempMsg.replace('>', '&gt');
			tempMsg = tempMsg.replace('"', '&quot');
			tempMsg = tempMsg.replace('\'', '&#39');
			tempMsg = tempMsg.replace(' ', '空格');
			msg = '输入不能包含非法字符：' + tempMsg;
			return msg;
		} else {
			return '';
		}
	} else {
		return '';
	}
};

/**
 * 判断是否是合法的ip地址
 * 
 * @param {}
 *            value
 * @return {Boolean}
 */
String.prototype.isIP = function() {
	var exp = /^([1-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.([0-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.([0-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.([0-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
	var reg = this.match(exp);
	if (reg == null) {
		return false;
	} else {
		return true;
	}
};

/**
 * 检查是否为任意数（实数）
 * 
 * @param {}
 *            strNumber
 * @return {}
 */
String.prototype.isNumeric = function() {
	var newPar = /^(-|\+)?\d+(\.\d+)?$/;
	return newPar.test(this);
};
/**
 * 检查是否为正数
 * 
 * @param {}
 *            strNumber
 * @return {}
 */
String.prototype.isUnsignedNumeric = function() {
	var newPar = /^\d+(\.\d+)?$/;
	return newPar.test(this);
};
/**
 * 检查是否为整数
 * 
 * @param {}
 *            strInteger
 * @return {}
 */
String.prototype.isInteger = function() {
	var newPar = /^(-|\+)?\d+$/;
	return newPar.test(this);
};
/**
 * 检查是否为正整数
 * 
 * @param {}
 *            strInteger
 * @return {}
 */
String.prototype.isUnsignedInteger = function() {
	var newPar = /^\d+$/;
	return newPar.test(this);
};
/**
 * 验证字符串是否符合电话号码格式
 * 
 * @param {}
 *            value
 * @return {}
 */
String.prototype.isPhone = function() {
	var item = this.split('-');
	for (var i = 0; i < item.length; i++) {
		if (isNaN(item[i])) {
			return false;
		}
	}
	return true;
};
if (!([].reduce)) {
	Array.prototype.reduce = function(fn) {
		if (fn.length) {
			var result = this[0];
			for (var i = 1; i < this.length; i++) {
				result = fn(result, this[i]);
			}
			return result;
		}
	};
}

/**
 * 删除元素
 * 
 * @param {}
 *            obj
 */
Array.prototype.remove = function(obj) {
	var matched = -1;
	$.each(this, function(index, o) {
		if (obj == o) {
			matched = index;
			return false;
		}
	});
	if (matched >= 0) {
		this.splice(matched, 1);
	}
};
Array.prototype.contains = function(obj, comparator) {
	var matched = -1;
	$.each(this, function(index, o) {
		if (comparator ? comparator(obj, o) : (obj == o)) {
			matched = index;
			return false;
		}
	});
	return matched >= 0;
};

/**
 * json转字符串
 * 
 * @param {}
 *            s
 */
tk.json2string = function(json) {
	return JSON.stringify(json);
};

/**
 * 字符串转json
 * 
 * @param {}
 *            s
 */
String.prototype.toJson = function() {
	return JSON.parse(this);
};

/**
 * 验证字符串是否符合电话号码格式 "兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
 * 
 * @param {}
 *            value
 * @return {}
 */
String.prototype.isMobile = function() {
	return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})/.test($.trim(this)));
};

/**
 * 验证是否为电话号码
 * 
 * @param {}
 *            value
 * @return {}
 */
String.prototype.isTel = function() {
	return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?|(^[0-9]{7,8}$)/.test($.trim(this)));
};
/**
 * 验证字符是否符合email格式
 * 
 * @param {}
 *            value
 * @return {}
 */
String.prototype.isEmail = function() {
	return (/^[a-z0-9_+.-]+\@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/i.test(this));
};
/**
 * 验证字符是否为英文
 * 
 * @param {}
 *            value
 * @return {Boolean}
 */
String.prototype.isEnglish = function() {
	if (this.length == 0)
		return false;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 128)
			return false;
	}
	return true;
};
/**
 * 验证字符是否包含中文
 * 
 * @param {}
 *            value
 * @return {Boolean}
 */
String.prototype.isChinese = function() {
	if (this.match(/[^!-~]/g) == null) {
		return false;
	} else {
		return true;
	}
};

/**
 * 验证给定的日期是否合法 ,参数格式要求：yyyy-mm-dd 可以根据情况更改正则表达式
 * 
 * @param {}
 *            oStartDate
 * @return {Boolean}
 */
String.prototype.isDate = function() {
	// 对日期格式进行验证 要求为2000-2099年 格式为 yyyy-mm-dd 并且可以正常转换成正确的日期
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var reg2 = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	var r = this.match(reg) || this.match(reg2);
	return r != null;
};

/**
 * 验证表达式时候符合OGNL规范
 * 
 * @param {}
 *            expression
 */
String.prototype.validateOGNLExpression = function() {
	if (this.indexOf('-') != -1) {
		return false;
	}
	return true;
};

/**
 * 判断年份是否为润年
 * 
 * @param {Number}
 *            year
 */
tk.isLeapYear = function(year) {
	return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
};
/**
 * 获取某一年份的某一月份的天数
 * 
 * @param {Number}
 *            year
 * @param {Number}
 *            month
 */
tk.getMonthDays = function(year, month) {
	return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (tk.isLeapYear(year) ? 29 : 28);
};
/**
 * 获取某年的某天是第几周
 * 
 * @param {Number}
 *            y
 * @param {Number}
 *            m
 * @param {Number}
 *            d
 * @returns {Number}
 */
tk.getWeekNumber = function(now) {
	var year = now.getFullYear(), month = now.getMonth(), days = now.getDate();
	// 那一天是那一年中的第多少天
	for (var i = 0; i < month; i++) {
		days += tk.getMonthDays(year, i);
	}

	// 那一年第一天是星期几
	var yearFirstDay = new Date(year, 0, 1).getDay() || 7;

	var week = null;
	if (yearFirstDay == 1) {
		week = Math.ceil(days / yearFirstDay);
	} else {
		days -= (7 - yearFirstDay + 1);
		week = Math.ceil(days / 7) + 1;
	}

	return week;
};
// 获得一周的第一天
tk.getFirstDayOfWeek = function(date) {
	var day = date.getDay() || 7;
	return new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1 - day);
};
tk.getFn = function(fnName, target, arg0, arg1, arg2, arg3) {
	var fn = null;
	var type = typeof(fnName);
	if (target == null) {
		target = {};
	}
	if (type == 'string') {// 事件以字符串方式传递一个方法名
		fn = function(fnName, target, arg0, arg1, arg2, arg3) {
			return function() {
				try {
					if (fnName.indexOf('(') != -1) {
						return eval(fnName);
					} else {
						var items = fnName.split('.');
						var length = items.length;
						if (length == 0) {
							return null;
						}
						var fun = window;
						for (var i = 0; i < length; i++) {
							fun = fun[items[i]];
						}
						return fun.call(target, arg0, arg1, arg2, arg3);
					}
				} catch (ee) {
				}
			};
		};
	} else if (type == 'function') {// 事件以方法方式直接传递
		fn = function(fnName, target, arg0, arg1, arg2, arg3) {
			return function() {
				return fnName.call(target, arg0, arg1, arg2, arg3);
			};
		};
	}
	if (fn == null) {
		return null;
	}
	return fn(fnName, target, arg0, arg1, arg2, arg3);
};

tk.execFn = function(fnName, target, arg0, arg1, arg2, arg3) {
	var fn = tk.getFn(fnName, target, arg0, arg1, arg2, arg3);
	if (fn != null) {
		return fn();
	}
};
tk.resizeViewContent = function(obj) {
	if (!obj) {
		return;
	}
	if (obj.each) {
		obj.each(function() {
			var viewContent = this.viewContent;
			if (!viewContent) {
				return;
			}
			$.each(viewContent, function(index, cnt) {
				tk.resizeViewContent2(cnt);
			});
		});
	} else {
		tk.resizeViewContent2(obj);
	}
};

tk.resizeViewContent2 = function(obj) {
	if (obj.resize) {
		if (obj.resizeTimer) {
			clearTimeout(obj.resizeTimer);
		}
		obj.resizeTimer = setTimeout(function() {
			obj.resize();
			obj.resizeTimer = null;
		}, 1);
	}
};

tk.getScrollWith = function() {
	var wrap = document.createElement('div');
	$(wrap).css({
		width : '200px',
		height : '200px',
		overflow : 'auto',
		position : 'absolute',
		visibility : 'hidden'
	});
	var inner = document.createElement('div');
	$(inner).css({
		width : '100px',
		height : '2000px'
	});

	document.body.appendChild(wrap);
	wrap.appendChild(inner);
	var w = wrap.offsetWidth - wrap.clientWidth;
	document.body.removeChild(wrap);
	wrap = null;
	inner = null;
	return w;
};

tk.getValidator = function(s) {
	var validator = {};
	var vs = s.split(';');
	for (var i = 0; i < vs.length; i++) {
		var v = vs[i].split(':');
		if (v.length == 2) {
			validator[v[0]] = v[1];
		} else if (v.length == 1) {
			validator[v[0]] = 'true';
		}
	}
	return validator;
};
jQuery.fn.applyVInfo = function() {
	return this.each(function() {
		if ($(this).attr('v-info') == 1) {
			return;
		}
		$this = $(this);
		var validator = $(this).attr('validator');
		var vtype = tk.getVInfo(validator);
		var items = vtype.split(' ');
		$.each(items, function(index, item) {
			item = $.trim(item);
			if (item == '') {
				return;
			}
			var kv = item.split('=');
			var val = '';
			if (kv.lenght == 1) {
				val = true;
			} else if (kv.lenght == 2) {
				val = kv[1].replaceAll('"', '').replaceAll('\'', '');
			}
			$this.attr(kv[0], val);
		});
		$(this).attr('v-info', 1);
	});
};
tk.getVInfo = function(validator) {
	if (!validator) {
		return '';
	}
	if (typeof validator == 'string') {
		validator = tk.getValidator(validator);
	}
	var vtype = '';
	var numOpt = '';
	var commonOpt = '';
	if (validator['illegalchar'] || validator['common']) {
		vtype += 'common';
		commonOpt = validator['illegalchar'];
		// 这里增加类似排除某些非法字符的功能excludeChars='/' ;
		commonOpt += ' includeChars="&" ';
	}
	if (validator['number'] || validator['integer']) {
		vtype += 'number';
		if (validator['integer']) {
			numOpt = ' allowDecimals="false"';
		} else {
			numOpt = ' allowDecimals="true" allowNegative="true" filterzero="true" ';
		}
		var domain = validator['domain'];
		if (domain) {
			domain = domain.split('~');
			if (domain.length == 2) {
				numOpt += ' minValue="' + domain[0] + '" maxValue="' + domain[1] + '"';
			} else if (domain.length == 1) {
				numOpt += ' minValue="' + domain[0] + '"';
			}
		}
	}
	if (validator['ip']) {
		vtype += 'ip';
	}
	if (validator['email']) {
		vtype += 'email';
	}
	if (validator['phone']) {
		vtype += 'mobile';
	}
	if (validator['mobile']) {
		vtype += 'mobile';
	}
	if (validator['tel']) {
		vtype += 'tel';
	}
	if (vtype == '') {
		vtype = 'defalut';
	}
	vtype = 'vtype="' + vtype + '"';
	vtype += ' ' + (validator['must'] ? 'required="true"' : 'required="false"');
	if (validator['length'] || validator['size']) {
		var lengthItems;
		if (validator['length']) {
			vtype += ' hasChinese="true" chineseUnitLength="2"';
			lengthItems = validator['length'].split('~');
		} else {
			vtype += ' hasChinese="false" chineseUnitLength="1"';
			lengthItems = validator['size'].split('~');
		}
		if (lengthItems.length == 2) {
			vtype += ' minLength="' + lengthItems[0] + '" maxLength="' + lengthItems[1] + '"';
		} else if (lengthItems.length == 1) {
			vtype += ' minLength="' + lengthItems[0] + '"';
		}
	}
	vtype += numOpt + commonOpt;
	return vtype;
};
tk.validateFields = function(fields) {
	var $field = null;
	var msg = '';
	if ('string' == typeof fields) {
		fields = $(fields).toArray();
	}
	$.each(fields, function(index, field) {
		$(field).clearValidate();
	});
	var result = true;
	$.each(fields, function(index, field) {
		$field = $(field);
		if (field.disabled == true) {
			return true;
		}
		var name = $field.attr('name') || $field.attr('id');
		if (name == null || name == '') {
			return true;
		}

		var fieldOffset = field.offsetHeight + field.offsetWidth;
		if (!$field.is(':visible') || $field.is('input[type=hidden]') || $field.css('display') == 'none' || fieldOffset == 0) {
			return true;
		}
		$field.applyVInfo();
		if (!$field.validate()) {
			result = false;
			$field.focus();
			return false;
		}
		var validator = $field.attr('validator');
		if (validator) {
			var validator = tk.getValidator(validator);
			// 得到验证集合对象
			var value = $field.val();
			var isValueNotNull = value != null && value != '';

			var ip2 = validator['ip2'];
			// 验证IP
			if (isValueNotNull && ip2 == 'true' && !value.isIP() && value != '0.0.0.0') {
				msg = '请输入正确的IP地址！';
				return false;
			}

			var ne = validator['ne'];
			// 验证不等于
			if (ne) {
				var neItems = ne.split(',,');
				var b_e = false;
				var otherMsg = '';
				for (var ne_i = 0; ne_i < neItems.length; ne_i++) {
					if (neItems[ne_i] == value) {
						b_e = true;
					}
					if (otherMsg == '') {
						otherMsg = neItems[ne_i];
					} else {
						otherMsg += '、' + neItems[ne_i];
					}
				}
				if (b_e) {
					msg = '输入内容不能为：' + otherMsg + '！';
					return false;
				}
			}

			var nc = validator['nc'];
			// 验证不等于
			if (nc) {
				var ncItems = nc.split(',,');
				var b_e = false;
				var otherMsg = '';
				for (var ne_i = 0; ne_i < ncItems.length; ne_i++) {
					var ncItem = ncItems[ne_i];
					if (ncItem == '') {
						continue;
					}
					if (value.indexOf(ncItem) != -1) {
						b_e = true;
					}
					if (otherMsg == '') {
						otherMsg = ncItem == ' ' ? '空格' : ncItem;
					} else {
						otherMsg += '、' + ncItem == ' ' ? '空格' : ncItem;;
					}
				}
				if (b_e) {
					msg = '输入内容不能包含：' + otherMsg + '！';
					return false;
				}
			}

			var chinese = validator['chinese'];
			// 验证中文
			if (isValueNotNull && chinese == 'true' && !value.isChinese()) {
				msg = '请输入中文字符！';
				return false;
			} else if (isValueNotNull && chinese == 'false' && value.isChinese()) {
				msg = '请输入非中文字符！';
				return false;
			}

			var english = validator['english'];
			// 验证英文
			if (isValueNotNull && english == 'true' && !value.isEnglish()) {
				msg = '请输入英文字符！';
				return false;
			} else if (isValueNotNull && english == 'false' && value.isEnglish()) {
				msg = '请输入非英文字符！';
				return false;
			}

			var custom = validator['custom'];
			// 自定义验证
			if (custom) {
				msg = tk.execFn(custom, field, value);
				if (msg) {
					return false;
				}
			}
		}

	});
	if (!result) {
		return false;
	}
	if (msg) {// 验证不通过，弹出错误信息
		if ($field) {
			$field.showValidate(msg);
			$field.focus();
		}
		return false;
	}
	return true;
};

/**
 * 用于将指定元素转成json
 * 
 * @return {}
 */
tk.getJsonByFields = function(fields) {
	if ('string' == typeof fields) {
		fields = $(fields).toArray();
	}

	var arrayMap = {};
	// 数组表，填充由arrayName属性对应的值，用于后台过滤器将这些值设置在Action中的一个数组对象中。
	var json = '';
	$.each(fields, function(index, field) {

		var $field = $(field);
		var name = $field.attr('name') || $field.attr('id');

		var value = $field.val();
		if (value == null) {
			value = '';
		} else if ("string" == typeof value) {
			value = $.trim(value);
			value = value.replaceAll('\n', '<br/>');
		}

		if ($field.is('input')) {
			var fieldType = $field.attr('type').toLowerCase();
			if ('radio' == fieldType && !field.checked) {
				return true;
			} else if ('checkbox' == fieldType) {
				value = field.checked ? '1' : '0';
			}

			if (field.isColorPicker) {
				value = $field.css('backgroundColor');
				if (typeof value == 'undefined') {
					value = '#000000';
				}
			}
		}
		var exclude = $field.attr('exclude');
		if (exclude == 'true') {
			return true;
		}
		if (!name || !name.validateOGNLExpression()) {
			return true;
		}
		var s;

		var arrayName = $field.attr('arrayName');
		if (arrayName) {
			var arrayKey = arrayName + '$_$' + name;
			// 加入$_$方便过滤器匹配该属性
			var arrayValue = arrayMap[arrayKey];
			arrayValue = (arrayValue == null) ? '' : arrayValue;
			arrayValue += value + '@_@';
			// 加入@_@，方便后台分割参数值
			arrayMap[arrayKey] = arrayValue;
			return true;
		}

		var mapName = $field.attr('mapName');
		if (mapName) {
			name = mapName + '#_#' + name;
		}

		try {
			if (value.toJson() && value.startWith('{') && value.endWith('}')) {// 若值是符合json规范的字符串，则无需增加引号
				s = '"' + name + '":' + value;
			} else {
				s = '"' + name + '":"' + value + '"';
			}
		} catch (e) {
			s = '"' + name + '":"' + value + '"';
		}

		json += (json == '') ? s : ',' + s;

	});
	for (var mKey in arrayMap) {
		mValue = arrayMap[mKey];
		mValue = mValue.substring(0, mValue.length - 3);
		var ms = '"' + mKey + '":"' + mValue + '"';
		json += (json == '') ? ms : ',' + ms;
	}
	return json;
};

/**
 * 用于将指定元素转成json
 * 
 * @param {}
 *            fields
 * @return {}
 */
tk.fields2json = function(fields) {
	var subJson = tk.getJsonByFields(fields);
	var json = '{' + subJson + '}';
	return json.toJson();
};

tk.json2form = function(param) {
	if (param) {
		for (var key in param) {
			$('#' + key).val(value = param[key]);
		}
	}
};
tk.dataDic = function(code, callback) {
	if (typeof code == 'string') {
		tk.ajax({
			url : 'dataDictionary!getDataDictionariesByCode.action?code=' + code,
			succ : callback
		});
	} else {
		tk.ajax({
			url : 'dataDictionary!getDataDictionariesByParentId.action?parentId=' + code,
			succ : callback
		});
	}

};
tk.ajax = function(options) {
	options = $.extend(true, {
		type : 'post',
		form : null,
		fields : '',
		dataType : '',
		validate : false,
		responseType : 'json',
		timeout : 120000,
		showErrorMsg : true,
		mask : {
			target : null,
			msg : '正在加载数据...'
		},
		data : {},
		success : function(result) {
			if (options.isBlocked) {
				$(options.mask.target).unblock();
			}
			if (result == 'OverdueSession') {
				top.window.location = '/common/jsp/error.jsp';
				return;
			}
			var realParam = result;
			if (result != '' && options.responseType == 'json') {
				try {
					realParam = result.toJson();
				} catch (e) {
				}
			}
			if (realParam.exception) {
				if (options.exception) {
					tk.execFn(options.exception, options.scope, realParam);
				} else {
					tk.showWindow({
						url : '/power/common/jsp/exception.jsp',
						title : '系统异常',
						width : 600,
						height : 500,
						response : realParam
					});
				}
			} else {
				tk.execFn(options.succ, options.scope, realParam);
			}
		},
		error : function(XMLHttpRequest, status, errorThrown) {
			if (options.showErrorMsg) {
				var msg = null;
				if (XMLHttpRequest.status == 0) {
					msg = '网络已断开或请求超时，请联系管理员！';
				} else if (XMLHttpRequest.status == 500) {
					msg = '系统内部出错，请联系管理员！';
				} else if (XMLHttpRequest.status == 404) {
					msg = '请求无法找到系统资源，请联系管理员！';
				}
				if (msg) {
					if (options.failure) {
						msgOpt.onclose = function() {
							tk.execFn(options.failure, options.scope, status);
						};
					}
					$.err(msg);
				}
			} else if (options.failure) {
				tk.execFn(options.failure, options.scope, status);
			}
			if (options.isBlocked) {
				$(options.mask.target).unblock();
			}
		}
	}, options);
	if (options.form) {
		var form = $(options.form);
		if (options.validate && !form.validateForm()) {
			return;
		}
		var formJson = form.form2json();
		$.extend(options.data, formJson);
	}
	if (options.fields) {
		if (options.validate == true && !tk.validateFields(options.fields)) {
			return;
		}
		var formJson = tk.fields2json(options.fields);
		$.extend(options.data, formJson);
	}
	for (var key in options.data) {
		if (typeof(options.data[key]) == 'object') {
			options.data[key] = null;
		}
	}
	if (options.mask && options.mask.target) {

		$(options.mask.target).mask({
			message : options.mask.msg,
			timeout : null
		});
		options.isBlocked = true;
	}
	$.ajax(options);
};

tk.genId = function(id) {
	return (id || '') + ($.now() + Math.random() + '').replace('.', '-');
};

tk.addCookie = function(name, value, expireDays, expireHours) {
	var cookieString = name + "=" + escape(value);
	// 判断是否设置过期时间
	if (expireDays > 0 || expireHours > 0) {
		expireDays = expireDays || 0;
		expireHours = expireHours || 0;
		expireHours = expireHours + expireDays * 24;
		var date = new Date();
		date.setTime(date.getTime() + expireHours * 3600 * 1000);
		cookieString = cookieString + "; expires=" + date.toGMTString();
	}
	document.cookie = cookieString;
};
tk.compareToDate = function(startTimeStr,endTimeStr){
	
	var d1 = startTimeStr.toDate();
	var d2 = endTimeStr.toDate();
	if(d1>d2){
		return 1;
	}
	if(d1<d2){
		return -1;
	}
	return 0;
}
/**
 * 获取指定名称的cookie值 该函数返回名称为name的cookie值，如果不存在则返回空，其实现如下：
 * 
 * @param {}
 *            name
 * @return {String}
 */
tk.getCookie = function(name) {
	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");

	if (!name) {
		return strCookie;
	}

	for (var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if (arr[0] == name) {
			return unescape(arr[1]);
		}
	}
	return "";
};

tk.cookie = function(key, value, options) {

	// write
	if (value !== undefined) {
		options = $.extend({}, {}, options);

		if (value === null) {
			options.expires = -1;
		}

		if (typeof options.expires === 'number') {
			var days = options.expires, t = options.expires = new Date();
			t.setDate(t.getDate() + days);
		}

		value = String(value);
		// 增加对 !的转义
		return (document.cookie = [encodeURIComponent(key).replace('!', '%21'), '=',
		    encodeURIComponent(value),
		    options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not
		    // supported by IE
		    options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '',
		    options.secure ? '; secure' : ''].join(''));
	}

	return null;
};

/**
 * 指定Key的前缀，返回匹配到的Key值集合
 * 
 * @param {}
 *            prefix Key的前缀
 * @return {} Key值集合
 */
tk.getCookieKeysByPrefix = function(keyPrefix, valuePrefix) {
	var newPar = eval('/^\\s*' + keyPrefix + '?\\S+$/i');
	var newValPar = eval('/^\\s*' + valuePrefix + '?\\S+$/i');

	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");
	if (!keyPrefix) {
		return strCookie;
	}

	var arrKey = {};
	for (var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if (newPar.test(arr[0]) && newValPar.test(arr[1])) {
			arrKey[i] = unescape(arr[0]);
		}
	}
	return arrKey;
};

/**
 * 删除指定名称的cookie
 * 
 * @param {}
 *            name
 */
tk.deleteCookie = function(name) {
	var date = new Date();
	date.setTime(date.getTime() - 10000);
	document.cookie = name + "=v; expires=" + date.toGMTString();
};

/**
 * 设置URL参数
 * 
 * @param {}
 *            url URL
 * @param {}
 *            param 参数
 * @param {}
 *            v 参数值
 * @return {} 新生成的URL
 */
tk.setUrlParam = function(url, param, v) {
	var re = new RegExp("(\\\?|&)" + param + "=([^&]+)(&|$)", "i");
	var match = url.match(re);
	if (match) {
		return (url.replace(re, function($0, $1, $2) {
			return ($0.replace($2, v));
		}));
	} else {
		if (url.indexOf('?') == -1)
			return (url + '?' + param + '=' + v);
		else
			return (url + '&' + param + '=' + v);
	}
};

/**
 * 获得URL参数
 * 
 * @param {}
 *            url URL
 * @param {}
 *            param 参数名
 * @return {}
 */
tk.getUrlParam = function(url, param) {
	var re = new RegExp("(\\\?|&)" + param + "=([^&]+)(&|$)", "i");
	var m = url.match(re);
	if (m)
		return m[2];
	else
		return '';
};
tk.loadDlg = function(msg) {// 关于loading的，获取对话框的id;
	var id = tk.getUrlParam(window.location.href, 'dlgId');
	parent.$("#" + id).loading('dot-circle-big', msg);
};
tk.getPageTarget = function() {
	var url = window.location.href;
	var targetId = tk.getUrlParam(url, 'targetId');
	window['targetId'] = targetId;
	return parent.pageTargets[targetId];
};

tk.uploadFile = function(options) {
	options = $.extend(true, {
		form : '',
		uploadURL : 'common!uploadFile.action', // 文件上传URL
		detailURL : 'common!getFileUploadDetail.action', // 上传详细信息URL
		uploadedFilesURL : 'common!getUploadedFiles.action', // 获取已上传文件列表URL
		objectType : 'Temp',
		objectId : '',
		savedPath : '',
		listenLimitSize : '10K',
		allowFileTypes : 'gif,jpeg,jpg,png,bmp',
		fileLimitSize : '0',
		maxSize : '100M',
		interval : 200, // 请求上传详细信息的间隔
		useInterceptor : true, // 是否启用Struts2拦截器自动保存
		showUploadDetail : true, // 是否显示上传详细信息
		bufferSize : 1024,
		mask : {
			msg : '正在上传...',
			target : null
		}
	}, options);
	var eventId = tk.genId('file-upload');
	var allowFileTypes = options.allowFileTypes;
	if (allowFileTypes != '') {
		allowFileTypes = allowFileTypes.replaceAll('，', ',');
		allowFileTypes = allowFileTypes.split(',');
	}

	if (allowFileTypes.length > 0) {
		var valid = true;
		$(options.form).find('input[type=file]').each(function() {
			var nameItems = $(this).val().split('.');
			var extension = nameItems[nameItems.length - 1].toLowerCase();
			if (!allowFileTypes.contains(extension)) {
				$.err('只允许上传' + options.allowFileTypes + '文件');
				valid = false;
				$(this).val('');
				return false;
			}
		});
		if (!valid) {
			return;
		}
	}

	var uploadURL = tk.parseFileUploadURL(options.uploadURL, options);
	uploadURL = tk.setUrlParam(uploadURL, 'FILE_UPLOAD_EVENT_ID', eventId);
	var form = $(options.form).each(function() {
		if (options.mask && options.mask.target) {
			$(options.mask.target).mask({
				message : options.mask.msg,
				timeout : null
			});
			options.isBlocked = true;
		}
		var $this = $(this);
		$this.attr('action', uploadURL);
		var iframeName;
		if (this.targetFrame) {
			iframeName = $(this.targetFrame).attr('name');
		} else {
			iframeName = "UPLOAD_IFRAME_" + Math.floor(Math.random() * 1000);
			var iframeHtml = '<iframe name="' + iframeName + '" width="0px" height="0px" style="display:none"></iframe>';
			$(document.body).append(iframeHtml);
		}

		$this.attr('target', iframeName);
		$this.attr('method', 'post');
		$this.attr('enctype', 'multipart/form-data');
		$this.attr('encoding', 'multipart/form-data');

		getFileUploadDetailFn = function(type) {
			return function() {
				var detailURL = tk.setUrlParam(options.detailURL, 'type', type);
				detailURL = tk.setUrlParam(detailURL, 'FILE_UPLOAD_EVENT_ID', eventId);
				tk.ajax({
					url : detailURL,
					responseType : 'json',
					succ : function(response) {
						var completePercent = response.completePercent;
						if (response.status === 0) {
							setTimeout(getFileUploadDetailFn('1'), options.interval);
						} else {
							if (!response.msg && response.result == false) {
								response.msg = "上传失败！";
							}
							if (response.result == true) {
								tk.execFn(options.success, response, response.msg);
								if (options.getFiles) {
									tk.getUploadedFiles(options);
								}
								$this.find('input[type=file]').val('');
							} else {
								tk.execFn(options.failure, response, response.msg);
								$this.find('input[type=file]').val('');
							}
							if (options.isBlocked) {
								$(options.mask.target).unblock();
								options.isBlocked = false;
							}
						}
					}
				});
			};
		};
		setTimeout(getFileUploadDetailFn('0'), options.interval);
		this.submit();
	});
};
tk.getUploadedFiles = function(options) {
	options = $.extend({
		form : '',
		uploadedFilesURL : 'common!getUploadedFiles.action', // 获取已上传文件列表URL
		objectType : 'Temp',
		objectId : '',
		savedPath : '',
		getFiles : function() {
		}
	}, options);
	tk.ajax({
		url : tk.parseFileUploadURL(options.uploadedFilesURL, options),
		responseType : 'json',
		succ : function(response) {
			options.getFiles(response);
		}
	});
};

tk.parseFileUploadURL = function(url, options) {
	var formURL = tk.setUrlParam(url, 'bufferSize', options.bufferSize || 0);
	formURL += '&objectType=' + options.objectType;
	formURL += '&objectId=' + options.objectId;
	formURL += '&savedPath=' + options.savedPath;
	if (options.listenLimitSize) {
		formURL += '&listenLimitSize=' + options.listenLimitSize;
	}
	if (options.allowFileTypes) {
		formURL += '&allowFileTypes=' + options.allowFileTypes;
	}
	if (options.fileLimitSize) {
		formURL += '&fileLimitSize=' + options.fileLimitSize;
	}
	if (options.maxSize) {
		formURL += '&maxSize=' + options.maxSize;
	}
	if (options.useInterceptor == true) {
		formURL += '&UPLOAD_TYPE=custom';
	}
	if (options.validatorCls) {
		formURL += '&validatorCls=' + options.validatorCls;
	}
	if (options.validatorBean) {
		formURL += '&validatorBean=' + options.validatorBean;
	}
	return formURL;
};

tk.log = function() {
	if (console) {
		if (console.log) {
			console.log(arguments);
		} else if (console.dir) {
			console.dir(arguments);
		}
	}
};

/**
 * 格式化日期
 * 
 * @param {}
 *            date
 * @param {}
 *            format
 * @return {}
 */
tk.formatDate = function(date, format) {
	if (!format) {
		format = 'yyyy-MM-dd HH:mm:ss';
	}
	var r = format;
	var d = date.getDate();
	d = (d < 10) ? '0' + d : d;
	var m = date.getMonth() + 1;
	m = (m < 10) ? '0' + m : m;
	var yy = date.getYear();
	yy = (yy < 1000) ? yy + 1900 : yy;
	var h = date.getHours();
	h = (h < 10) ? '0' + h : h;
	var mi = date.getMinutes();
	mi = (mi < 10) ? '0' + mi : mi;
	var s = date.getSeconds();
	s = (s < 10) ? '0' + s : s;
	r = r.replaceAll('dd', d).replaceAll('MM', m).replaceAll('yyyy', yy).replaceAll('HH', h).replaceAll('mm', mi).replaceAll(
	        'ss', s);
	return r;
};

tk.getCPU = function() {
	var agent = navigator.userAgent.toLowerCase();
	if (agent.indexOf("win64") >= 0 || agent.indexOf("wow64") >= 0)
		return "x64";
	return navigator.cpuClass;
};

jQuery.fn.setVisible = function(visible) {
	return this.each(function() {
		var $this = $(this);
		if ($.browser.msie) {
			if (visible) {
				$this.show();
			} else {
				$this.hide();
			}
		} else {
			if (visible) {
				$this.css({
					visibility : 'visible',
					width : '100%',
					height : '100%'
				});
				$this.show();
			} else {
				$this.css({
					visibility : 'hidden',
					width : 0,
					height : 0
				});
			}
		}
	});
};

tk.loadImg = function(imgContainer, url) {
	var imgDom = imgContainer.children('img');
	imgDom.src = '';
	imgContainer.css({
		'padding-left' : 0,
		'padding-top' : 0
	});
	var imgWidth = imgContainer.width();
	var imgHeight = imgContainer.height();
	var img = new Image();
	url = tk.setUrlParam(url, 't', $.now() + Math.random());
	img.src = url;
	$(img).load(function() {
		var realImgWidth = img.width;
		var realImgHeight = img.height;
		var d1 = imgWidth / imgHeight;
		var d2 = realImgWidth / realImgHeight;
		var width = 0;
		var height = 0;
		var marginLeft = 0;
		var marginTop = 0;
		if (d1 > d2) {
			width = imgHeight * d2;
			height = imgHeight;
			marginLeft = (imgWidth - width) / 2;
		} else {
			width = imgWidth;
			height = width / d2;
			marginTop = (imgHeight - height) / 2;
		}
		imgDom.attr('src', url).css({
			width : parseInt(width),
			height : parseInt(height),
			'margin-left' : parseInt(marginLeft),
			'margin-top' : parseInt(marginTop)
		});
	});
};
tk.getFileControl = function() {
	var webFileFolderControl = $('#WebFileFolderControl');
	if (!webFileFolderControl.length) {
		var html;
		if ($.browser.msie) {
			html = '<object id="WebFileFolderControl" classid="clsid:0ADF0C5A-16F9-44BA-B102-EEBD4F051264" style="display:none"></object>';
		} else {
			html = '<embed id="WebFileFolderControl" style="width:0px;height:0px;visibility:hidden" type="application/hik-WebFileFoderCtl-plugin">';
		}
		$(document.body).append(html);
		webFileFolderControl = $('#WebFileFolderControl');
	}
	return webFileFolderControl.get(0);
};

tk.openDir = function(path) {
	var obj = tk.getFileControl();
	obj.OpenDirectory(path);
};
tk.openFile = function(path) {
	var obj = tk.getFileControl();
	obj.ExecuteFile(path);
};

tk.silverLightExceptionEvent = function(sender, args) {
	if (args.Level == 1) {
		$.info(args.Message);
	} else {
		$.err(args.Message);
	}
};

// 执行导出，待MQ返回导出完成后关闭导出窗口
tk.exportTo = function(url, dt, method) {
	var expId = tk.genId("exp");
	var expFormId = tk.genId("expForm");
	var html = '<div style="text-align:center;size:14px;">正在导出，请稍后...<iframe name="' + expId + '" id="' + expId
	        + '" style="display:none" src="" ></iframe><form id="' + expFormId + '" method="post" target="' + expId
	        + '" ></form></div>';
	var dialog = $.dialog({
		html : html,
		width : '200px',
		height : '100px'
	});
	org.activemq.Amq.addListener('tk.exportTo', 'queue://exp-data-' + expId, function(msg) {
		var textContent = msg.text || msg.textContent;
		if (dialog) {
			dialog.close();
		}
		if (textContent != 'success') {
			$.warn(textContent);
		}
	});
	if (method == 'post') {
		if (dt && dt.length > 0) {
			var len = dt.length;
			var $form = $('#' + expFormId);
			$form.attr('action', url);
			for (var i = 0; i < len; i++) {
				$form.append('<input type="hidden" name="' + dt[i].name + '" value="' + dt[i].value + '">');
			}
			$form.append('<input type="hidden" name="expId" value="' + expId + '">');
		}
		$('#' + expFormId).get(0).submit();
	} else {
		if (!dt) {
			dt = [];
		}
		dt[dt.length] = {
			name : 'expId',
			value : expId
		};
		var paramStr = getParamString(dt);
		if (url.indexOf('?') >= 0) {
			url += '&' + paramStr;
		} else {
			url += '?' + paramStr;
		}
		$('#' + expId).attr('src', url);
	}
};

tk.hideComplexObj = function(notId, container) {
	var obj = $('[hideOnMask=1]', container);
	if (notId) {
		obj = obj.not('#' + notId + ' [hideOnMask=1]');
	}
	obj.each(function() {
		var originVisibility = $(this).attr('originVisibility') || 0;
		originVisibility++;
		$(this).attr('originVisibility', originVisibility).css({
			visibility : 'hidden'
		});
	});
};
tk.showComplexObj = function(notId, container) {
	var obj = $('[hideOnMask=1]', container);
	if (notId) {
		obj = obj.not('#' + notId + ' [hideOnMask=1]');
	}
	obj.each(function() {
		var originVisibility = $(this).attr('originVisibility') || 0;
		if (originVisibility > 0) {
			originVisibility--;
			$(this).attr('originVisibility', originVisibility);
			if (originVisibility == 0) {
				$(this).css({
					visibility : 'visible'
				});
			}
		}
	});
};
tk.uniEncode = function(text) {
	text = escape(text.toString()).replace(/\+/g, "%2B");
	var matches = text.match(/(%([0-9A-F]{2}))/gi);
	if (matches) {
		for (var matchid = 0; matchid < matches.length; matchid++) {
			var code = matches[matchid].substring(1, 3);
			if (parseInt(code, 16) >= 128) {
				text = text.replace(matches[matchid], '%u00' + code);
			}
		}
	}
	text = text.replace('%25', '%u0025');
	return text;
};
tk.unicodeToUTF8 = function(strInUni) {
	if (null == strInUni)
		return null;
	var strUni = String(strInUni);
	var strUTF8 = String();
	for (var i = 0; i < strUni.length; i++) {
		var wchr = strUni.charCodeAt(i);
		if (wchr < 0x80) {
			strUTF8 += strUni.charAt(i);
		} else if (wchr < 0x800) {
			var chr1 = wchr & 0xff;
			var chr2 = (wchr >> 8) & 0xff;
			strUTF8 += String.fromCharCode(0xC0 | (chr2 << 2) | ((chr1 >> 6) & 0x3));
			strUTF8 += String.fromCharCode(0x80 | (chr1 & 0x3F));
		} else {
			var chr1 = wchr & 0xff;
			var chr2 = (wchr >> 8) & 0xff;
			strUTF8 += String.fromCharCode(0xE0 | (chr2 >> 4));
			strUTF8 += String.fromCharCode(0x80 | ((chr2 << 2) & 0x3C) | ((chr1 >> 6) & 0x3));
			strUTF8 += String.fromCharCode(0x80 | (chr1 & 0x3F));
		}
	}
	return strUTF8;
};

tk.getTopZ = function() {
	return parseInt(new Date().getTime() / 1000);
};

tk.setTheme = function(theme) {
	var matched = $('link[themeLink=1][theme=' + theme + ']');
	if (!matched.length) {
		theme = 'blue';
		matched = $('link[themeLink=1][theme=' + theme + ']');
	}
	matched.removeAttr('disabled');
	$('link[themeLink=1][theme!=' + theme + ']').attr('disabled', 'disabled');
};
/**
 * 获得最大的z-index
 */
tk.getTopZ = function() {
	return parseInt(new Date().getTime() / 1000);
};
jQuery.fn.showOverlay = function(options) {
	return this.each(function() {
		options = $.extend({
			region : 'south'
		}, options);
		if (options.el) {
			var el = $(options.el);
			this.c_overlay = el.selector;
			var pos = $(this).getRelativePosition(options, el);
			var zIndex = tk.getTopZ();
			var overlayZ = zIndex + 1;
			var frameZ = zIndex;
			var overlay = el.show().offset({
				left : pos.left,
				top : pos.top
			}).css({
				zIndex : overlayZ
			});
			if (!this.ifrmLayerId) {
				this.ifrmLayerId = tk.genId('custom-overlay-iframe');
				var ifrm = '<iframe id="'
				        + this.ifrmLayerId
				        + '" scrolling= "no" frameborder= "0" style="display:none;zIndex:-1;position:absolute;border:1px solid transparent;FILTER: alpha(opacity = 0);opacity: 0"></iframe>';
				$(document.body).append(ifrm);
			}
			$('#' + this.ifrmLayerId).show().offset({
				left : pos.left,
				top : pos.top
			}).css({
				width : overlay.width(),
				height : overlay.height(),
				display : 'block',
				zIndex : frameZ
			});
		}
	});
};
jQuery.fn.hideOverlay = function() {
	return this.each(function() {
		if (this.c_overlay) {
			if (this.ifrmLayerId) {
				$('#' + this.ifrmLayerId).hide();
			}
			$(this.c_overlay).hide();
		}
	});
};

jQuery.fn.enlarge = function(opt) {
	opt = $.extend(true, {
		minWidth : 400
	}, opt);

	return this.each(function() {
		var url = $(this).attr('src');
		var img = new Image();
		url = tk.setUrlParam(url, 't', $.now() + Math.random());
		img.src = url;
		if (opt.mask) {
			$(opt.mask).mask({
				overlayCSS : {
					cursor : 'default'
				}
			});
		}
		$(img).load(function() {
			var realImgWidth = img.width;
			var realImgHeight = img.height;
			var minWidth = opt.minWidth;
			if (realImgWidth < minWidth) {
				realImgHeight = realImgHeight / realImgWidth * minWidth;
				realImgWidth = minWidth;
			}
			var winWidth = $(document.body).width();
			var winHeight = $(document.body).height() - 40;
			var imgWidth = realImgWidth;
			var imgHeight = realImgHeight;
			if (realImgWidth > winWidth || realImgHeight > winHeight) {
				var r1 = realImgWidth / realImgHeight;
				var r2 = winWidth / winHeight;
				if (r1 > r2) {
					imgWidth = winWidth;
					imgHeight = parseInt(imgWidth / r1);
				} else {
					imgHeight = winHeight;
					imgWidth = parseInt(r1 * imgHeight);
				}

			}
			imgHeight += 40;
			$.dialog({
				html : '<img src="' + url + '" style="width:100%;height:100%">',
				height : imgHeight + 'px',
				width : imgWidth + 'px',
				title : opt.title || '图片',
				onclose : function() {
					if (opt.mask) {
						$(opt.mask).unblock();
					}
				}
			});
		});
	});
};

jQuery.fn.bindDefCss = function() {
	return this.each(function() {
		var fields = $(this).find($.TagNames.join(','));
		fields.each(function() {
			var $this = $(this);
			if ($this.is('[type=radio]') || $this.is('[type=checkbox]')) {
				return true;
			}
			$this.hover(function() {
				$(this).addClass('input-hover');
			}, function() {
				$(this).removeClass('input-hover');
			}).focus(function() {
				$(this).addClass('input-active');
			}).blur(function() {
				$(this).removeClass('input-active');
			});
		});
	});
};

jQuery.fn.isVisible = function() {
	var fieldOffset = this.clientHeight + this.clientWidth;
	var $field = $(this);
	if (!$field.is(':visible') || $field.is('input[type=hidden]') || $field.css('display') == 'none' || fieldOffset == 0) {
		return false;
	}
	return true;
};

/**
 * form转json 转换对象：'input','select','radio','textarea' 可选属性： exclude="true" ：表示排除 arrayName="arrayName"
 * ：表示该属性会被填充到Action中定义的名为arrayName数组中
 * 
 * @param {}
 *            form
 * @param {}
 *            param
 * @return {}
 */
jQuery.fn.form2json = function() {
	var fields = [];
	this.each(function() {
		$(this).find($.TagNames.join(',')).each(function() {
			if ($(this).attr('type') == 'file') {
				return;
			}
			fields.push(this);
		});
	});
	return tk.fields2json(fields);
};
/**
 * 遍历输入域
 */
jQuery.fn.eachField = function(fn) {
	return this.each(function() {
		$(this).find($.TagNames.join(',')).each(function() {
			fn.call(this);
		});
	});
};
/**
 * 用指定的json填充表单
 */
jQuery.fn.fillForm = function(json, clear) {
	json = json || {};
	clear = clear || false;
	return this.each(function() {
		$(this).find($.TagNames.join(',')).each(function() {// 遍历所有可输入域，并设置其值
			var $this = $(this);
			if ($this.attr('type') == 'file') {
				return;
			}
			var domName = $this.attr('name');
			var mapName = $this.attr('mapName');
			var domValue = $this.val();
			if(!domName){
				return;
			}
			if (!clear && !(domName in json) && !((mapName + '#_#' + domName) in json) && !(domName.split('.')[1] in json)) {
				return;
			}
			var curValue = json[domName] || json[mapName + '#_#' + domName] || '';
			if (curValue == '' && domName.split('.').length == 2) {
				curValue = json[domName.split('.')[1]] || '';
			}
			if ($this.is('input')) {// 设置input属性，包括text、radio、hidden、checkbox等
				if ($this.attr('isColorPicker')) {
					$this.css('backgroundColor', curValue);
					$this.val('');
					return true;
				}
				if ($this.is('[type=radio]')) {
					if (curValue == domValue) {
						$this.attr('checked', 'checked');
					} else {
						$this.removeAttr('checked');
					}
				} else if ($this.is('[type=checkbox]')) {
					var mergeName = $this.attr('mergeName');
					var checked;
					if (mergeName) {
						var items = curValue.split(',');
						checked = (items.contains(domValue));
					} else {
						checked = (1 == curValue);
					}
					if (checked) {
						$this.attr('checked', 'checked');
					} else {
						$this.removeAttr('checked');
					}
				} else {
					$this.val(curValue);
				}
			} else if ($this.is('select')) {
				if (this.options.length) {
					var matched = 0;
					var selectedIndex = this.selectedIndex;
					$.each(this.options, function(i, o) {
						if (o.value == curValue) {
							matched = i;
						}
					});
					this.options[matched].selected = true;
					// $this.uniform();
					if (selectedIndex != matched) {
						$this.trigger('change');
					}
				}
			} else {
				if ($this.is('textarea')) {
					curValue = curValue.replaceAll('<br/>', '\n');
				}
				$this.val(curValue);
			}
		});
		$(this).find('.combo').each(function() {
			if (this.combo) {
				var options = this.combo.options;
				var domName = options.name;
				var mapName = options.mapName;
				var curValue = json[domName] || json[mapName + '#_#' + domName] || '';
				if (curValue == '' && domName.split('.').length == 2) {
					curValue = json[domName.split('.')[1]] || '';
				}
				this.combo.setValue(curValue);
			}
		});
		$(this).find('.combotree').each(function() {
			if (this.combotree) {
				var options = this.combotree.options;
				var domName = options.name;
				var mapName = options.mapName;
				var curValue = json[domName] || json[mapName + '#_#' + domName] || '';
				if (curValue == '' && domName.split('.').length == 2) {
					curValue = json[domName.split('.')[1]] || '';
				}
				this.combotree.setValue(curValue);
			}
		});
	});
};
/**
 * 取消蒙板
 */
jQuery.fn.unmask = function(opt) {
	return this.each(function() {
		if (jQuery.fn.unblock) {
			$(this).unblock(opt);
		}
	});
};

/**
 * 开启蒙板
 */
jQuery.fn.mask = function(opt) {
	if (typeof opt == 'string') {
		var html = $('<div class="loading-overlay"><div class="loading-m"><i></i><span>' + opt
		        + '</span></div><div class="shadow"></div></div>');
		opt = {
			message : html,
			css : {
				height : 0,
				width : 0,
				display : 'none',
				'border-width' : 0,
				opacity : 1
			}
		};
	}
	var opt = $.extend(true, {
		message : null
	}, opt);
	return this.each(function() {
		if (jQuery.fn.block) {
			$(this).block(opt);
		}
	});
};
/**
 * 检验表单
 */
jQuery.fn.validateForm = function() {
	var result = true;
	this.each(function() {
		var fields = $(this).find($.TagNames.join(','));
		result = tk.validateFields(fields.toArray());
		return result;
	});
	return result;
};
// 判断:当前元素是否是被筛选元素的子元素
jQuery.fn.isChildOf = function(b) {
	return (this.parents(b).length > 0);
};
// 判断:当前元素是否是被筛选元素的子元素或者本身
jQuery.fn.isChildOrSelfOf = function(b) {
	return (this.closest(b).length > 0);
};

jQuery.fn.changeVal = function(fn) {
	return this.each(function() {
		var str = '';
		var now = '';
		var $this = $(this);
		var _this = this;
		var filter_time = function() {
			var time = setInterval(filter_staff_from_exist, 100);
			$(this).bind('blur', function() {
				clearInterval(time);
			});
		};

		var filter_staff_from_exist = function() {
			now = $.trim($this.val());
			if (now != str) {
				fn.call(_this);
			}
			str = now;
		};
		$(this).bind('focus', filter_time);
	});
};
