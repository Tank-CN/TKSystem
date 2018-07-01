/**
 * Created by feiwen8772 on 15/6/4.
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

    var $win = $(window), st;
    var $element, elT, elH,elW;
    var fixedObj = {
        init: function ($el) {
            $element = $el;
            elT = $element.offset().top;
            elH = $element.outerHeight();
            elW=$element.outerWidth();
            $element.closest(".form-actions-topbox").height(elH);
            $element.width(elW);
            this.bind();
        },
        bind: function () {
            var self = this;
            var compute = function () {
                st = $win.scrollTop();
                if (st > elT) {
                    $element.css({
                        "position": "fixed",
                        "top": "46px",
                        "bottom": "auto"
                    })
                } else {

                    $element.css({
                        "position": "static",
                        "top": "auto",
                        "bottom": 0
                    })
                }
            };
            compute();
            $win.on("scroll.fixed", function () {
                compute();
            });
            $win.on('resize.fixed', function () {
                compute();
            });
        }
    };


    exports.scroll = function ($el) {
        fixedObj.init($el)
    }

});