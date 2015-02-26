<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<input type="hidden" id="initBranchId" value="${commodity.branchId}"/>

<div id="branchSelect" class="input-group-btn">
    <input type="hidden" name="branchId" value="{{branch.selected.id}}">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        {{branch.selected.name}}
    </button>
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in branch.items">
            <a href="javascript:void(0)" ng-click="selectBranch(item.id)">{{item.name}}</a>
        </li>
    </ul>
</div>
<div id="firstLevelBranchSelect" class="input-group-btn">
    <input type="hidden" name="subBranch1Id" value="{{subBranch1.selected.id}}">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
            ng-disabled="subBranch1.disabled">
        {{subBranch1.selected.name}}
    </button>
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in subBranch1.items">
            <a href="javascript:void(0)" ng-click="selectSubBranch1(item.id)">{{item.name}}</a>
        </li>
    </ul>
</div>
<div id="secondLevelBranchSelect" class="input-group-btn">
    <input type="hidden" name="subBranch2Id" value="{{subBranch2.selected.id}}">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
            ng-disabled="subBranch2.disabled">
        {{subBranch2.selected.name}}
    </button>
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in subBranch2.items">
            <a href="javascript:void(0)" ng-click="selectSubBranch2(item.id)">{{item.name}}</a>
        </li>
    </ul>
</div>