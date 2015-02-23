<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<div class="boxShadow">
    <form action="admin-commodity-add-update" method="post">
        <div class="branch">
            <div class="serial">
                商品编号：<span id="serial">${commodity.serialNumber}</span>
            </div>
            <div id="branchSelect" class="input-group-btn">
                <input type="hidden" name="branchId" value="{{branch.selected.id}}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    {{branch.selected.name}}
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li ng-repeat="item in branch.items">
                        <a href="javascript:void(0)" ng-click="selectBranch(item.id)">{{item.name}}</a>
                    </li>
                </ul>
            </div>
            <div id="firstLevelBranchSelect" class="input-group-btn">
                <input type="hidden" name="subbranch1Id" value="{{subBranch1.selected.id}}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        ng-disabled="subBranch1.disabled">
                    {{subBranch1.selected.name}}
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li ng-repeat="item in subBranch1.items">
                        <a href="javascript:void(0)" ng-click="selectSubBranch1(item.id)">{{item.name}}</a>
                    </li>
                </ul>
            </div>
            <div id="secondLevelBranchSelect" class="input-group-btn">
                <input type="hidden" name="subbranch1Id" value="{{subBranch2.selected.id}}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        ng-disabled="subBranch2.disabled">
                    {{subBranch2.selected.name}}
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li ng-repeat="item in subBranch2.items">
                        <a href="javascript:void(0)" ng-click="selectSubBranch2(item.id)">{{item.name}}</a>
                    </li>
                </ul>
            </div>
            <div style="position: absolute; right: 40px;top: 25px;">(必填)</div>
            <div style="float:right;clear:both;right:30px;font-size:9pt;top:8px;">
                <a target="_blank" href="admin-branch">品牌管理&gt;&gt;</a>
            </div>
        </div>
        <div class="link">
            <table>
                <tr>
                    <td>商品ID号</td>
                    <td>
                        <div class="input-group" role="group">
                            <input type="text" id="showId" name="showId" class="form-control"
                                   value="${commodity.showId}" placeholder="在此输入商品ID号"
                                   ng-model="showId"/>
                            <button ng-click="runCrawler()" class="btn btn-success">OK</button>
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
                        <!--
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">Nike</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">耐克官方</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">ZOOM</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">SB</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">STEFAN</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">JANOSKI</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">SE</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">男子滑板鞋</a>
                        <a class="noLineAnchor keyWordLink" href="javascript:void(0)">473284</a>
                        -->
                        <input type="hidden" name="commodityName" value="${commodity.name}"/>
                        <a id="commodityName" class="noLineAnchor keyWordLink"
                           href="${commodity.url}">${commodity.name}</a>
                    </td>
                </tr>
            </table>
            <div class="image">
                <div class="commodityImage">
                    <div>商品图片</div>
                    <table>
                        <tr>
                            <td><img src="resources/css/images/edit-commodity/desc1.png"/></td>
                            <td><img src="resources/css/images/edit-commodity/desc2.png"/></td>
                        </tr>
                        <tr>
                            <td><a class="noLineAnchor" href="javascript:void(0)">删除</a></td>
                            <td><a class="noLineAnchor" href="javascript:void(0)">删除</a></td>
                        </tr>
                    </table>
                </div>
                <div class="commodityDescImage">
                    <div>商品描述图片</div>
                    <table>
                        <tr>
                            <td><img src="resources/css/images/edit-commodity/desc1.png"/></td>
                            <td><img src="resources/css/images/edit-commodity/desc2.png"/></td>
                        </tr>
                        <tr>
                            <td><a class="noLineAnchor" href="javascript:void(0)">删除</a></td>
                            <td><a class="noLineAnchor" href="javascript:void(0)">删除</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="detail">
            <table>
                <tr>
                    <td>商品参数</td>
                    <td>
                        <textarea id="commodityDescription" cols="45" rows="12">{{commodityDescription}}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>相关好评</td>
                    <td>
                        <input type="text" class="form-control" value="无色差"/>
                        <input type="text" class="form-control" value=""/>
                        <input type="text" class="form-control" value=""/>
                    </td>
                </tr>
                <tr>
                    <td>相关差评</td>
                    <td>
                        <input type="text" class="form-control" value="起球"/>
                        <input type="text" class="form-control" value="跟描述不符"/>
                        <input type="text" class="form-control" value=""/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="commodityDescriptionEffect"></div>
                    </td>
                </tr>
            </table>
        </div>
        <div class="button">
            <button id="deleteCommodity" type="button">删除商品</button>
            <button id="publishCommodity" type="button">发布商品</button>
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
</div>
<%@include file="footer.jsp" %>
