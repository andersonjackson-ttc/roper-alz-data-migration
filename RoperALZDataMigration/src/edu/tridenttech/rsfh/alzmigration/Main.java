package edu.tridenttech.rsfh.alzmigration;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Main {

	public static void main(String[] args) throws InvalidFormatException, IOException 
	{
		String oldPath = "fakeDatabase1.xlsx";
		String newPath = "RoperSpreadSheet.xlsx";

		if (args.length > 0) {
			oldPath = args[0];
			newPath = args[1];
		}

		MigrationDriver driver = new MigrationDriver("log.txt");
		driver.performMigration(oldPath, newPath);
	}

}



