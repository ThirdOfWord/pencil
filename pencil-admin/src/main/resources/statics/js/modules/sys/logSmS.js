$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/myLogSms/list',
        datatype: "json",
        colModel: [
			{ label: '短信号码', name: 'smsphonenumber',width: 120},
			{ label: '发送者ID', name: 'smsuserid',width: 80},
			{ label: '短信内容', name: 'smscontent',width: 280},
			{ label: '短信返回值', name: 'smsreturncode',width: 50,formatter: function(value){
             if (value==1){
                 return '成功';
             } else {
                 return '失败';
             }
            }},
            { label: '验证码', name: 'smscode',width: 70},
            { label: '短信的接口', name: 'smsfunc', width: 80 },
			{ label: 'IP地址', name: 'smsip',width: 110},
			{ label: '创建时间', name: 'createtime',width: 110}
        ],
		viewrecords: true,
        height: "100%",
        width:"100%",
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        myLogSms:{
            smsPhoneNumber: null
		},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'key': vm.myLogSms.smsPhoneNumber},
                page:page
            }).trigger("reloadGrid");
		}
	}
});