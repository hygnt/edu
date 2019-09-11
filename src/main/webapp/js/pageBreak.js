/**
 * 功能：js分页类
 * @author Cicada
 * @date 2013-8-14
 * @param {string} id 需要追加到元素的节点id
 * @param {json:key->value} config 具体配置如下所示:
 * config = {
 *     itemCount: {int} 总记录数，这个是必须要给出的
 *     callBack: {function} 回调函数，翻页后要做的事情
 *     styleTYPE: {int: 1|2} 分页条的显示样式
 *     1、标准样式，2、两边可以带省略号的显示（还可以附带一个属性: styleSIZE {int} 省略号两头显示的宽度）
 *     index: {int} 当前页
 *     pageNo: {int} 要显示多少个页码
 *     isEvenPageNo: {string: LEFT|RIGHT} 如果页码数量为偶数的情况下，是左边比右边多，还是右边比左边多，自己配置
 *     size: {int} 一页显示多少条记录
 *     first|last|prev|next {string} 要显示的内容，默认为：首页、尾页、上一页、下一页
 * }
 * 
 *
 */
var PageBreak = function(id, config) {

	//把对象引用保存，以供下面调用
	var self = this;

	//dom元素的id
	this.id = id;

	//自身的dom元素
	this.container = null;

	//总记录数，默认0
	var _itemCount = 0;

	//当前页码，从第一页开始
	var _index = 1;

	//中间部分要显示多少个页码，默认显示5个页码
	var _pageNo = 5;

	//一页显示多少条，默认显示10
	var _size = 10;


	//总页数及起始页码
	var _pageCount = 0;
	var _start = 1;
	var _end = 1;


	//配置首页、上一页、下一页、尾页内容
	var _first = "首页";
	var _last  = "尾页";
	var _prev  = "上一页";
	var _next  = "下一页";

	//页码为偶数时，是左边要多显示一个，还是右边多显示一个
	//两个值：LEFT、RIGHT
	//默认值：左边的比右边的多一个
	var isEvenPageNo = "LEFT";

	//是否以另一种样式显示分页
	// 上一页 1 ... 4 5 6 7 8 ... 11 下一页
	// 1 为正常样式
	// 2 为带省略号的风格
	// 后期扩展 ...
	var styleTYPE = 1;
	var styleSIZE = 1;

	//内部使用变量
	var l_offset = r_offset = 0;

	//回调函数，翻页后需要的数据
	var callBack = function(index, size) {return true;};

	//验证函数
	var verify = function(param, _param) {
		return (!!param ? param : _param);
	};

	if (!!config) {

		//记录总条数必须传入，否则直接返回
		if (!config.itemCount) {
			return false;
		} else {
			_itemCount = config.itemCount;
		}

		//回调函数
		callBack = (typeof config.callBack == 'function') ? config.callBack : callBack;

		//当前页、页码数量及每页记录数
		_index  = verify(config.index, _index);
		_pageNo = verify(config.pageNo, _pageNo);
		_size   = verify(config.size, _size);

		//首页、尾页、上一页、下一页文字的显示
		_first = verify(config.first, _first);
		//此参数如果不存在的话，则就不用显示了
		_last  = verify(config.last, _last);
		_last  = _last == 'NULL' ? undefined : _last;
		_prev  = verify(config.prev, _prev);
		_next  = verify(config.next, _next);

		//页码为偶数时，是左边要多显示一个，还是右边多显示一个
		isEvenPageNo = verify(config.isEvenPageNo, isEvenPageNo);

		//页码显示风格
		styleTYPE = verify(config.styleTYPE, styleTYPE);
		styleSIZE = verify(config.styleSIZE, styleSIZE);

		//初始化一些共用参数
		_pageCount = Math.ceil(_itemCount / _size);
		var offset = parseInt(_pageNo / 2);
		var isEven = !(_pageNo % 2);
		var l_offset = r_offset = offset;
		if (isEven) {
			l_offset = (isEvenPageNo ===  "LEFT" ? offset : offset - 1);
			r_offset = (isEvenPageNo === "RIGHT" ? offset : offset - 1);
		}
	}


	//此方法为私有方法，相当于java中的private
	var _click = function(index) {
		//当前页码
		_index = index;
		//回调函数
		callBack(index, _size);
		//重新计算页码
		self.showPageBar();
	};

	//计算出起始页码及总页数
	var _calculate = function() {
		//起终页码赋值
		var start = _index - l_offset;
		var end   = _index + r_offset;

		if (start < 1) {
			start = 1;
			end = _pageNo;
			end = (end > _pageCount) ? _pageCount : end;
		} else {
			if (end > _pageCount) {
				end = _pageCount;
				start = end - (_pageNo - 1);
				start = (start < 1) ? 1 : start;
			}
		}

		//设置属性值
		_start = start;
		_end = end;
	};

	//直接定位到页码
	this.goPage = _click;

	//此方法用于页码更新的时候用
	this.showPageBar = function() {
		var div = document.getElementById(self.id);
		self.container = div;

		//函数调用，省去if else语句了
		var jsfun = {
			1: function() {_style1();},
			2: function() {_style2();},
		};
		jsfun[styleTYPE]();
		
		var a_list = self.container.getElementsByTagName('a');
		for (var i = 0; i < a_list.length; i++) {
			a_list[i].onclick = function() {
				var index = this.getAttribute('href');
				if (index != undefined && index != ''){
					index = parseInt(index.replace('javascript://', ''));
					_click(index);
				}
				return false;
			};
		}
		
		//go page
		var btn = self.container.getElementsByTagName('input');
		if (btn) {
			btn[0].onblur = function() {
				var num = parseInt(this.value);
				if (!isNaN(num)) {
					num = num <= 1 ? 1 : num;
					num = num >= _pageCount ? _pageCount : num;
					this.value = num;
				}
			};
			//翻页
			btn[1].onclick = function() {
				var num = parseInt(this.previousSibling.previousSibling.value);
				if (!isNaN(num)) {
					if (_index != num) _click(num);
				} else {
					this.previousSibling.previousSibling.focus();
				}
			};
		}
	};

	var _style1 = function() {

		//计算一下起始页码及总页数
		_calculate();

		var str = '';
		str += '<div class="page-bar">';
		if(_pageCount > 1){
			if(_index != 1){
				str += '<a href="javascript://1"><span>' + _first + '</span></a>';
				str += '<a href="javascript://' + (_index-1) + '"><span>' + _prev + '</span></a>';
			}else{
				str += '<span>' + _first + '</span>';
				str += '<span>' + _prev + '</span>';
			}
		}
		for (var i = _start; i <= _end; i++) {
			if (i == _index) {
				str += '<span class="on">' + i + "</span>";
			} else {
				str += '<a href="javascript://' + i + '"><span>' + i + '</span></a>';
			}
		}
		if (_pageCount > 1) {
			if (_index != _pageCount) {
				str += '<a href="javascript://' + (_index+1) + '"><span>' + _next + '</span></a>';
				if (!!_last) str += '<a href="javascript://' + _pageCount + '"><span>' + _last + '</span></a>';
			} else {
				str += '<span>' + _next + '</span>';
				if (!!_last) str += '<span>' + _last + '</span>';
			}
		}
		//str += ' 一共' + _pageCount + '页, ' + _itemCount + '条记录 ';
		str += '到第<input type="text" class="ipt1" name="page" id="jumpPage">页<input type="button" class="ipt2" value="" id="jumpBtn">';
		str += '</div>';

		self.container.innerHTML = str;

	};

	var _style2 = function() {

		var str = '';
		str += '<div class="page-bar">';
		//（ 两边的宽度 + 页码显示的宽度 + 省略点占的两个位置 ） < 总页数
		//那么直接就列出所有页码
		if (_pageCount > 0 && (styleSIZE * 2 + _pageNo + 2) < _pageCount) {

			if (_index != 1) {
				str += '<a href="javascript://' + (_index-1) + '"><span>' + _prev + '</span></a>';
			} else {
				str += '<span>' + _prev + '</span>';
			}

			for (var i = 1; i <= styleSIZE; i++) {
				if (i == _index) {
					str += '<span class="on">' + i + "</span>";
				} else {
					str += '<a href="javascript://' + i + '"><span>' + i + '</span></a>';
				}
			}

			if (_index > (l_offset + styleSIZE + 1)) {
				str += '<span>...</span>';
				if (_index < (_pageCount - r_offset - styleSIZE)) {
					_calculate();
				} else {
					_start = _pageCount - styleSIZE - _pageNo + 1;
					_end   = _pageCount - styleSIZE;
				}
			} else {
				//一直显示左边的几个
				_start = styleSIZE + 1;
				_end   = Math.min(_pageNo + styleSIZE, _pageCount);
			}

			for (var i = _start; i <= _end; i++) {
				if (i == _index) {
					str += '<span class="on">' + i + "</span>";
				} else {
					str += '<a href="javascript://' + i + '"><span>' + i + '</span></a>';
				}
			}

			if (_index < (_pageCount - r_offset - styleSIZE)) {
				str += '<span>...</span>';
			}

			for (var i = _pageCount - styleSIZE + 1; i <= _pageCount; i++) {
				if (i == _index) {
					str += '<span class="on">' + i + "</span>";
				} else {
					str += '<a href="javascript://' + i + '"><span>' + i + '</span></a>';
				}
			}

			if (_index != _pageCount) {
				str += '<a href="javascript://' + (_index+1) + '"><span>' + _next + '</span></a>';
			} else {
				str += '<span>' + _next + '</span>';
			}
		} else {
			if (_index != 1) {
				str += '<a href="javascript://' + (_index-1) + '"><span>' + _prev + '</span></a>';
			} else {
				str += '<span>' + _prev + '</span>';
			}
			for (var i = 1; i <= _pageCount; i++) {
				if (i == _index) {
					str += '<span class="on">' + i + "</span>";
				} else {
					str += '<a href="javascript://' + i + '"><span>' + i + '</span></a>';
				}
			}
			if (_index != _pageCount) {
				str += '<a href="javascript://' + (_index+1) + '"><span>' + _next + '</span></a>';
			} else {
				str += '<span>' + _next + '</span>';
			}
		}
		//str += ' 一共' + _pageCount + '页, ' + _itemCount + '条记录 ';
		str += '<div class="pageNum"><div>';
		str += '到第<input type="text" class="ipt1" name="page" id="jumpPage">页<input type="button" class="ipt2" value="" id="jumpBtn">';
		str += '</div></div>';
		str += '</div>';

		self.container.innerHTML = str;
	};

};
