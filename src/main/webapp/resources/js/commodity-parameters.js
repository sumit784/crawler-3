function loadCommodityParameters(divId, text) {
    var $div = $('#' + divId);
    text = $.trim(text);
    var lines = text.split('\n');

    if (lines.length == 0) {
        $div.html('');
        return;
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
    $div.empty().html(html);
}
