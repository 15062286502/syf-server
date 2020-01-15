package com.example.syfserver.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.SimpleDateFormat;

public class ExcelUtil {
    public static String getCellValue(XSSFSheet sheet, int row, int column) {
        try {
            return getCellValue(sheet.getRow(row).getCell(column)).toString().trim().replaceAll("　", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getCellValue(Cell cell) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String cellValue = "";
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING: // 文本
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC: // 数字、日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = fmt.format(cell.getDateCellValue()); // 日期型
                } else {
                    cellValue = String.valueOf((int) cell.getNumericCellValue()); // 数字
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: // 空白
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR: // 错误
                cellValue = "错误";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = "错误";
                break;
            default:
                cellValue = "错误";
        }
        return cellValue;
    }
}
