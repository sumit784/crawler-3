function getTableRowIdByImgElement(image) {
    var $tr = getParent($(image), "tr");
    return $tr.parseIntegerInId();
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

function moveUpTableRow(elementInTableRow) {
    var $tr = getParent($(elementInTableRow), 'tr');
    var $prevTr = $tr.prev();
    if ($prevTr.size() > 0) {
        $tr.insertBefore($prevTr);
    }
}

function moveDownTableRow(elementInTableRow) {
    var $tr = getParent($(elementInTableRow), 'tr');
    var $nextTr = $tr.next();
    if ($nextTr.size() > 0) {
        $tr.insertAfter($nextTr);
    }
}

function removeTableRow(elementInTableRowRow) {
    setTimeout(function () {
        getParent($(elementInTableRowRow), 'tr').remove();
    }, 500);
}
