/**
 * wuj 20180723
 * 通用方法封装处理
 */

/** 消息状态码 */
web_status = {
    SUCCESS: '0',
    FAIL: '500'
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "success",
    FAIL: "error",
    WARNING: "warning"
};

ACTION={
		EDIT:"EDIT",
		ADD:"ADD",
		DELETE:"DELETE"
}

var app={};

window.app = app;


/**
 * bootstrap-table http://bootstrap-table.wenzhixin.net.cn/
 * 
 * @param id
 * @param options {
 *            url: "后端json接口url", toolbar: '工具栏id', permission: true|false true
 *            开启功能权限过滤 params:function(){ //返回额外参数 return {params:'params1'}; },
 *            columns:[{ field: '字段', title: '标题', width: '宽度',
 *            formatter:function (value,row,index) { //字段格式化 return ''; } }] }
 */
app.table = function (id, options) {
    var defaultOptions = {
		method: 'post',
        striped: false,                      // 是否显示行间隔色
        cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   // 是否显示分页（*）
        sortable: false,                     // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       // 初始化加载第一页，默认第一页
        pageSize: 10,                       // 每页的记录行数（*）
        pageList: [10, 15, 20, 100],        // 可供选择的每页的行数（*）
        classes:"table table-no-bordered",
        // search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  // 是否显示所有的列
        showRefresh: false,                  // 是否显示刷新按钮
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,                // 是否启用点击选中行
        // height:window.innerHeight-160, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     // 每一行的唯一标识，一般为主键列
        // showToggle:true, //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    // 是否显示详细视图
        detailView: false,                   // 是否显示父子表
        maintainSelected:false,             //设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
        undefinedText:'-',                   // 当数据为 undefined 时显示的字符
        exportTypes:[ 'csv', 'excel'], // 导出文件类型 ，支持多种类型文件导出
        // 请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求
        trimOnSearch:true,// 将自动去掉搜索字符的前后空格。
        queryParams: function (params) {
            if (params.limit){
                var pageParams = {
                    // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    pageNo: params.offset/params.limit+1,  // 页码
                    pageSize: params.limit   // 页面大小
                };
                $.extend(params,pageParams);
            }
            if (options.params){
                $.extend(params,options.params());
            }
            return params;
        },// 传递参数（*）
        responseHandler:function (result) {
            return result.data;
        },
        onLoadSuccess:function () {
            
        },
        onColumnSwitch: function (field, checked) {
           
        }
    };
    options.url = app.apiRoot()+options.url;
    $.extend(defaultOptions,options);
    var $table = $(id);
    $table.bootstrapTable(defaultOptions);
};



/**
 * api根url地址
 * 
 * @return {string|*}
 */
app.apiRoot = function () {
    if(app.rootUrl){
        return app.rootUrl;
    }
    var js=document.scripts;
    var jsroot='';
    for(var i=js.length;i>0;i--){
        jsroot=js[i-1].src;
//        if(jsroot.indexOf("/js/common")>-1){
//            jsroot=jsroot.substring(0,jsroot.indexOf("/js/common"));
//            break;
//        }
        if(jsroot.indexOf("/js/plugins")>-1){
            jsroot=jsroot.substring(0,jsroot.indexOf("/js/plugins"));
            break;
        }
    }
    app.rootUrl = jsroot;
    return app.rootUrl;
};

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

// 消息窗体
app.modalMsg = function(content, type) {
    if (type != undefined) {
        var icon = "";
        if (type == modal_status.WARNING) {
            icon = 0;
        }
        else if (type == modal_status.SUCCESS) {
            icon = 1;
        }
        else if (type == modal_status.FAIL) {
            icon = 2;
        }
        layer.msg(content, { icon: icon, time: 2000, shift: 0 });
        $(".layui-layer-msg").find('i.' + icon).parents('.layui-layer-msg').addClass('layui-layer-msg-' + type);
    } else {
        layer.msg(content);
    }
}
// 弹出窗体
app.modalAlert = function(content, type) {
    var icon = "";
    if (type == modal_status.WARNING) {
        icon = 0;
    } else if (type == modal_status.SUCCESS) {
        icon = 1;
    } else if (type == modal_status.FAIL) {
        icon = 2;
    } else {
        icon = 3;
    }
    layer.alert(content, {
        icon: icon,
        title: "系统提示",
        btn: ['确认'],
        btnclass: ['btn btn-primary'],
    });
}
// 确认窗体
app.modalConfirm = function (content, callBack) {
        layer.confirm(content, {
        icon: 3,
        title: "系统提示",
        btn: ['确认', '取消'],
        btnclass: ['btn btn-primary', 'btn btn-danger'],
    }, function () {
        callBack(true);
    }, function () {
        // callBack(false)
    });
}
// 关闭窗体
app.modalClose = function () {
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    var $IsdialogClose = top.$("#layui-layer" + index).find('.layui-layer-btn').find("#IsdialogClose");
    var IsClose = $IsdialogClose.is(":checked");
    if ($IsdialogClose.length == 0) {
        IsClose = true;
    }
    if (IsClose) {
    	parent.layer.close(index);
    } else {
    	parent.location.reload();
    }
}
// 刷新父窗体
app.parentReload = function () {
	parent.location.reload();
	//parent.reLoad();
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index)
    return false;
}



