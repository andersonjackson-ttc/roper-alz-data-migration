package edu.tridenttech.rsfh.alzmigration;

import java.io.FileNotFoundException;

import edu.tridenttech.rsfh.alzmigration.dao.ExistingParticipantRecord;
import edu.tridenttech.rsfh.alzmigration.logging.AnomalyLogger;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		AnomalyLogger logger = AnomalyLogger.getInstance("test.txt");
		ExistingParticipantRecord record = new ExistingParticipantRecord();
		record.setFirstName("Christopher");
		record.setLastName("Alzmigration");
		record.setDob("01/01/1965");
		logger.Log(record, AnomalyLogger.ErrorType.DOB, "MSRE SCORES BAD");
		

	}

}
