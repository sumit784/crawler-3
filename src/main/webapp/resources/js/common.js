function scrollTop($targetElement) {
    var offset = $targetElement ? $targetElement.offset().top : 0;
    if (JSUtils.isFirefox()) {
        document.documentElement.scrollTop = offset;
    } else {
        $('body').animate({scrollTop: offset}, 250);
    }
}

function getTotalHeight() {
    return window.screen.availHeight;
}

function getTotalWidth() {
    return document.body.clientWidth;
}

function getParent($element, parentTagName) {
    if (!parentTagName) {
        parentTagName = 'div';
    }
    var parent = $element.parent();
    while (true) {
        if (parent.size() == 0 || parent.is('body') || parent.is('html')) {
            return null;
        }
        if (parent.is(parentTagName)) {
            return parent;
        }
        parent = parent.parent();
    }
}

function initLimitSizeElements() {
    $('.limit-size').each(function () {
        var limitSize = 20; // default limit size

        var $this = $(this);
        var dataOptions = $this.dataOptions();
        if (dataOptions && dataOptions['limit']) {
            limitSize = dataOptions['limit'];
        }

        var text = $this.text();
        $this.attr('title', text);
        if (text.length > limitSize) {
            $this.text(text.substr(0, limitSize) + '...');
        }
    });
}

var transparentBackground = {
    _getDiv: function (zIndex) {
        var $transparentBackground = $('#transparentBackground');
        if ($transparentBackground.size() == 0) {
            var style = {
                'position': 'fixed',
                'width': '100%',
                'height': '100%',
                'top': 0,
                'left': 0,
                'display': 'none',
                'background-color': '#000000'
            };
            if (zIndex) {
                style['z-index'] = zIndex;
            }
            return $('<div></div>').attr('id', 'transparentBackground').css(style)
                .addClass('deepTransparent').appendTo('body');
        } else {
            return $transparentBackground;
        }
    },
    show: function (zIndex) {
        this._getDiv(zIndex).show();
    },
    hide: function (zIndex) {
        this._getDiv(zIndex).hide();
    }
};

function setHoverColor($element, color) {
    $element.hover(function () {
        this['data-options'] = {};
        var $this = $(this);
        this['data-options']['original-color'] = $this.css('color');
        $this.css('color', color);
    }, function () {
        $(this).css('color', this['data-options']['original-color']);
    });
}

function buildSubmitCallback(successCallback, failCallback) {
    return function (data) {
        if (data.success) {
            successCallback();
        } else {
            if (failCallback) {
                failCallback();
            } else {
                alert(data.detail);
            }
        }
    };
}

function normalSubmitCallback(data) {
    if (data.success) {
        location.reload();
    } else {
        alert(data.detail);
    }
}

var images = {
    unSort: 'resources/css/images/unSort.gif',
    arrowUp: 'resources/css/images/arrow_up.gif',
    arrowDown: 'resources/css/images/arrow_down.gif',
    getHeight: function ($img) {
        var img = new Image();
        img.src = $img.attr('src');
        return img.height;
    },
    getWidth: function ($img) {
        var img = new Image();
        img.src = $img.attr('src');
        return img.width;
    }
};

var angularUtils = {
    _module: null,
    /**
     * Usage:
     * controller(controllerName, func)
     * or
     * controller(func)
     */
    controller: function () {
        if (!this._module) {
            this._module = angular.module('main', []);
        }
        var argSize = arguments.length;
        if (argSize == 1) {
            this._module.controller('ContentController', ['$scope', '$http', arguments[0]]);
        } else if (argSize >= 2) {
            this._module.controller(arguments[0], ['$scope', '$http', arguments[1]]);
        }
        return this;
    },
    ajaxGet: function (url, callback) {
        this._module.run(function ($http) {
            $http.get(url).success(function (data) {
                callback(data);
            });
        });
        return this;
    }
};

(function () {
    angularUtils.controller('NavigationController', function ($scope, $http) {
        $http.get("json/category.json").success(function (data) {
            var selectedCategoryIndex;
            var href = location.href.toString();
            href = href.replace('.html', '');
            if (href.indexOf('/list?') > 0 || href.substr(href.length - 5) == '/list') {
                var categoryId = $.url.param('id');
                if (!categoryId) {
                    categoryId = $('#categoryId').val();
                }
                selectedCategoryIndex = parseInt(categoryId);
            } else {
                selectedCategoryIndex = -1;
            }
            $scope.categories = [];
            $.each(data, function () {
                $scope.categories.push({
                    id: this.id,
                    text: this.name,
                    class: this.id == selectedCategoryIndex ? 'selected' : 'darkFont'
                });
            });
        });
    });

    var toTopLink = {
        enable: function () {
            $elements.toTopLink.removeClass('lightTransparent');
        },
        disable: function () {
            $elements.toTopLink.addClass('lightTransparent');
        },
        update: function () {
            if ($(document).scrollTop() == 0) {
                toTopLink.disable();
            } else {
                toTopLink.enable();
            }
        }
    };

    function adjustRightFloatPosition() {
        var top = 0.80 - $elements.rightFloat.height() / window.screen.availHeight;
        $elements.rightFloat.css('top', top * 100 + "%");
    }

    var $elements = {
        toTopLink: $('.toTop'),
        rightFloat: $('div.rightFloat')
    };

    var colors = {
        darkOrange: '#ED3800'
    };

    $elements.toTopLink.click(function () {
        scrollTop();
    });

    $(document).scroll(function () {
        toTopLink.update();
    });
    toTopLink.update();
    adjustRightFloatPosition();
    setTimeout(adjustRightFloatPosition, 500);

    setTimeout(function () {
        $('.orangeButton').hover(function () {
            colors.orange = $(this).css('background-color');
            $(this).css('background-color', colors.darkOrange);
        }, function () {
            if (colors.orange) {
                $(this).css('background-color', colors.orange);
            }
        });
    }, 500);

    initLimitSizeElements();
})();
