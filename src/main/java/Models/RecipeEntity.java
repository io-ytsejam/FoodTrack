package Models;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RECIPE", schema = "TEST", catalog = "")
public class RecipeEntity {
    @Id
    @Column(name = "RECIPEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long recipeid;
    private String name;
    private String description;
    private char ifexternal;

    public RecipeEntity() {
    }

    public RecipeEntity(String name, String description, char ifexternal){
        this.name=name;
        this.description=description;
        this.ifexternal=ifexternal;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "IFEXTERNAL")
    public char getIfexternal() {
        return ifexternal;
    }

    public void setIfexternal(char ifexternal) {
        this.ifexternal = ifexternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeEntity that = (RecipeEntity) o;

        if (recipeid != that.recipeid) return false;
        if (ifexternal != that.ifexternal) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeid, name, description, ifexternal);
    }
}
