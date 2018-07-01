/**
 * Created by feiwen8772 on 15/5/4.
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
/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/
define(function (require, exports, module) {

    var Datatable = function () {

        var tableOptions; // main options
        var dataTable; // datatable object
        var table; // actual table jquery object
        var tableContainer; // actual table container object
        var tableWrapper; // actual table wrapper jquery object
        var tableInitialized = false;
        var ajaxParams = {}; // set filter mode
        var the;

        var countSelectedRecords = function () {
            var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
            var text = tableOptions.dataTable.language.metronicGroupActions;
            if (selected > 0) {
                $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
            } else {
                $('.table-group-actions > span', tableWrapper).text("");
            }
        };

        return {

            //main function to initiate the module
            init: function (options) {

                if (!$().dataTable) {
                    return;
                }

                the = this;

                // default settings
                options = $.extend(true, {
                    src: "", // actual table
                    filterApplyAction: "filter",
                    filterCancelAction: "filter_cancel",
                    resetGroupActionInputOnSuccess: true,
                    loadingMessage: '数据载入中...',
                    dataTable: {
                        "dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>", // datatable layout
                        "lengthMenu": [10, 25, 50, 75, 100],
                        "pageLength": 10, // default records per page
                        "language": { // language settings
                            // metronic spesific
                            "metronicGroupActions": "已选择 _TOTAL_ 条记录:  ",
                            "metronicAjaxRequestGeneralError": "无法完成请求的操作，请检查网络连接是否正常",

                            // data tables spesific
                            "lengthMenu": "<span class='seperator'>|</span>每页 _MENU_ 条记录",
                            "info": "<span class='seperator'>|</span>总共获取 _TOTAL_ 条记录",
                            "infoEmpty": "找到 0 条记录",
                            "emptyTable": "该表无数据",
                            "zeroRecords": "没有找到匹配的记录",
                            "paginate": {
                                "previous": "上一页",
                                "next": "下一页",
                                "last": "尾页",
                                "first": "首页",
                                "page": "第",
                                "pageOf": "页 合计"
                            }
                        },

                        "orderCellsTop": true,
                        "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                            'orderable': false,
                            'class': 'heading',
                            'targets': [0, 1]
                        }],

                        "pagingType": "bootstrap_extended", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                        "autoWidth": false, // disable fixed width and enable fluid table
                        "processing": false, // enable/disable display message box on record load
                        "serverSide": true, // enable/disable server side ajax loading

                        "ajax": { // define ajax settings
                            "url": "", // ajax URL
                            "type": "POST", // request type
                            "timeout": 20000,
                            "data": function (data) { // add request parameters before submit
                                $.each(ajaxParams, function (key, value) {
                                    data[key] = value;
                                });
                                /*Metronic.blockUI({
                                    message: tableOptions.loadingMessage,
                                    target: tableContainer,
                                    overlayColor: 'none',
                                    cenrerY: true,
                                    boxed: true
                                });*/
                            },
                            "dataSrc": function (res) { // Manipulate the data returned from the server
                                if (res.customActionMessage) {
                                    /*Metronic.alert({
                                        type: (res.customActionStatus == 'OK' ? 'success' : 'danger'),
                                        icon: (res.customActionStatus == 'OK' ? 'check' : 'warning'),
                                        message: res.customActionMessage,
                                        container: tableWrapper,
                                        place: 'prepend'
                                    });*/
                                }

                                if (res.customActionStatus) {
                                    if (tableOptions.resetGroupActionInputOnSuccess) {
                                        $('.table-group-action-input', tableWrapper).val("");
                                    }
                                }

                                if ($('.group-checkable', table).size() === 1) {
                                    $('.group-checkable', table).attr("checked", false);
                                    $.uniform.update($('.group-checkable', table));
                                }

                                if (tableOptions.onSuccess) {
                                    tableOptions.onSuccess.call(undefined, the);
                                }

                                /*Metronic.unblockUI(tableContainer);*/

                                return res.data;
                            },
                            "error": function () { // handle general connection errors
                                if (tableOptions.onError) {
                                    tableOptions.onError.call(undefined, the);
                                }

                                /*Metronic.alert({
                                    type: 'danger',
                                    icon: 'warning',
                                    message: tableOptions.dataTable.language.metronicAjaxRequestGeneralError,
                                    container: tableWrapper,
                                    place: 'prepend'
                                });

                                Metronic.unblockUI(tableContainer);*/
                            }
                        },

                        "drawCallback": function (oSettings) { // run some code on table redraw
                            if (tableInitialized === false) { // check if table has been initialized
                                tableInitialized = true; // set table initialized
                                table.show(); // display table
                            }
                           /* Metronic.initUniform($('input[type="checkbox"]', table)); // reinitialize uniform checkboxes on each table reload*/
                            countSelectedRecords(); // reset selected records indicator
                        }
                    }
                }, options);

                tableOptions = options;

                // create table's jquery object
                table = $(options.src);
                tableContainer = table.parents(".table-container");

                // apply the special class that used to restyle the default datatable
                var tmp = $.fn.dataTableExt.oStdClasses;

                $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
                $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-small input-sm input-inline";
                $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";

                // initialize a datatable
                dataTable = table.DataTable(options.dataTable);

                // revert back to default
                $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
                $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
                $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

                // get table wrapper
                tableWrapper = table.parents('.dataTables_wrapper');

                // build table group actions panel
                if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                    $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                    $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
                }
                // handle group checkboxes check/uncheck
                $('.group-checkable', table).change(function () {
                    var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
                    var checked = $(this).is(":checked");
                    $(set).each(function () {
                        $(this).attr("checked", checked);
                    });
                    $.uniform.update(set);
                    countSelectedRecords();
                });

                // handle row's checkbox click
                table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function () {
                    countSelectedRecords();
                });

                // handle filter submit button click
                table.on('click', '.filter-submit', function (e) {
                    e.preventDefault();
                    the.submitFilter();
                });

                // handle filter cancel button click
                table.on('click', '.filter-cancel', function (e) {
                    e.preventDefault();
                    the.resetFilter();
                });


            },

            submitFilter: function () {
                //   alert();
                the.setAjaxParam("action", tableOptions.filterApplyAction);

                // get all typeable inputs
                $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function () {
                    the.setAjaxParam($(this).attr("name"), $(this).val());
                });

                // get all checkboxes
                $('input.form-filter[type="checkbox"]:checked', table).each(function () {
                    the.addAjaxParam($(this).attr("name"), $(this).val());
                });

                // get all radio buttons
                $('input.form-filter[type="radio"]:checked', table).each(function () {
                    the.setAjaxParam($(this).attr("name"), $(this).val());
                });

                dataTable.ajax.reload();
            },

            resetFilter: function () {
                $('textarea.form-filter, select.form-filter, input.form-filter', table).each(function () {
                    $(this).val("");
                });
                $('input.form-filter[type="checkbox"]', table).each(function () {
                    $(this).attr("checked", false);
                });
                the.clearAjaxParams();
                the.addAjaxParam("action", tableOptions.filterCancelAction);
                dataTable.ajax.reload();
            },
            reload: function () {
                dataTable.ajax.reload();
            },
            getSelectedRowsCount: function () {
                return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
            },

            getSelectedRows: function () {
                var rows = [];
                $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function () {
                    rows.push($(this).val());
                });

                return rows;
            },

            setAjaxParam: function (name, value) {
                ajaxParams[name] = value;
            },

            addAjaxParam: function (name, value) {
                if (!ajaxParams[name]) {
                    ajaxParams[name] = [];
                }

                skip = false;
                for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                    if (ajaxParams[name][i] === value) {
                        skip = true;
                    }
                }

                if (skip === false) {
                    ajaxParams[name].push(value);
                }
            },

            clearAjaxParams: function (name, value) {
                ajaxParams = {};
            },

            getDataTable: function () {
                return dataTable;
            },

            getTableWrapper: function () {
                return tableWrapper;
            },

            gettableContainer: function () {
                return tableContainer;
            },

            getTable: function () {
                return table;
            }

        };

    };
     return Datatable;
});