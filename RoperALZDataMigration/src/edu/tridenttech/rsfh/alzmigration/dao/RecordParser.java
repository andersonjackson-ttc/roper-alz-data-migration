package edu.tridenttech.rsfh.alzmigration.dao;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RecordParser {
	
	ExistingParticipantRecord exitsting = new ExistingParticipantRecord();
	
//	newParticipantRecord newRecord = new newParticipantRecord();
	
	String rowText="";
	
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
	




private NewParticipantRecord parse(ExistingParticipantRecord oldRec)
{
	NewParticipantRecord newRecord = new NewParticipantRecord();
//	tempText = ("^(?newRecord.setFirstName[a-zA-Z-.]) (?newRecord.setLastName[a-zA-Z-.])$");
	
	
	
	
	
	
	
	
	return newRecord;
}

private String verifyFirstName(String tempName)
{
	if(tempName.equals(""))
	{
		String msg = String.format("Problem with First Name");
		Alert emailError = new Alert(AlertType.ERROR, msg);
		emailError.showAndWait();
	}
	return tempName;
}


private String verifyLastName(String tempName)
{
	if(tempName.equals(""))
	{
		String msg = String.format("Problem with Last Name");
		Alert emailError = new Alert(AlertType.ERROR, msg);
		emailError.showAndWait();
	}
	
	return tempName;
}
}


//private String parseAddress(String tempAddress)
//{
//	
//	
//	
//	
//	return tempAddress
//}





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

