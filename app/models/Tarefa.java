package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Tarefa extends Model {
	
	@ManyToOne
	public TipoTarefa tipoTarefa;
	
	@ManyToOne
	public Usuario usuario;
	
	@Lob
	public String descricao;
	
	public Date dataInicio;
	
}
