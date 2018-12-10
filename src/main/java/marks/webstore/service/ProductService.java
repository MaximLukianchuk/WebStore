package marks.webstore.service;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    public List<ProductType> findAllProductsReverse() {
        List<ProductType> productTypes = productTypeRepo.findAll();
        Collections.reverse(productTypes);

        return productTypes;
    }

    public List<ProductType> findAllProducts() {
        return productTypeRepo.findAll();
    }

    public void saveProduct (ProductType productType) {
        productTypeRepo.save(productType);
    }
}
