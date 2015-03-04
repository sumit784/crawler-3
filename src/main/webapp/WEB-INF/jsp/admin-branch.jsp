<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <colgroup>
            <col class="index"/>
            <col class="name"/>
            <col class="firstLetter"/>
            <col class="parent"/>
            <col class="logo"/>
            <col class="shoppe"/>
            <col class="slogan"/>
            <col class="edit"/>
        </colgroup>
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>首字母</th>
            <th>父品牌</th>
            <th>logo</th>
            <th>旗舰店</th>
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
                    矩形: <a class="limit-size" data-options="limit:26" href="${branch.logo}"
                           target="_blank">${branch.logo}</a><br/>
                    方形: <a class="limit-size" data-options="limit:26" href="${branch.squareLogo}"
                           target="_blank">${branch.squareLogo}</a>
                </td>
                <td class="shoppe">
                    <%@include file="widget-shoppe-link.jsp" %>
                </td>
                <td class="slogan">${branch.slogan}</td>
                <td class="edit">
                    <jsp:include page="widget-edit-delete.jsp">
                        <jsp:param name="editAction" value="editBranch($event)"/>
                        <jsp:param name="deleteAction" value="deleteBranch($event)"/>
                    </jsp:include>
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
            <label>矩形logo(url或上传图片)</label>
            <input type="text" id="logo" name="logo"/>
            <input type="file" name="logoFile"/>
        </div>
        <div class="logo">
            <label>方形logo(url或上传图片)</label>
            <input type="text" id="squareLogo" name="squareLogo"/>
            <input type="file" name="squareLogoFile"/>
        </div>
        <div class="slogan">
            <label>品牌口号</label>
            <textarea cols="65" rows="3" name="slogan"></textarea>
        </div>
        <div class="shoppe">
            <div class="left"><label>官方旗舰店</label></div>
            <div class="right">
                <div ng-repeat="shoppe in shoppes">
                    名称: <input type="text" name="shoppeNames" value="{{shoppe.name}}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    url: <input type="text" name="shoppeUrls" value="{{shoppe.url}}"/>
                </div>
                <img ng-click="addShoppe()" class="link" title="添加" src="resources/css/images/add.png"/>
            </div>
        </div>
        <div class="button">
            <button id="addSubmit" type="submit" class="btn btn-primary"
                    ng-click="validateInput($event)">
                添加
            </button>
            <button id="editSubmit" type="submit" class="btn btn-success" disabled
                    ng-click="validateInput($event)">
                修改
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>