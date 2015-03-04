(function () {
    var historyTrend = {
        _chartCreated: false,
        show: function () {
            $elements.priceHistory.show();
            if (!this._chartCreated) {
                priceLineChart($elements.trendChart.attr('id'), {
                    xSerial: ['2014-09-25', '2014-09-30', '2014-10-05', '2014-10-10', '2014-12-01'],
                    ySerial: [180, 200, 200, 190, 190],
                    width: 300,
                    height: 200
                });
                this._chartCreated = true;
            }
        },
        hide: function () {
            $elements.priceHistory.hide();
        }
    };

    var $elements = {
        smallImages: $('div.snapshot div.left div.smallImage img'),
        trendChart: $('#trendChart'),
        trendImage: $('#trendImage'),
        priceHistory: $('div.content > div.left div.snapshot div.right div.priceHistory'),
        descriptionTitles: $('div.description div.title span'),
        descriptionDetail: $('div.description div.detail'),
        snapshotRight: $('div.content > div.left div.snapshot div.right'),
        largeImage: $('div.content > div.left div.snapshot div.left div.largeImage'),
        enlargeImage: $('div.content > div.left div.snapshot div.left div.largeImage div.enlargeImage'),
        enlargeIcon: $('div.content > div.left div.snapshot div.left div.largeImage div.enlarge'),
        couponLink: $('#couponLink'),
        closeLargeImageLink: $('div.content > div.left div.snapshot div.left div.largeImage div.enlargeImage div.closeLargeImage')
    };
    $elements.smallImages.mouseover(function () {
        $elements.smallImages.filter('.selected').removeClass('selected');
        var $this = $(this);
        $this.addClass('selected');
        var src = $this.attr('src');
        $elements.largeImage.find('img.boxShadow').attr('src', src);
        $elements.enlargeImage.find('img').attr('src', src);
    });
    $elements.trendImage.hover(function () {
        historyTrend.show();
    }, function () {
    });
    $elements.priceHistory.hover(function () {
    }, function () {
        historyTrend.hide();
    });
    $elements.descriptionTitles.mouseover(function () {
        $elements.descriptionTitles.filter('.selected').removeClass('selected');
        $(this).addClass('selected');
        if ($(this).hasClass('goodsDetail')) {
            $elements.descriptionDetail.find('div.goodsDetail').show();
            $elements.descriptionDetail.find('div.shoppe').hide();
        } else {
            $elements.descriptionDetail.find('div.goodsDetail').hide();
            $elements.descriptionDetail.find('div.shoppe').show();
        }
    });
    $elements.snapshotRight.hover(function () {
    }, function (event) {
        var $target = $(event.target);
        if ($target.is('div') && $target.hasClass('right')) {
            historyTrend.hide();
        }
    });
    $elements.enlargeIcon.click(function () {
        transparentBackground.show();
        $elements.enlargeImage.fadeIn(250);
    });
    $elements.closeLargeImageLink.click(function () {
        $elements.enlargeImage.fadeOut(250);
        transparentBackground.hide();
    });
    loadCommodityParameters("commodityParameters", $('#commodityParametersData').text());
    setHoverColor($elements.couponLink, '#3E3E3E');
})();
