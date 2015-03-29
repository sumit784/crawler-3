<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="inner">
    <tbody>
    <c:forEach var="poster" items="${richCategory.posters}">
        <tr id="poster_${poster.id}">
            <td class="image">
                <img title="单击放大" class="branch-logo link" src="${poster.path}" ng-click="enlargePoster($event)"/>
                <c:if test="${fn:length(poster.link)>0}">
                    <br/><a target="_blank" href="${poster.link}">${poster.link}</a>
                </c:if>
            </td>
            <td>
                <jsp:include page="widget-edit-delete.jsp">
                    <jsp:param name="editAction" value="editPoster($event)"/>
                    <jsp:param name="deleteAction" value="deletePoster($event)"/>
                </jsp:include>
                <jsp:include page="widget-ranking.jsp">
                    <jsp:param name="upAction" value="rankUpPoster($event)"/>
                    <jsp:param name="downAction" value="rankDownPoster($event)"/>
                </jsp:include>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div><img class="link" ng-click="addPoster($event)" src="resources/css/images/add.png"/></div>