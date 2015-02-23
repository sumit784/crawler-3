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
    var $elements = {
        selectButtons: $('div.content div.branch button'),
        commodityDescription: $('#commodityDescription'),
        commodityDescriptionEffect: $('#commodityDescriptionEffect')
    };
    $elements.selectButtons.click(function () {
        $(this).css({
            'background-color': '#ffffff',
            'background-image': 'url("resources/css/images/edit-commodity/select.png")'
        });
    });
    var commdityDescriptionEffect = new CommodityDescription($elements.commodityDescriptionEffect.attr('id'));
    $elements.commodityDescription.keyup(function () {
        var text = $(this).val();
        commdityDescriptionEffect.text(text);
    }).trigger('keyup');

    angularUtils.controller(function ($scope, $http) {
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
        $scope.onShowIdChange = function () {
            var showId = $.trim($scope['showId']);
            var crawlerLink = "", buyLink = "";
            if (showId != "") {
                crawlerLink = "http://s.etao.com/detail/" + showId + ".html";
                buyLink = "http://detail.tmall.com/item.htm?id=" + showId;
            }
            $('#crawlerLink').text(crawlerLink).attr('href', crawlerLink).prev().val(crawlerLink);
            $('#buyLink').text(buyLink).attr('href', buyLink).prev().val(buyLink);
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
    })
    ;
})
();
