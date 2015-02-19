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

    angularUtils.controller(function ($scope) {
    });
})();
