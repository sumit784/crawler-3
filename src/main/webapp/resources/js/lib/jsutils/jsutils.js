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
    newDate: function (arg) {
        if (this.isNumber(arg)) {
            return new Date(arg);
        }

        var dateArr = arg.split('-');
        var year = parseInt(dateArr[0]);
        var month = parseInt(dateArr[1] - 1);
        var day = parseInt(dateArr[2]);
        return new Date(year, month, day);
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
};
