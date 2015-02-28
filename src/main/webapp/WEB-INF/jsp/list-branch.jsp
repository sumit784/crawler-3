<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="branch">
    <div class="poster"></div>
    <div class="title blueFont"><img src="resources/css/images/branch_rank.png"/></div>
    <div class="logos">
        <div class="showBranch" ng-repeat="branch in showBranches" ng-mouseover="showMore()">
            <div class="border">
                <div class="fullBorder"></div>
                <div class="horizontalBorder"></div>
                <div class="verticalBorder"></div>
            </div>
            <div>
                <a href="shoppe?id={{branch.id}}" target="_blank">
                    <img class="link branch-logo" ng-src="{{branch.logo}}"
                         onmouseover="showBranchBorder(this)"
                         onmouseout="hideBranchBorder(this)"/>
                </a>
            </div>
        </div>
        <div class="moreBranch" ng-mouseover="showMore()" ng-show="moreBranch.show">
            <a href="javascript:void(0)" target="_blank">
                <img class="link branch-logo" ng-src="{{moreBranch.logo}}"
                     onmouseover="showBranchBorder(this)"
                     onmouseout="hideBranchBorder(this)"/>
            </a>
        </div>
        <div class="hideBranch" ng-repeat="branch in hideBranches">
            <div class="border">
                <div class="fullBorder"></div>
                <div class="horizontalBorder"></div>
                <div class="verticalBorder"></div>
            </div>
            <div>
                <a href="shoppe?id={{branch.id}}" target="_blank">
                    <img class="link branch-logo" ng-src="{{branch.logo}}"
                         onmouseover="showBranchBorder(this)"
                         onmouseout="hideBranchBorder(this)"/>
                </a>
            </div>
        </div>
    </div>
</div>