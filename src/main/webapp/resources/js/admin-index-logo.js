;
(function () {
    var $indexLogoForm = $('#indexLogoForm');
    var $addSubmit = $('#addSubmit');
    var $editSubmit = $('#editSubmit');

    $indexLogoForm.ajaxForm(normalSubmitCallback);
    var input = {
        get$Id: function () {
            return $indexLogoForm.find('input[name=id]');
        },
        get$link: function () {
            return $indexLogoForm.find('input[name=link]');
        },
        get$Logo: function () {
            return $indexLogoForm.find('input[name=logo]');
        },
        get$LogoFile: function () {
            return $indexLogoForm.find('input[name=logoFile]');
        },
        get$Description: function(){
            return $indexLogoForm.find('input[name=description]');
        },
        validate: function () {
            if ($.trim(this.get$Logo().val()) == '' &&
                $.trim(this.get$LogoFile().val()) == '') {
                alert('图片未设置');
                this.get$Logo().focusOrSelect();
                return false;
            }

            return true;
        }
    };

    angularUtils.controller(function ($scope) {
        $scope.validateInput = function (event) {
            if (!input.validate()) {
                event.preventDefault();
                return false;
            } else {
                return true;
            }
        };
        $scope.editIndexLogo = function (event) {
            var $tr = getParent($(event.target), 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var link = $tr.find('td.link a').attr('title');
            var path = $tr.find('td.path a').attr('title');
            var description = $tr.find('td.description').text();

            input.get$Id().val(id);
            input.get$link().val(link);
            input.get$Logo().val(path);
            input.get$Description().val(description);

            $addSubmit.attr('disabled', true);
            $editSubmit.attr('disabled', false);
        };
        $scope.deleteIndexLogo = function (event) {
            $.post('admin-index-logo-delete', {
                id: getTableRowIdByImgElement(event.target)
            }, normalSubmitCallback);
        };
        $scope.upIndexLogo = function (event) {
            $.post('admin-index-logo-rank-up', {
                id: getTableRowIdByImgElement(event.target)
            }, normalSubmitCallback);
        };
        $scope.downIndexLogo = function (event) {
            $.post('admin-index-logo-rank-down', {
                id: getTableRowIdByImgElement(event.target)
            }, normalSubmitCallback);
        };
    });
})();
