package xsscd.monitor.air.southwest.modules.core.service.impl;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xsscd.monitor.air.southwest.modules.core.entitys.mybatis.mapper.ForcastAssessmentMapper;
import xsscd.monitor.air.southwest.modules.core.service.ImportDateService;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.MediumLongAnalysisParamMapper;
import xsscd.monitor.air.southwest.modules.job.utils.Ftp;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
@Service
public class ImportDateServiceImpl implements ImportDateService {
	 @Autowired
	    private MediumLongAnalysisParamMapper mapper;

    /**
     * 中长期分析数据导入
     * @throws ParseException 
     */
    @Override
    public  boolean  importPollutantAnalysisDayDate( String time) throws ParseException {
    	 ZipInputStream zin;
         ZipFile zf;
         BufferedReader br;
         time="2018-04-21";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(time);
        Ftp ftp = new Ftp("172.16.10.106",21,"forecastftp","12345678");//连接FTP
        //outlook120_2018042100_sichuan.zip
        String remoteFileName = "outlook120_" +time.replaceAll("-", "") + "00_sichuan.zip";//凭借ftp下载数据包名
        String fileUrl="F:/tempfile/"+remoteFileName;
        try {
            ftp.connect();
            // /numforecast/products/outlook/:包路径
            if(!ftp.downloadFile("F:/tempfile/", "/home/share/numforecast/products/outlook/", remoteFileName)){
                ftp.ftpLogOut();
            }
            ftp.ftpLogOut();//下载数据包后关闭ftp
            zf = new ZipFile( fileUrl);//解压数据包
            InputStream is = new BufferedInputStream(new FileInputStream(fileUrl));//解压后存入数据流
            zin = new ZipInputStream(is);
            ZipEntry entry;
            int a=0;
            //String params="WS500,WD500,T500,WS850,WD850,T850,WS10m,WD10m,T2m,HPBL,RH2m,RAIN24";
            String params="500hpa风速,500hPa风向,500hPa温度,850hpa风速,850hPa风向,850hPa温度,十米风速,十米风向,2m温度,边界层高度,2m湿度,24小时累计降水量";
            String[] param=params.split(",");


            List<MediumLongAnalysisParam> list=new ArrayList<>();
            Map<String,String[][]> map =new HashMap<>();
            while ((entry = zin.getNextEntry()) != null) {
                if(entry.getName().startsWith("txt24_")){
                    String filName=entry.getName().substring(6);
                    String cityCode=filName.substring( filName.lastIndexOf("_")+1,filName.indexOf("."));
                    cityCode=getCityCode(cityCode);
                    String[][] data=new String[120][27];
                    String line;
                    br = new BufferedReader(new InputStreamReader(zf.getInputStream(entry)));
                    //每个文件（站点为准）有24*14=336条数据（转换成24条数据）
                    line = br.readLine();
                    Date Fdate=date;
                    for(int i = 0; i <120; i++){
                        line = br.readLine().trim();
                        String[] values = line.split("[\\s]+");

                        //创建预测时间
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(Fdate);
                        cal.add(Calendar.DATE,1);
                        Fdate =cal.getTime();
                        for(int j=0;j<12;j++){
                            MediumLongAnalysisParam value=new MediumLongAnalysisParam();
                            value.setCityCode(cityCode);
                            value.setCity(getCityName(cityCode));
                            value.setReportTime(date);
                            value.setForcastTime(Fdate);
                            value.setParam(param[j]);
                            int N=j+1+(j*1);
                            int L=j+2+(j*1);
                            value.setParamValue(values[N]);
                            value.setParamValueL(values[L]);
                            list.add(value);
                        }
                    }


                    map.put(cityCode,data);
                }
            }
            //批量插入数据库
            System.out.println(list);
            return   mapper.saveBatch(list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            ftp.ftpLogOut();
            e.printStackTrace();
        }

    	return false;
    }
    
    
    private String getCityCode(String param){
        String cityCode=param;
        if(param.length()==4){
            cityCode=param+"00";
        }
        if(param==null || "".equals(param)){
            cityCode="630100";
        }
        return cityCode;
    }
    private String getCityName(String param){
    	String cityName="";
        if ("510100".equals(param) ){
            cityName="成都市";
        }
        if ("511700".equals(param) ){
            cityName="达州市";
        }
        if ("510800".equals(param) ){
            cityName="广元市";
        }
        if ("110000".equals(param) ){
            cityName="北京市";
        }
        if ("500000".equals(param) ){
            cityName="重庆市";
        }
        if ("520100".equals(param) ){
            cityName="贵阳市";
        }
        if ("530100".equals(param) ){
            cityName="昆明市";
        }
        if ("540100".equals(param) ){
            cityName="拉萨市";
        }
        if ("621200".equals(param) ){
            cityName="陇南市";
        }
        if ("511300".equals(param) ){
            cityName="南充市";
        }
        if ("630100".equals(param)){
            cityName="西宁市";
        }
        if ("510300".equals(param) ){
            cityName="自贡市";
        }

        return cityName;
    }
  
}
