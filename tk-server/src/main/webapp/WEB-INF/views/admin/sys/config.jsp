<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css"/>
</content>

<body>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
    系统构建
    <small>构建信息</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li>
            <i class="iconfont ico-home">&#xe60a;</i>
            <a href="index.html">主页</a>
            <i class="iconfont ico-angle-right"> &#xe605;</i>
        </li>
        <li>
            <a href="#">系统构建</a>
            <i class="iconfont ico-angle-right">&#xe605;</i>
        </li>
        <li>
            <a href="#">构建信息</a>
        </li>
    </ul>
    <div class="page-bar-actions"><a class="btn btn-success btn-sm" href="${ctx}/admin/sys/configs">
            <i class="iconfont">&#xe632;</i>
            <span class="hidden-480">导出配置</span>
        </a></div>
</div>
<div class="row-line">
    <div class="row">
        <div class="col-md-6">
            <div class="portlet portlet-blue-steel portlet-module" id="regionCon">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        项目所属地区维护
                    </div>
                    <div class="tools">
                        <button type="button" class="btn btn-default btn-xs j-modifyModule-btn"
                                data-toggle="modal" <%--data-target="#updateRegionModal"--%>
                                title="修改" id="">
                            <span class="iconfont iconfont-xs">&#xe61c;</span>
                            修改
                        </button>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div class="region-index">
                        <dl class="dl-horizontal">
                            <c:forEach items="${region}" var="vo" begin="0" end="2" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.index==0}">
                                        <dt>省/自治区/直辖市：</dt>
                                        <dd class="j-province" data-id="${vo.text}">${vo.memo}</dd>
                                    </c:when>
                                    <c:when test="${status.index==1}">
                                        <dt>地级市/自治区/区：</dt>
                                        <dd class="j-city" data-id="${vo.text}">${vo.memo}</dd>
                                    </c:when>
                                    <c:when test="${status.index==2}">
                                        <dt>县级市/县/区：</dt>
                                        <dd class="j-county" data-id="${vo.text}">${vo.memo}</dd>
                                    </c:when>
                                    <c:otherwise>无</c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-6">
            <div class="portlet portlet-green-haze portlet-module" id="counsel-con">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        分诊咨询
                    </div>
                    <div class="tools">
                        <%--<button type="button" class="btn btn-default btn-xs" data-toggle="modal" title="添加根模块"
                            id="">添加根模块
                    </button>--%>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div style="padding: 0 10px">
                        <table class="table table-striped">

                            <tbody>
                            <c:forEach items="${reception}" var="vo" begin="0" varStatus="status">
                                <tr data-id="${vo.id}" data-title="${vo.memo}" data-val="${vo.text}">
                                    <th scope="row">${vo.memo}</th>
                                    <td>
                                        <span class="j-counsel-txt">${vo.text}</span>
                                    </td>

                                    <td>
                                        <button type="button" class="btn btn-default btn-xs j-edit">
                                            <span class="iconfont iconfont-xs">&#xe61c;</span>
                                            修改
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="portlet portlet-purple-wisteria portlet-module" id="">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        支付宝支付配置
                    </div>
                    <div class="tools">
                        <button type="button" class="btn btn-default btn-xs j-modifyModule-btn" data-toggle="modal"
                                data-target="#alipayConModal"
                                title="修改" id="">
                            <span class="iconfont iconfont-xs">&#xe61c;</span>
                            修改
                        </button>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div class="alipaycon-index" id="alipaycon">
                        <dl class="dl-horizontal">
                            <c:forEach items="${alipay}" var="vo" begin="0" varStatus="status">
                                <c:choose>
                                    <c:when test="${vo.code=='alipay_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-alipay-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='alipay_partner'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-alipay-partner">${vo.text}</dd>
                                    </c:when>
                                    <c:when test="${vo.code=='alipay_seller'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-alipay-seller">${vo.text}</dd>
                                    </c:when>
                                    <c:when test="${vo.code=='alipay_rsa_private'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-alipay-rsa-private">
                                            <button data-val="${vo.text}" class="btn btn-default btn-xs j-edit"
                                                    data-toggle="modal" data-target="#alipayRsaPrivateModal">
                                                <span class="iconfont iconfont-xs">&#xe62d;</span>
                                                查看密钥
                                            </button>
                                        </dd>
                                    </c:when>
                                    <c:otherwise>无</c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-6">
            <div class="portlet portlet-red-sunglo portlet-module" id="">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        APP首页模块配置
                    </div>
                    <div class="tools">
                        <button type="button" class="btn btn-default btn-xs j-modifyModule-btn" data-toggle="modal"
                                data-target="#appIndexconModal" title="修改"><span
                                class="iconfont iconfont-xs">&#xe61c;</span>
                            修改
                        </button>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div class="app-indexcon" id="appindexcon">
                        <dl class="dl-horizontal">
                            <c:forEach items="${index}" var="vo" begin="0" varStatus="status">
                                <c:choose>
                                    <c:when test="${vo.code=='pubappoint_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-pubappoint-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='implementappoint_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-implementappoint-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='childrenappoint_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-childrenappoint-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='reception_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-reception-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='report_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-report-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                    <c:when test="${vo.code=='seek_open'}">
                                        <dt>${vo.memo}：</dt>
                                        <dd class="j-seek-open" data-val="${vo.text}">
                                            <c:if test="${vo.text=='1'}">开启</c:if>
                                            <c:if test="${vo.text=='0'}">关闭</c:if>
                                        </dd>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="portlet portlet-green-haze portlet-module" id="">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        我的模块配置
                    </div>
                    <div class="tools">
                        <button type="button" class="btn btn-default btn-xs j-modifyModule-btn" data-toggle="modal"
                                data-target="#appMyConModal" title="修改" id="">
                            <span class="iconfont iconfont-xs">&#xe61c;</span>
                            修改
                        </button>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div class="appMycon-index" id="appmycon">
                        <dl class="dl-horizontal">
                            <c:forEach items="${mine}" var="vo" begin="0" varStatus="status">
                                <c:choose>
                                <c:when test="${vo.code=='myrecord_open'}">
                                    <dt>${vo.memo}：</dt>
                                    <dd class="j-myrecord-open" data-val="${vo.text}">
                                        <c:if test="${vo.text=='1'}">开启</c:if>
                                        <c:if test="${vo.text=='0'}">关闭</c:if>
                                    </dd>
                                </c:when>
                                <c:when test="${vo.code=='family_open'}">
                                    <dt>${vo.memo}：</dt>
                                    <dd class="j-family-open" data-val="${vo.text}">
                                        <c:if test="${vo.text=='1'}">开启</c:if>
                                        <c:if test="${vo.text=='0'}">关闭</c:if>
                                    </dd>
                                </c:when>
                                <c:when test="${vo.code=='serverrecord_open'}">
                                    <dt>${vo.memo}：</dt>
                                    <dd class="j-serverrecord-open" data-val="${vo.text}">
                                        <c:if test="${vo.text=='1'}">开启</c:if>
                                        <c:if test="${vo.text=='0'}">关闭</c:if>
                                    </dd>
                                </c:when>
                                <c:when test="${vo.code=='sign_open'}">
                                    <dt>${vo.memo}：</dt>
                                    <dd class="j-sign-open" data-val="${vo.text}">
                                        <c:if test="${vo.text=='1'}">开启</c:if>
                                        <c:if test="${vo.text=='0'}">关闭</c:if>
                                    </dd>
                                </c:when>
                                <c:when test="${vo.code=='monitor_open'}">
                                    <dt>${vo.memo}：</dt>
                                    <dd class="j-monitor-open" data-val="${vo.text}">
                                        <c:if test="${vo.text=='1'}">开启</c:if>
                                        <c:if test="${vo.text=='0'}">关闭</c:if>
                                    </dd>
                                </c:when>
                                </c:choose>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="portlet portlet-blue-steel  portlet-module" id="">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont"></i>
                        外网API地址
                    </div>
                    <div class="tools">
                        <button type="button" class="btn btn-default btn-xs j-modifyModule-btn" data-toggle="modal"
                                data-target="#appAPIconModal" title="修改" id="">
                            <span class="iconfont iconfont-xs">&#xe61c;</span>
                            修改
                        </button>
                    </div>
                </div>
                <div class="portlet-body" style="height: 206px">
                    <div class="app-apicon" id="appapicon">
                        <dl class="dl-horizontal">
                            <c:forEach items="${api}" var="vo" begin="0" varStatus="status">
                                <dt>${vo.memo}：</dt>
                                <dd class="j-apiurl">
                                    ${vo.text}
                                </dd>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE HEADER-->

