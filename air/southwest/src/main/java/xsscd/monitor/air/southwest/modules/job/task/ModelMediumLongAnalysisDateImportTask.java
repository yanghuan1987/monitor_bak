package xsscd.monitor.air.southwest.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xsscd.monitor.air.southwest.modules.job.service.GWFactCityDayService;
import xsscd.monitor.air.southwest.modules.job.service.MediumLongAnalysisPollutantService;
import xsscd.monitor.air.southwest.modules.job.service.ScheduleJobLogService;
import xsscd.monitor.air.southwest.modules.job.utils.Ftp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * * 模型-城市-日均数据导入
 \* @author yanghuan
 *
 */
@Component("ModelMediumLongAnalysisDateImportTask")
public class ModelMediumLongAnalysisDateImportTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ZipInputStream zin;
	private ZipFile zf;
	private BufferedReader br;

	@Autowired
	private MediumLongAnalysisPollutantService mediumLongAnalysisPollutantService;
	@Autowired
	private GWFactCityDayService gwFactCityDayService;

	@Autowired
	private ScheduleJobLogService jobLog;
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//SysUserEntity user = sysUserService.selectById(1L);
		System.out.println(/*ToStringBuilder.reflectionToString(user)*/"wwwww");
		
	}



	/**
	 * 模式预测小时数据导入
	 * @param time
	 * @return
	 */
	public String parseModelHourFile(Date time)  {
		long s = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd" );
		String startTime = format.format(time);
		Ftp ftp = new Ftp("172.16.10.106",21,"forecastftp","12345678");//连接FTP
		//outlook120_2018042100_sichuan.zip
		String remoteFileName = "outlook120_" + startTime + "00_sichuan.zip";//凭借ftp下载数据包名
		String fileUrl="F:/tempfile/"+remoteFileName;
		try {
			ftp.connect();
			// /numforecast/products/outlook/:包路径
			if(!ftp.downloadFile("F:/tempfile/", "/home/share/numforecast/products/outlook/", remoteFileName)){
				ftp.ftpLogOut();
				return "{\"state\":false,\"message\":\"对不起，未找到指定文件！"+remoteFileName+"\"}";
			}
			ftp.ftpLogOut();//下载数据包后关闭ftp
			zf = new ZipFile( fileUrl);//解压数据包
			InputStream is = new BufferedInputStream(new FileInputStream(fileUrl));//解压后存入数据流
			zin = new ZipInputStream(is);
			ZipEntry entry;
			int a=0;
			String[][] data=new String[120][25];
			while ((entry = zin.getNextEntry()) != null) {
				if(entry.getName().startsWith("sichuan.hourly")){
					String line;
					br = new BufferedReader(new InputStreamReader(zf.getInputStream(entry)));
					//每个文件（站点为准）有24*14=336条数据（转换成24条数据）
					line = br.readLine();
					line = br.readLine();
					line = br.readLine();
					for(int i = 0; i <120; i++){
						line = br.readLine();
						data[i] = line.split(",");
					}
					a++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return "{\"state\":false,\"message\":\"对不起，ftp连接错误！"+remoteFileName+"\"}";
		} catch (Exception e) {
			ftp.ftpLogOut();
			e.printStackTrace();
			return "{\"state\":false,\"message\":\"对不起，文件下载错误！"+remoteFileName+"\"}";
		}

		try {

			//开始批量插入数据库
		}catch (Exception e){
			e.printStackTrace();
			return "{\"state\":false,\"message\":\"对不起，上传数据库失败错误！"+remoteFileName+"\"}";
		}
		return "{\"state\":true,\"message\":\"成功，上传数据库成功！ "+remoteFileName+" 耗时："+(System.currentTimeMillis() - s)/ 1000+"秒"+"\"}";
	}


}
