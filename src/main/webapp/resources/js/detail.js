;
(function () {
    function loadPicturesData(elementId) {
        var pictures = $('#' + elementId).text();
        pictures = pictures.substring(1, pictures.length - 1);
        pictures = pictures.split(',');
        for (var i = 0, len = pictures.length; i < len; i++) {
            pictures[i] = $.trim(pictures[i]);
        }
        return pictures;
    }

    var pictures = loadPicturesData('pictures');
    var middlePictures = loadPicturesData('middlePictures');

    var historyTrend = {
        _chartCreated: false,
        _data: null,
        loadData: function () {
            var commodityId = parseInt($.url.param('id'));
            var url = "priceHistory.json?commodityId=" + commodityId;
            var self = this;
            $.get(url, function (data) {
                var xSerial = [], ySerial = [];
                $.each(data[commodityId]['prices'], function () {
                    xSerial.push(this['date']);
                    ySerial.push(this['price']);
                });
                self._data = {
                    xSerial: xSerial,
                    ySerial: ySerial,
                    width: 300,
                    height: 200
                };
            });
        },
        show: function () {
            $elements.priceHistory.show();
            if (!this._chartCreated) {
                priceLineChart($elements.trendChart.attr('id'), this._data);
                this._chartCreated = true;
            }
        },
        hide: function () {
            $elements.priceHistory.hide();
        }
    };
    historyTrend.loadData();

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
        var index = $this.dataOptions()['index'];
        $elements.largeImage.find('img.boxShadow').attr('src', middlePictures[index]);
        $elements.enlargeImage.find('img').attr('src', pictures[index]);
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
