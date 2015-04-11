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
        get$indexHeadPoster: function () {
            return this.$form.find('input[name=indexHeadPoster]');
        },
        get$indexHeadPosterFile: function () {
            return this.$form.find('input[name=indexHeadPosterFile]');
        },
        get$indexFootPoster: function () {
            return this.$form.find('input[name=indexFootPoster]');
        },
        get$indexFootPosterFile: function () {
            return this.$form.find('input[name=indexFootPosterFile]');
        },
        get$BranchRankImage: function () {
            return this.$form.find('input[name=branchRankImage]');
        },
        get$BranchRankImageFile: function () {
            return this.$form.find('input[name=branchRankImageFile]');
        },
        get$NoFoundImage: function () {
            return this.$form.find('input[name=noFoundImage]');
        },
        get$NoFoundImageFile: function () {
            return this.$form.find('input[name=noFoundImageFile]');
        },
        get$EditSubmit: function () {
            return $('#editImageSubmit');
        },
        validate: function () {
            if ($.trim(this.get$GlobalBanner().val()) == '' &&
                $.trim(this.get$GlobalBannerFile().val()) == '') {
                alert('页头部横幅未设置!');
                this.get$GlobalBanner().focusOrSelect();
                return false;
            } else if ($.trim(this.get$GlobalLogo().val()) == '' &&
                $.trim(this.get$GlobalLogoFile().val()) == '') {
                alert('页头Logo未设置!');
                this.get$GlobalLogo().focusOrSelect();
                return false;
            } else if ($.trim(this.get$indexHeadPoster().val()) == '' &&
                $.trim(this.get$indexHeadPosterFile().val()) == '') {
                alert('首页头部海报未设置!');
                this.get$indexHeadPoster().focusOrSelect();
                return false;
            } else if ($.trim(this.get$indexFootPoster().val()) == '' &&
                $.trim(this.get$indexFootPosterFile().val()) == '') {
                alert('首页尾部海报未设置!');
                this.get$indexFootPoster().focusOrSelect();
                return false;
            } else if ($.trim(this.get$BranchRankImage().val()) == '' &&
                $.trim(this.get$BranchRankImageFile().val()) == '') {
                alert('品牌排行图片未设置!');
                this.get$BranchRankImage().focusOrSelect();
                return false;
            } else if ($.trim(this.get$NoFoundImage().val()) == '' &&
                $.trim(this.get$NoFoundImageFile().val()) == '') {
                alert('无对应商品时显示的图片未设置!');
                this.get$NoFoundImage().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };
    input.get$GlobalBanner().focusOrSelect();
    angularUtils.controller(function ($scope) {
        $scope.validateInput = buildNormalValidationCallback(input);
    });
})();
