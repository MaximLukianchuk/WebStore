package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.Store;
import marks.webstore.service.ProductService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class serachController {

    @Autowired
    private ProductService productService;

    @Autowired
    StoreService storeService;

    @GetMapping("/search")
    public String getitem(
            @RequestParam("name") String itemid,
            Map<String, Object> model) {
        List<Store> stores = storeService.findAllPublishedStores().stream()
                .filter(store -> store.getName().toLowerCase().contains(itemid.toLowerCase()))
                .collect(Collectors.toList());
        List<ProductType> products = productService.findAllPublishedProductsReverse().stream()
                .filter(productType -> productType.getName().toLowerCase().contains(itemid.toLowerCase()))
                .collect(Collectors.toList());
        model.put("stores", stores);
        model.put("producttypes", products);
        model.put("searchWord", itemid);
        model.put("storesAmount", stores.size());
        model.put("productsAmount", products.size());
        return "search";
    }
}
