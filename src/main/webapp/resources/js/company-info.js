(function () {
    function setContentDetail(tplId) {
        var source = $('#' + tplId).html();
        var template = Handlebars.compile(source);
        $elements.contentDetail.html(template());
    }

    function switchContent($naviLink) {
        $elements.title.text($naviLink.text());
        $naviLink.parent().parent().find('div.company').removeClass('selected');
        $naviLink.parent().addClass('selected');
        setContentDetail($naviLink.attr('id') + 'Tpl');
    }

    var $elements = {
        contentDetail: $('#contentDetail'),
        rightNaviLinks: $('div.rightNavigation div.company a'),
        title: $('div.content div.title')
    };

    $elements.rightNaviLinks.click(function () {
        switchContent($(this));
    });

    var href = location.href.toString();
    var match = href.match(/tab=[0-9]+/);
    if (match) {
        var selectedRightNaviIndex = match[0].replace(/\D+/g, '');
    } else {
        var selectedRightNaviIndex = 1;
    }
    switchContent($elements.rightNaviLinks.eq(selectedRightNaviIndex - 1));
})();
