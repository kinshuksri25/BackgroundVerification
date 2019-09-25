package main.java.com.BGV.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passwordtable")
public class EmployeeLogin 
{
	@Id
	@Column(name = "employee_id")
	private String emp_ID;

	@Column(name="password")
	private String password;
	
	public String getEmp_ID() {
		return emp_ID;
	}
	public void setEmp_ID(String emp_ID) {
		this.emp_ID = emp_ID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

