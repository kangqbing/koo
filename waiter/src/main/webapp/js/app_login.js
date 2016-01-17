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
var log = function(msg) {
	console.log(msg);
}

angular.module('wxApp', ['ui.router', 'oc.lazyLoad',
				'angular-loading-bar', 'ngSanitize', 'ngAnimate',
				'wxConfig', 'wxController']);
