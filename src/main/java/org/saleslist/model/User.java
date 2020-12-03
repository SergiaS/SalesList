package org.saleslist.model;

import org.saleslist.enums.Role;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static org.saleslist.util.ProductsUtil.DEFAULT_PROFIT_PER_DAY;

public class User extends AbstractBaseEntity {
    private String name;
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;
    private int profitPerDay = DEFAULT_PROFIT_PER_DAY;

    public User() {
    }

    public User(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public User(User u) {

    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, DEFAULT_PROFIT_PER_DAY, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, int profitPerDay, boolean enabled, Date registered, Collection<Role> roles) {
        this(id, name);
        this.email = email;
        this.password = password;
        this.profitPerDay = profitPerDay;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public int getProfitPerDay() {
        return profitPerDay;
    }

    public void setProfitPerDay(int profitPerDay) {
        this.profitPerDay = profitPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", profitPerDay=" + profitPerDay +
                '}';
    }
}
