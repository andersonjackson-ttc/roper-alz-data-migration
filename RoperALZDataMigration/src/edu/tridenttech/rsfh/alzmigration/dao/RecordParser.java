package edu.tridenttech.rsfh.alzmigration.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.tridenttech.rsfh.alzmigration.logging.AnomalyLogger;


public class RecordParser {
	
	ExistingParticipantRecord exitsting = new ExistingParticipantRecord();
	
	
	
	
	Pattern addressPattern = Pattern.compile ("^(?add[0-9]{1,6} [a-zA-Z]??[.]??[ ]?[a-zA-Z]+? [a-zA-Z]{2,7}[.]?[,]?)(?city[a-zA-Z]+[ ]?[a-zA-Z]*[,]?)(?state[a-zA-Z]{2}[.]??)(?zip[0-9]{5})$");


	
	
	
//	newParticipantRecord newRecord = new newParticipantRecord();
	
//	String rowText="";
	
//	String firstName = "";
//	String lastName = "";
//	String race = "";
//	String gender = "";
//	String DOB = "";
//	String address = "";
//	String city = "";
//	String state = "";
//	String zipCode = "";
//	String email = "";
//	String phone = "";
//	String testDate = "";
//	String testScore = "";
	




public NewParticipantRecord parse(ExistingParticipantRecord oldRec)
{
	NewParticipantRecord newRecord = new NewParticipantRecord();
//	tempText = ("^(?newRecord.setFirstName[a-zA-Z-.]) (?newRecord.setLastName[a-zA-Z-.])$");
	
	
	verifyFirstName(newRecord, oldRec.getFirstName());
	
	verifyLastName(newRecord, oldRec.getLastName());
	
	parseAddress(newRecord, oldRec.getAddress());
	
	verifyGender(newRecord, oldRec.getGender());
	
	verifyRace(newRecord, oldRec.getRace());
	
	
	
	
	
	
	
	return newRecord;
}

private String verifyFirstName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with First Name or empty");
		return null;
	}
	newRec.setFirstName(tempName);
	return null;
}


private String verifyLastName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Last Name or empty");
		return null;
	}
	newRec.setLastName(tempName);
	return null;
}

private String verifyGender(NewParticipantRecord newRec, String tempGen)
{
	if(tempGen.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Race or empty");
		return null;
	}
	newRec.setGender(tempGen);
	return null;
}

private String verifyRace(NewParticipantRecord newRec, String tempRace)
{
	if(tempRace.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Gender or empty");
		return null;
	}
	newRec.setRace(tempRace);
	return null;
}

private void verifyDate(NewParticipantRecord newRec, String tempDate)
{
	
	SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy");
	Date tempNewDate;

	if (!tempDate.equals(""))
	{
		try 
		{
			dateParser.setLenient(false);
			tempNewDate = dateParser.parse(tempDate);

			newRec.setDob(tempNewDate);
			
		}
		catch (ParseException e)
		{
			AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Gender or empty");
		}
	}
	
}






private String parseAddress(NewParticipantRecord newRec, String tempAddress)
{
	//	String add = "";
	//	String city = "";
	//	String state = "";
	//	String zip = "";

	Matcher addressMatcher = addressPattern.matcher(tempAddress);

	if(addressMatcher.matches())
	{
		//		newRec.setAddress() = addressMatcher.group("add");
		newRec.setAddress(addressMatcher.group("add"));
		//		city = addressMatcher.group("city");
		newRec.setCity(addressMatcher.group("city"));
		//		state = addressMatcher.group("state");
		newRec.setState(addressMatcher.group("state"));
		//		zip = addressMatcher.group("zip");
		newRec.setZip(addressMatcher.group("zip"));

	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Address");
		return null;
	}


return null;
}





//return tempText.matches("^[a-zA-Z-.]*$");  first and last name
//return tempTest.matches("^[[0-9]{1,3}]*?/[[0-9]{1,3}]*?$");       test scores

//return tempTest.matches("^[0-9]{1,3}[/-]?[0-9]{1,3}[/-][0-9]{2}|{4}$");  valid date formula

//return tempTest.matches("^[HhoOlLDd]+[ ]+[forFOR]+[ ]+[futureFUTURE]+[ ]+[STUDYstudy]+$");  hold for future study
//return tempTest.matches("^[enrolledENROLLED]+[ ]+[INin]+[ ]+[STUDYstudy]*?[ ]+?[a-zA-Z]+?$");    enrolled in study  *****
//return tempTest.matches("^[interestedINTERESTED]+[ ]+[inIN]+[ ]+[FUTUREfuture]+[researchRESEARCH]+$");   interested in future study
//return tempTest.matches("^[DdEeCcEeAaSsEeDd]{6,9}$"); // <--- Dead
//return tempTest.matches("^[NnOo]+[ ]+[FfUuRrTtHhEeRr]+[ ]+[AaCcTtIiOoNn]+$"); // <---no further action
//return tempTest.matches("^[STUDYstudy]+[ ]+[PARTICIPATIONparticipation]+[ ]+[ENDEDended]+$");   study participation ended

// phone number 999-999-9999

// make participant instance variable

}