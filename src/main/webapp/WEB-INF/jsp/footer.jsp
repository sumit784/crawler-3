<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="footer">
    <div class="darkFont">
        @ 2005-2014 douban.com, all rights reserved<br>
        京ICP证090015号 京ICP备11027288号<br>
        <img src="resources/css/images/filing.gif"/>京公安备11010500728<br>
        违法和不良信息举报电话：4006910007
    </div>
    <div class="links">
        <a href="company-info?tab=1" target="_blank" class="blueFont">关于我们</a>
        <a href="company-info?tab=2" target="_blank" class="blueFont">联系我们</a>
        <a href="company-info?tab=3" target="_blank" class="blueFont">免责声明</a>
        <a href="company-info?tab=4" target="_blank" class="blueFont">友情链接</a>
        <a href="branch" target="_blank" class="blueFont">品牌大全</a>
    </div>
</div>
</div>
</body>
<script src="resources/lib/angularjs/angular.min.js"></script>
<script src="resources/lib/jquery-1.11.1.min.js"></script>
<script src="resources/lib/jquery.url.js"></script>
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/lib/handlebars.min-v1.3.0.js"></script>
<script src="resources/js/common.js"></script>
<c:forEach var="js" items="${moreJs}">
    <script src="resources/js/${js}.js"></script>
</c:forEach>
<script src="resources/js/<%=request.getRequestURI().replaceAll("^.*/","").replace(".jsp",".js")%>"></script>
</html>