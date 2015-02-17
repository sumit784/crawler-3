(function () {
    var $elements = {
        tdDiv: $('div.content > div.links td > div')
    };
    $elements.tdDiv.hover(function () {
        $(this).find('div.cover').fadeIn(300);
    }, function () {
        $(this).find('div.cover').fadeOut(300);
    });

    // foot image
    $('<div></div>').addClass('footImage')
        .append('<img class="link" src="resources/css/images/index/footer.png"/>')
        .appendTo($('body'));
})();