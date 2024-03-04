package com.example.demo.Controller;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product")
    public String showProduct(Model model){
        model.addAttribute("product",productService.getAllProducts());

        return "product";
    }
}
