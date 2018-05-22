/*季度选择数据*/
var quarter_day = [
	{
		type:'startquarter',
		child:[
			{
				value:1,
				name:'第一季度'
			},
			{	value:2,
				name:'第二季度'
			},
			{	value:3,
				name:'第三季度'
			},
			{	value:4,
				name:'第四季度'
			}
		]
	},
	{
		type:'endquarter',
		child:[
			{
				value:1,
				name:'第一季度'
			},
			{	value:2,
				name:'第二季度'
			},
			{	value:3,
				name:'第三季度'
			},
			{	value:4,
				name:'第四季度'
			}
		]
	}
];
/*西南五省数据*/
var inputcheckData = [
	{
		"valName":"西南五省",
		"child":[
			{
				"value":25,
				"name":"四川省",
				"check":true
			},
			{
				"value":24,
				"name":"重庆市",
				"check":true
			},
			{
				"value":27,
				"name":"云南省",
				"check":true
			},
			{
				"value":26,
				"name":"贵州省",
				"check":true
			},
			{
				"value":28,
				"name":"西藏自治区",
				"check":true
			}
		]
	}
];
var tableHeader= [
			{
				rows:[
					{
						value:'省份',
						rowspan:2,
						colspan:0
					},
					{
						value:'区域',
						rowspan:2,
						colspan:0
					},
					{
						value:'城市',
						rowspan:2,
						colspan:0
					}
				]
			},{
				rows:[
					{
						value:'PM2.5',
						rowspan:0,
						colspan:0
					},
					{
						value:'AQI',
						rowspan:0,
						colspan:0
					},
					{
						value:'污染等级',
						rowspan:0,
						colspan:0
					},
					{
						value:'首要污染物',
						rowspan:0,
						colspan:0
					}
					
				]
			}
		];
  var newarrs=[
		{
			value:'PM2.5',
			rowspan:0,
			colspan:0
		},
		{
			value:'AQI',
			rowspan:0,
			colspan:0
		},
		{
			value:'污染等级',
			rowspan:0,
			colspan:0
		},
		{
			value:'首要污染物',
			rowspan:0,
			colspan:0
		}
	];
//初始化数据
var day1 =new Date(new Date()-24*60*60*1000).format("yyyy-MM-dd");
var day2 =new Date(new Date()).format("yyyy-MM");
var day3 = new Date(new Date()).format("yyyy");
/*初始化头部*/
rendercheckbox(inputcheckData);


/********************时间切换********************/
$('#areatype').on('click','li',function(e){
	e.stopPropagation();
	var _index = $(this).attr('data-type');
	gettimeClick(_index);
	$(this).addClass('active');
	$(this).siblings('li').removeClass('active');
	setTimeout(function(){
		var _searchparm =  gettimetype();
		gettableHeader(tableHeader,_searchparm)
	},100);
	
});

$('body').on('click','#controlSidebar',function(e){
	var type = $('#areatype').children('.active').attr('data-type');
	gettimeClick(type)
});

$('body').on('click','.thirdbgs',function(e){
	e.stopPropagation();
	if($(this).hasClass('active')){
		$(this).removeClass('active');
	}else{
		$(this).addClass('active');
	}
});
new Slideicon($(".slide-button"),{
    callback:function (data) {
        var _searchparm =  gettimetype();
		gettableHeader(tableHeader,_searchparm)
    }
});
/**
 * 生成不同的时间选择
 * @param {Object} index 参数选择
 */
