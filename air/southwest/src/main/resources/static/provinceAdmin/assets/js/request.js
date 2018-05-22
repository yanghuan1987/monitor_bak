/**
 * Created by Administrator on 2017/9/21 0021.
 */
var mapUrl = "http://221.237.189.35:3390/product/";
//var baseUrl = "http://10.190.1.110:5105/";
//var baseUrl = "http://www.scnewair.cn:3392/";
//var baseUrl = "http://localhost:5105/";
var baseUrl = "http://10.190.1.110:5100/";
//var apiPath = "http://10.190.1.12:5001/";
var request = {
    http:function (url,method,params,fn,header,className,time) {
    	 var headers = (header===undefined ? '' : header);
         var ajax =  $.ajax({
                url:url,
                type:method,
                data:params,
                headers:headers,
                timeout :time||200000,
                dataType:"json",
                headers:headers,
                beforeSend:function () {
					if (className) {
						var loadHtml ='<div class="common-loading">' +
							'<div class="img"></div>' +
							'</div>';
						var fixDiv = $(className);
						fixDiv.css("position", "relative");
						fixDiv.append(loadHtml);
					}
                },
                success:function (data,status) {
                    fn(data)

                },
                complete:function (data,status,c) {
                	if (className) {
						var fixDiv = $(className);
						fixDiv.children(".common-loading").remove();
					}
                    if(status=='timeout'){
                        ajax.abort();
                      //  util.message('','网络请求超时，请稍后再试')
                    }
                    if(status=='error'){
                    	console.log(status)
//                      ajax.abort();
                      //  util.message('','网络错误，请稍后再试')
                    }
                },
                error:function (err,a,b) {
                	console.log(err.statusText)
                    if(err=="error"){
                        //  util.message('','网络错误，请稍后再试')
                    }
                }
            })
    }
};
//首页
var getAllCityAQIData = baseUrl+"core/home/getAllCityAQIData",
	getBouncedData = baseUrl+"core/home/getBouncedData",
    getPanelData = baseUrl+"xnair/getPanelData",
    getModelData = baseUrl+"xnair/getModelData",
    //城市
    getCityStation = baseUrl+"xnair/getCityStation",

    getModelRealData = baseUrl+"xnair/getModelRealData",
    /*公共信息城市预报结果*/
    getpublictableData = baseUrl+"core/cityForecast";
    /*评估表数据*/
    AssessmenttableData = baseUrl+"core/getForcastAudateCityDay";
     /*刷色表数据*/
    colortableData = baseUrl+"airReport/drow_do/get.do";
    /*区域预报结果数据*/
    forecasttableData = baseUrl+"airReport/report_do/gets.do";
	getevaltableData = baseUrl+"core/evaluationSummary";
    
    
    
    
    getAirReportDrow_do=baseUrl+"airReport/drow_do/get.do";


var urlconfig = {
    getAllCityAQIData:function (params,fn,header) {
        return request.http(getAllCityAQIData,'get',params,fn,header)
    },
    getPanelData:function (params,fn,header) {
        return request.http(getPanelData,'get',params,fn,header)
    },
    getModelData:function (params,fn) {
        return request.http(getModelData,'get',params,fn)
    },
    getCityStation:function (params,fn) {
        return request.http(getCityStation,'post',params,fn)
    },
    getModelRealData:function (params,fn) {
        return request.http(getModelRealData,'post',params,fn)
    },
    getpublictableData:function (params,fn) {
        return request.http(getpublictableData,'post',params,fn)
    },
    getcolortableData:function (params,fn) {
        return request.http(colortableData,'get',params,fn)
    },
    AssessmenttableData:function (params,fn) {
        return request.http(AssessmenttableData,'post',params,fn)
    },
    ForecasttableData:function (params,fn,header,className) {
        return request.http(forecasttableData,'post',params,fn,header,className)
    },
    
    getevaltableData:function (params,fn,header,className) {
        return request.http(getevaltableData,'post',params,fn,header,className)
    },
    
    /*业务填报*/
    getAirReportDrow_do:function (params,fn) {
        return request.http(getAirReportDrow_do,'post',params,fn)
    },
    getBouncedData:function (params,fn,header) {
        return request.http(getBouncedData,'get',params,fn,header)
    },

};