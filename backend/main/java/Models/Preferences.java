package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Preferences
{
    @Id @GeneratedValue
    @Column(name="PREFERENCES_ID", nullable=false)
    private int preferencesId;

    @Column(name="CATEGORY", nullable=false)
    private String category;

    public Preferences(int preferencesId, String category) {
        this.preferencesId = preferencesId;
        this.category = category;
    }

    public int getPreferencesId() {
        return preferencesId;
    }

    public void setPreferencesId(int preferencesId) {
        this.preferencesId = preferencesId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}