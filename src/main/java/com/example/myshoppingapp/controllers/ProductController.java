package com.example.myshoppingapp.controllers;

import com.example.myshoppingapp.models.products.InputProductDTO;
import com.example.myshoppingapp.models.products.OutputProductDTO;
import com.example.myshoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

  @Autowired
    public ProductController(ProductService productService) {
      this.productService = productService;
  }


    @GetMapping("/product/addProduct")
    public String openPageForAddingProduct(Model model) {

        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);
        return "product/addProduct";
    }


    @PostMapping("/product/addProduct")
    public String doAddProduct(InputProductDTO inputProductDTO, Model model) {
        productService.addProduct(inputProductDTO);

        String outputProducts = this.productService.getAllProducts();
        System.out.println("outputProducts...");
        model.addAttribute("showAllProducts", outputProducts);

        return "product/addProduct";
    }
}
