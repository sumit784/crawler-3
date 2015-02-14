(function () {
    var $elements = {
        closeLink: $('#closeLink'),
        loginDiv: $('div.content div.login')
    };
    $elements.closeLink.click(function () {
        $elements.loginDiv.fadeOut(500);
    });
})();
