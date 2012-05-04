package controllers;

import play.*;
import play.data.validation.Valid;
import play.db.jpa.JPABase;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	List<Task> tasks = Task.findAll();
        render(tasks);
    }
    
    public static void form(Long id) {
    	List<User> users = User.findAll();
    	List<TaskType> taskTypes = TaskType.findAll();
    	
    	if(id == null) {
    		render(users, taskTypes);
    	}
    	
    	Task task = Task.findById(id);
    	render(task, users, taskTypes);
    }
    
    public static void save(Task task){
    	task.save();
    	index();
    }
    
    public static void delete(Long id) {
    	Task task = Task.findById(id);
    	task.delete();
    	index();
    }

}