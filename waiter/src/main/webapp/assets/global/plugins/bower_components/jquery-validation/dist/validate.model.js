angular.module('validate-model', []).directive('validateModel',
		function() {
			return {
				restrict : 'A',
				link : function($scope, element, attrs) {
					var $validator = $(element).validate({
						highlight : function(element) {
							$(element).parents('.form-group').addClass('has-error has-feedback');
						},
						unhighlight : function(element) {
							$(element).parents('.form-group').removeClass('has-error has-feedback');
						},
						submitHandler : function() {
							$scope[attrs.validateModel]();
						}
					});
				}
			};
		});