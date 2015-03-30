<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form id="filterForm" method="get" action="admin-crawl-log.html">
        <div class="success">
            <label>是否成功</label>
            <select name="success">
                <option value="0">全部</option>
                <option value="1">是</option>
                <option value="2">否</option>
            </select>
        </div>
        <div class="showId">
            <label>商品ID</label>
            <input type="text" name="showId"/>
        </div>
        <div class="button">
            <button id="addSubmit" type="submit" class="btn btn-success">筛选</button>
        </div>
    </form>


    <table class="normal">
        <colgroup>
            <col class="index"/>
            <col class="showId"/>
            <col class="name"/>
            <col class="url"/>
            <col class="time"/>
            <col class="action"/>
            <col class="success"/>
        </colgroup>
        <thead>
        <tr>
            <th>序号</th>
            <th>商品ID</th>
            <th>商品名称</th>
            <th>爬虫链接</th>
            <th>时间</th>
            <th>操作</th>
            <th>是否成功</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="crawlLog" items="${crawlLogs}" varStatus="status">
            <tr>
                <td class="index">${status.index+1}</td>
                <td class="showId">${crawlLog.commodity.showId}</td>
                <td class="name">${crawlLog.commodity.name}</td>
                <td class="url">
                    <a href="${crawlLog.commodity.url}" target="_blank">${crawlLog.commodity.url}</a>
                </td>
                <td class="time">${crawlLog.logTime}</td>
                <td class="action">${crawlLog.action}</td>
                <td class="action">${crawlLog.success ? '是' : '否'}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="footer.jsp" %>