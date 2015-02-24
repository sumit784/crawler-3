function CommodityDescription(id) {
    this._div = $('#' + id);
}
CommodityDescription.prototype.text = function (text) {
    text = $.trim(text);
    var lines = text.split('\n');
    if (lines.length == 0) {
        this._div.html('');
    }

    while (lines.length % 3 != 2) {
        lines.push("");
    }
    var html = '<table>';
    var columnCount = 3;
    for (var i = 0, len = lines.length; i < len; i++) {
        if (i % columnCount == 0) {
            html += '<tr>';
        }
        html += '<td><div>' + $.trim(lines[i]) + '</div></td>';
        if (i % columnCount == columnCount - 1) {
            html += '</tr>';
        }
    }
    html += '</table>';
    this._div.empty().html(html);
};
(function () {
    var $commodityDescription = $('#commodityDescription');
    var $selectButtons = $('div.content div.branch button');
    var $commodityDescriptionEffect = $('#commodityDescriptionEffect');
    var $form = $('#mainForm');
    var $branchId = $('input[name=branchId]');
    var $commodityName = $('input[name=commodityName]');
    var $submitInfo = $('#submitInfo');
    var $showId = $('input[name=showId]');
    var $initBranchId = $('#initBranchId');

    $selectButtons.click(function () {
        $(this).css({
            'background-color': '#ffffff',
            'background-image': 'url("resources/css/images/edit-commodity/select.png")'
        });
    });
    var commodityDescriptionEffect = new CommodityDescription($commodityDescriptionEffect.attr('id'));
    $commodityDescription.keyup(function () {
        var text = $(this).val();
        commodityDescriptionEffect.text(text);
    }).trigger('keyup');

    $form.ajaxForm(function (data) {
        $submitInfo.fadeOut(200);
        if (data.success) {
            alert("操作成功");
        } else {
            alert(data.detail);
        }
    });
    $('#publishCommodity').click(function (e) {
        if (parseInt($branchId.val()) <= 0) {
            alert('品牌未设置!');
            e.preventDefault();
        } else if ($commodityName.val() == '') {
            alert('商品名称未设置!');
            e.preventDefault();
        } else {
            $submitInfo.fadeIn(200);
        }
    });

    angularUtils.controller(function ($scope, $http) {
        $scope.branch = {
            default: { id: 0, name: '(品牌选择)' },
            selected: { id: 0, name: '(品牌选择)' },
            items: []
        };
        var branchJsonUrl = "json/branch.json";
        loadBranches(branchJsonUrl, $scope.branch);
        $scope.subBranch1 = {
            disabled: true,
            default: { id: 0, name: '(一级子品牌)' },
            selected: { id: 0, name: '(一级子品牌)' },
            items: []
        };
        $scope.subBranch2 = {
            disabled: true,
            default: { id: 0, name: '(二级子品牌)' },
            selected: { id: 0, name: '(二级子品牌)' },
            items: []
        };
        $scope.selectBranch = function (id) {
            selectBranch($scope.branch, id, $scope.subBranch1);
            $scope.subBranch2.disabled = true;
        };
        $scope.selectSubBranch1 = function (id) {
            selectBranch($scope.subBranch1, id, $scope.subBranch2);
        };
        $scope.selectSubBranch2 = function (id) {
            selectBranch($scope.subBranch2, id);
        };
        $scope.imageUrls = [];
        $scope.detailImageUrls = [];
        var commodityId = $.url.param("id");
        if (commodityId) {
            $http.get("commodityPicture.json?commodityId=" + commodityId).success(function (data) {
                $scope.imageUrls = data['pictures'];
                $scope.detailImageUrls = data['detailPictures'];
            });
            if ($initBranchId.val() != '') {
                $http.get('json/parentBranch.json?branchId=' + $initBranchId.val()).success(function (data) {
                    var branchId = data['branchId'];
                    if (branchId) {
                        selectBranch($scope.branch, branchId, $scope.subBranch1, function () {
                            var subBranch1Id = data["subBranch1Id"];
                            if (subBranch1Id) {
                                selectBranch($scope.subBranch1, subBranch1Id, $scope.subBranch2, function () {
                                    var subBranch2Id = data["subBranch2Id"];
                                    if (subBranch2Id) {
                                        selectBranch($scope.subBranch2, subBranch2Id);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        $scope.deleteImage = function (index) {
            $scope.imageUrls.splice(index, 1);
        };
        $scope.deleteDetailImage = function (index) {
            $scope.detailImageUrls.splice(index, 1);
        };
        $scope.runCrawler = function () {
            var showId = $.trim($showId.val());
            var $crawlerLink = $('#crawlerLink');
            var $buyLink = $('#buyLink');
            var $commodityName = $('#commodityName');
            if (showId != "") {
                var crawlerLink = "http://s.etao.com/detail/" + showId + ".html";
                $scope.runningCrawler = true;
                $http.get("commodity-crawler.json?url=" + crawlerLink).success(function (data) {
                    $crawlerLink.text(crawlerLink).attr('href', crawlerLink).prev().val(crawlerLink);
                    var buyLink = data['buyUrl'];
                    $buyLink.text(buyLink).attr('href', buyLink).prev().val(buyLink);
                    var name = data['name'];
                    $commodityName.text(name).attr('href', crawlerLink).prev().val(name);

                    $scope.imageUrls = data['imageUrls'];
                    $scope.detailImageUrls = data['detailImageUrls'];

                    $scope.runningCrawler = false;
                });
            } else {
                $crawlerLink.text("").attr('href', "").prev().val("");
                $buyLink.text("").attr('href', "").prev().val("");
                $commodityName.text("").attr('href', "").prev().val("");
            }
        };
        $scope.addAppraiseGroup = function (event, name) {
            var input = '<input type="text" class="form-control" name="' + name + '"/>';
            $(event.target).before(input).prev().focus();
        };

        function selectBranch(branchObj, idToSelect, subBranchObj, callBack) {
            $.each(branchObj.items, function () {
                if (this.id == idToSelect) {
                    branchObj.selected.id = this.id;
                    branchObj.selected.name = this.name;
                    if (subBranchObj) {
                        loadBranches(branchJsonUrl + "?parentId=" + this.id, subBranchObj, callBack);
                    }
                }
            });
        }

        function loadBranches(url, branchObj, callBack) {
            var items = branchObj.items;
            $http.get(url).success(function (data) {
                items.splice(0, items.length);
                branchObj.disabled = (data.length == 0);
                branchObj.selected.id = branchObj.default.id;
                branchObj.selected.name = branchObj.default.name;
                $.each(data, function () {
                    items.push({id: this.id, name: this.name});
                });
                if (callBack) {
                    callBack();
                }
            });
        }
    });
})();
