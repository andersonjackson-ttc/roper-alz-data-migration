package edu.tridenttech.rsfh.alzmigration.dao;

import java.util.Date;

public class ExistingParticipantRecord 
{
	private String lastName;
	private String firstName;
	private String race;
	private String gender;
	private Date dob;
	private String address;
	private String email;
	private String phone;
	private String scores;
	private String status;
	private String pcp;
	private String spec;
	private String referal;
	private String mailing;
	
	
	public void setLastName(String name)
	{
		lastName = name;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setFirstName(String name)
	{
		firstName = name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setRace(String field)
	{
		race = field;
	}
	
	public String getRace()
	{
		return race;
	}
	
	public void setGender(String field)
	{
		gender = field;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public void setDob(Date field)
	{
		dob = field;
	}
	
	public Date getDob()
	{
		return dob;
	}
	
	public void setAddress(String field)
	{
		address = field;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setEmail(String field)
	{
		email = field;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setPhone(String field)
	{
		phone = field;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setScores(String field)
	{
		scores = field;
	}
	
	public String getScores()
	{
		return scores;
	}
	
	public void setStatus(String field)
	{
		status = field;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setPcp(String field)
	{
		pcp = field;
	}
	
	public String getPcp()
	{
		return pcp;
	}
	
	public void setSpec(String field)
	{
		spec = field;
	}
	
	public String getSpec()
	{
		return spec;
	}
	
	public void setReferal(String field)
	{
		referal = field;
	}
	
	public String getReferal()
	{
		return referal;
	}
	
	public void setMailing(String field)
	{
		mailing = field;
	}
	
	public String getMailing()
	{
		return mailing;
	}
}
