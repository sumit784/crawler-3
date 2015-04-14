<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="mainForm" method="post" action="admin-article-edit-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <textarea id="articleContent" name="articleContent"></textarea>

        <div class="button">
            <button type="submit" class="btn btn-success add">
                发布
            </button>
            <button type="button" class="btn btn-primary edit">
                预览
            </button>
            <button type="button" class="btn btn-default cancel">
                取消
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>