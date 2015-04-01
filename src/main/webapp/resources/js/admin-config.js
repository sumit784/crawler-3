;
(function () {
    var input = {
        $form: $('#mainForm'),
        get$GlobalLogo: function () {
            return this.$form.find('input[name=globalLogo]');
        },
        get$GlobalLogoFile: function () {
            return this.$form.find('input[name=globalLogoFile]');
        },
        get$GlobalBanner: function () {
            return this.$form.find('input[name=globalBanner]');
        },
        get$GlobalBannerFile: function () {
            return this.$form.find('input[name=globalBannerFile]');
        },
        get$GlobalBottomPoster: function () {
            return this.$form.find('input[name=globalBottomPoster]');
        },
        get$GlobalBottomPosterFile: function () {
            return this.$form.find('input[name=globalBottomPosterFile]');
        },
        get$EditSubmit: function () {
            return $('#editImageSubmit');
        },
        validate: function () {
            if ($.trim(this.get$GlobalLogo().val()) == '' &&
                $.trim(this.get$GlobalLogoFile().val()) == '') {
                alert('首页Logo未设置!');
                this.get$GlobalLogo().focusOrSelect();
                return false;
            } else if ($.trim(this.get$GlobalBanner().val()) == '' &&
                $.trim(this.get$GlobalBannerFile().val()) == '') {
                alert('首页头部横幅未设置!');
                this.get$GlobalBanner().focusOrSelect();
                return false;
            } else if ($.trim(this.get$GlobalBottomPoster().val()) == '' &&
                $.trim(this.get$GlobalBottomPosterFile().val()) == '') {
                alert('首页尾部海报未设置!');
                this.get$GlobalBottomPoster().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };
    input.$form.ajaxForm(normalSubmitCallback);
    input.get$GlobalLogo().focusOrSelect();
    angularUtils.controller(function ($scope) {
        $scope.validateInput = buildNormalValidationCallback(input);
    });
})();
