// =========================================================================
// CONTROLLER APP
// =========================================================================
'use strict';
(function() {
	angular.module('wxController', [])
	.controller('wxCtrl', function($scope, $http, settings) {
		$scope.supportIE = function() {
			var isIE8 = false;
			var isIE9 = false;
			var isIE10 = false;
			isIE8 = !!navigator.userAgent.match(/MSIE 8.0/);
			isIE9 = !!navigator.userAgent.match(/MSIE 9.0/);
			isIE10 = !!navigator.userAgent.match(/MSIE 10.0/);
			if (isIE10) {
				$('html').addClass('ie10');
			}
			if (isIE10 || isIE9 || isIE8) {
				$('html').addClass('ie');
			}
			if (isIE8 || isIE9) {
				$('input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)')
						.each(function() {
							var input = $(this);
							if (input.val() == ''
									&& input.attr("placeholder") != '') {
								input.addClass("placeholder").val(input
										.attr('placeholder'));
							}
							input.focus(function() {
										if (input.val() == input
												.attr('placeholder')) {
											input.val('');
										}
									});
							input.blur(function() {
										if (input.val() == ''
												|| input.val() == input
														.attr('placeholder')) {
											input
													.val(input
															.attr('placeholder'));
										}
									});
						});
			}
		};
		$scope.tooltip = function() {
			if ($('[data-toggle=tooltip]').length) {
				$('[data-toggle=tooltip]').tooltip({
							animation : 'fade'
						});
			}
		};
		$scope.popover = function() {
			if ($('[data-toggle=popover]').length) {
				$('[data-toggle=popover]').popover();
			}
		};
		$scope.user = {};
		$http.get(settings.dataPath + '/user') 
				.success(function(data) {
							$scope.user = data;
						}).error(function(data, status, headers, config) {
						});
		$scope.$on('ocLazyLoad.fileLoaded', function(e, file) {
					console.log('event file loaded', file);
				});
		$scope.supportIE(); 
		$scope.tooltip(); 
		$scope.popover(); 
	});
})();
