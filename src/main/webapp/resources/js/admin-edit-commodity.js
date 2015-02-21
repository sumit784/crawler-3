function CommodityDescription(id) {
    this._div = $('#' + id);
}
CommodityDescription.prototype.text = function (text) {
    text = $.trim(text);
    var lines = text.split('\n');
    if (lines.length == 0) {
        this._div.html('');
    }

    while (lines.length % 3 != 2) {
        lines.push("");
    }
    var html = '<table>';
    var columnCount = 3;
    for (var i = 0, len = lines.length; i < len; i++) {
        if (i % columnCount == 0) {
            html += '<tr>';
        }
        html += '<td><div>' + $.trim(lines[i]) + '</div></td>';
        if (i % columnCount == columnCount - 1) {
            html += '</tr>';
        }
    }
    html += '</table>';
    this._div.empty().html(html);
};
(function () {
    var $elements = {
        selectButtons: $('div.content div.branch button'),
        commodityDescription: $('#commodityDescription'),
        commodityDescriptionEffect: $('#commodityDescriptionEffect')
    };
    $elements.selectButtons.click(function () {
        $(this).css({
            'background-color': '#ffffff',
            'background-image': 'url("resources/css/images/edit-commodity/select.png")'
        });
    });
    var commdityDescriptionEffect = new CommodityDescription($elements.commodityDescriptionEffect.attr('id'));
    $elements.commodityDescription.keyup(function () {
        var text = $(this).val();
        commdityDescriptionEffect.text(text);
    }).trigger('keyup');

    angularUtils.controller(function ($scope) {
        $scope.commodityDescription = "产品名称：Nike/耐克 473284\n"
            + "颜色分类：444动力蓝/蓝黑/白\n"
            + "款号：473284\n"
            + "品牌：Nike/耐克\n"
            + "上市时间：2015春季\n"
            + "吊牌价：699\n"
            + "性别：男子\n"
            + "鞋帮高度：中帮\n"
            + "鞋码：39 40 40.5 41 42 42.5 43 44 44.5 45 46\n"
            + "闭合方式：系带\n"
            + "是否瑕疵：否";
    });
})();
