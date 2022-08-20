package be.ehb.kennypassenier.herexamen.services;

import be.ehb.kennypassenier.herexamen.entities.Product;
import be.ehb.kennypassenier.herexamen.repos.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    public void seedProducts(){
        Product newProduct = new Product();
        newProduct.setName("Leiband");
        newProduct.setDescription("Leiband voor honden");
        newProduct.setPrice(20.50);
        newProduct.setCategory("Allerlei");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Transportbox");
        newProduct.setDescription("Bench voor honden");
        newProduct.setPrice(100.00);
        newProduct.setCategory("Allerlei");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Hok");
        newProduct.setDescription("Hondenhok");
        newProduct.setPrice(250);
        newProduct.setCategory("Allerlei");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Puppy voedsel");
        newProduct.setDescription("Voedsel voor honden tot 1 jaar");
        newProduct.setPrice(25);
        newProduct.setCategory("Voeding");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Adult voedsel");
        newProduct.setDescription("Voedsel voor volwassen honden");
        newProduct.setPrice(26);
        newProduct.setCategory("Voeding");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Senior voedsel");
        newProduct.setDescription("Voesdel voor bejaarde honden");
        newProduct.setPrice(27.8);
        newProduct.setCategory("Voeding");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Bal");
        newProduct.setDescription("Plastic bal voor in de tuin");
        newProduct.setPrice(20.50);
        newProduct.setCategory("Speelgoed");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Knuffel");
        newProduct.setDescription("Teddybeer voor je puppy");
        newProduct.setPrice(21.50);
        newProduct.setCategory("Speelgoed");
        productDao.save(newProduct);

        newProduct = new Product();
        newProduct.setName("Kauwspeeltje");
        newProduct.setDescription("Iets om lekker in te knabbelen");
        newProduct.setPrice(22.50);
        newProduct.setCategory("Speelgoed");
        productDao.save(newProduct);
    }
}