/** 弹出层指定宽度 */
app.layer_show= function(options) {
	var defaultOptions={
			type: 2,
			area:["800px",($(window).height() - 50)+"px"],
			fix: false,
	        // 不固定
	        maxmin: true,
	       // shade: 0,
	        shadeClose:false,// 点击遮罩关闭层
			title:false,
			content:"404.html"
	};
	$.extend(defaultOptions, options);
    layer.open(defaultOptions);
}

/** 关闭弹出框口 */
app.layer_close= function() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

/** 对ajax的post方法再次封装 */
app.doSave = function(options) {
	var msg ="操作成功,正在刷新数据请稍后……";
	if(!options.msg){
		if(options.action){
			switch(options.action){
				case ACTION.ADD:msg='新增成功,正在刷新数据请稍后……';
					break;
				case ACTION.EDIT:msg='修改成功,正在刷新数据请稍后……';
				break;
				case ACTION.DELETE:msg='删除成功,正在刷新数据请稍后……';
				break;
				default:'操作成功,正在刷新数据请稍后……'
			}
		}
		
	}
	
	var defaultOptions = {
			   cache : true,
		        url: '',
		        type: "post",
		        dataType: "json",
		        data: '',
		        async : false,
				error : function(request) {
					app.modalAlert("系统错误", modal_status.FAIL);
				},
		        success: function(result) {
		        	if (result.code == web_status.SUCCESS) {
						parent.layer.msg(msg,{icon:1,time: 800,shade: [0.1,'#fff']},function(){
							app.parentReload();
						});
					} else {
						app.modalAlert(result.msg, modal_status.FAIL);
					}
		        }
		    };
	$.extend(defaultOptions, options);
    $.ajax(defaultOptions);
};

/** 对jquery的ajax方法再次封装 */
app._ajax = function(options) {
    var defaultOptions = {
        url:'',
        type: "POST",
        dataType: "json",
        timeout:30000,
        data: {},
        async:true,
        success: function(result) {
            app.simpleSuccess(result);
        },
        error : function(request) {
			parent.layer.msg("系统错误，联系管理员");
		}
    };
	$.extend(defaultOptions, options);
    $.ajax(defaultOptions)
};

/** 返回结果处理 */
app.simpleSuccess=function(result) {
    if (result.code == web_status.SUCCESS) {
		app.modalMsg(result.msg, modal_status.SUCCESS);
		app.refreshTable();
    } else {
    	app.modalAlert(result.msg, modal_status.FAIL);
    }
}


// 刷新bootstrap table数据
app.refreshTable = function () {
    $('.bootstrap-table').bootstrapTable('refresh');
}
// 获取bootstrap table选中项
app.getTableSelections = function (_id) {
    return $.map($('.bootstrap-table').bootstrapTable('getSelections'), function (row) {
        return row[_id];
    });
}

/**
 * query查询
 * 
 * @param id
 * @param params {
 *            params1:'params1', params2:'params2' }
 */
app.tableQuery = function (params) {
	$('.bootstrap-table').bootstrapTable('refresh', {
        pageNumber: 1,
        query:params
    });
};


/**
 * query查询(传入新url)
 * 
 * @param id
 * @param newUrl
 */
app.tableQueryUrl = function (newUrl) {
    $('.bootstrap-table').bootstrapTable('refresh', {
        pageNumber: 1,
        url:app.apiRoot()+ newUrl
    });
};

/**
 * 获取表格行数据
 * 
 * @param id
 * @param index
 * @return {*}
 */
app.tableRow = function (index) {
    return $('.bootstrap-table').bootstrapTable('getData')[index];
};

// 获取选中复选框项
app.getCheckeds = function (_name) {
	var checkeds=[];
    $('input:checkbox[name="' + _name + '"]:checked').each(function(i) {
//        if (0 == i) {
//        	arrCheckeds.push($(this).val());
//        } else {
//        	checkeds += ("," + $(this).val());
//        }
    	checkeds.push($(this).val());
    });
    return checkeds;
}
// 获取选中复选框项
app.getSelects = function (_name) {
    var selects = [];
    $('#' + _name + ' option:selected').each(function (i) {
    	selects.push($(this).val());
//    	if (0 == i) {
//        	selects = $(this).val();
//        } else {
//        	selects += ("," + $(this).val());
//        }
    });
    return selects;
}

app.isEmpty = function (obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}