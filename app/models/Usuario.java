package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

	@Required
	public String nome;
	
	@Required
	public String email;

	@Required
	public String senha; 
	
	public boolean isAdmin; 
	
}
