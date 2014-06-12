package com.example.jsfdemo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.jsfdemo.dao.PersonDao;



public class Person {
	
	private Integer custId;
	private String firstName = "unknown";
	private String zipCode = "";
	private String pin = "";
	private Date dateOfBirth = new Date();
	private double weight;
	private boolean married;
	private int numOfChildren;
	private String sd,msg,selectedname;
	
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Size(min = 2, max = 20)
	
	public Integer getCustId() {
        return this.custId;
    }
 
    public void setCustId(Integer custId) {
        this.custId = custId;
    }
	
	public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
	public String getSd() {
        return sd;
    }
 
    public void setSd(String sd) {
        this.sd = sd;
    }
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Pattern(regexp = "[0-9]{2}-[0-9]{3}")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Size(min = 2)
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	@Min(0)
	public int getNumOfChildren() {
		return numOfChildren;
	}
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	
	@Past
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	 public void saveCustomer() {
	        try {
	            Date d = sdf.parse(sd);
	            System.out.println(d);
	            this.dateOfBirth = d;
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        PersonDao dao = new PersonDao();
	        dao.addPerson(this);
	        this.msg = "Member Info Saved Successfull!";
	        clearAll();
	    }
	    public void updatePerson() {
	        try {
	            Date d = sdf.parse(sd);
	            System.out.println(d);
	            this.dateOfBirth = d;
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        PersonDao dao = new PersonDao();
	        dao.updatePerson(this);
	        this.msg = "Member Info Update Successfull!";
	        clearAll();
	    }
	    public void deletePerson() {
	        PersonDao dao = new PersonDao();
	        dao.deletePerson(custId);
	        this.msg = "Member Info Delete Successfull!";
	        clearAll();
	    }
	 
	    public List<Person> getAllCustomers() {
	        List<Person> users = new ArrayList<Person>();
	        PersonDao dao = new PersonDao();
	        users = dao.getAllPersons();
	        return users;
	    }
	 
	    public void fullInfo() {
	        PersonDao dao = new PersonDao();
	        List<Person> lc = dao.getCustomerById(selectedname);
	        System.out.println(lc.get(0).firstName);
	        this.custId = lc.get(0).custId;
	        this.firstName = lc.get(0).firstName;
	        this.zipCode = lc.get(0).zipCode;
	        this.pin = lc.get(0).pin;
	        this.dateOfBirth = lc.get(0).dateOfBirth;
	        this.sd = sdf.format(dateOfBirth);
	    }
	 
	    private void clearAll() {
	        this.firstName = "";
	        this.zipCode = "";
	        this.sd = "";
	        this.pin = "";
	        this.custId=0;
	    }
	}	


