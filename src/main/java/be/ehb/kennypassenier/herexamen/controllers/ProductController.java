package be.ehb.kennypassenier.herexamen.controllers;

import be.ehb.kennypassenier.herexamen.entities.Delivery;
import be.ehb.kennypassenier.herexamen.entities.Product;
import be.ehb.kennypassenier.herexamen.repos.DeliveryDao;
import be.ehb.kennypassenier.herexamen.repos.ProductDao;
import be.ehb.kennypassenier.herexamen.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private DeliveryDao deliveryDao;

    @Autowired
    private ProductService productService;

    // Seeder voor onze producten
    // In onze config hebben we ervoor gezorgd dat telkens dat de applicatie opstart
    // We onze database opnieuw recreëren aan de hand van onze entities
    @PostConstruct
    public void seedProducts(){
        productService.seedProducts();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("products")
    @ResponseBody
    public Iterable<Product> getAllProducts(){
        return productDao.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("products/category/{categoryName}")
    @ResponseBody
    public Iterable<Product> getAllProducts(@PathVariable(name = "categoryName") String categoryName){
        return productDao.findByCategoryIgnoreCase(categoryName);
    }
    @CrossOrigin(origins = "*")
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
        productDao.save(newProduct);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam("id") int id){
        productDao.deleteById(id);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("deliveries")
    @ResponseBody
    public Iterable<Delivery> getAllDeliveries(){
        return deliveryDao.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("deliveries/owner/{ownerName}")
    @ResponseBody
    public Iterable<Delivery> getAllDeliveriesByOwner(@PathVariable(name = "ownerName") String ownerName){
        return deliveryDao.findByOwnerIgnoreCase(ownerName);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("deliveries")
    @ResponseStatus(HttpStatus.OK)
    // Parameters meegeven die relevant zijn om het object aan te maken
    public void addProduct(@RequestBody Delivery delivery){
        // Nieuwe bestelling aanmaken met juiste informatie
        deliveryDao.save(delivery);
    }

}
