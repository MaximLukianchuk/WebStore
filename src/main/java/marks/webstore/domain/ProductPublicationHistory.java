package marks.webstore.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ProductPublicationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "product_type_id")
    private ProductType product;

    public ProductPublicationHistory() {
    }

    public ProductPublicationHistory(Date date, String reason) {
        this.date = date;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ProductType getProduct() {
        return product;
    }

    public void setProduct(ProductType product) {
        this.product = product;
    }
}
