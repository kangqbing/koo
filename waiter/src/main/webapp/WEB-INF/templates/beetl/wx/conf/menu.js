.state('index', {
	url : '/index',
	templateUrl : 'goto?t=wx/index/index.html',
	controller : 'indexCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
					var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
					return $ocLazyLoad.load([{
								name : 'wxApp.index',
								files : ['goto?t=wx/index/index.js']
							}]);
				}]
	}
})