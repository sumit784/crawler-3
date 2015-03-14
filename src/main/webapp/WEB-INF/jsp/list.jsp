<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<input type="hidden" id="categoryId" value="${categoryId}"/>
<div class="search boxShadow" ng-mouseout="hideMore($event)">
    <div class="navigationName boxShadow orangeBack">
        <span class="selectedNavigation">${categoryName}</span>
    </div>
    <div class="subCategory">
        <c:forEach var="subCategory" items="${subCategories}" varStatus="status">
            <c:if test="${status.index % 2 == 0}"><div></c:if>
            <a href="javascript:void(0)" class="noLineAnchor lightGrayFont"
               data-options="id:${subCategory.id}"
               ng-click="selectSubCategory($event)">${subCategory.name}</a>
            <c:if test="${status.index % 2 == 1}"></div></c:if>
        </c:forEach>
    </div>
    <div class="split"></div>
    <div class="right">
        <div class="searchForm">
            <div class="input-group">
                <div class="input-group-btn searchType">
                    <button type="button" class="btn btn-default dropdown-toggle grayBack"
                            data-toggle="dropdown">
                        <span class="text">全部分类</span><span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li data-options="id:0">
                            <a href="javascript:void(0)">全部分类</a>
                        </li>
                        <li data-options="id:${categoryId}">
                            <a href="javascript:void(0)">${categoryName}</a>
                        </li>
                        <c:forEach var="subCategory" items="${subCategories}">
                            <li data-options="id:${subCategory.id}">
                                <a href="javascript:void(0)">${subCategory.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <form action="search" target="_blank">
                    <input type="hidden" id="searchCategoryId" name="categoryId"/>
                    <input type="text" name="keyWord" id="searchInput"
                           class="form-control" placeholder="请输入您想查询的关键字"/>
                    <span class="input-group-addon searchCommit">
                        <img class="link" id="searchCommmit" src="resources/css/images/searchButton.png"/>
                    </span>
                </form>
            </div>
            <div class="hotWords">
                <span ng-repeat="hotWord in hotWords">
                    <a class="noLineAnchor {{hotWord.color}}" href="javascript:void(0)"
                       ng-href="search?keyWord={{hotWord.content}}" target="_blank">
                        {{hotWord.content}}
                    </a>
                </span>
            </div>
        </div>
        <%@include file="list-branch.jsp" %>
    </div>
</div>
<div class="goods">
    <div class="sort">
        <div class="title blueFont">
            今日<span class="selectedNavigation">女人</span>最低价
        </div>
        <%@include file="list-sort-links.jsp" %>
    </div>
    <%@include file="list-shapshots.jsp" %>
</div>
<%@include file="list-right-panel.jsp" %>
<%@include file="footer.jsp" %>