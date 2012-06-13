package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class TipoTarefa extends Model {
	
	public String descricao;
	
	public Integer prioridade;

}
