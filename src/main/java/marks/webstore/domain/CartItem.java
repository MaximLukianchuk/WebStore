package marks.webstore.domain;

import javax.persistence.*;

public class CartItem {
    private Long amount;

    private ProductType product;

    public CartItem() {
    }

    public CartItem(Long amount, ProductType product) {
        this.amount = amount;
        this.product = product;
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
}
