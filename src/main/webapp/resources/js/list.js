;
(function () {
    function getCategoryId() {
        return $('#categoryId').val();
    }

    var branchPoster = {
        speed: 500,
        $div: $('div.search div.right div.branch div.poster'),
        show: function () {
            $branchTitle.hide();
            $branchLogo.hide();
            this.$div.fadeIn(this.speed);
        },
        hide: function () {
            $branchTitle.fadeIn(this.speed);
            $branchLogo.fadeIn(this.speed);
            this.$div.hide();
        },
        init: function () {
            var $div = this.$div;
            var $a = $div.parent();
            $.get('json/category-poster.json', {
                'categoryId': getCategoryId()
            }, function (data) {
                if (data.length > 0) {
                    loadPoster(data[0]);
                    if (data.length > 1) {
                        var posterIndex = 0;
                        setInterval(function () {
                            if (posterIndex < data.length - 1) {
                                posterIndex++;
                            } else {
                                posterIndex = 0;
                            }
                            loadPoster(data[posterIndex]);
                        }, 3000);
                    }
                }
            });

            function loadPoster(posterObject) {
                $div.css('background-image', 'url(' + posterObject.path + ')');
                if (posterObject.link != null && $.trim(posterObject.link) != '') {
                    $a.attr('href', posterObject.link).css('cursor', 'pointer');
                } else {
                    $a.attr('href', 'javascript:void(0)').css('cursor', 'default');
                }
            }
        }
    };
    branchPoster.init();

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

    $collectButton.click(function () {
    });
    $refreshButton.click(function () {
        location.reload();
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
        $scope.inLowPrice = true;
        $scope.categoryId = getCategoryId();
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
            if ($this.hasClass('selected')) {
                $scope.categoryId = getCategoryId();
            } else {
                $scope.categoryId = $this.dataOptions()['id'];
            }
            loadHotWord();
            loadSnapshot($scope, $http);
            initBranch();
            subCategoryLinks.click($this);
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
                $.each($scope.hotWords, function () {
                    if (this['hot']) {
                        this['style'] = 'noLineAnchor red';
                    } else {
                        this['style'] = 'noLineAnchor';
                    }
                });
            });
        }
    });
    focusSearchInput();
})();
function showBranchBorder(element) {
    getParent($(element), 'div').prev().show();
}
function hideBranchBorder(element) {
    getParent($(element), 'div').prev().hide();
}
