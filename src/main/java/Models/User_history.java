package Models;

import javax.persistence.*;

@Entity
public class User_history
{
    @Id @GeneratedValue
    private int userhistoryId;

    public User_history(int userhistoryId) {
        this.userhistoryId=userhistoryId;
    }

    public int getUserhistoryId() {
        return userhistoryId;
    }

    public void setUserhistoryId(int userhistoryId) {
        this.userhistoryId = userhistoryId;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Recipe recipe;
}