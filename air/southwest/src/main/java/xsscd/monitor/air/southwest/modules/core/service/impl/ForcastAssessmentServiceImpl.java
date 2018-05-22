package xsscd.monitor.air.southwest.modules.core.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto.EvaluationSummaryDto;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastAssessmentMapper;
import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastDataMapper;
import xsscd.monitor.air.southwest.modules.core.service.ForcastAssessmentService;

import java.util.*;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
@Service
public class ForcastAssessmentServiceImpl implements ForcastAssessmentService {
    @Autowired
    ForcastAssessmentMapper mapper;

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getEvaluationSummary( int startYear, int endYear,int startTime, int endTime, String[] provinces,int timeType,int day) {
        List<Map<String, Object>> data=mapper.getEvaluationSummary( startYear,endYear,startTime,  endTime,  provinces, timeType,day);
        return data;
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  Map<String, Object>  getForcastAudateCityDayData( String startTime , String endTime , String cityCode, int day) {
        List<Map<String, Object>> data=mapper.getForcastAudateCityDayData( startTime,  endTime,  cityCode,day);
        List<Long> dateTime=new ArrayList<>();//时间列（有多少天返回多少个时间戳：从0开始）

        Map<String, Object> map=new HashMap<>();

        Map<String, List<String>> realPPMap=new HashMap<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）
        Map<String, List<String>> qualityRealMap=new HashMap<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）
        Map<String, List<String>> ppAuditMap=new HashMap<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）
        Map<String, List<String>> qualityAuditMap=new HashMap<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）

        List<Object> realPPSrc=new ArrayList<>();//首要污染物实测
        List<Object> ppAuditSrc=new ArrayList<>();//首要污染物预测
        List<Object> qualityRealSrc=new ArrayList<>();//首要污染物实测
        List<Object> qualityAuditSrc=new ArrayList<>();//空气等级预测
        List<Object> userNameAudit=new ArrayList<>();//预测专家

        String PPStr="细颗粒物(PM2.5),臭氧8小时,二氧化硫,二氧化氮,一氧化碳,颗粒物(PM10),无";
        String qualityStr="优,良,轻度污染,中度污染,重度污染,严重污染";
        String[] PPs=PPStr.split(",");
        String[] qualitys=qualityStr.split(",");
        long v=0;

        for(int i=0;i<PPs.length;i++){
            List<String> realPP=new ArrayList<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）
            List<String> ppAudit=new ArrayList<>();//首要污染物预测（每天给每个等级的情况：有—'1',无：'-'）
            for(Map<String, Object> value:data){
                if((value.get("primarypollutant_real")+"").indexOf(PPs[i])==-1){
                    realPP.add("-");
                }else {
                    realPP.add("-1");
                }

                if((value.get("primarypollutant")+"").indexOf(PPs[i])==-1){
                    ppAudit.add("-");
                }else {
                    ppAudit.add("1");
                }
            }
            realPPMap.put(PPs[i],realPP);
            ppAuditMap.put(PPs[i],ppAudit);
        }

        for(int j=0;j<qualitys.length;j++){
            List<String> qualityReal=new ArrayList<>();//首要污染物实测（每天给每个等级的情况：有—'-1',无：'-'）
            List<String> qualityAudit=new ArrayList<>();//空气等级预测（每天给每个等级的情况：有—'1',无：'-'）
            for(Map<String, Object> value:data){
                if((value.get("quality_real")+"").indexOf(qualitys[j])==-1){
                    qualityReal.add("-");
                }else {
                    qualityReal.add("-1");
                }
                if((value.get("quality")+"").indexOf(qualitys[j])==-1){
                    qualityAudit.add("-");
                }else {
                    qualityAudit.add("1");
                }
                qualityRealMap.put(qualitys[j],qualityReal);
                qualityAuditMap.put(qualitys[j],qualityAudit);
            }
        }
        for(Map<String, Object> value:data){
            dateTime.add(24*3600*1000*v);
            realPPSrc.add(value.get("primarypollutant_real"));//首要污染物实测
            ppAuditSrc.add(value.get("primarypollutant"));//首要污染物预测
            qualityRealSrc.add(value.get("quality_real"));//首要污染物实测
            qualityAuditSrc.add(value.get("quality"));//空气等级预测
            userNameAudit.add(value.get("username"));//空气等级预测
            v++;
        }
        map.put("dateTime",dateTime);
        map.put("qualityNames",qualitys);
        map.put("ppNames",PPs);
        map.put("realPP",realPPMap);
        map.put("qualityReal",qualityRealMap);
        map.put("ppAudit",ppAuditMap);
        map.put("qualityAudit",qualityAuditMap);

        map.put("realPPSrc",realPPSrc);
        map.put("qualityRealSrc",qualityRealSrc);
        map.put("ppAuditSrc",ppAuditSrc);
        map.put("qualityAuditSrc",qualityAuditSrc);
        map.put("userNameAudit",userNameAudit);

        return map;
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getForcastAudateCityData( String startTime , String endTime , String cityCode, int day) {
        List<Map<String, Object>> data=mapper.getForcastAudateCityData( startTime,  endTime,  cityCode,day);
        return data;
    }

    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public  List<Map<String, Object>>  getExpertsAssess( String startTime, String endTime, String cityCode ) {
        List<Map<String, Object>> data=new ArrayList<>();
        for(int i=1;i<8;i++){
            data.add(mapper.getExpertsAssess( startTime,  endTime,  cityCode, i));//获取数据
        }
        return data;
    }



    /**
     * pm2_5、aqi分数算法
     */
     private List<Double> getGas (List<String> forcasts,String reals){
         List<Double> gas=new ArrayList<>();//7天预测值情况
            double real =Double.parseDouble(reals);
            for(String p:forcasts){
                double p1 =Double.parseDouble(p.split("~")[0]);
                double p2 =Double.parseDouble(p.split("~")[1]);
                double avg=(p1+p2)/2;
                if(avg==real){
                    gas.add(100.00);
                    continue;
                }
               if(avg>real){
                   gas.add((1-(avg-real)/avg)*100<0?0:(1-(avg-real)/avg)*100);
                   continue;
               }
               if(avg<real){
                   gas.add((1-(real-avg)/real)*100<0?0:(1-(real-avg)/real)*100);
                   continue;
               }
            }
            return gas;
        }

    /**
     * 首要污染物分数算法
     */
    private List<Double> getPrimarypollutants (List<String> forcasts,String reals){
        List<Double> gas=new ArrayList<>();//7天预测值情况
        double real =Double.parseDouble(reals);
        for(String p:forcasts){
            double p1 =Double.parseDouble(p.split("~")[0]);
            double p2 =Double.parseDouble(p.split("~")[1]);
            double avg=(p1+p2)/2;
            if(avg==real){
                gas.add(100.00);
                continue;
            }
            if(avg>real){
                gas.add((1-(avg-real)/avg)*100<0?0:(1-(avg-real)/avg)*100);
                continue;
            }
            if(avg<real){
                gas.add((1-(real-avg)/real)*100<0?0:(1-(real-avg)/real)*100);
                continue;
            }
        }
        return gas;
    }





}
