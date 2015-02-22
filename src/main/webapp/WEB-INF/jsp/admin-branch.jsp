<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <thead>
        <tr>
            <th>名称</th>
            <th>logo</th>
            <th>父品牌</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="branch" items="${branches}">
            <c:choose>
                <c:when test="${fn:contains(branch.logo, '://')}">
                    <c:set var="logoUrl" value="${branch.logo}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="logoUrl" value="ftp://${pageContext.request.localAddr}/${branch.logo}"/>
                </c:otherwise>
            </c:choose>
            <tr id="branch_${branch.id}">
                <td class="name">${branch.name}</td>
                <td class="logo">
                    <a class="limit-size" data-options="limit:40" href="${logoUrl}">${branch.logo}</a>
                </td>
                <td class="parent" data-options="parentId: ${branch.parentId}">${branch.parentName}</td>
                <td>
                    <jsp:include page="widget-edit-delete.jsp"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="branchForm" method="post" action="admin-branch-add-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id"/>

        <div>
            <label>名称: </label><br/>
            <input type="text" id="name" name="name"/>
        </div>
        <div>
            <label>Logo(url或上传图片): </label><br/>
            <input type="text" id="logo" name="logo"/>
            <input type="file" name="logoFile"/>
        </div>
        <div>
            <label>父品牌</label><br/>
            <jsp:include page="admin-branch-select.jsp">
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
