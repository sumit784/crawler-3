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

    var branchInput = {
        $form: $('#branchForm'),
        get$CategoryId: function () {
            return this.$form.find('input[name=categoryId]');
        },
        get$AddSubmit: function () {
            return $('#addBranchSubmit');
        },
        get$BranchLogos: function () {
            return this.$form.find('img.branch-logo');
        },
        _get$BranchIds: function () {
            return this.$form.find('input[name=branchIds]');
        },
        _insertInput: function ($branchDiv, branchId) {
            $branchDiv.append('<input type="hidden" name="branchIds" value="' + branchId + '"/>');
        },
        _removeInput: function ($branchDiv) {
            $branchDiv.find('input').remove();
        },
        selectBranch: function (target) {
            var $target = $(target);
            var selectedClass = 'selected';
            if ($target.hasClass(selectedClass)) {
                $target.removeClass(selectedClass);
                this._removeInput($(target).parent());
            } else {
                $target.addClass(selectedClass);
                this._insertInput($(target).parent(), $target.dataOptions()['id']);
            }
        },
        show: function (categoryId, excludeBranchIds) {
            this.$form.css({
                top: '5%',
                height: '90%',
                left: '30%',
                width: '40%',
                overflow: 'auto'
            });
            this.get$CategoryId().val(categoryId);
            var self = this;
            this.get$BranchLogos().each(function () {
                var $this = $(this);
                var id = parseInt($this.dataOptions()['id']);
                var $branchDiv = $this.parent();
                if (_.indexOf(excludeBranchIds, id) >= 0) {
                    $branchDiv.hide();
                } else {
                    $branchDiv.show();
                }
                self._removeInput($branchDiv);
            });
            transparentBackground.show();
            this.$form.show();
        },
        hide: function () {
            this.$form.hide();
            transparentBackground.hide();
        },
        validate: function () {
            if (this._get$BranchIds().size() == 0) {
                alert('未选择品牌');
                return false;
            } else {
                return true;
            }
        }
    };
    branchInput.$form.ajaxForm(normalSubmitCallback);

    var posterInput = {
        $form: $('#posterForm'),
        get$Id: function () {
            return this.$form.find('input[name=id]');
        },
        get$CategoryId: function () {
            return this.$form.find('input[name=categoryId]');
        },
        get$AddSubmit: function () {
            return $('#addPosterSubmit');
        },
        get$EditSubmit: function () {
            return $('#editPosterSubmit');
        },
        get$Url: function () {
            return this.$form.find('input[name=url]');
        },
        get$Link: function () {
            return this.$form.find('input[name=link]');
        },
        get$UploadFile: function () {
            return this.$form.find('input[name=uploadFile]');
        },
        show: function (id, categoryId, imageUrl, link) {
            this.get$Id().val(id);
            this.get$CategoryId().val(categoryId);
            this.get$Url().val(imageUrl);
            this.get$Link().val(link);
            this.get$UploadFile().val(null);
            transparentBackground.show();
            this.$form.show();
            this.get$Url().focusOrSelect();
        },
        hide: function () {
            this.$form.hide();
            transparentBackground.hide();
        },
        validate: function () {
            if (this.get$Url().val() == '' && this.get$UploadFile().val() == '') {
                alert('图片未设置');
                this.get$Url().focusOrSelect();
                return false;
            } else {
                return true;
            }
        }
    };
    posterInput.$form.ajaxForm(normalSubmitCallback);

    var enlargePoster = {
        $div: $('#enlargePoster'),
        show: function (url, top) {
            transparentBackground.show();
            this.$div.css('top', top).show().find('image').empty()
                .append('<img src="'+url+'" onload="adjustImageWidth(500)"/>');
        },
        hide: function () {
            this.$div.hide();
            transparentBackground.hide();
        }
    };

    angularUtils.controller(function ($scope) {
        // actions about category
        $scope.validateInput = buildNormalValidationCallback(input);
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
            var id = $tr.parseIntegerInId();
            var name = $.trim($tr.find('td.name').text());
            var parentId = $tr.find('td.parent').dataOptions()['parentId'];

            input.get$Id().val(id);
            input.get$Name().val(name).focusOrSelect();
            input.get$ParentId().val(parentId);
            input.get$AddSubmit().attr('disabled', true);
            input.get$EditSubmit().attr('disabled', false);
            scrollTop(input.$form);
        };
        $scope.rankUpCategory = function (event) {
            var target = event.target;
            $.post('admin-category-rank-up', {
                id: getTableRowIdByImgElement(target)
            }, normalSubmitCallback);
        };
        $scope.rankDownCategory = function (event) {
            var target = event.target;
            $.post('admin-category-rank-down', {
                id: getTableRowIdByImgElement(target)
            }, normalSubmitCallback);
        };

        // actions about search word
        $scope.validateSearchWordInput = buildNormalValidationCallback(searchWordInput);
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
            var id = $tr.parseIntegerInId();
            var content = $.trim($tr.find('td.content').text());
            var hot = $tr.find('td.content span').hasClass('hot');
            var categoryId = getParent($tr, 'tr').parseIntegerInId();

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

        // actions about branches
        $scope.validateBranchInput = buildNormalValidationCallback(branchInput);
        $scope.addBranch = function (event) {
            var $tr = getParent($(event.target), 'tr');
            var categoryId = $tr.parseIntegerInId();
            var branchIds = [];
            $tr.find('td.branch tr').each(function () {
                branchIds.push($(this).parseIntegerInId());
            });
            branchInput.show(categoryId, branchIds);
        };
        $scope.deleteBranch = function (event) {
            var target = event.target;
            $.post('admin-category-branch-delete', getCategoryBranchParam(target),
                buildSubmitCallback(function () {
                    removeTableRow(target);
                }));
        };
        $scope.selectBranch = function (event) {
            branchInput.selectBranch(event.target);
        };
        $scope.cancelBranchInput = function () {
            branchInput.hide();
        };
        $scope.rankUpBranch = function (event) {
            var target = event.target;
            $.post('admin-category-branch-rank-up', getCategoryBranchParam(target),
                buildSubmitCallback(function () {
                    moveUpTableRow(target);
                }));
        };
        $scope.rankDownBranch = function (event) {
            var target = event.target;
            $.post('admin-category-branch-rank-down', getCategoryBranchParam(target),
                buildSubmitCallback(function () {
                    moveDownTableRow(target);
                }));
        };
        function getCategoryBranchParam(target) {
            return  {
                'branchId': getInnerRowId(target),
                'categoryId': getOuterRowId(target)
            }
        }

        function getOuterRowId(target) {
            var $this = $(target);
            return getParent(getParent($this, 'tr'), 'tr').parseIntegerInId();
        }

        function getInnerRowId(target) {
            var $this = $(target);
            return getParent($this, 'tr').parseIntegerInId();
        }

        // actions about poster
        $scope.validatePosterInput = buildNormalValidationCallback(posterInput);
        $scope.addPoster = function (event) {
            posterInput.get$AddSubmit().show();
            posterInput.get$EditSubmit().hide();
            posterInput.show(0, getTableRowIdByImgElement(event.target), '', '');
        };
        $scope.editPoster = function (event) {
            var $this = $(event.target);
            var $tr = getParent($this, 'tr');
            var id = $tr.parseIntegerInId();
            var imageUrl = $tr.find('img').attr('src');
            var link = $tr.find('a').attr('href');
            var categoryId = getParent($tr, 'tr').parseIntegerInId();

            posterInput.get$AddSubmit().hide();
            posterInput.get$EditSubmit().show();
            posterInput.show(id, categoryId, imageUrl, link);
        };
        $scope.deletePoster = function (event) {
            var target = event.target;
            $.post('admin-category-branch-delete', {
                'id': getTableRowIdByImgElement(event.target)
            }, buildSubmitCallback(function () {
                removeTableRow(target);
            }));
        };
        $scope.enlargePoster = function (event) {
            var $target = $(event.target);
            var url = $target.attr('src');
            var top = $target.offset().top;
            enlargePoster.show(url, top);
        };
        $scope.closeEnlargePoster = function () {
            enlargePoster.hide();
        };
        $scope.cancelPosterInput = function () {
            posterInput.hide();
        };
        $scope.rankUpPoster = function (event) {
            var target = event.target;
            $.post('admin-category-poster-rank-up', {
                'id': getTableRowIdByImgElement(event.target)
            }, buildSubmitCallback(function () {
                moveUpTableRow(target);
            }));
        };
        $scope.rankDownPoster = function (event) {
            var target = event.target;
            $.post('admin-category-poster-rank-down', {
                'id': getTableRowIdByImgElement(event.target)
            }, buildSubmitCallback(function () {
                moveDownTableRow(target);
            }));
        };
    });
})();
