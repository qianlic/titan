package com.cjwx.titan.engine.util.file;

import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年10月24日 16:13
 */
@Slf4j
public class ExcelUtils {

    public static void download(String[] title, String[][] data) {
        OutputStream out = null;
        try {
            HttpServletResponse response = ResponseHelper.getResponse();
            out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=download.xls");
            buildWorkbook("Sheet1", title, data).write(out);
        } catch (IOException e) {
            log.error("Excel创建失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("Excel创建失败", e);
                }
            }
        }
    }


    public static HSSFWorkbook buildWorkbook(String sheetName, String[] title, String[][] values) {
        return buildWorkbook(new HSSFWorkbook(), sheetName, title, values);
    }

    public static HSSFWorkbook buildWorkbook(HSSFWorkbook wb, String sheetName, String[] title, String[][] values) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.autoSizeColumn(1, true);//自适应列宽度
        //填充表头标题
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //创建一个居中格式
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //填充表格内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                HSSFCell cell = row.createCell(j);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(values[i][j]);
            }
        }
        return wb;
    }

}
