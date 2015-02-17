;(function () {
    angularUtils.controller(function ($scope) {
        var letters = getBranchGroupLetters();
        $scope.branches = [];
        for (var i = 0; i < letters.length; i++) {
            var letter = letters[i];
            var item = {
                'letter': letters[i],
                'branches': getBranches(letter)
            };
            $scope.branches.push(item);
        }
        $scope.letters = splitArray(letters, 3);
        $scope.letterClick = function (letter) {
            $('div.branchGroup').each(function () {
                if ($.trim($(this).find('div.title').text()) == $.trim(letter)) {
                    scrollTop($(this));
                    return false;
                } else {
                    return true;
                }
            });
        };
    });

    function getBranchGroupLetters() {
        var letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var arr = [];
        for (var i = 0; i < letters.length; i++) {
            arr.push(letters[i]);
        }
        arr.push('0-9');
        return arr;
    }

    function getBranches(letter) {
        var branches = [];
        for (var i = 0; i < 18; i++) {
            if (i > 5 && i < 12) {
                branches.push({
                    "href": "javascript:void(0)",
                    "src": "resources/css/images/branchs/branch2.png",
                    "text": "BRACCIALINI"
                });
            } else {
                branches.push({
                    "href": "javascript:void(0)",
                    "src": "resources/css/images/branchs/branch1.png",
                    "text": "OLAY"
                });
            }
        }
        return splitArray(branches, 7);
    }
})();
