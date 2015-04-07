<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<input type="hidden" id="initBranchId" value="${commodity.branchId}"/>

<div id="branchSelect" class="input-group-btn">
    <input type="hidden" name="branchId" value="{{branch.selected.id}}">
    <button type="button" class="btn btn-default" ng-click="showBranchesToSelect($event)">
        {{branch.selected.name}}
    </button>
    <div class="branchDropdown" style="left: 0;">
        <div class="branchGroupLetter">
            <div class="{{getBranchGroupLetterStyle($index)}}" ng-repeat="branchGroup in branch.items"
                 ng-mouseover="showBranchGroup($event, $index)">
                {{branchGroup.letter}}
            </div>
        </div>
        <div class="branchGroup" style="{{getBranchGroupStyle($index)}}" ng-repeat="branchGroup in branch.items">
            <div class="branch" ng-repeat="branch in branchGroup.branches"
                 ng-click="selectBranch(branch.id)">
                {{branch.name}}
            </div>
        </div>
    </div>
    <!--
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in branch.items">
            <a href="javascript:void(0)" ng-click="selectBranch(item.id)">{{item.name}}</a>
        </li>
    </ul>
    -->
</div>
<div id="firstLevelBranchSelect" class="input-group-btn">
    <input type="hidden" name="subBranch1Id" value="{{subBranch1.selected.id}}">
    <button type="button" class="btn btn-default" ng-click="showBranchesToSelect($event)"
            ng-disabled="subBranch1.disabled">
        {{subBranch1.selected.name}}
    </button>
    <div class="branchDropdown" style="left: 0;">
        <div class="branchGroupLetter">
            <div class="{{getBranchGroupLetterStyle($index)}}" ng-repeat="branchGroup in subBranch1.items"
                 ng-mouseover="showBranchGroup($event, $index)">
                {{branchGroup.letter}}
            </div>
        </div>
        <div class="branchGroup" style="{{getBranchGroupStyle($index)}}" ng-repeat="branchGroup in subBranch1.items">
            <div class="branch" ng-repeat="branch in branchGroup.branches"
                 ng-click="selectSubBranch1(branch.id)">
                {{branch.name}}
            </div>
        </div>
    </div>
    <!--
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in subBranch1.items">
            <a href="javascript:void(0)" ng-click="selectSubBranch1(item.id)">{{item.name}}</a>
        </li>
    </ul>
    -->
</div>
<div id="secondLevelBranchSelect" class="input-group-btn">
    <input type="hidden" name="subBranch2Id" value="{{subBranch2.selected.id}}">
    <button type="button" class="btn btn-default" ng-click="showBranchesToSelect($event)"
            ng-disabled="subBranch2.disabled">
        {{subBranch2.selected.name}}
    </button>
    <div class="branchDropdown" style="left: 0;">
        <div class="branchGroupLetter">
            <div class="{{getBranchGroupLetterStyle($index)}}" ng-repeat="branchGroup in subBranch2.items"
                 ng-mouseover="showBranchGroup($event, $index)">
                {{branchGroup.letter}}
            </div>
        </div>
        <div class="branchGroup" style="{{getBranchGroupStyle($index)}}" ng-repeat="branchGroup in subBranch2.items">
            <div class="branch" ng-repeat="branch in branchGroup.branches"
                 ng-click="selectSubBranch2(branch.id)">
                {{branch.name}}
            </div>
        </div>
    </div>
   <!--
    <ul class="dropdown-menu" role="menu">
        <li ng-repeat="item in subBranch2.items">
            <a href="javascript:void(0)" ng-click="selectSubBranch2(item.id)">{{item.name}}</a>
        </li>
    </ul>
    -->
</div>
