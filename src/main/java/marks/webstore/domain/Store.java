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
    private String description;
    private Boolean isPublished;

    private String filename;

    @OneToMany(fetch=FetchType.EAGER, targetEntity = ProductTypeStore.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval=true, mappedBy="store")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductTypeStore> productTypeStores;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = UserStore.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, mappedBy = "store")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserStore> userStores;

    public Store() {
    }

    public Store(String name, String description, Boolean isPublished) {
        this.name = name;
        this.description = description;
        this.isPublished = isPublished;
    }

    public List<UserStore> getUserStores() {
        return userStores;
    }

    public void setUserStores(List<UserStore> userStores) {
        this.userStores = userStores;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }
}
