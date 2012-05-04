package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class TaskType extends Model {
	
	public String description;
	
	public Integer priority;

}
