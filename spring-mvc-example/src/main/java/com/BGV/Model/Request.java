package main.java.com.BGV.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "requestID")
@Entity
@Table (name= "REQUEST")
public class Request 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REQUESTID")
	private int requestID;
	@Column(name="BUSINESSVERTICAL")
	private String businessVertical;
	@Column(name="ACCOUNTNAME")
	private String accountName;
	@Column(name="DATEOFSUBMISSION")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfSubmission; 
	@Column(name="FILELOCATION")
	private String fileLocation;
	
	//@JsonBackReference
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="EMPLOYEEDETAILSEMPID")
	private EmployeeDetails employeeDetails;
	
	//files
	@Transient
	private List<CommonsMultipartFile> supportingDocuments;
	
	@Column(name="reviewer")
	public String assignedReviewer;
	
	@Column(name="REVIEWERID")
	public String assignedReviewerID;
	
	@Column(name="REQUESTCREATOR")
	public String requestCreator;
	
	@Column(name="REQUESTCREATORID") 
	public String requestCreatorID;

	@Column(name="STATUS")
	public String status;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	public String getRequestCreatorID() {
		return requestCreatorID;
	}
	public void setRequestCreatorID(String requestCreatorID) {
		this.requestCreatorID = requestCreatorID;
	}
	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getBusinessVertical() {
		return businessVertical;
	}
	public void setBusinessVertical(String businessVertical) {
		this.businessVertical = businessVertical;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getDateOfSubmission() {
		return dateOfSubmission;
	}
	public void setDateOfSubmission(Date dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}
	public List<CommonsMultipartFile> getSupportingDocuments() {
		return supportingDocuments;
	}
	public void setSupportingDocuments(List<CommonsMultipartFile> supportingDocuments) {
		this.supportingDocuments = supportingDocuments;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getAssignedReviewer() {
		return assignedReviewer;
	}
	public void setAssignedReviewer(String assignedReviewer) {
		this.assignedReviewer = assignedReviewer;
	}
	public String getAssignedReviewerID() {
		return assignedReviewerID;
	}
	public void setAssignedReviewerID(String assignedReviewerID) {
		this.assignedReviewerID = assignedReviewerID;
	}
	public String getRequestCreator() {
		return requestCreator;
	}
	public void setRequestCreator(String requestCreator) {
		this.requestCreator = requestCreator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
