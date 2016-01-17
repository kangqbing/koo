<body data-ng-controller="BlankonCtrl" class="page-session page-header-fixed page-footer-fixed page-sidebar-fixed">
<!--  page-sound -->
	<!--[if lt IE 9]>
        <p class="upgrade-browser">Upps!! You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/" target="_blank">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

	<!-- START @WRAPPER -->
	<section id="wrapper">

		<!-- START @HEADER -->
		<header data-ng-include="'${base}/partials/header'" id="header"></header>
		<!-- /#header -->
		<!--/ END HEADER -->
		<!--

            START @SIDEBAR LEFT
            |=========================================================================================================================|
            |  TABLE OF CONTENTS (Apply to sidebar left class)                                                                        |
            |=========================================================================================================================|
            |  01. sidebar-box               |  Variant style sidebar left with box icon                                              |
            |  02. sidebar-rounded           |  Variant style sidebar left with rounded icon                                          |
            |  03. sidebar-circle            |  Variant style sidebar left with circle icon                                           |
            |=========================================================================================================================|

            -->
		<aside data-sidebar-left-nicescroll data-sidebar-minimize
			data-ng-include="'${base}/partials/sidebarleft'" id="sidebar-left"
			class="sidebar-rounded"></aside>
		<!-- /#sidebar-left -->
		<!--/ END SIDEBAR LEFT -->

		<!-- START @PAGE CONTENT -->
		<section id="page-content">

			<!-- Start page header -->
			<div class="header-content"
				data-ng-include="'${base}/partials/headercontent'"></div>
			<!-- /.header-content -->
			<!--/ End page header -->

			<!-- Start body content -->
			<div data-ui-view class="body-content animated fadeIn"></div>
			<!-- /.body-content -->
			<!--/ End body content -->

			<!-- Start footer content -->
			<footer class="footer-content" data-ng-include="'${base}/partials/footer'"></footer>
			<!-- /.footer-content -->
			<!--/ End footer content -->

		</section>
		<!-- /#page-content -->
		<!--/ END PAGE CONTENT -->

		<!-- START @SIDEBAR RIGHT -->
		<aside data-choose-themes data-navbar-color data-sidebar-color
			data-layout-setting data-ng-include="'${base}/partials/sidebarright' "
			id="sidebar-right"></aside>
		<!-- /#sidebar-right -->
		<!--/ END SIDEBAR RIGHT -->

	</section>
	<!-- /#wrapper -->
	<!--/ END WRAPPER -->

	<!-- START @BACK TOP -->
	<div data-back-top id="back-top" class="animated pulse circle">
		<i class="fa fa-angle-up"></i>
	</div>
	<!-- /#back-top -->
	<!--/ END BACK TOP -->

	<!-- START JAVASCRIPT SECTION (Load javascripts at bottom to reduce load time) -->

	<!-- START @CORE PLUGINS -->
	<script
		src="${base}/assets/global/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/jquery-cookie/jquery.cookie.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/ionsound/js/ion.sound.min.js"></script>
	<!-- -->
	<script
		src="${base}/assets/global/plugins/bower_components/jquery-nicescroll/jquery.nicescroll.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/jquery-easing-original/jquery.easing.1.3.min.js"></script>
	<script
		src="${base}/assets/global/plugins/bower_components/bootbox/bootbox.js"></script>

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
		<script
		src="${base}/assets/global/plugins/bower_components/angular-breadcrumb/angular-breadcrumb.js"></script>
	
		
		
	<!-- END @CORE ANGULARJS PLUGINS -->

	<!-- START @PAGE LEVEL SCRIPTS -->
	<script type="text/javascript">
		var baseURL = '${ctx}';
	</script>
	<script src="${base}/goto?t=conf/app.js"></script>
	<script src="${base}/goto?t=conf/config.js"></script>
	<script src="${base}/goto?t=conf/directives.js"></script>
	<script src="${base}/goto?t=conf/controllers.js"></script>

</body>