<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res-build/h5/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res-build/h5/css/css.css">
</head>

<body>
    <div class="newsdetail">
        <div class="news_title">
            <h3>${vo.title}</h3>
            <p class="time">${time}</p>        </div>
        <div class="news_content">
            ${vo.introduce}
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/res-build/h5/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res-build/h5/js/public.js"></script>

</body>

</html>
