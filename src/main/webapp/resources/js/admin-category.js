;
(function () {
    var input = {
        $form: $('#categoryForm'),
        get$AddSubmit: function () {
            return $('#addSubmit');
        },
        get$EditSubmit: function () {
            return $('#editSubmit');
        },
        get$Id: function () {
            return this.$form.find('input[name=id]');
        },
        get$Name: function () {
            return this.$form.find('input[name=name]');
        },
        get$ParentId: function () {
            return this.$form.find('select[name=parentId]');
        },
        validate: function () {
            if ($.trim(this.get$Name().val()) == '') {
                alert('名称不能为空');
                this.get$Name().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };
    input.$form.ajaxForm(normalSubmitCallback);

    var searchWordInput = {
        $form: $('#searchWordForm'),
        get$Id: function () {
            return this.$form.find('input[name=id]');
        },
        get$CategoryId: function () {
            return this.$form.find('input[name=categoryId]');
        },
        get$Content: function () {
            return this.$form.find('input[name=content]');
        },
        get$Hot: function () {
            return this.$form.find('input[name=hot]');
        },
        get$AddSubmit: function () {
            return $('#addSearchWordSubmit');
        },
        get$EditSubmit: function () {
            return $('#editSearchWordSubmit');
        },
        show: function (id, categoryId, content, hot) {
            this.get$Id().val(id);
            this.get$CategoryId().val(categoryId);
            this.get$Content().val(content);
            this.get$Hot().val(hot);
            transparentBackground.show();
            this.$form.show();
            this.get$Content().focusOrSelect();
        },
        hide: function () {
            this.$form.hide();
            transparentBackground.hide();
        },
        validate: function () {
            if ($.trim(this.get$Content().val()) == '') {
                alert('搜索关键词不能为空');
                this.get$Content().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };

    searchWordInput.$form.ajaxForm(normalSubmitCallback);

    angularUtils.controller(function ($scope) {
        $scope.validateInput = buildNormalValidationCallback(input);
        $scope.validateSearchWordInput = buildNormalValidationCallback(searchWordInput);
        $scope.deleteCategory = function (event) {
            $.post('admin-category-delete', {
                id: getTableRowIdByImgElement(event.target)
            }, normalSubmitCallback);
        };
        $scope.editCategory = function (event) {
            var $this = $(event.target);
            var $tr = getParent($this, 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var name = $.trim($tr.find('td.name').text());
            var parentId = $tr.find('td.parent').dataOptions()['parentId'];

            input.get$Id().val(id);
            input.get$Name().val(name).focusOrSelect();
            input.get$ParentId().val(parentId);
            input.get$AddSubmit().attr('disabled', true);
            input.get$EditSubmit().attr('disabled', false);
        };
        $scope.cancelSearchWordInput = function () {
            searchWordInput.hide();
        };
        $scope.addSearchWord = function (event) {
            var categoryId = getTableRowIdByImgElement(event.target);
            searchWordInput.get$EditSubmit().hide();
            searchWordInput.get$AddSubmit().show();
            searchWordInput.show(-1, categoryId, '', false);
        }
    });
})();
