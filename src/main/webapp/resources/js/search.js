;
(function () {
    angularUtils.controller(function ($scope, $http) {
        $scope.keyWord = $.url.param('keyWord');
        var categoryId = $.url.param('categoryId');
        if ($.trim(categoryId) != "") {
            $scope.categoryId = categoryId;
        }
        initSnapshot($scope, $http);
    });
})();