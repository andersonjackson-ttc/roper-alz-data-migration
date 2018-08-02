package edu.tridenttech.rsfh.alzmigration.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.tridenttech.rsfh.alzmigration.logging.AnomalyLogger;


public class RecordParser {
	
	ExistingParticipantRecord existing = new ExistingParticipantRecord();
	
	
	static String ADDR_PATTERN = "(?<add>\\d+[^,]+)";
	static String CITY_PATTERN = "(?<city>[A-Za-z][^,]+)";
	static String STATE_PATTERN = "(?<state>[a-zA-Z]{2})";
	static String ZIP_PATTERN = "(?<zip>[0-9]{5}(-[0-9]{4}){0,1})";
	static String EMPTY_ZIP_PATTERN = "(?<zip> *)";
	static String COMMA_WS = ", *";
	static String OPT_COMMA_WS = ",* *";
	static String ENDING_WS = "\\s*$";
	static String TWO_ADDR_PATTERN = "(?<add>\\d+[^,]+, *[^,]+)";
	static String SINGLE_CITY_PATTERN = "(?<city> [A-Za-z][^,]+)";


	private static String confidentMatch = ADDR_PATTERN + COMMA_WS + CITY_PATTERN + OPT_COMMA_WS + STATE_PATTERN + OPT_COMMA_WS + ZIP_PATTERN + ENDING_WS;
	private static Pattern confidentPattern = Pattern.compile(confidentMatch);
	
	private static String secondAddressMatch = TWO_ADDR_PATTERN + COMMA_WS + CITY_PATTERN + OPT_COMMA_WS + STATE_PATTERN + OPT_COMMA_WS + ZIP_PATTERN + ENDING_WS;
	private static Pattern secondAddressPattern = Pattern.compile(secondAddressMatch);

	private static String missingZipMatch = ADDR_PATTERN + COMMA_WS + CITY_PATTERN + OPT_COMMA_WS + STATE_PATTERN + EMPTY_ZIP_PATTERN + ENDING_WS;
	private static Pattern missingZipPattern = Pattern.compile(missingZipMatch);
	
	private static String questionableCityMatch = ADDR_PATTERN + SINGLE_CITY_PATTERN + COMMA_WS + STATE_PATTERN + "\\s*" + ZIP_PATTERN + ENDING_WS;
	private static Pattern questionableCityPattern = Pattern.compile(questionableCityMatch);
	
//	Pattern addressPattern = Pattern.compile ("^(?add[0-9]{1,6} [a-zA-Z]??[.]??[ ]?[a-zA-Z. ]+? [a-zA-Z]{2,7}[.]?[,]? )(?city[a-zA-Z]+[ ]?[a-zA-Z]*[,]?)(?state[a-zA-Z]{2}[.]??)(?zip([0-9]{5})?)$");                 
//												     [0-9]{1,6} [a-zA-Z]??[.]??[ ]?[a-zA-Z. ]+? [a-zA-Z]{2,7}[.]?[,]?        [a-zA-Z]+[ ]?[a-zA-Z]*[,]?        [a-zA-Z]{2}[.]??      ([0-9]{5})?
	Pattern emailPattern = Pattern.compile("^[a-zA-Z_0-9-.]*@[a-zA-Z_0-9.-]*.[a-zA-Z]*$");
	Pattern phonePattern = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
	Pattern scoresPattern = Pattern.compile("^(?<date>([0-9]{1,3}[/-]?[0-9]{1,3}[/-]([0-9]{2}|[0-9]{4}))*)[-]?[ ]?(?<score>([[0-9]{1,2}]*?/[[0-9]{1,2}]*?)*)$");
//	Pattern namePattern = Pattern.compile("^[a-zA-Z.-() ]*$");
//	Pattern referalPattern = Pattern.compile("^[a-zA-Z0-9-.,()// ]$");
	





public NewParticipantRecord parse(ExistingParticipantRecord oldRec)
{
	
	NewParticipantRecord newRecord = new NewParticipantRecord();
	existing = oldRec;
	
//	tempText = ("^(?newRecord.setFirstName[a-zA-Z-.]) (?newRecord.setLastName[a-zA-Z-.])$");
	
	
	verifyFirstName(newRecord, oldRec.getFirstName());
	
	verifyLastName(newRecord, oldRec.getLastName());
	
	parseAddress(newRecord, oldRec.getAddress());
	
	verifyGender(newRecord, oldRec.getGender());
	
	verifyRace(newRecord, oldRec.getRace());
	
	verifyEmail(newRecord, oldRec.getEmail());

	verifyPhone(newRecord, oldRec.getPhone());
	
	verifyDate(newRecord, oldRec.getDob());
	
	verifyMMSEDateScores(newRecord, oldRec.getMmseScores());
	
	verifySCADateScores(newRecord, oldRec.getScaScores());
	
	verifyPCP(newRecord, oldRec.getPcp());
	
	verifySpecialist(newRecord, oldRec.getSpec());
	
	verifyReferal(newRecord, oldRec.getReferal());
	
	verifyMailing(newRecord, oldRec.getMailing());
	
	return newRecord;
}

private void verifySpecialist(NewParticipantRecord newRec, String tempSpecial)
{
//	Matcher specialMatch = namePattern.matcher(tempSpecial);
	
	if(!tempSpecial.equals(""))
	{
		newRec.setSpec(tempSpecial);
	}
	else
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.SPECIALIST, "Empty Specialist");
	}
}

