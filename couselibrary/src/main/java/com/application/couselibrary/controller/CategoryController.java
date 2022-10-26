package com.application.couselibrary.controller;

import com.application.couselibrary.entity.Category;
import com.application.couselibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String findAllCategories(Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model){
        categoryService.deleteCategory(id);
        model.addAttribute("categories",categoryService.findAllCategories());
        return "categories";
    }

    @GetMapping("update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "update-category";
    }

    @PostMapping("/update-category/{id}")
    public String saveCategory(@PathVariable Long id, Model model, Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String showCreateCategory(Category category){
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "add-category";
        categoryService.createCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }
}









