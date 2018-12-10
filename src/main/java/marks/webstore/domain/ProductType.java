package marks.webstore.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float price;
    private String description;
    private Integer discount;

    private String filename;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ProductTypeStore.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, mappedBy = "product")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductTypeStore> productTypeStores;


    public ProductType() {
    }

    public ProductType(String name, Float price, String description, Integer discount) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<ProductTypeStore> getProductTypeStores() {
        return productTypeStores;
    }

    public void setProductTypeStores(List<ProductTypeStore> productTypeStores) {
        this.productTypeStores = productTypeStores;
    }
}
