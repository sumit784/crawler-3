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
        if (data.success) {
            alert("操作成功");
        } else {
            alert(data.detail);
        }
    });

    angularUtils.controller(function ($scope, $http) {
        /*
         $scope.commodityDescription = "产品名称：Nike/耐克 473284\n"
         + "颜色分类：444动力蓝/蓝黑/白\n"
         + "款号：473284\n"
         + "品牌：Nike/耐克\n"
         + "上市时间：2015春季\n"
         + "吊牌价：699\n"
         + "性别：男子\n"
         + "鞋帮高度：中帮\n"
         + "鞋码：39 40 40.5 41 42 42.5 43 44 44.5 45 46\n"
         + "闭合方式：系带\n"
         + "是否瑕疵：否";
         */
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
            $.each($scope.branch.items, function () {
                if (this.id == id) {
                    $scope.branch.selected.id = this.id;
                    $scope.branch.selected.name = this.name;
                    loadBranches(branchJsonUrl + "?parentId=" + this.id, $scope.subBranch1);
                    $scope.subBranch2.disabled = true;
                }
            });
        };
        $scope.selectSubBranch1 = function (id) {
            $.each($scope.subBranch1.items, function () {
                if (this.id == id) {
                    $scope.subBranch1.selected.id = this.id;
                    $scope.subBranch1.selected.name = this.name;
                    loadBranches(branchJsonUrl + "?parentId=" + this.id, $scope.subBranch2);
                }
            });
        };
        $scope.selectSubBranch2 = function (id) {
            $.each($scope.subBranch2.items, function () {
                if (this.id == id) {
                    $scope.subBranch2.selected.id = this.id;
                    $scope.subBranch2.selected.name = this.name;
                }
            });
        };
        $scope.runCrawler = function () {
            var showId = $.trim($scope['showId']);
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

        function loadBranches(url, branchObj) {
            var items = branchObj.items;
            $http.get(url).success(function (data) {
                items.splice(0, items.length);
                branchObj.disabled = (data.length == 0);
                branchObj.selected.id = branchObj.default.id;
                branchObj.selected.name = branchObj.default.name;
                $.each(data, function () {
                    items.push({id: this.id, name: this.name});
                });
            });
        }
    });
})();
