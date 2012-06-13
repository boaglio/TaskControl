package controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.Tarefa;
import models.TipoTarefa;
import models.Usuario;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

public class Application extends Controller {

    public static void index() {
    	List<Tarefa> tarefas = Tarefa.findAll();
    	render(tarefas);
    }

}