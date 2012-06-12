package controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.Task;
import models.TaskType;
import models.User;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

public class Application extends Controller {

    public static void index() {
    	List<Task> tasks = Task.findAll();
    	render(tasks);
    }

}