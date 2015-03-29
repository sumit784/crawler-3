;
(function () {
    var success = $.url.param('success');
    if (success != null) {
        $('#successSelect').val(success);
    }
    angularUtils.controller(function ($scope) {
    });
})();
