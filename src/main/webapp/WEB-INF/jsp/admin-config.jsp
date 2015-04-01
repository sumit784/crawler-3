<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="mainForm" method="post" action="admin-config-update" enctype="multipart/form-data">
        <fieldset>
            <legend>页头设置</legend>
            <div class="uploadImage">
                <label>页头横幅(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="globalBanner"/>
                    <jsp:param name="value" value="${appConfig.globalBanner}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>页头Logo(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="globalLogo"/>
                    <jsp:param name="value" value="${appConfig.globalLogo}"/>
                </jsp:include>
            </div>
        </fieldset>
        <fieldset>
            <legend>主页设置</legend>
            <div class="uploadImage">
                <label>首页头部海报(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="indexHeadPoster"/>
                    <jsp:param name="value" value="${appConfig.indexHeadPoster}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>首页尾部海报(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="indexFootPoster"/>
                    <jsp:param name="value" value="${appConfig.indexFootPoster}"/>
                </jsp:include>
                <br/>
                <label>目标链接: </label>
                <input style="width:150px;" type="text" name="indexFootPosterLink" value="${appConfig.indexFootPosterLink}"/>
            </div>
        </fieldset>
        <fieldset>
            <legend>商品列表页设置</legend>
            <div class="uploadImage">
                <label>名牌排行图片(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="branchRankImage"/>
                    <jsp:param name="value" value="${appConfig.branchRankImage}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>无对应商品时显示的图片(url或上传图片)</label><br/>
                <jsp:include page="admin-config-upload-image.jsp">
                    <jsp:param name="id" value="noFoundImage"/>
                    <jsp:param name="value" value="${appConfig.noFoundImage}"/>
                </jsp:include>
            </div>
            <div class="uploadImage">
                <label>无对应商品时显示的文本</label><br/>
                <textarea name="noFoundText" cols="25" rows="4">${appConfig.noFoundText}</textarea>
            </div>
        </fieldset>

        <button id="editSubmit" type="submit" class="btn btn-primary"
                ng-click="validateInput($event)">
            修改
        </button>
    </form>
</div>
<%@include file="footer.jsp" %>