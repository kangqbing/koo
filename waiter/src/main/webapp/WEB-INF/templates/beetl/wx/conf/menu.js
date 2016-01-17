.state('index', {
	url : '/index',
	templateUrl : '${base}/goto?t=wx/index/index.html',
	controller : 'indexCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
					var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
					return $ocLazyLoad.load([{
								name : 'wxApp.index',
								files : ['${base}/goto?t=wx/index/index.js']
							}]);
				}]
	}
})