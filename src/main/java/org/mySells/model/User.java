package org.mySells.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static org.mySells.util.ProductsUtil.PROFIT_PER_DAY;

public class User extends AbstractNamedEntity {

    private String password;

    private boolean profited = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private int profitsPerDay = PROFIT_PER_DAY;

    public User(Integer id, String nickname, String password, Role role, Role... roles) {
        this(id, nickname, password, PROFIT_PER_DAY, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String nickName, String password, int profitsPerDay, boolean profited, Set<Role> roles) {
        super(id, nickName);
        this.password = password;
        this.profited = profited;
        this.roles = roles;
        this.profitsPerDay = profitsPerDay;
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
