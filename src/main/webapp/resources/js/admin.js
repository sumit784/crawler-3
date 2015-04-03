(function () {
    var $elements = {
        commodityImages: $('div.content div.right div.image img'),
        commodityDescription: $('div.content div.right div.description a')
    };
    $elements.commodityImages.hover(function () {
        $(this).addClass('deepTransparent');
    }, function () {
        $(this).removeClass('deepTransparent');
    });
    $elements.commodityDescription.hover(function () {
        $(this).css({
            'color': '#4A2B1B',
            'text-decoration': 'none',
            'text-shadow': '2px 2px 5px rgb(238, 238, 238)'
        });
    }, function () {
        $(this).css({
            'color': '#7F4B2F',
            'text-shadow': 'none'
        });
    });

    function getVisiblePaginationButtons() {
        return $('div.pagination ul.pagination li.showButton');
    }

    function getVisiblePaginationButtonCount() {
        return getVisiblePaginationButtons().size();
    }

    function getFirstVisiblePaginationButtons() {
        return getVisiblePaginationButtons().first();
    }

    function getLastVisiblePaginationButtons() {
        return getVisiblePaginationButtons().last();
    }

    $('#nextPaginationButtons').click(function () {
        var displayCount = getVisiblePaginationButtonCount();
        var first = getFirstVisiblePaginationButtons();
        var last = getLastVisiblePaginationButtons();
        for (var i = 0; i < displayCount; i++) {
            if (!last.next().hasClass('pageButton')) {
                return;
            }
            first.addClass('hideButton').removeClass('showButton');
            first = first.next();
            last = last.next();
            last.addClass('showButton').removeClass('hideButton');
        }
    });
    $('#prevPaginationButtons').click(function () {
        var displayCount = getVisiblePaginationButtonCount();
        var first = getFirstVisiblePaginationButtons();
        var last = getLastVisiblePaginationButtons();
        for (var i = 0; i < displayCount; i++) {
            if (!first.prev().hasClass('pageButton')) {
                return;
            }
            last.addClass('hideButton').removeClass('showButton');
            last = last.prev();
            first = first.prev();
            first.addClass('showButton').removeClass('hideButton');
        }
    });

    angularUtils.controller(function ($scope) {
    });
    JSUtils.recordScrollStatus();
})();
