package publicisSapient.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public static Logger log = LogManager.getLogger(ExcelHelper.class);

	/***
	 * Pass the excelName without any extension and place the Excel file under
	 * GAFDefaultdata package.It will return and 2*2 object array.
	 * 
	 * @param excelName
	 * @param sheetName
	 * @param testCasename
	 * @return
	 */
	public Object[][] getExcelData(String excelName, String sheetName) {
		try {
			Object[][] dataSets = null;

			String excelLocation = "src/main/java/publicisSapient/GAFDefaultdata/" + excelName + ".xlsx";
			String fullExcelLocation = ResourceHelper.getResourcePath(excelLocation);

			FileInputStream fileInputStream = new FileInputStream(new File(fullExcelLocation));

			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int totalRows = sheet.getLastRowNum();
			System.out.println("Total Rows are: " + (totalRows + 1));

			int totalColoumns = sheet.getRow(0).getLastCellNum();
			System.out.println("Total Rows are: " + totalColoumns);

			dataSets = new Object[totalRows][totalColoumns];

			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getStringCellValue().contains("Test Case")) {
						i = 0;
						break;
					}

					switch (cell.getCellType()) {
					case STRING:
						dataSets[i - 1][j] = cell.getStringCellValue();
						j++;
						break;
					case NUMERIC:
						dataSets[i - 1][j] = cell.getNumericCellValue();
						j++;
						break;
					case BOOLEAN:
						dataSets[i - 1][j] = cell.getBooleanCellValue();
						j++;
						break;
					case FORMULA:
						dataSets[i - 1][j] = cell.getCellFormula();
						j++;
						break;
					default:
						System.out.println("no matching enum data found for cell number" + i + " " + j);
						j++;
						break;
					}

				}
			}

			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object[][] getTestDataFromExcel(String excelName, String sheetName, String testCaseName) {
		try {
			Object[][] dataSets = null;

			String excelLocation = "src/main/java/publicisSapient/GAFDefaultdata/" + excelName + ".xlsx";
			String fullExcelLocation = ResourceHelper.getResourcePath(excelLocation);

			FileInputStream fileInputStream = new FileInputStream(new File(fullExcelLocation));

			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int totalRow = sheet.getLastRowNum() + 1;
			System.out.println("Total row counts: "+ totalRow);
			int i = 0, j = 1;
			dataSets = new Object[i][j-1];
			for (int x = 0; x <= totalRow; x++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if (ce.contains(testCaseName)) {
					i++;
					int totalCell=r.getLastCellNum();
					System.out.println("Total Column counts: "+ totalCell);
					for (j = 1; j <= totalCell; j++) {
						XSSFCell cell = r.getCell(j);
						switch (r.getCell(j).getCellType()) {
						case STRING:
							dataSets[x][j] = cell.getStringCellValue();
							j++;
							break;
						case NUMERIC:
							dataSets[x][j] = cell.getNumericCellValue();
							j++;
							break;
						case BOOLEAN:
							dataSets[x][j] = cell.getBooleanCellValue();
							j++;
							break;
						case FORMULA:
							dataSets[x][j] = cell.getCellFormula();
							j++;
							break;
						default:
							System.out.println("no matching enum data found for cell number" + i + " " + j);
							j++;
							break;
						}
					}
				}
			}
			return dataSets;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void updateResult(String excelName, String sheetName, String testCaseName, String testStatus) {
		try {
			String excelLocation = "src/main/java/publicisSapient/GAFDefaultdata/" + excelName + ".xlsx";
			String fullExcelLocation = ResourceHelper.getResourcePath(excelLocation);
			FileInputStream file = new FileInputStream(new File(fullExcelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum() + 1;
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if (ce.contains(testCaseName)) {
					r.createCell(4).setCellValue(testStatus);
					file.close();
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData("TestData", "verifyLogin");
		// System.out.println(data);
		{
			// Loop through all rows
			for (int i = 0; i < data.length; i++) {
				// Loop through all elements of current row
				for (int j = 0; j < data[i].length; j++) {
					System.out.print(data[i][j] + " ");
				}
				System.out.println();
			}
		}

		/*
		 * excelHelper.updateResult("TestData", "verifyLogin", "Test2", "PASS");
		 * excelHelper.updateResult("TestData", "verifyLogin", "Test1", "FAIL");
		 * excelHelper.updateResult("TestData", "verifyLogin", "Test5", "PASS");
		 * System.out.println("Done");
		 */
	}
}
