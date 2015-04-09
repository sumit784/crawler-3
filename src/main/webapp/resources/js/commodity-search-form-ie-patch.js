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

    $('div.searchForm > div').eq(0).css('z-index', 11);
})();
