package edu.tridenttech.rsfh.alzmigration.logging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.tridenttech.rsfh.alzmigration.dao.ExistingParticipantRecord;

public class AnomalyLogger 
{
	
		public enum ErrorType
		{
			ADDRESS, 
			MMSE,
			SCA,
			STATUS,
			DOB,
			PHONE,
			EMAIL,
			FIRST_NAME,
			LAST_NAME,
			GENDER,
			RACE,
			PCP,
			SPECALIST,
			REFERAL,
			MAILING
			

			
		}
		

	    // static variable single_instance of type Singleton
	    private static AnomalyLogger instance = null;
	    private PrintStream writer;
	 
	    // variable of type String
	    public String filename;
	 
	    // private constructor restricted to this class itself
	    private AnomalyLogger(String filename) throws FileNotFoundException
	    {
	    	PrintStream writer = new PrintStream(filename);
	    	this.filename = filename;
	    	writer.close();
	    	
	    }
	 
	    // static method to create instance of Singleton class
	    public static AnomalyLogger getInstance(String filename) throws FileNotFoundException
	    {
	        if (instance == null)
	            instance = new AnomalyLogger(filename);
	 
	        return instance;
	    }
	    
	    public static AnomalyLogger getInstance()
	    {
	    	if (instance == null)
	    		System.err.println("Log file not opened");
	    	
	    	return instance;
	    }
	    
	    public void Log(ExistingParticipantRecord record,  ErrorType type, String message)
	    {
	    	PrintStream writer;
			try {
				writer = new PrintStream(new FileOutputStream(filename, true));
				writer.printf("%-20s%-20s%-10s%10s%20s", record.getFirstName(), record.getLastName(), record.getDob(), type, message);
		    	writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	     
	    
	}



	



	