function rendertimepicker(index,arr){
	$('#rangeDatePicker').html('');
	var _timepicker = ''
	switch(index){
		case "1":
		_timepicker = '<input type="text" class="input-sm form-control" name="startmonth" />'
            +'<span class="input-group-addon zhitime">至</span>'
            +'<input type="text" class="input-sm form-control" name="endmonth" />'
		break;
		
		case "2":
		arr.forEach(function(value,index){
				_timepicker+='<div class="select-day">'
                    +'<div class="dropdown"><input type="text" class="input-sm form-control" name='+value.type+'>'
                        +'<button type="button" class="btn dropdown-toggle active" id="areaselect" data-toggle="dropdown">'
                            +'<span class="title seven">'+(index==0 ? '第一季度' : '第二季度')+'</span>'
                        +'</button>'
                        +'<ul class="dropdown-menu" role="menu" aria-labelledby="areaselect">';
                        	value.child.forEach(function(v){
                            _timepicker+='<li type="day" data-type='+v.value+'>'+v.name+'</li>';
                         });
                       _timepicker+='</ul>'
                   + '</div>'
                +'</div>';
			
		});       
		break;
		
		case "3":
		_timepicker = '<input type="text" class="input-sm form-control" name="startyear" />'
            +'<span class="input-group-addon zhitime">至</span>'
            +'<input type="text" class="input-sm form-control" name="endyear" />'
		break;
	    default:
	    break;
		
	}
	$('#rangeDatePicker').html(_timepicker);
}

/**			
 * 时间点击选择事件
 * @param {Object} index
 */
function gettimeClick(index){
	if(index == "1"){
		return new Promise(function(resolve,reject){
   		rendertimepicker(index,quarter_day);
   		resolve();
		    }).then(function(){
		   	    $('#rangeDatePicker input[name="startmonth"]').val(day2);
		   	    $('#rangeDatePicker input[name="endmonth"]').val(day2);
		   	   //初始化日期
				$('#rangeDatePicker input[name="startmonth"]').datepicker({ 
					language:'zh-CN',
					format: 'yyyy-mm',
					todayBtn : "linked",
			        startDate:new Date("2006-1-01"),
					startView: 2,  
					maxViewMode: 1,
					minViewMode:1,
					endDate:new Date(),
					forceParse:false,
			        autoclose: true
				}).on('changeDate',function(ev){
			  });

				$('#rangeDatePicker input[name="endmonth"]').datepicker({
				    language :  'zh-CN',  //日历显示为中文
				    minView  : "month",
				    format   : "yyyy-mm",
				    todayBtn : "linked",
				    keyboardNavigation: false,
				    autoclose: true,
				    endDate: new Date(),
				    startView: 2,  
					maxViewMode: 1,
					minViewMode:1,
				});
				
				$('#rangeDatePicker input[name="startmonth"]').change(function () {
					 setTimeout(function () {
					       var _searchparm =  gettimetype();
							gettableHeader(tableHeader,_searchparm)
					    },100);
				});
				$('#rangeDatePicker input[name="endmonth"]').change(function () {
					 setTimeout(function () {		       
					        var _searchparm =  gettimetype();
							gettableHeader(tableHeader,_searchparm)
					    },100);
				});
			});
	}else if(index=="2"){
		return new Promise(function(resolve,reject){
   		rendertimepicker(index,quarter_day);
   		resolve();
		    }).then(function(){
		   	    $('#rangeDatePicker input[name="startquarter"]').val(day3);
		   	    $('#rangeDatePicker input[name="endquarter"]').val(day3);
		   	   //初始化日期
				$('#rangeDatePicker input[name="startquarter"]').datepicker({
				    language:'zh-CN',
					format: 'yyyy',
					todayBtn : "linked",
			        startDate:new Date("2006-1-01"),
					startView: 2,  
			         maxViewMode: 2,
			         minViewMode:2,
					endDate:new Date(),
					forceParse:false,
			        autoclose: true
				});
				$('#rangeDatePicker input[name="endquarter"]').datepicker({
				   language:'zh-CN',
					format: 'yyyy',
					todayBtn : "linked",
			        startDate:new Date("2006-1-01"),
					startView: 2,  
			         maxViewMode: 2,
			         minViewMode:2,
					endDate:new Date(),
					forceParse:false,
			        autoclose: true
				});
				//单模式下拉菜单
				new Dropdown($(".dropdown"),{
				    callback:function (e) {
				      setTimeout(function () {
					       var _searchparm =  gettimetype();
							gettableHeader(tableHeader,_searchparm)
					    },100);
				    }
				});
//				$('#rangeDatePicker input[name="startquarter"]').change(function () {
//					 setTimeout(function () {
//					      
//					        var _searchparm =  gettimetype();
//								gettableHeader(tableHeader,_searchparm)
//					    },100);
//				});
//				$('#rangeDatePicker input[name="endquarter"]').change(function () {
//					 setTimeout(function () {
//					       
//					        var _searchparm =  gettimetype();
//							gettableHeader(tableHeader,_searchparm)
//					    },100);
//				});
		   })
	}else if(index=="3"){
		return new Promise(function(resolve,reject){
   		rendertimepicker(index,quarter_day);
   		resolve();
		    }).then(function(){
		   	    $('#rangeDatePicker input[name="startyear"]').val(day3);
		   	    $('#rangeDatePicker input[name="endyear"]').val(day3);
		   	   //初始化日期
				$('#rangeDatePicker input[name="startyear"]').datepicker({
				    language:'zh-CN',
					format: 'yyyy',
					todayBtn : "linked",
			        startDate:new Date("2006-1-01"),
					 startView: 2,  
			         maxViewMode: 2,
			         minViewMode:2,
					endDate:new Date(),
					forceParse:false,
			        autoclose: true
				});
				$('#rangeDatePicker input[name="endyear"]').datepicker({
				    language:'zh-CN',
					format: 'yyyy',
					todayBtn : "linked",
			        startDate:new Date("2006-1-01"),
					startView: 2,  
			         maxViewMode: 2,
			         minViewMode:2,
					endDate:new Date(),
					forceParse:false,
			        autoclose: true
				});
				$('#rangeDatePicker input[name="startyear"]').change(function () {
					 setTimeout(function () {   
					        var _searchparm =  gettimetype();
					         gettableHeader(tableHeader,_searchparm)
					    },100);
				});
				$('#rangeDatePicker input[name="endyear"]').change(function () {
					 setTimeout(function () {
					        var _searchparm =  gettimetype();
					        gettableHeader(tableHeader,_searchparm)
					    },100);
				});
			});
			
	}
	 
}

