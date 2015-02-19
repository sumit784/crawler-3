<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <div class="user">XXX账号名</div>
    <div class="split"></div>
    <div class="commodity">发布和商品<span id="commdityCount">(12230)</span></div>
    <div class="split">
        <div class="boldSplit"></div>
    </div>
    <div class="log">操作日志</div>
    <div class="split"></div>
</div>
<div class="right">
    <table>
        <c:forEach var="commodity" items="${commodities}" varStatus="status">
            <c:if test="${status.index%4==0}"><tr></c:if>
            <td>
                <div class="boxShadow">
                    <div class="image">
                        <a href="admin-edit-commodity?${commodity.id}" target="_blank">
                            <img class="link" src="${commodity.picture}"/>
                        </a>
                    </div>
                    <div class="description">
                        <a href="edit-commodity.html" class="noLineAnchor" target="_blank">${commodity.name}</a>
                    </div>
                </div>
            </td>
            <c:if test="${status.index%4==3}"></tr></c:if>
        </c:forEach>
        <c:if test="${fn:length(commodities)%4==0}"><tr></c:if>
            <td>
                <div>
                    <div class="image">
                        <a href="edit-commodity.html?new=true" target="_blank">
                            <img class="link" src="resources/css/images/manage-commodity/add_c.png"/>
                        </a>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
<%@include file="footer.jsp" %>
