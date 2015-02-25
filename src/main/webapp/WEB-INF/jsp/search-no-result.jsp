<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="info boxShadow">
    <div class="image"><img src="resources/css/images/search/black-cat.png"/></div>
    <div class="text">
        <div style="font-weight: bold;margin-bottom: 20px;color:#333333;">
            喵~没找到与“khhkhlj”相关的 商品 哦，要不您找个关键词我帮您再找找看
        </div>
        <div style="font-weight: bold;font-size:10pt;margin-bottom: 10px;color:#595959;">
            建议您：
        </div>
        <div style="font-size: 9pt;color:#595959;line-height:20px;">
            1. 看看输入的文字是否有误
            <br>
            2. 调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”
        </div>
    </div>
    <div class="searchForm">
        <div class="input-group">
            <div class="input-group-btn searchType">
                <button type="button" class="btn btn-default dropdown-toggle grayBack" data-toggle="dropdown">
                    全部分类<span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">女士钱包</a></li>
                    <li><a href="#">双肩包</a></li>
                    <li><a href="#">...</a></li>
                </ul>
            </div>
            <form action="search">
                <input type="text" id="searchInput" name="keyWord" class="form-control"
                       placeholder="请输入您想查询的关键字"/>
                    <span class="input-group-addon searchCommit">
                        <img class="link" id="searchCommmit" src="resources/css/images/searchButton.png"/>
                    </span>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>