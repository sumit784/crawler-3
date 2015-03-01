<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <div class="user">XXX账号名</div>
    <div class="split"></div>
    <div class="commodity">发布的商品<span id="commodityCount">(${fn:length(commodities)})</span></div>
    <div class="split">
        <div class="boldSplit"></div>
    </div>
    <div class="log">操作日志</div>
    <div class="split"></div>
    <div class="active">有效 <span class="active">&nbsp;&nbsp;</span></div>
    <div class="inactive">无效 <span class="inactive">&nbsp;&nbsp;</span></div>
    <div class="split"></div>
    <div class="branch"><a target="_blank" href="admin-branch">品牌管理&gt;&gt;</a></div>
    <div class="category"><a target="_blank" href="admin-category">分类管理&gt;&gt;</a></div>
    <div class="split"></div>
</div>
<div class="right">
    <c:forEach var="commodity" items="${commodities}">
        <div class="boxShadow ${commodity.active ? 'active' : 'inactive'}">
            <div class="image">
                <a href="admin-edit-commodity?id=${commodity.id}">
                    <img class="link" src="${commodity.picture}"/>
                </a>
            </div>
            <div class="description">
                <a href="admin-edit-commodity?id=${commodity.id}"
                   class="noLineAnchor limit-size" data-options="limit:28">${commodity.name}</a>
            </div>
        </div>
    </c:forEach>
    <div>
        <div class="image">
            <a href="admin-edit-commodity">
                <img class="link" src="resources/css/images/manage-commodity/add_c.png"/>
            </a>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