/**
 * 渲染头部省站
 * @param {Object} arr
 */
function rendercheckbox(arr){
	return new Promise(function(resolve,reject){
		$('.headcheck').html('');
		var _urbancheckhtml='';
			arr.forEach(function(v){
			_urbancheckhtml += '<div class="spancity"><span>'+v.valName+'</span>';
			         v.child.forEach(function(value){
			         	_urbancheckhtml+='<label>'
	                        +'<input type="checkbox"  class="minimal" checked='+value.check+' value='+value.value+'>'
	                        +'<span>'+value.name+'</span>'
	                    +'</label>';
			         });
			         _urbancheckhtml+='</div>';
		});
		$('.headcheck').html(_urbancheckhtml);
			resolve(2);
	}).then(function(){
		//初始化单选按钮框
	
		$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
		    radioClass   : 'iradio_minimal-blue',
		    checkboxClass: 'icheckbox_minimal-blue'
		});
		 $('input[type="checkbox"].minimal, input[type="radio"].minimal').on('ifClicked', function(event){
		  
		  	setTimeout(function () {
		        	var _searchparm =  gettimetype();
					gettableHeader(tableHeader,_searchparm)
		    },100);
		  });		
	}).then(function(){
		gettimetype();
	 })	
	
}
setTimeout(function(){
	var _searchparm =  gettimetype();
	gettableHeader(tableHeader,_searchparm)
},100);

