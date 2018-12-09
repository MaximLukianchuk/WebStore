package marks.webstore.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;

    private String filename;

    @OneToMany(fetch=FetchType.EAGER, targetEntity = ProductTypeStore.class, cascade=CascadeType.REMOVE, orphanRemoval=true, mappedBy="store")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductTypeStore> productTypeStores;

    public Store() {
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<ProductTypeStore> getProductTypeStores() {
        return productTypeStores;
    }

    public void setProductTypeStores(List<ProductTypeStore> productTypeStores) {
        this.productTypeStores = productTypeStores;
    }
}
