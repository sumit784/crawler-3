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

var transparentBackground = {
    _getDiv: function () {
        if ($('#transparentBackground').size() == 0) {
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
        return $('#transparentBackground');
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
    unSort: 'css/images/unSort.gif',
    arrowUp: 'css/images/arrow_up.gif',
    arrowDown: 'css/images/arrow_down.gif',
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

(function () {
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
})();
