<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="logo boxShadow" id="logoDiv">
    <div class="logoImage"><img src="${shopDescription.logoUrl}"/></div>
    <div class="description">${shopDescription.desc}</div>
    <div class="links">
        <c:forEach var="link" items="${shopDescription.links}">
            <a href="${link.url}">${link.name}</a>
        </c:forEach>
    </div>
</div>
<div class="goods">
    <div class="sort">
        <div class="title">
            <div class="lowest"><a class="noLineAnchor" href="javascript:void(0)">历史最低</a></div>
            <div class="all"><a class="noLineAnchor" href="javascript:void(0)">所有宝贝</a></div>
        </div>
        <%@include file="list-sort-links.jsp" %>
    </div>
    <%@include file="list-shapshots.jsp" %>
</div>
<%@include file="list-right-panel.jsp" %>
<%@include file="footer.jsp" %>
