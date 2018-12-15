package marks.webstore.service;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Autowired
    private ProductTypeStoreRepo productTypeStoreRepo;

    @Autowired
    private StoreRepo storeRepo;

    public List<ProductType> findAllProductsReverse() {
        List<ProductType> productTypes = productTypeRepo.findAll();
        Collections.reverse(productTypes);

        return productTypes;
    }

    public List<ProductType> findAllProducts() {
        return productTypeRepo.findAll();
    }

    public void saveProduct(ProductType productType) {
        productTypeRepo.save(productType);
    }

    public void deleteProduct(ProductType productType) {
        productTypeRepo.delete(productType);
    }

    public void updateProduct(ProductType product,
                              String name,
                              String storeName,
                              Float price,
                              Long amount,
                              String description) {
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get().setStore(storeRepo.findByName(storeName));
        productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get().setAmount(amount);
        productTypeRepo.save(product);
        productTypeStoreRepo.save(productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get());
    }
}