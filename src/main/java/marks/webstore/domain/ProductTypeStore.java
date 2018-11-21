package marks.webstore.domain;

import javax.persistence.*;

@Entity
@Table(name = "ProductTypeStore")
public class ProductTypeStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double price;
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    private ProductType product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    public ProductTypeStore() {
    }

    public ProductTypeStore(double price, int amount) {
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

}