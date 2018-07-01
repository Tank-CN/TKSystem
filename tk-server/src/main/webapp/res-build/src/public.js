/**
 * Created by feiwen8772 on 15/4/28.
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               佛祖保佑         永无BUG
 *
 */
define(function (require, exports, module) {
    var Sidebar = require("./sidebar.js");
    //初始化导航
    Sidebar.init();
    var $W = $(window);
    var Util = require("./utiliy.js");
    var $pagesidebar = $("#page-sidebar");
    var $pageContent = $("#page-content");
    var windowW = Util.getViewPort().width;
    var pageUtil = {
        init: function () {
            this.bind();
        },
        bind: function () {
            var headerH = $('.page-header').outerHeight();
            var footerH = $('.page-footer').outerHeight();

            $W.on("resize.contentH", function () {
                if (Util.getViewPort().width >= 992) {
                    var available_height = Util.getWindowSize().height - footerH - headerH;
                    //重置宽度
                    windowW = Util.getViewPort().width;
                    //内容高度小于可视高度
                    if ($pageContent.height() < available_height) {
                        $pageContent.attr('style', 'min-height:' + available_height + 'px');
                    }
                    $W.trigger("scroll");
                }
            }).trigger("resize.contentH");

            $W.scroll(function () {
                if (windowW >= 992) {
                    var available_height = Util.getWindowSize().height - footerH - headerH;
                    if ($pagesidebar.outerHeight() <= available_height) {
                        $pagesidebar.addClass("sidebar-fixed")
                    } else {
                        $pagesidebar.removeClass("sidebar-fixed")
                    }
                }
            });
            /*var available_height = Util.getViewPort().height - $('.page-footer').outerHeight() - $('.page-header').outerHeight();
             if($pagesidebar.outerHeight()>available_height){
             $pagesidebar.removeClass("sidebar-fixed")
             }*/
        }
    };
    pageUtil.init()
});
