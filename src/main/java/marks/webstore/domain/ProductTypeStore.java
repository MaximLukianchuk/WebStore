package marks.webstore.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ProductTypeStore")
public class ProductTypeStore {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "Price")
    private double price;

    @Column (name = "Amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreId")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductTypeId")
    private ProductType productType;

    public ProductTypeStore() {
    }

    public ProductTypeStore(ProductType productType, Store store, double price, int amount) {
        this.productType = productType;
        this.store = store;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return price + " " + amount;
    }
}