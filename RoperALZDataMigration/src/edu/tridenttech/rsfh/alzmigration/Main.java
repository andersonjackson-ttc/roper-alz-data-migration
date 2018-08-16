package edu.tridenttech.rsfh.alzmigration;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Main {

	public static void main(String[] args) throws InvalidFormatException, IOException 
	{
		String oldPath = "fakeDatabase1.xlsx";
		String newPath = "RoperSpreadSheet2.xlsx";
		boolean failed = false;

		if (args.length > 0) {
			oldPath = args[0];
			newPath = args[1];
		}
		
		File in = new File(oldPath);
		File out = new File(newPath);
		if (!in.exists()) {
			System.out.printf("Input file %s does not exist.\n", oldPath);
			failed = true;
		}

		if (!out.exists()) {
			System.out.printf("Output file %s does not exist.\n", newPath);
			failed = true;
		}
		
		if (failed)
			System.exit(1);

		MigrationDriver driver = new MigrationDriver("log.txt");
		driver.performMigration(oldPath, newPath);
	}

}



