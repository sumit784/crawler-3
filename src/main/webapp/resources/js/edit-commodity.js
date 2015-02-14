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
            'background-image': 'url("css/images/edit-commodity/select.png")'
        });
    });
    var commdityDescriptionEffect = new CommodityDescription($elements.commodityDescriptionEffect.attr('id'));
    $elements.commodityDescription.keyup(function () {
        var text = $(this).val();
        commdityDescriptionEffect.text(text);
    }).trigger('keyup');
})();
