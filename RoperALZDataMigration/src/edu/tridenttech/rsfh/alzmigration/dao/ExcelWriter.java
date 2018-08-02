package edu.tridenttech.rsfh.alzmigration.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelWriter 
{
	
	private Workbook wb;
	private String filePath;
	private FileInputStream inputStream;
	
	
	public ExcelWriter (String file) throws IOException, InvalidFormatException, FileNotFoundException
	{
		filePath = file;
		inputStream = new FileInputStream(new File(filePath));
		wb = WorkbookFactory.create(inputStream);
	}
	
		
	public boolean writeRecord(NewParticipantRecord rd) throws IOException
	{
				
		try
		{
					
			//primary sheet
			
			Sheet sheet = wb.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			
			Row row = sheet.createRow(++rowCount);
			
			
			CellStyle cellStyle = wb.createCellStyle();
			CreationHelper createHelper = wb.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
			
			
			Cell cell = row.createCell(1);
			cell.setCellValue(rd.getLastName());
			
			cell = row.createCell(2);
			cell.setCellValue(rd.getFirstName());
			
			cell = row.createCell(3);
			cell.setCellValue(rd.getDob());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(5);
			cell.setCellValue(rd.getRace());
			
			cell = row.createCell(6);
			cell.setCellValue(rd.getGender());
			
			cell = row.createCell(7);
			cell.setCellValue(rd.getAddress());
			
			cell = row.createCell(8);
			cell.setCellValue(rd.getCity());
			
			cell = row.createCell(9);
			cell.setCellValue(rd.getState());
			
			cell = row.createCell(10);
			cell.setCellValue(rd.getZip());
			
			cell = row.createCell(11);
			cell.setCellValue(rd.getEmail());
			
			cell = row.createCell(12);
			cell.setCellValue(rd.getPhone());
			
			cell = row.createCell(13);
			cell.setCellValue(rd.getStatus());
			
			//cell = row.createCell(14);
			//cell.setCellValue(rd.Deseased());
			
			cell = row.createCell(15);
			cell.setCellValue(rd.getPcp());
			
			cell = row.createCell(16);
			cell.setCellValue(rd.getSpec());
			
			cell = row.createCell(17);
			cell.setCellValue(rd.getReferal());
			
			cell = row.createCell(18);
			cell.setCellValue(rd.getMailing());
			
			
			//Status Sheet
            sheet = wb.getSheetAt(4);
			
			rowCount = sheet.getLastRowNum();
			
			row = sheet.createRow(++rowCount);
			
			
			cell = row.createCell(2);
			cell.setCellValue(rd.getLastName());
			
			cell = row.createCell(1);
			cell.setCellValue(rd.getFirstName());
			
			cell = row.createCell(3);
			cell.setCellValue(rd.getDob());
			cell.setCellStyle(cellStyle);
			
			
			//Test Scores
            sheet = wb.getSheetAt(5);
			
			rowCount = sheet.getLastRowNum();
			
			row = sheet.createRow(++rowCount);
			
			
			cell = row.createCell(2);
			cell.setCellValue(rd.getLastName());
			
			cell = row.createCell(1);
			cell.setCellValue(rd.getFirstName());
			
			cell = row.createCell(3);
			cell.setCellValue(rd.getDob());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(4);
			cell.setCellValue(rd.getMmseDate());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(5);
			cell.setCellValue(rd.getMmseScore());
			
			cell = row.createCell(6);
			cell.setCellValue(rd.getScaDate());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(7);
			cell.setCellValue(rd.getScaScore());
			
			
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(filePath);
			wb.write(outputStream);
			wb.close();
			outputStream.close();
			
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}
