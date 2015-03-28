<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <div class="user">
        <security:authentication property="name"/>
        <a href="/j_spring_security_logout">[退出]</a>
    </div>
    <div class="split"></div>
    <div class="commodity">发布的商品<span id="commodityCount">(${fn:length(commodities)})</span></div>
    <div class="split">
        <div class="boldSplit"></div>
    </div>
    <div class="log">
        <a href="admin-log" target="_blank">操作日志</a>
    </div>
    <div class="split"></div>
    <div class="active">有效 <span class="active">&nbsp;&nbsp;</span></div>
    <div class="inactive">无效 <span class="inactive">&nbsp;&nbsp;</span></div>
    <div class="split"></div>
    <div class="branch"><a target="_blank" href="admin-branch.html">品牌管理&gt;&gt;</a></div>
    <div class="category"><a target="_blank" href="admin-category.html">分类管理&gt;&gt;</a></div>
    <div class="index-logo"><a target="_blank" href="admin-index-logo.html">主页管理&gt;&gt;</a></div>
    <div class="detail"><a target="_blank" href="admin-detail.html">详情页管理&gt;&gt;</a></div>
    <div class="user"><a target="_blank" href="admin-detail.html">用户管理&gt;&gt;</a></div>
    <div class="split"></div>
</div>
<div class="right">
    <c:forEach var="commodity" items="${commodities}">
        <div class="boxShadow ${commodity.active ? 'active' : 'inactive'}">
            <div class="image">
                <a href="admin-edit-commodity.html?id=${commodity.id}">
                    <img class="link" src="${commodity.picture}"/>
                </a>
            </div>
            <div class="description">
                <a href="admin-edit-commodity.html?id=${commodity.id}"
                   class="noLineAnchor limit-size" data-options="limit:25">${commodity.name}</a>
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
