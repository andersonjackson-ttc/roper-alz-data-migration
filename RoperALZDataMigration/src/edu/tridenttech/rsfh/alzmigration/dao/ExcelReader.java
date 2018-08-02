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
	Workbook wb;
	int rowCount;
	
	
	
	public ExcelReader (String filePathName) throws IOException, InvalidFormatException
	{
		//create workbook
		wb = WorkbookFactory.create(new File(filePathName));
		rowCount = 0;
		
	}
		
	
	public ExistingParticipantRecord getNextRecord ()
	{
		//Get main data sheet
		Sheet sheet = wb.getSheetAt(0);
		
		ExistingParticipantRecord rd = new ExistingParticipantRecord();
		
		Row row = sheet.getRow(rowCount);
		int cellCount = 0;
		
		for (Cell cell: row)
		{
			//printCellValue(cell);
			storeRecord(rd,convertCellToString(cell), cellCount);
			cellCount++;
		}
		
		rowCount++;
		return rd;
	}
		
	public void close() throws IOException
	{
		//close workbook
		wb.close();
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
			rd.setMmseScores(cell);
			break;
		case 9:
			rd.setScaScores(cell);
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
	
	public boolean hasMoreRecords ()
	{
		Sheet sheet = wb.getSheetAt(0);
		System.out.print(sheet.getLastRowNum());
		
		if (rowCount + 1 > sheet.getLastRowNum())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
}
