"use strict";
(function() {
	angular.module('blankonApp.newstore', []).controller('newstoreCtrl',
			function($scope, $rootScope, $http, $stateParams, settings) {
				$scope.store={};
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
	}).directive('validationNewWizard', function($http,$rootScope) {
		return {
			restrict : 'A',
			link : function(scope, element) {
				var $validator = $("#form-wizard", element).validate({
					rules : {
						name : {
							required : true
						}
					},
					messages : {
						type_id : '该项不能为空  ',
						phone : '电话必填',
						name : {
							required : '门店名称必填'
						}
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
						$http.post("${base}/store/save", scope.store).success(
								function(json) {
									$rootScope.$state.go('store',{id:json.id});
								});
					}
				});
				element.bootstrapWizard({
							'onNext' : function(tab, navigation, index) {
								var $valid = $("#form-wizard", element).valid();
								if (!$valid) {
									$validator.focusInvalid();
									return false;
								}
							},
							onTabClick : function(tab, navigation, index) {
								var $valid = $("#form-wizard", element).valid();
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
