;
(function () {
    var $mainForm = $('#mainForm');
    var $viewDiv = $('#viewDiv');
    var editor = CKEDITOR.replace('content', {
        'height': getTotalHeight() - 290
    });
    setTimeout(function () {
        editor.focus();
    }, 500);
    angularUtils.controller(function ($scope) {
        function getContent() {
            return editor.getData();
        }

        $scope.publish = function (event) {
            if ($.trim(getContent()) == '') {
                alert('文章内容不能为空');
                event.preventDefault();
                return false;
            } else {
                return true;
            }
        };
        $scope.view = function () {
            $mainForm.hide();
            $viewDiv.show().find('div.article').empty().html(getContent());
        };
        $scope.cancelEdit = function () {
            location.href = "admin-article-list.html";
        };
        $scope.cancelView = function () {
            $viewDiv.hide();
            $mainForm.show();
        }
    });
})();
