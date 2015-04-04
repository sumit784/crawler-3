(function () {
    var $commodityImages = $('div.content div.right div.image img');
    var $commodityDescription = $('div.content div.right div.description a');
    var $rightDiv = $('div.content div.right');
    var $paginationDiv = $('div.content div.right div.pagination');
    var $paginationUl = $('div.content div.right div.pagination ul.pagination');

    function adjustPaginationPosition() {
        var paddingLeft = ($rightDiv.width() - $paginationUl.width()) / 2;
        $paginationDiv.css('padding-left', paddingLeft + 'px');
    }

    adjustPaginationPosition();

    $commodityImages.hover(function () {
        $(this).addClass('deepTransparent');
    }, function () {
        $(this).removeClass('deepTransparent');
    });
    $commodityDescription.hover(function () {
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

    JSUtils.recordScrollStatus();
    angularUtils.controller(function ($scope) {
    });
})();
