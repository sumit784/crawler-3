(function () {
    var $commodityDescription = $('#commodityDescription');
    var $selectButtons = $('div.content div.basic button');
    var $branchId = $('input[name=branchId]');
    var $commodityName = $('input[name=commodityName]');
    var $submitInfo = $('#submitInfo');
    var $showId = $('input[name=showId]');
    var $initBranchId = $('#initBranchId');
    var $initCategoryId = $('#initCategoryId');

    var enlargeImage = {
        $div: $('#enlargeImage'),
        get$SubDiv: function (className) {
            return this.$div.find('div.' + className);
        },
        get$Images: function () {
            return this.$div.find('img');
        },
        get$NormalDiv: function () {
            return this.get$SubDiv('normal');
        },
        get$DetailDiv: function () {
            return this.get$SubDiv('detail');
        },
        hideAll: function () {
            this.get$Images().hide();
            this.get$NormalDiv().hide();
            this.get$DetailDiv().hide();
        },
        show$Image: function (type, index) {
            var $subDiv = this.get$SubDiv(type).eq(index).show();
            return $subDiv.find('img').show();
        },
        adjustImagePosition: function ($image) {
            var totalWidth = this.$div.parent().width();
            //var x = (totalWidth - images.getWidth($image)) / 2;
            var x = (totalWidth - $image.get(0).width) / 2;
            this.$div.css('left', x);
        },
        show: function () {
            transparentBackground.show(5);
            this.$div.show();
        },
        hide: function () {
            this.$div.hide();
            transparentBackground.hide();
        }
    };

    $selectButtons.click(function () {
        $(this).css({
            'background-color': '#ffffff',
            'background-image': 'url("resources/css/images/edit-commodity/select.png")'
        });
    });
    $commodityDescription.keyup(function () {
        var text = $(this).val();
        loadCommodityParameters('commodityDescriptionEffect', text);
    }).trigger('keyup');

    $('#publishCommodity').click(function (e) {
        if (parseInt($branchId.val()) <= 0) {
            alert('品牌未设置!');
            e.preventDefault();
            return false;
        } else if ($commodityName.val() == '') {
            alert('商品名称未设置!');
            e.preventDefault();
            return false;
        } else {
            $submitInfo.fadeIn(200);
            return true;
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
                        if (!data.success) {
                            $scope.crawlerInfo = data.detail;
                            return;
                        }

                        $crawlerLink.text(crawlerLink).attr('href', crawlerLink).prev().val(crawlerLink);
                        var buyLink = data['buyUrl'];
                        $buyLink.text(buyLink).attr('href', buyLink).prev().val(buyLink);
                        var name = data['name'];
                        $commodityName.text(name).attr('href', crawlerLink).prev().val(name);

                        $scope.imageUrls = JSUtils.copyArray(data['imageUrls']);
                        $scope.originalImageUrls = JSUtils.copyArray(data['imageUrls']);
                        $scope.detailImageUrls = JSUtils.copyArray(data['detailImageUrls']);
                        $scope.originalDetailImageUrls = JSUtils.copyArray(data['detailImageUrls']);
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
                JSUtils.removeArrayItem($scope.imageUrls, index);
                JSUtils.removeArrayItem($scope.originalImageUrls, index);
            };
            $scope.deleteDetailImage = function (index) {
                JSUtils.removeArrayItem($scope.detailImageUrls, index);
                JSUtils.removeArrayItem($scope.originalDetailImageUrls, index);
            };
            $scope.enlargeImage = function (index, event, type) {
                enlargeImage.hideAll();
                var $targetImage = enlargeImage.show$Image(type, index);
                //enlargeImage.adjustImageSize($targetImage);
                enlargeImage.adjustImagePosition($targetImage);
                enlargeImage.show();
            };
            $scope.closeEnlargeImage = function () {
                enlargeImage.hide();
            };
            $scope.imageUrls = [];
            $scope.detailImageUrls = [];
            var commodityId = $.url.param("id");
            if (commodityId) {
                // load image after 1 second, or page will be blocked
                setTimeout(function () {
                    $http.get("commodityPicture.json?commodityId=" + commodityId).success(function (data) {
                        $scope.imageUrls = data['pictures'];
                        $scope.originalImageUrls = data['originalPictures'];
                        $scope.detailImageUrls = data['detailPictures'];
                        $scope.originalDetailImageUrls = data['originalDetailPictures'];
                    });
                }, 1000);
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
            var recordCategoryKey = 'record-category';
            $scope.selectCategory = function (id) {
                $.cookie(recordCategoryKey, id);
                selectItem($scope.category, id, $scope.subCategory, categoryJsonUrl);
            };
            $scope.selectSubCategory = function (id) {
                $.cookie(recordCategoryKey, id);
                selectItem($scope.subCategory, id);
            };
            var initCategoryId = $initCategoryId.val();
            if (initCategoryId != '') {
                initCategoryValue(initCategoryId, categoryJsonUrl);
            } else if ($.cookie(recordCategoryKey)) {
                initCategoryValue($.cookie(recordCategoryKey), categoryJsonUrl);
            }
        }

        function initCategoryValue(initCategoryId, categoryJsonUrl) {
            setTimeout(function () {
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
            }, 100);
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
            var recordBranchKey = 'record-branch';
            $scope.selectBranch = function (id) {
                $.cookie(recordBranchKey, id);
                selectItem($scope.branch, id, $scope.subBranch1, branchJsonUrl);
                $scope.subBranch2.disabled = true;
            };
            $scope.selectSubBranch1 = function (id) {
                $.cookie(recordBranchKey, id);
                selectItem($scope.subBranch1, id, $scope.subBranch2, branchJsonUrl);
            };
            $scope.selectSubBranch2 = function (id) {
                $.cookie(recordBranchKey, id);
                selectItem($scope.subBranch2, id);
            };
            var initBranchId = $initBranchId.val();
            if (initBranchId != '') {
                initBranchValue(initBranchId, branchJsonUrl);
            } else if ($.cookie(recordBranchKey)) {
                initBranchValue($.cookie(recordBranchKey), branchJsonUrl);
            }
        }

        function initBranchValue(initBranchId, branchJsonUrl) {
            setTimeout(function () {
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
            }, 100)
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
    var errorInfo = $('#errorInfo').val();
    if (errorInfo != null && errorInfo != '') {
        alert(errorInfo);
    }
})();
