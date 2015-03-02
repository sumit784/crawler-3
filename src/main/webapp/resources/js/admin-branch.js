;
(function () {
    var $branchForm = $('#branchForm');
    var $table = $('table.normal');
    //var $deleteImages = $table.find('img.delete');
    //var $editImages = $table.find('img.edit');
    var $addSubmit = $('#addSubmit');
    var $editSubmit = $('#editSubmit');
    var $name = $('#name');
    var $firstLetter = $('#firstLetter');

    $branchForm.ajaxForm(function (data) {
        if (data.success) {
            location.reload();
        } else {
            alert(data.detail);
        }
    });
    /*
     $deleteImages.click(function () {
     $.post('admin-branch-delete', {
     id: getBranchIdByImgElement(this)
     }, function (data) {
     if (data.success) {
     location.reload();
     } else {
     alert(data.detail);
     }
     });
     });
     */
    var input = {
        get$Id: function () {
            return $branchForm.find('input[name=id]');
        },
        get$Name: function () {
            return $branchForm.find('input[name=name]');
        },
        get$FirstLetter: function () {
            return $branchForm.find('input[name=firstLetter]');
        },
        get$ParentId: function () {
            return $branchForm.find('select[name=parentId]');
        },
        get$Logo: function () {
            return $branchForm.find('input[name=logo]');
        },
        get$LogoFile: function () {
            return $branchForm.find('input[name=logoFile]');
        },
        get$SquareLogo: function () {
            return $branchForm.find('input[name=squareLogo]');
        },
        get$SquareLogoFile: function () {
            return $branchForm.find('input[name=squareLogoFile]');
        },
        get$Slogan: function () {
            return $branchForm.find('textarea[name=slogan]');
        },
        validate: function () {
            function isEmpty($input) {
                var empty = ($.trim($input.val()) == '');
                if (empty) {
                    $input.focusOrSelect();
                }
                return empty;
            }

            if (isEmpty(this.get$Name())) {
                alert('名称未设置');
                return false
            } else if (isEmpty(this.get$FirstLetter())) {
                alert('首字母未设置');
                return false;
            }

            if ($.trim(this.get$Logo().val()) == '' &&
                $.trim(this.get$LogoFile().val()) == '') {
                alert('矩形logo未设置');
                this.get$Logo().focusOrSelect();
                return false;
            }

            if ($.trim(this.get$SquareLogo().val()) == '' &&
                $.trim(this.get$SquareLogoFile().val()) == '') {
                alert('方形logo未设置');
                this.get$SquareLogo().focusOrSelect();
                return false;
            }

            return true;
        }
    };
    /*
     $editImages.click(function () {
     var $tr = getParent($(this), 'tr');
     var id = $tr.attr('id').replace(/\D/g, '');
     var name = $tr.find('td.name').text();
     var firstLetter = $tr.find('td.firstLetter').text();
     var parentId = $tr.find('td.parent').dataOptions()['parentId'];
     var logo = $tr.find('td.logo a').attr('title');
     var squareLogo = $tr.find('td.squareLogo a').attr('title');
     var slogan = $tr.find('td.slogan').text();

     input.get$Id().val(id);
     input.get$Name().val(name).focusOrSelect();
     input.get$FirstLetter().val(firstLetter);
     input.get$ParentId().val(parentId);
     input.get$Logo().val(logo);
     input.get$SquareLogo().val(squareLogo);
     input.get$Slogan().val(slogan);

     $addSubmit.attr('disabled', true);
     $editSubmit.attr('disabled', false);
     });
     */
    $name.keyup(function () {
        var name = $(this).val();
        if (name != '') {
            $.post('chinese-letter.json?string=' + name, function (data) {
                if (data.result) {
                    $firstLetter.val(data.result);
                }
            });
        } else {
            $firstLetter.val("");
        }
    });
    function getBranchIdByImgElement(image) {
        var $tr = getParent($(image), "tr");
        return $tr.attr('id').replace(/\D/g, '');
    }

    angularUtils.controller(function ($scope) {
        $scope.validateInput = function (event) {
            if (!input.validate()) {
                event.preventDefault();
                return false;
            } else {
                return true;
            }
        };
        $scope.editBranch = function (event) {
            var $tr = getParent($(event.target), 'tr');
            var id = $tr.attr('id').replace(/\D/g, '');
            var name = $tr.find('td.name').text();
            var firstLetter = $tr.find('td.firstLetter').text();
            var parentId = $tr.find('td.parent').dataOptions()['parentId'];
            var logo = $tr.find('td.logo a:first').attr('title');
            var squareLogo = $tr.find('td.logo a:last').attr('title');
            var slogan = $tr.find('td.slogan').text();

            input.get$Id().val(id);
            input.get$Name().val(name).focusOrSelect();
            input.get$FirstLetter().val(firstLetter);
            input.get$ParentId().val(parentId);
            input.get$Logo().val(logo);
            input.get$SquareLogo().val(squareLogo);
            input.get$Slogan().val(slogan);

            $addSubmit.attr('disabled', true);
            $editSubmit.attr('disabled', false);
        };
        $scope.deleteBranch = function (event) {
            $.post('admin-branch-delete', {
                id: getBranchIdByImgElement(event.target)
            }, function (data) {
                if (data.success) {
                    location.reload();
                } else {
                    alert(data.detail);
                }
            });
        };
        $scope.shoppes = [];
        $scope.addShoppe = function () {
            $scope.shoppes.push({name: '', url: ''});
            setTimeout(function () {
                $('div.content form div.shoppe div.right div:last').find('input:first').focus();
            }, 100);
        };
    });
})();
