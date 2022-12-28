package com.example.myshoppingapp.controllers;

import com.example.myshoppingapp.models.products.InputProductDTO;
import com.example.myshoppingapp.models.products.OutputProductDTO;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    @GetMapping("/product/addProduct")
    public String openPageForAddingProduct(Model model) {
        if (this.userService.getLoggedInUser() ==null) {
            return "index";
        }
        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);
        return "product/addProduct";
    }


    @PostMapping("/product/addProduct")
    public String doAddProduct(InputProductDTO inputProductDTO, Model model) {
        productService.addProduct(inputProductDTO);

        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);

        return "product/addProduct";
    }

    @GetMapping("/product/updateProduct/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {
        productService.updateProduct();
        model.addAttribute("product", product);
      return "product/updateProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        productService.deleteById(id);
        return "redirect:/product/addProduct";
    }
}
