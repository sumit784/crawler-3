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
    var $selectButtons = $('div.content div.basic button');
    var $commodityDescriptionEffect = $('#commodityDescriptionEffect');
    var $form = $('#mainForm');
    var $branchId = $('input[name=branchId]');
    var $commodityName = $('input[name=commodityName]');
    var $submitInfo = $('#submitInfo');
    var $showId = $('input[name=showId]');
    var $initBranchId = $('#initBranchId');
    var $initCategoryId = $('#initCategoryId');
    var $enlargeImage = $('#enlargeImage');

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
            location.href = "admin";
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
        initBranchSelects();
        initCategorySelects();
        initImages();
        $scope.runCrawler = function () {
            var showId = $.trim($showId.val());
            if (showId == "") {
                $scope.crawlerInfo = '请输入商品ID！';
                $scope.showCrawlerInfo = true;
                $showId.focus();
                return;
            }

            var validateUrl = "json/commodityShowIdValidate.json?showId=" + showId;
            var commodityId = $.url.param("id");
            if (commodityId != null) {
                validateUrl += "&id=" + commodityId;
            }
            $http.get(validateUrl).success(function (data) {
                if (!data['result']) {
                    $scope.crawlerInfo = '该商品ID已经存在！';
                    $scope.showCrawlerInfo = true;
                } else {
                    var $crawlerLink = $('#crawlerLink');
                    var $buyLink = $('#buyLink');
                    var $commodityName = $('#commodityName');
                    var crawlerLink = "http://s.etao.com/detail/" + showId + ".html";
                    $scope.crawlerInfo = '正在抓取网页...';
                    $scope.showCrawlerInfo = true;
                    $http.get("commodity-crawler.json?url=" + crawlerLink).success(function (data) {
                        $crawlerLink.text(crawlerLink).attr('href', crawlerLink).prev().val(crawlerLink);
                        var buyLink = data['buyUrl'];
                        $buyLink.text(buyLink).attr('href', buyLink).prev().val(buyLink);
                        var name = data['name'];
                        $commodityName.text(name).attr('href', crawlerLink).prev().val(name);

                        $scope.imageUrls = data['imageUrls'];
                        $scope.detailImageUrls = data['detailImageUrls'];
                        $scope.showCrawlerInfo = false;
                    });

                }
            });
        };
        $scope.addAppraiseGroup = function (event, name) {
            var input = '<input type="text" class="form-control" name="' + name + '"/>';
            $(event.target).before(input).prev().focus();
        };

        function initImages() {
            $scope.deleteImage = function (index) {
                $scope.imageUrls.splice(index, 1);
            };
            $scope.deleteDetailImage = function (index) {
                $scope.detailImageUrls.splice(index, 1);
            };
            $scope.enlargeImage = function (imageUrl, event) {
                var $image = $enlargeImage.find('img');
                $image.attr('src', imageUrl);

                var imageHeight = images.getHeight($image);
                var imageWidth = images.getWidth($image);
                var totalHeight = window.screen.availHeight - 120;
                if (imageHeight > totalHeight) {
                    imageWidth = imageWidth * totalHeight / imageHeight;
                    imageHeight = totalHeight;
                }
                $image.css({
                    width: imageWidth,
                    height: imageHeight
                });

                var pageX = event.clientX;
                var pageY = event.clientY;
                var totalWidth = document.body.clientWidth;
                var x, y;
                if (pageX > totalWidth / 2) {
                    x = pageX - imageWidth - 100;
                } else {
                    x = pageX + 100;
                }
                y = pageY - imageHeight / 2;
                if (y < 0) {
                    y = 0;
                }

                $enlargeImage.css({
                    left: x,
                    top: y
                }).show();
            };
            $scope.closeEnlargeImage = function () {
                $enlargeImage.hide();
            };
            $scope.imageUrls = [];
            $scope.detailImageUrls = [];
            var commodityId = $.url.param("id");
            if (commodityId) {
                // load image after 2 second, or page will be blocked
                setTimeout(function () {
                    $http.get("commodityPicture.json?commodityId=" + commodityId).success(function (data) {
                        $scope.imageUrls = data['pictures'];
                        $scope.detailImageUrls = data['detailPictures'];
                    });
                }, 2000);
            }
        }

        function initCategorySelects() {
            $scope.category = {
                default: {id: 0, 'name': '(一级分类)'},
                selected: {id: 0, 'name': '(一级分类)'},
                items: []
            };
            var categoryJsonUrl = "json/category.json";
            loadSelectData(categoryJsonUrl, $scope.category);
            $scope.subCategory = {
                disabled: true,
                default: {id: 0, name: '(二级分类)'},
                selected: {id: 0, name: '(二级分类)'},
                items: []
            };
            $scope.selectCategory = function (id) {
                selectItem($scope.category, id, $scope.subCategory, categoryJsonUrl);
            };
            $scope.selectSubCategory = function (id) {
                selectItem($scope.subCategory, id);
            };
            var initCategoryId = $initCategoryId.val();
            if (initCategoryId != '') {
                $http.get('json/parentCategory.json?categoryId=' + initCategoryId).success(function (data) {
                    var categoryId = data['categoryId'];
                    if (categoryId) {
                        selectItem($scope.category, categoryId, $scope.subCategory, categoryJsonUrl, function () {
                            var subCategoryId = data["subCategoryId"];
                            if (subCategoryId) {
                                selectItem($scope.subCategory, subCategoryId);
                            }
                        });
                    }
                });
            }
        }

        function initBranchSelects() {
            $scope.branch = {
                default: {id: 0, name: '(品牌选择)'},
                selected: {id: 0, name: '(品牌选择)'},
                items: []
            };
            var branchJsonUrl = "json/branch.json";
            loadSelectData(branchJsonUrl, $scope.branch);
            $scope.subBranch1 = {
                disabled: true,
                default: {id: 0, name: '(一级子品牌)'},
                selected: {id: 0, name: '(一级子品牌)'},
                items: []
            };
            $scope.subBranch2 = {
                disabled: true,
                default: {id: 0, name: '(二级子品牌)'},
                selected: {id: 0, name: '(二级子品牌)'},
                items: []
            };
            $scope.selectBranch = function (id) {
                selectItem($scope.branch, id, $scope.subBranch1, branchJsonUrl);
                $scope.subBranch2.disabled = true;
            };
            $scope.selectSubBranch1 = function (id) {
                selectItem($scope.subBranch1, id, $scope.subBranch2, branchJsonUrl);
            };
            $scope.selectSubBranch2 = function (id) {
                selectItem($scope.subBranch2, id);
            };
            var initBranchId = $initBranchId.val();
            if (initBranchId != '') {
                $http.get('json/parentBranch.json?branchId=' + initBranchId).success(function (data) {
                    var branchId = data['branchId'];
                    if (branchId) {
                        selectItem($scope.branch, branchId, $scope.subBranch1, branchJsonUrl, function () {
                            var subBranch1Id = data["subBranch1Id"];
                            if (subBranch1Id) {
                                selectItem($scope.subBranch1, subBranch1Id, $scope.subBranch2, branchJsonUrl, function () {
                                    var subBranch2Id = data["subBranch2Id"];
                                    if (subBranch2Id) {
                                        selectItem($scope.subBranch2, subBranch2Id);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }

        function selectItem(dataModel, idToSelect, subDataModel, url, callBack) {
            $.each(dataModel.items, function () {
                if (this.id == idToSelect) {
                    dataModel.selected.id = this.id;
                    dataModel.selected.name = this.name;
                    if (subDataModel) {
                        loadSelectData(url + "?parentId=" + this.id, subDataModel, callBack);
                    }
                }
            });
        }

        function loadSelectData(url, dataModel, callBack) {
            var items = dataModel.items;
            $http.get(url).success(function (data) {
                items.splice(0, items.length);
                dataModel.disabled = (data.length == 0);
                dataModel.selected.id = dataModel.default.id;
                dataModel.selected.name = dataModel.default.name;
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
