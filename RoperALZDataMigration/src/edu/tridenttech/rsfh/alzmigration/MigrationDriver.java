package edu.tridenttech.rsfh.alzmigration;

import edu.tridenttech.rsfh.alzmigration.dao.ExcelReader;
import edu.tridenttech.rsfh.alzmigration.dao.ExistingParticipantRecord;
import edu.tridenttech.rsfh.alzmigration.dao.newParticipantRecord;

public class MigrationDriver 

{

	
	public void performMigration(String oldFile, String newFile)
    {
	RecordParser parser = new RecordParser();
	ExcelReader reader;
	ExcelWriter writer;
	reader = new ExcelReader(oldFile);
	writer = new ExcelWriter(newFile);
	while (reader.hasMoreRecords()) {
	ExistingParticipantRecord record = reader.getNextRecord();

	newParticipantRecord participant = parser.parse(record);
	writer.writeParticipantPage(participant);
	writer.writeTestsPage(participant);
	writer.writeStatusPage(participant);
	}
	reader.close();
	writer.close();
	}
}