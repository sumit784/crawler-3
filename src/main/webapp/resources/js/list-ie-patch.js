;
(function () {
    adjustHeight($('div.navigationName'), 0.6);
    $('div.search div.subCategory').css('width', '190px');
    $('div.search div.subCategory > div').each(function () {
        var textLength = $(this).text().length;
        var width = textLength * 12 + 16;
        $(this).css('width', width + 'px');
    });
    adjustHeight($('div.search > div.right div.searchFormBack'), 0.4);

    setTimeout(function(){
        $('div.images div.image').each(function(){
            var url = 'url(http://qinyuan/images/img06.taobaocdn.com/bao/uploaded/i1/TB1Pw7sHpXXXXXGXXXXXXXXXXXX_!!0-item_pic_thumbnail_middle.jpg)';
            $(this).css('background-image', url);
            //$(this).attr('style', $(this).attr('ng-style'));
            console.log($(this).html());
        });
    }, 2000);
})();
