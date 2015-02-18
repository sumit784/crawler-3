<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="images">
    <table>
        <tr ng-repeat="snapshotGroup in snapshots">
            <td ng-repeat="snapshot in snapshotGroup">
                <div class="boxShadow">
                    <div class="image">
                        <a href="detail?id={{snapshot.id}}" target="_blank">
                            <img class="link" ng-src="{{snapshot.src}}"/>
                        </a>
                    </div>
                    <div class="description">
                        <a href="detail?id={{snapshot.id}}" class="noLineAnchor" target="_blank">
                            {{snapshot.description}}
                        </a>
                    </div>
                    <div class="price">
                        ￥{{snapshot.price}}
                    </div>
                    <div class="branch">
                        <a href="shoppe?id={{snapshot.branch.id}}" target="_blank">
                            <img class="link" ng-src="{{snapshot.branch.url}}"/>
                        </a>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
<div class="loading">
    <span>加载中，请稍候...</span>
</div>
