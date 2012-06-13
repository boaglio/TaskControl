package controllers;

import models.Usuario;

public class Security extends Secure.Security {
    
    static boolean authenticate(String login, String senha) {
        Usuario usuario = Usuario.find("byEmail", login).first();
        return usuario != null && usuario.senha.equals(senha);
    }
    
    static boolean check(String perfil) {
        Usuario usuario = Usuario.find("byEmail", connected()).first();
        if (usuario != null && "administrador".equals(perfil)) {
            return usuario.isAdmin;
        }
        else {
            return false;
        }
    }    
}