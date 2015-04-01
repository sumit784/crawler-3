<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="mainForm" method="post" action="admin-config-update" enctype="multipart/form-data">
        <fieldset>
            <legend>页头设置</legend>
            <div class="uploadImage">
                <label>页头横幅(url或上传图片)</label><br/>
                <jsp:include page="widget-upload-image.jsp">
                    <jsp:param name="id" value="globalBanner"/>
                    <jsp:param name="value" value="${appConfig.globalBanner}"/>
                </jsp:include>
                <jsp:include page="admin-config-image-snapshot.jsp">
                    <jsp:param name="url" value="${appConfig.globalBanner}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>页头Logo(url或上传图片)</label><br/>
                <jsp:include page="widget-upload-image.jsp">
                    <jsp:param name="id" value="globalLogo"/>
                    <jsp:param name="value" value="${appConfig.globalLogo}"/>
                </jsp:include>
                <jsp:include page="admin-config-image-snapshot.jsp">
                    <jsp:param name="url" value="${appConfig.globalLogo}"/>
                </jsp:include>
            </div>
        </fieldset>
        <fieldset>
            <legend>主页设置</legend>
            <div class="uploadImage">
                <label>首页头部海报(url或上传图片)</label><br/>
                <jsp:include page="widget-upload-image.jsp">
                    <jsp:param name="id" value="indexHeadPoster"/>
                    <jsp:param name="value" value="${appConfig.indexHeadPoster}"/>
                </jsp:include>
                <jsp:include page="admin-config-image-snapshot.jsp">
                    <jsp:param name="url" value="${appConfig.indexHeadPoster}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>首页尾部海报(url或上传图片)</label><br/>
                <jsp:include page="widget-upload-image.jsp">
                    <jsp:param name="id" value="indexFootPoster"/>
                    <jsp:param name="value" value="${appConfig.indexFootPoster}"/>
                </jsp:include>
                <jsp:include page="admin-config-image-snapshot.jsp">
                    <jsp:param name="url" value="${appConfig.indexFootPoster}"/>
                </jsp:include>
            </div>
        </fieldset>
        <button id="editSubmit" type="submit" class="btn btn-primary"
                ng-click="validateInput($event)">
            修改
        </button>
    </form>
</div>
<%@include file="footer.jsp" %>