package controllers;

import java.util.List;

import models.TaskType;

import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("administrator")
public class TaskTypes extends Controller {

	 public static void formTaskType(Long id) {
	    	List<TaskType> taskTypes = TaskType.findAll();
	    	
	    	if (id == null) {
	    		render(taskTypes);
			}
	    	
	    	TaskType taskType = TaskType.findById(id);
	    	render(taskTypes, taskType);
	    }
	    
	    public static void saveTasktype(@Required TaskType tasktype) {
	    	validation.required(tasktype.description);
	    	
	    	if(validation.hasErrors()) {
	    		flash.error("taskType.required"); // coloca o erro no request para ser exibido.
	    		formTaskType(0L); // chama novamente o formulario
	    	}
	    	
	    	tasktype.save(); // persite o objeto
	    }
	    
	    public static void deleteTaskType(Long id) {
	    	TaskType taskType = TaskType.findById(id);
	    	taskType.delete();
	    	formTaskType(0L);
	    }
}
