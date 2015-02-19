<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="left">
    <div class="snapshot boxShadow">
        <div class="left">
            <div class="branch">
                所属品牌：
                <a href="shoppe?id=${branch.id}" target="_blank">
                    <img src="${branch.logoPath}"/>
                </a>
            </div>
            <div class="largeImage">
                <c:if test="${fn:length(pictures)>0}">
                    <img class="boxShadow" src="${pictures[0].url}"/>

                    <div class="enlarge"></div>
                    <div class="enlargeImage">
                        <img src="${pictures[0].url}"/>

                        <div class="closeLargeImage"></div>
                    </div>
                </c:if>
            </div>
            <div class="smallImage">
                <c:forEach var="picture" items="${pictures}" varStatus="status">
                    <img class="link<c:if test='${status.index==0}'> selected</c:if>" src="${picture.url}"/>
                </c:forEach>
            </div>
        </div>
        <div class="right">
            <div class="name">
                烟雨集森林清新文艺个性棉麻纯色抽象五分袖宽松大码连衣长裙
            </div>
            <div class="price">
                <div>
                    历史低价：<span class="lowPrice">￥168.85</span>
                    销量： <span class="sales">16</span>件
                </div>
                <div>
                    最高售价： <span class="highestPrice">￥399</span>
                    上价时间： <span class="releaseDate">2014.03.21</span>
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
                    <a class="noLineAnchor" href="javascript:void(0)"
                       style="background-color:#FFE2E2;border:1px solid #B10000;color:#B10000;">无色差</a>
                    <a class="noLineAnchor" href="javascript:void(0)"
                       style="background-color:#D9F5ED;border:1px solid #40927F;color:#40927F;">起球</a>
                    <a class="noLineAnchor" href="javascript:void(0)"
                       style="background-color:#D9F5ED;border:1px solid #40927F;color:#40927F;">跟描述不符</a>
                </div>
            </div>
            <div class="buySubmit">
                <a id="couponLink" class="noLineAnchor" href="javascript:void(0)">
                    别忘了领取
                    <img src="resources/css/images/edit-commodity/coupon.png"/>
                    哦
                </a>
                <button id="buySubmit" class="orangeButton">去购买</button>
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
                <span class="shoppe">adidas官方店铺</span>
                <img src="resources/css/images/detail/vertical-split.png"/>
            </div>
            <div class="detail">
                <div class="goodsDetail">
                    <table>
                        <tr>
                            <td>保修：全国联保</td>
                            <td>是否商场同款：是</td>
                            <td>品牌：COACH/蔻驰</td>
                        </tr>
                        <tr>
                            <td>型号：14000035</td>
                            <td>机芯类型：石英表</td>
                            <td>手表各类：情侣表</td>
                        </tr>
                        <tr>
                            <td>风格：时尚潮流</td>
                            <td>表带材质：真皮</td>
                            <td>形状：圆形</td>
                        </tr>
                        <tr>
                            <td>显示方式：指针式</td>
                            <td>上市时间：2013年</td>
                            <td>颜色分类：140000035 14000034</td>
                        </tr>
                        <tr>
                            <td>成色：全新</td>
                            <td>防水深度：30米生活防水</td>
                            <td>表扣款式：皮带喑扣</td>
                        </tr>
                        <tr>
                            <td>表底类型：普通</td>
                            <td>表冠类型：普通</td>
                            <td>表盘深度：7mm</td>
                        </tr>
                        <tr>
                            <td>表盘直径：27mm</td>
                            <td>产地：美国</td>
                            <td>手表价格区间：3001-5000元</td>
                        </tr>
                        <tr>
                            <td>流行元素：复古</td>
                            <td>表壳村质：精钢</td>
                        </tr>
                    </table>
                </div>
                <div class="shoppe">
                    <a href="javascript:void(0)">唯品会营店</a>
                    <a href="javascript:void(0)">聚美优品官方旗舰店</a>
                    <a href="javascript:void(0)">天猫官方旗舰店</a>
                    <a href="javascript:void(0)">京东官方旗舰店</a>
                </div>
            </div>
        </div>
    </div>
    <div class="detailImage boxShadow">
        <img src="resources/css/images/detail/detail1.png"/>
    </div>
    <div class="detailImage boxShadow">
        <img src="resources/css/images/detail/detail2.png"/>
    </div>
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
