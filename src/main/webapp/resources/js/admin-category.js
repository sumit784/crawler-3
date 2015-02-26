;
(function () {
    var $categoryForm = $('#categoryForm');
    var $table = $('table.normal');
    var $deleteImages = $table.find('img.delete');
    var $editImages = $table.find('img.edit');
    var $addSubmit = $('#addSubmit');
    var $editSubmit = $('#editSubmit');

    $categoryForm.ajaxForm(normalSubmitCallback);
    $deleteImages.click(function () {
        $.post('admin-category-delete', {
            id: getCategoryIdByImgElement(this)
        }, normalSubmitCallback);
    });
    $editImages.click(function () {
        var $tr = getParent($(this), 'tr');
        var id = $tr.attr('id').replace(/\D/g, '');
        var name = $tr.find('td.name').text();
        var parentId = $tr.find('td.parent').dataOptions()['parentId'];

        $categoryForm.find('input[name=id]').val(id);
        $categoryForm.find('input[name=name]').val(name).focusOrSelect();
        $categoryForm.find('select[name=parentId]').val(parentId);

        $addSubmit.attr('disabled', true);
        $editSubmit.attr('disabled', false);
    });
    function getCategoryIdByImgElement(image) {
        var $tr = getParent($(image), "tr");
        return $tr.attr('id').replace(/\D/g, '');
    }

    angularUtils.controller(function ($scope) {
    });
})();
