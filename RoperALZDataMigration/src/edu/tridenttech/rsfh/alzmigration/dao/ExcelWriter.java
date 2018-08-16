package edu.tridenttech.rsfh.alzmigration.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
		private String inPath;
		private FileInputStream inputStream;
		FileOutputStream outputStream;
		private int count;
		
		
		public ExcelWriter (String file) throws IOException, InvalidFormatException, FileNotFoundException
		{
			filePath = file;
			inPath = file;
			count = 0;
			inputStream = new FileInputStream(new File(inPath));
			wb = WorkbookFactory.create(inputStream);
			inputStream.close();
			
		}
		
			
		public boolean writeRecord(NewParticipantRecord rd) throws IOException, EncryptedDocumentException, InvalidFormatException
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
				
				cell = row.createCell(9);
				cell.setCellValue(rd.getCity());
				
				cell = row.createCell(10);
				cell.setCellValue(rd.getState());
				
				cell = row.createCell(11);
				cell.setCellValue(rd.getZip());
				
				cell = row.createCell(12);
				cell.setCellValue(rd.getEmail());
				
				cell = row.createCell(13);
				cell.setCellValue(rd.getPhone());
				
				cell = row.createCell('R' - 'A');
				cell.setCellValue(rd.getStatus());
				
				cell = row.createCell('S' - 'A');
				cell.setCellValue(rd.getDead());
				
				cell = row.createCell('T' - 'A');
				cell.setCellValue(rd.getPcp());
				
				cell = row.createCell('U' - 'A');
				cell.setCellValue(rd.getSpec());
				
				cell = row.createCell('W' - 'A');
				cell.setCellValue(rd.getReferal());
				
				cell = row.createCell('X' - 'A');
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
				
				count++;
				System.out.println(count);
				
				
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
		
		public void close() {
			try {
//				outputStream.close();
				outputStream = new FileOutputStream(filePath);
				wb.write(outputStream);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
