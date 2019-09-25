package main.java.com.BGV.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name= "employeedetails")
public class EmployeeDetails 
{
	@Id
	@Column(name="EMPLOYEEID")
	private String employeeID;
	@Column(name="EMPLOYEENAME")
	private String employeeName;
	@Column(name="COMPANYNAME")
	private String companyName;
	@Column(name="EMAILID")
	private String emailID;
	
	//@JsonManagedReference
	@OneToOne(mappedBy ="employeeDetails", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Request request;
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
}
