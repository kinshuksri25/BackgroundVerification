package main.java.com.BGV.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name= "BUSINESSVERTICAL")
public class BusinessVertical 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name="VERTICALID")
	private int verticalID;
	@Column(name="VERTICALNAME")
	private String verticalName;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "businessVertical" , cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<AccountName> accountName;

	public int getVerticalID() {
		return verticalID;
	}

	public void setVerticalID(int verticalID) {
		this.verticalID = verticalID;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public List<AccountName> getAccountName() {
		return accountName;
	}

	public void setAccountName(List<AccountName> accountName) {
		this.accountName = accountName;
	}
}
