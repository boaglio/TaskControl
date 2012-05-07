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
    
    @Check("administrator")
    public static void formUser(Long id) {
    	List<User> users = User.findAll();
    	
    	if(id == null) {
    		render(users);
    	}
    	
    	User user = User.findById(id);
    	render(users,user);
    }
    
    @Check("administrator")
    public static void formTaskType(Long id) {
    	List<TaskType> taskTypes = TaskType.findAll();
    	
    	if (id == null) {
    		render(taskTypes);
		}
    	
    	TaskType taskType = TaskType.findById(id);
    	render(taskTypes, taskType);
    }
    
    public static void saveTasktype(TaskType tasktype) {
    	tasktype.save();
    	index();
    }
    
    public static void deleteTaskType(Long id) {
    	TaskType taskType = TaskType.findById(id);
    	taskType.delete();
    	formTaskType(0L);
    }
    
    public static void saveUser(User user) {
    	user.save();
    	index();
    }
    
    public static void deleteUser(Long id ) {
    	User user = User.findById(id);
    	user.delete();
    	formUser(0L);
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