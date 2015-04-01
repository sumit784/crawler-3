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
        <div class="user"><a href="admin-log.html" target="_blank">操作日志</a></div>
        <div class="crawl"><a href="admin-crawl-log.html" target="_blank">爬虫日志</a></div>
    </div>
    <div class="split"></div>
    <div class="activeGroup">
        <div class="active">有效 <span class="active">&nbsp;&nbsp;</span></div>
        <div class="inactive">无效 <span class="inactive">&nbsp;&nbsp;</span></div>
    </div>
    <div class="split"></div>
    <div class="linkGroup">
        <div class="branch"><a target="_blank" href="admin-branch.html">品牌管理&gt;&gt;</a></div>
        <div class="category"><a target="_blank" href="admin-category.html">分类管理&gt;&gt;</a></div>
        <security:authorize ifAnyGranted="ROLE_SUPPER_ADMIN">
            <div class="index-logo"><a target="_blank" href="admin-index-logo.html">主页管理&gt;&gt;</a></div>
            <div class="detail"><a target="_blank" href="admin-detail.html">详情页管理&gt;&gt;</a></div>
            <div class="user"><a target="_blank" href="admin-user.html">用户管理&gt;&gt;</a></div>
            <div class="config"><a target="_blank" href="admin-config.html">系统配置&gt;&gt;</a></div>
        </security:authorize>
    </div>
    <div class="split"></div>
</div>
<div class="right">
    <c:forEach var="commodity" items="${commodities}">
        <div class="boxShadow ${commodity.active ? 'active' : 'inactive'}">
            <div class="image">
                <a target="_blank" href="admin-edit-commodity.html?id=${commodity.id}">
                    <img class="link" src="${commodity.picture}"/>
                </a>
            </div>
            <div class="description">
                <a htarget="_blank" ref="admin-edit-commodity.html?id=${commodity.id}"
                   class="noLineAnchor limit-size" data-options="limit:25">${commodity.name}</a>
            </div>
            <div class="action">
                <a class="noLineAnchor" target="_blank" href="detail.html?id=${commodity.id}">
                    <img class="link" title="预览" src="resources/css/images/admin/overview.gif"/>
                </a>
                <c:choose>
                    <c:when test="${commodity.active}">
                        <a class="noLineAnchor" href="admin-commodity-deactivate?id=${commodity.id}">
                            <img class="link" title="关闭" src="resources/css/images/admin/close.gif"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="noLineAnchor" href="admin-commodity-activate?id=${commodity.id}">
                            <img class="link" title="激活" src="resources/css/images/admin/activate.gif"/>
                        </a>
                    </c:otherwise>
                </c:choose>
                <a class="noLineAnchor" href="admin-commodity-delete?id=${commodity.id}">
                    <img class="link" title="删除" src="resources/css/images/admin/delete.gif"/>
                </a>
            </div>
        </div>
    </c:forEach>
    <div>
        <div class="image">
            <a target="_blank" href="admin-edit-commodity">
                <img class="link" src="resources/css/images/manage-commodity/add_c.png"/>
            </a>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
