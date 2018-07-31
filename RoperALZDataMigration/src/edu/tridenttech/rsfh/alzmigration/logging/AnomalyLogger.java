package edu.tridenttech.rsfh.alzmigration.logging;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.tridenttech.rsfh.alzmigration.dao.ExistingParticipantRecord;

public class AnomalyLogger 
{
	
		public enum ErrorType
		{
			ADDRESS, 
			MMSE_TEST_SCORE,
			SCA_TEST_SCORE,
			STATUS,
			DOB
		}
		

	    // static variable single_instance of type Singleton
	    private static AnomalyLogger instance = null;
	    private PrintStream writer;
	 
	    // variable of type String
	    public String s;
	 
	    // private constructor restricted to this class itself
	    private AnomalyLogger(String filename) throws FileNotFoundException
	    {
	    	writer = new PrintStream(filename);
	    	
	    }
	 
	    // static method to create instance of Singleton class
	    public static AnomalyLogger getInstance(String filename) throws FileNotFoundException
	    {
	        if (instance == null)
	            instance = new AnomalyLogger(filename);
	 
	        return instance;
	    }
	    
	    public static AnomalyLogger getInstance() throws FileNotFoundException
	    {
	    	if (instance == null)
	    		throw new FileNotFoundException();
	    	
	    	return instance;
	    	
	    	
	    	
	    }
	    
	    public void Log(ExistingParticipantRecord record,  ErrorType type, String message)
	    {
	    	writer.printf("%s%s%s%s%s", record.getFirstName(), record.getLastName(), record.getDob(), type, message);
	    	
	    	
	    }
	     
	    
	}



	



	
