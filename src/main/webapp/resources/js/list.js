;
(function () {
    var branchPoster = {
        speed: 500,
        show: function () {
            $branchTitle.hide();
            $branchLogo.hide();
            $branchPoster.fadeIn(this.speed);
        },
        hide: function () {
            $branchTitle.fadeIn(this.speed);
            $branchLogo.fadeIn(this.speed);
            $branchPoster.hide();
        }
    };

    var subCategoryLinks = {
        originalColor: 'rgb(102, 102, 102)',
        _unSelect: function ($target) {
            $target.removeClass('orangeBack').removeClass('selected').css('color', this.originalColor);
        },
        click: function ($link) {
            if ($link.hasClass('selected')) {
                this._unSelect($link);
                $hotWords.hide();
                branchPoster.show();
            } else {
                this._unSelect($subCategoryLinks.filter('.orangeBack'));
                $link.addClass('orangeBack').addClass('selected').css('color', '#ffffff');
                $hotWords.show();
                branchPoster.hide();
            }
        },
        over: function ($link) {
            if (!$link.hasClass('selected')) {
                $link.addClass('orangeBack').css('color', '#ffffff');
            }
        },
        off: function ($link) {
            if (!$link.hasClass('selected')) {
                this._unSelect($link);
            }
        }
    };

    var $collectButton = $('#collectButton');
    var $refreshButton = $('#refreshButton');
    var $subCategoryLinks = $('div.search > div.subCategory a');
    var $branchLogo = $('div.branch div.logos');
    var $branchTitle = $('div.branch div.title');
    var $goodsImages = $('div.goods div.images div.image img');
    var $hotWords = $('div.search div.right div.searchForm div.hotWords');
    var $branchPoster = $('div.search div.right div.branch div.poster');
    var $searchTypeList = $('div.search div.right div.searchForm div.searchType li');
    var $searchCategoryId = $('#searchCategoryId');

    $collectButton.click(function () {
    });
    $refreshButton.click(function () {
        location.reload();
    });
    $searchTypeList.click(function () {
        var $this = $(this);
        var text = $this.text();
        var $div = getParent($this, 'div');
        $div.find('span.text').text(text);
        $searchCategoryId.val($this.dataOptions()['id']);
    });
    $subCategoryLinks.hover(function () {
        subCategoryLinks.over($(this));
    }, function () {
        subCategoryLinks.off($(this));
    });

    $goodsImages.hover(function () {
        $(this).addClass('deepTransparent');
    }, function () {
        $(this).removeClass('deepTransparent');
    });
    angularUtils.controller(function ($scope, $http) {
        $scope.categoryId = $('#categoryId').val();
        initBranch();
        initSnapshot($scope, $http);
        $scope.showMore = function () {
            if ($scope.hideBranches.length == 0) {
                return;
            }
            get$HideBranch().show();
            get$MoreBranch().hide();
        };
        $scope.hideMore = function (event) {
            if ($scope.hideBranches.length == 0) {
                return;
            }

            var target = event.target;
            var $target = $(target);
            if (!($target.is('div'))) {
                return;
            }

            if ($target.hasClass("logos") || $target.hasClass("branch")
                || $target.hasClass('right') || $target.hasClass('search')
                || $target.hasClass('split')) {
                get$HideBranch().hide();
                get$MoreBranch().show();
            }
        };
        $scope.selectSubCategory = function (event) {
            var $this = $(event.target);
            $scope.categoryId = $this.dataOptions()['id'];
            loadHotWord();
            loadSnapshot($scope, $http);
            subCategoryLinks.click($this);
            event.stopPropagation();
        };

        function get$HideBranch() {
            return  $('div.search > div.right div.branch > div.logos div.hideBranch');
        }

        function get$MoreBranch() {
            return $('div.search > div.right div.branch > div.logos div.moreBranch');
        }

        function initBranch() {
            var url = "json/branch.json?categoryId=" + $scope.categoryId;
            $http.get(url).success(function (branches) {
                $scope.moreBranch = {logo: 'resources/css/images/list/more-branch.png'};
                if (branches.length > 14) {
                    $scope.showBranches = branches.slice(0, 13);
                    $scope.hideBranches = branches.slice(13);
                    $scope.moreBranch.show = true;
                } else {
                    $scope.showBranches = branches;
                    $scope.hideBranches = [];
                    $scope.moreBranch.show = false;
                }
            });
        }

        function loadHotWord() {
            var url = 'json/hotSearchWord.json?size=8&categoryId=' + $scope.categoryId;
            $http.get(url).success(function (data) {
                $scope.hotWords = data;
                if (data[0]) {
                    data[0].color = 'red';
                }
                if (data[3]) {
                    data[3].color = 'red';
                }
                if (data[7]) {
                    data[7].color = 'red';
                }
            });
        }
    });
    $('#searchInput').focus();
})();
function showBranchBorder(element) {
    getParent($(element), 'div').prev().show();
}
function hideBranchBorder(element) {
    getParent($(element), 'div').prev().hide();
}
