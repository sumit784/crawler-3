<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html ng-app="main">
<head lang="zh-ch">
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>${title}</title>
    <link href="resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/common.css">
    <c:forEach var="css" items="${moreCss}">
        <link rel="stylesheet" href="resources/css/${css}.css">
    </c:forEach>
    <link rel="stylesheet"
          href="resources/css/<%=request.getRequestURI().replaceAll("^.*/","").replace(".jsp",".css")%>">
</head>
<body>
<div class="header">
    <div><img src="resources/css/images/header.gif"/></div>
</div>
<div class="navigationBack" ng-controller="NavigationController">
    <div class="navigation">
        <div class="logo">
            <a href="index"><img src="resources/css/images/logo.png"/></a>
        </div>
        <div class="navigationLinks">
            <a ng-repeat="category in categories" class="darkFont noLineAnchor"
               href="list?keyWord={{category}}">{{category}}</a>
        </div>
    </div>
</div>
<div class="content" ng-controller="ContentController">