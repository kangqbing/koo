"use strict";
(function() {
	angular.module('blankonApp.storelist', []).controller('storelistCtrl',
			function($scope, $http, settings) {

			}).directive('datatableAjax', function($rootScope, $http) {
		return {
			restrict : 'A',
			link : function($scope, element) {
				var responsiveHelperAjax = undefined, breakpointDefinition = {
					tablet : 1024,
					phone : 480
				}, tableAjax = element;
				$scope.grid = tableAjax.dataTable({
					"bAutoWidth" : false,
					"bProcessing" : false,
					"bServerSide" : true,
					"ordering" : false,
					"searching" : true,
					stateSave : false,
					"ajax" : {
						"url" : "${base}/store/list_grid",
						"type" : "POST"
					},
					"columns" : [{
								"width" : "30%",
								"data" : "name"
							}, {
								"width" : "30%",
								"data" : "phone"
							}, {
								"width" : "10%",
								"data" : "id"
							}, {
								"width" : "30%",
								"data" : "id",
								render : function(data, type, full, meta) {
									var $edit = $('<a data-store-edit class="btn btn-sm btn-primary btn-xs btn-push"><i class="fa fa-pencil"></i> 修改</a>');
									$edit.attr("href", "#/store/" + full.id);
									var $del = $('<a data-store-del href="#" class="btn btn-sm btn-danger btn-xs btn-push"><i class="fa fa-trash"></i> 删除</a>');
									$del.attr("data", full.id);
									return $edit.prop('outerHTML')
											+ $del.prop('outerHTML');
								}
							}],
					preDrawCallback : function() {
						if (!responsiveHelperAjax) {
							responsiveHelperAjax = new ResponsiveDatatablesHelper(
									tableAjax, breakpointDefinition);
						}
					},
					rowCallback : function(nRow) {
						responsiveHelperAjax.createExpandIcon(nRow);
					},
					drawCallback : function(oSettings) {
						responsiveHelperAjax.respond();
						$("[data-store-del]").each(function() {
							$(this).off('click').on("click",function() {
								$http.post("${base}/store/del", {
											id : $(this).attr("data")
										}).success(function() {
											$scope.grid.api(true).ajax.reload();
										})
							})
						});

					}
				});
			}
		}
	})
})();
