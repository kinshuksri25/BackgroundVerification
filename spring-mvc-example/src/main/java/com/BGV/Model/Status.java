package main.java.com.BGV.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Status 
{
	@Id
	@Column(name = "STATUSNAME")
	private String statusName;
	
	@OneToMany(mappedBy = "" , cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Request> requestName;
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Request> getRequestName() {
		return requestName;
	}

	public void setRequestName(List<Request> requestName) {
		this.requestName = requestName;
	}
}
