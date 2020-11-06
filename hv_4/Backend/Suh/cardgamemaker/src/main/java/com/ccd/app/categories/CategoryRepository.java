package com.ccd.app.categories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ccd.app.model.Categories;


@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {

	Categories findByCategoryName(String categoryName);
//	List<Categories> getCategoryList();
	
}
