package marks.webstore.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private Integer discount;
    private Boolean isPublished;
    private Boolean isCanceled;

    private String filename;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ProductTypeStore.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, mappedBy = "product")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductTypeStore> productTypeStores;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ProductPublicationHistory.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, mappedBy = "product")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductPublicationHistory> productPublicationHistories;


    public ProductType() {
    }

    public ProductType(String name, Double price, String description, Integer discount, Boolean isPublished, Boolean isCanceled) {
        this.name = name;
        this.price = (double)Math.round(price * 100d) / 100d;
        this.description = description;
        this.discount = discount;
        this.isPublished = isPublished;
        this.isCanceled = isCanceled;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public List<ProductPublicationHistory> getProductPublicationHistories() {
        return productPublicationHistories;
    }

    public void setProductPublicationHistories(List<ProductPublicationHistory> productPublicationHistories) {
        this.productPublicationHistories = productPublicationHistories;
    }
}
