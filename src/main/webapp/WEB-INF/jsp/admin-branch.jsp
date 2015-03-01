<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>首字母</th>
            <th>父品牌</th>
            <th>logo(矩形)</th>
            <th>logo(方形)</th>
            <th>品牌口号</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="branch" items="${branches}" varStatus="status">
            <tr id="branch_${branch.id}">
                <td class="index">${status.index+1}</td>
                <td class="name">${branch.name}</td>
                <td class="firstLetter">${branch.firstLetter}</td>
                <td class="parent" data-options="parentId: ${branch.parentId}">${branch.parentName}</td>
                <td class="logo">
                    <a class="limit-size" data-options="limit:30" href="${branch.logo}"
                       target="_blank">${branch.logo}</a>
                </td>
                <td class="squareLogo">
                    <a class="limit-size" data-options="limit:30" href="${branch.squareLogo}"
                       target="_blank">${branch.squareLogo}</a>
                </td>
                <td class="slogan limit-size" data-options="limit:50">${branch.slogan}</td>
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

        <div class="basic">
            <label>名称</label>
            <input type="text" id="name" name="name"/>
            <label>首字母</label>
            <input type="text" id="firstLetter" name="firstLetter"/>
            <label>父品牌</label>
            <jsp:include page="admin-branch-select.jsp">
                <jsp:param name="elementId" value="parentId"/>
            </jsp:include>
        </div>
        <div class="logo">
            <label>矩形Logo(url或上传图片)</label>
            <input type="text" id="logo" name="logo"/>
            <input type="file" name="logoFile"/>
        </div>
        <div class="logo">
            <label>方形Logo(url或上传图片)</label>
            <input type="text" id="squareLogo" name="squareLogo"/>
            <input type="file" name="squareLogoFile"/>
        </div>
        <div class="slogan">
            <label>品牌口号</label>
            <textarea cols="65" rows="3" name="slogan"></textarea>
        </div>
        <div class="button">
            <button id="addSubmit" type="submit" class="btn btn-primary">
                添加
            </button>
            <button id="editSubmit" type="submit" class="btn btn-success" disabled>
                修改
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>