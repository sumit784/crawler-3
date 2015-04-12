<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="taglib.jsp" %>
<div class="images">
    <div class="addIcon">
        <div class="image">
            <a target="_blank" href="admin-edit-commodity">
                <img class="link" src="resources/css/images/manage-commodity/add_c.png"/>
            </a>
        </div>
    </div>
    <c:forEach var="commodity" items="${commodities}">
        <div class="boxShadow ${commodity.active ? 'active' : 'inactive'}">
            <div class="image">
                <a target="_blank" href="admin-edit-commodity.html?id=${commodity.id}">
                    <img class="link" src="${commodity.picture}"/>
                </a>
            </div>
            <div class="description">
                <a target="_blank" href="admin-edit-commodity.html?id=${commodity.id}"
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
</div>
<div class="pagination">
    <%@include file="pagination.jsp" %>
</div>
