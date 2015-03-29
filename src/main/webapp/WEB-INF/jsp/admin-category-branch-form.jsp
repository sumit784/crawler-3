<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="fixedForm" id="branchForm" method="post" action="admin-category-branch-add">
    <input type="hidden" name="categoryId"/>

    <div class="branchGroup">
        <c:forEach var="branch" items="${branches}">
            <div class="branch">
                <img class="branch-logo link" src="${branch.logo}" ng-click="electBranch($event)"
                     data-options="id:${branch.id}"/><br/>
                <span>${branch.name}</span>
            </div>
        </c:forEach>
    </div>
    <div>
        <button id="addBranchSubmit" type="submit" class="btn btn-success"
                ng-click="validateBranchInput($event)">
            添加
        </button>
        <button ng-click="cancelBranchInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
</form>