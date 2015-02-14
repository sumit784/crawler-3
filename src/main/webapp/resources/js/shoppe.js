(function () {
    function switchSortTitle($target) {
        $elements.sortTitles.css('border-bottom-width', '0');
        $target.css('border-bottom-width', '2px');
    }
    $elements = {
        sortTitles: $('div.goods div.sort div.title div'),
        logoCopy: $('div.content > div.copy'),
        logoDiv: $('#logoDiv')
    };
    $elements.sortTitles.click(function () {
        switchSortTitle($(this));
    });
})();
