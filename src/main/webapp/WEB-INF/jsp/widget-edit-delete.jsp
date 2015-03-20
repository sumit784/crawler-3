<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span <c:if test="${param.editAction!=null}">ng-click="${param.editAction}"</c:if>>
    <img class="link edit" title="编辑" src="resources/css/images/table/reply.png"/>
</span>
<span <c:if test="${param.deleteAction!=null}">ng-click="${param.deleteAction}"</c:if>>
    <img class="link delete" title="删除" src="resources/css/images/table/action_delete.png"/>
</span>
