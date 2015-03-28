<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="images">
    <div ng-repeat="snapshot in snapshots" class="boxShadow">
        <div class="image">
            <a href="detail.html?id={{snapshot.id}}" target="_blank">
                <div style="background-image:url({{snapshot.picture}})"></div>
            </a>
        </div>
        <div class="description">
            <a href="detail?id={{snapshot.id}}" class="noLineAnchor" target="_blank">
                {{snapshot.name}}
            </a>
        </div>
        <div class="price">
            ￥{{snapshot.price}}
        </div>
        <div class="branch">
            <a href="shoppe?id={{snapshot.branch.id}}" target="_blank">
                <img class="link branch-logo" ng-src="{{snapshot.branch.logo}}"/>
            </a>
        </div>
    </div>
    <div ng-show="(snapshots.length==0)" style="display: none;" class="no-found boxShadow">
        <img src="resources/css/images/list/no-found.png"/>

        <p>对不起，没有处于最低的商品</p>

        <p>您可以再看看其他类别的商品</p>
    </div>
</div>
<div class="loading">
    <span>加载中，请稍候...</span>
</div>
<div class="loadMore">
    <a href="javascript:void(0)" class="noLineAnchor" ng-click="loadMore()">加载更多</a>
</div>