package com.example.myshoppingapp.web;

import com.example.myshoppingapp.models.products.InputProductDTO;
import com.example.myshoppingapp.models.products.OutputProductDTO;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping("/product-list")
    public String openPageForAddingProduct(Model model) {
        if (this.userService.getLoggedInUser() == null) {
            return "index";
        }
        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);
        return "product/product-list";
    }


    @PostMapping("/product-list")
    public String doAddProduct(InputProductDTO inputProductDTO, Model model) {
        productService.addProduct(inputProductDTO);

        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);

        return "product/product-list";
    }



    @GetMapping("product/updateProduct/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/updateProduct";
    }

    @GetMapping("/moveUpProduct/{position}")
    public String moveUpProduct(@PathVariable(value = "position") long position) {
        productService.moveUpProduct(position);
        return "redirect:/product-list";
    }

    @GetMapping("/moveDownProduct/{position}")
    public String moveDownProduct(@PathVariable(value = "position") long position) {
        productService.moveDownProduct(position);
        return "redirect:/product-list";
    }


    @GetMapping("/deleteProduct/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        productService.deleteById(id);
        return "redirect:/product-list";
    }

}
