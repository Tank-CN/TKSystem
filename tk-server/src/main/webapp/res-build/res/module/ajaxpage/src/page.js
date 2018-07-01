define(function (require, exports, module) {


    var f = function () {},
        _defaults = {
            pageName: 'page', //当前页数名称
            pageClass: 'ajaxpage-wrapper', //分页css class
            pageHalf: 2,
            tpl: {
                page: '<a href="#" class="page">{page}</a>',
                current: '<span href="#" class="page current">{page}</span>',
                next: '<a href="#" class="page"><span class="iconfont">&#xe605;</span></a>',
                prev: '<a href="#" class="page"><span class="iconfont">&#xe608;</span></a>',
                ellipsis: '<span class="ellipsis">...</span>',
                go: '<form class="form">跳转到第<input type="text" class="num" />页<input type="submit" class="btn btn-default btn-sm" value="go" /></form>'
            }
        },
        util = {
            render: function (obj) {
                var half = parseInt(obj.config.pageHalf, 10) || 2,
                    i,
                    html = [],
                    tpl = obj.config.tpl,
                    pageHolder = obj.config.pageWrapper,
                    totalPage = obj.total,
                    cp = obj.current;

                function pushHtml(str, page) {
                    if (str) {
                        html.push(util.replace(str, {page:page}));
                    }
                }
                pushHtml(tpl.prev);

                if (cp > half + 2) {
                    pushHtml(tpl.page, 1);
                    pushHtml(tpl.ellipsis);
                    for (i = cp - half; i < cp; i++) {
                        pushHtml(tpl.page, i, i);
                    }
                } else {
                    for (i = 1; i < cp; i++) {
                        pushHtml(tpl.page, i);
                    }
                }
                pushHtml(tpl.current, cp);

                if (cp + half + 1 < totalPage) {
                    for (i = cp + 1; i <= cp + half; i++) {
                        pushHtml(tpl.page, i);
                    }
                    pushHtml(tpl.ellipsis);
                } else {
                    for (i = cp + 1; i < totalPage; i++) {
                        pushHtml(tpl.page, i);
                    }
                }
                if (cp < totalPage) {
                    pushHtml(tpl.page, totalPage);
                }

                pushHtml(tpl.next);
                pushHtml(tpl.go);

                pageHolder.html(html.join(''));
            },
            replace: function (str, obj) {
                var reg = /\{[^\}]+\}/g;
                str = str.replace(reg, function (a) {
                    a = a.slice(1, -1);
                    return obj[a] || '';
                });
                return str;
            }
        };

    function Page(options) {
        this.config = $.extend({}, _defaults);
        $.extend(this.config.tpl, options.tpl);
        delete options.tpl;
        $.extend(this.config, options);

        this.current = 1; //当前页数
        this._idle = true;
        this.config.pageWrapper = $(this.config.pageWrapper);
        this.config.pageWrapper.addClass(this.config.pageClass);

        if ($.isFunction(this.config.ajax.data)) {
            this._getAjaxData = this.config.ajax.data;
        }

        this._init();
        this._fixTPL();
    }
    Page.prototype = {
        _init: function () {
            var self = this,
                ajax = self.config.ajax,
                _success = ajax.success || f,
                _complete = ajax.complete || f;

            ajax.complete = function () {
                //解锁
                self._idle = true;
                _complete.apply(self, arguments);
            };
            ajax.success = function (res) {
                var getTotalPage = self.config.getTotalPage;
                //解锁
                self._idle = true;
                //获取总页数
                if ($.isFunction(getTotalPage)) {
                    self.total = getTotalPage(res);
                } else {
                    self.total = res.data.totalPage;
                }

                //返回false时，说明请求出错。需要隐藏分页组件
                if (_success.apply(self, arguments) !== false) {
                    self.config.pageWrapper.show();
                    util.render(self);
                } else {
                    self.config.pageWrapper.hide();
                }
            };
            ajax.cache = false;

            this._bind();
        },
        _fixTPL: function () {
            var reg = /^<[^>]*/,
                tpl = this.config.tpl,
                p, str;

            function _fix(str, key) {
                return str.replace(reg, function (a) {
                    return a + ' data-page="' + key + '"';
                });
            }

            for (var p in tpl) {
                str = tpl[p];
                if (tpl.hasOwnProperty(p) && str && str.indexOf('data-page') === -1) {
                    switch (p) {
                        case 'next':
                            str = _fix(str, 'next');
                            break;
                        case 'prev':
                            str = _fix(str, 'prev');
                            break;
                        case 'page':
                            str = _fix(str, '{page}');
                            break;
                        case 'current':
                            str = _fix(str, '{page}');
                            break;
                    }
                    tpl[p] = str;
                }
            }
        },
        _bind: function () { //事件绑定
            var self = this;

            //分页单击事件
            this.config.pageWrapper.on('click','[data-page]',  function (ev) {
                var target = $(this),
                    page = target.data('page');

                if (page) {
                    ev.preventDefault();
                }
                switch (page) {
                    case 'next':
                        self.next();
                        break;
                    case 'prev':
                        self.prev();
                        break;
                    default:
                        self.go(page);
                        break;
                }
            });

            //跳转到第N页事件
            this.config.pageWrapper.on('submit', 'form',  function (ev) {
                ev.preventDefault();
                var page = $('input[type=text]', this).val();

                self.go(page);
            });
        },
        resetgoto:function (page) { //重置
            this.current = page;
            this.request();
        },
        reset: function () { //重置
            this.current = 1;
            this.request();
        },
        go: function (page) { //跳转到第N页
            page = parseInt(page, 10);
            if (page <= this.total && page >= 1) {
                this.current = page;
                this.request();
            }
        },
        next: function () { //下一页
            this.current = this.current + 1;
            if (this.current > this.total) {
                this.current = this.total;
            }
            this.request();
        },
        prev: function () { //上一页
            this.current = this.current - 1;
            if (this.current < 1) {
                this.current = 1;
            }
            this.request();
        },
        request: function () { //请求数据
            if (this._idle) {
                this._idle = false;
                if (this._getAjaxData) {
                    this.config.ajax.data = this._getAjaxData();
                }
                this.config.ajax.data[this.config.pageName] = this.current;
                $.ajax(this.config.ajax);
            }
        }
    };

    return Page;
});
