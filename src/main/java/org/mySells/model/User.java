package org.mySells.model;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static org.mySells.util.ProductsUtil.DEFAULT_PROFIT_PER_DAY;

public class User extends AbstractNamedEntity {

    private String password;

    private boolean profited = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private int profitsPerDay = DEFAULT_PROFIT_PER_DAY;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getNickname(), u.getPassword(), u.getProfitsPerDay(), u.isProfited(), u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String nickname, String password, Role role, Role... roles) {
        this(id, nickname, password, DEFAULT_PROFIT_PER_DAY, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String nickName, String password, int profitsPerDay, boolean profited, Date registered, Set<Role> roles) {
        super(id, nickName);
        this.password = password;
        this.profitsPerDay = profitsPerDay;
        this.profited = profited;
        this.registered = registered;
        setRoles(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isProfited() {
        return profited;
    }

    public void setProfited(boolean profited) {
        this.profited = profited;
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

    public int getProfitsPerDay() {
        return profitsPerDay;
    }

    public void setProfitsPerDay(int profitsPerDay) {
        this.profitsPerDay = profitsPerDay;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", profited=" + profited +
                ", roles=" + roles +
                ", profitsPerDay=" + profitsPerDay +
                '}';
    }
}
