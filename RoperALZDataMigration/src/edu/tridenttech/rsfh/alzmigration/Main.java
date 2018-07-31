package edu.tridenttech.rsfh.alzmigration;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import edu.tridenttech.rsfh.alzmigration.dao.ExcelReader;

public class Main {

	public static void main(String[] args) throws InvalidFormatException, IOException 
	{
		MigrationDriver driver = new MigrationDriver("log.txt");
		driver.performMigration("fakeDatabase1.xlsx", "RoperSpreadSheet.xlsx");
		

	}

}



