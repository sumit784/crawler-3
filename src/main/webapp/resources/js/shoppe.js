;
(function () {
    angularUtils.controller(function ($scope, $http) {
        $scope.branchId = $.url.param('id');
        initSnapshot($scope, $http);
    });
})();