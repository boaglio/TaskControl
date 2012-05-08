package controllers;

import java.util.List;
import models.Task;
import models.TaskType;
import models.User;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
    	List<Task> tasks = Task.findAll();
    	render(tasks);
    }
    
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
    	index();
    }
    
    @Check("administrator")
    public static void deleteTask(Long id) {
    	Task task = Task.findById(id);
    	task.delete();
    	index();
    }

}