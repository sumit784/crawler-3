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
        get$Description: function () {
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
        $scope.validateInput = buildNormalValidationCallback(input);
        $scope.editIndexLogo = function (event) {
            var $tr = getParent($(event.target), 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var link = $tr.find('td.link a').attr('title');
            var path = $tr.find('td.path a').attr('title');
            var description = $tr.find('td.description').text();

            input.get$Id().val(id);
            input.get$link().val(link);
            input.get$Logo().val(path).focusOrSelect();
            input.get$Description().val(description);

            $addSubmit.attr('disabled', true);
            $editSubmit.attr('disabled', false);
        };
        $scope.deleteIndexLogo = function (event) {
            var target = event.target;
            $.post('admin-index-logo-delete', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                setTimeout(function () {
                    getParent($(target), 'tr').remove();
                }, 500);
            }));
        };
        $scope.upIndexLogo = function (event) {
            var target = event.target;
            $.post('admin-index-logo-rank-up', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                var $tr = getParent($(target), 'tr');
                var $prevTr = $tr.prev();
                if ($prevTr.size() > 0) {
                    $tr.insertBefore($prevTr);
                }
            }));
        };
        $scope.downIndexLogo = function (event) {
            var target = event.target;
            $.post('admin-index-logo-rank-down', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                var $tr = getParent($(target), 'tr');
                var $nextTr = $tr.next();
                if ($nextTr.size() > 0) {
                    $tr.insertAfter($nextTr);
                }
            }));
        };
    });

    input.get$Logo().focus();
})();
