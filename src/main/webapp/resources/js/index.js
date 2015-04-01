(function () {
    var $linksDiv = $('div.content > div.links');
    var $logoGroup = $linksDiv.find('div.logoGroup');
    $logoGroup.hover(function () {
        $(this).find('div.cover').fadeIn(300);
    }, function () {
        $(this).find('div.cover').fadeOut(300);
    });

    // foot image
    var img = '<img src="' + $.trim($('#footPoster').text()) + '"/>';
    var footPosterLink = $.trim($('#footPosterLink').text());
    if (footPosterLink != '') {
        img = '<a href="' + footPosterLink + '">' + img + '</a>';
    }
    $('<div></div>').addClass('footImage').append(img).css({
        top: $linksDiv.height() + 370
    }).appendTo($('body'));
    angularUtils.controller(function () {
    });
})();