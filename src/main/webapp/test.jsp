<%--
  Created by IntelliJ IDEA.
  User: qinyuan
  Date: 15-1-22
  Time: 上午8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>HelloWorld</h1>
</body>
<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
<script>
    $.post('deleteCommodities.json', {
        'ids': [1, 2, 3]
    }, function (data) {
        console.log(data);
    });
    console.log("HelloWorld");
</script>
</html>
