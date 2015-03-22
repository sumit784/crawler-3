<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <table class="normal">
        <colgroup>
            <col class="index">
            <col class="name">
            <col class="parent">
            <col class="action">
            <col class="hotSearchWord">
        </colgroup>
        <thead>
        <tr>
            <th class="index">序号</th>
            <th class="name">名称</th>
            <th class="parent">父分类</th>
            <th class="action"></th>
            <th class="hotSearchWord">搜索关键词</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="searchWordGroup" items="${searchWordGroups}" varStatus="status">
            <c:set var="category" value="${searchWordGroup.category}"/>
            <c:set var="hotSearchWords" value="${searchWordGroup.hotSearchWords}"/>
            <tr id="category_${category.id}">
                <td class="index">${status.index+1}</td>
                <td class="name"><c:if test="${searchWordGroup.categoryLevel>0}">&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                        ${category.name}</td>
                <td class="parent" data-options="parentId: ${category.parentId}">${category.parentName}</td>
                <td>
                    <jsp:include page="widget-edit-delete.jsp">
                        <jsp:param name="editAction" value="editCategory($event)"/>
                        <jsp:param name="deleteAction" value="deleteCategory($event)"/>
                    </jsp:include>
                </td>
                <td class="hotSearchWord">
                    <table class="inner">
                        <tbody>
                        <c:forEach var="searchWord" items="${hotSearchWords}">
                            <tr id="hotSearchWord_${searchWord.id}">
                                <td class="content"><span
                                        <c:if test="${searchWord.hot}">class="hot"</c:if>>${searchWord.content}</span>
                                </td>
                                <td>
                                    <jsp:include page="widget-edit-delete.jsp">
                                        <jsp:param name="editAction" value="editSearchWord($event)"/>
                                        <jsp:param name="deleteAction" value="deleteSearchWord($event)"/>
                                    </jsp:include>
                                    <jsp:include page="widget-ranking.jsp">
                                        <jsp:param name="upAction" value="rankUpSearchWord($event)"/>
                                        <jsp:param name="downAction" value="rankDownSearchWord($event)"/>
                                    </jsp:include>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div><img class="link" ng-click="addSearchWord($event)" src="resources/css/images/add.png"/></div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form id="categoryForm" method="post" action="admin-category-add-update">
        <input type="hidden" name="id"/>

        <div>
            <label>名称: </label><br/>
            <input type="text" id="name" name="name"/>
        </div>
        <div>
            <label>父分类</label><br/>
            <jsp:include page="admin-category-parent-select.jsp">
                <jsp:param name="elementId" value="parentId"/>
            </jsp:include>
        </div>
        <div>
            <button id="addSubmit" type="submit" class="btn btn-success"
                    ng-click="validateInput($event)">
                添加
            </button>
            <button id="editSubmit" type="submit" class="btn btn-success" disabled
                    ng-click="validateInput($event)">
                修改
            </button>
        </div>
    </form>
    <form class="fixedForm" id="searchWordForm" method="post"
          action="admin-hot-search-word-add-update">
        <input type="hidden" name="id"/>
        <input type="hidden" name="categoryId"/>

        <div>
            <input type="text" name="content"/>
            &nbsp;&nbsp;
            <input type="checkbox" name="hot"/>热点词
        </div>
        <div>
            <button id="addSearchWordSubmit" type="submit" class="btn btn-success"
                    ng-click="validateSearchWordInput($event)">
                添加
            </button>
            <button id="editSearchWordSubmit" type="submit" class="btn btn-success"
                    ng-click="validateSearchWordInput($event)">
                修改
            </button>
            <button ng-click="cancelSearchWordInput()" id="cancelSearchWordSubmit" type="button"
                    class="btn btn-default">
                取消
            </button>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>