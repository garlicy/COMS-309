package com.ccd.app.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccd.app.model.Categories;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void save(Categories cat) {
		categoryRepository.save(cat);
	}
	
	@Override
	public Categories findByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}
	
//	@Override
//	public List<Categories> getCategoryList() {
//		return categoryRepository.findAll();
//	}
}
