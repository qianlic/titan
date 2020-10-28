package com.cjwx.spark.engine.util.file;

import com.cjwx.spark.engine.util.ExceptionUtils;
import com.cjwx.spark.engine.web.http.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: Excel工具类
 * @Author: qian li
 * @Date: 2018年10月24日 16:13
 */
@Slf4j
public class ExcelUtils {

    public static final int DEFAULT_WIDTH = 2048;

    public static void download(String[] title, String[][] data) {
        OutputStream out = null;
        try {
            HttpServletResponse response = ResponseHelper.getResponse();
            out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=download.xls");
            buildWorkbook("Sheet1", title, data).write(out);
        } catch (IOException e) {
            ExceptionUtils.throwError("Excel创建失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    ExceptionUtils.throwError("Excel创建失败", e);
                }
            }
        }
    }

    public static HSSFWorkbook buildWorkbook(String sheetName, String[] title, String[][] values) {
        return buildWorkbook(new HSSFWorkbook(), sheetName, title, values);
    }

    private static HSSFWorkbook buildWorkbook(HSSFWorkbook wb, String sheetName, String[] title, String[][] values) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        //填充表头标题
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = new CellStyleBuilder(wb).setBorder().setColor().setAlign().build();
        buildSheetRow(row, style, title);
        //填充表格内容
        style = new CellStyleBuilder(wb).setBorder().build();
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            buildSheetRow(row, style, values[i]);
        }
        setColumnWidth(sheet, title.length);
        return wb;
    }

    /**
     * 创建表格行
     */
    private static void buildSheetRow(HSSFRow row, HSSFCellStyle style, String[] values) {
        for (int i = 0; i < values.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(values[i]);
            cell.setCellStyle(style);
        }
    }

    /**
     * 设置列宽度
     */
    private static void setColumnWidth(HSSFSheet sheet, int s) {
        for (int i = 0; i < s; i++) {
            sheet.autoSizeColumn(i);
            if (sheet.getColumnWidth(i) < DEFAULT_WIDTH) {
                sheet.setColumnWidth(i, DEFAULT_WIDTH);
            }
        }
    }

    /**
     * @Description: 单元格样式构建器
     * @Author: qian li
     * @Date: 2018年10月24日 16:13
     */
    public static class CellStyleBuilder {

        private HSSFCellStyle style;

        public CellStyleBuilder(HSSFWorkbook wb) {
            this.style = wb.createCellStyle();
        }

        /**
         * 设置背景色
         */
        public CellStyleBuilder setColor() {
            this.style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            this.style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            return this;
        }


        /**
         * 设置边框
         */
        public CellStyleBuilder setBorder() {
            this.style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
            this.style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
            this.style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
            this.style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
            return this;
        }

        /**
         * 设置居中
         */
        public CellStyleBuilder setAlign() {
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            return this;
        }

        public HSSFCellStyle build() {
            return this.style;
        }
    }

}
