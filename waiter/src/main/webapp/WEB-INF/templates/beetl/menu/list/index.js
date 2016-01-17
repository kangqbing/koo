"use strict";
(function() {
	angular.module('blankonApp.menulist',
			["checklist-model", 'ngTable', 'validate-model', 'ngResource'])
			.controller(
					'menulistCtrl',
					function($scope, $http, settings, NgTableParams,
							ngTableDefaults, $rootScope, $resource, $log) {
						$scope.isshow = 0;

						$scope.open = function(v) {
							$scope.isshow = v;
							if (v == 1) {
								$scope.too = {};
								$scope.koo = {
									"store_id" : 6,
									"image_url" : "/waiter/admin/KindEditor/img?id=3"
								};
								$scope.soo = {};
								$scope.attrList = {};
							}
							if (v == 0) {
								$scope.change();
							}
						}

						$http.get("${base}/store/list").success(function(list) {
									$scope.allstore = list;
								});
						$scope.loadGood = function(storeid) {
							if (typeof(storeid) == "undefined")
								storeid = 0;
							$http.get("${base}/good/list?count=-1&storeid="
									+ storeid).success(function(list) {
										$scope.allgood = list.data;
									});
						}
						$scope.loadGood(0);

						$scope.$watch('cx.storeid', function(storeid) {
									$scope.loadGood(storeid);
								});

						$scope.change = function() {
							$scope.tableParams.parameters($scope.cx, false);
							$scope.tableParams.reload();
						}

						$scope.soto = function() {
							$http.post("${base}/good/save", {
										koo : $scope.koo,
										soo : $scope.soo,
										too : $scope.too,
										attrList : $scope.attrList
									}).success(function() {
										isshow = 0;
										$scope.change();
									})
						}

						$scope.loadGoodkoo = function(storeid) {
							if (typeof(storeid) == "undefined")
								storeid = 0;
							$http.get("${base}/good/list?count=-1&storeid="
									+ storeid).success(function(list) {
										$scope.allgoodkoo = list.data;
									});
						}

						$scope.loadAllStorekoo = function(goodid) {
							if (typeof(goodid) == "undefined")
								goodid = 0;
							$http.get("${base}/store/list_goodid/" + goodid)
									.success(function(list) {
												$scope.allstorekoo = list;
											});
						}
						$scope.loadAllStorekoo();

						$scope.allgoodkoo_change = function(item) {
							$scope.loadGoodkoo($scope.koo.store_id);
						}

						$scope.req = function() {
							if ($scope.required1 == false) {
								$scope.soo = {};
								$scope.too = {};
							};
						}
						$scope.boxchange = function(oo, vvv) {
							if (!oo.id[vvv]) {
								for (var c in $scope.attrList.price) {
									if (c.indexOf(vvv) > -1) {
										delete $scope.attrList.price[c];
									}
								}
							}
						}
						$scope.showTable = function() {
							return $scope.tooisok() || $scope.sooisok();
						}
						$scope.tooisok = function() {
							var rt = false;
							if ($scope.too) {
								if ($scope.too.id) {
									for (var id in $scope.too.id) {
										var v = $scope.too.id[id];
										if (!!v) {
											rt = true;
										} else {
											delete $scope.too.id[id];
											delete $scope.too.name[id];
											for (var c in $scope.attrList.price) {
												if (c.indexOf(id) > -1) {
													delete $scope.attrList.price[c];
												}
											}
										}
									}
								}
							}
							return rt;
						}
						$scope.sooisok = function() {
							var rt = false;
							if ($scope.too) {
								if ($scope.soo.id) {
									for (var id in $scope.soo.id) {
										var v = $scope.soo.id[id];
										if (!!v) {
											rt = true;
										} else {
											delete $scope.soo.id[id];
											delete $scope.soo.name[id];
											for (var c in $scope.attrList.price) {
												if (c.indexOf(id) > -1) {
													delete $scope.attrList.price[c];
												}
											}
										}
									}
								}
							}
							return rt;
						}

						$scope.delprice = function(key, key1) {
							if (key == 0) {
								for (var c in $scope.attrList.price) {
									if (c.indexOf(',' + key1) > -1) {
										if (c != key + ',' + key1) {
											delete $scope.attrList.price[c];
										}
									}
								}
							} else if (key1 == 0) {
								for (var c in $scope.attrList.price) {
									if (c.indexOf(key + ',') > -1) {
										if (c != key + ',' + key1) {
											delete $scope.attrList.price[c];
										}
									}
								}
							} else {
								for (var c in $scope.attrList.price) {
									if (c.indexOf(',' + key1) > -1) {
										if (c == 0 + ',' + key1) {
											delete $scope.attrList.price[c];
										}
									}
									if (c.indexOf(key + ',') > -1) {
										if (c == key + ',' + 0) {
											delete $scope.attrList.price[c];
										}
									}
								}
							}
						}
						$http.get("${base}/attr/get").success(function(list) {
									$scope.attrs = list;
								});

						var Api = $resource("${base}/menu/list");
						$scope.tableParams = new NgTableParams({}, {
									getData : function(params) {
										return Api.get(params.url()).$promise
												.then(function(data) {
													params
															.total(data.recordsTotal);
													return data.data;
												});
									}
								});
						$scope.$on('image_url', function(event, data) {
									$scope.koo.image_url = data;
								});

					}).filter('myfilter', function() {
						return function(input) {
							var out = {};
							for (var key in input) {
								if (input[key]) {
									out[key] = input[key];
								}
							}
							return out;
						}
					}).directive('imageClick', function() {
				return {
					restrict : 'A',
					link : function($scope, element) {
						var editor = KindEditor.editor({
							uploadJson : '${base}/admin/KindEditor/upload_json',
							fileManagerJson : '${base}/admin/KindEditor/file_manager_json',
							allowFileManager : true,
							langType : "zh_CN"
						});
						element.click(function() {
							editor.loadPlugin('image', function() {
										editor.plugin.imageDialog({
													imageUrl : KindEditor('#ImagUrl')
															.val(),
													clickFn : function(url,
															title, width,
															height, border,
															align) {
														if (!$scope.$parent.koo)
															$scope.$parent.koo = {};
														$("#ImagUrl").attr(
																"src", url);
														$scope.$emit(
																'image_url',
																url);

														editor.hideDialog();
													}
												});
									});

						});
					}
				}
			});
})();