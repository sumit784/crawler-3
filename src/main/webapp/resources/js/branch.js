(function () {
    function scrollToBranchGroup(letter) {
        $('div.branchGroup').each(function () {
            if ($.trim($(this).find('div.title').text()) == $.trim(letter)) {
                scrollTop($(this));
                return false;
            } else {
                return true;
            }
        });
    }

    var $elements = {
        letterLinks: $('div.rightFloat a.branchGroupLink'),
        branchGroups: $('#branchGroups')
    };
    $elements.letterLinks.click(function () {
        scrollToBranchGroup($(this).text());
    });
    var source = $('#branchGroupTpl').html();
    var template = Handlebars.compile(source);
    var letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (var i = 0; i < letters.length; i++) {
        $elements.branchGroups.append(template({title: letters[i]}));
    }
    $elements.branchGroups.append(template({title: '0-9'}));
})();
