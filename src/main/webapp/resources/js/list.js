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
        getHideBranchHeight: function () {
            return $elements.hideBranch.height();
        },
        getMoreBranchSpan: function () {
            return $elements.moreBranch.find('span');
        },
        getMoreBranchSpanText: function () {
            return this.getMoreBranchSpan().text();
        },
        hideMore: function () {
            if (!this.searchDivInitHeight)  {
                this.searchDivInitHeight = $elements.searchDiv.height();
            }
            if (this.getMoreBranchSpanText() == '更多品牌') {
                return;
            }
            $elements.moreBranch.show().find('span').text('更多品牌');
            $elements.moreBranch.parent().children('div.logoImage').hide();
            var self = this;
            $elements.hideBranch.stop(true, true).slideUp(function () {
                $elements.searchDiv.height(self.searchDivInitHeight);
            });
        },
        showMore: function () {
            if (!this.searchDivInitHeight)  {
                this.searchDivInitHeight = $elements.searchDiv.height();
            }
            if (this.getMoreBranchSpanText() == '隐藏') {
                return;
            }
            $elements.moreBranch.parent().children('div.logoImage').show();
            $elements.moreBranch.hide().find('span').text('隐藏');
            $elements.searchDiv.height(this.searchDivInitHeight + this.getHideBranchHeight());
            var self = this;
            $elements.hideBranch.stop(true, true).slideDown(function() {
                $elements.searchDiv.height(self.searchDivInitHeight + self.getHideBranchHeight());
            });
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
        branchLogoLinks: $('div.branch div.logos img'),
        goodsImages: $('div.goods div.images div.image img'),
        goodsDescriptions: $('div.goods div.images div.description a'),
        hideBranch: $('div.search > div.branch > div.logos div.hideBranch'),
        moreBranch: $('div.search > div.branch > div.logos div.moreBranch'),
        hotWords: $('div.search > div.searchForm div.hotWords'),
        branchPoster: $('div.search > div.branch > div.poster')
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
    $elements.branchLogoLinks.hover(function () {
        getParent($(this), 'div').prev().show();
    }, function () {
        getParent($(this), 'div').prev().hide();
    });
    $elements.goodsImages.hover(function () {
        $(this).addClass('deepTransparent');
    }, function () {
        $(this).removeClass('deepTransparent');
    });
    $elements.goodsDescriptions.hover(function () {
        $(this).css({
            'color': 'rgb(126, 71, 52)',
            'text-decoration': 'none',
            'text-shadow': '2px 2px 5px rgb(238, 238, 238)'
        });
    }, function () {
        $(this).css({
            'color': 'rgb(152, 93, 62)',
            'text-shadow': 'none'
        });
    });
    $elements.branchDiv.find('td').hover(function () {
        var text = $elements.moreBranch.text();
        if (text == '隐藏') {
            branchLinks.hideMore();
        } else {
            branchLinks.showMore();
        }
    });
    $elements.branchDiv.hover(function (event) {
    }, function (event) {
        var $target = $(event.target);
        if ($target.hasClass('branch') && $target.is('div')) {
            branchLinks.hideMore();
        }
    });
})();
