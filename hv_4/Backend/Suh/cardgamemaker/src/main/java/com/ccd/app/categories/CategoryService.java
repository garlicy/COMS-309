package com.ccd.app.categories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccd.app.model.Categories;

@Service
public interface CategoryService {

	void save(Categories cat);
	Categories findByCategoryName(String categoryName);
//	List<Categories> getCategoryList();
}
