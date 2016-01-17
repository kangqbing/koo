'use strict';
(function(){
angular.module('wxDirective', [])
    .directive('tooltip', function(){
        return {
            restrict: 'A',
            link: function(scope, element, attrs){
                $(element).hover(function(){
                    $(element).tooltip('show');
                }, function(){
                    $(element).tooltip('hide');
                });
            }
        };
    })
    .directive('a', function(){
        return {
            restrict: 'E',
            link: function(scope, element, attrs) {
                if (attrs.ngClick || attrs.href === '' || attrs.href === '#' || element.data('toggle') || element.data('slide')) {
                    element.on('click', function(e) {
                        e.preventDefault(); // prevent link click for above criteria
                    });
                }
            }
        }
    })
})();