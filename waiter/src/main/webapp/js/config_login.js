'use strict';
angular.module('wxConfig', []).factory('settings',
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
		}).config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
			$ocLazyLoadProvider.config({
						events : true,
						debug : true,
						cache : false,
						cssFilesInsertBefore : 'load_css_before'
					});
		}]).config(
		function($stateProvider, $locationProvider, $urlRouterProvider) {
			$stateProvider.state('login', {
				url : '/login',
				templateUrl : baseURL + 'goto?t=admin/login/login.html',
				controller : 'LoginCtrl',
				resolve : {
					deps : ['$ocLazyLoad', 'settings',
							function($ocLazyLoad, settings) {
								var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
								return $ocLazyLoad.load([{
									name : 'wxApp.login',
									files : [baseURL
											+ 'goto?t=admin/login/js/login.js']
								}]);
							}]
				}
			})
			$urlRouterProvider.otherwise('login');
		}).run(["$rootScope", "settings", "$state",
		function($rootScope, settings, $state, $location) {
			$rootScope.$state = $state;
			$rootScope.$location = $location;
			$rootScope.settings = settings;
		}]);