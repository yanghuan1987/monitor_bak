package xsscd.monitor.air.southwest.core.excel;
import java.util.Date;
import java.util.HashMap;
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

import xsscd.monitor.air.southwest.common.utils.DateUtils;

public class ExcelViewRead extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        Date date = new Date();
        String filename = DateUtils.formatDate(date, "yyyyMMddHHmmss");
        HSSFSheet sheet;
        HSSFCell cell;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
        HSSFWorkbook book = (HSSFWorkbook) workbook;  
        sheet = book.createSheet();  

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
        List<HashMap<String,Object>> varList = (List<HashMap<String,Object>>) model.get("varList");
        int varCount = varList.size();
        for(int i=0; i<varCount; i++){
            HashMap<String,Object> vpd = varList.get(i);
            for(int j=0;j<len;j++){
                String varstr = vpd.get("var"+(j+1)) != null ? vpd.get("var"+(j+1)).toString() : "";
                row = sheet.createRow(i + 1);  
                row.createCell(j).setCellValue(varstr);  
            }
        }
		
	}


}
