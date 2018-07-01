/**
 * Created with IntelliJ IDEA.
 * User: sodaojsb2
 * Date: 13-5-8
 * Time: 下午6:23
 * To change this template use File | Settings | File Templates.
 */
define(function (require, exports, module) {
    /***********省市区联动*******************************************/
    /**
     * 省市区联动控制
     * @param id 对象的ID
     */

    var
        getProvinceListUrl = ROOTPAth + '/admin/bas/region/list',
        getCityListUrl = ROOTPAth + '/admin/bas/region/list',
        getAreaListUrl = ROOTPAth + '/admin/bas/region/list';
    var selectArea = function (id) {
        this.selID = id;
        this.$selProvince = $("#selProvince-" + this.selID);
        this.$selProvinceTxt = $("#selProvinceTxt-" + this.selID);
        this.$selCity = $("#selCity-" + this.selID);
        this.$selCityTxt = $("#selCityTxt-" + this.selID);
        this.$selArea = $("#selCounty-" + this.selID);
        this.$selAreaTxt = $("#selAreaTxt-" + this.selID);
        this.bind();
    };
    selectArea.prototype = {

        /**
         * 绑定对象
         * @param id
         */
        bind: function (id) {
            var self = this;

            this.$selProvince.bind("change", function () {
                var $this = $(this);
                self.$selProvinceTxt.val($this.find("option:selected").text());
                self.onProvinceChange();


            });
            if (this.$selArea.length > 0) {//区域显示的时候
                this.$selCity.bind("change", function () {

                    self.onCityChange();
                });
            }

            if (this.$selArea.length > 0) {//区域显示的时候
                this.$selArea.bind("change", function () {
                    var $this = $(this);
                    self.$selAreaTxt.val($this.find("option:selected").text());

                });
            }
        },

        autoSel: function (provincecode, citycode, areacode) {

            this.$selProvince.find("option[value='" + provincecode + "']").prop("selected", true);
            this.onProvinceChange(citycode, areacode)
        },
        initProvince: function (provincecode, citycode, areacode,tag) {

            var self = this;
            var ajax = {

                url: getProvinceListUrl,
                dataType: 'json',
                type: "POST",
                data: {
                    'pid': 1
                },
                success: function (Data) {

                    var len = Data.length;
                    var provinceOptions = "";
                    for (var i = 0; i < len; i++) {
                        var province = Data[i];
                        provinceOptions += '<option value="' + province.id + '">' + province.text + '</option>';
                        /*if (provincecode == province.id) {
                            self.$selProvinceTxt.val(province.text)
                        }*/
                    }

                    self.$selProvince.append(provinceOptions).focus();//触发改变事件
                     if(tag) {
                         self.autoSel(provincecode, citycode, areacode);
                     }
                }
            };
            $.ajax(ajax);
        },
        /**
         * 省改变事件
         */
        onProvinceChange: function (citycode, areacode) {
            var self = this;
            self.$selProvinceTxt.val(this.$selProvince.find("option:selected").text());
            /*self.$selCityTxt.val(this.$selCity.find("option:selected").text());
            self.$selAreaTxt.val(this.$selArea.find("option:selected").text());*/
            self.$selCityTxt.val("");
            self.$selAreaTxt.val("");
            var provinceCode = this.$selProvince.val();
            //没有选择省份
            if (provinceCode == "" || provinceCode == "0") {
                /*this.$selCity.empty().append("<option value=''>请选择城市...</option>");
                 this.$selArea.empty().append("<option value=''>请选择区/县...</option>");*/
                this.$selCity.find("option:not(:first)").remove();
                this.$selArea.find("option:not(:first)").remove();
                return;
            }


            var ajax = {


                url: getCityListUrl,
                dataType: 'json',
                type: "POST",
                data: {
                    'pid': provinceCode
                },
                success: function (Data) {
                    var len = Data.length;
                    var cityOptions = "";
                    for (var i = 0; i < len; i++) {
                        var city = Data[i];
                        cityOptions += '<option value="' + city.id + '" ' + (citycode == city.id ? "selected" : "") + '>' + city.text + '</option>';

                    }
                    //var $selCity = $("#selCity_" + thisId);
                    self.$selArea.find("option:not(:first)").remove();
                    self.$selCity.find("option:not(:first)").remove();
                    self.$selCity.append(cityOptions).focus();//触发改变事件
                    self.onCityChange(areacode)
                }
            };
            $.ajax(ajax);

        },
        onCityChange: function (areacode) {
            var self = this;
            self.$selCityTxt.val(this.$selCity.find("option:selected").val()==""?"":this.$selCity.find("option:selected").text());
            //self.$selAreaTxt.val(this.$selArea.find("option:selected").text());
            self.$selAreaTxt.val("");
            /*var selCity = $this;
             var thisId = selCity.attr("id").substr("selCity_".length);
             var selArea = $("#selArea_" + thisId);*/
            var cityCode = this.$selCity.val();
            if (this.$selArea.length == 0) return;//不显示区域的不需要处理
            //没有选择省份
            if (cityCode == "" || cityCode == "0") {
                this.$selArea.find("option:not(:first)").remove();
                return;
            }


            var ajax = {
                /*url: getAreaListUrl + cityCode + '?jsonp=?',
                 dataType: 'jsonp',
                 type: "GET",
                 data: {

                 },
                 */
                url: getAreaListUrl,
                dataType: 'json',
                type: "POST",
                data: {
                    'pid': cityCode
                },
                success: function (Data) {
                    var areaOptions = "";
                    var len = Data.length;
                    for (var i = 0; i < len; i++) {
                        var area = Data[i];
                        areaOptions += '<option value="' + area.id + '" ' + (areacode == area.id ? "selected" : "") + '>' + area.text + '</option>';
                    }

                    //var selArea = $("#selArea_" + thisId);
                    self.$selArea.find("option:not(:first)").remove();
                    self.$selArea.append(areaOptions).focus();//焦点改变
                    self.onAreaChange()
                }
            };
            $.ajax(ajax);

        },
        onAreaChange: function () {
            this.$selAreaTxt.val(this.$selArea.find("option:selected").text()==="请选择县级市/县/区..."?this.$selArea.find("option:selected").text():"");
        }
    };
    return selectArea;
});
