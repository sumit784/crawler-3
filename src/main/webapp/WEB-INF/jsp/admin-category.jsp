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
        <col class="branch">
        <col class="poster">
    </colgroup>
    <thead>
    <tr>
        <th class="index">序号</th>
        <th class="name">名称</th>
        <th class="parent">父分类</th>
        <th class="action"></th>
        <th class="hotSearchWord">搜索关键词</th>
        <th class="branch">关联品牌</th>
        <th class="poster">海报</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="richCategory" items="${richCategories}" varStatus="status">
        <c:set var="category" value="${richCategory.category}"/>
        <tr id="category_${category.id}">
            <td class="index">${status.index+1}</td>
            <td class="name"><c:if test="${richCategory.categoryLevel>0}">&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                    ${category.name}</td>
            <td class="parent" data-options="parentId: ${category.parentId}">${category.parentName}</td>
            <td>
                <jsp:include page="widget-edit-delete.jsp">
                    <jsp:param name="editAction" value="editCategory($event)"/>
                    <jsp:param name="deleteAction" value="deleteCategory($event)"/>
                </jsp:include>
                <jsp:include page="widget-ranking.jsp">
                    <jsp:param name="upAction" value="rankUpCategory($event)"/>
                    <jsp:param name="downAction" value="rankDownCategory($event)"/>
                </jsp:include>
            </td>
            <td class="hotSearchWord">
                <table class="inner">
                    <tbody>
                    <c:forEach var="searchWord" items="${richCategory.hotSearchWords}">
                        <tr id="hotSearchWord_${searchWord.id}">
                            <td class="content">
                                <span <c:if test="${searchWord.hot}">class="hot"</c:if>>${searchWord.content}</span>
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
            <td class="branch">
                <table class="inner">
                    <tbody>
                    <c:forEach var="branch" items="${richCategory.branches}">
                        <tr id="branch_${branch.id}">
                            <td><img class="branch-logo" src="${branch.logo}"/></td>
                            <td>
                                <jsp:include page="widget-delete.jsp">
                                    <jsp:param name="deleteAction" value="deleteBranch($event)"/>
                                </jsp:include>
                                <jsp:include page="widget-ranking.jsp">
                                    <jsp:param name="upAction" value="rankUpBranch($event)"/>
                                    <jsp:param name="downAction" value="rankDownBranch($event)"/>
                                </jsp:include>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div><img class="link" ng-click="addBranch($event)" src="resources/css/images/add.png"/></div>
            </td>
            <td class="poster">
                <table class="inner">
                    <tbody>
                    <c:forEach var="poster" items="${richCategory.posters}">
                        <tr id="poster_${poster.id}">
                            <td class="image">
                                <img class="branch-logo" src="${poster.path}"/>
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
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form id="categoryForm" method="post" action="admin-category-add-update">
    <input type="hidden" name="id"/>

    <div class="name">
        <label>名称</label>
        <input type="text" id="name" name="name"/>
    </div>
    <div class="parent">
        <label>父分类</label>
        <jsp:include page="admin-category-parent-select.jsp">
            <jsp:param name="elementId" value="parentId"/>
        </jsp:include>
    </div>
    <div class="button">
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
        <button ng-click="cancelSearchWordInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
</form>
<form class="fixedForm" id="branchForm" method="post" action="admin-category-branch-add">
    <input type="hidden" name="categoryId"/>

    <div class="branchGroup">
        <c:forEach var="branch" items="${branches}">
            <div class="branch">
                <img class="branch-logo link" src="${branch.logo}" ng-click="electBranch($event)"
                     data-options="id:${branch.id}"/><br/>
                <span>${branch.name}</span>
            </div>
        </c:forEach>
    </div>
    <div>
        <button id="addBranchSubmit" type="submit" class="btn btn-success"
                ng-click="validateBranchInput($event)">
            添加
        </button>
        <button ng-click="cancelBranchInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
</form>

<form class="fixedForm" id="posterForm" method="post" action="admin-category-poster-add-update"
      enctype="multipart/form-data">
    <input type="hidden" name="id"/>
    <input type="hidden" name="categoryId"/>

    <div class="path">
        <label>图片(url或上传图片)</label>
        <span class="required">*</span>
        <br/>
        <input type="text" name="url"/>
        <input type="file" name="uploadFile"/>
    </div>
    <div class="link">
        <label>目标链接</label><br/>
        <input type="text" name="link"/>
    </div>
    <div class="button">
        <button id="addPosterSubmit" type="submit" class="btn btn-primary"
                ng-click="validatePosterInput($event)">
            添加
        </button>
        <button id="editPosterSubmit" type="submit" class="btn btn-success"
                ng-click="validatePosterInput($event)">
            修改
        </button>
        <button ng-click="cancelPosterInput()" type="button" class="btn btn-default">
            取消
        </button>
    </div>
</form>
</div>
<%@include file="footer.jsp" %>