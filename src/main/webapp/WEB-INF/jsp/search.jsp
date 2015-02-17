<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="searchInfo boxShadow">${keyWord} 相关搜索商品</div>
<div class="goods">
    <div class="sort">
        <div class="title">
            <div class="lowest"><a class="noLineAnchor" href="javascript:void(0)">历史最低</a></div>
            <div class="all"><a class="noLineAnchor" href="javascript:void(0)">所有宝贝</a></div>
        </div>
        <div class="links">
            <a href="javascript:void(0)" class="noLineAnchor">
                上架时间 <img src="resources/css/images/arrow_up.gif"/>
            </a>
            <a href="javascript:void(0)" class="noLineAnchor">
                价格 <img src="resources/css/images/unSort.gif"/>
            </a>
            <a href="javascript:void(0)" class="noLineAnchor">
                销量 <img src="resources/css/images/unSort.gif"/>
            </a>
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
