.state('${tag.name}',{
  url: '/${tag.name}',
  templateUrl: '${tag.html}',
  data: {
    pageTitle: '${tag.subtitle}',
    pageHeader: {
      title: '${tag.title}',
      subtitle: '${tag.subtitle}'
    }
  },
  controller: '${tag.name}Ctrl',
  resolve: {
    deps: ['$ocLazyLoad', 'settings',
    function($ocLazyLoad, settings) {
      return $ocLazyLoad.load([{
        name: 'blankonApp.${tag.name}',
        files: ['${tag.js}']
      }]);
    }]
  }
})