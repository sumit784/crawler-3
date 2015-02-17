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
        <span class="selectedNavigation">女人</span>
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
            <form action="search-no-result.jsp" target="_blank">
                <input type="text" id="searchInput" class="form-control" placeholder="请输入您想查询的关键字"/>
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
                            <a href="shoppe.html?id={{branch.id}}" target="_blank">
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
                                <a href="shoppe.html?id={{branch.id}}" target="_blank">
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
        <div class="links">
            <a href="javascript:void(0)" class="noLineAnchor">上架时间 <img src="resources/css/images/arrow_up.gif"/></a>
            <a href="javascript:void(0)" class="noLineAnchor">价格 <img src="resources/css/images/unSort.gif"/></a>
            <a href="javascript:void(0)" class="noLineAnchor">销量 <img src="resources/css/images/unSort.gif"/></a>
        </div>
    </div>
    <div class="images">
        <table>
            <tr ng-repeat="snapshotGroup in snapshots">
                <td ng-repeat="snapshot in snapshotGroup">
                    <div class="boxShadow">
                        <div class="image">
                            <a href="detail.html" target="_blank">
                                <img class="link" ng-src="{{snapshot.src}}"/>
                            </a>
                        </div>
                        <div class="description">
                            <a href="detail.html" class="noLineAnchor" target="_blank">
                                {{snapshot.description}}
                            </a>
                        </div>
                        <div class="price">
                            ￥{{snapshot.price}}
                        </div>
                        <div class="branch">
                            <a href="shoppe.html" target="_blank">
                                <img class="link" ng-src="{{snapshot.branchSrc}}"/>
                            </a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="loading">
        <span>加载中，请稍候...</span>
    </div>
</div>
<div class="rightFloat">
    <div>
        <img class="link" id="collectButton" src="resources/css/images/collect.gif"/>
    </div>
    <div class="split"></div>
    <div>
        <img class="link" id="refreshButton" src="resources/css/images/refresh.gif"/>
    </div>
    <div class="split"></div>
    <div>
        <img class="link toTop" src="resources/css/images/toTop.gif"/>
    </div>
</div>
<%@include file="footer.jsp" %>
