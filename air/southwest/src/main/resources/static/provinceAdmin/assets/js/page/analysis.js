$(function () {
	//var exporturl = 'http://10.190.1.110:5000/';
	var exporturl = 'http://www.scnewair.cn:3392/';
    //初始化数据
    let supersetUrl = window.pageConfig.superset.url;
    let gasLineUrl = window.pageConfig.superset.end;
    let gasLineUrl_aqi=window.pageConfig.superset.endAqi
    let supersetData = {
        wind: '%7B%22datasource%22%3A%2219__table%22%2C%22viz_type%22%3A%22line%22%2C%22slice_id%22%3A92%2C%22granularity_sqla%22%3A%22forcast_time%22%2C%22time_grain_sqla%22%3A%22P1D%22%2C%22since%22%3A%222018-04-22T00%3A00%3A00%22%2C%22until%22%3A%222018-08-20T00%3A00%3A00%22%2C%22metrics%22%3A%5B%7B%22column%22%3A%7B%22column_name%22%3A%22param_value%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_param_value%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E9%A2%84%E6%B5%8B%E5%80%BC%22%2C%22optionName%22%3A%22metric_791jgpdylcw_yr8w1wteex%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22param_value_l%22%2C%22verbose_name%22%3A%22%22%2C%22description%22%3A%22%22%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E5%8E%BB%E5%B9%B4%E5%90%8C%E6%9C%9F%E5%80%BC%22%2C%22optionName%22%3A%22metric_9t26z92yhzr_dm7tjn9gk9w%22%7D%5D%2C%22groupby%22%3A%5B%5D%2C%22timeseries_limit_metric%22%3Anull%2C%22order_desc%22%3Atrue%2C%22contribution%22%3Afalse%2C%22color_scheme%22%3A%22bnbColors%22%2C%22show_brush%22%3A%22yes%22%2C%22show_legend%22%3Atrue%2C%22rich_tooltip%22%3Atrue%2C%22show_markers%22%3Atrue%2C%22line_interpolation%22%3A%22linear%22%2C%22x_axis_label%22%3A%22%22%2C%22bottom_margin%22%3A%22auto%22%2C%22x_axis_showminmax%22%3Atrue%2C%22x_axis_format%22%3A%22%25m-%25d%22%2C%22y_axis_label%22%3A%22%22%2C%22left_margin%22%3A%22auto%22%2C%22y_axis_showminmax%22%3Afalse%2C%22y_log_scale%22%3Afalse%2C%22y_axis_format%22%3A%22.3s%22%2C%22y_axis_bounds%22%3A%5Bnull%2Cnull%5D%2C%22rolling_type%22%3A%22None%22%2C%22time_compare%22%3Anull%2C%22num_period_compare%22%3A%22%22%2C%22period_ratio_type%22%3A%22growth%22%2C%22resample_how%22%3Anull%2C%22resample_rule%22%3Anull%2C%22resample_fillmethod%22%3Anull%2C%22annotation_layers%22%3A%5B%5D%2C%22where%22%3A%22param+%3D+%27500hpa%E9%A3%8E%E9%80%9F%27+AND+city%3D%27%E5%8C%97%E4%BA%AC%E5%B8%82%27+AND+report_time%3D%272018-04-21+00%3A00%3A00%27%22%2C%22having%22%3A%22%22%2C%22filters%22%3A%5B%5D%2C%22url_params%22%3A%7B%7D%7D',
        pollution: '%7B%22datasource%22%3A%2222__table%22%2C%22viz_type%22%3A%22echarts_stack_columnar%22%2C%22slice_id%22%3A109%2C%22granularity_sqla%22%3A%22report_time%22%2C%22time_grain_sqla%22%3A%22P1D%22%2C%22since%22%3A%222016-09-16T00%3A00%3A00%22%2C%22until%22%3A%222017-01-01T00%3A00%3A00%22%2C%22metrics%22%3A%5B%7B%22column%22%3A%7B%22column_name%22%3A%22PM2_5%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_PM2_5%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E7%BB%86%E9%A2%97%E7%B2%92%E7%89%A9%28PM2.5%29%22%2C%22optionName%22%3A%22metric_2g67ij340bn_sgbk8gjncfm%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22O3_8h%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_O3_8h%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E8%87%AD%E6%B0%A78%E5%B0%8F%E6%97%B6%22%2C%22optionName%22%3A%22metric_70vtqrmcw99_lrvhyb2112k%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22SO2%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_SO2%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E4%BA%8C%E6%B0%A7%E5%8C%96%E7%A1%AB%22%2C%22optionName%22%3A%22metric_f04vy9e2tb5_j6zdrh9rbi%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22NO2%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_NO2%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E4%BA%8C%E6%B0%A7%E5%8C%96%E6%B0%AE%22%2C%22optionName%22%3A%22metric_cyxih0m4xqb_ku61hrl416e%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22CO%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_CO%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E4%B8%80%E6%B0%A7%E5%8C%96%E7%A2%B3%22%2C%22optionName%22%3A%22metric_al4o4llq8fv_3alvg33odbs%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22PM_10%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_PM_10%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E9%A2%97%E7%B2%92%E7%89%A9%EF%BC%88PM10%EF%BC%89%22%2C%22optionName%22%3A%22metric_bu590mg3zmw_88ftwtu5oky%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22IS_NONE%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_IS_NONE%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E6%97%A0%22%2C%22optionName%22%3A%22metric_ncxid27hp8_dnlac33a39h%22%7D%5D%2C%22groupby%22%3A%5B%5D%2C%22where%22%3A%22city%3D%27%E5%8C%97%E4%BA%AC%E5%B8%82%27%22%2C%22having%22%3A%22%22%2C%22filters%22%3A%5B%5D%2C%22color_scheme%22%3A%22bnbColors%22%2C%22url_params%22%3A%7B%7D%7D',
        air: '%7B%22datasource%22%3A%2220__table%22%2C%22viz_type%22%3A%22bar%22%2C%22slice_id%22%3A93%2C%22granularity_sqla%22%3A%22report_time%22%2C%22time_grain_sqla%22%3A%22P1D%22%2C%22since%22%3A%222016-09-16T00%3A00%3A00%22%2C%22until%22%3A%222016-09-17T00%3A00%3A00%22%2C%22metrics%22%3A%5B%7B%22column%22%3A%7B%22column_name%22%3A%22lvl_code%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E7%AD%89%E7%BA%A7%22%2C%22optionName%22%3A%22metric_ku663j0qt3d_d82von3ne3h%22%7D%5D%2C%22groupby%22%3A%5B%5D%2C%22limit%22%3A0%2C%22timeseries_limit_metric%22%3Anull%2C%22order_desc%22%3Afalse%2C%22contribution%22%3Afalse%2C%22color_scheme%22%3A%22bnbColors%22%2C%22show_brush%22%3A%22auto%22%2C%22show_legend%22%3Atrue%2C%22show_bar_value%22%3Afalse%2C%22rich_tooltip%22%3Atrue%2C%22bar_stacked%22%3Afalse%2C%22line_interpolation%22%3A%22linear%22%2C%22show_controls%22%3Afalse%2C%22bottom_margin%22%3A%22auto%22%2C%22x_axis_format%22%3A%22%25m-%25d%22%2C%22y_axis_format%22%3A%22.3s%22%2C%22x_axis_showminmax%22%3Afalse%2C%22reduce_x_ticks%22%3Afalse%2C%22x_axis_label%22%3A%22%22%2C%22y_axis_label%22%3A%22%22%2C%22y_axis_bounds%22%3A%5Bnull%2Cnull%5D%2C%22y_log_scale%22%3Afalse%2C%22rolling_type%22%3A%22mean%22%2C%22time_compare%22%3Anull%2C%22num_period_compare%22%3A%22%22%2C%22period_ratio_type%22%3A%22growth%22%2C%22resample_how%22%3Anull%2C%22resample_rule%22%3Anull%2C%22resample_fillmethod%22%3Anull%2C%22annotation_layers%22%3A%5B%5D%2C%22where%22%3A%22%22%2C%22having%22%3A%22%22%2C%22filters%22%3A%5B%5D%2C%22all_columns_x%22%3A%22lvl_code%22%2C%22row_limit%22%3A10000%2C%22link_length%22%3A5%2C%22normalized%22%3Afalse%2C%22url_params%22%3A%7B%7D%7D',
        aqi:'%7B%22datasource%22%3A%2218__table%22%2C%22viz_type%22%3A%22line%22%2C%22slice_id%22%3A107%2C%22granularity_sqla%22%3A%22predict_time%22%2C%22time_grain_sqla%22%3A%22P1D%22%2C%22since%22%3A%222017-01-01T00%3A00%3A00%22%2C%22until%22%3A%222017-05-01T00%3A00%3A00%22%2C%22metrics%22%3A%5B%7B%22column%22%3A%7B%22column_name%22%3A%22AQI_R%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_AQI_R%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22AQI-%E5%AE%9E%E6%B5%8B%22%2C%22optionName%22%3A%22metric_jmkaw5kdky_y990f2i5s8%22%7D%2C%7B%22column%22%3A%7B%22column_name%22%3A%22AQI_C%22%2C%22verbose_name%22%3Anull%2C%22description%22%3Anull%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Atrue%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22VARCHAR%2820%29%22%2C%22optionName%22%3A%22_col_AQI_C%22%7D%2C%22aggregate%22%3A%22AVG%22%2C%22hasCustomLabel%22%3Atrue%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22AQI-CMAQ%22%2C%22optionName%22%3A%22metric_0pieocmfet8n_i1sbv1ia0eg%22%7D%5D%2C%22groupby%22%3A%5B%5D%2C%22timeseries_limit_metric%22%3Anull%2C%22order_desc%22%3Atrue%2C%22contribution%22%3Afalse%2C%22color_scheme%22%3A%22bnbColors%22%2C%22show_brush%22%3A%22no%22%2C%22show_legend%22%3Atrue%2C%22rich_tooltip%22%3Atrue%2C%22show_markers%22%3Atrue%2C%22line_interpolation%22%3A%22linear%22%2C%22x_axis_label%22%3A%22%22%2C%22bottom_margin%22%3A%22auto%22%2C%22x_axis_showminmax%22%3Afalse%2C%22x_axis_format%22%3A%22%25m-%25d%22%2C%22y_axis_label%22%3A%22%22%2C%22left_margin%22%3A%22auto%22%2C%22y_axis_showminmax%22%3Afalse%2C%22y_log_scale%22%3Afalse%2C%22y_axis_format%22%3A%22.3s%22%2C%22y_axis_bounds%22%3A%5Bnull%2Cnull%5D%2C%22rolling_type%22%3A%22None%22%2C%22time_compare%22%3Anull%2C%22num_period_compare%22%3A%22%22%2C%22period_ratio_type%22%3A%22growth%22%2C%22resample_how%22%3Anull%2C%22resample_rule%22%3Anull%2C%22resample_fillmethod%22%3Anull%2C%22annotation_layers%22%3A%5B%5D%2C%22where%22%3A%22city+%3D+%27%E5%B7%B4%E4%B8%AD%E5%B8%82%27+AND+station+%3D+%27%E5%9D%87%E5%80%BC%27+AND+forcast_day+%3D+%271%27%22%2C%22having%22%3A%22%22%2C%22filters%22%3A%5B%5D%2C%22url_params%22%3A%7B%7D%7D'
        }

    // 城市
    let city = $('input[name="city"]:checked').val()

    // 参数
    let argument = $('input[name="argument"]:checked').val()

    // 开始时间
    let time = $('#rangeDatePicker input[name="time"]').val() ? $('#rangeDatePicker input[name="time"]').val() : moment().subtract(1, 'days').format('YYYY-MM-DD')
    $('#rangeDatePicker input[name="time"]').val(time)
 

    // 默认视图渲染
    readyView()
    let current_year=moment(time).format("YYYY");
    let yestoday_year=moment(time).subtract(1, "years").format("YYYY");
    $(".current_year").html("切换为"+current_year+"年");
    $(".yestoday_year").html("切换为"+yestoday_year+"年");
      	
	function timetab(time){
		 current_year=moment(time).format("YYYY");
         yestoday_year=moment(time).subtract(1, "years").format("YYYY");
         $(".current_year").addClass("selcted-year");
    $(".current_year").html("切换为"+current_year+"年");
    $(".yestoday_year").html("切换为"+yestoday_year+"年");
      	
	}
    // 渲染视图
    function readyView() {
    	
        const windP = $('<p></p>')
        windP.text(city + argument)
        const wind = $('<iframe width="100%" height="290" style="float: left" seamless frameBorder="0" scrolling="no"></iframe>')
        wind.addClass('wind')
        wind.attr('src', `${supersetUrl}${formatData('wind', 'argument')}${gasLineUrl}`)
        $('.windBox').append(windP)
        $('.windBox').append(wind)

		const aqiP = $('<p></p>')
        aqiP.text(`AQI（${city}）`)
        const aqi = $('<iframe width="100%" height="290" style="float: left" seamless frameBorder="0" scrolling="no"></iframe>')
        aqi.addClass('aqi')
        aqi.attr('src', `${supersetUrl}${formatData('aqi')}${gasLineUrl_aqi}`)
        $('.aqiBox').append(aqiP)
        $('.aqiBox').append(aqi)
		
        const pollutionP = $('<p></p>')
        pollutionP.text(`首要污染物（${city}）`)
        const pollution = $('<iframe width="100%" height="290" style="float: left" seamless frameBorder="0" scrolling="no"></iframe>')
        pollution.addClass('pollution')
        pollution.attr('src', `${supersetUrl}${formatData('pollution')}${gasLineUrl}`)
        $('.pollutionBox').append(pollutionP)
        $('.pollutionBox').append(pollution)

        const airP = $('<p></p>')
        airP.text(`空气质量等级（${city}）`)
        const air = $('<iframe width="100%" height="290" style="float: left" seamless frameBorder="0" scrolling="no"></iframe>')
        air.addClass('air')
        air.attr('src', `${supersetUrl}${formatData('air')}${gasLineUrl}`)
        $('.airBox').append(airP)
        $('.airBox').append(air)
    }

    // 处理form_data
    function formatData(item, type) {
        let data = supersetData[item]
        let newData = JSON.parse(unescape(data))

        if (time) {
            newData.since = `${moment(time).add(1, 'days').format('YYYY-MM-DD')} 00:00:00`
            newData.until = `${moment(time).add(121, 'days').format('YYYY-MM-DD')} 00:00:00`
        }

        let newWhere = `city = '${unescape(encodeURI(city))}'`

        if (type == 'argument') {
            newWhere += ` and param = '${unescape(encodeURI(argument))}'`
        }
        
        if (item == 'wind') {
          newData.granularity_sqla = 'forcast_time'
          newWhere += ` and report_time = '${time} 00:00:00'`
          
        }
        newData.where = newWhere

        newData = escape(JSON.stringify(newData))
		 
        return newData
    }

    // 更新视图
    function updataView() {
        $('.windBox p').text(city + argument)
        $('.wind').attr('src', `${supersetUrl}${formatData('wind', 'argument')}${gasLineUrl}`).ready()

		$('.aqiBox p').text(`AQI（${city}）`)
        $('.aqi').attr('src', `${supersetUrl}${formatData('aqi')}${gasLineUrl_aqi}`).ready()

        $('.pollutionBox p').text(`首要污染物（${city}）`)
        $('.pollution').attr('src', `${supersetUrl}${formatData('pollution')}${gasLineUrl}`).ready()

        $('.airBox p').text(`空气质量等级（${city}）`)
        $('.air').attr('src', `${supersetUrl}${formatData('air')}${gasLineUrl}`).ready()
    }



    // console.log(JSON.parse(decodeURIComponent(supersetData[forecastMode][pollutant[0]].line)))

    //初始化日期
    $('#rangeDatePicker').datepicker({
        language: 'zh-CN', //日历显示为中文
        minView: "month",
        format: "yyyy-mm-dd",
        todayBtn: "linked",
        keyboardNavigation: false,
        autoclose: true,
        beforeShowYear: function (date) {
            if (date.getFullYear() == 2007) {
                return false;
            }
        }
    });
    $("#rangeDatePicker").change(function () {
        time = $('#rangeDatePicker input[name="time"]').val()
        console.log('time',time)
        timetab(time);
        updataView()
    });

    //预报模式
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        radioClass: 'iradio_minimal-blue',
        checkboxClass: 'icheckbox_minimal-blue'
    });

    $('input[name="city"]').on('ifClicked', function (event) {
        city = $(this).val();
       
        updataView()
    });

    $('input[name="argument"]').on('ifClicked', function (event) {
        argument = $(this).val()
        
        $('.windBox p').text(city + argument)
        $('.wind').attr('src', `${supersetUrl}${formatData('wind', 'argument')}${gasLineUrl}`).ready()
    });

	console.log('length',$('.exportable').length)
	$('.exportable').on('click',function(e){
//		e.stopPropagation();
		var href = exporturl+"supersetDraw/get/exportExcelGet?city="+city+'&param='+argument+'&reportTime='+time;
		window.open(href,"_self");
	})
	let  current_year_time;
	let yestoday_year_time;
	//点击时间切换
	$(".pollutionBox-btn").on("click" ,"span",function(){
		var index_one=$(this).index();
		$(this).addClass('selcted-year').siblings('span').removeClass('selcted-year');  
		  time = $('#rangeDatePicker input[name="time"]').val();
		
         
		switch (Number(index_one)){
			case 0:
			    //当前年
			      time=moment(time).format("YYYY-MM-DD");
			     $('.pollutionBox p').text(`首要污染物（${city}）`)
                 $('.pollution').attr('src', `${supersetUrl}${formatData('pollution')}${gasLineUrl}`).ready()
				break;
			case 1:
			   //去年
			    time=moment(time).subtract(1, "years").format("YYYY-MM-DD");
			    $('.pollutionBox p').text(`首要污染物（${city}）`)
                $('.pollution').attr('src', `${supersetUrl}${formatData('pollution')}${gasLineUrl}`).ready()
			    break;
			default:
				break;
		}
	})
	
	$(".airBox-btn").on("click" ,"span",function(){
		var index_one=$(this).index();
		$(this).addClass('selcted-year').siblings('span').removeClass('selcted-year'); 
		 time = $('#rangeDatePicker input[name="time"]').val();
		switch (Number(index_one)){
			case 0:
			    //当前年
			    time=moment(time).format("YYYY-MM-DD");
			    $('.airBox p').text(`空气质量等级（${city}）`)
                $('.air').attr('src', `${supersetUrl}${formatData('air')}${gasLineUrl}`).ready()
				break;
			case 1:
		        //去年
		         time=moment(time).subtract(1, "years").format("YYYY-MM-DD");
		         $('.airBox p').text(`空气质量等级（${city}）`)
                 $('.air').attr('src', `${supersetUrl}${formatData('air')}${gasLineUrl}`).ready()
			    break
			default:
				break;
		}
	})
})
/*导出表格数据0*/
	