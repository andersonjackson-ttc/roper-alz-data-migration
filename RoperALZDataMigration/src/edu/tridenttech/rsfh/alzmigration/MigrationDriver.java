package edu.tridenttech.rsfh.alzmigration;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import edu.tridenttech.rsfh.alzmigration.dao.ExcelReader;
import edu.tridenttech.rsfh.alzmigration.dao.ExcelWriter;
import edu.tridenttech.rsfh.alzmigration.dao.ExistingParticipantRecord;
import edu.tridenttech.rsfh.alzmigration.dao.NewParticipantRecord;
import edu.tridenttech.rsfh.alzmigration.dao.RecordParser;
import edu.tridenttech.rsfh.alzmigration.logging.AnomalyLogger;
import edu.tridenttech.rsfh.alzmigration.logging.AnomalyLogger.ErrorType;


public class MigrationDriver 

{
	public MigrationDriver(String loggerPath) throws FileNotFoundException
	{
		AnomalyLogger.getInstance(loggerPath);
		
	}

	
	public void performMigration(String oldFile, String newFile)
    {
	RecordParser parser = new RecordParser();
	ExcelReader reader;
	ExcelWriter writer;
	try {
		reader = new ExcelReader(oldFile);
	} catch (InvalidFormatException e) {
		e.printStackTrace();
		System.err.println("File not found");
	} catch (IOException e) {
		e.printStackTrace();
		System.err.println("File not found");
	}
	try {
		writer = new ExcelWriter(newFile);
	} catch (InvalidFormatException e) {
		e.printStackTrace();
		System.err.println("File not found");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.err.println("File not found");
	} catch (IOException e) {
		e.printStackTrace();
		System.err.println("File not found");
	}
	while (reader.hasMoreRecords()) {
	ExistingParticipantRecord record = reader.getNextRecord();

	NewParticipantRecord participant = parser.parse(record);
	writer.writeRecord(participant);
	}

	reader.close();

	}
}