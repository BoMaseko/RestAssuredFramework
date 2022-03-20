package org.bongz.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bongz.constants.FrameworkConstants;

public final class ExcelUtils {

	private ExcelUtils() {}

	public static List<Map<String, String>> getTestDetails(String sheetname) {


		/*
		 * This can be utilised by ??Try with resources?? 
		 * will close the connection of inputstream
		 * 
		 * */
		List<Map<String, String>> list = null;

		//FileInputStream fs = null;
		/*Try with resource .. can be implemented with class that implements clickable
		 * if used no need for finally block
		 * **/
		try (FileInputStream fs = new FileInputStream(FrameworkConstants.getExcelFolderPath())){
			//fs = new FileInputStream(FrameworkConstants.getExcelpath());
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			//String sheetname = "RUNMANAGER";
			XSSFSheet sheet = workbook.getSheet(sheetname);


			int lastrownum = sheet.getLastRowNum();
			int lastcolumn = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<Map<String,String>>();

			for(int i=1; i<=lastrownum; i++) {//row
				map = new HashMap<String, String>();
				for(int j=0; j<lastcolumn; j++) {//colum
					String key = sheet.getRow(0).getCell(j).getStringCellValue();//column
					String value = sheet.getRow(i).getCell(j).getStringCellValue();//row
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * finally { try { if(Objects.nonNull(fs)) fs.close(); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
		return list;

	}

}
