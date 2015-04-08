<%@ page import="com.qinyuan15.crawler.ui.RequestUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="footer">
    <div class="darkFont">
        @ 2005-2014 douban.com, all rights reserved<br>
        京ICP证090015号 京ICP备11027288号<br>
        <img src="resources/css/images/filing.gif"/>京公安备11010500728<br>
        违法和不良信息举报电话：4006910007
    </div>
    <div class="links">
        <a href="company-info.html?tab=1" target="_blank" class="blueFont">关于我们</a>
        <a href="company-info.html?tab=2" target="_blank" class="blueFont">联系我们</a>
        <a href="company-info.html?tab=3" target="_blank" class="blueFont">免责声明</a>
        <a href="company-info.html?tab=4" target="_blank" class="blueFont">友情链接</a>
        <a href="branch.html" target="_blank" class="blueFont">品牌大全</a>
    </div>
</div>
</div>
</body>
<script src="resources/js/lib/jquery-1.11.1.min.js"></script>
<script src="resources/js/lib/jquery.url.js"></script>
<script src="resources/js/lib/jquery.cookie.js"></script>
<script src="resources/js/lib/jquery-form-3.51.0.js"></script>
<script src="resources/js/lib/bootstrap/js/bootstrap.min.js"></script>
<!--[if IE]>
<script src="resources/js/lib/angular/html5shiv.js"></script>
<script src="resources/js/lib/angular/json2.js"></script>
<script src="resources/js/lib/ie-patch.js"></script>
<![endif]-->
<script src="resources/js/lib/angular/angular.min.js"></script>
<script src="resources/js/lib/underscore-min.js"></script>
<script src="<%=RequestUtils.getJs("lib/jsutils")%>"></script>
<script src="<%=RequestUtils.getJs("common")%>"></script>
<c:forEach var="js" items="${moreJs}">
    <script src="resources/js/${js}.js?t=<%=AppConfig.VERSION%>"></script>
</c:forEach>
<script src="<%=RequestUtils.getRelativeJs(request)%>"></script>
<!--[if IE]>
<c:forEach var="js" items="${ieJs}">
    <script src="resources/js/${js}.js?t=<%=AppConfig.VERSION%>"></script>
</c:forEach>
<![endif]-->
</html>