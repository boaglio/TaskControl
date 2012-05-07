package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {

	@Required
	public String name;
	
	@Required
	public String email;

	@Required
	public String password; 
	
	@Required
	public boolean admin; 
	
}
