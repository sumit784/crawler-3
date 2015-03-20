(function () {
    var $linksDiv = $('div.content > div.links');
    var $logoGroup = $linksDiv.find('div.logoGroup');
    $logoGroup.hover(function () {
        $(this).find('div.cover').fadeIn(300);
    }, function () {
        $(this).find('div.cover').fadeOut(300);
    });

    // foot image
    console.log($linksDiv.height());
    $('<div></div>').addClass('footImage')
        .append('<img class="link" src="resources/css/images/index/footer.png"/>').css({
            top: $linksDiv.height() + 370
        }).appendTo($('body'));
    angularUtils.controller(function () {
    });
})();