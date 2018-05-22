package xsscd.monitor.air.southwest.modules.core.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/5/18.
 */
public class ExcelUtils {

    public static List<Map<String, Object>> readExcel(String path, int tieleNum, String[] cellName) {
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File(path);
            FileInputStream is = new FileInputStream(excelFile);
            Workbook workbook = WorkbookFactory.create(is);

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            List<Map<String, Object>> list = new ArrayList<>();
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
                for (int i = tieleNum; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    int cells = row.getPhysicalNumberOfCells();
                    Map<String, Object> result = new HashMap<String, Object>();
                    for (int j = 0; j < cellName.length; j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            result.put(cellName[j], null);
                            continue;
                        }
                        int cellType = cell.getCellType();
                        Object cellValue = null;
                        if (cellType == Cell.CELL_TYPE_STRING) {
                            cellValue = cell.getStringCellValue();
                        } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
                            cellValue = cell.getNumericCellValue();
                        } else if (cellType == Cell.CELL_TYPE_BLANK) {
                            cellValue = null;
                        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
                            cellValue = cell.getBooleanCellValue();
                        } else if (cellType == Cell.CELL_TYPE_FORMULA) {
                            cellValue = cell.getCellFormula();
                        } else if (cellType == Cell.CELL_TYPE_ERROR) {
                            cellValue = null;
                        } else {
                            cellValue = null;
                        }
                        result.put(cellName[j], cellValue);
                    }
                    list.add(result);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
