package marks.webstore.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, REDACTOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
