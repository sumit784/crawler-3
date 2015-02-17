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
    <table>
        <c:forEach var="image" items="${images}" varStatus="status">
            <c:if test="${status.index % 6 == 0}"><tr></c:if>
            <td>
                <div>
                    <img class="link" src="${image.src}">

                    <div class="text">${image.text}</div>
                    <div class="cover deepTransparent"></div>
                </div>
            </td>
            <c:if test="${status.index % 6 == 5}"></tr></c:if>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp" %>

