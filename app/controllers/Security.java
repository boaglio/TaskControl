package controllers;

import models.User;

public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
        User user = User.find("byEmail", username).first();
        return user != null && user.password.equals(password);
    }
    
    static boolean check(String profile) {
        User user = User.find("byEmail", connected()).first();
        if (user != null && "administrator".equals(profile)) {
            return user.admin;
        }
        else {
            return false;
        }
    }    
}