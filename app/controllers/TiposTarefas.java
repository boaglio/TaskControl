package controllers;

import java.util.List;

import models.TipoTarefa;

import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("administrador")
public class TiposTarefas extends Controller {

	 public static void formTipoTarefa(Long id) {
	    	List<TipoTarefa> tipoTarefas = TipoTarefa.findAll();
	    	
	    	if (id == null) {
	    		render(tipoTarefas);
			}
	    	
	    	TipoTarefa tipoTarefa = TipoTarefa.findById(id);
	    	render(tipoTarefas, tipoTarefa);
	    }
	    
	    public static void salvarTipoTarefa(@Required TipoTarefa tipoTarefa) {
	    	validation.required(tipoTarefa.descricao);
	    	
	    	if(validation.hasErrors()) {
	    		flash.error("tipoTarefa.required"); // coloca o erro no request para ser exibido.
	    		formTipoTarefa(0L); // chama novamente o formulario
	    	}
	    	
	    	tipoTarefa.save(); // persite o objeto
	    	formTipoTarefa(0L);
	    }
	    
	    public static void deletarTipoTarefa(Long id) {
	    	TipoTarefa tipoTarefa = TipoTarefa.findById(id);
	    	tipoTarefa.delete();
	    	formTipoTarefa(0L);
	    }
}
