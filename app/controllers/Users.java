package controllers;

import java.util.List;

import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;
import models.User;

@With(Secure.class)
@Check("administrator")
public class Users extends Controller {

	public static void index() {
		List<User> users = User.findAll();
		
		if (users != null && users.size() > 0) {
			render(users); 
		}

		render();
		
	}
	
	public static void formUser(Long id) {
		List<User> users = User.findAll();

		if (id == null) {
			render(users);
		}

		User user = User.findById(id);
		render(users, user);
	}

	public static void saveUser(User user) {
		user.save();
		formUser(0L);
	}

	public static void deleteUser(Long id) {
		User user = User.findById(id);
		user.delete();
		formUser(0L);
	}

}