function gettableHeader(arr,searchparm){
	var _tableheader = '';
	$('#tablelist').html('');
	var timearr=[];
	var timeType = $('#areatype').children('.active').attr('data-type');
	var _tbodyhtml = '<tbody id="tbodytable">';
	var _appendtbody = '';
	var cityarr=[];
	var flagarr=['aqi','pm25','quality','primarypollutant'];
	var later = '';
	urlconfig.getevaltableData(searchparm,function (data) { 
		if(data.code==0){
			if(data.data.length==0){
				arr.length=1;
				_tableheader='<thead>'
					arr.forEach(function(v){
						_tableheader+='<tr>'
						    v.rows.forEach(function(value){
						    	_tableheader+='<td  rowspan='+value.rowspan+' colspan='+value.colspan+' >'+value.value+'</td>'
						    });
						_tableheader+='</tr>';
					});
				_tableheader+='</thead>';
				$('#tablelist').append(_tableheader);
				_tbodyhtml='<tr><td colspan="8">暂无数据</td></tr>';
				$('#tablelist').find('thead').after(_tbodyhtml);
			}else{
				var years = data.data;
				for(var i=0;i<years.length;i++){
					timearr.push({
						value:getyearsdata(years[i].time,timeType),
						rowspan:0,
						colspan:4
					});
				}
				arr.length=2;
				arr[0].rows.length = 3;
				arr[0].rows = arr[0].rows.concat(timearr);
				arr[1] = {};
				arr[1]['rows'] = getrowtwo(years.length,newarrs);
				
				console.log(arr)
				_tableheader='<thead>' 
				arr.forEach(function(v){
					_tableheader+='<tr>'
					    v.rows.forEach(function(value){
					    	if(value.rowspan==0){
						    		_tableheader+='<td colspan='+value.colspan+' >'+value.value+'</td>'
						    	}else{
						    		_tableheader+='<td rowspan='+value.rowspan+'>'+value.value+'</td>'
						    	}
					    });
					_tableheader+='</tr>';
				});
				_tableheader+='</thead>';
				$('#tablelist').append(_tableheader);
				var province="";
				var areaN="";
				var timeN="";
				var resulttime = [];
				data.data.forEach(function(v,i){
					resulttime.push(v.time);
					if(i==0){
						v.provinceList.forEach(function(value,j){
							value.areaList.forEach(function(_value){
								_value.dataList.forEach(function(values){
								
									if(province!=values.provinceName ){      				
				         				_tbodyhtml+='<tr class='+values.provinceId+' id='+values.cityCode+'><td rowspan='+getProvinceNum(value.provinceName)+'>'+value.provinceName+'</td>'   
				         			}else{
				         				_tbodyhtml+='<tr class='+values.provinceId+' id='+values.cityCode+'>'   
				         			}
				         			if(areaN!=values.areaName){
				         				_tbodyhtml+='<td rowspan='+_value.dataList.length+'>'+values.areaName+'</td>'
				         			}
			         	        	_tbodyhtml+= '<td>'+values.cityName+'</td>'
			         	        		+'<td class='+values.provinceId+v.time+'pm25'+'>'+(values.pm25=='-1.0'? '-' : Number(values.pm25).toFixed(2))+'</td>'
				         				+'<td class='+values.provinceId+v.time+'aqi'+'>'+(values.aqi=='-1.0' ? '-' : Number(values.aqi).toFixed(2))+'</td>'
				         				+'<td class='+values.provinceId+v.time+'quality'+'>'+(values.quality=='-1.0' ? '-' : Number(values.quality).toFixed(2))+'</td>'
				         				+'<td class='+values.provinceId+v.time+'primarypollutant'+'>'+(values.primarypollutant=='-1.0' ? '-' : Number(values.primarypollutant).toFixed(2))+'</td>';
				         				_tbodyhtml+='</tr>';
				         				 province=values.provinceName
				         				 areaN=values.areaName;	
				         				 cityarr.push(values.cityName);		
								})
							  
							})
						})
				        $('#tablelist').find('thead').after(_tbodyhtml);
					}else{
						v.provinceList.forEach(function(value,j){
							value.areaList.forEach(function(_value){
								_value.dataList.forEach(function(values,z){
									        var cityCode=values.cityCode;
									        var id="#"+cityCode;
									        $(id).append('<td class='+values.provinceId+v.time+'pm25'+'>'+(values.pm25=='-1.0'? '-' : Number(values.pm25).toFixed(2))+'</td>'
									    +'<td class='+values.provinceId+v.time+'aqi'+'>'+(values.aqi=='-1.0' ? '-' : Number(values.aqi).toFixed(2))+'</td>'
				         				+'<td class='+values.provinceId+v.time+'quality'+'>'+(values.quality=='-1.0' ? '-' : Number(values.quality).toFixed(2))+'</td>'
				         				+'<td class='+values.provinceId+v.time+'primarypollutant'+'>'+(values.primarypollutant=='-1.0' ? '-' : Number(values.primarypollutant).toFixed(2))+'</td>');
									        
								})
							  
							})
						})
				        
				     }
				});
                var provinceN="" ;
                var num=$('#tablelist').find("tr").length-1;
                var gas=["pm25","aqi","quality","primarypollutant"];
                getaveragetable(resulttime,gas,num)
//					$("#tablelist").tableHeadFixer()
			}
		}
	},'',".tablebox");


}
function getProvinceNum(provinceName){
	var num=0;
	if(provinceName=="四川省"){num=22}
	if(provinceName=="重庆市"){num=2}
	if(provinceName=="云南省"){num=17}
	if(provinceName=="贵州省"){num=10}
	if(provinceName=="西藏自治区"){num=8}
	return num;
}
/**
 * 获取所有参数
 */
