package cn.zhangjingyao.util;

import cn.zhangjingyao.entity.PageData;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 从EXCEL导入到数据库
 * @author
 * @version
 */
public class ObjectExcelRead {

	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();
		Workbook wb = null;
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			if (filename.toLowerCase().endsWith(".xlsx")) {
				wb = new XSSFWorkbook(fi);
			} else {
				wb = new HSSFWorkbook(fi);
			}
			
			Sheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
				PageData varpd = new PageData();
				Row row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					Cell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((long)cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						default:
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
					
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return varList;
	}
	
	public static List<Object> readJCSJExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();
		Workbook wb = null;
		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			if (filename.toLowerCase().endsWith(".xlsx")) {
				wb = new XSSFWorkbook(fi);
			} else {
				wb = new HSSFWorkbook(fi);
			}
			
			Sheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
				PageData varpd = new PageData();
				Row row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					
					Cell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf(cell.getNumericCellValue());
							if(HSSFDateUtil.isCellDateFormatted(cell)){
			                    Date date = cell.getDateCellValue();
			                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			                    cellValue =dateFormat.format(date);
			                    }  
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						default:
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
					
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return varList;
	}
}
