package marks.webstore.domain;

import javax.persistence.*;

@Entity
@Table(name = "ProductTypeStore")
public class ProductTypeStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "product_type_id")
    private ProductType product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    public ProductTypeStore() {
    }

    public ProductTypeStore(ProductType product, Store store, Long amount) {
        this.product = product;
        this.store = store;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ProductType getProduct() {
        return product;
    }

    public void setProduct(ProductType product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}