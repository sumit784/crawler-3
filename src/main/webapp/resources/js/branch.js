;
(function () {
    angularUtils.controller(function ($scope, $http) {
        $http.get('json/groupedBranches.json').success(function (data) {
            $scope.branches = [];
            for (var i = 0, len = letters.length; i < len; i++) {
                var letter = letters[i];
                if (letter in data) {
                    $scope.branches.push({'letter': letters[i], 'branches': splitArray(data[letter], 7)});
                } else {
                    $scope.branches.push({'letter': letters[i], 'branches': []});
                }
            }
        });
        var letters = getBranchGroupLetters();
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
})();
