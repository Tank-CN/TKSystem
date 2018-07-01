/**
 * Created by feiwen8772 on 15/4/24.
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
    var Util = require("./utiliy.js");
    var $page = $("#page");
    var $sidebar = $("#page-sidebar-menu");
    var $pagesidebar = $("#page-sidebar");
    var $pageContent = $("#page-content");
    var $W = $(window);
    var Sidebar = {
        init: function () {
            this.bind();
            this.getNav();
        },
        bind: function () {
            //切换 sidebar toggler
            $sidebar.on("click", ".sidebar-toggler", function (event) {
                event.preventDefault();
                if ($page.hasClass("page-sidebar-closed")) {
                    $page.removeClass("page-sidebar-closed");
                    $sidebar.removeClass("page-sidebar-menu-closed");
                    //只显示OPEN的子菜单
                    $sidebar.find(".menu-item:not(.open)").find(".sub-menu").hide()
                } else {
                    $page.addClass("page-sidebar-closed");
                    $sidebar.addClass("page-sidebar-menu-closed");


                }

            });
            /*$sidebar.on('mouseenter', function () {
             if ($page.hasClass('page-sidebar-closed')) {
             $(this).removeClass('page-sidebar-menu-closed');
             }
             }).on('mouseleave', function () {
             if ($page.hasClass('page-sidebar-closed')) {
             $(this).addClass('page-sidebar-menu-closed');
             }
             });*/
            var slideSpeed = parseInt($sidebar.data("slide-speed"));

            /* parent.children('li.open').children('a').children('.arrow').removeClass('open');
             parent.children('li.open').children('.sub-menu:not(.always-open)').slideUp(slideSpeed);
             //sidebar menu toggler*/
            //处理 sidebar fixed
            var doFixed = function () {
                /*var available_height = Util.getViewPort().height - $('.page-footer').outerHeight() - $('.page-header').outerHeight();
                 if ($pagesidebar.outerHeight() > available_height) {
                 $pagesidebar.removeClass("sidebar-fixed")
                 } else {
                 $pagesidebar.addClass("sidebar-fixed")
                 }*/
                //重置内容区域最小高度
                if (Util.getViewPort().width >= 992) {
                    var pagesidebarH = $pagesidebar.height();
                    if (pagesidebarH > $pageContent.height()) {
                        $pageContent.height(pagesidebarH)
                    } else {
                        $pageContent.height("auto")
                    }
                }
            };
            $sidebar.on("click", ".menu-item-a", function (event) {
                event.preventDefault();
                //关闭状态关闭所有子菜单
                if ($page.hasClass("page-sidebar-closed")) {

                    return false;
                }
                var $this = $(this);
                var $item = $this.closest(".menu-item");
                var $subMenu = $item.find(".sub-menu");
                //todo:重置内容区域最小高度
                /*var pagesidebarH=$pagesidebar.height();
                 if(pagesidebarH>$pageContent.height()){
                 $pageContent.height(pagesidebarH)
                 }*/

                if ($item.hasClass("open")) {
                    $item.removeClass("open");
                    //$subMenu.slideUp(slideSpeed, doFixed);

                } else {
                    //var $openMenu = $sidebar.find(".open");
                    //var $openSubMenu=$openMenu.find(".sub-menu:not(.always-open)");
                    /*var $openSubMenu = $openMenu.find(".sub-menu");
                     $openSubMenu.slideUp(slideSpeed);*/
                    //$openMenu.removeClass("open");
                    $item.addClass("open");
                    //$subMenu.slideDown(slideSpeed, doFixed);
                    //doFixed()
                }
                doFixed();

            });
        },
        getNav: function () {
        }
    };
    return Sidebar;

});
