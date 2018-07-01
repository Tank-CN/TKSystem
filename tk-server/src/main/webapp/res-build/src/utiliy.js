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
    var Utilitiy={
        getWindowSize: function () {
                    var $W = $(window);

                    return {
                        width: $W.width(),
                        height: $W.height()
                    }
                },
        getViewPort: function () {
                    var e = window, a = 'inner';
                    if (!('innerWidth' in window)) {
                        a = 'client';
                        e = document.documentElement || document.body;
                    }
                    return {
                        width: e[a + 'Width'],
                        height: e[a + 'Height']
                    }
                },
    };
    return Utilitiy;

});
