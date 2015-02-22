function isFirefox() {
    return navigator.userAgent.indexOf('Firefox') >= 0;
}

function scrollTop($targetElement) {
    var offset = $targetElement ? $targetElement.offset().top : 0;
    if (isFirefox()) {
        document.documentElement.scrollTop = offset;
    } else {
        $('body').animate({scrollTop: offset}, 250);
    }
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

function splitArray(array, groupSize) {
    var result = [], group;
    for (var i = 0, len = array.length; i < len; i++) {
        if (i % groupSize == 0) {
            group = [];
            result.push(group);
        }
        group.push(array[i]);
    }
    return result;
}

var transparentBackground = {
    _getDiv: function () {
        var $transparentBackground = $('#transparentBackground');
        if ($transparentBackground.size() == 0) {
            $('body').append("<div id='transparentBackground'></div>");
            $('#transparentBackground').css({
                'position': 'fixed',
                'width': '100%',
                'height': '100%',
                'top': 0,
                'left': 0,
                'display': 'none',
                'background-color': '#000000'
            }).addClass('deepTransparent');
        }
        return $transparentBackground;
    },
    show: function () {
        this._getDiv().show();
    },
    hide: function () {
        this._getDiv().hide();
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
            this._module.controller('ContentController', ['$scope', arguments[0]]);
        } else if (argSize >= 2) {
            this._module.controller(arguments[0], ['$scope', arguments[1]]);
        }
    }
};

function getKeyWord() {
    return $.url.param('keyWord');
}

/*
 * query plugins
 */
jQuery.fn.dataOptions = function () {
    var dataOptionsString = this.attr('data-options');
    var dataOptions = null;
    eval('dataOptions = {' + dataOptionsString + "}");
    return dataOptions;
};

jQuery.fn.focusOrSelect = function () {
    var value = this.val();
    if (value != null && value != '') {
        this.select();
    } else {
        this.focus();
    }
};

(function () {
    angularUtils.controller('NavigationController', function ($scope) {
        var categories = getCategories();
        $scope.selectedCategory = getKeyWord();
        $scope.categories = [];
        for (var i = 0, len = categories.length; i < len; i++) {
            var category = categories[i];
            $scope.categories.push({
                'text': category,
                'class': category == $scope.selectedCategory ? 'selected' : 'darkFont'
            });
        }
        function getCategories() {
            return ['女人' , '男人', '小孩', '数码家电', '居家'];
        }
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

    $('table.normal .limit-size').each(function () {
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
})();
