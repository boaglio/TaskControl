package controllers;

import java.util.List;

import play.mvc.Controller;
import play.mvc.With;

import models.Task;
import models.TaskType;
import models.User;

@With(Secure.class)
public class TaskController extends Controller {

	public static void formTask(Long id) {
    	List<User> users = User.findAll();
    	List<TaskType> taskTypes = TaskType.findAll();
    	
    	if(id == null) {
    		render(users, taskTypes);
    	}
    	
    	Task task = Task.findById(id);
    	render(task, users, taskTypes);
    }
    
    public static void saveTask(Task task) {
    	task.save();
    	Application.index();
    }
    
    @Check("administrator")
    public static void deleteTask(Long id) {
    	Task task = Task.findById(id);
    	task.delete();
    	Application.index();
    }
	
}
