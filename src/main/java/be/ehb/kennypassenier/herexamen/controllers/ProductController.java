package be.ehb.kennypassenier.herexamen.controllers;

import be.ehb.kennypassenier.herexamen.entities.Product;
import be.ehb.kennypassenier.herexamen.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductRepo repo;

    @Autowired
    public ProductController(ProductRepo repo){
        this.repo = repo;
    }

    @GetMapping("products")
    @ResponseBody
    public Iterable<Product> getAllProducts(){
        return repo.findAll();
    }

    @GetMapping("products/category/{categoryName}")
    @ResponseBody
    public Iterable<Product> getAllProducts(@PathVariable(name = "categoryName") String categoryName){
        return repo.findByCategoryIgnoreCase(categoryName);
    }

    @PostMapping("products")
    @ResponseStatus(HttpStatus.OK)
    // Parameters meegeven die relevant zijn om het object aan te maken
    public void addProduct(@RequestParam("name") String name,
                              @RequestParam("category") String category,
                              @RequestParam("price") Double price,
                              @RequestParam("description") String description
                              ){
        // Nieuw product aanmaken met juiste informatie
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setCategory(category);
        // Object opslaan
        repo.save(newProduct);
    }

    @DeleteMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam("id") int id){
        repo.deleteById(id);
    }

}
