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
            if (hot) {
                this.get$Hot().attr('checked', 'checked');
            } else {
                this.get$Hot().attr('checked', null);
            }
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
        // actions about validation
        $scope.validateInput = buildNormalValidationCallback(input);
        $scope.validateSearchWordInput = buildNormalValidationCallback(searchWordInput);

        // actions about category
        $scope.deleteCategory = function (event) {
            var target = event.target;
            $.post('admin-category-delete', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                removeTableRow(target);
            }));
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

        // actions about search word
        $scope.cancelSearchWordInput = function () {
            searchWordInput.hide();
        };
        $scope.deleteSearchWord = function (event) {
            var target = event.target;
            $.post('admin-hot-search-word-delete', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                removeTableRow(target);
            }));
        };
        $scope.addSearchWord = function (event) {
            var categoryId = getTableRowIdByImgElement(event.target);
            searchWordInput.get$EditSubmit().hide();
            searchWordInput.get$AddSubmit().show();
            searchWordInput.show(-1, categoryId, '', false);
        };
        $scope.editSearchWord = function (event) {
            var $this = $(event.target);
            var $tr = getParent($this, 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var content = $.trim($tr.find('td.content').text());
            var hot = $tr.find('td.content span').hasClass('hot');
            var categoryId = getParent($tr, 'tr').attr('id').replace(/\D/g, '');

            searchWordInput.get$EditSubmit().show();
            searchWordInput.get$AddSubmit().hide();
            searchWordInput.show(id, categoryId, content, hot);
        };
        $scope.rankUpSearchWord = function (event) {
            var target = event.target;
            $.post('admin-hot-search-word-rank-up', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                moveUpTableRow(target);
            }));
        };
        $scope.rankDownSearchWord = function (event) {
            var target = event.target;
            $.post('admin-hot-search-word-rank-down', {
                id: getTableRowIdByImgElement(target)
            }, buildSubmitCallback(function () {
                moveDownTableRow(target);
            }));
        };
    });
})();
