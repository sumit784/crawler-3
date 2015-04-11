<%@ page import="com.qinyuan15.crawler.ui.RequestUtils" %>
<%@ page import="com.qinyuan15.crawler.dao.AppConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head lang="zh-ch">
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>${title}</title>
    <link href="resources/js/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=RequestUtils.getCss("common")%>">
    <c:forEach var="css" items="${moreCss}">
        <link rel="stylesheet" href="resources/css/${css}.css?t=<%=AppConfig.VERSION%>">
    </c:forEach>
    <link rel="stylesheet" href="<%=RequestUtils.getRelativeCss(request)%>">
    <c:forEach var="js" items="${headJs}">
        <script src="resources/js/${js}.js?t=<%=AppConfig.VERSION%>"></script>
    </c:forEach>
</head>
<body class="ng-app:main" ng-app="main" id="ng-app">
<div class="header">
    <div><img src="${appConfig.globalBanner}"/></div>
</div>
<div class="navigationBack" ng-controller="NavigationController">
    <div class="navigation">
        <div class="logo">
            <a href="index"><img src="${appConfig.globalLogo}"/></a>
        </div>
        <div class="navigationLinks">
            <a ng-repeat="category in categories" class="{{category.class}} noLineAnchor"
               href="javascript:void(0)" ng-href="list.html?id={{category.id}}">{{category.text}}</a>
        </div>
    </div>
</div>
${headerAdditions}
<div class="content" ng-controller="ContentController">