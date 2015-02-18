if (!Array.isArray) {
    Array.isArray = $.isArray;
}

if (!Object.create) {
    Object.create = (function () {
        function F() {
        }

        return function (o) {
            if (arguments.length != 1) {
                throw new Error('Object.create implementation only accepts one parameter.');
            }
            F.prototype = o;
            return new F()
        };
    })();
}

Object.keys = function (object) {
    var r = [];
    for (var key in object) {
        r.hasOwnProperty.call(object, key) && r.push(key);
    }
    return r;
};
