<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="fixedForm" id="posterForm" method="post" action="admin-category-poster-add-update"
      enctype="multipart/form-data">
    <input type="hidden" name="id"/>
    <input type="hidden" name="categoryId"/>

    <div class="path">
        <label>图片(url或上传图片)</label>
        <span class="required">*</span>
        <br/>
        <input type="text" name="url"/>
        <input type="file" name="uploadFile"/>
    </div>
    <div class="link">
        <label>目标链接</label><br/>
        <input type="text" name="link"/>
    </div>
    <div class="button">
        <button id="addPosterSubmit" type="submit" class="btn btn-primary"
                ng-click="validatePosterInput($event)">
            添加
        </button>
        <button id="editPosterSubmit" type="submit" class="btn btn-success"
                ng-click="validatePosterInput($event)">
            修改
        </button>
        <button ng-click="cancelPosterInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
</form>