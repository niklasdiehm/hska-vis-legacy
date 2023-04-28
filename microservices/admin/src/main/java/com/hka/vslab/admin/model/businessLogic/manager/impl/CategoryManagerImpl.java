package com.hka.vslab.admin.model.businessLogic.manager.impl;


import com.hka.vslab.admin.model.businessLogic.manager.CategoryManager;
import com.hka.vslab.admin.model.database.dataAccessObjects.CategoryDAO;
import com.hka.vslab.admin.model.database.dataobjects.Category;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager{
	private CategoryDAO helper;
	
	public CategoryManagerImpl() {
		helper = new CategoryDAO();
	}

	public List<Category> getCategories() {
		return helper.getObjectList();
	}

	public Category getCategory(int id) {
		return helper.getObjectById(id);
	}

	public Category getCategoryByName(String name) {
		return helper.getObjectByName(name);
	}

	public void addCategory(String name) {
		Category cat = new Category(name);
		helper.saveObject(cat);

	}

	public void delCategory(Category cat) {
	
// 		Products are also deleted because of relation in Category.java 
		helper.deleteById(cat.getId());
	}

	public void delCategoryById(int id) {
		
		helper.deleteById(id);
	}
}
