;
(function () {
    var $categoryForm = $('#categoryForm');
    var $addSubmit = $('#addSubmit');
    var $editSubmit = $('#editSubmit');

    $categoryForm.ajaxForm(normalSubmitCallback);

    angularUtils.controller(function ($scope) {
        $scope.deleteCategory = function (event) {
            $.post('admin-category-delete', {
                id: getTableRowIdByImgElement(event.target)
            }, normalSubmitCallback);
        };
        $scope.editCategory = function (event) {
            var $this = $(event.target);
            var $tr = getParent($this, 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var name = $tr.find('td.name').text();
            var parentId = $tr.find('td.parent').dataOptions()['parentId'];

            $categoryForm.find('input[name=id]').val(id);
            $categoryForm.find('input[name=name]').val(name).focusOrSelect();
            $categoryForm.find('select[name=parentId]').val(parentId);

            $addSubmit.attr('disabled', true);
            $editSubmit.attr('disabled', false);
        };
    });
})();
