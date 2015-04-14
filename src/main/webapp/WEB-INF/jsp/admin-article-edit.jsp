<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="mainForm" method="post" action="admin-article-add-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${article.id}"/>
        <textarea id="content" name="content">${article.content}</textarea>

        <div class="button">
            <button type="submit" class="btn btn-success" ng-click="publish($event)">
                发布
            </button>
            <button type="button" class="btn btn-primary" ng-click="view()">
                预览
            </button>
            <button type="button" class="btn btn-default" ng-click="cancelEdit()">
                取消
            </button>
        </div>
    </form>
    <div id="viewDiv">
        <div class="article">${article.content}</div>
        <div class="button">
            <button type="button" class="btn btn-primary" ng-click="cancelView()">
                继续编辑
            </button>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>