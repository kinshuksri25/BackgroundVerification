package main.java.com.BGV.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menutab")
public class MenuTab {

	@Id
	@Column(name = "TABNAME")
	private String tabName;
	@Column(name = "TABDESCRIPTION")
	private String tabDescription;
	@Column(name = "ADMINTAB")
	private int adminTab;
	@Column(name = "URLMAPPING")
	private String urlmapping;
	
	public String getUrlmapping() {
		return urlmapping;
	}
	public void setUrlmapping(String urlmapping) {
		this.urlmapping = urlmapping;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getTabDescription() {
		return tabDescription;
	}
	public void setTabDescription(String tabDescription) {
		this.tabDescription = tabDescription;
	}
	public int getIsAdmin() {
		return adminTab;
	}
	public void setIsAdmin(int adminTab) {
		this.adminTab = adminTab;
	}
}
