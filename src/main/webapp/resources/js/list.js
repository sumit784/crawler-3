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

    var classifyLinks = {
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
                this._unSelect($elements.classifyLinks.filter('.orangeBack'));
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
        classifyLinks: $('div.search > div.classification a'),
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
    $elements.classifyLinks.click(function (event) {
        classifyLinks.click($(this));
        event.stopPropagation();
    }).hover(function () {
        classifyLinks.over($(this));
    }, function () {
        classifyLinks.off($(this));
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

        $scope.hotWords = getHotWords();
        $scope.showMore = function () {
            if ($scope.hideBranches.length == 0) {
                return;
            }
            $('div.search > div.right div.branch > div.logos div.hideBranch').show();
            $('div.search > div.right div.branch > div.logos div.moreBranch').hide();
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
                $('div.search > div.right div.branch > div.logos div.hideBranch').hide();
                $('div.search > div.right div.branch > div.logos div.moreBranch').show();
            }
        };

        function initBranch() {
            var url = "json/branch.json?categoryId=" + $.url.param('id');
            $http.get(url).success(function (branches) {
                //var branches = getBranches();
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
    });

    function getHotWords() {
        var words = [];
        words.push({'text': '加湿器', color: 'red'});
        words.push({'text': '羽绒服 男'});
        words.push({'text': '小米4'});
        words.push({'text': '冲锋衣', color: 'red'});
        words.push({'text': '棉拖鞋'});
        words.push({'text': '丝棉被'});
        words.push({'text': '护手霜'});
        words.push({'text': '男 棉衣', color: 'red'});
        return words;
    }
})();

function showBranchBorder(element) {
    getParent($(element), 'div').prev().show();
}

function hideBranchBorder(element) {
    getParent($(element), 'div').prev().hide();
}
