package by.ruslan.radzevich.carsharingservice.model;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    USER, ADMIN;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
