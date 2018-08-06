package edu.tridenttech.rsfh.alzmigration.dao;

import java.util.Date;

public class NewParticipantRecord 
{
	private String lastName;
	private String firstName;
	private String race;
	private String gender;
	private Date dob;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String phone;
	private Date mmseDate;
	private String mmseScore;
	private Date scaDate;
	private String scaScore;
	private String status;
	private String pcp;
	private String spec;
	private String referal;
	private String mailing;
	private String dead;
	private String holdFutureStudy;
	private String interestFutureStudy;
	private String noAction;
	
	
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getMmseDate() {
		return mmseDate;
	}

	public void setMmseDate(Date mmseDate) {
		this.mmseDate = mmseDate;
	}

	public String getMmseScore() {
		return mmseScore;
	}

	public void setMmseScore(String mmseScore) {
		this.mmseScore = mmseScore;
	}

	public Date getScaDate() {
		return scaDate;
	}

	public void setScaDate(Date scaDate) {
		this.scaDate = scaDate;
	}

	public String getScaScore() {
		return scaScore;
	}

	public void setScaScore(String scaScore) {
		this.scaScore = scaScore;
	}

	public String getDead() {
		return dead;
	}

	public void setDead(String dead) {
		this.dead = dead;
	}

	public String getHoldFutureStudy() {
		return holdFutureStudy;
	}

	public void setHoldFutureStudy(String holdFutureStudy) {
		this.holdFutureStudy = holdFutureStudy;
	}

	public String getInterestFutureStudy() {
		return interestFutureStudy;
	}

	public void setInterestFutureStudy(String interestFutureStudy) {
		this.interestFutureStudy = interestFutureStudy;
	}

	public String getNoAction() {
		return noAction;
	}

	public void setNoAction(String noAction) {
		this.noAction = noAction;
	}
}
