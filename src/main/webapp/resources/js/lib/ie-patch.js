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


/*
$(function () {
    //判断浏览器是否支持placeholder属性
    supportPlaceholder = 'placeholder'in document.createElement('input'),
        placeholder = function (input) {

            var text = input.attr('placeholder'),
                defaultValue = input.defaultValue;

            if (!defaultValue) {

                input.val(text).addClass("phcolor");
            }

            input.focus(function () {

                if (input.val() == text) {

                    $(this).val("");
                }
            });


            input.blur(function () {

                if (input.val() == "") {

                    $(this).val(text).addClass("phcolor");
                }
            });

            //输入的字符不为灰色
            input.keydown(function () {

                $(this).removeClass("phcolor");
            });
        };

    //当浏览器不支持placeholder属性时，调用placeholder函数
    if (!supportPlaceholder) {

        $('input').each(function () {

            text = $(this).attr("placeholder");

            if ($(this).attr("type") == "text") {

                placeholder($(this));
            }
        });
    }

});*/
