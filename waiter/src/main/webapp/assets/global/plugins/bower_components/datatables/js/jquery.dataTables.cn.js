$(function() {
			$.extend(true, $.fn.dataTable.defaults, {
						"oLanguage" : {
							"sLengthMenu" : "显示 _MENU_ 条记录",
							"oPaginate" : {
								"sFirst" : "第一页",
								"sLast" : "最后一页",
								"sNext" : "下一页",
								"sPrevious" : "上一页"
							},
							"sEmptyTable" : "未有查询结果",
							"sInfo" : "显示从 _START_ 到 _END_ 条,共 _TOTAL_ 条",
							"sInfoEmpty" : "显示从 0 到 0 条,共 0 条",
							"sInfoFiltered" : "(过滤完成 ,共计 _MAX_ 条数据)",
							"sLengthMenu" : "显示 _MENU_ 条",
							"sLoadingRecords" : "加载中...",
							"sProcessing" : "加载中...",
							"sSearch" : "查询:",
							"sZeroRecords" : "没有找到匹配的数据"
						}
					});
		})
