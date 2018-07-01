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
    签约信息
    <small>修改签约</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="${ctx}/admin/bussiness/sign?pcode=3&subcode=2">签约维护</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">修改签约</a></li>
       
    </ul>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data">
             <!--    <input type="hidden" name="provinceid"/>
                <input type="hidden" name="provincename"/>
                 <input type="hidden" name="districtid"/>
                 <input type="hidden" name="districtname"/>
                  <input type="hidden" name="cityid"/>
                  <input type="hidden" name="cityname"/>
                   <input type="hidden" name="streetid"/>
                   <input type="hidden" name="streetname"/>
                    <input type="hidden" name="villageid"/>
                     <input type="hidden" name="villagename"/>
                      <input type="hidden" name="orgid"/>
                       <input type="hidden" name="deptid"/>
 -->
                <div class="form-body">
                <div class="form-actions-topbox">
                        <div class="form-actions-fixtop">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="button" class="btn btn-success j-edit"><i class="iconfont">
                                        &#xe61c;</i>我要编辑
                                    </button>
                                    <button type="submit" class="btn btn-success j-save" style="display: none"><i
                                            class="iconfont">
                                        &#xe62c;</i>保存
                                    </button>
                                    <a href="${ctx}/admin/bussiness/sign?pcode=3&subcode=2&currentpage=${currentpage}"
                                       class="btn btn-default">返回</a>
                                    <%--<button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>
                                        我要编辑
                                    </button>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">用户姓名</label>

                        <div class="col-md-4">
                            <input type="text" id="name" name="name" placeholder="用户姓名" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">机构名称</label>

                        <div class="col-md-4">
                          <input type="text"  data-required="1" name="orgname"  placeholder="机构名称" class="form-control"  style="position:relative;" disabled>
                           <!--  <div id="tree_4" class="tree-demo" style=" width:550px;position: absolute;   padding: 8px; z-index: 9999;background: #fff;border: 1px solid #666;display: none;">
                                <div class="row">

                                    <div class="col-md-10">
                                        <div class="alert alert-warning j-tips" role="alert" style="padding: 5px;
                                        margin-bottom: 0"><span class="iconfont" style="font-size: 16px">&#xe615;</span>所属科室必须选择二级科室</div>
                                        </div>
                                    <div class="col-md-2" style="padding-top: 6px;"><span class="j-close-tree" ><span class="iconfont" style="font-size: 18px">&#xe61d;</span>关闭</span></div></div>
                                <div id="tree-con4" style="margin-top: 6px;"></div>
                            </div> -->
                            
                        </div>
                    </div>
                  
                    <div class="form-group">
                        <label class="control-label col-md-3">科室名称</label>

                        <div class="col-md-4">
                        <input type="text"  data-required="1" name="deptname"  placeholder="科室名称" class="form-control"  value="" disabled>
                             <!-- <input type="text"  data-required="1" name="orgdept"  placeholder="所属机构科室" class="form-control"  style="position:relative;" value="${orgname} ${pdeptname} ${deptname}">
                            <div id="tree_4" class="tree-demo" style=" width:550px;position: absolute;   padding: 8px; z-index: 9999;background: #fff;border: 1px solid #666;display: none;">
                                <div class="row">

                                    <div class="col-md-10">
                                        <div class="alert alert-warning j-tips" role="alert" style="padding: 5px;
                                        margin-bottom: 0"><span class="iconfont" style="font-size: 16px">&#xe615;</span>所属科室必须选择二级科室</div>
                                        </div>
                                    <div class="col-md-2" style="padding-top: 6px;"><span class="j-close-tree" ><span class="iconfont" style="font-size: 18px">&#xe61d;</span>关闭</span></div></div>
                                <div id="tree-con4" style="margin-top: 6px;"></div>
                            </div> -->
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">申请签约时间</label>

                        <div class="col-md-4">
                            <input type="text" id="applysigndate" name="applysigndate" placeholder="申请签约时间" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">签约时间</label>

                        <div class="col-md-4">
                            <input type="text" id="signdate" name="signdate" placeholder="签约时间" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">到期时间</label>

                        <div class="col-md-4">
                            <input type="text" id="expiredate" name="expiredate" placeholder="到期时间" class="form-control" disabled>
                        </div>
                    </div>
                  
                    <div class="form-group">
                        <label class="control-label col-md-3">签约医生ID</label>

                        <div class="col-md-4">
                            <input type="text" id="doctorid" name="doctorid" placeholder="签约医生ID" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">签约医生名称</label>

                        <div class="col-md-4">
                            <input type="text" id="doctorname" name="doctorname" placeholder="签约医生名称" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">签约机构ID</label>

                        <div class="col-md-4">
                            <input type="text" id="orgid" name="orgid" placeholder="签约机构ID" class="form-control" disabled>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">签约机构名称</label>

                        <div class="col-md-4">
                            <input type="text" id="orgname" name="orgname" placeholder="签约机构名称" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">申请解约时间</label>

                        <div class="col-md-4">
                            <input type="text" id="applycanceldate" name="applycanceldate" placeholder="申请解约时间" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">解约时间</label>

                        <div class="col-md-4">
                            <input type="text" id="canceldate" name="canceldate" placeholder="解约时间" class="form-control" disabled>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-3">状态</label>

                        <div class="col-md-4">
                            <select name="status" class="form-control" disabled>
                                <option value="0">未签约</option>
                                <option value="1">签约申请 </option>
                                <option value="2">签约成功</option>
                                <option value="3">签约失败</option>
                                <!--  <option value="05">医学出生证明</option> -->
                                <option value="4">解约申请 </option>
                                <option value="5">解约成功</option>
                            </select>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">申请审核时间</label>

                        <div class="col-md-4">
                            <input type="text" id="verifyapplydate" name="verifyapplydate" placeholder="申请审核时间" class="form-control" disabled>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">申请审核人</label>

                        <div class="col-md-4">
                            <input type="text" id="verifyapplyuser" name="verifyapplyuser" placeholder="申请审核人" class="form-control" disabled>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">解约审核时间</label>

                        <div class="col-md-4">
                            <input type="text" id="verifycancelddate" name="verifycancelddate" placeholder="解约审核时间" class="form-control" disabled>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">解约审核人</label>

                        <div class="col-md-4">
                            <input type="text" id="verifycancelduser" name="verifycancelduser" placeholder="解约审核人" class="form-control" disabled>
                        </div>
                    </div>
                    <!--  <div class="form-group">
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
                        <label class="control-label col-md-3">出生日期</label>

                        <div class="col-md-4">
                            <input type="text" id="birthdate" name="birthdate" placeholder="出生日期" class="form-control">
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label class="control-label col-md-3">医生介绍</label>

                        <div class="col-md-9" style="width: 50%">
                         
                            <textarea id="summernote" name="introduce" class="form-control" rows="3"></textarea>
                        </div>
                    </div> -->

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
                                <button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>我要编辑
                                </button>
                                <button type="submit" class="btn btn-success j-save" style="display: none"><i
                                        class="iconfont">
                                    &#xe62c;</i>保存
                                </button>
                                <a href="${ctx}/admin/bussiness/sign?pcode=3&subcode=2&currentpage=${currentpage}"
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
                <a href="${ctx}/admin/bussiness/sign?pcode=3&subcode=2&currentpage=${currentpage}"
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
<script type="text/javascript">
        var signId =${id},currentpage=${currentpage};
    </script>
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="sign-update.js"></script>

</content>
