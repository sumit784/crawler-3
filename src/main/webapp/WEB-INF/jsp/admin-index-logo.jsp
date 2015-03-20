<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <thead>
        <tr>
            <th>序号</th>
            <th>图片链接</th>
            <th>目录链接</th>
            <th>描述</th>
            <th>缩略图</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="indexLogo" items="${indexLogos}" varStatus="status">
            <tr id="indexLogo_${indexLogo.id}">
                <td class="index">${status.index+1}</td>
                <td class="path">
                    <a class="limit-size" href="${indexLogo.path}" title="${indexLogo.path}" target="_blank"
                       data-options="limit:26">${indexLogo.path}</a>
                </td>
                <td class="link">
                    <a class="limit-size" href="${indexLogo.link}" title="${indexLogo.link}" target="_blank"
                       data-options="limit:26">${indexLogo.link}</a>
                </td>
                <td class="description">${indexLogo.description}</td>
                <td class="thumbnail">
                    <a href="${indexLogo.path}" target="_blank">
                        <img src="${indexLogo.path}"/>
                    </a>
                </td>
                <td>
                    <jsp:include page="widget-edit-delete.jsp">
                        <jsp:param name="editAction" value="editIndexLogo($event)"/>
                        <jsp:param name="deleteAction" value="deleteIndexLogo($event)"/>
                    </jsp:include>
                    <jsp:include page="widget-ranking.jsp">
                        <jsp:param name="upAction" value="upIndexLogo($event)"/>
                        <jsp:param name="downAction" value="downIndexLogo($event)"/>
                    </jsp:include>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="indexLogoForm" method="post" action="admin-index-logo-add-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id"/>

        <div class="logo">
            <label>图片(url或上传图片)</label>
            <span class="required">*</span>
            <br/>
            <input type="text" name="logo"/>
            <input type="file" name="logoFile"/>
        </div>
        <div class="link">
            <label>目标链接</label><br/>
            <input type="text" name="link"/>
        </div>
        <div class="description">
            <label>描述</label><br/>
            <input type="text" name="description"/>
        </div>
        <div>
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
</div>
<%@include file="footer.jsp" %>