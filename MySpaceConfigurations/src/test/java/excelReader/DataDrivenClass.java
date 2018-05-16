package excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class DataDrivenClass {
	public static String data;
	public static String excelReader(String sheetname,int rownum,String objectlabel) throws IOException{
		File excel=new File("ExcelSheet//UserCredentials.xls");
		FileInputStream finput=new FileInputStream(excel);
		HSSFWorkbook workbook=new HSSFWorkbook(finput);
		Sheet sheet=workbook.getSheet(sheetname);
		int rowcnt=sheet.getLastRowNum();
		int colcnt=sheet.getRow(0).getLastCellNum();
		for(int i=0;i<rowcnt;i++){
			for(int j=0;j<colcnt;j++){
				//Row row=sheet.getRow(i);
				//Cell cell=row.getCell(j);
				if(sheet.getRow(i).getCell(j).toString().equals(objectlabel)){
					data=sheet.getRow(rownum).getCell(j).toString();
				}				
			}
		}
		return data;
	}
}
