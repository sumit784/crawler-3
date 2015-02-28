;
(function () {
    var branchPoster = {
        speed: 500,
        show: function () {
            $elements.branchTitle.hide();
            $elements.branchLogo.hide();
            $elements.branchPoster.fadeIn(this.speed);
        },
        hide: function () {
            $elements.branchTitle.fadeIn(this.speed);
            $elements.branchLogo.fadeIn(this.speed);
            $elements.branchPoster.hide();
        }
    };

    var hotWords = {
        show: function () {
            $elements.hotWords.show();
        },
        hide: function () {
            $elements.hotWords.hide();
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
                hotWords.hide();
                branchPoster.show();
            } else {
                this._unSelect($elements.subCategoryLinks.filter('.orangeBack'));
                $link.addClass('orangeBack').addClass('selected').css('color', '#ffffff');
                hotWords.show();
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
                $elements.sortLinks.find('img').attr('src', images.unSort);
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

    var $elements = {
        collectButton: $('#collectButton'),
        refreshButton: $('#refreshButton'),
        subCategoryLinks: $('div.search > div.subCategory a'),
        sortLinks: $('div.sort > div.links a'),
        searchDiv: $('div.search'),
        branchDiv: $('div.branch'),
        branchLogo: $('div.branch div.logos'),
        branchTitle: $('div.branch div.title'),
        branchLogoTable: $('div.branch div.logos table'),
        goodsImages: $('div.goods div.images div.image img'),
        goodsDescriptions: $('div.goods div.images div.description a'),
        hotWords: $('div.search div.right div.searchForm div.hotWords'),
        branchPoster: $('div.search div.right div.branch div.poster')
    };

    $elements.collectButton.click(function () {
    });
    $elements.refreshButton.click(function () {
        location.reload();
    });
    $elements.subCategoryLinks.hover(function () {
        subCategoryLinks.over($(this));
    }, function () {
        subCategoryLinks.off($(this));
    });
    $elements.sortLinks.click(function () {
        switchSortLinks($(this));
    });
    $elements.goodsImages.hover(function () {
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
