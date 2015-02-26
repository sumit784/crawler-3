<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>父分类</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <tr id="category_${category.id}">
                <td class="index">${status.index+1}</td>
                <td class="name">${category.name}</td>
                <td class="parent" data-options="parentId: ${category.parentId}">${category.parentName}</td>
                <td>
                    <jsp:include page="widget-edit-delete.jsp"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="categoryForm" method="post" action="admin-category-add-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id"/>

        <div>
            <label>名称: </label><br/>
            <input type="text" id="name" name="name"/>
        </div>
        <div>
            <label>父分类</label><br/>
            <jsp:include page="admin-category-select.jsp">
                <jsp:param name="elementId" value="parentId"/>
            </jsp:include>
        </div>
        <div>
            <button id="addSubmit" type="submit" class="btn btn-success">
                添加
            </button>
            <button id="editSubmit" type="submit" class="btn btn-success" disabled>
                修改
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>