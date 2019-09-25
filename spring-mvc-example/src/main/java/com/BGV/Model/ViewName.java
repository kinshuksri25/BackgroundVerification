package main.java.com.BGV.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "viewtable")
public class ViewName 
{
	@Id
	@Column(name = "urlmapping")
	public String urlMapping;
	
	@Column(name = "viewname")
	public String viewname;
	
	@Column(name = "description")
	public String description;
	
	public String getUrlMapping() {
		return urlMapping;
	}

	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}

	public String getViewname() {
		return viewname;
	}

	public void setViewname(String viewname) {
		this.viewname = viewname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
