<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="boxShadow">
    <form action="admin-commodity-add-update" method="post" id="mainForm">
        <input type="hidden" name="id" value="${commodity.id}">

        <div class="basic">
            <div class="serial">
                <input type="hidden" name="serialNumber" value="${commodity.serialNumber}"/>
                商品编号
                &nbsp;&nbsp;
                <span id="serial">${commodity.serialNumber}</span>
            </div>
            <div class="branch">
                <%@include file="admin-edit-commodity-branch-select.jsp" %>
                <div style="position: absolute; right: 40px;top: 25px;">(必填)</div>
                <div style="float:right;clear:both;right:30px;font-size:9pt;top:8px;">
                    <a target="_blank" href="admin-branch">品牌管理&gt;&gt;</a>
                </div>
            </div>
        </div>
        <div class="link">
            <table>
                <tr>
                    <td>商品ID号</td>
                    <td>
                        <div class="input-group" role="group">
                            <input type="text" id="showId" name="showId" class="form-control"
                                   value="${commodity.showId}" placeholder="在此输入商品ID号"/>
                            <button type="button" ng-click="runCrawler()" class="btn btn-success">
                                OK
                            </button>
                        <span class="runCrawlerInfo" ng-show="runningCrawler">
                            (正在抓取网页...)
                        </span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>爬虫链接</td>
                    <td>
                        <input type="hidden" name="url" value="${commodity.url}"/>
                        <a id="crawlerLink" href="${commodity.url}" target="_blank">${commodity.url}</a>
                    </td>
                </tr>
                <tr>
                    <td>购买链接</td>
                    <td>
                        <input type="hidden" name="buyUrl" value="${commodity.buyUrl}"/>
                        <a id="buyLink" href="${commodity.buyUrl}" target="_blank">${commodity.buyUrl}</a>
                    </td>
                </tr>
                <tr>
                    <td>商品名称</td>
                    <td>
                        <input type="hidden" name="commodityName" value="${commodity.name}"/>
                        <a id="commodityName" class="noLineAnchor keyWordLink"
                           href="${commodity.url}">${commodity.name}</a>
                    </td>
                </tr>
            </table>
            <div class="images">
                <div class="commodityImage">
                    <div class="title">商品图片</div>
                    <div class="imageGroup">
                        <div class="image" ng-repeat="imageUrl in imageUrls">
                            <input type="hidden" name="imageUrls" value="{{imageUrl}}"/>
                            <img ng-src="{{imageUrl}}"/><br/>
                            <a class="noLineAnchor" href="javascript:void(0)"
                               ng-click="deleteImage($index)">删除</a>
                        </div>
                    </div>
                </div>
                <div class="commodityDescImage">
                    <div class="title">商品描述图片</div>
                    <div class="imageGroup">
                        <div class="image" ng-repeat="detailImageUrl in detailImageUrls">
                            <input type="hidden" name="detailImageUrls" value="{{detailImageUrl}}"/>
                            <img ng-src="{{detailImageUrl}}"/><br/>
                            <a class="noLineAnchor" href="javascript:void(0)"
                               ng-click="deleteDetailImage($index)">删除</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail">
            <table>
                <colgroup>
                    <col class="title">
                    <col class="content">
                </colgroup>
                <tbody>
                <tr>
                    <td>商品参数</td>
                    <td>
                        <textarea id="commodityDescription" name="parameters" cols="45"
                                  rows="12">${commodity.parameters}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="commodityDescriptionEffect"></div>
                    </td>
                </tr>
                <tr>
                    <td>相关好评</td>
                    <td>
                        <c:forEach var="appraiseGroup" items="${positiveAppraiseGroups}">
                            <input type="text" class="form-control" name="positiveAppraiseGroups"
                                   value="${appraiseGroup.content}"/>
                        </c:forEach>
                        <img class="link" title="添加" ng-click="addAppraiseGroup($event, 'positiveAppraiseGroups')"
                             src="resources/css/images/edit-commodity/add.png"/>
                    </td>
                </tr>
                <tr>
                    <td>相关差评</td>
                    <td>
                        <c:forEach var="appraiseGroup" items="${negativeAppraiseGroups}">
                            <input type="text" class="form-control" name="negativeAppraiseGroups"
                                   value="${appraiseGroup.content}"/>
                        </c:forEach>
                        <img class="link" title="添加" ng-click="addAppraiseGroup($event, 'negativeAppraiseGroups')"
                             src="resources/css/images/edit-commodity/add.png"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="button">
            <button id="deleteCommodity" name="deleteSubmit" type="submit">删除商品</button>
            <button id="publishCommodity" name="publishSubmit" type="submit">发布商品</button>
        </div>
        <div class="price">
            <div class="recommend">(建议不写)</div>
            <table>
                <tr>
                    <td class="title">历史低价</td>
                    <td class="text"><input type="text" class="form-control"/></td>
                    <td class="title">销量</td>
                    <td class="text"><input type="text" class="form-control"/></td>
                    <td class="title">最高售价</td>
                    <td class="text"><input type="text" class="form-control"/></td>
                </tr>
                <tr>
                    <td class="title">上价时间</td>
                    <td><input type="text" class="form-control"/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td class="title">价格历史</td>
                    <td colspan="3"><input type="text" class="form-control"/></td>
                    <td colspan="2">
                        <span style="font-size: 9pt">
                        输入格式：<br>['2014-12-24', 83.83],['2015-01-04', 88.83]
                        </span>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="deepTransparent" id="submitInfo">
        <h1>正在处理，请耐心等待...</h1>
    </div>
</div>
<%@include file="footer.jsp" %>
