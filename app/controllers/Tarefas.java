package controllers;

import java.util.List;

import play.mvc.Controller;
import play.mvc.With;

import models.Tarefa;
import models.TipoTarefa;
import models.Usuario;

@With(Secure.class)
public class Tarefas extends Controller {

	public static void formTarefa(Long id) {
    	List<Usuario> usuarios = Usuario.findAll();
    	List<TipoTarefa> tiposTarefa = TipoTarefa.findAll();
    	
    	if(id == null) {
    		render(usuarios, tiposTarefa);
    	}
    	
    	Tarefa tarefa = Tarefa.findById(id);
    	render(tarefa, usuarios, tiposTarefa);
    }
    
    public static void salvarTarefa(Tarefa t) {
    	t.save();
    	Application.index();
    }
    
    @Check("administrator")
    public static void deletarTarefa(Long id) {
    	Tarefa tarefa = Tarefa.findById(id);
    	tarefa.delete();
    	Application.index();
    }
	
}
