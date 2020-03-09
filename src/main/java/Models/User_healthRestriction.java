package Models;

import javax.persistence.*;

@Entity
public class User_healthRestriction
{
    @Id @GeneratedValue
    @Column(name="USER_HEALTH_ID", nullable=false)
    private int userhealthId;

    @Column(name="VALUE", nullable=false)
    private boolean value;

    public User_healthRestriction(User user, Health_Restriction health_restriction) {
        this.user = user;
        this.health_restriction = health_restriction;
    }

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Health_Restriction health_restriction;
}