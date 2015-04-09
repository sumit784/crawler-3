;
(function () {
    $('div.content div.searchForm span.searchCommit').css({
        width: '91px',
        border: 'none'
    });

    $('div.searchForm div.searchType').css({
        'width': '80px',
        'float': 'left',
        'z-index': '10'
    });
    var $searchInput = $('#searchInput');
    $searchInput.css({
        'width': '338px',
        'margin-top': 0
    });

    var zIndex = {'z-index': 10};
    var $searchForm = $('div.searchForm').css(zIndex);
    $searchForm.parent().css(zIndex);
    $searchForm.parent().parent().css(zIndex);
    $searchForm.find('div').css(zIndex);
    $searchForm.find('>div').eq(0).css('z-index', 11);

    adjustHeight($('div.content div.searchForm'), 0.6);
})();
