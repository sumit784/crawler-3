function getTableRowIdByImgElement(image) {
    var $tr = getParent($(image), "tr");
    return $tr.attr('id').replace(/\D/g, '');
}

function buildNormalValidationCallback(inputObject) {
    return function (event) {
        if (!inputObject.validate()) {
            event.preventDefault();
            return false;
        } else {
            return true;
        }
    };
}
