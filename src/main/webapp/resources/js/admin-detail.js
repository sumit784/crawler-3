;
(function () {
    var imageInput = {
        $form: $('#imageForm'),
        get$Id: function () {
            return this.$form.find('input[name=id]');
        },
        get$Url: function () {
            return this.$form.find('input[name=url]');
        },
        get$UploadFile: function () {
            return this.$form.find('input[name=uploadFile]');
        },
        get$AddSubmit: function () {
            return $('#addImageSubmit');
        },
        get$EditSubmit: function () {
            return $('#editImageSubmit');
        },
        show: function (id, url) {
            this.get$Id().val(id);
            this.get$Url().val(url);
            transparentBackground.show();
            this.$form.show();
            this.get$Url().focusOrSelect();
        },
        hide: function () {
            this.$form.hide();
            transparentBackground.hide();
        },
        validate: function () {
            if ($.trim(this.get$Url().val()) == '' &&
                $.trim(this.get$UploadFile().val()) == '') {
                alert('url和上传文件至少设置一项');
                this.get$Url().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };

    angularUtils.controller(function ($scope) {
        $scope.validateImageInput = buildNormalValidationCallback(imageInput);
        $scope.cancelImageInput = function () {
            imageInput.hide();
        };
        $scope.addImage = function (event) {
            imageInput.get$EditSubmit().hide();
            imageInput.get$AddSubmit().show();
            imageInput.show(-1, '');
        };
        /*
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
        */
    });
})();
