/**
 * This file create some useful function for javascript
 */
var JSUtils = {
        /**
         * notice that we can use new Date("2012-01-01") in IE8,
         * so we use this function to ensure compatibility to IE8
         * @param arg date string or timestamp
         * @returns {Date}
         */
        newDate: function
            (arg) {
            if (this.isNumber(arg)) {
                return new Date(arg);
            }

            var dateArr = arg.split('-');
            var year = parseInt(dateArr[0]);
            var month = parseInt(dateArr[1] - 1);
            var day = parseInt(dateArr[2]);
            return new Date(year, month, day);
        },
        splitArray: function (array, groupSize) {
            var result = [], group;
            for (var i = 0, len = array.length; i < len; i++) {
                if (i % groupSize == 0) {
                    group = [];
                    result.push(group);
                }
                group.push(array[i]);
            }
            return result;
        },
        copyArray: function (array) {
            var arr = [];
            for (var i = 0, len = array.length; i < len; i++) {
                arr.push(array[i]);
            }
            return arr;
        },
        removeArrayItem: function (array, index) {
            array.splice(index, 1);
        },
        isString: function (arg) {
            return (typeof arg) == 'string';
        },
        isNumber: function (arg) {
            return (typeof arg) == 'number';
        },
        getUserAgent: function () {
            return navigator['userAgent'];
        },
        isFirefox: function () {
            return this.getUserAgent().indexOf('Firefox') > -1;
        },
        isChrome: function () {
            return this.getUserAgent().indexOf('Chrome') > -1;
        },
        getCurrentTime: function () {
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        },
        recordScrollStatus: function () {
            var $document = $(document);
            var key = 'scroll-status-record_' + location.pathname;
            var value = $.cookie(key);
            if (value) {
                $document.scrollTop(value);
            }
            $document.scroll(function () {
                $.cookie(key, $document.scrollTop());
            });
        },
        /**
         * In firefox, offsetX and offsetY is undefined, so we use this function to
         * ensure compatibility to firefox
         * @param e event object
         * @returns {{offsetX: number, offsetY: number}}
         */
        getOffsetByEvent: function (e) {
            if (e.offsetX !== undefined && e.offsetY !== undefined) {
                return {
                    offsetX: e.offsetX,
                    offsetY: e.offsetY
                };
            }

            function getPageCoord(element) {
                var coord = {x: 0, y: 0};
                while (element) {
                    coord.x += element.offsetLeft;
                    coord.y += element.offsetTop;
                    element = element.offsetParent;
                }
                return coord;
            }

            var target = e.target;
            if (target.offsetLeft == undefined) {
                target = target.parentNode;
            }
            var pageCoord = getPageCoord(target);
            var eventCoord =
            {
                x: window.pageXOffset + e.clientX,
                y: window.pageYOffset + e.clientY
            };
            return {
                offsetX: eventCoord.x - pageCoord.x,
                offsetY: eventCoord.y - pageCoord.y
            };
        }
    }
    ;


/**
 * query plugins
 */
jQuery.fn.dataOptions = function () {
    var dataOptionsString = $.trim(this.attr('data-options'));
    if (dataOptionsString.match(/:$/g)) {
        dataOptionsString += 'null';
    }
    if (dataOptionsString) {
        var dataOptions = null;
        eval('dataOptions = {' + dataOptionsString + "}");
        return dataOptions;
    } else {
        return null;
    }
};

jQuery.fn.parseIntegerInId = function () {
    var id = this.attr('id');
    if (id) {
        return parseInt(id.replace(/\D/g, ''));
    } else {
        return null;
    }
};

jQuery.fn.focusOrSelect = function () {
    var value = this.val();
    if (value != null && value != '') {
        this.select();
    } else {
        this.focus();
    }
};