function gettimetype(){
	var params = {},
	    provinceslist = $(".spancity").find('.checked input'),
	    provinces=[],
	    day = +$('#time').find('.active').attr('data-type');
	    for(var i= 0; i<provinceslist.length; i++){
	        provinces[i]=$(provinceslist[i]).val()
	    }
	    if(provinces.length==0){
	    	provinces[0]=0
	    }
	if($('.content-wrapper').hasClass('left-offset')){ 
	  var  timeType = $('#areatype').children('.active').attr('data-type'),
	        _quartstart = getquart($('.seven').eq(0).text()),
	        _quartend = getquart($('.seven').eq(1).text()),
	        startYear=-1,
	    	startTime=-1,
	    	endYear=-1,
	    	endTime=-1,
	    	mode = +$('.modebox').find('.active').attr('data-type');
	   switch(timeType){
	   	 case '1':
	   	 startYear = $('#rangeDatePicker input[name="startmonth"]').val().split('-')[0];
	   	 startTime = $('#rangeDatePicker input[name="startmonth"]').val().split('-')[1];
	   	 endYear = $('#rangeDatePicker input[name="endmonth"]').val().split('-')[0];
	   	 endTime = $('#rangeDatePicker input[name="endmonth"]').val().split('-')[1];
	   	 break;
	   	 case '2':
	   	 startYear = $('#rangeDatePicker input[name="startquarter"]').val();
	   	 startTime = _quartstart;
	   	 endYear = $('#rangeDatePicker input[name="endquarter"]').val();
	   	 endTime = _quartend;
	   	 break;
	   	 case '3':
	   	 startYear = $('#rangeDatePicker input[name="startyear"]').val();
	   	 startTime = -1;
	   	 endYear = +$('#rangeDatePicker input[name="endyear"]').val();
	   	 endTime = -1;
	   	 break;
	   	 default:
	   	 break
	   }
	    params = {
	    	timeType:timeType,
	    	day:day,
	    	startYear:startYear,
	    	startTime:startTime,
	    	endYear:endYear,
	    	endTime:endTime,
	    	model:mode,
	    	provinces:getnewpro(provinces)
	    };
	}else{
		params =  {
	    	timeType:2,
	    	day:day,
	    	startYear:new Date().getFullYear(),
	    	startTime:1,
	    	endYear:new Date().getFullYear(),
	    	endTime:getquartmonth(new Date()),
	    	provinces:getnewpro(provinces),
	    	model:1
	    };
	}

// 	console.log('params',params)
	  return params;
}
/**
 * 获取季度
 * @param {Object} value
 */