private void verifyReferal(NewParticipantRecord newRec, String tempReferal)
{	
	if(tempReferal != null && !tempReferal.equals(""))
	{
		newRec.setReferal(tempReferal);
	}
	else
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.REFERAL, "Empty Referal");
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
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.MAILING, "Problem with Mailing");
	}
	
}

private void verifyPCP(NewParticipantRecord newRec, String temppcp) 
{
	if(!temppcp.equals(""))
	{
		newRec.setPcp(temppcp);
	}
	else
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.PCP, "Empty PCP");
	}
}

private String verifyFirstName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.FIRST_NAME, "Problem with First Name or empty");
		return null;
	}
	newRec.setFirstName(tempName);
	return null;
}


private String verifyLastName(NewParticipantRecord newRec, String tempName)
{
	if(tempName.equals(""))
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.LAST_NAME, "Problem with Last Name or empty");
		return null;
	}
	newRec.setLastName(tempName);
	return null;
}

private String verifyGender(NewParticipantRecord newRec, String tempGen)
{
	if(tempGen.equals(""))
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.GENDER, "Problem with Race or empty");
		return null;
	}
	newRec.setGender(tempGen);
	return null;
}

private String verifyRace(NewParticipantRecord newRec, String tempRace)
{
	if(tempRace.equals(""))
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.RACE, "Problem with Gender or empty");
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
			AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.DOB, "Problem with DOB or empty");
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
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.EMAIL, "Problem with Email");
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
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.PHONE, "Problem with Phone");
	}
}

private void verifyMMSEDateScores(NewParticipantRecord newRec, String tempScores) 
{
	SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy");
	Date holdDate;
	Matcher scoresMatch = scoresPattern.matcher(tempScores);
	
	
	if(scoresMatch.matches())
	{
		try
		{
		dateParser.setLenient(false);
		holdDate = dateParser.parse(scoresMatch.group("date"));
		newRec.setMmseDate(holdDate);
		newRec.setMmseScore(scoresMatch.group("score"));
		}
		catch (ParseException e)
		{
			AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.DATE, "Problem with MMSE date");
		}
		
	}
	else
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.MMSE, "Problem with MMSE Score or date");
	}
}

private void verifySCADateScores(NewParticipantRecord newRec, String tempScores) 
{
	SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy");
	Date holdDate;
	Matcher scoresMatch = scoresPattern.matcher(tempScores);
	
	if(scoresMatch.matches())
	{
		try
		{
		dateParser.setLenient(false);
		holdDate = dateParser.parse(scoresMatch.group("date"));
		newRec.setScaDate(holdDate);
		newRec.setScaScore(scoresMatch.group("score"));
		}
		catch (ParseException e)
		{
			AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.DATE, "Problem with SCA date");
		}
	}
	else
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.SCA, "Problem with SCA Score or date");
	}
}




private void parseAddress(NewParticipantRecord newRec, String tempAddress)
{

//	Matcher addressMatcher = addressPattern.matcher(tempAddress);
//
//	if(addressMatcher.matches())
//	{
//		newRec.setAddress(addressMatcher.group("add"));
//		newRec.setCity(addressMatcher.group("city"));
//		newRec.setState(addressMatcher.group("state"));
//		newRec.setZip(addressMatcher.group("zip"));
//
//	}
//	else
//	{
//		AnomalyLogger.getInstance().Log(exitsting, AnomalyLogger.ErrorType.ADDRESS, "Problem with Address");
//
//	}

	if (setAddressFieldsFromPattern(newRec, confidentPattern, tempAddress)) 
	{
		// succeeded; do nothing
	} 
	else if (setAddressFieldsFromPattern(newRec, secondAddressPattern, tempAddress)) 
	{
		// succeeded; do nothing
	} 
	else if (setAddressFieldsFromPattern(newRec, missingZipPattern, tempAddress))
	{
		// succeeded; do nothing
	} 
	else if (setAddressFieldsFromPattern(newRec, questionableCityPattern, tempAddress)) 
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.ADDRESS, "possible bad city");
//		log(rawAddress.toString() + ": -- possible bad city");
	} 
	else 
	{
		AnomalyLogger.getInstance().Log(existing, AnomalyLogger.ErrorType.ADDRESS, "Problem with Address");
	}


}

private boolean setAddressFieldsFromPattern(NewParticipantRecord newRec, Pattern pattern, String address)
{
	Matcher addressMatcher = pattern.matcher(address);
	if(addressMatcher.matches()) 
	{
		setAddressValuesFromMatcher(newRec, addressMatcher); 
		return true;
	} 
	else 
	{
		return false;
	}
}

private void setAddressValuesFromMatcher(NewParticipantRecord newRec, Matcher matcher)
{
	newRec.setAddress(matcher.group("add").trim());
	newRec.setCity(matcher.group("city").trim());
	newRec.setState(matcher.group("state").trim());
	newRec.setZip(matcher.group("zip").trim());
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