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

    function switchSortLinks($link) {
        var $image = $link.find('img');
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
                break;
        }
    }

    var $collectButton = $('#collectButton');
    var $refreshButton = $('#refreshButton');
    var $subCategoryLinks = $('div.search > div.subCategory a');
    var $sortLinks = $('div.sort > div.links a');
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
    $sortLinks.click(function () {
        switchSortLinks($(this));
    });
    $goodsImages.hover(function () {
        $(this).addClass('deepTransparent');
    }, function () {
        $(this).removeClass('deepTransparent');
    });
    angularUtils.controller(function ($scope, $http) {
        initSnapshot();
        initBranch();
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
            var id = $this.dataOptions()['id'];
            loadHotWord(id);
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
            var url = "json/branch.json?categoryId=" + $.url.param('id');
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

        function initSnapshot() {
            $http.get("json/commoditySnapshot.json").success(function (data) {
                $scope.snapshots = data;
            });
        }

        function loadHotWord(categoryId) {
            var url = 'json/hotSearchWord.json?size=8';
            if (categoryId) {
                // TODO just for test
                //url += '&categoryId=' + categoryId;
            }
            $http.get(url).success(function (data) {
                $scope.hotWords = data;
                data[0].color = 'red';
                data[3].color = 'red';
                data[7].color = 'red';
            });
        }
    });
})();
function showBranchBorder(element) {
    getParent($(element), 'div').prev().show();
}
function hideBranchBorder(element) {
    getParent($(element), 'div').prev().hide();
}
