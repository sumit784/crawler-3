;
(function () {
    var $branchForm = $('#branchForm');
    var $table = $('table.normal');
    var $deleteImages = $table.find('img.delete');
    var $editImages = $table.find('img.edit');
    var $addSubmit = $('#addSubmit');
    var $editSubmit = $('#editSubmit');
    var $name = $('#name');
    var $firstLetter = $('#firstLetter');

    $branchForm.ajaxForm(function (data) {
        if (data.success) {
            location.reload();
        } else {
            alert(data.detail);
        }
    });
    $deleteImages.click(function () {
        $.post('admin-branch-delete', {
            id: getBranchIdByImgElement(this)
        }, function (data) {
            if (data.success) {
                location.reload();
            } else {
                alert(data.detail);
            }
        });
    });
    $editImages.click(function () {
        var $tr = getParent($(this), 'tr');
        var id = $tr.attr('id').replace(/\D/g, '');
        var name = $tr.find('td.name').text();
        var logo = $tr.find('td.logo a').attr('title');
        var parentId = $tr.find('td.parent').dataOptions()['parentId'];

        $branchForm.find('input[name=id]').val(id);
        $branchForm.find('input[name=name]').val(name).focusOrSelect();
        $branchForm.find('input[name=logo]').val(logo);
        $branchForm.find('select[name=parentId]').val(parentId);

        $addSubmit.attr('disabled', true);
        $editSubmit.attr('disabled', false);
    });
    $name.keyup(function () {
        var name = $(this).val();
        if (name != '') {
            $.post('chinese-letter.json?string=' + name, function (data) {
                if (data.result) {
                    $firstLetter.val(data.result);
                }
            });
        } else {
            $firstLetter.val("");
        }
    });
    function getBranchIdByImgElement(image) {
        var $tr = getParent($(image), "tr");
        return $tr.attr('id').replace(/\D/g, '');
    }

    angularUtils.controller(function ($scope) {
    });
})();
