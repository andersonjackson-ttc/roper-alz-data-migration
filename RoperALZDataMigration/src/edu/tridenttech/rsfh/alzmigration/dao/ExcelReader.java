package edu.tridenttech.rsfh.alzmigration.dao;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
				storeRecord(rd,convertCellToString(cell), cellCount);
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
	
	private static String convertCellToString(Cell cell) 
	{
	   
		//date format
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
				
		switch (cell.getCellTypeEnum()) 
	    {
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case STRING:
	            return cell.getRichStringCellValue().getString();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                return df.format(cell.getDateCellValue());
	            } else {
	                return String.valueOf(cell.getNumericCellValue());
	            }
	        case BLANK:
	            return " ";
	        default:
	            return " ";
	    }
	}
	
	public static void storeRecord(ExistingParticipantRecord rd, String cell, int count)
	{
		switch (count)
		{
		case 0:
			rd.setLastName(cell);
			break;
		case 1:
			rd.setFirstName(cell);
			break;
		case 2:
			rd.setRace(cell);
			break;
		case 3:
			rd.setGender(cell);
			break;
		case 4:
			rd.setDob(cell);
			break;
		case 5:
			rd.setAddress(cell);
			break;
		case 6:
			rd.setEmail(cell);
			break;
		case 7:
			rd.setPhone(cell);
			break;
		case 8:
			rd.setScores(cell);
			break;
		case 10:
			rd.setStatus(cell);
			break;
		case 11:
			rd.setPcp(cell);
			break;
		case 12:
			rd.setSpec(cell);
			break;
		case 13:
			rd.setReferal(cell);
			break;
		case 14:
			rd.setMailing(cell);
			break;
		default:
			break;
		}
	}
	
	public static boolean writeRecord(ExistingParticipantRecord rd)
	{
		String file = "RoperSpreadSheet.xlsx";
		System.out.println("Write record");
		
		try
		{
			FileInputStream inputStream = new FileInputStream(new File(file));
			Workbook workbook = WorkbookFactory.create(inputStream);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			
			Row row = sheet.createRow(++rowCount);
			
			
			CellStyle cellStyle = workbook.createCellStyle();
			CreationHelper createHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
			
			
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
