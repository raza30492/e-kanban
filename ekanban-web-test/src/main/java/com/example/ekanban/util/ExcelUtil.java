package com.example.ekanban.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final String SEPARATOR = ";";

    public static String getCsvFromXlsx(File file, int sheetNo) {
        logger.debug("getCsvFromXlsx()");
        StringBuilder builder = new StringBuilder();
        String output = null;
        try {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(sheetNo);

            for (Row row : sheet) {
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        builder.append(SEPARATOR);
                    }else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                        builder.append(SEPARATOR);
                    }
                    else {
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                builder.append(cell.getRichStringCellValue().getString());
                                builder.append(SEPARATOR);
                                break;
                            case NUMERIC:
                                builder.append(cell.getNumericCellValue());
                                builder.append(SEPARATOR);
                                break;
                            case BOOLEAN:
                                builder.append(cell.getBooleanCellValue());
                                builder.append(SEPARATOR);
                                break;
                            case BLANK:
                                builder.append(SEPARATOR);
                                break;
                            default:
                                builder.append(SEPARATOR);

                        }
                    }

                }
                builder.append("$");
            }
            output = builder.toString();
            output = output.replaceAll("\n"," ");
            output = Pattern.compile("[$]").matcher(output).replaceAll("\n");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error converting xlsx to csv", e.getMessage());
        }
        return output;
    }

    public static String getCsvFromXls(File file) {
        logger.debug("getCsvFromXls()");
        StringBuilder builder = new StringBuilder();
        String output = null;
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        builder.append(SEPARATOR);
                    } else {
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                builder.append(cell.getRichStringCellValue().getString());
                                builder.append(SEPARATOR);
                                break;
                            case NUMERIC:
                                builder.append(cell.getNumericCellValue());
                                builder.append(SEPARATOR);
                                break;
                            case BOOLEAN:
                                builder.append(cell.getBooleanCellValue());
                                builder.append(SEPARATOR);
                                break;
                            case BLANK:
                                builder.append(SEPARATOR);
                                break;
                            default:
                                builder.append(SEPARATOR);
                        }
                    }

                }
                builder.append("$");
            }
            output = builder.toString();
            output = output.replaceAll("\n"," ");
            output = Pattern.compile("[$]").matcher(output).replaceAll("\n");

        } catch (Exception e) {
            logger.info("Error converting xls to csv", e.getMessage());
        }
        return output;
    }
}
