<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <%@include file="admin-navi.jsp" %>
</div>
<div class="right">
    <div class="search input-group">
        <form action="admin" method="get">
            <input type="text" name="keyWord" id="searchInput" class="form-control"
                   value="${keyWord}" placeholder="商品搜索"/>
        <span class="input-group-addon searchCommit">
            <img class="link" id="searchCommit" src="resources/css/images/index/search.png"/>
        </span>
        </form>
    </div>
    <%@include file="admin-commodities.jsp" %>
</div>
<%@include file="footer.jsp" %>