<div class="modal fade" id="appAPIconModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">外网API地址管理</h4>
            </div>
            <form class="form-horizontal" id="appAPIconForm">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">外网API地址</label>

                                <div class="col-md-7">
                                    <input type="text" name="apiurl_text" class="form-control"
                                                                               placeholder="外网API地址，http://...">
                                </div>
                            </div>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="appMyConModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">我的模块配置</h4>
            </div>
            <form class="form-horizontal" id="appMyconForm">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">我的档案</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="myrecord_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="myrecord_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">家庭管理</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="family_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="family_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">服务记录</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="serverrecord_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="serverrecord_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">签约信息</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="sign_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="sign_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">健康监测</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="monitor_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="monitor_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="updateRegionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">项目所属地区</h4>
            </div>
            <form class="form-horizontal" id="updateRegionForm">
                <input type="hidden" id="selProvinceTxt-Region" name="provincetext"/>
                <input type="hidden" id="selCityTxt-Region" name="citytext"/>
                <input type="hidden" id="selAreaTxt-Region" name="countytext"/>

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">省/自治区/直辖市</label>

                                <div class="col-md-7">
                                    <select name="provinceid" id="selProvince-Region" class="form-control">
                                        <option value=''>请选择省/自治区/直辖市...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">地级市/自治区/区</label>

                                <div class="col-md-7">
                                    <select name="cityid" id="selCity-Region" class="form-control">
                                        <option value=''>请选择地级市/自治区/区...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">县级市/县/区</label>

                                <div class="col-md-7">
                                    <select name="countyid" id="selCounty-Region" class="form-control">
                                        <option value=''>请选择县级市/县/区...</option>
                                    </select>
                                </div>
                            </div>

                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="formSaveBtn">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="appIndexconModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">APP首页模块配置</h4>
            </div>
            <form class="form-horizontal" id="appIndexconForm">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">预约挂号</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="pubappoint_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="pubappoint_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">计免预约</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="implementappoint_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="implementappoint_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">儿保预约</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="childrenappoint_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="childrenappoint_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">分诊咨询</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="reception_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="reception_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">报告查询</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="report_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="report_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">便捷寻医</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="seek_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="seek_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="alipayConModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">支付宝支付功能设置</h4>
            </div>
            <form class="form-horizontal" id="alipayConForm">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">支付宝支付功能</label>

                                <div class="col-md-7">
                                    <label class="radio-inline">
                                        <input type="radio" name="alipay_open_text"
                                               value="1"> 开启
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="alipay_open_text"
                                               value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">支付宝用户名</label>

                                <div class="col-md-7">
                                    <input type="text" name="alipay_partner_text" data-required="1"
                                           class="form-control j-disabled-hook"
                                           placeholder="支付宝用户名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">支付宝帐号</label>

                                <div class="col-md-7">
                                    <input type="text" name="alipay_seller_text" data-required="1"
                                           class="form-control j-disabled-hook"
                                           placeholder="支付宝帐号为邮箱/手机号等">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">支付宝密钥</label>

                                <div class="col-md-7">
                                        <textarea class="form-control j-disabled-hook" rows="10" placeholder="支付宝密钥"
                                                  name="alipay_rsa_private_text"></textarea>
                                </div>
                            </div>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="alipayRsaPrivateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">支付宝密钥</h4>
            </div>

            <div class="modal-body">
                <div class="portlet-body " style="word-break:break-all;">
                </div>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- END PAGE HEADER-->
<div class="modal fade" id="updateCounselModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    分诊咨询-
                    <span class="j-counsel-title"></span>
                    修改
                </h4>
            </div>
            <form class="form-horizontal" id="updateCounselForm">
                <input type="hidden" name="id"/>

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <span class="j-counsel-title"></span>
                                </label>

                                <div class="col-md-7">
                                    <input type="text" class="form-control" placeholder="名称" name="text">
                                </div>
                            </div>

                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</body>

<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="build.js"></script>
</content>
