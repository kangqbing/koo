"use strict";
(function() {
	angular.module('wxApp.login', []).controller('LoginCtrl',
			function($scope, $http, settings) {
				$scope.checkLogin = function() {
					var t;
					$.ajax({
								type : 'post',
								url : '${base}/data/checkLogin?t='
										+ Math.random(),
								data : {
									ek : $scope.ek
								},
								dataType : "json",
								success : function(msg) {
									clearTimeout(t);
									if (msg.success) {
										window.location.href = '${base}';
									} else {
										t = setTimeout(function() {
													$scope.checkLogin();
												}, 2000);
									}
								}
							});
				}
				$http.get(settings.dataPath + '/login_ticket').success(
						function(data) {
							$scope.ek = data.t;
							$scope.ticket = data.ticket;
							$scope.checkLogin();
						}).error(function(data, status, headers, config) {
							window.location.href = '${base}';
						});
			});
})();
