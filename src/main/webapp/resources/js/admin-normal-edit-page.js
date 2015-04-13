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

function buildInput(attrs, disableAjax) {
    attrs.get$AddSubmit = function () {
        return $('#addSubmit');
    };
    attrs.get$EditSubmit = function () {
        return $('#editSubmit');
    };
    attrs.get$EditCancel = function () {
        return $('#editCancel');
    };
    attrs.get$Id = function () {
        return this.$form.find('input[name=id]');
    };
    if (!disableAjax) {
        attrs.$form.ajaxForm(normalSubmitCallback);
    }
    attrs.toEditMode = function () {
        this.get$AddSubmit().attr('disabled', true);
        this.get$EditSubmit().attr('disabled', false);
        this.get$EditCancel().attr('disabled', false);
        scrollTop(this.$form);
        return this;
    };
    attrs.focus = function () {
        this.$form.find('input[type=text]').eq(0).focus();
        return this;
    };
    attrs.get$EditCancel().click(function () {
        attrs.$form[0].reset();
        attrs.focus();
        attrs.get$Id().val(null);
        attrs.get$AddSubmit().attr('disabled', false);
        attrs.get$EditSubmit().attr('disabled', true);
        attrs.get$EditCancel().attr('disabled', true);
        if (attrs['afterEditCancel']) {
            attrs['afterEditCancel']();
        }
    });
    return attrs;
}

function validateTextInput($input, info) {
    if ($.trim($input.val()) == '') {
        alert(info);
        $input.focusOrSelect();
        return false;
    } else {
        return true;
    }
}

(function () {
    var index = 0;
    $('input[type=text],input[type=password],button').each(function () {
        var $this = $(this);
        if (!$this.attr('tabindex')) {
            $this.attr('tabindex', ++index);
        }
    });
    JSUtils.recordScrollStatus();
})();
