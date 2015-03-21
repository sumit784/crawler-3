function getTableRowIdByImgElement(image) {
    var $tr = getParent($(image), "tr");
    return $tr.attr('id').replace(/\D/g, '');
}
