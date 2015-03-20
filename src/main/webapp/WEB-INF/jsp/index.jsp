<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="title">
    <img src="resources/css/images/index/title.png"/>
</div>
<div class="search input-group">
    <form action="search">
        <input type="text" name="keyWord" id="searchInput" class="form-control"
               placeholder="搜下有什么好东西"/>
        <span class="input-group-addon searchCommit">
            <img class="link" id="searchCommmit" src="resources/css/images/index/search.png"/>
        </span>
    </form>
</div>
<div class="links">
    <c:forEach var="indexLogo" items="${indexLogos}">
        <div class="logoGroup">
            <a href="${fn:length(indexLogo.link)>0?indexLogo.link:'javascript:void(0)'}">
                <img src="${indexLogo.path}">
            </a>

            <div class="text">${indexLogo.description}</div>
            <div class="cover deepTransparent"></div>
        </div>
    </c:forEach>
</div>
<%@include file="footer.jsp" %>

