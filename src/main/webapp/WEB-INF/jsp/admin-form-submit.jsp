<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="button">
    <button id="addSubmit" type="submit" class="btn btn-success"
            ng-click="validateInput($event)">
        添加
    </button>
    <button id="editSubmit" type="submit" class="btn btn-primary" disabled
            ng-click="validateInput($event)">
        修改
    </button>
    <button id="editCancel" type="button" class="btn btn-default" disabled>
        取消修改
    </button>
</div>
