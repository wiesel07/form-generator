/**
 * wuj 20180821
 */
var prefix = "/activity";
$(function() {
	
});


function start(){
	layer.confirm('确定录入表单？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url :  prefix + "/start",
			type : "post",
			data :{},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg,{time:600});
				} else {
					layer.msg(r.msg,{time:600});
				}
			}
		});
	})
}

function  complete(){
	layer.confirm('确定审核表单？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url :  prefix + "/complete",
			type : "post",
			data :{},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg,{time:600});
				} else {
					layer.msg(r.msg,{time:600});
				}
			}
		});
	})
}

function code(tableName) {
	location.href = prefix + "/code/" + tableName;
}


function edit(){
	layer.open({
		type : 2,
		title : '策略编辑',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/edit'
	});
}