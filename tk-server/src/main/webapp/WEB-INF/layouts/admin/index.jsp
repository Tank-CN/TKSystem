<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>

    <title>管理平台</title>
    <link href="${ctx}/res-build/css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!--[if lt IE 9]>
      <link href="${ctx}/res-build/res/bootstrap-3.3.2/css/ie8.min.css" rel="stylesheet">
    <![endif]-->
    <link href="${ctx}/res-build/css/layout.css" rel="stylesheet" type="text/css"/>
    <sitemesh:getProperty property="page.cssconfig"/>
    <script type="text/javascript" src="${ctx}/res-build/res/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/res-build/res/bootstrap-3.3.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/res-build/res/seajs-3.0.0/dist/sea.js"></script>
    <sitemesh:getProperty property="page.headerjsconfig"/>
</head>

<body>
<div id="page" class="page-header-fixed">
    <!-- B:page-header -->
    <div class="page-header clearfix header-fixed">
        <div class="page-header-inner">
            <div class="page-logo">
                <a href="${ctx}/admin/index">
                    管理平台
                </a>
            </div><!--<!--href="#page-sidebar-menu" aria-expanded="false" aria-controls="collapseExample" data-toggle="collapse" data-target=".navbar-collapse"-->
            <a class="sidebar-toggler" data-toggle="collapse" href="#page-sidebar" aria-expanded="false"
               aria-controls="page-sidebar"><i
                    class="iconfont">&#xe603;</i></a>
            <div class="top-menu">
                <ul class="nav top-tools">
                    <%--<li class="top-tools-item dropdown">
                        <a href="#" class="top-tools-item-a top-notification">
                            <i class="iconfont">&#xe600;</i>
                            <span class="tool-name">消息</span>
                            <span class="badge">4</span>
                        </a>

                        <div class="dropdown-menu dropdown-notification">
                            <div class="dropdown-menu-con "></div>
                        </div>
                    </li>--%>
                    <li class="top-tools-item dropdown top-userbox">
                        <a href="#" class="top-tools-item-a top-user">
                            <img alt="" class="img-circle hide1" src="${ctx}/res-build/img/avatar3_small.jpg">
                            <span class="username username-hide-on-mobile">${sessionScope.account.username}</span>
                            <i class="iconfont">&#xe606;</i>
                        </a><%--

                        <div class="dropdown-menu dropdown-notification">
                            <div class="dropdown-menu-con "></div>
                        </div>--%>
                    </li>
                    <li class="top-tools-item top-logout" id="top-logout">
                        <a href="${ctx}/logout" class="top-tools-item-a j-top-logout">
                            <i class="iconfont">&#xe61e;</i>
                            <span class="tool-name">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- E:page-header -->
    <!-- B:page-container -->
    <div class="page-container clearfix">
        <!-- B:page-sidebar -->
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar sidebar-fixed navbar-collapse collapse" id="page-sidebar"><!-- page-sidebar-in -->
                <div class="page-sidebar-menu" id="page-sidebar-menu" data-slide-speed="200">
                    <div class="page-sidebar-tit clearfix">
                        <h3>导航</h3>
                        <a href="#" class="sidebar-toggler"><i class="iconfont">&#xe603;</i>
                        </a>
                    </div>
                    <ul class="page-sidebar-menu-ul">
                    <c:forEach items="${sessionScope.modules}" var="vo" varStatus="status">
                    	<li class="menu-item <c:if test='${vo.code == pcode}'>open active</c:if>">
                            <a href="javascript:" class="menu-item-a">
                                <i class="iconfont">&#xe604;</i>
                                <span class="title">${vo.title}</span>
                                <span class="selected"></span>
                                <i class="iconfont arrow"></i>
                            </a>
                            <ul class="sub-menu sub-menu-hide" data-code="RMsg">
                                <c:forEach items="${vo.list}" var="subvo" varStatus="substatus">
	                                <li class="sub-menu-item <c:if test='${subvo.code==subcode}'>sub-active</c:if>">
	                                <a class="ajaxify" href='${ctx}${subvo.url}?pcode=${subvo.pcode}&subcode=${subvo.code}'>${subvo.title}</a>
	                                </li>
                            	</c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!-- E:page-sidebar -->
        <!-- B:page-content -->
        <div class="page-content-wrapper">
            <div class="page-content" id="page-content">
                <div class="page-content-body">
                <sitemesh:body/>
                </div>
            </div>
        </div>
        <!-- E:page-content -->
    </div>
    <!-- E:page-container -->
    <!-- B:page-footer -->
    <div class="page-footer clearfix">
        <div class="page-footer-inner">
            2015 © XX股份有限公司
        </div>
        <div class="page-footer-tools">
                <span class="go-top">
                    <i class="iconfont">&#xe602;</i>
                </span>
        </div>
    </div>
    <!-- E:page-footer -->
</div>
<script type="text/javascript">
    var ROOTPAth="${ctx}";
</script>

<sitemesh:getProperty property="page.jsconfig"/>
</body>

</html>


