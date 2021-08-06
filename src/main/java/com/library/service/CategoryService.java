package com.library.service;

import com.library.model.Category;
import com.library.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public Category findCategoryById(long id) {
        Category category = repository.findById(id).orElse(null);
        logger.info("Book category {} by id {} has been found", category, id);
        return category;
    }

    public List<Category> findAllCategories() {
        List <Category> categories = repository.findAll();
        logger.info("All book categories {} have been found", categories);
        return categories;
    }

    public void deleteCategory(long id) {
        logger.info("Book category {} deleted", id);
        repository.deleteById(id);
    }

    public Category updateCategory(Category category) {
        logger.info("Book category {} updated", category);
        repository.save(category);
        return category;
    }

    public Category createCategory(Category category) {
        logger.info("Book category {} created", category);
        repository.save(category);
        return category;
    }


}
