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
	Pattern emailPattern = Pattern.compile("^[a-zA-Z_0-9-.]*@[a-zA-Z_0-9.-]*.[a-zA-Z]*$");
	Pattern phonePattern = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
	Pattern scoresPattern = Pattern.compile("^(?date([0-9]{1,3}[/-]?[0-9]{1,3}[/-]([0-9]{2}|[0-9]{4}))*)[-]?[ ]?(?score([[0-9]{1,2}]*?/[[0-9]{1,2}]*?)*)$");
	Pattern namePattern = Pattern.compile("^[a-zA-Z.-() ]*$");
	Pattern referalPattern = Pattern.compile("^[a-zA-Z0-9-.,()// ]$");
	





public NewParticipantRecord parse(ExistingParticipantRecord oldRec)
{
	NewParticipantRecord newRecord = new NewParticipantRecord();
//	tempText = ("^(?newRecord.setFirstName[a-zA-Z-.]) (?newRecord.setLastName[a-zA-Z-.])$");
	
	
	verifyFirstName(newRecord, oldRec.getFirstName());
	
	verifyLastName(newRecord, oldRec.getLastName());
	
	parseAddress(newRecord, oldRec.getAddress());
	
	verifyGender(newRecord, oldRec.getGender());
	
	verifyRace(newRecord, oldRec.getRace());
	
	verifyEmail(newRecord, oldRec.getEmail());
	
	verifyPhone(newRecord, oldRec.getPhone());
	
	verifyDate(newRecord, oldRec.getDob());
	
	verifyMMSEDateScores(newRecord, oldRec.getMMSE);
	
	verifySCADateScores(newRecord, oldRec.getSCA);
	
	verifyPCP(newRecord, oldRec.getPcp());
	
	verifySpecialist(newRecord, oldRec.getSpec());
	
	verifyReferal(newRecord, oldRec.getReferal());
	
	verifyMailing(newRecord, oldRec.getMailing());
	
	return newRecord;
}

private void verifySpecialist(NewParticipantRecord newRec, String tempSpecial)
{
	Matcher specialMatch = namePattern.matcher(tempSpecial);
	
	if(specialMatch.matches())
	{
		newRec.setSpec(tempSpecial);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.SPECIALIST, "Problem with Specialist");
	}
}

private void verifyReferal(NewParticipantRecord newRec, String tempReferal)
{
	Matcher referalMatch = referalPattern.matcher(tempReferal);
	
	if(referalMatch.matches())
	{
		newRec.setReferal(tempReferal);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.REFERAL, "Problem with Referal");
	}
	
}

private void verifyMailing(NewParticipantRecord newRec, String tempMailing)
{
	if(tempMailing.matches("^[YNyn ]{1}$"))
	{
		newRec.setReferal(tempMailing);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.MAILING, "Problem with Mailing");
	}
	
}

private void verifyPCP(NewParticipantRecord newRec, String temppcp) 
{
	Matcher pcpMatch = namePattern.matcher(temppcp);
	
	if(pcpMatch.matches())
	{
		newRec.setPcp(temppcp);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.PCP, "Problem with PCP");
	}
}

private String verifyFirstName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.FIRST_NAME, "Problem with First Name or empty");
		return null;
	}
	newRec.setFirstName(tempName);
	return null;
}


private String verifyLastName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.LAST_NAME, "Problem with Last Name or empty");
		return null;
	}
	newRec.setLastName(tempName);
	return null;
}

private String verifyGender(NewParticipantRecord newRec, String tempGen)
{
	if(tempGen.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.GENDER, "Problem with Race or empty");
		return null;
	}
	newRec.setGender(tempGen);
	return null;
}

private String verifyRace(NewParticipantRecord newRec, String tempRace)
{
	if(tempRace.equals(""))
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.RACE, "Problem with Gender or empty");
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
			AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.DOB, "Problem with DOB or empty");
		}
	}
	
}

private void verifyEmail(NewParticipantRecord newRec, String tempEmail)
{
	Matcher emailMatch = emailPattern.matcher(tempEmail);
	
	if(emailMatch.matches())
	{
		newRec.setEmail(tempEmail);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.EMAIL, "Problem with Email");
	}
}

private void verifyPhone(NewParticipantRecord newRec, String tempPhone)
{
	Matcher phoneMatch = phonePattern.matcher(tempPhone);
	
	if(phoneMatch.matches())
	{
		newRec.setPhone(tempPhone);
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.PHONE, "Problem with Phone");
	}
}

private void verifyMMSEDateScores(NewParticipantRecord newRec, String tempScores)
{
	Matcher scoresMatch = scoresPattern.matcher(tempScores);
	
	if(scoresMatch.matches())
	{
		newRec.setMmseDate(scoresPattern.group("date"));
		newRec.setMmseScore(scoresPattern.group("score"));
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.MMSE, "Problem with MMSE Score or date");
	}
}

private void verifySCADateScores(NewParticipantRecord newRec, String tempScores)
{
	Matcher scoresMatch = scoresPattern.matcher(tempScores);
	
	if(scoresMatch.matches())
	{
		newRec.setScaDate(scoresPattern.group("date"));
		newRec.setScaScore(scoresPattern.group("score"));
	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.SCA, "Problem with SCA Score or date");
	}
}




private void parseAddress(NewParticipantRecord newRec, String tempAddress)
{

	Matcher addressMatcher = addressPattern.matcher(tempAddress);

	if(addressMatcher.matches())
	{
		newRec.setAddress(addressMatcher.group("add"));
		newRec.setCity(addressMatcher.group("city"));
		newRec.setState(addressMatcher.group("state"));
		newRec.setZip(addressMatcher.group("zip"));

	}
	else
	{
		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Address");

	}



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