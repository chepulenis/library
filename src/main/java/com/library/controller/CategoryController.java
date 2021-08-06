package com.library.controller;

import com.library.model.Category;
import com.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/categories", produces = "text/html")
    public String getCategories(Model model) {
        List<Category> categories = service.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories/categories";
    }

    @GetMapping(value = "/categories/{id}", produces = "text/html")
    public ModelAndView getCategoryById(@RequestParam long id) {
        Category category = service.findCategoryById(id);
        ModelAndView mav = new ModelAndView("categories/result");
        mav.addObject("category", category);
        return mav;
    }

    @GetMapping(value = "/categories/new-category", produces = "text/html")
    public String showNewForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/new-category";
    }

    @PostMapping(value = "/categories", produces = "text/html")
    public String save(@ModelAttribute("category") Category category) {
        service.createCategory(category);
        return "redirect:/categories";
    }

    @PutMapping(value = "/categories/{id}", produces = "text/html")
    public ModelAndView showEditForm(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("categories/edit-category");
        Category category = service.findCategoryById(id);
        mav.addObject("category", category);
        return mav;
    }

    @PutMapping(value = "/categories", produces = "text/html")
    public String update(@ModelAttribute("category") Category category) {
        service.updateCategory(category);
        return "redirect:/categories";
    }

    @DeleteMapping(value = "/categories/{id}", produces = "text/html")
    public String delete(@PathVariable(name = "id") long id) {
        service.deleteCategory(id);
        return "redirect:/categories";
    }

}
