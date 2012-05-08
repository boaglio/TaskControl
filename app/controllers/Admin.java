package controllers;

import java.util.List;

import models.TaskType;
import models.User;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("administrator")
public class Admin extends Controller {

	public static void index(){
		List<User> users = User.findAll();
		render(users);
	}
	
    public static void formUser(Long id) {
    	List<User> users = User.findAll();
    	
    	if(id == null) {
    		render(users);
    	}
    	
    	User user = User.findById(id);
    	render(users,user);
    }
    
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
    	formTaskType(0L);
    }
    
    public static void deleteTaskType(Long id) {
    	TaskType taskType = TaskType.findById(id);
    	taskType.delete();
    	formTaskType(0L);
    }
    
    public static void saveUser(User user) {
    	user.save();
    	formUser(0L);
    }
    
    public static void deleteUser(Long id ) {
    	User user = User.findById(id);
    	user.delete();
    	formUser(0L);
    }

	
}
