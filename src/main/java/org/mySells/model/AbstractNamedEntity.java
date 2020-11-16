package org.mySells.model;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    protected String nickname;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String nickname) {
        super(id);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + nickname + ')';
    }
}
