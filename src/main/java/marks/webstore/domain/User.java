package marks.webstore.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String city;
    private Boolean allowedToCreateStores;
    private String email;
    private String activationCode;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = UserStore.class,
            cascade = {CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true, mappedBy = "user")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<UserStore> userStores;

    public User(String username, String password, String name, String surname, String city, String email, Boolean allowedToCreateStores) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.email = email;
        this.allowedToCreateStores = allowedToCreateStores;
    }

    public User() {
    }

    public List<Store> getStores() {
        return userStores.stream()
                .map(UserStore::getStore)
                .collect(Collectors.toList());
    }

    public Set<UserStore> getUserStores() {
        return userStores;
    }

    public void setUserStores(Set<UserStore> userStores) {
        this.userStores = userStores;
    }

    public Boolean getAllowedToCreateStores() {
        return allowedToCreateStores;
    }

    public void setAllowedToCreateStores(Boolean allowedToCreateStores) {
        this.allowedToCreateStores = allowedToCreateStores;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isRedactor() {
        return roles.contains(Role.REDACTOR);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}