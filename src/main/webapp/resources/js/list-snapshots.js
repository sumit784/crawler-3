;
(function () {
    $('div.goods div.sort div.title div').click(function () {
        var $this = $(this);
        $this.siblings().css('border-bottom-width', '0');
        $this.css('border-bottom-width', '2px');
    });
})();
function initSnapshot($scope, $http) {
    $scope.switchSortLinks = function (event, orderField) {
        var $sortLinks = $('div.sort > div.links a');
        var $this = $(event.target);
        var $image = $this.find('img');
        var orderType = 'asc';
        switch ($image.attr('src')) {
            case images.unSort:
                $sortLinks.find('img').attr('src', images.unSort);
                $image.attr('src', images.arrowUp);
                break;
            case images.arrowDown:
                $image.attr('src', images.arrowUp);
                break;
            case images.arrowUp:
                $image.attr('src', images.arrowDown);
                orderType = 'desc';
                break;
        }
        loadSnapshot($scope, $http, orderField, orderType);
    };
    loadSnapshot($scope, $http, 'onShelfTime', 'desc');
}
function loadSnapshot($scope, $http, orderField, orderType) {
    var params = [];
    if ($scope.categoryId) {
        params.push("categoryId=" + $scope.categoryId);
    }
    if (orderField) {
        params.push("orderField=" + orderField);
    }
    if (orderType) {
        params.push("orderType=" + orderType);
    }
    var url = "json/commoditySnapshot.json?" + params.join('&');
    $http.get(url).success(function (data) {
        $scope.snapshots = data;
    });
}