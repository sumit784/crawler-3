;
function initSnapshot($scope, $http) {
    $('div.goods div.sort div.title div').click(function () {
        var $this = $(this);
        if ($this.hasClass('all')) {
            $scope.inLowPrice = false;
        } else if ($this.hasClass('lowest')) {
            $scope.inLowPrice = true;
        }
        loadSnapshot($scope, $http);
        $this.siblings().css('border-bottom-width', '0');
        $this.css('border-bottom-width', '2px');
    });
    $scope.switchSortLinks = function (event, orderField) {
        var $sortLinks = $('div.sort > div.links a');
        var $this = $(event.target);
        var $image = $this.find('img');
        $scope.orderField = orderField;
        $scope.orderType = 'asc';
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
                $scope.orderType = 'desc';
                break;
        }
        loadSnapshot($scope, $http);
    };
    $scope.orderField = 'onShelfTime';
    $scope.orderType = 'desc';
    loadSnapshot($scope, $http);
}
function buildSnapshotUrl($scope, pageNumber) {
    var params = [];
    if ($scope.categoryId) {
        params.push("categoryId=" + $scope.categoryId);
    }
    if ($scope.branchId) {
        params.push('branchId=' + $scope.branchId);
    }
    if ($scope.keyWord) {
        params.push("keyWord=" + encodeURI($scope.keyWord));
    }
    if ($scope.inLowPrice) {
        params.push("inLowPrice=true");
    } else {
        params.push('inLowPrice=false');
    }
    if ($scope.orderField) {
        params.push("orderField=" + $scope.orderField);
    }
    if ($scope.orderType) {
        params.push("orderType=" + $scope.orderType);
    }
    params.push('pageNumber', pageNumber);
    return "json/commoditySnapshot.json?" + params.join('&');
}

function loadSnapshot($scope, $http) {
    var url = buildSnapshotUrl($scope, 0);
    $http.get(url).success(function (data) {
        $scope.snapshots = data;
    });
}