package marks.webstore.service;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductType> findAllPublishedProducts() {
        return findAllProducts().stream()
                .filter(ProductType::getPublished)
                .collect(Collectors.toList());
    }

    public List<ProductType> findAllPublishedProductsReverse() {
        List<ProductType> productTypes = findAllProducts().stream()
                .filter(ProductType::getPublished)
                .collect(Collectors.toList());
        Collections.reverse(productTypes);

        return productTypes;
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
                              Double price,
                              Long amount,
                              String description,
                              Boolean isPublished,
                              Boolean isCanceled) {
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setPublished(isPublished);
        product.setCanceled(isCanceled);
        if (storeName != null) {
            productTypeStoreRepo.findAll().stream()
                    .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                    .findAny().get().setStore(storeRepo.findByName(storeName));
        }
        productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get().setAmount(amount);
        productTypeRepo.save(product);
        productTypeStoreRepo.save(productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get());
    }
}