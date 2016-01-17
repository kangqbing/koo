"use strict";
(function() {
	Array.prototype.remove = function(b) {
		var a = this.indexOf(b);
		if (a >= 0) {
			this.splice(a, 1);
			return true;
		}
		return false;
	};
	angular.module('blankonApp.menuattr', []).controller('menuattrCtrl',
			function($scope, $http, settings) {
				$scope.Tattrs = [{}];
				$scope.Sattrs = [{}];
				
				$http.get("${base}/attr/get").success(function(json) {
							if (json) {
								if (json.tastelist && json.tastelist.length > 0)
									$scope.Tattrs = json.tastelist;
								else
									$scope.Tattrs = [{}];
								if (json.sizelist && json.sizelist.length > 0)
									$scope.Sattrs = json.sizelist
								else
									$scope.Sattrs = [{}];
							}
						});
				$scope.Sadd = function(e) {
					$scope.Sattrs.push({});
				}
				$scope.Sdel = function(e) {
					$scope.Sattrs.remove(e.attr);
					if ($scope.Sattrs.length == 0) {
						$scope.Sattrs.push({});
					}
				}
				$scope.Tadd = function(e) {
					$scope.Tattrs.push({});
				}
				$scope.Tdel = function(e) {
					$scope.Tattrs.remove(e.attr);
					if ($scope.Tattrs.length == 0) {
						$scope.Tattrs.push({});
					}
				}
				$scope.save = function() {
					$http.post("${base}/attr/save", {
								t : $scope.Tattrs,
								s : $scope.Sattrs
							}).success(function(json) {
								if (json.tastelist && json.tastelist.length > 0)
									$scope.Tattrs = json.tastelist;
								else
									$scope.Tattrs = [{}];
								if (json.sizelist && json.sizelist.length > 0)
									$scope.Sattrs = json.sizelist
								else
									$scope.Sattrs = [{}];

							});
				}
			})
})();