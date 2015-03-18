<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<input type="hidden" id="categoryId" value="${categoryId}"/>

<div class="search boxShadow" ng-mouseout="hideMore($event)">
    <div class="navigationName boxShadow orangeBack">
        <span class="selectedNavigation">${categoryName}</span>
    </div>
    <div class="subCategory">
        <c:forEach var="subCategory" items="${subCategories}" varStatus="status">
            <div>
                <a href="javascript:void(0)" class="noLineAnchor lightGrayFont"
                   data-options="id:${subCategory.id}"
                   ng-click="selectSubCategory($event)">${subCategory.name}</a>
            </div>
        </c:forEach>
    </div>
    <div class="split"></div>
    <div class="right">
        <%@include file="commodity-search-form.jsp" %>
        <%@include file="list-branch.jsp" %>
    </div>
</div>
<div class="goods">
    <div class="sort">
        <div class="title blueFont">
            今日<span class="selectedNavigation">${categoryName}</span>最低价
        </div>
        <%@include file="list-sort-links.jsp" %>
    </div>
    <%@include file="list-shapshots.jsp" %>
</div>
<%@include file="list-right-panel.jsp" %>
<%@include file="footer.jsp" %>