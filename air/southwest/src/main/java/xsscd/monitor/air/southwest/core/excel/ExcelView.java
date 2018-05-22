package xsscd.monitor.air.southwest.core.excel;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import xsscd.monitor.air.southwest.common.utils.DateUtils;

public class ExcelView extends org.springframework.web.servlet.view.document.AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String excelName = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + ".xls";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName,"utf-8"));
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        @SuppressWarnings("unchecked")
        List<String> titles = (List<String>) map.get("titles");
        int len = titles.size();
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern((short) 1);
        //font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row header = sheet.createRow(0);
        for(int i=0; i<len; i++){ //设置标题     
        	header.createCell(i).setCellValue(titles.get(i));        	
        }
        

        int rowCount = 1;
        List<HashMap<String,Object>> varList = (List<HashMap<String,Object>>) map.get("varList");
        int varCount = varList.size();
        for(int i=0; i<varCount; i++){
            Row userRow = sheet.createRow(rowCount++);
            HashMap<String,Object> vpd = varList.get(i);
            for(int j=0;j<len;j++){
            	String varstr = vpd.get("var"+(j+1)) != null ? vpd.get("var"+(j+1)).toString() : "";
            	userRow.createCell(j).setCellValue(varstr);
            }
        }
    }

}
