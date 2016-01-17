"use strict";
(function() {

	angular.module('blankonApp.menugood',
			["checklist-model", 'ngTable', 'ngResource','validate-model']).controller(
			'menugoodCtrl',
			function($scope, $http, settings, NgTableParams,
					ngTableDefaults, $rootScope, $resource) {
				$scope.good = {};
				$scope.isshow = 0;
				$scope.firstLoad = false;
				$scope.reload = function() {
					$scope.tableParams.parameters({
								storeid : $scope.storeid
							}, true);
					$scope.tableParams.reload();
				}
				$scope.$watch('storeid', function(storeid) {
							if (typeof(storeid) == "undefined") {
								storeid = 0;
								if ($scope.firstLoad) {
									$scope.isshow = 0;
									$scope.reload();
								}
								if (!$scope.firstLoad)
									$scope.firstLoad = true;
							} else {
								if ($scope.firstLoad) {
									$scope.isshow = 0;
									$scope.reload();
								}
							}
						});
				$scope.edit = function(goodid) {
					$scope.good = {
						name : goodid.name,
						radio_store : "1",
						storeid : [goodid.store_id],
						sort : goodid.sort
					};
					$scope.isshow = 1;
				}
				$scope.del = function(goodid) {
					$http.post("${base}/good/del", goodid).success(
							function(list) {
								$scope.reload();
							});
				}
				$http.get("${base}/store/list").success(function(list) {
							$scope.allstore = list;
						});

				var Api = $resource("${base}/good/list");
				$scope.tableParams = new NgTableParams({}, {
							getData : function(params) {
								return Api.get(params.url()).$promise.then(
										function(data) {
											params.total(data.recordsTotal);
											return data.data;
										});
							}
						});
				$scope.tableParams.parameters({
							storeid : $scope.storeid
						}, true);
						
				$scope.soto=function(){
						if ($scope.good.radio_store == 0) {
							$scope.good.storeid = [0];
						}
						$http.post("${base}/good/save", $scope.good).success(
								function(json) {
									$scope.isshow = 0;
									$scope.reload();
								});
					
				}

			})
})();