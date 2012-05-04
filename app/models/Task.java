package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Task extends Model {
	
	@ManyToOne
	public TaskType taskType;
	
	@ManyToOne
	public User user;
	
	@Lob
	public String description;
	
	public Date initialDate;
	
}
