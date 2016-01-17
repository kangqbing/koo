<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8" data-ng-app="blankonApp"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9" data-ng-app="blankonApp"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" data-ng-app="blankonApp">
<!--<![endif]-->
<!-- START @HEAD -->
<head>
<!-- START @META SECTION -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="koo">
<title data-ng-bind="$state.current.data.pageTitle + ' | 店小二'"></title>
<!--/ END META SECTION -->

<!-- START @FAVICONS -->
<link
	data-ng-href="{{settings.globalImagePath}}/ico/apple-touch-icon-144x144-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="144x144">
<link
	data-ng-href="{{settings.globalImagePath}}/ico/apple-touch-icon-114x114-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="114x114">
<link
	data-ng-href="{{settings.globalImagePath}}/ico/apple-touch-icon-72x72-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="72x72">
<link
	data-ng-href="{{settings.globalImagePath}}/ico/apple-touch-icon-57x57-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="57x57">
<link
	data-ng-href="{{settings.globalImagePath}}/ico/apple-touch-icon.png"
	rel="shortcut icon" sizes="16x16">
<!--/ END FAVICONS -->

<!-- START @GLOBAL MANDATORY STYLES -->
<link
	data-ng-href="{{settings.pluginPath}}/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	data-ng-href="{{settings.pluginPath}}/fontawesome/css/font-awesome.min.css"
	rel="stylesheet">
<link data-ng-href="{{settings.pluginPath}}/animate.css/animate.min.css"
	rel="stylesheet">
<link
	data-ng-href="{{settings.pluginPath}}/angular-loading-bar/build/loading-bar.min.css"
	rel="stylesheet">
<!--/ END GLOBAL MANDATORY STYLES -->

<!-- START @PAGE LEVEL STYLES -->
<link id="load_css_before" />
<!--/ END PAGE LEVEL STYLES -->

<!-- START @THEME STYLES -->
<link data-ng-href="{{settings.cssPath}}/reset.css" rel="stylesheet">
<link data-ng-href="{{settings.cssPath}}/layout.css" rel="stylesheet">
<link data-ng-href="{{settings.cssPath}}/components.css" rel="stylesheet">
<link data-ng-href="{{settings.cssPath}}/plugins.css" rel="stylesheet">
<link data-ng-href="{{settings.cssPath}}/themes/angularjs-theme.css" rel="stylesheet" id="theme">
<link data-ng-href="{{settings.cssPath}}/custom.css" rel="stylesheet">
<!--/ END THEME STYLES -->

<!-- START @ANGULARJS STYLES -->
<link data-ng-href="{{settings.cssPath}}/angular-custom.css" rel="stylesheet">
<!-- END @ANGULARJS STYLES -->

<!-- START @IE SUPPORT -->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="${base}/assets/global/plugins/bower_components/html5shiv/dist/html5shiv.min.js"></script>
        <script src="${base}/assets/global/plugins/bower_components/respond-minmax/dest/respond.min.js"></script>
        <![endif]-->
<!--/ END IE SUPPORT -->
</head>
<!--/ END HEAD -->