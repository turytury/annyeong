package com.tury.controller;

import com.tury.domain.Category;
import com.tury.domain.Product;
import com.tury.domain.ProductDisplay;
import com.tury.domain.ProductSearchForm;
import com.tury.mappers.CategoryMapper;
import com.tury.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value = "list.html")
    public String list(@ModelAttribute ProductSearchForm productSearchForm, Model model, HttpServletRequest request) {
        productSearchForm.setItemPerPage(10);
        productSearchForm.setTotalSize(productMapper.searchIds(productSearchForm).size());
        productSearchForm.handlePageToRedirect(request.getParameter("paginationPage"));

        Map<String, String> categoryParentMap = getCategoryParentMap();
        List<ProductDisplay> productDisplayList =
                productMapper.searchWithLimit(productSearchForm)
                        .stream()
                        .map(p -> new ProductDisplay(
                                p.getId(),
                                p.getName(),
                                String.format( "%.2f", p.getPrice()),
                                p.getCreateDate(),
                                categoryParentMap.get(p.getCategory().getParentId().toString()),
                                p.getCategory().getName()))
                        .collect(Collectors.toList());
        model.addAttribute("productList", productDisplayList);
        model.addAttribute("size", productDisplayList.size());
        model.addAttribute("categoryMap", getCategoryMap());
        model.addAttribute("categoryParentMap", getCategoryParentMap());
        return "product_list";
    }

    @RequestMapping(value = "create.html")
    public String create(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categoryMap", getCategoryMap());
        return "product_save";
    }

    @RequestMapping(value = "edit.html")
    public String edit(Model model, @RequestParam(value = "id", required = true) Integer productId) {
        Product product = productMapper.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categoryMap", getCategoryMap());
        return "product_save";
    }

    @RequestMapping(value = "save.html", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("product") Product product, HttpServletRequest request){
        if (request.getParameter("id").isEmpty()) {
            return saveCreate(model, product);
        }
        return saveEdit(model, product);
    }

    @RequestMapping(value = "delete.html")
    public String delete(Model model, @RequestParam(value = "id", required = true) Integer productId) {
        productMapper.delete(productId);
        model.addAttribute("updatemessage", "Deleted product successfully");
        return "redirect:list.html";
    }

    private String saveCreate(Model model, @ModelAttribute("product") Product product){
        product.setCreateDate(new Date(new java.util.Date().getTime()));
        productMapper.create(product);
        model.addAttribute("updatemessage", "Created product successfully");
        return "product_save";
    }

    private String saveEdit(Model model, @ModelAttribute("product") Product product){
        productMapper.update(product);
        model.addAttribute("updatemessage", "Updated product successfully");
        return "product_save";
    }
    
    private Map<Category, List<Category>> getCategoryMap(){
        List<Category> categoryParentList = categoryMapper.findByParentId(0);
        Map<Category, List<Category>> categoryMap = new HashMap<>();
        for(Category category : categoryParentList){
            categoryMap.put(category, categoryMapper.findByParentId(category.getId()));
        }
        return categoryMap;
    }

    private Map<String, String> getCategoryParentMap(){
        return categoryMapper.findByParentId(0)
                .stream()
                .collect(Collectors.toMap(c -> c.getId().toString(), c -> c.getName()));
    }
}