<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.css" rel="stylesheet">
    <link href="${ctx}/res-build/css/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
     <link rel="stylesheet" href="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/css/jquery-ui-1.10.3.custom.css" />
    <!--[if lt IE 9]>
    <link href="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/css/jquery.ui.1.10.3.ie.css" rel="stylesheet" />
    <![endif]-->
</content>
<content tag="headerjsconfig">
  
    <script src="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.js"></script>
    <script src="${ctx}/res-build/res/plugin/bootstrap-summernote/lang/summernote-zh-CN.js"></script>
  <script type="text/javascript" src="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/jquery-ui-1.10.3.custom.min.js"></script>
</content>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

<body>
<h3 class="page-title">
    居民信息
    <small>新增居民</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="${ctx}/admin/bussiness/resident?pcode=3&subcode=1">居民维护</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">新增居民</a></li>
    </ul>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="provinceid"/>
                <input type="hidden" name="provincename"/>
                <input type="hidden" name="cityid"/>
                <input type="hidden" name="cityname"/>
                 <input type="hidden" name="districtid"/>
                 <input type="hidden" name="districtname"/>
                   <input type="hidden" name="streetid"/>
                   <input type="hidden" name="streetname"/>
                    <input type="hidden" name="villageid"/>
                     <input type="hidden" name="villagename"/>
                      <input type="hidden" name="orgid"/>
                       <input type="hidden" name="deptid"/>

                <div class="form-body">
                     <div class="form-group">
                        <label class="control-label col-md-3">真实姓名</label>

                        <div class="col-md-4">
                            <input type="text" id="realname" name="realname" placeholder="真实姓名" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">用户昵称</label>

                        <div class="col-md-4">
                            <input type="text" id="nickname" name="nickname" placeholder="用户昵称" class="form-control">
                        </div>
                    </div>
                   <div class="form-group">
                        <label class="control-label col-md-3">国籍</label>

                        <div class="col-md-4">
                            <select name="nationality" class="form-control">
                                <option value="1">中国大陆</option>
                                <option value="2">中国港澳</option>
                                <option value="3">中国台湾</option>
                                <option value="9">海外同胞</option>
                              
                            </select>
                        </div>
                    </div>
                  
                    
                    <div class="form-group">
                        <label class="control-label col-md-3">证件类别</label>

                        <div class="col-md-4">
                            <select name="cardtype" class="form-control">
                                <option value="1">身份证</option>
                                <option value="2">军官证</option>
                                <option value="3">护照</option>
                                <option value="4">医学出生证明</option>
                                <!--  <option value="05">医学出生证明</option> -->
                                <option value="6">港澳通行证</option>
                                <option value="7">台湾通行证</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">证件号码</label>

                        <div class="col-md-4">
                            <input type="text" id="idcard" name="idcard" placeholder="证件号码" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">民族</label>

                        <div class="col-md-4">
                            <input type="text" id="nation" name="nation" placeholder="民族" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">出生地</label>

                        <div class="col-md-4">
                            <input type="text" id="birthplace" name="birthplace" placeholder="出生地" class="form-control">
                        </div>
                    </div>
                      <div class="form-group">
                        <label class="control-label col-md-3">职业</label>

                        <div class="col-md-4">
                            <input type="text" id="occupation" name="occupation" placeholder="职业" class="form-control">
                        </div>
                    </div>
                      <div class="form-group">
                        <label class="control-label col-md-3">工作单位</label>

                        <div class="col-md-4">
                            <input type="text" id="company" name="company" placeholder="工作单位" class="form-control">
                        </div>
                    </div>
                      <div class="form-group">
                        <label class="control-label col-md-3">健康卡号</label>

                        <div class="col-md-4">
                            <input type="text" id="health_sn" name="health_sn" placeholder="健康卡号" class="form-control">
                        </div>
                    </div>
                      <div class="form-group">
                        <label class="control-label col-md-3">医保卡号</label>

                        <div class="col-md-4">
                            <input type="text" id="ehr_sn" name="ehr_sn" placeholder="医保卡号" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">所属地区</label>

                        <div class="col-md-4">
                            <input type="text" data-required="1" name="regionname" placeholder="所属地区"
                                   class="form-control" style="position:relative;">

                            <%--<div id="tree_3" class="tree-demo col-md-10"
                                 style="position: absolute;z-index: 9999;background: #fff;border: 1px solid #666;display: none;"></div>--%>
                            <div id="tree_3" class="tree-demo col-md-10" style="position:
                            absolute; padding-right: 68px; z-index: 9999;background: #fff;border: 1px solid #666;display: none;">
                                                            <span class="j-close-tree"
                                                                  style="position: absolute; right: 10px; cursor: pointer"><span
                                                                    class="iconfont" style="font-: 18px">
                                                                &#xe61d;</span>关闭</span>
                                                            <div id="tree-con"></div>
                                                        </div>

                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">电话</label>

                        <div class="col-md-4">
                            <input type="text" id="mobile" name="mobile" placeholder="电话" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">详细地址</label>

                        <div class="col-md-4">
                            <input type="text" id="detailadr" name="detailadr" placeholder="详细地址" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">邮箱</label>

                        <div class="col-md-4">
                            <input type="text" id="email" name="email" placeholder="邮箱" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">地址</label>

                        <div class="col-md-4">
                            <input type="text" id="address" name="address" placeholder="地址" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">性别</label>

                        <div class="col-md-4">
                            <select name="sexcode" class="form-control">
                                <option value="1">男</option>
                                <option value="2">女</option>
                               
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">出生日期</label>

                        <div class="col-md-4">
                            <input type="text" id="birthdate" name="birthdate" placeholder="出生日期" class="form-control">
                        </div>
                    </div>
                   
                     <div class="form-group">
                        <label class="control-label col-md-3">头像</label>

                        <div class="col-md-4">
                            <div id="selImg" class="form-control"><i class="iconfont">&#xe626;</i>
                                <span>选择图片</span></div>
                            <input type="file" id="file" name="file" class="" style="display: none">
                        </div>
                        <div class="col-md-4" style="line-height: 34px; color: #888888;">
                            建议图片尺寸：1200x800(px)
                        </div>
                    </div>

                    

              <!--       <div class="form-group">
                        <label class="control-label col-md-3">图片地址</label>

                        <div class="col-md-4">
                            <div id="selImg" class="form-control"><i class="iconfont">&#xe626;</i>
                                <span>选择图片</span></div>
                            <input type="file" id="file" name="file" class="">
                        </div>
                        <div class="col-md-4" style="line-height: 34px; color: #888888">
                            建议图片尺寸：1200x800(px)
                        </div>
                    </div>
 -->

                   


                  

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn btn-success"><i class="iconfont">&#xe62c;</i>保存
                                </button>
                                <a href="${ctx}/admin/bussiness/resident?pcode=3&subcode=1"
                                   class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div>
    <input type="hidden" id="webBasePath" value="${ctx}">
</div>

<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">

            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-success">
                    <div class="dialogtip-icon iconfont">&#xe614;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作成功</h4>

                        <div class="dialogtip-msg">数据添加成功</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="${ctx}/admin/bussiness/resident?pcode=3&subcode=1"
                   class="btn btn-success">返回列表</a>
                <a href="#" class="btn btn-danger j-modal-closebtn">关闭</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="modal-box-error" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">

            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-error">
                    <div class="dialogtip-icon iconfont">&#xe616;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作失败</h4>

                        <div class="dialogtip-msg">请上传gif|jpg|jpeg|png格式的图片</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="ajax_fail" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-warning">
                    <div class="dialogtip-icon iconfont">&#xe615;</div>
                        <div class="dialogtip-con">
                            <h4 class="dialogtip-tit" style="margin-top: 12px;">保存失败</h4>
                        </div>
                </div>
            </div>
            <div class="modal-footer">        
                <a href="#" class="btn btn-danger j-modal-closebtn" data-dismiss="modal">关闭</a>
            </div>
            </div>
    </div>
</div>
</body>

<content tag="jsconfig">

    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="resident-add.js"></script>

</content>
