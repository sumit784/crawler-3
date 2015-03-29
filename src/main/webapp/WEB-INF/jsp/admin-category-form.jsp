<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form id="categoryForm" method="post" action="admin-category-add-update">
    <input type="hidden" name="id"/>

    <div class="name">
        <label>名称</label>
        <input type="text" id="name" name="name"/>
    </div>
    <div class="parent">
        <label>父分类</label>
        <jsp:include page="admin-category-parent-select.jsp">
            <jsp:param name="elementId" value="parentId"/>
        </jsp:include>
    </div>
    <div class="button">
        <button id="addSubmit" type="submit" class="btn btn-success"
                ng-click="validateInput($event)">
            添加
        </button>
        <button id="editSubmit" type="submit" class="btn btn-success" disabled
                ng-click="validateInput($event)">
            修改
        </button>
    </div>
</form>