package com.hka.vslab.admin.model.businessLogic.manager;

import com.hka.vslab.admin.model.database.dataobjects.Role;
import com.hka.vslab.admin.model.database.dataobjects.User;


public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, Role role);
    
    public User getUserByUsername(String username);
    
    public boolean deleteUserById(int id);
    
    public Role getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);
}
