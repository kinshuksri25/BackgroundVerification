package main.java.com.BGV.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name= "ACCOUNTNAME")
public class AccountName 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name="ACCOUNTID")
	private int accountID;
	@Column(name="ACCOUNTNAME")
	private String accountName;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="BUSINESSVERITCALID") 
	private BusinessVertical businessVertical;
	
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BusinessVertical getBusinessVertical() {
		return businessVertical;
	}

	public void setBusinessVertical(BusinessVertical businessVertical) {
		this.businessVertical = businessVertical;
	}

}
