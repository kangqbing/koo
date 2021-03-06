'use strict';
angular.module('blankonConfig', []).factory('settings',
		['$rootScope', function($rootScope) {
			var settings = {
				baseURL : baseURL,
				pluginPath : baseURL + 'assets/global/plugins/bower_components',
				pluginCommercialPath : baseURL + 'assets/commercial/plugins',
				globalImagePath : baseURL + 'img',
				adminImagePath : baseURL + 'assets/admin/img',
				cssPath : baseURL + 'assets/admin/css',
				dataPath : baseURL + 'data'
			};
			$rootScope.settings = settings;
			return settings;
		}]).config(function(cfpLoadingBarProvider) {
			cfpLoadingBarProvider.includeSpinner = true;
		}).config(function($httpProvider) {
			$httpProvider.defaults.headers.common['Cache-Control'] = 'no-cache';
		}).config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
			$ocLazyLoadProvider.config({
						events : true,
						debug : true,
						cache : true,
						cssFilesInsertBefore : 'load_css_before'
					});
		}]).config(
		function($stateProvider, $locationProvider, $urlRouterProvider) {
			<!--:var _bill_="bill";if(isbill()){_bill_="bill";}else{
			 _bill_="storelist";}-->
			$urlRouterProvider.otherwise('${_bill_}');
			 $stateProvider
			 <!--:include('menu.js',{}){}-->;

		}).run(["$rootScope", "settings", "$state", '$location', 
		function($rootScope, settings, $state, $location) {
			$rootScope.$state = $state;
			$rootScope.$location = $location;
			$rootScope.settings = settings;
		}]);