package controllers;

import java.util.List;

import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;
import models.Usuario;

@With(Secure.class)
@Check("administrador")
public class Usuarios extends Controller {

	public static void index() {
		List<Usuario> usuarios = Usuario.findAll();
		
		if (usuarios != null && usuarios.size() > 0) {
			render(usuarios); 
		}

		render();
	}
	
	public static void formUsuario(Long id) {
		List<Usuario> usuarios = Usuario.findAll();

		if (id == null) {
			render(usuarios);
		}

		Usuario usuario = Usuario.findById(id);
		render(usuarios, usuario);
	}

	public static void salvarUsuario(Usuario usuario) {
		usuario.save();
		formUsuario(0L);
	}

	public static void deletarUsuario(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		formUsuario(0L);
	}

}