;
(function () {
    function switchNavigationLinks($link) {
        $elements.navigationLinks.filter('.selected').removeClass('selected').addClass('darkFont');
        $link.removeClass('darkFont').addClass("selected");
        $elements.selectedNavigation.text($link.text());
    }

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

    var branchLinks = {
        searchDivInitHeight: null,
        _initSearchDivInitHeight: function () {
            this.searchDivInitHeight = $elements.searchDiv.height();
        },
        getHideBranchHeight: function () {
            return $elements.hideBranch.height();
        },
        hideMore: function () {
            if (!this.searchDivInitHeight) {
                this._initSearchDivInitHeight();
            }
            $elements.getMoreBranch().show().next().hide();
            var self = this;
            $elements.hideBranch.stop(true, true).slideUp(function () {
                $elements.searchDiv.height(self.searchDivInitHeight);
            });
        },
        showMore: function () {
            if (!this.searchDivInitHeight) {
                this._initSearchDivInitHeight();
            }
            var newHeight = this.searchDivInitHeight + this.getHideBranchHeight();
            $elements.searchDiv.height(newHeight);
            var self = this;
            $elements.hideBranch.stop(true, true).slideDown(function () {
                $elements.searchDiv.height(self.searchDivInitHeight + self.getHideBranchHeight());
            });
            $elements.getMoreBranch().hide().next().show();
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
        navigationLinks: $('div.navigation > div.navigationLinks a'),
        classifyLinks: $('div.search > div.classification a'),
        sortLinks: $('div.sort > div.links a'),
        selectedNavigation: $('span.selectedNavigation'),
        searchDiv: $('div.search'),
        branchDiv: $('div.branch'),
        branchLogo: $('div.branch div.logos'),
        branchTitle: $('div.branch div.title'),
        branchLogoTable: $('div.branch div.logos table'),
        goodsImages: $('div.goods div.images div.image img'),
        goodsDescriptions: $('div.goods div.images div.description a'),
        hideBranch: $('div.search > div.branch > div.logos div.hideBranch'),
        hotWords: $('div.search > div.searchForm div.hotWords'),
        branchPoster: $('div.search > div.branch > div.poster'),
        getMoreBranch: function () {
            return $('div.search > div.branch > div.logos div.moreBranch');
        }
    };

    $elements.collectButton.click(function () {
    });
    $elements.refreshButton.click(function () {
        location.reload();
    });
    $elements.navigationLinks.click(function () {
        switchNavigationLinks($(this));
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
    $elements.branchDiv.hover(function (event) {
    }, function (event) {
        var $target = $(event.target);
        if ($target.hasClass('branch') && $target.is('div')) {
            branchLinks.hideMore();
        }
    });

    angularUtils.controller(function ($scope) {
        $scope.snapshots = splitArray(getSnapshots(), 3);
        $scope.hotWords = getHotWords();
        $scope.keyWord = getKeyWord();
        $scope.showMore = function () {
            branchLinks.showMore();
        };

        var branches = getBranches();
        if (branches.length > 14) {
            branches[13]['more'] = true;
            $scope.hideBranches = splitArray(branches.slice(14), 7);
            branches = branches.slice(0, 14);
            $scope.branches = splitArray(branches, 7);
        } else {
            $scope.hideBranches = [];
            $scope.branches = splitArray(branches, 7);
        }
    });

    function getBranches() {
        var branches = [];
        for (var i = 0; i < 35; i++) {
            branches.push({
                "id": i,
                "src": "resources/css/images/branchs/branch1.png"
            });
        }
        return branches;
    }

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

    function getSnapshots() {
        var snapshots = [];
        for (var i = 0; i < 9; i++) {
            var snapshot = {
                id: 1,
                src: "resources/css/images/goods/clothes4.gif",
                description: "原创春夏季女装 文艺田园清新宽松娃娃领纯绵双层纱T恤上衣常规",
                price: 19.80,
                branch: {
                    id: 1,
                    url: "resources/css/images/branchs/branch3.gif"
                }
            };
            snapshots.push(snapshot);
        }
        return snapshots;
    }
})();

function showBranchBorder(element) {
    getParent($(element), 'div').prev().show();
}

function hideBranchBorder(element) {
    getParent($(element), 'div').prev().hide();
}
