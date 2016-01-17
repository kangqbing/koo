<body data-ng-controller="wxCtrl" class="page-session page-header-fixed page-footer-fixed page-sidebar-fixed">
	<section id="wrapper">
			<div data-ui-view class="body-content animated fadeIn"></div>
	</section>

	<!-- START @CORE PLUGINS -->
	<script
		src="${base}/assets/global/plugins/bower_components/jquery/dist/jquery.min.js"></script>

	<script
		src="${base}/assets/global/plugins/bower_components/jquery-cookie/jquery.cookie.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- -->

	<!--/ END CORE PLUGINS -->
	<!-- BEGIN @CORE ANGULARJS PLUGINS -->
	<script
		src="${base}/assets/global/plugins/bower_components/angular/angular.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/angular/angular-sanitize.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/angular/angular-touch.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/angular/angular-animate.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>
	<!-- END @CORE ANGULARJS PLUGINS -->

	<!-- START @PAGE LEVEL SCRIPTS -->
	<script type="text/javascript">
		var baseURL = '${ctx}';
	</script>
	<script src="${base}/js/app_login.js"></script>
	<script src="${base}/js/config_login.js"></script>
	<script src="${base}/js/controllers_login.js"></script>
</body>