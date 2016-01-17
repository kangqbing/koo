/*
 * ==========================================================================
 * Template: Blankon Fullpack Admin Theme
 * ---------------------------------------------------------------------------
 * Author: Djava UI Website: http://djavaui.com Email: maildjavaui@gmail.com
 * ==========================================================================
 */

'use strict';
// =========================================================================
// BLANKON MODULE APP
// =========================================================================
angular.module('blankonApp', ['ui.router', 'oc.lazyLoad',
				'angular-loading-bar', 'ngSanitize', 'ngAnimate','ncy-angular-breadcrumb',
				'blankonConfig', 'blankonDirective', 'blankonController']);
var log = function(msg) {
	console.log(msg);
}
