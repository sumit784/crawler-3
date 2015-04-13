<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="fixedForm" id="searchWordForm" method="post"
      action="admin-hot-search-word-add-update">
    <input type="hidden" name="id"/>
    <input type="hidden" name="categoryId"/>

    <div>
        <input type="text" name="content"/>
        &nbsp;&nbsp;
        <input type="checkbox" name="hot"/>热点词
    </div>
    <%@include file="admin-form-submit.jsp"%>
    <%--
    <div>
        <button id="addSearchWordSubmit" type="submit" class="btn btn-success"
                ng-click="validateSearchWordInput($event)">
            添加
        </button>
        <button id="editSearchWordSubmit" type="submit" class="btn btn-success"
                ng-click="validateSearchWordInput($event)">
            修改
        </button>
        <button ng-click="cancelSearchWordInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
    --%>
</form>