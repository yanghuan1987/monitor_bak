package xsscd.monitor.air.southwest.modules.job.utils;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import xsscd.monitor.air.southwest.common.jdbc.util.DateUtil;
import xsscd.monitor.air.southwest.common.utils.DateUtils;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisAir;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisParam;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.MediumLongAnalysisPollutant;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;

public class ExcelViewWrite extends AbstractXlsView{
	
	private MediumLongVO mediumLongVO;
    List<MediumLongAnalysisParam> listMP;
    

    List<MediumLongAnalysisAir> listMA;
    List<MediumLongAnalysisAir> listMALast;

    List<MediumLongAnalysisPollutant> listMPu;
    List<MediumLongAnalysisPollutant> listMPuLast;

    List<ForcastDataView> listMAQI;
    List<ForcastDataView> listMAQILast;
    
	public ExcelViewWrite(MediumLongVO mediumLongVO,List<MediumLongAnalysisParam> listMP,
			List<MediumLongAnalysisAir> listMA,List<MediumLongAnalysisAir> listMALast,
			List<MediumLongAnalysisPollutant> listMPu,List<MediumLongAnalysisPollutant> listMPuLast,
			List<ForcastDataView> listMAQI,List<ForcastDataView> listMAQILast) {
		this.mediumLongVO = mediumLongVO;
		this.listMA = listMA;
		this.listMALast = listMALast;
		this.listMAQI = listMAQI;
		this.listMAQILast = listMAQILast;
		this.listMP = listMP;
		this.listMPu = listMPu;
		this.listMPuLast = listMPuLast;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        Date date = new Date();
        String filename = "中长期分析_"+DateUtils.formatDate(mediumLongVO.getReportTime(), "yyyyMMdd");
        HSSFSheet sheet;
        HSSFCell cell;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
    	OutputStream os=response.getOutputStream();
        HSSFWorkbook book = (HSSFWorkbook) workbook;


        sheet = book.createSheet(mediumLongVO.getParam());  
        List<String> titles = (List<String>) model.get("titles");
        int len = titles.size();
        HSSFCellStyle headerStyle = (HSSFCellStyle) workbook.createCellStyle(); //标题样式
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont = (HSSFFont) workbook.createFont();	//标题字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short)11);
        headerStyle.setFont(headerFont);
        short width = 20,height=25*20;
        sheet.setDefaultColumnWidth(width);
        HSSFRow row = sheet.createRow(0);  
        for(int i=0; i<len; i++){ //设置标题        	
            String title = titles.get(i);
            row.createCell(i).setCellValue(title); 
        }
        sheet.getRow(0).setHeight(height);

        HSSFCellStyle contentStyle = (HSSFCellStyle) workbook.createCellStyle(); //内容样式
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i=0; i<listMP.size(); i++){
        	row = sheet.createRow(i + 1);  
        	row.createCell(0).setCellValue(DateUtil.convertToStr(listMP.get(i).getReportTime()));
        	row.createCell(1).setCellValue(listMP.get(i).getParamValue());
        	row.createCell(2).setCellValue(listMP.get(i).getParamValueL());
        }

        sheet = book.createSheet("空气质量等级");  
        HSSFCellStyle headerStyle2 = (HSSFCellStyle) workbook.createCellStyle(); //标题样式
        headerStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont2 = (HSSFFont) workbook.createFont();	//标题字体
        headerFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont2.setFontHeightInPoints((short)11);
        headerStyle2.setFont(headerFont2);
        sheet.setDefaultColumnWidth(width);
        HSSFRow row2 = sheet.createRow(0);  
        for(int i=0; i<titles.size(); i++){ //设置标题        	
            String title = titles.get(i);
            row2.createCell(i).setCellValue(title); 
        }
        sheet.getRow(0).setHeight(height);

