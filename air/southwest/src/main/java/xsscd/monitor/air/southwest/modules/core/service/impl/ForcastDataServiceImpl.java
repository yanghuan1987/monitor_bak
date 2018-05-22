package xsscd.monitor.air.southwest.modules.core.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.AreaProvince;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.ForcastDataConditions;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastDataMapper;
import xsscd.monitor.air.southwest.modules.core.service.ForcastDataService;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
@Service
public class ForcastDataServiceImpl implements ForcastDataService {
    @Autowired
    ForcastDataMapper mapper;

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getForcastAudateCity(String provinces1, String timePoint,String[] gasLi,int day) {
        List<Map<String, String>> forcastData=mapper.getForcastAudateCity(timePoint,provinces1);
        List<Map<String, Object>> forcastDataN=new ArrayList<>();
        for(int i=0;i<forcastData.size();i++){
            Map<String, String> value=forcastData.get(i);
                Map<String, Object> map=new HashMap<>();
                map.put("areaname",value.get("areaName"));
                map.put("provincename",value.get("ProvinceName"));
                map.put("cityname",value.get("cityname"));
                for(int j=0;j<gasLi.length;j++){
                    map.put(gasLi[j],sepStr(value.get(gasLi[j]),day));
                }
            forcastDataN.add(map);
        }

        return forcastDataN;
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<AreaProvince> getForcastAudateCity1(ForcastDataConditions conditions) {
        System.out.println(conditions);
        return mapper.getForcastAudateCity1( conditions);
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public Map<String ,Object> getForcastAudateCityDay(String provinces1, String timePoint) {
        List<Map<String, String>> data=mapper.getForcastAudateCityDay(timePoint,provinces1);
        List<Map<String, Object>> forcastDataN=new ArrayList<>();
        int r_24=0;
        int r_48=0;
        int r_72=0;
        int day=data.size();
        for(Map<String, String> value:data){
            Map<String, Object> map=new HashMap<>();

            String[] strings = value.get("quality").split("\\|");
            if("-或-".equals(strings[0])){
                day--;
            }
            map.put("areaname",value.get("areaName"));
            map.put("provincename",value.get("ProvinceName"));
            map.put("cityname",value.get("CityName"));
            map.put("quality_real",value.get("quality_real"));

            map.put("quality_24",strings[0]);
            map.put("quality_48",strings[1]);
            map.put("quality_72",strings[2]);

            map.put("quality_24r","-或-".equals(strings[0])?"-":quilty_level(value.get("quality_real"),strings[0]));
            map.put("quality_48r","-或-".equals(strings[1])?"-":quilty_level(value.get("quality_real"),strings[1]));
            map.put("quality_72r","-或-".equals(strings[2])?"-":quilty_level(value.get("quality_real"),strings[2]));

            String[] le_24=strings[0].split("\\或");
            String[] le_48=strings[1].split("\\或");
            String[] le_72=strings[2].split("\\或");
            map.put("le_real",level(value.get("quality_real")));
            map.put("le_24",level(le_24.length==1?le_24[0]:le_24[1]));
            map.put("le_48",level(le_48.length==1?le_48[0]:le_48[1]));
            map.put("le_72",level(le_72.length==1?le_72[0]:le_72[1]));
            if(quilty_level(value.get("quality_real"),strings[0])==0){
                r_24++;
            }
            if(quilty_level(value.get("quality_real"),strings[1])==0){
                r_48++;
            }
            if(quilty_level(value.get("quality_real"),strings[2])==0){
                r_72++;
            }
            forcastDataN.add(map);
        }

        double percentage_24=(float)r_24/day*100;
        double percentage_48=(float)r_48/day*100;
        double percentage_72=(float)r_72/day*100;
        Map<String ,Object> forcastAudateCityDay=new HashMap<>();
        forcastAudateCityDay.put("percentage_24",percentage_24);
        forcastAudateCityDay.put("percentage_48",percentage_48);
        forcastAudateCityDay.put("percentage_72",percentage_72);
        forcastAudateCityDay.put("cityData",forcastDataN);


        return forcastAudateCityDay;
    }


    //------------------------工具------------------------------------------

    /**
     * 转换数组
     * @param str
     * @param day
     * @return
     */
    private String[] sepStr(String str,int day) {
        String[] vlue=new String[day];
        if(str==null){
            for(int i=0;i<day;i++){
                vlue[i]="-";
            }
            return vlue;
        }
        String[] strings = str.split("\\|");
        for(int i=0;i<day;i++){
            if(i<strings.length){
                vlue[i]=strings[i]==""?"-":strings[i];
            }else {
                vlue[i]="-";
            }
        }
        return vlue;
    }

    private  int quilty_level(String real,String forcast){
        if(forcast.indexOf(real)!=-1 ){
            return 0;
        }
        String[] forcasts=forcast.split("\\或");
        int r= level(forcasts[0]);

        if(forcasts.length==1){
           /* if(level(forcasts[0])>level(real)){
                return level(forcasts[0])-level(real);
            }
            return level(real)-level(forcasts[0]);*/
            return level(forcasts[0])-level(real);
        }


        if(level(forcasts[0])>level(real)){
            return level(forcasts[0])-level(real);
        }

        return level(forcasts[1])-level(real);
    }

    private  int level(String quilty){
        if("".equals("-")){
            return 0;
        }
        if("优".equals(quilty)){
            return 1;
        }
        if("良".equals(quilty)){
            return 2;
        }
        if("轻度污染".equals(quilty)){
            return 3;
        }
        if("中度污染".equals(quilty)){
            return 4;
        }
        if("重度污染".equals(quilty)){
            return 5;
        }
        if("严重污染".equals(quilty)){
            return 6;
        }
        return 0;
    }


}
