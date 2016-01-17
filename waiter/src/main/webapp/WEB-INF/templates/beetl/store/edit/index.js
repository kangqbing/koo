"use strict";
(function() {
	angular.module('blankonApp.store', []).controller('storeCtrl',
			function($scope, $rootScope, $http, $stateParams, settings) {
				$scope.stateParams = $stateParams;
				$http.get('${base}/store/id/' + $stateParams.id).success(
						function(data) {
							$scope.store = data;
						}).error(function(data, status, headers, config) {
							$scope.store = {};
						});
				$scope.model = [{
							id : 46,
							name : '火锅'
						}, {
							id : 56,
							name : '川湘菜'
						}];
			}).directive('imageClick', function() {
		return {
			restrict : 'A',
			link : function(scope, element) {
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
											clickFn : function(url, title,
													width, height, border,
													align) {
												KindEditor('#ImagUrl').val(url);
												$("#img_src").attr("src", url);
												scope.store.avatar=url;
												editor.hideDialog();
											}
										});
							});

				});
			}
		}
	}).directive('validationWizard', function($http,$rootScope) {
		return {
			restrict : 'A',
			link : function(scope, element) {
				var $validator = $("#form-wizard").validate({
					messages : {
						name : '该项不能为空  '
					},
					highlight : function(element) {
						$(element).parents('.form-group')
								.addClass('has-error has-feedback');
					},
					unhighlight : function(element) {
						$(element).parents('.form-group')
								.removeClass('has-error');
					},
					submitHandler : function() {
						$http.post("${base}/store/update", scope.store).success(
								function(json) {
									$rootScope.$state.go('storelist');
								});
					}
				});
				element.bootstrapWizard({
							'onNext' : function(tab, navigation, index) {
								var $valid = $("#form-wizard").valid();
								if (!$valid) {
									$validator.focusInvalid();
									return false;
								}
							},
							onTabClick : function(tab, navigation, index) {
								var $valid = $("#form-wizard").valid();
								if (!$valid) {
									$validator.focusInvalid();
									return false;
								}
							}
						});
			}
		}
	})
})();