        HSSFCellStyle contentStyle2 = (HSSFCellStyle) workbook.createCellStyle(); //内容样式
        contentStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i=0; i<listMA.size(); i++){
        	row2 = sheet.createRow(i + 1);  
        	row2.createCell(0).setCellValue(DateUtil.convertToStr(listMA.get(i).getReportTime()));
        	row2.createCell(1).setCellValue(listMA.get(i).getLvlName()!=null?listMA.get(i).getLvlName():"");
        	for (MediumLongAnalysisAir last : listMALast) {
				if (listMA.get(i).getReportTime().compareTo(DateUtil.setDate(last.getReportTime(),1))==0) {
					row2.createCell(2).setCellValue(last.getLvlName()!=null?last.getLvlName():"");
	        		continue;
				}
        	}
        }
		
        sheet = book.createSheet("首要污染物");  
        HSSFCellStyle headerStyle3 = (HSSFCellStyle) workbook.createCellStyle(); //标题样式
        headerStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont3 = (HSSFFont) workbook.createFont();	//标题字体
        headerFont3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont3.setFontHeightInPoints((short)11);
        headerStyle3.setFont(headerFont3);
        sheet.setDefaultColumnWidth(width);
        HSSFRow row3 = sheet.createRow(0);  
        for(int i=0; i<titles.size(); i++){ //设置标题        	
            String title = titles.get(i);
            row3.createCell(i).setCellValue(title); 
        }
        sheet.getRow(0).setHeight(height);

        HSSFCellStyle contentStyle3 = (HSSFCellStyle) workbook.createCellStyle(); //内容样式
        contentStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i=0; i<listMPu.size(); i++){
        	row3 = sheet.createRow(i + 1);  
        	row3.createCell(0).setCellValue(DateUtil.convertToStr(listMPu.get(i).getReportTime()));
        	row3.createCell(1).setCellValue(getPollutant(listMPu.get(i)));
        	for (MediumLongAnalysisPollutant last : listMPuLast) {
				if (listMPu.get(i).getReportTime().compareTo(DateUtil.setDate(last.getReportTime(),1))==0) {
	        		row3.createCell(2).setCellValue(getPollutant(last));
	        		continue;
				}
			}
        }
        
        sheet = book.createSheet("空气质量AQI");
        HSSFCellStyle headerStyle4 = (HSSFCellStyle) workbook.createCellStyle(); //标题样式
        headerStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont headerFont4 = (HSSFFont) workbook.createFont();	//标题字体
        headerFont4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont4.setFontHeightInPoints((short)11);
        headerStyle4.setFont(headerFont4);
        sheet.setDefaultColumnWidth(width);
        HSSFRow row4 = sheet.createRow(0);  
        for(int i=0; i<titles.size(); i++){ //设置标题        	
            String title = titles.get(i);
            row4.createCell(i).setCellValue(title); 
        }
        sheet.getRow(0).setHeight(height);

        HSSFCellStyle contentStyle4 = (HSSFCellStyle) workbook.createCellStyle(); //内容样式
        contentStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i=0; i<listMAQI.size(); i++){
        	row4 = sheet.createRow(i + 1);  
        	row4.createCell(0).setCellValue(DateUtil.convertToStr(listMAQI.get(i).getPredictTime()));
        	row4.createCell(1).setCellValue(listMAQI.get(i).getaQIR()!= null?listMAQI.get(i).getaQIR():"");
        	for (ForcastDataView last : listMAQILast) {
				if (listMAQI.get(i).getPredictTime().compareTo(DateUtil.setDate(last.getPredictTime(),1))==0) {
					row4.createCell(2).setCellValue(last.getaQIR()!= null?last.getaQIR():"");
					continue;
				}
			}
        }
        os.flush();
        book.write(os);
        os.close();
	}

	public String getPollutant(MediumLongAnalysisPollutant mediumLongAnalysisPollutant) {
		StringBuffer sb = new StringBuffer();
		if (mediumLongAnalysisPollutant.getiSNONE().equals("1")) {
			return "无";
		}
		if (mediumLongAnalysisPollutant.getnO2().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("二氧化氮");
		}

		if (mediumLongAnalysisPollutant.getO38h().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("臭氧8小时");
		}

		if (mediumLongAnalysisPollutant.getpM10().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("颗粒物(PM10)");
		}

		if (mediumLongAnalysisPollutant.getpM25().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("细颗粒物(PM2.5)");
		}

		if (mediumLongAnalysisPollutant.getsO2().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("二氧化硫");
		}

		if (mediumLongAnalysisPollutant.getcO().equals("1")) {
			if (sb.length() != 0) {
				sb.append("|");
			}
			sb.append("一氧化碳");
		}
		return sb.toString();
	}

	public MediumLongVO getMediumLongVO() {
		return mediumLongVO;
	}


	public void setMediumLongVO(MediumLongVO mediumLongVO) {
		this.mediumLongVO = mediumLongVO;
	}


}