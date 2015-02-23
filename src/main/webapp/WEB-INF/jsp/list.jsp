<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="search boxShadow">
    <div class="classification">
        <c:forEach var="classification" items="${classifications}" varStatus="status">
            <c:if test="${status.index % 2 == 0}"><div></c:if>
            <a href="javascript:void(0)" class="noLineAnchor lightGrayFont">${classification}</a>
            <c:if test="${status.index % 2 == 1}"></div></c:if>
        </c:forEach>
    </div>
    <div class="navigationName boxShadow orangeBack">
        <span class="selectedNavigation">{{keyWord}}</span>
    </div>
    <div class="split"></div>
    <div class="searchForm">
        <div class="input-group">
            <div class="input-group-btn searchType">
                <button type="button" class="btn btn-default dropdown-toggle grayBack" data-toggle="dropdown">
                    全部分类<span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <c:forEach var="classification" items="${classifications}">
                        <li><a href="javascript:void(0)">${classification}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <form action="search" target="_blank">
                <input type="text" name="keyWord" id="searchInput"
                       class="form-control" placeholder="请输入您想查询的关键字"/>
                <span class="input-group-addon searchCommit">
                    <img class="link" id="searchCommmit" src="resources/css/images/searchButton.png"/>
                </span>
            </form>
        </div>
        <div class="hotWords">
        <span ng-repeat="hotWord in hotWords">
            <a class="noLineAnchor {{hotWord.color}}" href="javascript:void(0)">{{hotWord.text}}</a>
        </span>
        </div>
    </div>
    <div class="branch">
        <div class="poster"></div>
        <div class="title blueFont"><img src="resources/css/images/branch_rank.png"/></div>
        <div class="logos">
            <table id="showBranches">
                <tr ng-repeat="branchGroup in branches">
                    <td ng-repeat="branch in branchGroup" ng-mouseover="showMore()">
                        <div class="border">
                            <div class="fullBorder"></div>
                            <div class="horizontalBorder"></div>
                            <div class="verticalBorder"></div>
                        </div>
                        <div ng-if="branch.more" class="moreBranch">
                            <a class="noLineAnchor" href="javascript:void(0)">
                                <span>更多品牌</span><img src="resources/css/images/options.png"/>
                            </a>
                        </div>
                        <div>
                            <a href="shoppe?id={{branch.id}}" target="_blank">
                                <img class="link" ng-src="{{branch.src}}"
                                     onmouseover="showBranchBorder(this)"
                                     onmouseout="hideBranchBorder(this)"/>
                            </a>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="hideBranch">
                <table>
                    <tr ng-repeat="branchGroup in branches">
                        <td ng-repeat="branch in branchGroup">
                            <div class="border">
                                <div class="fullBorder"></div>
                                <div class="horizontalBorder"></div>
                                <div class="verticalBorder"></div>
                            </div>
                            <div>
                                <a href="shoppe?id={{branch.id}}" target="_blank">
                                    <img class="link" ng-src="{{branch.src}}"
                                         onmouseover="showBranchBorder(this)"
                                         onmouseout="hideBranchBorder(this)"/>
                                </a>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
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