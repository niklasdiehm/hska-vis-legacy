package com.hka.vslab.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hka.vslab.admin.model.businessLogic.manager.UserManager;
import com.hka.vslab.admin.model.businessLogic.manager.impl.UserManagerImpl;
import com.hka.vslab.admin.model.database.dataobjects.User;

import com.opensymphony.xwork2.ActionSupport;

@RestController
public class AdminController extends ActionSupport {
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        UserManager myCManager = new UserManagerImpl();
		
		// Get user from DB:
		User user = myCManager.getUserByUsername(username);
        
        if (user != null) {
			// Is the password correct?
			if (user.getPassword().equals(password)) {
				return "success";
			}
			else {
				addActionError(getText("error.password.wrong"));
			}
		}
		else {
			addActionError(getText("error.username.wrong"));
		}

		return "null";
	}
}
