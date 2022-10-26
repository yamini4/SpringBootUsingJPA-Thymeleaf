package com.project.cmsShoppingCart.controllers;

import com.project.cmsShoppingCart.models.CategoryRepository;
import com.project.cmsShoppingCart.models.data.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository ;

    @GetMapping
    public String index(Model model){
        List<Category> categories = categoryRepository.findAllByOrderBySortingAsc();
        model.addAttribute("categories",categories);
        return "admin/categories/index";
    }


    /*
    *@ModelAttribute("category")
    public Category getCategory(){

        return new Category();
    }*/


    @GetMapping("/add")
    public String add(Category category) {

        return "admin/categories/add";
    }

    @PostMapping("/add")
    public String add(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/categories/add";
        }

        redirectAttributes.addFlashAttribute("message", "Category added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        String slug = category.getName().toLowerCase().replace(" ", "-");

        Category categoryExists = categoryRepository.findByName(category.getName());

        if ( categoryExists != null ) {
            redirectAttributes.addFlashAttribute("message", "Category exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("categoryInfo", category);

        } else {
            category.setSlug(slug);
            category.setSorting(100);

            categoryRepository.save(category);
        }

        return "redirect:/admin/categories/add";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Category category = categoryRepository.getById(id);
        model.addAttribute("category",category);
        return "admin/categories/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model){
        Category categoryCurrent = categoryRepository.getById(category.getId());

        if(bindingResult.hasErrors()){
            model.addAttribute("categoryName", categoryCurrent.getName());
            return "admin/categories/edit";
        }
        redirectAttributes.addFlashAttribute("message","Category edited");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        String slug = category.getName().toLowerCase().replace(" ","-");

        Category categoryExists = categoryRepository.findByName(category.getName());
        if(categoryExists != null){
            redirectAttributes.addFlashAttribute("message","Slug exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            }else {
            category.setSlug(slug);

            categoryRepository.save(category);
        }

        return "redirect:/admin/categories/edit/" + category.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes){
        categoryRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Category Deleted");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");
        return "redirect:/admin/categories";
    }

    @PostMapping("/reorder")
    public @ResponseBody String reorder(@RequestParam("id[]") int[] id){

        int count = 1;
        Category category;
        for(int categoryId: id){
            category = categoryRepository.getById(categoryId);
            category.setSorting(count);
            categoryRepository.save(category);
            count++;
        }

        return "ok";
    }
}




