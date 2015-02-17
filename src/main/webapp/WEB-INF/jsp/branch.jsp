<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div id="branchGroups">
    <div class="branchGroup" ng-repeat="branchGroup in branches">
        <div class="title"><span>{{branchGroup.letter}}</span></div>
        <table>
            <tr ng-repeat="branchRow in branchGroup.branches">
                <td ng-repeat="branch in branchRow">
                    <a class="noLineAnchor" ng-href="{{branch.href}}">
                        <img ng-src="{{branch.src}}"/><br>{{branch.text}}
                    </a>
                </td>
            </tr>
        </table>
    </div>
</div>
<!--
<div class="loading">
<span>加载中，请稍候...</span>
</div>
-->
<div class="rightFloat">
    <table>
        <tr ng-repeat="letterGroup in letters">
            <td ng-repeat="letter in letterGroup">
                <a class="darkFont branchGroupLink sameWidthFont"
                   href="javascript:void(0)" ng-click="letterClick(letter)">{{letter}}</a>
            </td>
        </tr>
    </table>
</div>
<%@include file="footer.jsp" %>
