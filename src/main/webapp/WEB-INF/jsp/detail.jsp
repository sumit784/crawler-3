<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <div class="snapshot boxShadow">
        <div class="left">
            <div class="branch">
                所属品牌：
                <a href="shoppe?id=${branch.id}" target="_blank">
                    <img class="branch-logo" src="${branch.logo}"/>
                </a>
            </div>
            <script id="pictures" type="text/x-data">${pictures}</script>
            <script id="middlePictures" type="text/x-data">${middlePictures}</script>
            <div class="largeImage">
                <c:if test="${fn:length(pictures)>0}">
                    <img class="boxShadow" src="${middlePictures[0]}"/>

                    <div class="enlarge"></div>
                    <div class="enlargeImage">
                        <img src="${pictures[0]}"/>

                        <div class="closeLargeImage"></div>
                    </div>
                </c:if>
            </div>
            <div class="smallImage">
                <c:forEach var="picture" items="${smallPictures}" varStatus="status">
                    <img class="${status.index==0?'link selected':'link'}" data-options="index:${status.index}"
                         src="${picture}"/>
                </c:forEach>
            </div>
        </div>
        <div class="right">
            <div class="name">${commodity.name}</div>
            <div class="price">
                <div>
                    历史低价： <span class="lowPrice">￥${lowPrice}</span>
                    销量： <span class="sales">${commodity.sales}</span>件
                </div>
                <div>
                    最高售价： <span class="highestPrice">￥${highPrice}</span>
                    上架时间： <span class="releaseDate">${commodity.onShelfTime}</span>
                </div>
                <div>
                    <span class="trend" id="trendImage">相关评价 和 历史价格走势
                    <img class="link" src="resources/css/images/detail/trend.png"/></span>
                </div>
            </div>
            <div class="priceHistory boxShadow">
                <div id="trendChart"></div>
                <div class="triangle-border tb-border"></div>
                <div class="triangle-border tb-background"></div>
                <div class="comments">
                    <c:forEach var="appraiseGroup" items="${positiveAppraiseGroups}">
                        <a class="noLineAnchor positive" href="javascript:void(0)">${appraiseGroup.content}</a>
                    </c:forEach>
                    <c:forEach var="appraiseGroup" items="${negativeAppraiseGroups}">
                        <a class="noLineAnchor negative" href="javascript:void(0)">${appraiseGroup.content}</a>
                    </c:forEach>
                </div>
            </div>
            <div class="buySubmit">
                <a id="couponLink" class="noLineAnchor" href="javascript:void(0)">
                    别忘了领取
                    <img src="resources/css/images/edit-commodity/coupon.png"/>
                    哦
                </a>
                <a href="${commodity.buyUrl}" target="_blank">
                    <button id="buySubmit" class="orangeButton">去购买</button>
                </a>
            </div>
        </div>
        <div class="share">
            <div class="foundTime">
                发现时间：<span id="foundTime">15日 09:03</span>
            </div>
            <div class="links">
                告诉小伙伴：
                    <span class="links">
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share1.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share2.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share3.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share4.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share5.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share6.png"/></a>
                    <a href="javascript:void(0)"><img src="resources/css/images/detail/share7.png"/></a>
                    </span>
            </div>
        </div>
        <div class="description">
            <div class="title">
                <span class="goodsDetail selected">商品详情</span>
                <img src="resources/css/images/detail/vertical-split.png"/>
                <span class="shoppe">${branch.name}官方店铺</span>
                <img src="resources/css/images/detail/vertical-split.png"/>
            </div>
            <div class="detail">
                <script id="commodityParametersData" type="text/x-data">${commodity.parameters}</script>
                <div id="commodityParameters" class="goodsDetail"></div>
                <div class="shoppe">
                    <%@include file="widget-shoppe-link.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <c:forEach var="detailPicture" items="${detailPictures}">
        <img src="${detailPicture}"/>
    </c:forEach>
</div>
<div class="right">
    <div class="whiteBack boxShadow">
        <div class="title">
            更多相关商品
        </div>
        <div class="other">
            <div>
                <img src="resources/css/images/detail/otherGoods1.png"/>
                <img src="resources/css/images/detail/otherGoods2.png"/>
            </div>
            <div>
                <img src="resources/css/images/detail/otherGoods1.png"/>
                <img src="resources/css/images/detail/otherGoods2.png"/>
            </div>
            <div>
                <img src="resources/css/images/detail/otherGoods1.png"/>
                <img src="resources/css/images/detail/otherGoods2.png"/>
            </div>
            <div>
                <img src="resources/css/images/detail/otherGoods1.png"/>
                <img src="resources/css/images/detail/otherGoods2.png"/>
            </div>
        </div>
    </div>
    <div class="text">
        <div style="font-size:10pt;color:#a16b4e;margin: 12px 15px;">
            无非就是买买买，何必等到又十一
        </div>
        <div style="font-size:9pt;color:#6a6a6a;margin: 0 30px;">
            距离双十一还有不到一个月的时间，钱包君好像已经感受到了什么，渐渐开始躁动不安起来...
        </div>
        <div style="font-size:10pt;margin:12px 15px;text-align:right;">
            ......（<span style="color:#a16b4e;">更多</span>）
        </div>
        <div style="margin: 5px 5px 5px -5px;">
            <img src="resources/css/images/detail/other11.png"/>
        </div>
        <div style="margin: 5px 5px 5px -5px;">
            <img src="resources/css/images/detail/other11.png"/>
        </div>
    </div>
    <div class="attention">
        <div class="title">关注我们</div>
        <div class="link">
            豆瓣：<a href="javascript:void(0)">豆豆</a><br>
            微博：<a href="javascript:void(0)">@豆瓣东西</a>
        </div>
    </div>
    <div class="advice">
        <a href="javascript:void(0)">我想给你们些建议</a>
    </div>
</div>
<%@include file="footer.jsp" %>
