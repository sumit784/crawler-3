<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="detailForm" method="post" action="admin-detail-update">
        <label>相关商品数量限制(0表示无限制)：</label>
        <input type="text" style="width: 30px;" name="relatedCommoditySize" value="${appConfig.relatedCommoditySize}"/>
        <h4>文字描述：</h4>
        <textarea class="ckeditor" name="detailText">${appConfig.detailText}</textarea>

        <div class="submit">
            <button id="editSubmit" type="submit" class="btn btn-success">
                提交修改
            </button>
        </div>
        <div class="image">
            <h4>相关图片：</h4>
            <table class="normal">
                <thead>
                <tr>
                    <th>图片链接</th>
                    <th>目标链接</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="detailImage" items="${appConfig.detailImagesList}" varStatus="status">
                    <tr id="detailImage_${status.index}">
                        <td>
                            <a class="limit-size" data-options="limit:50" href="${detailImage}"
                               target="_blank" title="${detailImage}">${detailImage}</a>
                        </td>
                        <td>
                            <jsp:include page="widget-edit-delete.jsp">
                                <jsp:param name="editAction" value="editImage($event)"/>
                                <jsp:param name="deleteAction" value="deleteImage($event)"/>
                            </jsp:include>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <button id="addImage" ng-click="addImage()" type="button" class="btn btn-default">
                增加图片
            </button>
        </div>
    </form>
    <fieldset class="effect">
        <legend>效果</legend>
        <div class="effect">
            <%@include file="widget-detail-text.jsp" %>
            <%@include file="widget-detail-images.jsp" %>
        </div>
    </fieldset>
    <form class="fixedForm" id="imageForm" method="post" action="admin-detail-image-add-update"
          enctype="multipart/form-data">
        <input type="hidden" name="id"/>

        <div>
            <label>(url或上传图片)</label><span class="required">*</span><br/>
            <input type="text" name="url"/><br/>
            <input type="file" name="uploadFile"/>
        </div>
        <div>
            <button id="addImageSubmit" type="submit" class="btn btn-success"
                    ng-click="validateImageInput($event)">
                添加
            </button>
            <button id="editImageSubmit" type="submit" class="btn btn-success"
                    ng-click="validateImageInput($event)">
                修改
            </button>
            <button ng-click="cancelImageInput()" type="button" class="btn btn-default">
                取消
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>