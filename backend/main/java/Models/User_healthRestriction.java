package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_healthRestriction
{
    @Id @GeneratedValue
    @Column(name="USER_HEALTH_ID", nullable=false)
    private int userhealthId;

    @Column(name="VALUE", nullable=false)
    private boolean value;

    public User_healthRestriction(int userhealthId, boolean value) {
        this.userhealthId=userhealthId;
        this.value=value;
    }

    public int getUserhealthId() {
        return userhealthId;
    }

    public void setUserhealthId(int userhealthId) {
        this.userhealthId = userhealthId;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}