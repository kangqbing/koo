.state('bill', {
	url : '/bill',
	templateUrl : 'goto?t=bill/index.html',
	data : {
		pageTitle : '订单管理',
		pageHeader : {
			title : '订单管理',
			icon : 'fa fa-home',
			subtitle : '订单'
		}
	},
	ncyBreadcrumb : {
		label : '订单'
	},
	controller : 'BillCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
					var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
					return $ocLazyLoad.load([{
								name : 'blankonApp.bill',
								files : ['goto?t=bill/index.js']
							}]);
				}]
	}
})

.state('storelist', {
	url : '/storelist',
	templateUrl : 'goto?t=store/index.html',
	data : {
		pageTitle : '门店',
		pageHeader : {
			title : '门店管理',
			icon : 'fa fa-home',
			subtitle : '门店'
		}
	},
	ncyBreadcrumb : {
		label : '门店'
	},
	controller : 'storelistCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
			var pluginPath = settings.pluginPath;
			return $ocLazyLoad.load([{
				insertBefore : '#load_css_before',
				files : [
						pluginPath + '/datatables/css/dataTables.bootstrap.css',
						pluginPath
								+ '/datatables/css/datatables.responsive.css']
			}, {
				files : [pluginPath + '/checklist/checklist-model.js',
						pluginPath + '/datatables/js/jquery.dataTables.min.js',
						pluginPath + '/datatables/js/dataTables.bootstrap.js',
						pluginPath + '/datatables/js/datatables.responsive.js']
			}, {
				name : 'blankonApp.storelist',
				files : ['goto?t=store/index.js']
			}]);

		}]
	}
}).state('menulist', {
	url : '/menulist',
	templateUrl : 'goto?t=menu/list/index.html',
	data : {
		pageTitle : '所有菜品',
		pageHeader : {
			title : '菜品管理',
			icon : 'fa fa-home',
			subtitle : '所有菜品'
		}
	},
	ncyBreadcrumb : {
		label : '所有菜品'
	},
	controller : 'menulistCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
			var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
			return $ocLazyLoad.load([{
						insertBefore : '#load_css_before',
						files : [pluginPath + '/ng-table/ng-table.min.css']
					},{
				insertBefore : '#load_css_before',
				files : [pluginPath + '/KindEditor/css.css',
						pluginPath + '/KindEditor/themes/default/default.css']
			}, {
						files : [pluginPath + '/KindEditor/kindeditor-min.js',
								pluginPath
										+ '/jquery-validation/dist/jquery.validate.min.js',
								pluginPath
										+ '/ng-table/angular-resource.min.js']
					}, {
						files : [
								pluginPath
										+ '/jquery-validation/dist/validate.model.js',
								pluginPath + '/checklist/checklist-model.js',

								pluginPath + '/ng-table/ng-table.min.js'

						]
					}, {
						name : 'blankonApp.menulist',
						files : ['goto?t=menu/list/index.js']
					}]);
		}]
	}
})

.state('menuattr', {
	url : '/menuattr',
	templateUrl : 'goto?t=menu/attr/index.html',
	data : {
		pageTitle : '菜品属性',
		pageHeader : {
			title : '菜品管理',
			icon : 'fa fa-home',
			subtitle : '菜品属性'
		}
	},
	ncyBreadcrumb : {
		label : '菜品属性'
	},
	controller : 'menuattrCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
					var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
					return $ocLazyLoad.load([{
								name : 'blankonApp.menuattr',
								files : ['goto?t=menu/attr/index.js']
							}]);
				}]
	}
}).state('menugood', {
	url : '/menugood',
	templateUrl : 'goto?t=menu/good/index.html',
	data : {
		pageTitle : '菜品分类',
		pageHeader : {
			title : '菜品管理',
			icon : 'fa fa-home',
			subtitle : '菜品分类'
		}
	},
	ncyBreadcrumb : {
		label : '菜品分类'
	},
	controller : 'menugoodCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
			var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
			return $ocLazyLoad.load([{
						insertBefore : '#load_css_before',
						files : [pluginPath + '/ng-table/ng-table.min.css']
					}, {
						files : [
								pluginPath
										+ '/jquery-validation/dist/jquery.validate.min.js',
								pluginPath
										+ '/jquery-validation/dist/validate.model.js',
								pluginPath + '/checklist/checklist-model.js',
								pluginPath
										+ '/ng-table/angular-resource.min.js',
								pluginPath + '/ng-table/ng-table.min.js'

						]
					}, {
						name : 'blankonApp.menugood',
						files : ['goto?t=menu/good/index.js']
					}]);
		}]
	}
})

.state('store', {
	url : '/store/:id',
	templateUrl : 'goto?t=store/edit/index.html',
	data : {
		pageTitle : '门店修改',
		pageHeader : {
			title : '门店管理',
			icon : 'fa fa-home',
			subtitle : '门店修改'
		}
	},
	ncyBreadcrumb : {
		label : '门店修改',
		parent : 'storelist'
	},
	controller : 'storeCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
			var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
			return $ocLazyLoad.load([{
				insertBefore : '#load_css_before',
				files : [pluginPath + '/KindEditor/css.css',
						pluginPath + '/KindEditor/themes/default/default.css']
			}, {
				name : 'blankonApp.store',
				files : [
						pluginPath
								+ '/jquery-validation/dist/jquery.validate.min.js',
						pluginPath
								+ '/twitter-bootstrap-wizard/jquery.bootstrap.wizard.min.js',
						pluginPath + '/KindEditor/kindeditor-min.js',
						pluginPath + '/jquery-form/jquery.form.js',
						'goto?t=store/edit/index.js']
			}]);
		}]
	}
})

.state('newstore', {
	url : '/newstore',
	templateUrl : 'goto?t=store/newstore/index.html',
	data : {
		pageTitle : '门店新增',
		pageHeader : {
			title : '门店管理',
			icon : 'fa fa-home',
			subtitle : '门店新增'
		}
	},
	ncyBreadcrumb : {
		label : '门店新增',
		parent : 'storelist'
	},
	controller : 'newstoreCtrl',
	resolve : {
		deps : ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
			var cssPath = settings.cssPath, pluginPath = settings.pluginPath;
			return $ocLazyLoad.load([{
				insertBefore : '#load_css_before',
				files : [pluginPath + '/KindEditor/css.css',
						pluginPath + '/KindEditor/themes/default/default.css']
			}, {
				name : 'blankonApp.newstore',
				files : [
						pluginPath
								+ '/jquery-validation/dist/jquery.validate.min.js',
						pluginPath
								+ '/twitter-bootstrap-wizard/jquery.bootstrap.wizard.min.js',
						pluginPath + '/KindEditor/kindeditor-min.js',
						pluginPath + '/jquery-form/jquery.form.js',
						'goto?t=store/newstore/index.js']
			}]);
		}]
	}
})