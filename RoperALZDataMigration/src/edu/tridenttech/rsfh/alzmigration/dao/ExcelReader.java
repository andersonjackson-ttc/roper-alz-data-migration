package edu.tridenttech.rsfh.alzmigration.dao;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelReader
{
	public static final String XLSX_FILE_PATH = "fakeDatabase1.xlsx";
	
	public void createWorkbook () throws InvalidFormatException, IOException
	{
	
		
		//create workbook
		Workbook workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
		
		//Get main data sheet
		Sheet sheet = workbook.getSheetAt(0);
		
		ExistingParticipantRecord rd = new ExistingParticipantRecord();
		
		
		//Use for each 
		System.out.println("Go through rows and columns");
		int count = 0;
		int rowCount = 0;
		int cellCount = 0;
		
		for (Row row: sheet) 
		{
			for (Cell cell: row)
			{
				//printCellValue(cell);
				storeRecord(rd,cell, cellCount);
				cellCount++;
			}
			writeRecord(rd);
			rowCount++;
			cellCount = 0;
			count++;
			if (count > 12)
			{
				break;
			}
			
		}
		
		System.out.println(rd.getLastName());
		
		//close workbook
		workbook.close();
	}
	
	private static void printCellValue(Cell cell) 
	{
	    switch (cell.getCellTypeEnum()) 
	    {
	        case BOOLEAN:
	            System.out.print(cell.getBooleanCellValue());
	            break;
	        case STRING:
	            System.out.print(cell.getRichStringCellValue().getString());
	            break;
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                System.out.print(cell.getDateCellValue());
	            } else {
	                System.out.print(cell.getNumericCellValue());
	            }
	            break;
	        case FORMULA:
	            System.out.print(cell.getCellFormula());
	            break;
	        case BLANK:
	            System.out.print("");
	            break;
	        default:
	            System.out.print("");
	    }

	    System.out.print("\t");
	}
	
	public static void storeRecord(ExistingParticipantRecord rd, Cell cell, int count)
	{
		switch (count)
		{
		case 0:
			rd.setLastName(cell.getRichStringCellValue().getString());
			break;
		case 1:
			rd.setFirstName(cell.getRichStringCellValue().getString());
			break;
		case 2:
			rd.setRace(cell.getRichStringCellValue().getString());
			break;
		case 3:
			rd.setGender(cell.getRichStringCellValue().getString());
			break;
		case 4:
			rd.setDob(cell.getDateCellValue());
			break;
		case 5:
			rd.setAddress(cell.getRichStringCellValue().getString());
			break;
		case 6:
			rd.setEmail(cell.getRichStringCellValue().getString());
			break;
		case 7:
			rd.setPhone(cell.getRichStringCellValue().getString());
			break;
		case 8:
			rd.setScores(cell.getRichStringCellValue().getString());
			break;
		case 10:
			if (cell.getCellTypeEnum() == CellType.NUMERIC)
			{
				System.out.println("oops");
				break;
			}
			rd.setStatus(cell.getRichStringCellValue().getString());
			break;
		case 11:
			rd.setPcp(cell.getRichStringCellValue().getString());
			break;
		case 12:
			rd.setSpec(cell.getRichStringCellValue().getString());
			break;
		case 13:
			rd.setReferal(cell.getRichStringCellValue().getString());
			break;
		case 14:
			rd.setMailing(cell.getRichStringCellValue().getString());
			break;
		default:
			System.out.printf("Issue with cell %d", count);
			break;
		}
	}
	
	public static boolean writeRecord(ExistingParticipantRecord rd)
	{
		String file = "RoperSpreadSheet.xlsx";
		
		try
		{
			FileInputStream inputStream = new FileInputStream(new File(file));
			Workbook workbook = WorkbookFactory.create(inputStream);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			
			Row row = sheet.createRow(++rowCount);
			
			Cell cell = row.createCell(1);
			cell.setCellValue(rd.getLastName());
			
			cell = row.createCell(2);
			cell.setCellValue(rd.getFirstName());
			
			cell = row.createCell(3);
			cell.setCellValue(rd.getDob());
			
			cell = row.createCell(5);
			cell.setCellValue(rd.getRace());
			
			cell = row.createCell(6);
			cell.setCellValue(rd.getGender());
			
			cell = row.createCell(7);
			cell.setCellValue(rd.getAddress());
			
			cell = row.createCell(8);
			cell.setCellValue(rd.getEmail());
			
			cell = row.createCell(9);
			cell.setCellValue(rd.getPhone());
			
			cell = row.createCell(15);
			cell.setCellValue(rd.getScores());
			
			cell = row.createCell(16);
			cell.setCellValue(rd.getStatus());
			
			cell = row.createCell(10);
			cell.setCellValue(rd.getPcp());
			
			cell = row.createCell(11);
			cell.setCellValue(rd.getSpec());
			
			cell = row.createCell(12);
			cell.setCellValue(rd.getReferal());
			
			cell = row.createCell(13);
			cell.setCellValue(rd.getMailing());
			
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			
			return true;
		} catch (IOException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
