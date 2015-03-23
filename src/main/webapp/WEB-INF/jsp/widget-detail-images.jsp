<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="detailImage" items="${detailImages}">
    <div style="margin: 5px 5px 5px -5px;">
        <img src="${detailImage}"/>
    </div>
</c:forEach>
