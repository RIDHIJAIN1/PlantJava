package com.example.demo.Controller;
//import com.mysql.cj.util.StringUtils;
import com.example.demo.services.ProductService;
import com.example.demo.services.ProductServiceImpl;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class AdminController {

    private final ProductRepository productRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public AdminController(ProductRepository productRepository, ResourceLoader resourceLoader){
        this.productRepository = productRepository;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/admin")
    public String showAdmin(Model model){

      model.addAttribute("product",new Product());
        return "admin";
    }

    @GetMapping("/showadminproduct")
    public String showAdminProduct(Model model){
        ProductService productService = new ProductServiceImpl(productRepository);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "showadminproduct";
    }

    @PostMapping("/admin")
    public String handleProductFormSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute Product product) {
        try {
            // Save the file to a specified location on the server
            String uploadDir = "D:/demo (3)/demo/src/main/resources/static/images";

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String filePath = uploadDir + fileName;

            // Copies the input stream of the uploaded file to the specified path
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            // Set the image URL in the product
            product.setImage_url("/images/" + fileName); // Set the appropriate URL based on your server structure
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }

        // Save the product to the database
        productRepository.save(product);

        return "redirect:/admin";
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){

        ProductService productService = new ProductServiceImpl(productRepository);
        productService.deleteProduct(id);
            return ResponseEntity.ok().body("Product Deleted Successfully");

    }


}
