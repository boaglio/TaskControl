package controllers;

import java.util.List;

import models.TaskType;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("administrator")
public class TaskTypeController extends Controller {

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
}