function getquart(value){
	var num=0;
	switch(value){
		case "第一季度":
		num=1;
		break;
		case "第二季度":
		num=2;
		break;
		case "第三季度":
		num=3;
		break;
		case "第四季度":
		num=4;
		break;
		default:
		break;
	}
	return num;
}
/**
 * 判断当前月份的季度
 * @param {Object} month
 */
function getquartmonth(newdate){
	var month = new Date(newdate).getMonth()+1;
	var num = 0;
	if(month<=3){
		num = 1;
	}else if(month>3 && month<=6){
		num = 2;
	}else if(month>6 && month<=9){
		num = 3;
	}else if(month>9 && month<=12){
		num = 4;
	}
	return num	
}

/**
 * 计算合并行的长度
 * @param {Object} length
 */
function getrowtwo(length,arr){
	var result = [];
   for(var i=0;i<length;i++){
   	 for(var j=0;j<arr.length;j++){
   	 	result.push(arr[j]);
   	 }
   }
   if(length==0){
   	result = arr;
   }
	return result;
}


/**
 * 得到月份 季度 年
 * @param {Object} time
 * @param {Object} type
 */
function getyearsdata(time,type){
	var times='';
	switch(type){
		case "1":
			times = time.slice(0,4)+'年'+time.slice(4,5)+'月';
		break;
		case "2":
			times = time.slice(0,4)+'年'+time.slice(4,5)+'季度';
		break;
		case "3":
			times = time+'年';
		break;
		default:
		break;
	}
       return times;
}

function getbodydata(searchparm){
	
}
function getnewpro(arr){
	var result=[];
	for(var i=0;i<arr.length;i++){
		if(result.indexOf(arr[i])==-1){
			result.push(arr[i])
		}
	}
	return result;
}

function getnewpro(arr){
	var result=[];
	for(var i=0;i<arr.length;i++){
		if(result.indexOf(arr[i])==-1){
			result.push(arr[i])
		}
	}
	return result;
}
/**
 * 得到表格中的平均值
 * @param {Object} resulttime 时间
 * @param {Object} gas 所选参数
 * @param {Object} num tr的长度
 */
function getaveragetable(resulttime,gas,num){
	   			var provinceN="" ;
               $('#tablelist').find("tr").each(function(i){
               	if(provinceN != $(this).attr('class') && i>2){
               		var td="";
               		resulttime.forEach(function(_time){
               			gas.forEach(function(_gas){
               				var avgNum=0;
               		        var classNam="."+provinceN+_time+_gas;
               		        $(classNam).each(function(i){
                	          avgNum+=$(this).text()=="-"?0:parseFloat($(this).text());
                          });
                          td+='<td>'+(avgNum/$(classNam).length).toFixed(2)+'</td>'
               			})
               		})

               		$(this).before('<tr class="avg_'+provinceN+'"><td colspan="2" >均值</td>'
               		+td+'</tr>')
               	}
               
               	if(i==num){
               		var td="";
               		var _class = $(this).attr('class');
               		resulttime.forEach(function(_time){
               			gas.forEach(function(_gas){
               				var avgNum=0;
               		        var classNam="."+_class+_time+_gas;
               		       
               		        $(classNam).each(function(i){
                	          avgNum+=$(this).text()=="-"? 0 :parseFloat($(this).text());
                          });
                         td+='<td>'+(avgNum/$(classNam).length).toFixed(2)+'</td>'
                          
               			})
               		})
               			$(this).after('<tr class="avg_'+provinceN+'"><td colspan="2" >均值</td>'
               			+td+'</tr>')
               		}
               	   provinceN= $(this).attr('class')
               });
}
