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
angular.module('wxApp', ['ui.router','oc.lazyLoad','wxConfig', 'wxDirective', 'wxController']);
var log = function(msg) {
	console.log(msg);
}